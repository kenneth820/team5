<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/product.css">
<script type="text/javascript" src="script/product.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="wrap" align="center">
<h2>상품 삭제 - 관리자 페이지</h2>
<form action="deleteProduct.do" method="post">
<table border="1">
	<tr>
		<td style="widht:310px">
			<c:choose>
			<c:when test="${empty product.pictureurl}">
				<img src="image/noimage.jpg">
			</c:when>
			<c:otherwise>
				<img src="upload/${product.pictureurl}">
			</c:otherwise>
			</c:choose>
		</td>
		<td>
			<table border="1">
					<tr>
						<th>상품명</th>
						<td>${product.name}</td>
					</tr>
					<tr>
						<th>가격</th>
						<td>${product.price}</td>
					</tr>
					<tr>
						<th>설명</th>
						<td>
							<div>
								${product.description}
							</div>
						</td>
					</tr>
					<tr>
						<th>등록 일자</th>
						<td>${product.reg_date}</td>
					</tr>
				</tr>
			</table>
		</td>
</table>
<input type="hidden" name="code" value="${product.code}">
<input type="submit" value="삭제">
<input type="button" value="목록" onclick="location.href='productList.do'">
</form>
</div>
</body>
</html>