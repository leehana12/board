<%@page import="board.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- taglib : jstl을 사용하기 위한 라이브러리 설정 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	//BoardDAO를 사용하기 위해 객체 생성
	BoardDAO dao = new BoardDAO();
	//dao에서 selectAll메소드를 통해 전체 게시글을 가져와 List<>타입의 ls변수에 할당해주는 것이다.
	//List<>는 순서가 있는 데이터의 모음이다. 삽입 순서가 유지되며, 중복 요소를 허용한다.
	List<BoardVO> ls = dao.selectAll();
	//pageContext : 한페이지 요청~응답까지 과정에 관련된 여러가지 행동을 하는 객체 / request요청/responcse응답 과 유사
	//List<BoardVO> 타입의 변수의 값을 가져와 ls속성명으로 불러 올수 있다.
	pageContext.setAttribute("ls", ls);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록</title>
</head>
<body>
	<h2>게시글 목록</h2>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="board" items="${ls}">
			<tr>
				<td>${board.num}</td>
				<td><a href="${pageContext.request.contextPath}/board/boardDetail.jsp?num=${board.num}">${board.title}</a></td>
				<td>${board.writer}</td>
				<td>${board.regdate}</td>
				<td>${board.cnt}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="<c:url value="/board/registForm.jsp"/>"><button>글등록</button> </a>
</body>
</html>


