<%@page import="board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="dao" class="board.BoardDAO" /><!-- id로 board.BoardDAO를 불러옴 -->
<%
	//파라미털을 받아와 정수형int로 변환해 num에 저장
	int num = Integer.parseInt(request.getParameter("num"));
	//selectOne메소드를 호출해 num에 해당하는 게시글 정보를 데이터베이스에서 조회해 vo변수에 저장
	BoardVO vo = dao.selectOne(num); 
	//jsp페이지의 컨텍스트에 "vo"에 vo객체를 저장
	pageContext.setAttribute("vo", vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
</head>
<body>
	<form action="edit.jsp" method="post" >
		<input type="hidden" name="num" value="${vo.num}" >
		<!-- required 속성은 폼 데이터(form data)가 서버로 제출되기 전 반드시 채워져 있어야 하는 입력 필드를 명시 -->
		<input type="text" name="title" value="${vo.title}" required ><br>
		<!-- disabled 속성은 해당 <input> 요소가 비활성화됨을 명시합니다 /값이 전달되지 않아 내용 수정이 안됨.-->
		<!-- readonly는 값이 전달 되는 반면 desabled은 값이 전달 되지 않는다. -->
		<input type="text" name="writer" value="${vo.writer}" required readonly ><br>
		<textarea rows="4" cols="20" name="content">${vo.content}</textarea><br>	
		<input type="submit" value="수정" >	
	</form>
	<a href="<c:url value="/board/list.jsp?num=${vo.num}" />"><button>게시판</button> </a>
</body>
</html>