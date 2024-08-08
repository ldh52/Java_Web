<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>펫 정보 입력</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <%-- jQuery 추가 --%>
    <style>
        /* 기본 스타일 (필요에 따라 추가/수정) */
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

        input[type="text"], input[type="number"], input[type="date"], input[type="file"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        button {
            padding: 10px 20px;
            margin: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #3e8e41;
        }
    </style>
    <script>
        // 폼 제출 이벤트 핸들러 등록 (jQuery 사용)
        $(function() { // jQuery shorthand for $(document).ready()
            $("#petForm").submit(function(event) {
                event.preventDefault();

                var formData = new FormData(this);

                $.ajax({
                    url: this.action,
                    type: this.method,
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function(response) {
                        if (response.success) {
                            alert(response.message); // 서버에서 전달된 성공 메시지 표시
                            location.href = "${pageContext.request.contextPath}/pet?action=list";
                        } else {
                            alert(response.message); // 서버에서 전달된 에러 메시지 표시
                        }
                    },
                    error: function(xhr, status, error) {
                        // AJAX 요청 자체가 실패한 경우 (예: 네트워크 오류)
                        alert("펫 정보 등록 요청 중 오류가 발생했습니다.");
                    }
                });
            });
        });
    </script>
</head>
<body>

<h2>펫 정보 입력</h2>

<form id="petForm" action="${pageContext.request.contextPath}/pet?action=add" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>이름:</td>
            <td><input type="text" name="name" required></td>
        </tr>
        <tr>
            <td>원산지:</td>
            <td><input type="text" name="origin" required></td>
        </tr>
        <tr>
            <td>몸무게(kg):</td>
            <td><input type="number" name="weight" min="0.1" step="0.1" required></td>
        </tr>
        <tr>
            <td>생년월일:</td>
            <td><input type="date" name="birth" required></td>
        </tr>
        <tr>
            <td>가격:</td>
            <td><input type="number" name="price" min="1000" step="1000" required></td>
        </tr>
        <tr>
            <td>사진:</td>
            <td><input type="file" name="pic" accept="image/*" required></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button type="submit">등록</button>
                <button type="reset">초기화</button>
                <button type="button" onclick="location.href='${pageContext.request.contextPath}/pet?action=list'">목록</button>
            </td>
        </tr>
    </table>
</form>

</body>
</html>