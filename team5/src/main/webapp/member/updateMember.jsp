<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<script type="text/javascript" src="Script/member.js"></script>
</head>
<body>
<h2>회원 정보 수정 페이지</h2>
* 표시 항목은 필수 입력 항목입니다.
<form action="updateMember.do" method="post" name="frm">
<table>
	<tr>
		<td>*이름</td>
		<td><input type="text" name="name" value="${mVo.name}"></td>
	</tr>
	<tr>
		<td>*아이디</td>
		<td><input type="text" name="userid" value="${mVo.userid}" readonly></td>
	</tr>
	<tr>
		<td>*암호</td>
		<td><input type="password" name= "pwd"></td>
	</tr>
	<tr>
		<td>*암호확인</td>
		<td><input type="password" name= "pwd_check"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email" value="${mVo.email}"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" name="phone" value="${mVo.phone}"></td>
	</tr>
	<tr>
		<td>등급</td>
		<td><input type="radio" name="admin" value="0" checked="checked"> 일반회원</td>
		<td><input type="radio" name="admin" value="1"> 관리자</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="확인" onclick="return checkJoin()">&nbsp;&nbsp;&nbsp;
			<input type="reset" value="취소">
		</td>
	</tr>
</table>
</form>
</body>
</html>