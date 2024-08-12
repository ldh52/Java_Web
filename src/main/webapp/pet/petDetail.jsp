<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.test.sku.pet.PetVO" %>
<%@ page import="java.text.SimpleDateFormat" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펫 상세 정보</title>

<style>
    /* 기본 스타일 */
    body {
        font-family: sans-serif;
        line-height: 1.6;
        margin: 0;
        padding: 0;
    }

    h2 {
        text-align: center;
        margin-top: 2rem;
    }

    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
    }

    th, td {
        padding: 15px;
        border: 1px solid #ccc;
        text-align: left;
    }

    th {
        background-color: #f0f0f0;
        text-align: center;
    }

    img {
        max-width: 200px; /* 이미지 최대 너비 설정 */
        height: auto;
    }
</style>

</head>
<body>

<h2>펫 상세 정보</h2>

<%
PetVO pet = (PetVO) request.getAttribute("pet"); 
if(pet != null) { // pet 객체가 null인지 확인
%>
<table>
    <tr>
        <th>번호</th>
        <td><%= pet.getNo() %></td> 
    </tr>
    <tr>
        <th>사진</th>
        <td><img src="<%= request.getContextPath() %>/img/pet/<%= pet.getPic() %>" alt="<%= pet.getName() %> 사진"></td> 
    </tr>
    <tr>
        <th>이름</th>
        <td><%= pet.getName() %></td> 
    </tr>
    <tr>
        <th>원산지</th>
        <td><%= pet.getOrigin() %></td> 
    </tr>
    <tr>
        <th>몸무게(kg)</th>
        <td><%= pet.getWeight() %></td> 
    </tr>
    <tr>
        <th>생년월일</th>
        <td><%= new SimpleDateFormat("yyyy-MM-dd").format(pet.getBirth()) %></td> 
    </tr>
    <tr>
        <th>가격</th>
        <td><%= String.format("%,d", pet.getPrice()) %>원</td> 
    </tr>
</table>
<%
} else {
%>
<p>펫 정보가 없습니다.</p> 
<%
}
%>

<div>
<center>
<button type="button" onclick="location.href='<%= request.getContextPath() %>/pet?action=list'">목록으로 돌아가기</button>

<form action="${pageContext.request.contextPath}/pet?action=edit&no=<%= pet.getNo() %>" method="post" style="display: inline;">
<input type="hidden" name="no" value="<%= pet.getNo() %>">
<button type="button" onclick="location.href='<%= request.getContextPath() %>/pet?action=edit&no=<%= pet.getNo() %>'">수정</a></button>
</form>

<form action="${pageContext.request.contextPath}/pet?action=delete" method="post" style="display: inline;">
<input type="hidden" name="no" value="<%= pet.getNo() %>">
<button type="submit" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</button>
</form>

</center>
</div> 

</body>
</html>