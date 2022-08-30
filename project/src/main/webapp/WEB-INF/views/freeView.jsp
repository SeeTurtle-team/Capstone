<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Space_view</title>
<link rel="stylesheet" href="resources/css/Free_view.css">
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
	
	var freeBoardSeq = ${list.seq};
	
	function comment(){
		var comment = document.getElementById("comment-input").value;
		
		var time= new Date();
		var timeString = time.toLocaleString();
		console.log(comment);
		console.log(freeBoardSeq);
		
		if(comment ==""){
			alert("댓글을 입력하세요");
			return;
		}
		if(comment.length>50){
			alert("댓글이 너무 깁니다");
			return;
		}
		
		location.href = "/free"+"?option=comment&comment="+comment+"&seq="+freeBoardSeq+"&time="+timeString;
	}
	
	function commentDelete(commentNum, id){
		var num = commentNum;
		console.log(num);
		var freeNum = ${list.seq}
		console.log(freeNum);
		var userId = id;
		console.log(userId);
		
		if(userId!="${id}"){
			console.log("여?")
			console.log(num);
			alert("권한이 없습니다");
		}
		else{
			alert("삭제되었습니다!")
			location.href="/free"+"?option=commentDel&commentNum="+num+"&freeNum="+freeNum;
		}
		
	}
	
	function delFree(seq){
		var seq = seq;
		console.log(seq);
				
		if("${list.userId}"!="${id}"){
			console.log("여기까지 옴?")
			alert("권한이 없습니다");
		}
		else{
			alert("삭제되었습니다!")
			location.href="/free"+"?option=delFree&seq="+seq;
		}
	}
	
	function modify(){
		var seq = "${list.seq}"
		
		if("${list.userId}"=="${id}"){
			location.href="/free"+"?option=modify&seq="+seq;
		}
		else{
			alert("권한이 없습니다");
		}
		
		
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
			<div class="header_name">Spaces View</div>
		</header>


		<div class="Main">


			<div class="bar2">
				<h1 class="write_title2">작성 제목</h1>
			</div>
			<div class="search-input">${list.title}</div>

			<div class="bar3">
				<h1 class="write_title2">작성 내용</h1>
			</div>
			<div class="content_table">
				${list.content}
				<div>
					<img src="${imgSrc}">
				</div>
			</div>
			<br>
			<div class="wab">
				<div class="WriterAndbtn">
					<div id="revise" onclick="modify()">수정</div>
					<div id="delete" onclick="delFree('${list.seq}')">삭제</div>
				</div>
			</div>
			<div class="Writer">${list.userId}</div>
			<br><br>
			
			<div id="form-commentInfo">
				<div id="comment-count">
					댓글 <span id="count">${size}</span>
				</div>
				<input id="comment-input" placeholder="댓글을 입력해 주세요.">
				<button id="submit" onclick="comment()">등록</button>
				<div id=comments></div>


				<c:forEach items="${rlist}" var="dataVO">
					<div class="coment_writer">
						<div class="inline">
							<c:out value="${dataVO.userId}" />
						</div>
						<div class="inline">
							<c:out value="${dataVO.time}" />
						</div>

						<div class="conment_box">
							<c:out value="${dataVO.text}" />
						</div>
						<div class="pull-right">

							<div id="delete"
								onclick="commentDelete(${dataVO.commentNum} ,'${dataVO.userId}')">삭제</div>
						</div>
					</div>
				</c:forEach>




				<div class="paging">
					<a href="#" class="num ">1</a> <a href="#" class="num ">2</a> <a
						href="#" class="num ">3</a> <a href="#" class="num"> &gt; </a> <a
						href="#" class="bt">마지막</a>
				</div>

			</div>
			<!---------form-commentInfo-->
		</div>
		<!-------Main-->

	</div>
	<!--all -->
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