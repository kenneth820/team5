<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel ="stylesheet" href="css/Shop.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Dongle&display=swap" rel="stylesheet">

<title>상점</title>
</head>
<body>
<c:set var="category" value="${(empty param.c)?00:param.c}"></c:set>
<c:set var="page" value="${(empty param.p)?1:param.p}"></c:set>
<c:set var="startNum" value="${(param.p==null or param.p <= 3)?1:param.p-2}"></c:set>
<c:set var="endNum" value="${fn:substringBefore(Math.ceil(count/9), '.')}"></c:set>
    <header>
        <a href="login.do">쇼룸으로 이동</a>
    </header>
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
		                <p class="point">${loginUser.point}p</p>
		            </div>
				</li>
	    	</ul>
        </div>
        <div class="S_L">
	        <p>
    	        <a href="cartList.do?userid=${loginUser.userid}"><h2>장바구니로 이동</h2></a>
            </p>
        </div>
        <div class="Cart_List">
        <table id=cartTable border="1" width="100%">
	        	<tr>
		        	<th width=25%>사진</th>
		        	<th colspan="2">상품정보</th>
		        </tr>
        		<c:forEach var="Cart" items="${CartList}">
        		<tr>
		        	<td rowspan="2">
               			<c:choose>
							<c:when test="${empty Cart.pictureurl}">
								<img src="image/noimage.jpg">
							</c:when>
							<c:otherwise>
								<img src="upload/${Cart.pictureurl}"
								onerror="this.onerror=null; this.src='./image/shop/${Cart.picturerl}';" 
								alt="쇼룸" class="image">									
								>
							</c:otherwise>
						</c:choose>
		        	</td>	
		        	<td>
		        		${Cart.name}
		        	</td>	
		        </tr>
				<tr>	   
		        	<td>
		        		${Cart.price}
		        	</td>	
				</tr>
				</c:forEach>
        </table>
        </div>
    </article>
    <section>
    <c:if test="${empty pageList}">
    <h2>모든 상품을 구매하셨습니다.</h2>
    </c:if>
    	<div class="shop_Form">
	   		<c:forEach var="product" items="${pageList}">
		        <ul class="item_list">
		            <li class="items"> 
		                <div class="flip">  
		                    <div class="card">
		                    <!-- 앞면 -->
		                    <div class="front">
                       			<c:choose>
									<c:when test="${empty product.pictureurl}">
										<img src="image/noimage.jpg">
									</c:when>
									<c:otherwise>
										<img src="upload/${product.pictureurl}"
									onerror="this.onerror=null; this.src='./image/shop/${product.pictureUrl}';" 
									alt="쇼룸" class="image">									
										>
									</c:otherwise>
								</c:choose>
		                    </div>
		                    <!-- 뒷면 -->
		                    <div class="back">
		                        <p>${product.name}</p>
		                        <p>${product.price}</p>
		                        <a href="addCart.do?code=${product.code}&userid=${loginUser.userid}">
		                        	<input type="button" value="장바구니">                 
		                        </a>
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
							<li>다음</li>
						</a>
					</c:if>
					<c:if test="${page == endNum}"><li></li></c:if>
				</ul>
			</tr>
		</table>
    </section>
</body>
</html>