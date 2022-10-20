<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<c:if test="${empty loginUser}">
	<jsp:forward page='login.do'/>
</c:if>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- CSS 초기화는 CDN 기법을 통해 링크로 적용시킬 수 있음 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css" />
    <link rel="stylesheet" href="./community/master.css">
</head>
<body>
    <header>
        <div class="box__logo" style="display: inline-block;">
            <img src="./image/comm/logo.png" width="240" height="145" margin="0" padding="0" alt="K마켓">
        </div>
        <div class="box__mytools">
            <!-- 회원 인증된 사용자에게 제공하는 메인 페이지 -->
			<form action="logout.do" method="get">
				<!-- <table border="1"> -->
				<table>
					<tr>
						<td>안녕하세요. ${loginUser.name}(${loginUser.userid})님.</td>						
						<td colspan="2" align="left">
							&nbsp;|
							<input type="submit" class="input_submit" value="로그아웃">|
							<input type="button" class="input_button" value="회원정보변경"
								onclick="location.href='updateMember.do?userid=${loginUser.userid}'">|
							<span class="mypage"><a href="#" title="나의 쇼핑정보">마이페이지</a></span>
				            &nbsp;|&nbsp;
		            		<span class="basket"><a href="#" title="장바구니 이동">장바구니</a></span>
						</td>
					</tr>
				</table>
			</form>

		</div>
    </header>
</body>
</html>