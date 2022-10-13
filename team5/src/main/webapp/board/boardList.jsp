<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>게시글 리스트</h2>
	<table class="list">
		<tr>
			<td colspan="5" style="border:white; text-algin:right; padding-right:10px;">
				<a href="../board"> 게시글 등록</a>
			</td>
		</tr>
		<tr>
			<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회</th>
		</tr>
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.num}</td>
				<td><a href="board?num=${board.num}">${board.title}</a></td>
				<td>${board.name}</td>
				<td>${board.writedate}</td>
				<td>${board.readcount}</td>
				
			</tr>
		</c:forEach>
	</table>
</body>
</html>