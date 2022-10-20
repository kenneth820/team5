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

<style>
.slides {
    padding: 0;
    width: 65%;
    height: 420px;
    display: flex;
    min-width: 534px;
    z-index: 9;
    margin: 0 auto;
    position: relative;
}

.slides * {
    user-select: none;
    -ms-user-select: none;
    -moz-user-select: none;
    -khtml-user-select: none;
    -webkit-user-select: none;
    -webkit-touch-callout: none;
}

.slides input { display: none; }

.slide-container { display: block; }

.slide {
    top: 0;
    opacity: 0;
    width: 100%;
    height: 420px;
    display: flex;
    position: absolute;

    transition: all .8s ease-in-out;
}

.slide img {
    width: 100%;
    height: 100%;
}

.nav label {
    width: 200px;
    height: 100%;
    display: none;
    position: absolute;

    opacity: 0;
    z-index: 9;
    cursor: pointer;

    transition: opacity .0s;

    color: #FFF;
    font-size: 156pt;
    text-align: center;
    line-height: 380px;
    font-family: "Varela Round", sans-serif;
    background-color: rgba(255, 255, 255, .3);
    text-shadow: 0px 0px 15px rgb(119, 119, 119);
}

.slide:hover + .nav label { opacity: 0; }
.nav label:hover { opacity: 0; }
.nav .next { left: auto; }
input:checked + .slide-container  .slide {
    opacity: 1;
    transition: opacity 1s ease-in-out;
}

input:checked + .slide-container .nav label { display: block; }

.nav-dots {
  width: 100%;
  bottom: 9px;
  height: 11px;
  display: flex;
  justify-content: center;
  position: absolute;
  text-align: center;
}

.nav-dots .nav-dot {
  top: -5px;
  width: 11px;
  height: 11px;
  margin: 0 4px;
  position: relative;
  border-radius: 100%;
  display: inline-block;
  background-color: rgba(0, 0, 0, 0.6);
}

.nav-dots .nav-dot:hover {
  cursor: pointer;
  background-color: rgba(0, 0, 0, 0.8);
}

input#img-1:checked ~ .nav-dots label#img-dot-1,
input#img-2:checked ~ .nav-dots label#img-dot-2,
input#img-3:checked ~ .nav-dots label#img-dot-3,
input#img-4:checked ~ .nav-dots label#img-dot-4,
input#img-5:checked ~ .nav-dots label#img-dot-5,
input#img-6:checked ~ .nav-dots label#img-dot-6 {
  background: rgba(120, 200, 120);
}

</style>

</head>
<body>

<!-- 헤더 영역 구현 -->
<jsp:include page="header.jsp"></jsp:include>		<!-- 현재 파일 위치 기준 -->

<div id="wrap" align="center">
	<h2>커뮤니티 페이지</h2>
</div>
<!-- <img alt="" src="./community/logo.png"> -->	<!-- 서블릿 경로 기준 -->

<!-- ======================= -->
<!-- 상품 검색 기능 구현 -->
<div class="search">
<form action="commList.doo">
	<select name=column>
			<option ${(param.column=="num")?"selected":""} value="num">룸번호</option>
			<option ${(param.column=="name")?"selected":""} value="name">이름</option>
			<option ${(param.column=="pictureUrl")?"selected":""} value="pictureUrl">가격</option>
	</select>
	<input type="text" name="keyword" value="${param.keyword}">
	<input type="submit" value="검색">
</form>
</div>

<!-- ======================= -->
<!-- 슬라이드 기능 구현 -->
<div>
    <ul class="slides">
        <input type="radio" name="radio-btn" id="img-1" checked />
        <li class="slide-container">
            <div class="slide">
                <img src="https://cdn.pixabay.com/photo/2022/05/17/15/59/cat-7203072_960_720.jpg" />
            </div>
            <div class="nav">
            <label for="img-6" class="prev">&#x2039;</label>
            <label for="img-2" class="next">&#x203a;</label>
            </div>
        </li>

        <input type="radio" name="radio-btn" id="img-2" />
        <li class="slide-container">
            <div class="slide">
                <img src="https://cdn.pixabay.com/photo/2012/06/08/06/19/clouds-49520_960_720.jpg" />
            </div>
            <div class="nav">
                <label for="img-1" class="prev">&#x2039;</label>
                <label for="img-3" class="next">&#x203a;</label>
            </div>
        </li>

        <input type="radio" name="radio-btn" id="img-3" />
        <li class="slide-container">
            <div class="slide">
                <img src="https://cdn.pixabay.com/photo/2014/04/17/23/26/environmental-protection-326923_960_720.jpg" />
            </div>
            <div class="nav">
                <label for="img-2" class="prev">&#x2039;</label>
                <label for="img-4" class="next">&#x203a;</label>
            </div>
        </li>

        <input type="radio" name="radio-btn" id="img-4" />
        <li class="slide-container">
            <div class="slide">
                <img src="https://cdn.pixabay.com/photo/2014/02/05/08/19/smoke-258786_960_720.jpg" />
            </div>
            <div class="nav">
                <label for="img-3" class="prev">&#x2039;</label>
                <label for="img-5" class="next">&#x203a;</label>
            </div>
        </li>
            
        <input type="radio" name="radio-btn" id="img-5" />
        <li class="slide-container">
                <div class="slide">
                    <img src="https://cdn.pixabay.com/photo/2015/04/19/08/33/flower-729512_960_720.jpg" />
                </div>
                <div class="nav">
                    <label for="img-4" class="prev">&#x2039;</label>
                    <label for="img-6" class="next">&#x203a;</label>
                </div>
        </li>
            
        <input type="radio" name="radio-btn" id="img-6" />
        <li class="slide-container">
                <div class="slide">
                    <img src="https://cdn.pixabay.com/photo/2014/01/22/19/44/flower-field-250016_960_720.jpg" />
                </div>
                <div class="nav">
                    <label for="img-5" class="prev">&#x2039;</label>
                    <label for="img-1" class="next">&#x203a;</label>
                </div>
        </li>

        <li class="nav-dots">
            <label for="img-1" class="nav-dot" id="img-dot-1"></label>
            <label for="img-2" class="nav-dot" id="img-dot-2"></label>
            <label for="img-3" class="nav-dot" id="img-dot-3"></label>
            <label for="img-4" class="nav-dot" id="img-dot-4"></label>
            <label for="img-5" class="nav-dot" id="img-dot-5"></label>
            <label for="img-6" class="nav-dot" id="img-dot-6"></label>
        </li>
    </ul>
</div>

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