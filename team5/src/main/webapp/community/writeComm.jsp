<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트랜드 룸 등록</title>
<link rel="stylesheet" type="text/css" href="community/product.css">
<script type="text/javascript" src="script/trand.js"></script>
</head>
<body>
<div id="wrap" align="center">
<h2>트랜드 룸 등록</h2>

<form method="post" action="writeComm.do" enctype="multipart/form-data" name="frm">
	<table border="1">
	<tr>
		<th>트랜드 방이름</th>
		<td>
			<input type="text" name="name" size="80px">
		</td>
	</tr>
	<tr>
		<th>사 진</th>
		<td><input type="file" name="pictureUrl"></td>
	</tr>
	</table>
	<br>
	<input type="submit" value="등록" onclick="return checkTrand()">
	<input type="reset" value="취소">
	<input type="button" value="목록" onclick="location.href='trandList.do'">
</form>
</div>
</body>
</html>