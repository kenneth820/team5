<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/Login.css">
<script type="text/javascript" src="Script/member.js"></script>
</head>
<body>
	<div style="flex: 20%;  ">
		    <div style="width: 100%; height:909px; float: right; border-left: 1px solid black; ">
        <div style="margin-top: 40%; padding-left: 40px;">
            로고 사이트 이름
        </div>
        <div style="padding-top: 70%; margin-left: 10%;">
        <form action="login.do" method="post" name="frm">
	<!-- <table border="1"> -->
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="userId"></td>
		</tr>
		<tr>
			<td>암 호</td>
			<td><input type="password" name="userPwd"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="로그인" onclick="return checkLogin()">
				<input type="reset" value="취소">
				<input type="button" value="회원가입" onclick="location.href='join.do'">
			</td>
		</tr>	
	</table>
</form>
        </div>
    </div>
	</div>
</body>
</html>