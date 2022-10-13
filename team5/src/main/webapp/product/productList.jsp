<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/product.css">
<meta charset="UTF-8">
<title>상품 리스트 페이지</title>
</head>
<body>
<div id="wrap" align="center">
	<h2>상품 리스트 - 관리자 페이지</h2>
	
	<%-- ${productList} --%>
	
	<table class="list">
		<tr>
			<td colspan="7" style="border:white; text-align:right; padding-right:20px">
				<a href="writeProduct.do">상품 등록</a>
			</td>
		</tr>
		<tr>
			<th>코드</th><th>이름</th><th>가격</th><th>생성일자</th><th>상품 상세</th><th>상품 수정</th><th>상품 삭제</th><th>장바구니</th>
		</tr>
		<c:forEach var="product" items="${pageList}">
			<tr>
				<td>${product.code}</td>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>${product.reg_date}</td>
				<td><a href="productDetail.do?code=${product.code}">상품 상세</a></td>
				<td><a href="updateProduct.do?code=${product.code}">상품 수정</a></td>
				<td><a href="deleteProduct.do?code=${product.code}">상품 삭제</a></td>
				<td><a href="insertCart.do?code=${product.code}">장바구니 등록</a></td>	
			</tr>
		</c:forEach>
	</table>
<%-- ${message} --%>
</div>

<div class="search">
	<form action="searchProduct.do">
		<select name="column">
			<option value="code">상품코드</option>
			<option ${(param.column=="name")?"selected":""} value="name">이름</option>
		</select>
		<input type="text" name="keyword" value="${param.keyword}">
		<input type="submit" value="검색">
	</form>
</div>
<div>
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
</div>
</body>
</html>