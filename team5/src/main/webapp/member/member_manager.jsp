<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/product.css">
<meta charset="UTF-8">
<title>회원 리스트 페이지</title>
</head>
<body>
<c:set var="page" value="${(empty param.p)?1:param.p}"></c:set>
<c:set var="startNum" value="${(param.p==null or param.p <= 3)?1:param.p-2}"></c:set>
<c:set var="endNum" value="${fn:substringBefore(Math.ceil(count/9), '.')}"></c:set>
<c:set var="column" value="${(empty param.c)?'userid':param.c}"></c:set>

<div id="wrap" align="center">

	<a href="manager.jsp">관리자 메인 페이지</a>
	
	<h2>회원 리스트 - 관리자 페이지</h2>
	
	<table class="list">
		<tr>
			<th>이름</th><th>아이디</th><th>비밀번호</th><th>이메일</th><th>전화번호</th><th>포인트</th><th>사진</th><th>회원수정</th><th>회원삭제</th>
		</tr>
		<c:forEach var="member" items="${memberList}">
			<tr>
				<td>${member.name}</td>
				<td>${member.userid}</td>
				<td>${member.pwd}</td>
				<td>${member.email}</td>
				<td>${member.phone}</td>
				<td>${member.point}</td>
				<td>${member.pictureurl}</td>
				<td><a href="updateMember.do?userId=${member.userid}">회윈 수정</a></td>
				<td><a href="deleteMember.do?userId=${member.userid}">회원 삭제</a></td>	
			</tr>
		</c:forEach>
	</table>
</div>

<div class="search">
	<form action="memberList.do?k=${param.k}&p=${1}&c=${column}">
		<select name="c">
			<option value="userid">유저아이디</option>
			<option ${(param.c=="name")?"selected":""} value="name">이름</option>
		</select>
		<input type="text" name="k" value="${param.k}">
		<input type="submit" value="검색">
	</form>
</div>
<div>
	<table id=channel style="padding: 100px;" align="center">
			<tr>
				<td colspan="7" align="center">
					<ul>
						<c:if test="${page-3>0}"><!-- 3페이지부터 페이지 이동이 생김 -->
						<li>
							<a class="btn btn-prev" href="memberList.do?p=${page-1}&k=${param.k}&c=${column}">이전</a>
						</li>
						</c:if>
						<c:if test="${page-3<=0}"><li></li></c:if>
						<c:choose>
							<c:when test="${page > 3}">
								<c:choose>
									<c:when test="${page+2 > endNum}">		<!-- 마지막 3개 페이지 처리 ==> 마지막 세개 페이지에서는 다음이 없기 때문에 마지막 페이지는 고정된다. -->
										<c:forEach var="i" begin="${endNum-4}" end="${endNum}">
											<li>
												<a style="color:${(page==(i))?'cornflowerblue':''}" href="?p=${i}&k=${param.k}&c=${column}">${i}</a>
											</li>
										</c:forEach>			
									</c:when>
									<c:otherwise>
										<c:forEach var="i" begin="${page-2}" end="${page+2}">	<!-- 페이지 한개씩 바뀌기 -->
											<li>
												<a style="color:${(page==(i))?'cornflowerblue':''}" href="?p=${i}&k=${param.k}&c=${column}">${i}</a>
											</li>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:if test="${startNum+4 > endNum}">
										<c:forEach var="i" begin="${startNum}" end="${endNum}">
											<li>
												<a style="color:${(page==(i))?'cornflowerblue':''}" href="?p=${i}&k=${param.k}&c=${column}">${i}</a>
											</li>
										</c:forEach>
								</c:if>
								<c:if test="${startNum+4 <= endNum}">
										<c:forEach var="i" begin="0" end="4">
											<li>
												<a style="color:${(page==(i+startNum))?'cornflowerblue':''}" href="?p=${i+startNum}&k=${param.k}&c=${column}">${i+startNum}</a>
											</li>
										</c:forEach>
								</c:if>							
							</c:otherwise>
						</c:choose>
						<c:if test="${page+2 < endNum}"> <!-- 더 넘어갈 페이지가 있는 경우 -->
							<a class="btn btn-next" href="?c=${column}&p=${page+1}&k=${param.k}">
								<li>
								다음
								</li>
							</a>
						</c:if>
						<c:if test="${page == endNum}"><li></li></c:if>
					</ul>
			</tr>
	</table>
</div>
</body>
</html>