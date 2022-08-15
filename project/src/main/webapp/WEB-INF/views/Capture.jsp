<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="건호" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Electrolize&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
    <!--swiper 가져옴 -->
    <script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <!--제이쿼리 가져옴-->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <title>Capture</title>
    <script src="https://kit.fontawesome.com/af4e1eff79.js" crossorigin="anonymous"></script>
</head>

<style>
body{
    width: 100vw;
    height: 100vh;
    margin:0;
}
.helpImage{
    box-sizing: content-box;
    width: 100%;
    height: 70%;
    margin-right:10%;
}
img{
    object-fit: contain;
    margin:auto;
    width: 100%;
    height: 100%;
}

h2,h3{
    text-align: center;
    margin:auto;
    width: 100%;
    height: 10%;
}
button{
display:block;
margin:auto;
}

</style>

<body>
	<div class="helpImage">
	<img src="${cctv.imgUrl}">
	</div>
	<h2 class="abc">차량 모델 : ${cctv.model}</h2>
	<h3 class="abcd">지나간 시간 : ${cctv.time}</h3>
	<button id="back">돌아가기</button>
</body>

<script>
 $(function (){
	 $("#back").click(function(){
		 location.href="/carList";
	 })
 })
</script>

</html>