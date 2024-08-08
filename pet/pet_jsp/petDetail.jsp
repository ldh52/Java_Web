<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.test.sku.pet.PetVO" %> <%-- PetVO import 추가 --%>
<%@ page import="java.text.SimpleDateFormat" %> <%-- SimpleDateFormat import 추가 --%>
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
        padding: 10px;
        border: 1px solid #ccc;
        text-align: left;
    }

    th {
        background-color: #f0f0f0;
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
        <td>번호:</td>
        <td><%= pet.getNo() %></td> 
    </tr>
    <tr>
        <td>사진:</td>
        <td><img src="<%= request.getContextPath() %>/img/pet/<%= pet.getPic() %>" alt="<%= pet.getName() %> 사진"></td> 
    </tr>
    <tr>
        <td>이름:</td>
        <td><%= pet.getName() %></td> 
    </tr>
    <tr>
        <td>원산지:</td>
        <td><%= pet.getOrigin() %></td> 
    </tr>
    <tr>
        <td>몸무게(kg):</td>
        <td><%= pet.getWeight() %></td> 
    </tr>
    <tr>
        <td>생년월일:</td>
        <td><%= new SimpleDateFormat("yyyy-MM-dd").format(pet.getBirth()) %></td> 
    </tr>
    <tr>
        <td>가격:</td>
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

<button type="button" onclick="location.href='<%= request.getContextPath() %>/pet?action=list'">목록으로 돌아가기</button> <%-- 스크립트릿으로 변경 --%>

</body>
</html>