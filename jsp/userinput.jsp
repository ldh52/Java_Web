<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이용자 정보 추가</title>
<style type="text/css">
	#main { width:fit-content; margin:0.5em auto; padding:1em; }
	form { border:1px solid black; padding:0.5em;}
	h3 { text-align:center; }
	div:last-child { margin-top:0.3em; text-align:center; }
	label { display:inline-block; width:3em; } 	
</style>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>
    function addUser() {
		var ser = $('#addForm').serialize();
		$.ajax({
            url: 'user',
            method: 'post',
            cache: false,
            data: ser,
            dataType: 'json',
            success: function(res) {
            	alert(res.added ? '추가 성공' : '추가 실패');
                if (res.added) {
                    location.href = 'user?cmd=detail&uid=' + $('#uid').val();
                }
            },
            error: function(xhr, status, err) {
                alert('에러: ' + err);
            }
        });

        return false;
    }
</script>
</head>
<body>
<div id="main">
    <h3>이용자 정보 추가</h3>
    <form id="addForm" action="user" method="post">
    	<input type="hidden" name="cmd" id ="cmd" value="add">
        <div>
        	<label for="uid">아이디</label>
			<input type="text" name="uid" id="uid">
		</div>
        <div>
        	<label for="pwd">암호</label>
			<input type="password" name="pwd" id="pwd">
		</div>
        <div>
			<button type="reset">취소</button>
			<button type="button" onclick="addUser();">저장</button>
		</div>
    </form>  
</div>     
</body>
</html>
