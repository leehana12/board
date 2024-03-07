<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- taglib : jstl을 사용하기 위한 라이브러리 설정 -->    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="dao" class="board.BoardDAO" />
<%
	int num = Integer.parseInt(request.getParameter("num"));
	dao.delete(num);
%>

<c:redirect url="${pageContext.request.contextPath}/list.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>