<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>ȸ������</title>
	<meta charset="utf-8">
	    <title>���� ù ������</title>
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
			alert("���̵� �Է����ּ���");
			return false;
		}
		if(userPw==""){
			alert("��й�ȣ�� �Է����ּ���");
			return false;
		}
		console.log(userID);
		console.log(userPw);
		alert("ȸ�������� �Ϸ�Ǿ����ϴ�!");
		location.href="/SignUp"+"?option=signUp&userId="+userID+"&userPw="+userPw;
	}
	
	
	
	
</script>
<body>
	 <div id="nav">
	        <h2>  ȸ�� ����</h2>
	        <form method="post" id="authForm" action="javascript:logIn()">
				  <div>
				    <label for="loginId">���̵�</label>
				    <input type="text" id="userID" name="loginId"  value="" placeholder="���̵� �Է��ϼ���"><br>
				    <label for="loginPw">��й�ȣ</label>
				    <input type="text" id="userPw" name="password"  value="" placeholder="��й�ȣ�� �Է��ϼ���">
				 
				  <input id="button" type="button" onclick="signUp()" value="ȸ������">
				   </div>
				  <!--  <div>
				    <input type="checkbox" id="keepLogin" name="keepLogin">
				    <label for="keepLogin"><span>�α��� ���� ����</span></label>
				  </div>-->
			</form>
	  </div>
		<img class="image1" src="resources/img/image1.jpg" alt="My Image">
</body>
</html>