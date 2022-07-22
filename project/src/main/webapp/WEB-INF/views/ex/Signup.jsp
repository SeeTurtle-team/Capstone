<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>회원가입</title>
	<meta charset="utf-8">
	    <title>졸프 첫 페이지</title>
	    <style>
	        #header{
	            height: 100px;
	        }
	        #nav{
	            width:300px;
	            height: 300px;
	            float :left;
	            padding : 10px
	        }
	        #section{
	            width: 200px;
	            
	        }
	    </style>
</head>
<script type="text/javascript">
	function signUp(){
		console.log("sign Up!!!!");
		var userID = document.getElementById("userID").value;
		var userPw = document.getElementById("userPw").value;
		
		if(userID==""){
			alert("아이디를 입력해주세요");
			return false;
		}
		if(userPw==""){
			alert("비밀번호를 입력해주세요");
			return false;
		}
		console.log(userID);
		console.log(userPw);
		alert("회원가입이 완료되었습니다!");
		location.href="/SignUp"+"?option=signUp&userId="+userID+"&userPw="+userPw;
	}
	
	
	
	
</script>
<body>
	 <div id="nav">
	        <h2>  회원 가입</h2>
	        <form method="post" id="authForm" action="javascript:logIn()">
				  <div>
				    <label for="loginId">아이디</label>
				    <input type="text" id="userID" name="loginId"  value="" placeholder="아이디를 입력하세요"><br>
				    <label for="loginPw">비밀번호</label>
				    <input type="text" id="userPw" name="password"  value="" placeholder="비밀번호를 입력하세요">
				 
				  <input id="button" type="button" onclick="signUp()" value="회원가입">
				   </div>
				  <!--  <div>
				    <input type="checkbox" id="keepLogin" name="keepLogin">
				    <label for="keepLogin"><span>로그인 상태 유지</span></label>
				  </div>-->
			</form>
	  </div>
		<img class="image1" src="resources/img/image1.jpg" alt="My Image">
</body>
</html>