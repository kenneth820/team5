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
					설명: 
				</th>
				<td >
					<!-- <input type="text" name="description"> -->
					<textarea rows="10" cols="80" name="description" placeholder="상품 설명을 입력하세요."></textarea>
				</td>
			</tr>
			<tr>
				<th>
				등록일자
				</th>
				<td>
					<input type="text" name="reg_date" placeholder="2022-10-03"> (예시) 2022-10-03
				</td>
			</tr>
		</table>
					<input type="submit" value="등록" onclick="return checkProduct()">
					<input type="reset" value="취소">
					<input type="button" value="목록" onclick="location.href='productList.do'">	
	</form>
</body>
</html>