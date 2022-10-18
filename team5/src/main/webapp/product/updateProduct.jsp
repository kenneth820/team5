<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/product.css">
<script type="text/javascript" src="script/product.js"></script>
<title> 상품 업데이트 화면 </title>
</head>
<body>
<div>
<h2>상품 수정 페이지</h2>
<form action="updateProduct.do" method="post" enctype="multipart/form-data" name="frm">
	<table border="1">
		<tr>
			<td>
				<c:choose>
					<c:when test="${empty product.pictureurl}">
						<img src="image/noimage.jpg" width="200px" height="200px">
					</c:when>
					<c:otherwise>
						<img src="upload/${product.pictureurl}" width="200px" height="200px">
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				<table border="1">
					<tr>
						<th style="width:80px">상품명</th>
						<td><input type="text" name="name" value="${product.name}"></td>
					</tr>
					<tr>
						<th>가격</th>
						<td><input type="text" name="price" value="${product.price}"></td>
					</tr>
					<tr>
						<th>사진</th>
						<td><input type="file" name="pictureurl" value="${product.pictureurl}"><br>()이미지 변경시 선택하세요.)</td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td><input type="text" name="category" value="${product.category}">(바닥: 200 전등: 400 장식: 600)</td>
					</tr>	
					<tr>
						<th>상품 좌표</th>
						<td><input type="text" name="coordinate" value="${product.coordinate}">예시(000,000,000,000)</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
<input type="hidden" name="code" value="${product.code}">
<input type="submit" value="수정" onclick="return checkProduct()">
<input type="reset" value="취소">
<input type="button" value="목록" onclick="location.href='productList.do'">
</form>
</div>
</body>
</html>