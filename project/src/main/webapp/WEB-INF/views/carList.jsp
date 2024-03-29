<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/Free.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Electrolize&display=swap" rel="stylesheet">

<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<!--swiper 가져옴 -->
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<!--제이쿼리 가져옴-->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>CarList</title>
<script src="https://kit.fontawesome.com/af4e1eff79.js" crossorigin="anonymous"></script>

</head>
<script>
	function logOut() {
		console.log("logOut!!");

		alert("로그아웃 되었습니다!");
		location.href = "/" + "?option=logOut";
	}
	function search(){
		var text  = document.getElementById("search").value;
		var selection = document.getElementById("sel").value;

		location.href = "/carList"+"?option=search&key="+text+"&select="+selection;
	}

	function img(seq) {
		console.log(seq);
		var seq = seq;
		location.href = "/carList" + "?option=img&seq=" + seq;
	}
	
	function cctvNow(seq){
		console.log(seq);
		var seq = seq;
		location.href = "/carList"+"?option=cctvNow&seq="+seq;
	}
	
	function CCTV(){
		location.href = "/carList"+"?option=cctv";
	}
	
	function timeBack(){
		var flag = ${flag};
		if(${flag=='0'}){
			flag = 1;
		}
		else{
			flag = 0;
		}
		var option= '${option}';
		var key = '${key}';
		var sel = '${sel}';
		var num = '${num}';
		
		if(option==''){
			location.href = "/carList?timeFlag="+flag
		}
		else{
			location.href = "/carList?timeFlag="+flag+"&option="+option+"&key="+key+"&select="+sel;
		}
		//num=${num}&option=${option}&key=${key}&select=${sel}&timeFlag=${flag}
		
		
	}
</script>
<body>



	<div class="all">
		<ul class="bar_menu">
			<li class="bar_logo"><i class="fa-solid fa-car-crash"></i> <a
				href="/">HANSUNG</a>
				                <a href="#" class="bar_toogle">
                    <i class="fa-solid fa-bars"></i>
                </a>   
				</li>
			<div class="nav_li">
				<li title="CCTV 페이지" onclick="location.href='/carList'"><a href="/carList">CCTV</a></li>
				<li title= "차량 정보 페이지" onclick="location.href='/CarModel'"><a href="/CarModel">Vehicle </a></li>
				<li title = "자유게시판" onclick="location.href='/free'"><a href="/free">Spaces</a></li>
				<li title = "QnA 게시판"onclick="location.href='/QnA'"><a href="/QnA">Questions</a></li>
				<li title = "개발한 이들" onclick="location.href='/developer'"><a href="/developer">Developer</a></li>
			</div>
		</ul>

		<header>
			<div class="header_name">CCTV</div>
			<div class="menu">
				
				<div class="Login_menu" onclick="CCTV()">
					<a href="/carList?option=cctv">DL CCTV</a>
				</div>
				<div class="Login_menu" onclick="logOut()">
					<a href="/?option=logOut">EXIT</a>
				</div>
			</div>
		</header>


		<div class="all_tb">
			<div class="search-wrap">
				<select id=sel>
					<option value="model">차종</option>
					<option value="time">시간</option>
					
				</select> 
				<input type="text" class="search-input" id="search" value=""
					placeholder="Please Enter Text" autocomplete="off">

				<button class="search-btn" type="button" onclick="search()">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</div>
			
			<div class="board_list_wrap">
				<table class="board_list">

					<thead>
						<tr>
							<th>번&nbsp;호</th>
							<th title="해당 시간에 지나간 차종입니다">차&nbsp;종</th>
							<th title="클릭하시면 시간 순서를 바꾸실 수 있습니다" onclick="timeBack()">시&nbsp;간↑↓ </th>
							<th>보&nbsp;기</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="dataVO">
							<!--   begin="${firstIndex}" end="${lastIndex}" step="1" varStatus="status">-->
							<tr>
								<td><c:out value="${dataVO.seq}" /></td>
								<td title="클릭하시면 해당 CCTV 캡쳐본을 보실 수 있습니다"
									onclick="img('${dataVO.seq}')"><c:out
										value="${dataVO.model}" /></td>
								<td><c:out value="${dataVO.time}" /></td>
								<td>
									<div class="cctv_img">
										<p onclick = "img('${dataVO.seq}')" id="capture">캡쳐</p>
										<p title="해당 시점부터 CCTV를 보실 수 있습니다" onclick = "cctvNow('${dataVO.seq}')" id="LookForCctv">CCTV</p>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>


			




				<div class="paging">
					<c:if test="${prev}">
						<span>[ <a
							href="/carList?num=${startPageNum - 1}&option=${option}$key=${key}&timeFlag=${flag}">이전</a>
							]
						</span>
					</c:if>

					<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
						<span> <c:if test="${select != num}">
								<a
									href="/carList?num=${num}&option=${option}&key=${key}&select=${sel}&timeFlag=${flag}">${num}</a>
							</c:if> <c:if test="${select == num}">
								<a>${num}</a>
							</c:if>

						</span>
					</c:forEach>

					<c:if test="${next}">
						<span>[ <a
							href="/carList?num=${endPageNum + 1}&option=${option}&key=${key}&timeFlag=${flag}">다음</a>
							]
						</span>
					</c:if>
				</div>
			</div>

		</div>
		<!-------all_tb---3.23------->


	</div>



</body>
<script>
        const bar_toogle=document.querySelector('.bar_toogle');
    const menu =document.querySelector('.nav_li');
    const header =document.querySelector('header');

    bar_toogle.addEventListener('click', () =>{
        menu.classList.toggle('active');
        header.classList.toggle('active');
    });
</script>

</html>