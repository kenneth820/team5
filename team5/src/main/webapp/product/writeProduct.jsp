<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/product.css">
<title>상품 등록 페이지</title>
<script type="text/javascript" src="script/product.js"></script>
</head>
<body>
	<h2>상품 등록</h2>
	<!-- action="writeProduct.do" -->
	<form method="post" enctype="multipart/form-data" name="frm"> 
		<table border="1">
<!-- 			<tr>
				<th>
					상품코드: 
				</th>
				<td>
					<input type="text" name="code">
				</td>
			</tr> -->
			<tr>
				<th>
					상품명: 
				</th>
				<td>
					<input type="text" name="name">
				</td>
			</tr>
			<tr>
				<th>
					가격: 
				</th>
				<td>
					<input type="text" name="price">
				</td>
			</tr>
			<tr>
				<th>
					사진: 
				</th>
				<td>
					<input type="file" name="pictureurl">	
				</td>
			</tr>
			<tr>
				<th>
					카테고리: 
				</th>
				<td>
					<!-- <input type="text" name="description"> -->
					<input type="text" name="category" placeholder="상품 카테고리를 설정하세요."> (바닥: 200 전등: 400 장식: 600)
				</td>
			</tr>
		</table>
					<input type="submit" value="등록" onclick="return checkProduct()">
					<input type="reset" value="취소">
					<input type="button" value="목록" onclick="location.href='productList.do'">	
	</form>
</body>
</html>