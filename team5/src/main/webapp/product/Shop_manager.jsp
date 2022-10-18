<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel ="stylesheet" href="css/Shop.css">
<title>관리자페이지 - 상점</title>
</head>
<body>
<c:set var="category" value="${(empty param.c)?00:param.c}"></c:set>
<c:set var="page" value="${(empty param.p)?1:param.p}"></c:set>
<c:set var="startNum" value="${(param.p==null or param.p <= 3)?1:param.p-2}"></c:set>
<c:set var="endNum" value="${fn:substringBefore(Math.ceil(count/9), '.')}"></c:set>

	    <header>
        <a href="manager.jsp">관리자 메인 페이지</a>
    </header>
    <h3>관리자 페이지-상점입니다.</h3>
    <nav>
    	<div>
    		<a id=logo_img href="productList.do">
		        <img class="logo_img" src="image/Logo.jpg"></img>
    		</a>
   		  	<form id=SearchForm value="name" action="productList.do?k=${param.k}&p=${1}&c=${category}">
		        <input id="Search" type="text" name="k" placeholder="검색어를 입력해주세요.">
		        <input id="submit" type="image" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTDlkdG6O5lJVaXJ40p34v1j4CUJDpb7Ml7g&usqp=CAU">
			</form>
    	</div>
    </nav>
    <div class="lists">
        <ul class="nav">
        	<li class="category">
        		<a href="productList.do">
	        		<p>
		        		전체
		        	</p>
        		</a>
        	</li>
            <li class="category">
            	<a href="productList.do?k=${param.k}&p=${1}&c=${200}">
	            	<p>
		                바닥
	                <p>
            	</a>
            </li>
            <li class="category">
            	<a href="productList.do?k=${param.k}&p=${1}&c=${400}">
	            	<p>
	                	물건
	               	<p> 
                </a>
            </li>
            <li class="category">
            	<a href="productList.do?k=${param.k}&p=${1}&c=${600}">
	            	<p>
		                장식
	                </p>
	            </a>
            </li>
        </ul>
    </div>
    <article>
      <div class="menubar">
    	<ul class="right-menu">
	        <li>
		        <div class="profilePhoto">
               			<c:choose>
							<c:when test="${empty loginUser.pictureurl}">
								<img src="image/noimage.jpg">
							</c:when>
							<c:otherwise>
								<img src="profilePhoto/${loginUser.pictureurl}">
							</c:otherwise>
						</c:choose>
		        </div>
	        </li>
			<li>
	            <div class="userInfo">
	                <p class="name">${loginUser.name}</p>
	            </div>
			</li>
    	</ul>
       </div>
	    <div class="shopping_list">
        </div>
            <div class="B_or_N">
            <a href="writeProduct.do">
            <input type="button" value="상품 등록">
            </a>
            </div>
    </article>
        <section>
    	<form class="shop_Form">
	        <ul class="item_list">
        		<c:forEach var="product" items="${pageList}">
	            <li class="items"> 
	                <div class="flip">  
	                    <div class="card">
	                    <!-- 앞면 -->
	                    <div class="front">
							<c:choose>
								<c:when test="${empty product.pictureurl}}">
									<img src="profile.jpg">
								</c:when>
								<c:otherwise>
									<img src="upload/${product.pictureurl}">
								</c:otherwise>
							</c:choose>
							</div>
	                    <!-- 뒷면 -->
	                    <div class="back">
	                        <p>${product.name}</p>
	                        <p>${product.price}</p>
	                        <p>${product.reg_date}</p>
	                        <a href="updateProduct.do?code=${product.code}">
	                        <input type="button" value="상품 수정">
	                        </a>
	                        <a href="deleteProduct.do?code=${product.code}">	                        
	                        <input type="button" value="상품 삭제">                 
	                        </a>
	                    </div>
	                    </div>
	                </div>
	            </li>
	            </c:forEach>
	        </ul>
       </form>

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

    <footer style="border-color: grey;"> footer</footer>
</body>
</html>