<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.test.sku.pet.PetVO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>펫 정보 수정</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <%-- jQuery 추가 --%>
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
                        // JSON 응답 처리
                        if (response.success) {
                            alert(response.message); 
                            location.href = "${pageContext.request.contextPath}/pet?action=list";
                        } else {
                            alert(response.message); 
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

<form id="petEditForm" action="<%= request.getContextPath() %>/pet?action=update" method="post" enctype="multipart/form-data"> <%-- 폼 ID 추가 --%>
    <input type="hidden" name="no" value="<%= pet.getNo() %>">
    <table>
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