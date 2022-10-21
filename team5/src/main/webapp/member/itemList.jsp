<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<link rel ="stylesheet" href="css/Shop.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="category" value="${(empty param.c)?00:param.c}"></c:set>
<c:set var="page" value="${(empty param.p)?1:param.p}"></c:set>
<c:set var="startNum" value="${(param.p==null or param.p <= 3)?1:param.p-2}"></c:set>
<c:set var="endNum" value="${fn:substringBefore(Math.ceil(count/9), '.')}"></c:set>

<header>
<a href="main.jsp">
	쇼룸으로 이동
</a>
</header>
<div class="lists">
    <ul class="nav2">
    	<li class="category">
    		<a href="itemList.do">
    		<p>
     		전체
     	</p>
    		</a>
    	</li>
        <li class="category">
        	<a href="itemList.do?&p=${1}&c=${200}">
        	<p>
             바닥
            <p>
        	</a>
        </li>
        <li class="category">
        	<a href="itemList.do?&p=${1}&c=${400}">
        	<p>
            	물건
           	<p> 
            </a>
        </li>
        <li class="category">
        	<a href="itemList.do?&p=${1}&c=${600}">
        	<p>
             장식
            </p>
         </a>
        </li>
    </ul>
</div>
<section id=item_section>
    <c:if test="${empty itemList}">
    <h2> 아이템이 존재하지 않습니다.</h2>
    </c:if>
    	<div class="shop_Form">
		        <ul class="item_list">
			   		<c:forEach var="item" items="${itemList}">
		            <li class="items"> 
		                <div class="flip">  
		                    <div class="card">
		                    <!-- 앞면 -->
		                    <div class="front">
                       			<c:choose>
									<c:when test="${empty item.pictureurl}">
										<img src="image/noimage.jpg">
									</c:when>
									<c:otherwise>
										<img src="upload/${item.pictureurl}">
									</c:otherwise>
								</c:choose>
		                    </div>
		                    <!-- 뒷면 -->
		                    <div class="back">
		                        <p>${item.name}</p>
		                        <p>${item.category}</p>
		                        <c:if test="${item.equip == 0}">
			                        <a href="equipItem.do?code=${item.code}&userid=${loginUser.userid}">
			                        	<input type="button" value="배치하기">                 
			                        </a>
		                        </c:if>
		                        <c:if test="${item.equip == 1}">
			                        <a href="unequipItem.do?code=${item.code}&userid=${loginUser.userid}">
			                        	<input type="button" value="배치 해제하기">                 
			                        </a>
		                        </c:if>
		                    </div>
		                    </div>
		                </div>
		            </li>
	        	</ul>
  			</c:forEach> 
    	</div>
<table id=channel style="padding: 100px;" align="center">
		<tr>
			<td colspan="7" align="center">
				<ul>
					<c:if test="${page-3>0}"><!-- 3페이지부터 페이지 이동이 생김 -->
					<li>
						<a class="btn btn-prev" href="board?p=${page-1}&k=${param.k}&c=${category}">이전</a>
					</li>
					</c:if>
					<c:if test="${page-3<=0}"><li></li></c:if>
					<c:choose>
						<c:when test="${page > 3}">
							<c:choose>
								<c:when test="${page+2 > endNum}">		<!-- 마지막 3개 페이지 처리 ==> 마지막 세개 페이지에서는 다음이 없기 때문에 마지막 페이지는 고정된다. -->
									<c:forEach var="i" begin="${endNum-4}" end="${endNum}">
										<li>
											<a style="color:${(page==(i))?'cornflowerblue':''}" href="?p=${i}&k=${param.k}&c=${category}">${i}</a>
										</li>
									</c:forEach>			
								</c:when>
								<c:otherwise>
									<c:forEach var="i" begin="${page-2}" end="${page+2}">	<!-- 페이지 한개씩 바뀌기 -->
										<li>
											<a style="color:${(page==(i))?'cornflowerblue':''}" href="?p=${i}&k=${param.k}&c=${category}">${i}</a>
										</li>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:if test="${startNum+4 > endNum}">
									<c:forEach var="i" begin="${startNum}" end="${endNum}">
										<li>
											<a style="color:${(page==(i))?'cornflowerblue':''}" href="?p=${i}&k=${param.k}&c=${category}">${i}</a>
										</li>
									</c:forEach>
							</c:if>
							<c:if test="${startNum+4 <= endNum}">
									<c:forEach var="i" begin="0" end="4">
										<li>
											<a style="color:${(page==(i+startNum))?'cornflowerblue':''}" href="?p=${i+startNum}&k=${param.k}&c=${category}">${i+startNum}</a>
										</li>
									</c:forEach>
							</c:if>							
						</c:otherwise>
					</c:choose>
					<c:if test="${page+2 < endNum}"> <!-- 더 넘어갈 페이지가 있는 경우 -->
						<a class="btn btn-next" href="?p=${page+1}&k=${param.k}&c=${category}">
							<li>
							다음
							</li>
						</a>
					</c:if>
					<c:if test="${page == endNum}"><li></li></c:if>
				</ul>
		</tr>
	</table>
</section>
</body>
</html>