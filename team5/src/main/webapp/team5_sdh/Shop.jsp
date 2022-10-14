<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel ="stylesheet" href="Shop.css">
<title>Insert title here</title>
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
	                <p class="name">이름${loginUser.name}</p>
	                <p class="point">보유포인트${loginUser.point}</p>
	            </div>
			</li>
	        </div>
    	</ul>
        <div>
	        <p class="S_L">
    	        <a href="#">장바구니</a>
            </p>
        </div>
        <div class="Cart_List">
        <table border="1" width="100%">
        <%-- <c:forEach var="cart" items="${CartList}"> --%>
        	<tr>
        	<td>
        	<table>
		                	<tr height="50" align="center">
							<td>${cart.name}</td>
		                	</tr>
		                	<tr height="50" align="center">
			                <td>${cart.price}</td>
		                	</tr>
        	</table>    		
			</td>			
			</tr>
        <%-- </c:forEach> --%>
        </table>
        </div>
    </article>
    <section>
    	<form class="shop_Form" method="post" action="addCart.do">
<%--   		<c:forEach var="product" items="${productList}"> --%>
	        <ul class="item_list">
	            <li class="items"> 
	                <div class="flip">  
	                    <div class="card">
	                    <!-- 앞면 -->
	                    <div class="front">
	                        <img src="IMG_9347.jpg">
	                    </div>
	                    <!-- 뒷면 -->
	                    <div class="back">
	                        <p>${product.name}</p>
	                        <p>${product.price}</p>
	                        <p>${product.description}</p>
	                        <input type="submit" value="장바구니">                 
	                    </div>
	                    </div>
	                </div>
	            </li>
	            <li class="items"> 
	                <div class="flip">  
	                    <div class="card">
	                    <!-- 앞면 -->
	                    <div class="front">
	                        <img src="IMG_9347.jpg">
	                    </div>
	                    <!-- 뒷면 -->
	                    <div class="back">
	                        <p>${product.name}</p>
	                        <p>${product.price}</p>
	                        <p>${product.description}</p>
	                        <input type="submit" value="장바구니">                    
	                    </div>
	                    </div>
	                </div>
	            </li>
	            <li class="items"> 
	                <div class="flip">  
	                    <div class="card">
	                    <!-- 앞면 -->
	                    <div class="front">
	                        <img src="IMG_9347.jpg">
	                    </div>
	                    <!-- 뒷면 -->
	                    <div class="back">
	                        <p>${product.name}</p>
	                        <p>${product.price}</p>
	                        <p>${product.description}</p>
	                        <input type="submit" value="장바구니">                    
	                    </div>
	                    </div>
	                </div>
	            </li>
	            <li class="items"> 
	                <div class="flip">  
	                    <div class="card">
	                    <!-- 앞면 -->
	                    <div class="front">
	                        <img src="IMG_9347.jpg">
	                    </div>
	                    <!-- 뒷면 -->
	                    <div class="back">
	                        <p>${product.name}</p>
	                        <p>${product.price}</p>
	                        <p>${product.description}</p>
	                        <input type="submit" value="장바구니">                  
	                    </div>
	                    </div>
	                </div>
	            </li>
	            <li class="items"> 
	                <div class="flip">  
	                    <div class="card">
	                    <!-- 앞면 -->
	                    <div class="front">
	                        <img src="IMG_9347.jpg">
	                    </div>
	                    <!-- 뒷면 -->
	                    <div class="back">
	                        <p>${product.name}</p>
	                        <p>${product.price}</p>
	                        <p>${product.description}</p>
	                        <input type="submit" value="장바구니"> 
	                    </div>
	                    </div>
	                </div>
	            </li>
	            <li class="items"> 
	                <div class="flip">  
	                    <div class="card">
	                    <!-- 앞면 -->
	                    <div class="front">
	                        <img src="IMG_9347.jpg">
	                    </div>
	                    <!-- 뒷면 -->
	                    <div class="back">
	                        <p>${product.name}</p>
	                        <p>${product.price}</p>
	                        <p>${product.description}</p>
	                        <input type="submit" value="장바구니">                  
	                    </div>
	                    </div>
	                </div>
	            </li>
	            <li class="items"> 
	                <div class="flip">  
	                    <div class="card">
	                    <!-- 앞면 -->
	                    <div class="front">
	                        <img src="IMG_9347.jpg">
	                    </div>
	                    <!-- 뒷면 -->
	                    <div class="back">
	                        <p>${product.name}</p>
	                        <p>${product.price}</p>
	                        <p>${product.description}</p>
	                        <input type="submit" value="장바구니">                   
	                    </div>
	                    </div>
	                </div>
	            </li>
	            <li class="items"> 
	                <div class="flip">  
	                    <div class="card">
	                    <!-- 앞면 -->
	                    <div class="front">
	                        <img src="IMG_9347.jpg">
	                    </div>
	                    <!-- 뒷면 -->
	                    <div class="back">
	                        <p>${product.name}</p>
	                        <p>${product.price}</p>
	                        <p>${product.description}</p>
	                        <input type="submit" value="장바구니">                  
	                    </div>
	                    </div>
	                </div>
	            </li>
	            <li class="items"> 
	                <div class="flip">  
	                    <div class="card">
	                    <!-- 앞면 -->
	                    <div class="front">
	                        <img src="IMG_9347.jpg">
	                    </div>
	                    <!-- 뒷면 -->
	                    <div class="back">
	                        <p>${product.name}</p>
	                        <p>${product.price}</p>
	                        <p>${product.description}</p>
	                        <input type="submit" value="장바구니">                    
	                    </div>
	                    </div>
	                </div>
	            </li>
	        </ul>
  		<%-- </c:forEach> --%>
    	</form>

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