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
		let cmd = $('#cmd').val();
        let uid = $('#uid').val();
        let pwd = $('#pwd').val();

        if (uid.trim() === '' || pwd.trim() === '') {
            alert('아이디와 암호를 모두 입력해주세요.');
            return false; 
        }
     
        let obj = {};
		obj.cmd = cmd;
		obj.uid = uid;
		obj.pwd = pwd;

        $.ajax({
            url: 'user',
            method: 'post',
            cache: false,
            data: obj,
            dataType: 'json',
            success: function(res) {
            	alert(res.added ? '추가 성공' : '추가 실패');
                if (res.added) {
                    location.href = 'user?cmd=list';
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
    <form action="user" method="post" onsubmit="return addUser();"> 
    	<input type="hidden" name="cmd" id="cmd" value="adduser">
        <div><label for="uid">아이디</label>
			<input type="text" name="uid" id="uid">
		</div>
        <div><label for="pwd">암호</label>
			<input type="password" name="pwd" id="pwd">
		</div>
        <div>
			<button type="reset">취소</button>
			<button type="submit">저장</button>
		</div>
    </form>  
</div>     
</body>
</html>
