<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel ="stylesheet" type="text/css" href="css/Shop.css">
</head>
<body>
    <header>
        <a href="">쇼룸으로 이동</a>
    </header>
    <nav>
    	<div>
	        <img class="logo_img" src="profile.jpg"></img>
		  	<form id=SearchForm value="name" action="searchProduct.do">
		        <input id="Search" type="text" name="keyword" placeholder="검색어를 입력해주세요.">
		        <input id="submit" type="submit" value="검색">
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
	                    <td><a href="deleteCart.do?code=${product.code}">상품 삭제</a></td>	
	                </tr>
	                </c:forEach>
	                <td>
	                <input type=button value="상품전체삭제">
	                </td>
	            </table>
	        <%-- ${message} --%>
	        </div>
        </div>
    </section>
    <article class="Cart_result">
        	<ul class="right-menu">
		        <div class="menubar">
			        <li>
				        <div class="profilePhoto">
				            <img src="profile.jpg">
				        </div>
			        </li>
					<li>
			            <div class="userInfo">
			                <p class="name">${loginUser.name}</p>
			                <%-- <p class="point">보유포인트${loginUser.point}</p> --%>
			            </div>
					</li>
		        </div>
    		</ul>
    		<div>
                <p id=Money>
                    &nbsp;&nbsp;&nbsp;&nbsp;상품 가격
                </p>
                <p id=Money>
                    - &nbsp;&nbsp;&nbsp; 보유 포인트
                </p>
                <p id=empty_Money>
                <h2>잔여포인트</h2>
                </p>
            </div>
            <hr>
            <button id=buy>상품 구매</button>
    </article>
</body>
</html>