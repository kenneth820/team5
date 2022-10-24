<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트랜드 룸 수정</title>
<link rel="stylesheet" type="text/css" href="css/product.css">
<script type="text/javascript" src="Script/trend.js"></script>
</head>
<body>
<div id="wrap" align="center">
<h2>트랜드 룸 수정</h2>

<form method="post" action="updateTrend.do" enctype="multipart/form-data" name="frm">
	<table border="1" style="width:80%;">
		<tr>
			<th>글 제목</th>
			<td>
				<input type="text" name="title" size="105px" value="${trend.title}">
			</td>
		</tr>
		<tr>
			<th>사 진</th>
			<td><input type="file" name="pictureUrl" value="${trend.pictureUrl}"></td>
		</tr>
		<tr height="500">
			<th>글 내용</th>
			<td rowspan="4"><textarea name="content" cols="105" rows="40" value="${trend.text}"></textarea></td>
		</tr>
	</table>
	<br>
	<input type="hidden" name="num" value="${trend.num}">
	<input type="submit" value="등록" onclick="return checkTrend()">
	<input type="reset" value="취소">
	<input type="button" value="목록" onclick="location.href='commList.do'">
</form>
</div>
</body>
</html>