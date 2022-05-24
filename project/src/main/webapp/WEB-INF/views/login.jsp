<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <link rel="stylesheet" href="/resources/css/create.css">
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
   
    <section class="forgot-form">
    <h1></h1>
    <div class="bar_logo">
        <a href="/">HansungProject</a>
    </div>
    <form action="/login" method="POST"> 
        <div class="forgot-area">
            <input type="text" name="id" id="userId" value=""
            autocomplete="off" required>
            <label for="id">USER NAME</label>
        </div>

        <div class="forgot-area">
            <input type="password" name="pw" id="userPw" value=""
            autocomplete="off" required>
            <label for="pw">PASSWORD</label>
        </div>
        
       <div class="btn-area">
           <button type="submit" >LOGIN</button>
       </div>
    </form> 

    <div class="caption">
        <a href="/create">Create Account</a>
    </div>

    <div class="caption">
        <a href="/forgot">Forgot Password?</a>
    </div>
    </section>
    <script> 
		function login(){
			console.log("log in");
			var userID = document.getElementById("userId").value;
			var userPw = document.getElementById("userPw").value;
			
			console.log(userID);
			console.log(userPw);
			
			location.href="/login"+"?option=login&userId="+userID+"&userPw="+userPw;
		}
		var a = "${failFlag}";
		console.log(a);
		if ( a=="no") {
			alert("로그인거부 : 아이디와 비밀번호를 확인하세요");
		}
		var s = "${loginAgain}";
		console.log(s);
		if (s == "login"){
			alert("다시 로그인 해주세요");
		}
    </script>
</body>
</html>