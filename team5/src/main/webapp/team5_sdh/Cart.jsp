<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel ="stylesheet" href="Shop.css">
</head>
<body>
    <header>
        <a href="">쇼룸으로 이동</a>
    </header>
    <nav>
    	<div>
	        <img class="logo_img" src="profile.jpg"></img>
	        <input id="Search" type="text" placeholder="검색어를 입력해주세요.">
	        <input id="submit" type="submit" value="검색">
    	</div>
    </nav>
 
    <section style="align-items: center;">
        <div style="width: 100%; padding-top: 5%;">
	        <div class="Shopping_Cart">
	            <h2>장바구니</h2>
	            <table border="1" style="width: 100%;">
	                <tr>
	                    <th>상품 사진</th><th>이름</th><th>가격</th><th>삭제</th>
	                </tr>
	                <c:forEach var="product" items="${productList}">
	                <tr>
	                    <td>상품 사진</td>
	                    <td>${product.name}</td>
	                    <td>${product.price}</td>
	                    <td><a href="deleteProduct.do?code=${product.code}">상품 삭제</a></td>	
	                </tr>
	                </c:forEach>
	            </table>
	        <%-- ${message} --%>
	        </div>
        </div>
    </section>
    <article class="Cart_result">
    		<div>
                <p id=Money>
                    상품 가격
                </p>
                <p id=Money>
                    "-" + 보유 포인트
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