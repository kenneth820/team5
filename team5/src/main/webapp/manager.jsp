<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
<link rel ="stylesheet" type="text/css" href="css/Shop.css">
</head>
<body>
<div id = wrap align="center">
<h2>관리자페이지입니다.</h2>
 ${loginUser.name}님 환영합니다.<br>

<form action="logout.do"><input type="submit" value="로그아웃"></form>

<section style="width:100%;" >
<div style="width:100%;">
<a href="productList.do" id=white>
	<div id=managermode>
			<p style="position: relative; top:55px;">
				상점 관리
			</p>
	</div>
</a>

<a href="memberList.do" id=white>
	<div id=managermode>
		<p style="position: relative; top:55px;" >
			회원 관리
		</p>
	</div>
</a>

	<div id=managermode>
		<p style="position: relative; top:55px;" >
		커뮤니티 관리
		</p>
	</div>

</div>
</section>
</div>

</body>
</html>