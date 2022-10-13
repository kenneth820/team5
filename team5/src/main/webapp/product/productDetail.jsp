<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/product.css">
<meta charset="UTF-8">
<title>상품 상세 페이지</title>
</head>
<body>
<div id="wrap">
<h2>상품 상세 페이지</h2>
<table border="1">
	<tr>
		<td style="width:330px;">
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
					<th style="width:80px">상품명</th>
					<td>${product.name}</td>
				</tr>
				<tr>
					<th>가격</th>
					<td>${product.price}</td>
				</tr>
				<tr>
					<th>설명</th>
					<td>
						<div style="height:220px; width:100%">
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
</table>
<input type="button" value="목록" onclick="location.href='productList.do'">
</div>

</body>
</html>