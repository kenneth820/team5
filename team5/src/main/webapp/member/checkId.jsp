<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복확인</title>
<script type="text/javascript" src="Script/member.js"></script>
</head>
<body>
<h2>아이디 중복 확인</h2>

<form action="checkId.do" name="frm">
	<input type="text" name="userid" value="${userid}">
	<input type="submit" value="중복체크">
	<br>
	
	${message}
	
<!-- 만약, 사용 불가능한 아이디인 경우 => 사용 버튼 미생성--> 
<c:if test="${result==1}">					<!-- 불가능할 경우  -->
	<script type="text/javascript">
		// 회원가입 폼에서 입력받은 아이디를 삭제
		opener.document.frm.userid.value="";
	</script>
</c:if>


<!-- 만약, 사용 가능한 아이디인 경우 => 사용 버튼 생성 -->

<c:if test="${result==-1}">					<!-- 가능할 경우  -->
	<input type="button" value="사용" onclick="idOk()">
</c:if>

</form>
</body>
</html>