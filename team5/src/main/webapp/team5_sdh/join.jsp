<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 페이지</title>
<link rel="stylesheet" type="text/css" href="css/Login.css">
<script type="text/javascript" src="Script/member.js"></script>
</head>
<body>
'*' 표시 항목은 필수 입력 항목입니다.
<form action=join.do method="post" name="frm">
	<div class="CreateId">
		<table id="joinTable">
			<tr>
				<td id=title>*이름</td>
				<td id=insert><input id=text type="text" name="name"></td>
			</tr>
			<tr>
				<td id=title>*아이디</td>
				<td id=insert>
				<input id=text type="text" name="userid">
				<!-- 중복체크 수행여부 저장변수 -->
				<input type="hidden" name="checkid">
				</td>
				<td style="width:1%; padding-top:50px;">
				<input id=check type="button" value="중복 체크" onclick="checkId()">
				</td>
			</tr>
			<tr>
				<td id=title>*암호</td>
				<td id=insert><input id=text type="password" name= "pwd"></td>
			</tr>
			<tr>
				<td id=title>*암호확인</td>
				<td id=insert><input id=text type="password" name= "pwd_check"></td>
			</tr>
			<tr>
				<td id=title>이메일</td>
				<td id=insert><input id=text type="text" name="email"></td>
			</tr>
			<tr>
				<td id=title>전화번호</td>
				<td id=insert><input id=text type="text" name="phone"></td>
			</tr>
			<tr>
				<td id=title>등급</td>
				<td id=insert><input type="radio" name="admin" value="0" checked="checked"> 일반회원
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="admin" value="1"> 관리자</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					&nbsp;&nbsp;&nbsp;
					<input type="submit" value="확인" onclick="return checkJoin()">
					&nbsp;&nbsp;&nbsp;
					<input type="reset" value="초기화">
					&nbsp;&nbsp;&nbsp;
					<input type="button" value="뒤로가기" onclick="history.go(-1)">
				</td>
			</tr>
		</table>
	</div>	
</form>
</body>
</html>