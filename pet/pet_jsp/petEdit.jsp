<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.test.sku.pet.PetVO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>펫 정보 수정</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
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
            max-width: 100px;
            height: auto;
        }
    </style>
    <script>
        $(function() {
            $("#petEditForm").submit(function(event) {
                event.preventDefault();

                var formData = new FormData(this);

                $.ajax({
                    url: this.action,
                    type: this.method,
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function(response) {
                    	try {
                            var parsedResponse = JSON.parse(response); 
                            if (parsedResponse.success) {
                                alert(parsedResponse.message);
                                location.href = "${pageContext.request.contextPath}/pet?action=list";
                            } else {
                                alert(parsedResponse.message);
                            }
                        } catch (e) {
                            alert("서버 응답 파싱 오류: " + e.message);
                        }
                    },
                    error: function(xhr, status, error) {
                        alert("펫 정보 수정 요청 중 오류가 발생했습니다.");
                    }
                });
            });
        });
    </script>
</head>
<body>

<h2>펫 정보 수정</h2>

<%
PetVO pet = (PetVO) request.getAttribute("pet");
if (pet != null) {
    String birthDateString = new SimpleDateFormat("yyyy-MM-dd").format(pet.getBirth());
%>

<form id="petEditForm" action="<%= request.getContextPath() %>/pet?action=update" method="post" enctype="multipart/form-data">
    <input type="hidden" name="no" value="<%= pet.getNo() %>">
    <table>
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
            <td><input type="number" name="weight" min="0.1" step="0.1" value="<%= pet.getWeight() %>" required></td> 
        </tr>
        <tr>
            <td>생년월일:</td>
            <td><%= birthDateString %></td> 
        </tr>
        <tr>
            <td>가격:</td>
            <td><input type="number" name="price" min="1000" step="1000" value="<%= pet.getPrice() %>" required></td> 
        </tr>
        <tr>
            <td>사진:</td>
            <td><img src="<%= request.getContextPath() %>/img/pet/<%= pet.getPic() %>" alt="<%= pet.getName() %> 사진" width="100">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button type="submit">수정</button>
                <button type="button" onclick="location.href='<%= request.getContextPath() %>/pet?action=list'">목록</button>
            </td>
        </tr>
    </table>
</form>

<% 
} else {
%>
<p>펫 정보가 없습니다.</p> 
<%
}
%>

</body>
</html>