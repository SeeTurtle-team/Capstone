<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="resources/css/forgot.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot</title>
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
    
    <section class="forgot-form">
        <h1></h1>
        <div class="bar_logo">
            <a href="login.html">Your PassWord</a>
        </div>
        <form action="">
            <div class="forgot-area">
                <input type="text" name="forgot_id" id="forgot_id"
                autocomplete="off" required>
               
            </div>
           
                        
           <div class="btn-area">
               <button type="submit" onclick="location.href='index.html'">Sign in</button>
            
           </div>
        </form>
    </section>

        <script> 
            function change() {
              const ans= document.getElementById('forgot_answer');
              ans.innerText = '당신이 가장 좋아하는 차량은?'
          }
            
           
           
               </script>


</body>
</html>