<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<link rel="stylesheet" type="text/css" href="css/Login.css">
<script type="text/javascript" src="Script/member.js"></script>
</head>
<body>
* 표시 항목은 필수 입력 항목입니다.
<form action="updateMember.do" method="post" name="frm">
	<div class="CreateId">
		<table>
			<tr>
				<td id=title>*이름</td>
				<td id=insert><input id=text type="text" name="name" value="${mVo.name}"></td>
			</tr>
			<tr>
				<td id=title>*아이디</td>
				<td id=insert><input id=text type="text" name="userid" value="${mVo.userid}" readonly></td>
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
				<td id=insert><input id=text type="text" name="email" value="${mVo.email}"></td>
			</tr>
			<tr>
				<td id=title>전화번호</td>
				<td id=insert><input id=text type="text" name="phone" value="${mVo.phone}"></td>
			</tr>
			<tr>
				<td id=title>프로필사진</td>
				<td id=insert><input type="file" name="pictureurl">			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인" onclick="return checkJoin()">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="초기화">&nbsp;&nbsp;&nbsp;
					<input type="button" value="뒤로가기" onclick="history.go(-1)">
				</td>
			</tr>
	</table>
</div>
</form>
</body>
</html>