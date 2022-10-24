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
<c:set var="page" value="${(empty param.p)?1:param.p}"></c:set>
<c:set var="start" value="${(param.p==null or param.p <= 3)?1:param.p-2}"></c:set>
<c:set var="end" value="${fn:substringBefore(Math.ceil(count/9), '.')}"></c:set>

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
<!-- 섹션(리스트) 영역 구현 -->
<jsp:include page="section.jsp"></jsp:include> 


</body>
</html>