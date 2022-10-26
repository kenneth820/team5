<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="Script/click11.js"></script>
</head>
<style>
header {
height:;
}

nav {
height: 808px;
background:;
}

ul, li {
list-style: none;
padding: 0px;
}

.main {
width: 100%;
height: 909px;
float: right;
border-left: 1px solid black;
}

#info {
justify-content: flex-end;
display: flex;
}

#updateProf {
padding-right: 8px;
}

#Logout {

}

.userinfo {
display: flex;
width: 100%;
padding: 12% 0% 12% 10%;
float: left;
}

.profilePhoto {
width: 20%;
}

#name-point {

}

#btn_group button {
background-color: black;
color: white;
padding: 5px;
}

#btn_group button:hover {
color: white;
background-color: gray;
}

.userInfo {
width: 70%;
float: right;
}

.intro {
font-size: 17px;
padding-left: 80px;
border-top: 1px dotted gray;
}

#form-commentInfo {
width: 100%;
}

#comment-count {
margin-bottom: 10px;
}

#comment-input {
width: 50%;
height: 3.3em;
}

#submit {
background-color: rgb(0, 128, 255);
width: 5.5em;
height: 3.3em;;
font-size: 15px;
font-weight: bold;
color: aliceblue;
}

#player_btn_group button {
background-color: black;
font-color: white;
padding: 5px;
}

#player_btn_group button:hover {
color: white;
background-color: gray;
}
</style>
<body>

<div class="main">
<header>

<div id="info">
<div id="updateProf">
<a href="updateMember.do?userId=${loginUser.userid}">개인정보수정</a>
</div>
<div id="Logout">
<a href="logout.do">로그아웃</a>
</div>
</div>

<div class="user-info">
<c:choose>
	<c:when test="${empty loginUser.pictureurl}">
		<img src="image/noimage.jpg">
	</c:when>
	<c:otherwise>
	<img src="profilePhoto/${loginUser.pictureurl}" 
	onerror="this.onerror=null; this.src='./image/noimage.jpg';" 
	alt="트랜드룸" class="image">									
	</c:otherwise>
</c:choose>

<div class="userInfo">
<table id=userinfoT>
<tr>
<td>유저 이름:</td>
<td>
<p class="name">${loginUser.name}</p>
</td>
</tr>
<tr>
<td>보유 포인트:</td>
<td>
<p class="point">${loginUser.point}p</p>
</td>
</tr>
<tr>
<td colspan="2">
<p class="selfcomment">${loginUser.selfcomment}</p>
</td>
</tr>
</table>

</div>
</div>

</header>

<nav>
<div id="btn_group" style="">
<!-- <input type="button" onclick="change1()" value="사진" style="background-color:yellow" id="myButton1"> -->
<!-- 아래 스크립트 함수있음. -->
<!-- 제목줄 : 사진 방명록 BGM -->
<ul style="width: 100%">
<li style="display: inline-block; width: 32%;">
<button data-toggle-id1="subscribe-mail"
style="width: 100%; height: 40px; font-size: 1em;">사진</button>
</li>
<li style="display: inline-block; width: 32%;">

<button style="width: 100%; height: 40px; font-size: 1em;" data-toggle-id2="subscribe-mail2">
 방명록
 </button>

</li>

<li style="display: inline-block; width: 32%;">
<button data-toggle-id3="subscribe-mail3"
style="width: 100%; height: 40px; font-size: 0.9em;">BGM</button>
</li>
</ul>
<!-- 내용역역 : 사진 방명록 BGM -->
<ul>
<li>
<form id="subscribe-mail" hidden>
<!-- 버튼누르면 스크립트 함수사용 아래 사진 띄워줌 사진레이아웃은 테이블사용 -->
<table border>
<tr>
<td><img src="Small1.jpg" width=100% height=220></td>
<td><img src="Small2.jpg" width=100% height=220></td>
<td><img src="Small3.jpg" width=100% height=220></td>
</tr>
<tr align=center>
<td>사진1</td>
<td>사진2</td>
<td>사진3</td>
</tr>
<tr>
<td><img src="Small5.jpg" width=100% height=220></td>
<td><img src="Small6.jpg" width=100% height=220></td>
<td><img src="Small7.jpg" width=100% height=220></td>
</tr>
<tr align=center>
<td>사진4</td>
<td>사진5</td>
<td>사진6</td>
</tr>
<tr>
<td><img src="Small8.jpg" width=100% height=220></td>
<td><img src="Small4.jpg" width=100% height=220></td>
<td><img src="Small9.jpg" width=100% height=220></td>
</tr>
<tr align=center>
<td>사진7</td>
<td>사진8</td>
<td>사진9</td>
</tr>
</table>
</form>
</li>
<li>
<form method="post" action="commentprofile.do" id="subscribe-mail2"
hidden>
<table>
<tr>
<td width="20%" height="15" bgcolor="#eeeeee"
style="padding-left: 6px;">${loginUser.userid}</td>
<td width="80%" style="padding-left: 10px;"><textarea
rows="5" cols="34" name="content" class=""></textarea></td>
<td style="padding-top: 35px;">
<input type="submit" value="등록" style="width: 40px; letter-spacing: 5px; float: right;">
</td>
</tr>
</table>
<br>
<br>

 <table>
<c:forEach var="comment" items="${commentList}">
<tr>
<td width="20%" height="10" bgcolor="#eeeeee"
style="padding-left: 10px;">${comment.replier}</td>
<td width="80%" style="padding-left: 10px;"><textarea
rows="5" cols="38" name="content" class="">${comment.reply} </textarea></td>
</tr>
</c:forEach>
</table>
</form>
</li>
<li>

</li>
<li>
<form id="subscribe-mail3" hidden>
<script type="text/javascript">
function $(id) {
return document.getElementById(id);
}
var audio = new Audio("team5_sdh/music1.mp3");
audio
.addEventListener(
"timeupdate",
function(e) {
$("time_info").innerHTML = "진행 : "
+ audio.currentTime
+ "/"
+ audio.duration;
$("volume_info").innerHTML = "볼륨 : "
+ audio.volume;
});
function changeVolume(v) {
var new_v = audio.volume + v;
if (0 <= new_v && new_v <= 1.0) {
audio.volume = new_v;
}
}
function audio_play() {
audio.play();
}
function audio_pause() {
audio.pause();
}
function audio_stop() {
audio.currentTime = 0;
audio.pause();
}
</script>
<div style="font-size: 1.5em; location: center;">BGM Player</div>
<div id="time_info"></div>
<div id="volume_info"></div>
<div id="btn_group">
<ul style="width: 100%; height: 40px;">
<li style="display: inline-block; width: 23%;"><input
type="button" value="play" onClick="audio_play()"
style="width: 100%; height: 40px; font-size: 1em;" /></li>
<li style="display: inline-block; width: 23%;"><input
type="button" value="pause" onClick="audio_pause()"
style="width: 100%; height: 40px; font-size: 1em;" /></li>
<li style="display: inline-block; width: 23%;"><input
type="button" value="Volume ↑" onClick="changeVolume(+0.2)"
style="width: 100%; height: 40px; font-size: 1em;" /></li>
<li style="display: inline-block; width: 23%;"><input
type="button" value="Volume ↓" onClick="changeVolume(-0.2)"
style="width: 100%; height: 40px; font-size: 1em;" /></li>
</ul>
</div>
</form>
</li>
</ul>
</div>
</nav>
<script>
change3()
</script>
<script>
change4()
</script>
<script>
change5()
</script>
</div>
</body>
</html>
