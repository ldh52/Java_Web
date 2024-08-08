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
<style type="text/css">
	table { text-align:center; border:1px solid black; padding:10px 
	border-spacing: 0; border-collapse: collapse; margin:0.2em auto; 
	}
	tr>th { border-right: 3px double black; }
	th { background-color:#ddd; }
	th, td { border:1px solid black; padding: 0.2em 1em; }
	td>a { text-decoration:none; color:blue; }
	main { width:fit-content; margin:1em auto; text-align:center; }
	main>a { text-align:center; text-decoration:none; color:blue;}
	h3 { text-align:center; text-decoration:underline; }
	div>a, div>button>a { text-align:center; text-decoration:none; color:blue; }
</style>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function goList() {
		location.href="user?cmd=list";
	}
	
	function deleteUser() {
		if (!confirm('정말 삭제하시겠습니까?')) return;
        let obj = {};
        obj.cmd = 'delete'; 
        obj.uid = '<%= user.getUid()%>';
		
		$.ajax({		
			url: 'user', 
			method: 'post',
			cache: false,
			data: obj,
			dataType: 'json',
			success: function(res) {
				alert(res.deleted ? '삭제 성공' : '삭제 실패');
			},
			error: function(xhr,status,err) {
				alert('에러:' + err)
			}
		});		
	}
</script>
<body>
<main>
<h3>회원정보 상세보기</h3>
<table>
	<tr>
		<th>아이디</th><td>
		<% if (user != null) { %>
        <%= user.getUid() %>
        <% } %>
		</td>
	</tr>
	<tr>
		<th>암호</th><td>
		<% if (user != null) { %>
        <%= user.getPwd() %>
		<% } %>
		</td>
	</tr>
</table><br>
<div>
<a href="user?cmd=list">목록보기</a>
<a href="user?cmd=list"><button>목록보기</button></a>
<a href="user?cmd=list"><button onclick="goList();">목록보기2</button></a>
<a href="user?cmd=edit&uid=<%=user.getUid()%>">수정</a>
<a href="javascript:deleteUser();">삭제</a>
</div>
</main>
</body>
</html>