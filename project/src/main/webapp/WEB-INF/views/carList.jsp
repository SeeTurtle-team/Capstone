<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="건호" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/css/Free.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Electrolize&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<!--swiper 가져옴 -->
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<!--제이쿼리 가져옴-->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<title>시시 티비</title>
<script src="https://kit.fontawesome.com/af4e1eff79.js"
	crossorigin="anonymous"></script>

</head>
<script>
	function logOut() {
		console.log("logOut!!");

		alert("로그아웃 되었습니다!");
		location.href = "/" + "?option=logOut";
	}
	function search() {
		var car = document.getElementById("search").value;
		console.log(car);

		var sel = document.getElementById("sel").value;
		console.log(sel);

		location.href = "/carList" + "?option=search&name=" + car + "&sel="
				+ sel;
	}

	function img(seq) {
		console.log(seq);
		var seq = seq;
		location.href = "/carList" + "?option=img&seq=" + seq;
	}
</script>
<body>



	<div class="all">
		<ul class="bar_menu">
			<li class="bar_logo"><i class="fa-solid fa-car-crash"></i> <a
				href="/">Han Sung</a></li>
			<div class="li">
				<li><a href="/carList">CCTV_analysis</a></li>
				<li><a href="/CarModel">Car_model </a></li>
				<li><a href="/free">Free_Board</a></li>
				<li><a href="/QnA">QnA</a></li>
				<li><a href="/developer">Developer</a></li>
			</div>
		</ul>

		<header>
			<div class="header_name">시시티비</div>
			<div class="menu">
				<div class="Login_menu" onclick="logOut()">
					<a href="/">로그인</a>
				</div>
				<div class="d-btn" onclick="CCTV()">
					<a href="/">슬라이드 버튼</a>
				</div>
			</div>
		</header>


		<div class="all_tb">
			<div class="search-wrap">
				<select id=sel>
					<option value="model">차종</option>
					<option value="time">시간</option>

				</select> <input type="text" class="search-input" id="search" value=""
					placeholder="Please Enter Text" autocomplete="off">

				<button class="search-btn" type="button" onclick="search()">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</div>

			<div class="board_list_wrap">
				<table class="board_list">

					<thead>
						<tr>
							<th>인덱스</th>
							<th>차종</th>
							<th>시간</th>
							<th>보기</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" begin="0" end="9" var="dataVO">
							<!--   begin="${firstIndex}" end="${lastIndex}" step="1" varStatus="status">-->
							<tr>
								<td><c:out value="${dataVO.seq}" /></td>
								<td title="클릭하시면 해당 CCTV 캡쳐본을 보실 수 있습니다"
									onclick="img('${dataVO.seq}')"><c:out
										value="${dataVO.model}" /></td>
								<td><c:out value="${dataVO.time}" /></td>
								<td>
									<div class="cctv_img">
										<p id="capture">캡쳐 보기</p>
										<p id="LookForCctv">cctv 보기</p>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>






				<div class="imgList1">
					<div class="imgC">
						<div class="imgPlusp">
							<!-- <c:set var="img" value="${dataVO.carImage}" /> -->
							<img src="https://ifh.cc/g/BBpfCT.png" alt="Image Error"
								style="display: none">
							<p class="kind" style="color: transparent">차량 모델: test car</p>
							<p class="detail" style="color: transparent">지나간 시간:
								2022:08:10 14:59</p>
							<!-- <p class="detail2" style="color:transparent">차 엔진 : test
                                | 적재용량 : test
                                </p> -->
						</div>
					</div>
				</div>


				<div class="modal1">
					<button>&times;</button>
					<div class="modal_content">
						<!--이미지 받아오는 곳-->
						<div class="img_container1">
							<img src="" alt="">
						</div>
						<div class="modal_content1">
							<strong>
								<p></p>
							</strong>
						</div>
						<div class="modal_content2">
							<p></p>
						</div>
					</div>
				</div>


				<div class="modal2">
					<button>&times;</button>
					<div class="modal_content">
						<!--이미지 받아오는 곳-->
						<div class="img_container">
							<!-- Slider main container -->
							<div class="swiper-container">
								<!-- 보여지는 영역 -->
								<div class="swiper-wrapper" id="swiper-wrapper">
									<!-- <div class="swiper-slide">내용</div> 를 추가하면된다 -->
									<div class="swiper-slide slide">
										<img src="">
										<div class="modal_content1">
											<strong>
												<p></p>
											</strong>
										</div>
										<div class="modal_content2">
											<p></p>
										</div>
									</div>
									<div class="swiper-slide slide">
										<img src="">
										<div class="modal_content1">
											<strong>
												<p></p>
											</strong>
										</div>
										<div class="modal_content2">
											<p></p>
										</div>
									</div>
									<div class="swiper-slide slide">
										<img src="">
										<div class="modal_content1">
											<strong>
												<p></p>
											</strong>
										</div>
										<div class="modal_content2">
											<p></p>
										</div>
									</div>
								</div>
								<!-- 페이징 버튼 처리 상황에 따라 추가 삭제가능-->
							</div>
						</div>
						<div class="swiper-navigation">
							<div class="hot_prev">
								<div class="swiper-button-prev"></div>
							</div>
							<div class="hot_next">
								<div class="swiper-button-next"></div>
							</div>
						</div>
					</div>

					<!--slide2-->
				</div>







				<div class="paging">
					<c:if test="${prev}">
						<span>[ <a href="/carList?page=${startPageNum - 1}">이전</a>
							]
						</span>
					</c:if>

					<c:forEach begin="${startPageNum}" end="${endPageNum}" var="page">
						<span> <c:if test="${select != page}">
								<a href="/carList?page=${page}">${page}</a>
							</c:if> <c:if test="${select == page}">
								<a>${page}</a>
							</c:if>

						</span>
					</c:forEach>

					<c:if test="${next}">
						<span>[ <a href="/carList?page=${endPageNum + 1}">다음</a> ]
						</span>
					</c:if>
				</div>
			</div>

		</div>
		<!-------all_tb---3.23------->


	</div>



</body>

<script>

$(function () {
    // 	이미지 클릭시 해당 이미지 모달
    $("#capture").click(function () {
        $(".modal1").show();
        // 해당 이미지 가져오기
        var imgSrc = $('.imgPlusp').children("img").attr("src");
        $(".modal_content img").attr("src", imgSrc);

        // 해당 이미지 텍스트 가져오기
        var imgTit = $('.imgPlusp').children(".kind").text();
        $(".modal_content1 p").text(imgTit);
        var srcText = $('.imgPlusp').children(".detail").text();
        var srcText3 = srcText + "\n";
        $(".modal_content2 p").text(srcText3);
    });
    $(".modal1 button").click(function () {
        $(".modal1").hide();
    });
});


$(function () {
    // 	이미지 클릭시 해당 이미지 모달
    $("#LookForCctv").click(function () {
        $(".modal2").show();
        // 해당 이미지 가져오기
        var imgSrc = $('.imgPlusp').children("img").attr("src");
        $(".modal_content img").attr("src", imgSrc);

        // 해당 이미지 텍스트 가져오기
        var imgTit = $('.imgPlusp').children(".kind").text();
        $(".modal_content1 p").text(imgTit);
        var srcText = $('.imgPlusp').children(".detail").text();
        var srcText3 = srcText + "\n";
        $(".modal_content2 p").text(srcText3);
    });
    $(".modal2 button").click(function () {
        $(".modal2").hide();
    });
});

var swiper = new Swiper('.swiper-container',
    {
        loop: true,
        spaceBetween: 30,
        centeredSlides: true,
        autoplay: {
            delay: 2000,
            disableOnInteraction: false,
        },
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        },
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
    });

$('.swiper-slide').hover(
    function () {
        swiper.autoplay.stop();
    }, function () {
        swiper.autoplay.start();
    }
);
</script>



</html>