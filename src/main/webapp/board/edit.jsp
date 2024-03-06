<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*" %>
<!-- taglib : jstl을 사용하기 위한 라이브러리 설정 -->    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!-- 
	jsp:useBean
	class의 패키지이름을 포함한 자바빈 클래스의 완전한 이름을 id속성에서 지정한 이름으로 불러 올 수 있게 해준다.
	id 속성값이 있을 경우 객체를 ㄱ드대로 사용하고, 없을 경우 새로운 객체를 생성한다.
 -->
<jsp:useBean id="vo" class="board.BoardVO" />
<jsp:useBean id="dao" class="board.BoardDAO" />
<!-- 프로퍼티갑슬 지정할 자바빈 객체에 id를 name에 넣고 property값을 *로 기정할 경우 각각의 프로퍼티의 값을 파라미터 값으로 설정  -->
<jsp:setProperty name="vo" property="*" />
<%
	dao.update(vo);
%>

<c:redirect url="${pageContext.request.contextPath}/boardDetail.jsp?num=${vo.num}"/>
