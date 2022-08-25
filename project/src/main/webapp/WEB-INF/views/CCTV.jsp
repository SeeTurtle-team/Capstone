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
<title>CCTV</title>
<script src="https://kit.fontawesome.com/af4e1eff79.js"
	crossorigin="anonymous"></script>
</head>
<style>
body {
	width: 100vw;
	height: 100vh;
	margin: 0;
}

#choonDiv {
	box-sizing: content-box;
	width: 100%;
	height: 65%;
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
	display: inline-block;
}

.button {
	text-align: center;
}

.left_right {
	display: flex;
	position: relative;
	top: 35vh;
}

i {
	font-size: 2.8vw;
	cursor: pointer;
	text-align: center;
	margin: auto;
}
</style>
<body>
	<div class="all">
		<ul class="bar_menu">
			<li class="bar_logo"><i class="fa-solid fa-car-crash"></i> <a
				href="/">HANSUNG</a> <a href="#" class="bar_toogle"> <i
					class="fa-solid fa-bars"></i>
			</a></li>
			<div class="nav_li">
				<li><a href="/carList">CCTV</a></li>
				<li><a href="/CarModel">Vehicle </a></li>
				<li><a href="/free">Spaces</a></li>
				<li><a href="/QnA">Questions</a></li>
				<li><a href="/developer">Developer</a></li>
			</div>
		</ul>

		<header>
			<div class="header_name">CCTV</div>
			<div class="menu">
				<div class="Login_menu" >
					<a href="/carList">LIST</a>
				</div>
				<div class="Login_menu" onclick="logOut()">
					<a href="/?option=logOut">EXIT</a>
				</div>
			</div>
		</header>
		<div class="BackGroundBox_Psfixed">
			<section>
				<div class="left_right">
					<i class="fa-solid fa-chevron-left" onclick="i=i-2;"></i> 
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<i class="fa-solid fa-chevron-right" onclick="i=i+0.5;"></i>
				</div>
				<div id="choonDiv"></div>
				<br>
				<div class="button">
					<button class="d-btn1" id="back">돌아가기</button>
					<button class="d-btn2" onclick="cctv()">정보보기</button>
					<button class="d-btn3" id="back" onclick="stop()">정지</button>
					

				</div>
				<div>
					<input class="data1" id="speed" type="text" placeholder="사진간의 속도를 조절할 수 있습니다(초)" />
					<button class="d-btn4" onclick="speed()">속도 조절</button>
				</div>

			</section>



		</div>
		<!-------con-->




	</div>
	<!-------all_tb---3.23------->
</body>



<script>
	/*
	 const url = "${list}";
	 let img = new Array();
	 img = url.split("?");
	 function show(){

	 for(let i=0; i<img.length-1; i++){
	 (x => {
	 setTimeout(()=> {
	 if(img[i+1]===undefined){
	 alert("cctv가 끝났습니다");
	 location.href="/carList";
	 }
	 console.log(img[i+1]);
	 document.getElementById("cctv_img").setAttribute("src",img[i+1]);
	 },1000*x)
	 })(i)
	 }
	
	 }
	 */
	$(function() {
		$("#back").click(function() {
			location.href = "/carList";
		})
	})
	// show()랑 showImg() 중 구현하시려는 거 이용해서 구현하시면 될 듯.

	var url = "${list}";
	var img = new Array();
	img = url.split("?");
	var i = -1;
	var flag = true;
	var sec = 1000;
	function showImg() {
		i++;
		if (img[parseInt(i)] === undefined) {  //시연시 null로 수정할 것
			console.log(i);
			alert("cctv가 끝났습니다");
			location.href = "/carList";
		} else {
			if (flag === false) {
				return;
			}
			console.log(i);
			i = parseInt(i);
			document.all.choonDiv.innerHTML = "<img id='cctv_img' src="+img[i]+">";

			setTimeout("showImg()", sec);
		}
	}

	function stop() {
		if (flag) {
			flag = false;
		} else {
			flag = true;
			showImg();
		}
	}

	function speed() {
		var speed = document.getElementById("speed").value;
		sec = speed * 1000;
	}

	function cctv() {
		var imgUrl = img[i];
		console.log(imgUrl);
		var url = imgUrl.replace(/%/gi, '@');
		//url = url.replace(/+/gi,'(');
		console.log(url);
		location.href = "/carList?option=img&sel=sel&url=" + url;
	}

	showImg();
</script>

</html>

