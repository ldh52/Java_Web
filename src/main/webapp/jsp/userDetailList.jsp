<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.test.sku.servlet.User"%>
<%
	User user = (User)request.getAttribute("detail");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 상세보기</title>
</head>
<body>
<h3>회원정보 상세보기</h3>
<div id="main">
<% if (user != null) { %>
        <p>아이디: <%= user.getUid() %></p>
        <p>암호: <%= user.getPwd() %></p>
	<% } %>
</div>
</body>
</html>