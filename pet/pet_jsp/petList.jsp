<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.test.sku.pet.PetVO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펫 목록</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>
function search() {
   var cat = $('#category').val();
   alert('검색할 카테고리:' + cat);
}
</script>
<style>
    /* CSS 클래스 정의 */
    .container {
        width: 80%;
        margin: 20px auto;
    }

    .table {
        width: 100%;
        border-collapse: collapse;
    }

    .table th, .table td {
        padding: 10px;
        border: 1px solid #ccc;
        text-align: center;
    }

    .table th {
        background-color: #f0f0f0;
    }

    .table img {
        max-width: 100px;
        height: auto;
    }
</style>
</head>
<body>

<div class="container">
    <h2>펫 목록</h2>

	<form action="pet" method="get"> 
    <input type="hidden" name="action" value="search"> 
    검색 카테고리
    <select name="category" id="category">
        <option value="no">번호</option>
        <option value="name">이름</option>
    </select>
    <input type="text" name="keyword" id="keyword">
    <button type="submit">검색</button>
</form>
    <br>

    <button type="button" onclick="location.href='${pageContext.request.contextPath}/pet/petInput.jsp'">펫 추가</button><br>
    <br>

    <table class="table">
        <thead>
        <tr>
            <th>번호</th>
            <th>사진</th>
            <th>이름</th>
            <th>원산지</th>
            <th>몸무게(kg)</th>
            <th>생년월일</th>
            <th>가격</th>
            <!-- <th>수정</th>
            <th>삭제</th> -->
        </tr>
        </thead>
        <tbody>
        <%
            List<PetVO> petList = (List<PetVO>) request.getAttribute("petList");
            if (petList != null && !petList.isEmpty()) {
                for (PetVO pet : petList) {
        %>
        <tr>
            <td><%= pet.getNo() %>
            </td>
            
            <td>
                <% if (pet.getPic() != null) { %>
                <img src="${pageContext.request.contextPath}/img/pet/<%= pet.getPic() %>" alt="<%= pet.getName() %> 사진">
                <% } %>
            </td>
            
            <td><a href="${pageContext.request.contextPath}/pet?action=detail&no=<%= pet.getNo() %>"><%= pet.getName() %>
            </a></td>
            <td><%= pet.getOrigin() %>
            </td>
            <td><%= pet.getWeight() %>
            </td>
            <td><%= new SimpleDateFormat("yyyy-MM-dd").format(pet.getBirth()) %>
            </td>
            <td><%= String.format("%,d", pet.getPrice()) %>원</td>
            <!--  
            <td><a href="${pageContext.request.contextPath}/pet?action=edit&no=<%= pet.getNo() %>">수정</a></td>
            <td>
                <form action="${pageContext.request.contextPath}/pet?action=delete" method="post" style="display: inline;">
                    <input type="hidden" name="no" value="<%= pet.getNo() %>">
                    <button type="submit" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</button>
                </form>
            </td> -->
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="9">등록된 펫이 없습니다.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>