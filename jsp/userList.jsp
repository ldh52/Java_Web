<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.test.sku.servlet.User"%>
<%
	List<User> userList = (List<User>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<style type="text/css">
	table { text-align:center; border:1px solid black; padding:10px border-spacing: 0; border-collapse: collapse; }
	tr:first-child>th { background-color:#ddd; border-bottom: 3px double black; }
	th, td { border:1px solid black; padding: 0.2em 1em; }
	td>a { text-decoration:none; color:blue; } 
	tr:nth-child(odd) { background-color:#cde; }
	main { width:fit-content; margin:1em auto; }
	h3 { text-align:center; text-decoration:underline; }
</style>
</head>
<body>
<main>
<h3>이용자 목록보기</h3>
<table>
<tr><th>아이디</th><th>암호</th></tr>
<%
	for(int i=0;i<userList.size();i++){
		User u = userList.get(i); %>
		<tr>
			<td><a href="user?cmd=detail&uid=<%= u.getUid() %>"><%= u.getUid() %></a></td>
			<td><%= u.getPwd() %></td>
		</tr> 
	<% } 
%>
</table>	
</main>
</body>
</html>