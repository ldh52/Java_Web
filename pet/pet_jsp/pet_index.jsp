<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>펫 판매점</title>
    <style>
        /* 기본 스타일 */
        body {
            font-family: sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4; /* 배경색 추가 */
            color: #333; /* 글자 색상 추가 */
        }

        header {
            text-align: center;
            padding: 2rem 0;
            background-color: #fff; /* 헤더 배경색 추가 */
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 그림자 효과 추가 */
        }

        h1 {
            margin: 0;
        }

        main {
            padding: 2rem;
        }
    </style>
</head>
<body>

<header>
    <h1>펫 판매점에 오신 것을 환영합니다!</h1>
</header>

<main>
    <%
        response.sendRedirect(request.getContextPath() + "/pet?action=list");
    %>
</main>

</body>
</html>