<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel ="stylesheet" href="css/Shop.css">
<title>상점</title>
</head>
<body>
    <header>
        <a href="login.do">쇼룸으로 이동</a>
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
<%--                			<c:choose>
							<c:when test="${empty loginUser.pictureurl}">
								<img src="image/noimage.jpg">
							</c:when>
							<c:otherwise>
								<img src="profilePhoto/${loginUser.pictureurl}">
							</c:otherwise>
						</c:choose> --%>
						<img src="image/noimage.jpg">
			        </div>
		        </li>
				<li>
		            <div class="userInfo">
		                <p class="name">${loginUser.name}</p>
		                <%-- <p class="point">${loginUser.point}</p> --%>
		            </div>
				</li>
	        </div>
    	</ul>
        <div>
   
	        <p class="S_L">
    	        <a href="cartList.do?userid=${loginUser.userid}">장바구니</a>
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
								<img src="upload/${Cart.pictureurl}">
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
										<img src="upload/${product.pictureurl}">
									</c:otherwise>
								</c:choose>
		                    </div>
		                    <!-- 뒷면 -->
		                    <div class="back">
		                        <p>${product.name}</p>
		                        <p>${product.price}</p>
		                        <p>${product.description}</p>
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
	<table style="padding: 100px;" align="center">
			<tr>
				<td colspan="5" align="center">
           			<ul>
           				<!-- 2. 이전버튼 활성화 여부 -->
           				<c:if test="${pageVo.prev}">
                   			<li><a href="productList.do?pageNum=${pageVo.startPage - 1 }&amount=${pageVo.amount}">이전</a></li>
						</c:if>		                        		
                   		<!-- 1. 페이지번호 처리 -->
                   		<c:forEach var="num" begin="${pageVo.startPage}" end="${pageVo.endPage}">
               	      		<li><a href="productList.do?pageNum=${num}&amount=${pageVo.amount}">${num}</a></li>
                   		</c:forEach>       		
                   		<!-- 3. 다음버튼 활성화 여부 -->
                   		<c:if test="${pageVo.next}">
                   			<li><a href="productList.do?pageNum=${pageVo.endPage + 1 }&amount=${pageVo.amount}">다음</a></li>
                   		</c:if>
               			</ul>					
				</td>
			</tr>
       </table>

    </section>
    <footer style="border-color: grey;"> footer</footer>
</body>
</html>