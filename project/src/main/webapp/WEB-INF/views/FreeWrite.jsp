<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="건호" content="width=device-width, initial-scale=1.0">
    <title>Free_Write</title>
    <link rel="stylesheet" href="resources/css/Free_Write.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Electrolize&display=swap" rel="stylesheet">
<script
     src="https://kit.fontawesome.com/af4e1eff79.js"
     crossorigin="anonymous"></script>

</head>

<body>

<div class="all">
    <header class="back_color"></header><!----3.23--->
    <footer class="back_color2"></footer><!---3.23-->
        <ul class="bar_menu">
            <li class="bar_logo">
                <i class="fa-solid fa-car-crash"></i>
                <a href="/"><b>HansungProject</b></a>
            </li>
            <li><a href="/carList">CCTV_analysis</a></li>
            <li><a href="/CarModel">Car_model </a></li>
            <li><a href="/QnA">QnA</a></li>
            <li><a href="/free">Free_Board</a></li>
    		<li><a href="/developer">Developer</a></li>
        </ul>    
     
<br>


<div class="BackGroundBox_Psfixed">
    <div class="title">
        <h1>Free_Write</h1>
    </div>

    <section>
        <div class="bar1">&nbsp<h5 class="write_title1">*표는 필수 입력사항입니다.</h5></div>
        
		<form action="/freeEnroll"enctype="multipart/form-data" method="post"> 
		    <div class="bar2"><h1 class="write_title2">작성 제목*</h1></div>
	    	<input name= title type="search-input" class="search-input" id="title" value="">
	
	    	<div class="bar3"> <h1 class="write_title2">작성 내용*</h1></div>
	    	<table class="content_table">
	        <tr>
		        <td>
		        	<textarea name="content" class="content" id="content"></textarea>
		        </td>	
	        </tr>
	        </table>
	       
	       
	        <input class="data" type='file' name='imgFile' multiple/>
	        
	
	        <div class="hr">${id}</div>
	        <button class="d-btn" type="submit">등록</button >   
		</form>



</section>



</div> <!-------con-->



    
 </div>    <!-------all_tb---3.23------->

 
</div>
</body>

<script>

</script>



</html>