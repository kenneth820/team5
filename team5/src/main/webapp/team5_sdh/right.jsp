<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="flex: 20%;  ">
		    <div style="width: 100%; height:909px; float: right; border-left: 1px solid black; ">
        <div style="margin-top: 40%; padding-left: 40px;">
            로고 사이트 이름
        </div>
        <div style="padding-top: 70%; margin-left: 10%;">
            <form>
                아이디 : &nbsp; &nbsp;<input type="text" value="아이디를 입력하세요.">
                <br>
                비밀번호 : <input type="password">
                <br>
                <br>
                <input type="button" value="로그인" onclick="location.href='first.jsp'" style="margin-left: 15%;">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="회원가입">
            </form>
        </div>
    </div>
	</div>
</body>
</html>