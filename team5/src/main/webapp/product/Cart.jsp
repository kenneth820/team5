<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel ="stylesheet" type="text/css" href="css/Shop.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Dongle&display=swap" rel="stylesheet">

</head>
<body>
    <header>
        <a href="productList.do">상점으로 이동</a>
    </header>
    <nav>
    	<div>
    		<a id=logo_img href="productList.do">
		        <img class="logo_img" src="image/Logo.jpg"></img>
    		</a>
		  	<form id=SearchForm value="name" action="searchProduct.do">
		        <input id="Search" type="text" name="keyword" placeholder="검색어를 입력해주세요.">
		        <input id="submit" type="image" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTDlkdG6O5lJVaXJ40p34v1j4CUJDpb7Ml7g&usqp=CAU">
			</form>
    	</div>
    </nav>
 
    <section style="align-items: center;">
        <div style="width: 100%; padding-top: 5%;">
	        <div class="Shopping_Cart">
	            <h2>장바구니</h2>
	            <table id=cartTable style="width: 100%;">
	                <tr>
	                    <th id="cartImg">상품 사진</th><th>이름</th><th>가격</th><th>삭제</th>
	                </tr>
	                <c:forEach var="Cart" items="${CartList}">
	                <tr>
	                    <td>
							<c:choose>
							<c:when test="${empty Cart.pictureurl}">
								<img id=cartImg src="image/noimage.jpg">
							</c:when>
							<c:otherwise>
								<img id=cartImg src="upload/${Cart.pictureurl}">
							</c:otherwise>
							</c:choose>
						</td>
	                    <td>${Cart.name}</td>
	                    <td>${Cart.price}</td>
	                    <td><a href="deleteCart.do?cartid=${Cart.cartid}">상품 삭제</a></td>	
	                </tr>
	                </c:forEach>
	                <tr>
		                <td colspan="4">
		                	<c:choose>
		                	<c:when test="${CartList.size() == 0}">
		                	 <h2>장바구니가 비었습니다.</h2>
		                	 <a href="productList.do">
		                	 	<input id=update type="button" value="상점으로 이동">
 		                	 </a>
		                	</c:when>
		                	<c:otherwise>
			                <a href="deleteAllCart.do?userid=${loginUser.userid}">
			                	<input id=button type=button value="상품전체삭제">
			                </a>
			                </c:otherwise>
			                </c:choose>
		                </td>
	                </tr>
	            </table>
	        <%-- ${message} --%>
	        </div>
        </div>
    </section>
    <article class="Cart_result">
        <div class="menubar">
        	<ul class="right-menu">
			        <li>
				        <div class="profilePhoto">
	               			<c:choose>
								<c:when test="${empty loginUser.pictureurl}">
									<img src="image/noimage.jpg">
								</c:when>
								<c:otherwise>
								<img src="profilePhoto/${loginUser.pictureurl}" 
								onerror="this.onerror=null; this.src='./image/noimage.jpg';" 
								alt="트랜드룸" class="image">									

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
   		<table id=resultForm>
		    <tbody>
		        <tr>
			        <td id="resultForm table">
			           보유한 P:     
			        </td>
			        <td>
	                    ${loginUser.point}
	                </td>
                </tr>
                <tr>
	                <td>
	                총 금액:
	                </td>
	                <td>
	                    ${total}p
	                </td>
                </tr>
	        </tbody>
        </table>
        <c:if test="${change >= 0  and total > 0}">
	        <div>
	           	<h2>${change}</h2>
	        </div>
	        <a id= buy href="buyCart.do?userid=${loginUser.userid}&change=${change}">
	        	<button id=buy>상품 구매</button>
	        </a>
        </c:if>
        <c:if test="${change == -1 }">
	        <div>
	        	<h2>보유 포인트가 부족합니다.</h2>
	        </div>
        </c:if>
    </article>
</body>
</html>