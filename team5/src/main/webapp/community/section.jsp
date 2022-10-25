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
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Dongle&display=swap" rel="stylesheet">
  
</head>
<body>
<c:set var="page" value="${(empty param.p)?1:param.p}"></c:set>
<c:set var="start" value="${(param.p==null or param.p <= 3)?1:param.p-2}"></c:set>
<c:set var="end" value="${fn:substringBefore(Math.ceil(count/9), '.')}"></c:set>
<c:set var="startNum" value="${1}"></c:set>
<c:set var="endNum" value="${showCount}"></c:set>
	<h2 class="text__title">인기 쇼룸</h2>
    <ul class="slides">
  	<c:forEach var="showroom" items="${showroomList}" varStatus="status">
        <input type="radio" name="radio-btn" id="img-${status.count}" checked /> <!-- status.count=현재 번호 -->
        <li class="slide-container">
            <div class="slide">
            	<a id=ShowroomId href="#">
	               	<c:choose>
						<c:when test="${empty showroom.pictureUrl}">
							<img src="upload/noimage.jpg">
						</c:when>
						<c:otherwise>
							<!-- img 태그 onerror 처리 -->
							<!-- https://zxchsr.tistory.com/16 -->
							<img src="showroom/${showroom.pictureUrl}" 
							onerror="this.onerror=null; this.src='./image/comm/${showroom.pictureUrl}';" 
							alt="쇼룸" class="image">									
						</c:otherwise>
					</c:choose>
            	</a>
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
            <li class="list-item">
              <div class="box__information">
                <div class="text__name">
	                  <h3>${trend.userid}</h3>
	                  <c:if test="${trend.userid == loginUser.userid}">
	                  <div>
						<a href="updateTrend.do?num=${trend.num }">
			                <button id="update">글 수정</button>		                  
	                  	</a>
		                  <a href="deleteTrend.do?num=${trend.num}">
			                <button id="update">글 삭제</button>
		                  </a>
	                  </div>
	                  </c:if>
                </div>
              </div>
    			<div class="btn-open-popup">
	              		<c:choose>
							<c:when test="${empty trend.pictureUrl}">
								<img src="image/noimage.jpg">
							</c:when>
							<c:otherwise>
								<img id=m_img  src="trend/${trend.pictureUrl}" 
								onerror="this.onerror=null; this.src='./image/comm/${trend.pictureUrl}';" 
								alt="트랜드룸" class="image">									
							</c:otherwise>
						</c:choose>
                </div>
			    <div class="modal">
			      <div class="modal_body">
			      	<div class="modal_img">
			          	<c:choose>
							<c:when test="${empty trend.pictureUrl}">
								<img src="image/noimage.jpg">
							</c:when>
							<c:otherwise>
								<img src="trend/${trend.pictureUrl}" 
								onerror="this.onerror=null; this.src='./image/comm/${trend.pictureUrl}';" 
								alt="트랜드룸" class="image">									
							</c:otherwise>
						</c:choose>
			      	</div>
			      	<div class="modal_content">
						<div class="user-info">
							<img class="profilePhoto" src="image/noimage.jpg" alt="기본프로필사진">
							<div class="userInfo">
								<p class="name">${trend.userid}</p>
							</div>
							<p class="selfcomment">${trend.title}</p>
							<p class="point">${trend.text}</p>
						</div>
			
			      	</div>
			      </div>
			    </div>
            </li>
		    <!-- </form> -->
            </c:forEach>
          </ul>
        </div>
      </div>
	<table id=channel style="padding: 100px;" align="center">
		<tr>
			<td colspan="7" align="center">
				<ul>
					<c:if test="${page-3>0}"><!-- 3페이지부터 페이지 이동이 생김 -->
					<li>
						<a href="commList.do?p=${page-1}&k=${param.k}">이전</a>
					</li>
					</c:if>
					<c:if test="${page-3<=0}"><li></li></c:if>
					<c:choose>
						<c:when test="${page > 3}">
							<c:choose>
								<c:when test="${page+2 > end}">		<!-- 마지막 3개 페이지 처리 ==> 마지막 세개 페이지에서는 다음이 없기 때문에 마지막 페이지는 고정된다. -->
									<c:forEach var="i" begin="${end-4}" end="${end}">
										<li>
											<a style="color:${(page==(i))?'cornflowerblue':''}" href="commList.do?p=${i}&k=${param.k}">${i}</a>
										</li>
									</c:forEach>			
								</c:when>
								<c:otherwise>
									<c:forEach var="i" begin="${page-2}" end="${page+2}">	<!-- 페이지 한개씩 바뀌기 -->
										<li>
											<a style="color:${(page==(i))?'cornflowerblue':''}" href="commList.do?p=${i}&k=${param.k}">${i}</a>
										</li>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:if test="${start+4 > end}"> <!-- 끝페이지가 5보다 적을 경우 -->
								<c:forEach var="i" begin="${start}" end="${end}">
									<li>
										<a style="color:${(page==(i))?'cornflowerblue':''}" href="commList.do?p=${i}&k=${param.k}">${i}</a>
									</li>
								</c:forEach>
							</c:if>
							<c:if test="${start+4 <= end}">
								<c:forEach var="i" begin="0" end="4">
									<li>
										<a style="color:${(page==(i+start))?'cornflowerblue':''}" href="commList.do?p=${i+start}&k=${param.k}">${i+start}</a>
									</li>
								</c:forEach>
							</c:if>							
						</c:otherwise>
					</c:choose>
					<c:if test="${page+2 < end}"> <!-- 더 넘어갈 페이지가 있는 경우 -->
						<a href="commList.do?p=${page+1}&k=${param.k}">
							<li>다음</li>
						</a>
					</c:if>
					<c:if test="${page == end}"><li></li></c:if>
				</ul>
			</tr>
		</table>
    </section>
    <script>
    const body = document.querySelector('body');
    const modal = document.querySelectorAll('.modal');
    const btnOpenPopup = document.querySelectorAll('.btn-open-popup');

    for (let i =0; i< btnOpenPopup.length; i++) {
    	let item = btnOpenPopup.item(i);
    	let modal_item = modal.item(i);
    	
	    item.addEventListener('click', () => {
	      modal_item.classList.toggle('show');
      	if (modal_item.classList.contains('show')) {
        	body.style.overflow = 'hidden';
      		}
   		})    	
    	modal_item.addEventListener('click', (event) => {
      	if (event.target === modal_item) {
        	modal_item.classList.toggle('show');
        	if (!modal_item.classList.contains('show')) {
          	body.style.overflow = 'auto';
        		}
      		}
   		});
   }



    </script>
</body>
</html>