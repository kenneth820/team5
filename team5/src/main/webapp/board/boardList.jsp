<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/product.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="page" value="${(empty param.p)?1:param.p}"></c:set>
<c:set var="startNum" value="${(param.p==null or param.p <= 3)?1:param.p-2}"></c:set>
<c:set var="endNum" value="${fn:substringBefore(Math.ceil(count/2), '.')}"></c:set>
<div id="wrap" align="center">
<h2>게시글 리스트</h2>
	<table class="list">
		<tr>
			<td colspan="5" style="border:white; text-algin:right; padding-right:10px;">
				<div class="search">
					<form action="board?k=${param.k}&p=${1}">
						<input type="text" name="k" value="${param.keyword}">
						<input type="submit" value="검색">
					</form>
				</div>
			</td>
		</tr>
		<tr>
			<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회</th>
		</tr>
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.num}</td>
				<td><a href="board?num=${board.num}">${board.title}</a></td>
				<td>${board.name}</td>
				<td>${board.writedate}</td>
				<td>${board.readcount}</td>
				
			</tr>
		</c:forEach>
	</table>
</div>

<!--  -->

<!-- 페이징 -->
<!-- 페이징 번호, 디비에서 전체 페이지 수 조회 -->
<!-- 5개 페이지 번호를 표시, 5번이 넘어가면 "다음" 버튼을 통해 이동, "이전" 버튼을 통해 복귀 -->
<!-- 이전 12345 다음 -->

<!-- 게시물 수 / 페이지 번호 / 페이지 시작 번호(startNum) / 전체 페이지 끝번호(endNum) -->
<!-- 페이지 시작 번호 : stratNum -->
<!-- startNum = page - (page-1) % 보여줄 페이지 수; -->
<!-- 끝 번호 : lastNum -->
<!-- lastNum = Math.ceil(전체게시물/보여줄 게시물 수) -->
<!-- fn:substirngBefore(String1, String2) = String1을 String2 기준 앞부분만 자르기-->
<div>게시물 총 개수 : ${count} </div>
<div>페이지 번호 : ${page}</div>
<div>페이지 시작번호 : ${startNum} </div>
<div>페이지 끝번호 : ${endNum} </div>
<div>페이지 번호2 : ${param.p}</div>
<!-- 12345 -->
<!-- 678910 -->


<!-- 이전, 다음페이지 생성 -->

<!-- (시작번호-1) 값이 0보다 커야함 -->
<table id=channel style="padding: 100px;" align="center">
		<tr>
			<td colspan="7" align="center">
				<ul>
					<c:if test="${page-3>0}"><!-- 3페이지부터 페이지 이동이 생김 -->
					<li>
						<a class="btn btn-prev" href="board?p=${page-1}&k=${param.k}">이전</a>
					</li>
					</c:if>
					<c:if test="${page-3<=0}"><li></li></c:if>
					<c:choose>
						<c:when test="${page > 3}">
							<c:choose>
								<c:when test="${page+2 > endNum}">		<!-- 마지막 3개 페이지 처리 ==> 마지막 세개 페이지에서는 다음이 없기 때문에 마지막 페이지는 고정된다. -->
									<c:forEach var="i" begin="${endNum-4}" end="${endNum}">
										<li>
											<a style="color:${(page==(i))?'cornflowerblue':''}" href="?p=${i}&k=${param.k}">${i}</a>
										</li>
									</c:forEach>			
								</c:when>
								<c:otherwise>
									<c:forEach var="i" begin="${page-2}" end="${page+2}">	<!-- 페이지 한개씩 바뀌기 -->
										<li>
											<a style="color:${(page==(i))?'cornflowerblue':''}" href="?p=${i}&k=${param.k}">${i}</a>
										</li>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:if test="${startNum+4 > endNum}">
									<c:forEach var="i" begin="${startNum}" end="${endNum}">
										<li>
											<a style="color:${(page==(i))?'cornflowerblue':''}" href="?p=${i}&k=${param.k}">${i}</a>
										</li>
									</c:forEach>
							</c:if>
							<c:if test="${startNum+4 <= endNum}">
									<c:forEach var="i" begin="0" end="4">
										<li>
											<a style="color:${(page==(i+startNum))?'cornflowerblue':''}" href="?p=${i+startNum}&k=${param.k}">${i+startNum}</a>
										</li>
									</c:forEach>
							</c:if>							
						</c:otherwise>
					</c:choose>
					<c:if test="${page+2 < endNum}"> <!-- 더 넘어갈 페이지가 있는 경우 -->
						<a class="btn btn-next" href="?p=${page+1}&k=${param.k}">
							<li>
							다음
							</li>
						</a>
					</c:if>
					<c:if test="${page == endNum}"><li></li></c:if>
				</ul>
		</tr>
</table>
<%-- <c:if test="${startNum+4 < lastNum}">
	<a href="?p=${startNum+5}">
		다음
	</a>
</c:if>
 --%>
<%-- <c:if test="${startNum+4 >= lastNum}">
	<a href="?p=${startNum+5}">
		<span onclick="alert('다음 페이지가 없습니다.');">다음</span>
	</a>
</c:if> --%>


 
 <!-- ============================================ -->
 <!-- ============================================ -->
<!-- 처음 5개 페이지 링크 표시 -->
<!-- 페이지 시작 번호: ?p=3 => 3-(3-1) % 5 = 1 -->
<!-- startNum = page - (page-1) % 보여줄 페이지 수; -->
<%-- <c:set var="page" value="${param.p}"></c:set>
<c:set var="startNum" value="${page - (page-1) % 5}"></c:set>
<c:forEach var="i" begin="0" end="4">
	<a href="?p=${i+startNum}">${i+startNum}</a>
</c:forEach> --%>

<!-- ============================================ -->
<!-- ============================================ -->
<!-- 처음 5개 페이지 링크 표시 -->
<%-- <c:forEach var="i" begin="0" end="4">
	<a href="?p=${i+1}">${i+1}</a>
</c:forEach> --%>

<!-- ============================================ -->
<!-- ============================================ -->
<!-- 1 2 3 4 5 -->
<!-- <a href="?p=1">1</a>
<a href="?p=2">2</a>
<a href="?p=3">3</a>
<a href="?p=4">4</a>
<a href="?p=5">5</a> -->

</body>
</html>