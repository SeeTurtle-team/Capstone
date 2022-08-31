<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
	<title>HASNUNG</title>
	<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
	<link rel="stylesheet"
		href="https://unpkg.com/swiper/swiper-bundle.min.css" />
	<link rel="stylesheet" href="resources/css/index.css" />
	<link rel="preconnect" href="https://fonts.googleapis.com" />
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link
		href="https://fonts.googleapis.com/css2?family=Electrolize&display=swap"
		rel="stylesheet">
	<link rel="stylesheet" href="resources/css/jquery.fullPage.css">
	<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
	<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>
	<script src="resources/js/jquery.fullPage.js"></script>
	<script src="resources/js/main.js"></script>
	<script src="https://kit.fontawesome.com/af4e1eff79.js"
		crossorigin="anonymous"></script>

</head>
<script>
	function login(){
		if('${id}'==null){
			alert("이미 로그인 되어있습니다");
			
		}
		else{
			location.href="/login";	
		}
			
			
	}
	
	function logOut(){
		location.href="/"+"?option=logOut";	
	}
</script>
<body>
	<div id="fullpage">
		<section class="section">
			<div class="wrap">
				<header id="index_header" class="header">
					<nav class="prontbar" id="index_prontbar">
						<div class="bar_logo">
							<i class="fa-solid fa-car-crash"></i> <a href="">HANSUNG</a>
						</div>

						<ul class="bar_menu">
							<li title="딥러닝을 통한 CCTV 분석 페이지"><a href="/carList">CCTV</a></li>
							<li title="차에 대한 다양한 정보 페이지"><a href="/CarModel">Vehicle
							</a></li>
							<li title="자유롭게 나누는 자유게시판"><a href="/free">Spaces</a></li>
							<li title="질문이 있다면 QnA 게시판"><a href="/QnA">Questions</a></li>
							<li title="만든 이들"><a href="/developer">Developer</a></li>
						</ul>

						<div class="Login_menu">
							<p onclick="login()">${login}</p>
							&nbsp;&nbsp;
							<p onclick="logOut()">${logOut}</p>
						</div>


						<ul class="bar_icons">
							<li><a href="https://twitter.com/"><i
									class="fa-brands fa-twitter"></i></a></li>
							<li><a href="https://www.facebook.com/"><i
									class="fa-brands fa-facebook"></i></a></li>
						</ul>

						<a href="#" class="bar_toogle"> <i class="fa-solid fa-bars"></i>
						</a>
					</nav>
				</header>

				<div class="shell">
					<img src="https://ifh.cc/g/rp1twW.png" id="img-1">
					<!--  <img src="https://ifh.cc/g/AD5DHx.png" id="img-2">
					<img src="https://ifh.cc/g/H5kNHW.png" id="img-3">-->
				</div>

			
				<div class="main">
					<!-- Slider main container -->
					 
					<div class="swiper-container">
						<!-- 보여지는 영역 -->
						<div class="swiper-wrapper" id="swiper-wrapper">
							<!-- <div class="swiper-slide">내용</div> 를 추가하면된다 -->
							<!-- <div class="swiper-slide slide1">
								<img class="pr1" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSegMALLpCwxUtToQei9LlkeMVcGTiG5w_bmvz4YSZlRwRIukYP8BfVBaFS8uR5Xx1ByQ&usqp=CAU">
							</div>
							<div class="swiper-slide slide2">
								<img class="pr1" src="https://mblogthumb-phinf.pstatic.net/20160527_121/cheol1980_1464340993972IQY1G_JPEG/KakaoTalk_20150502_112519902.jpg?type=w800">
							</div>-->
							<div class="swiper-slide slide3">
								<img class="pr1" src="https://mblogthumb-phinf.pstatic.net/MjAxNzA2MDVfMjM0/MDAxNDk2NjUyNTc4NDQ5.m40Br9yVXZRcOfYtII5mTYx_v9mBMJx-qEdMzvW0kgwg.VCUOARbimFpRoyv_-uu0v_sCL7TP9YIQG_bVQLcJAXog.JPEG.citymedia1/2017-06-05_17-31-31.jpg?type=w800">
							</div>
						</div>
						

					</div>
				</div>
 				

			</div>
		</section>

		<section class="section">
			<div class="section section_fullpage" onclick="location.href='/carList'">
				<img class="pr2"
					src="https://images.pexels.com/photos/3205735/pexels-photo-3205735.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"><img
					class="pr3"
					src="http://www.netchus.com/resources/img/layout/main/banner-3-bg.jpg">
				<div class="banner-title" >
					<hr>
					<p class="title">CCTV_analysis</p>
					<p class="summary">딥러닝을 이용한 CCTV 분석</p>
					<a class="arr" href="/carList">내용보기<img
						src="http://www.netchus.com/resources/img/layout/main/arr.png"></a>
				</div>
			</div>
		</section>

		<section class="section">
			<div class="section section_fullpage" onclick="location.href='/CarModel'">
				<img class="pr4"
					src="https://images.pexels.com/photos/3770875/pexels-photo-3770875.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260">
				<div class="banner-title_two" >
					<hr>
					<p class="title">Car_Model</p>
					<p class="summary">다양한 차량에 대한 정보는 ?</p>
					<a class="arr" href="/CarModel">내용보기<img
						src="http://www.netchus.com/resources/img/layout/main/arr.png"></a>
				</div>
			</div>
		</section>

		<section class="section">
			<div class="section section_fullpage" onclick="location.href='/QnA'">
				<img class="pr4"
					src="https://images.pexels.com/photos/10981242/pexels-photo-10981242.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940">
				<div class="banner-title_two" >
					<hr>
					<p class="title">QnA</p>
					<p class="summary">문의 사항이 있다면</p>

					<a class="arr" href="/QnA">내용보기
					<img src="http://www.netchus.com/resources/img/layout/main/arr.png"></a>
				</div>
			</div>
		</section>

		<section class="section">
			<div class="section section_fullpage" onclick="location.href='/free'">
				<img class="pr4"
					src="https://images.pexels.com/photos/6210598/pexels-photo-6210598.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940">
				<div class="banner-title_two" >
					<hr>
					<p class="title">Free_Board</p>
					<p class="summary">다양한 소통, 다채로운 이야기</p>
					<a class="arr" href="/free">내용보기<img
						src="http://www.netchus.com/resources/img/layout/main/arr.png"></a>
				</div>
			</div>
		</section>

		<section class="section">
			<div class="section section_fullpage" onclick="location.href='/developer'">
				<img class="pr5"
					src="http://www.netchus.com/resources/img/layout/main/banner-4-bg.jpg">
				<div class="banner-title_two">
					<hr>
					<p class="title">Developer</p>
					<p class="summary">만든 이들</p>
					<a class="arr" href="/developer">내용보기<img
						src="http://www.netchus.com/resources/img/layout/main/arr.png"></a>
				</div>
				<footer class="index_footer">
					<div class="name_box">
						<p>
							<b>김영우 &nbsp 김민수 &nbsp 라건호</b>
						<p>
						<p>
							<b>조병규 &nbsp 조윤태</b>
						</p>
					</div>
					<div class="name_project">
						<p>Hansung_Project</p>
					</div>
					<div class="name_number">
						<p>대표번호</p>
						<p>010-9230-9536</p>
					</div>
				</footer>
			</div>
		</section>

	</div>


	<div class="top">
		<!--5.10-->
		<a href="" onClick="javascript:window.scrollTo(0,0)">
			<p>TOP</p> <img
			src="http://www.netchus.com/resources/img/layout/top.png">
		</a>
	</div>



	<script>
    const swiper = new Swiper('.swiper-container',
    {
        direction: 'horizontal',
        slidesPerView:3,
        spaceBetween: 30,
        effect: 'fade',
        centeredSlides:true,
        autoplay:{
            delay:4000,
            disableOnlnteraction: false,
        },
    }
    
    );


    $( "#fullpage" ).mousemove(function() {
			x = event.pageX;
			y = event.pageY; 
			$("section .shell img#img-1").css("margin-top",(x)*.007).css("margin-left",-482+((y)*.007)).css("opacity",1-((x-y)*.0002));
			$("section .shell img#img-2").css("margin-top",(y)*.022).css("margin-left",-490+((x)*.025)).css("opacity",1-((x-y)*.0008));
			$("section .shell img#img-3").css("margin-top",-(y)*.028).css("margin-left",-475-((x)*.02)).css("opacity",1-((x-y)*.0007));
		
		})
		
	$(function(){
        $("banner-title_two")
	});

</script>
	-->

	<script>

const bar_toogle=document.querySelector('.bar_toogle');
const menu =document.querySelector('.bar_menu');
const header =document.querySelector('.Login_menu');

bar_toogle.addEventListener('click', () =>{
    menu.classList.toggle('active');
    header.classList.toggle('active');
});

</script>




</body>





</html>