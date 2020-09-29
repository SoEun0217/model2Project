<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- html 주석 -->
	<%-- jsp 주석  
	    <% %> : jsp안에 java code를 자유롭게 기술할 수 있는 영역, scriptlet tag
	    <%= %> : java의 method의 실행결과, 변수를 출력 expression tag
	--%>
	<% 
	   Date date = new Date();
	   out.println(date);
	%>
	<h4>현재 날짜는 <%=date%></h4>
	<h2>사용자 관리 메인</h2>
	<ol>
		<li><a href="user.do?cmd=userList">사용자 리스트</a></li>
	</ol>
</body>
</html>