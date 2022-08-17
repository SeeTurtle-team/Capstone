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
    <title>CCTV</title>
    <script src="https://kit.fontawesome.com/af4e1eff79.js" crossorigin="anonymous"></script>
</head>
<style>
body{
    width: 100vw;
    height: 100vh;
    margin:0;
}
#choonDiv{
    box-sizing: content-box;
    width: 100%;
    height:65%;
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
display:inline-block;
}
.button{
text-align:center;
}
.left_right{
display:flex;
position:relative;
top:35vh;

}
i{
font-size:2.8vw;
cursor:pointer;
text-align:center;
margin:auto;
}

</style>
<body>
	<div class="left_right">
		<i class="fa-solid fa-chevron-left" onclick="i=i-2;"></i>
		<i class="fa-solid fa-chevron-right" onclick="i=i+0.5;"></i>
	</div>
	<div id="choonDiv">
	
	</div>
	<h2 class="abc">차량 모델 : ${cctv.model}</h2>
	<h3 class="abcd">지나간 시간 : ${cctv.time}</h3>
	
	<div class="button">
		<button id="back">돌아가기</button>
		<button id="back" onclick="stop()">정지</button>
	</div>
</body>

<script>

</script>

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
	$(function (){
		 $("#back").click(function(){
			 location.href="/carList";
		 })
	})
	// show()랑 showImg() 중 구현하시려는 거 이용해서 구현하시면 될 듯.
	
	var url = "${list}";
	var img = new Array();
	img = url.split("?");
	var i = 1;
	var flag = true;
	function showImg(){		
		if(img[parseInt(i)]===undefined){
			console.log(i);
			alert("cctv가 끝났습니다");
			location.href="/carList";
		}
		else{
			if(flag===false){
				return;
			}
			console.log(i);
			document.all.choonDiv.innerHTML = "<img id='cctv_img' src="+img[parseInt(i)]+">";
		    i=parseInt(i);
			i++;
			setTimeout("showImg()",1000);
		}
	}
	
	function stop(){
		if(flag){
			flag=false;
		}
		else{
			flag=true;
			showImg();
		}
	}
	
	
	showImg();
</script>

</html>

