<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Spaces</title>
<link rel="stylesheet" href="resources/css/Free.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Electrolize&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/af4e1eff79.js"
	crossorigin="anonymous"></script>

</head>

<script>
	history.replaceState({}, null, location.pathname);
	window.onkeydown = function() {
		var kcode = event.keyCode;
		if(kcode == 116) {
			history.replaceState({}, null, location.pathname);
		}
	}
	function view(seq){
		var seq = seq;
		
		location.href = "/free"+"?option=view&seq="+seq;
	}
	
	function search(){
		var text  = document.getElementById("search").value;  //검색어
		var selection = document.getElementById("sel").value; //카테고리
		
		console.log(selection);
		console.log(text);
		
		location.href = "/free"+"?option=search&key="+text+"&select="+selection;
	}
	
	function gotoEnroll(){
		location.href = "/free"+"?option=gotoEnroll";
	}
	
	function logOut(){
		console.log("logOut!!");
		
		alert("로그아웃 되었습니다!");
		location.href = "/"+"?option=logOut";
	}
</script>
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
			<div class="header_name">Spaces</div>
			<div class="menu">
				<div class="Login_menu" onclick="logOut()">
					<a href="/?option=logOut">EXIT</a>
				</div>
			</div>
		</header>

		<div class="all_tb">
			<div class="search-wrap">
				<select id="sel">
					<option value="title">제목</option>
					<option value="userId">작성자</option>
					<option value="multi">제목+작성자</option>
				</select> <input type="text" class="search-input"
					placeholder="Please Enter Text" id="search" value=""
					autocomplete="off" />

				<button class="search-btn" type="button" onclick="search()">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</div>

			<div class="board_list_wrap">
				<table class="board_list">

					<thead>
						<tr>
							<th>번&nbsp;호</th>
							<th>제&nbsp;목</th>
							<th>글&nbsp;쓴&nbsp;이</th>
							<th>시&nbsp;간</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="dataVO">
							<!--  begin="${firstIndex}" end="${lastIndex}" step="1" varStatus="status"> -->
							<tr>
								<td><c:out value="${dataVO.seq }" /></td>
								<td onclick="view(${dataVO.seq})"><c:out
										value="${dataVO.title}" /></td>
								<td><c:out value="${dataVO.userId}" /></td>
								
								<td>
									<div class="free_tb">
										<p><c:out value="${dataVO.time}" /></p>
									</div>
								</td>

							</tr>

						</c:forEach>

					</tbody>
				</table>
				<div class="paging">
					<c:if test="${prev}">
						<span>[ <a
							href="/free?num=${startPageNum - 1}&option=${option}$key=${key}">이전</a>
							]
						</span>
					</c:if>

					<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
						<span> <c:if test="${select != num}">
								<a
									href="/free?num=${num}&option=${option}&key=${key}&select=${sel}">${num}</a>
							</c:if> <c:if test="${select == num}">
								<a>${num}</a>
							</c:if>

						</span>
					</c:forEach>

					<c:if test="${next}">
						<span>[ <a
							href="/free?num=${endPageNum + 1}&option=${option}&key=${key}">다음</a>
							]
						</span>
					</c:if>
				</div>




				<button class="f-btn" onclick="gotoEnroll()">등록</button>

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