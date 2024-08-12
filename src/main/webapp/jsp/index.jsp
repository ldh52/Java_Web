<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello~</title>
<style type="text/css">
	img { width:50%; }
	main div:last-child {display:flex; justify-content:center; margin-top:4em;}
	li {display: inline-block; width:5em;}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
    function logout() {
        $.ajax({
            url: 'user?cmd=logout',
            method: 'post',
            cache: false,
            dataType: 'json',
            success: function(res) {
                if (res.logout) { 
                    alert('로그아웃 되었습니다.'); 
                    location.href = 'user?cmd=loginForm'; 
                } else {
                    alert('로그아웃 실패');
                }
            },
            error: function(xhr, status, err) {
                alert('에러: ' + err);
            }
        });
    }
</script>

</head>
<body>
<main>
   <nav>
      <ul>
         <li><a href="user">HOME</a>
         <li><a href="user?cmd=loginForm">LOGIN</a>
         <li><a href="user?cmd=list">LIST</a>
         <li><a href="user?cmd=addForm">ADD</a>
         <li><a href="javascript:logout()">LOGOUT</a>
      </ul>
   </nav>
   <div><img src="img/bird.jpg"></div>
</main>
</body>
</html>