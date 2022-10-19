<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel ="stylesheet" href="Shop.css">
<title>Insert title here</title>
</head>
<body>
	    <header>
        <a href="">이전 페이지</a>
        <h3>관리자 페이지-상점입니다.</h3>
    </header>
    <nav>
    	<div>
        <img class="logo_img" src="profile.jpg"></img>
        <input id="Search" type="text" placeholder="검색어를 입력해주세요.">
        <input id="submit" type="submit" value="검색">
    	</div>
    </nav>
    <div class="lists">
        <ul class="nav">
            <li class="category">
                <p>바닥</p>
            </li>
            <li class="category">
                <p> 물건 </p>
            </li>
            <li class="category">
                <p>장식</p>
            </li>
        </ul>
    </div>
    <article>
    	<ul class="right-menu">
	        <div class="menubar">
	        <li>
		        <div class="profilePhoto">
		            <img src="profile.jpg">
		        </div>
	        </li>
			<li>
	            <div class="userInfo">
	                <p class="name">관리자이름${loginUser.name}</p>
	            </div>
			</li>
	        </div>
    	</ul>
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
        		<c:forEach var="product" items="${productList}">
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

        <table style="padding: 100px;" align="center">
            <td>
                <a href="">1</a>
            </td>
            <td>
                <a href="">2</a>
            </td>
            <td>
                <a href="">3</a>
            </td>
            <td>
                <a href="">4</a>
            </td>
        </table>

    </section>

    <footer style="border-color: grey;"> footer</footer>
</body>
</html>