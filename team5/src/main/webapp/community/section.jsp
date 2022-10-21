<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 영역 페이지</title>
  <!-- CSS 초기화는 CDN 기법을 통해 링크로 적용시킬 수 있음 -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css" />
  <link rel="stylesheet" href="./community/master.css">
  
</head>
<body>
<c:set var="startNum" value="${1}"></c:set>
<c:set var="endNum" value="${showCount}"></c:set>

    <ul class="slides">
  	<c:forEach var="showroom" items="${showroomList}" varStatus="status">
        <input type="radio" name="radio-btn" id="img-${status.count}" checked />
        <li class="slide-container">
            <div class="slide">
                <img src="./image/comm/${showroom.pictureUrl}" />
            </div>
            <div class="nav">
            <c:if test="${status.count==startNum}">
            	<label for="img-${endNum}" class="prev">&#x2039;</label>
            </c:if>
            <c:if test="${status.count!=startNum}">
            	<label for="img-${status.count-1}" class="prev">&#x2039;</label>
            </c:if>
            <c:if test="${status.count==endNum}">
            	<label for="img-${startNum}" class="next">&#x203a;</label>           
            </c:if>
            <c:if test="${status.count!=endNum}">
            	<label for="img-${status.count+1}" class="next">&#x203a;</label>
            </c:if>
            </div>
        </li>
    </c:forEach>
        <li class="nav-dots">
		<c:forEach var="showroom" items="${showroomList}" varStatus="status">
            <label for="img-${status.count}" class="nav-dot" id="img-dot-${status.count}"></label>
		</c:forEach>
        </li>
    </ul>

    <section class="section__container3__main-today">
      <div class="box__today">
        <h2 class="text__title">
          <span class="text">오늘의 트렌드</span>
        </h2>
        <div class="box__item">
          <ul class="list__item">
          	<c:forEach var="trend" items="${trendList}">
		    <!-- <form action="commenttrend" > -->
            <li class="list-item">
              <div class="box__information">
                <div class="text__name">
                  <%-- ${trend.username} --%>
                </div>
              </div>
              <a href="#" class="link__item">
                <div class="box__image">
                  <img src="./image/comm/${trend.pictureUrl}" alt="트랜드룸" class="image">
                </div>
                <%-- <div>${trend.userpictureurl}</div> --%>
                <div>${trend.name}<br>
                	${trend.name}
                </div>
                <div>${loginUser.name} 댓글 1</div>
                <div>${loginUser.name} 댓글 2</div>
                <input type="text">
                <input type="submit">
              </a>
            </li>
		    <!-- </form> -->
            </c:forEach>
          </ul>
        </div>
      </div>
    </section>
  </section>
  
</body>
</html>