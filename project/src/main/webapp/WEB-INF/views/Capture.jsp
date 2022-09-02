<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="건호" content="width=device-width, initial-scale=1.0">
<link
	href="https://fonts.googleapis.com/css2?family=Electrolize&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<link rel="stylesheet" href="resources/css/Free_Write.css">
<!--swiper 가져옴 -->
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<!--제이쿼리 가져옴-->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<title>Capture</title>
<script src="https://kit.fontawesome.com/af4e1eff79.js"
	crossorigin="anonymous"></script>
</head>

<style>
body {
	width: 100vw;
	height: 100vh;
	margin: 0;
}

.helpImage {
	box-sizing: content-box;
	width: 100%;
	height: 70%;
	margin-right: 10%;
}

img {
	object-fit: contain;
	margin: auto;
	width: 100%;
	height: 100%;
}

h2, h3 {
	text-align: center;
	margin: auto;
	width: 100%;
	height: 10%;
}

button {
	display: block;
	margin: auto;
}
</style>

<body>
	<div class="all">
		<ul class="bar_menu">
			<li class="bar_logo"><i class="fa-solid fa-car-crash"></i>
			 <a	href="/">HANSUNG</a> <a href="#" class="bar_toogle"> 
			 <i	class="fa-solid fa-bars"></i>
			</a></li>
			<div class="nav_li">
				<li title="CCTV 페이지" onclick="location.href='/carList'"><a href="/carList">CCTV</a></li>
				<li title= "차량 정보 페이지" onclick="location.href='/CarModel'"><a href="/CarModel">Vehicle </a></li>
				<li title = "자유게시판" onclick="location.href='/free'"><a href="/free">Spaces</a></li>
				<li title = "QnA 게시판"onclick="location.href='/QnA'"><a href="/QnA">Questions</a></li>
				<li title = "개발한 이들" onclick="location.href='/developer'"><a href="/developer">Developer</a></li>
			</div>
		</ul>

		<header>
			<div class="header_name">Spaces</div>
			<div class="menu">
				<div class="Login_menu" onclick="logOut()">
					<a href="/carList">LIST</a>
				</div>
				<div class="Login_menu" onclick="logOut()">
					<a href="/?option=logOut">EXIT</a>
				</div>
			</div>
		</header>
		<div class="BackGroundBox_Psfixed">
			<section>

				<div class="helpImage">
					<img src="${cctv.imgUrl}">
				</div>
				<br>
				<h2 class="abc">차량 모델 : ${cctv.model}</h2>
				<h3 class="abcd">지나간 시간 : ${cctv.time}</h3>
				<button class="d-btn5" id="back" title="이 시간대부터 CCTV를 보실 수 있습니다">CCTV 보기</button>

			</section>



		</div>
		<!-------con-->




	</div>
	<!-------all_tb---3.23------->


</body>

<script>
	$(function() {
		$("#back").click(function() {
			var seq = "${cctv.seq}";
			location.href = "/carList"+"?option=cctvNow&seq="+seq;
		})
	})
</script>

</html>