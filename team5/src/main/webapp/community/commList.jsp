<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티</title>
<!-- <link rel="stylesheet" type="text/css" href="community/product.css"> -->


</head>
<body>

<!-- 헤더 영역 구현 -->
<jsp:include page="header.jsp"></jsp:include>		<!-- 현재 파일 위치 기준 -->
<!-- <img alt="" src="./community/logo.png"> -->	<!-- 서블릿 경로 기준 -->

<nav>
   	<div>
   		<a id=logo_img href="commList.do">
	        <img class="logo_img" src="./image/Logo.jpg">
   		</a>
	  	<form id=SearchForm value="name" action="commList.do?k=${param.k}&p=${1}">
	        <input id="Search" type="text" name="k" placeholder="검색어를 입력해주세요.">
	        <input id="submit" type="image" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTDlkdG6O5lJVaXJ40p34v1j4CUJDpb7Ml7g&usqp=CAU">
		</form>
   	</div>
</nav>

<!-- ======================= -->
<!-- 방 등록 기능 구현 -->
<div id="wrap" align="center">
	<table class="list">
		<tr>
			<td colspan="7" style="border:white; text-align:right; padding-right:20px">
				<a href="writeComm.do">트랜드 등록</a>
			</td>
		</tr>
	</table>
</div>

<!-- 섹션(리스트) 영역 구현 -->
<jsp:include page="section.jsp"></jsp:include> 


<!-- ======================= -->
<!-- 쇼룸 리스트 표시 기능 구현 -->
<div id="wrap" align="center">
<%-- <table class="list">
	<tr>
		<th>코드</th> <th>이름</th> <th>사진경로</th>
	</tr>
	<!-- 상품 목록 출력 -->
	<c:forEach var="showroom" items="${showroomList}">
		<tr>
			<td><a href="commDetail.do?code=${showroom.code}">${showroom.code}</a></td>
			<td><a href="commDetail.do?code=${showroom.name}">${showroom.name}</a></td>
			<td><a href="commDetail.do?code=${showroom.pictureUrl}">${showroom.pictureUrl}</a></td>
		</tr>
	</c:forEach>
</table> --%>
</div>

<!-- ======================= -->
<!-- 트랜드 리스트 표시 기능 구현 -->
<div id="wrap" align="center">
<%-- <table class="list">
	<tr>
		<th>코드</th> <th>이름</th> <th>사진경로</th>
	</tr>
	<!-- 상품 목록 출력 -->
	<c:forEach var="trand" items="${TrandList}">
		<tr>
			<td><a href="commDetail.do?code=${trand.num}">${trand.num}</a></td>
			<td><a href="commDetail.do?code=${trand.name}">${trand.name}</a></td>
			<td><a href="commDetail.do?code=${trand.pictureUrl}">${trand.pictureUrl}</a></td>
		</tr>
	</c:forEach>
</table> --%>
</div>


</body>
</html>