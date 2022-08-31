<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="건호" content="width=device-width, initial-scale=1.0">
<title>QnA_Write</title>
<link rel="stylesheet" href="resources/css/Free_Write.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Electrolize&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/af4e1eff79.js"
	crossorigin="anonymous"></script>

</head>
<script>
	function modify() { //QnA 글 수정
		var seq = "${list.seq}";
		var title = document.getElementById("title").value;
		var content = document.getElementById("content").value;
		var writer = "${id}";
		var time = new Date();
		var timeString = time.toLocaleString();
		content = content.replaceAll(/(\n|\r\n)/g, "<br>");
		console.log(seq);
		if (title == "") {
			alert("제목을 입력하세요");
			return false;
		}
		if (content == "") {
			alert("내용을 입력하세요");
			return false;
		}

		alert("수정이 완료되었습니다!");
		location.href = "/QnA" + "?option=modifyQnA&title=" + title
				+ "&content=" + content + "&writer=" + writer + "&time="
				+ timeString + "&seq=" + seq;
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
				<li onclick="location.href='/carList'"><a href="/carList">CCTV</a></li>
				<li onclick="location.href='/CarModel'"><a href="/CarModel">Vehicle </a></li>
				<li onclick="location.href='/free'"><a href="/free">Spaces</a></li>
				<li onclick="location.href='/QnA'"><a href="/QnA">Questions</a></li>
				<li onclick="location.href='/developer'"><a href="/developer">Developer</a></li>
			</div>
		</ul>

		<header>
			<div class="header_name">QnA</div>
			<div class="menu">
				<div class="Login_menu" onclick="logOut()">
					<a href="/?option=logOut">EXIT</a>
				</div>
			</div>
		</header>
		<div class="BackGroundBox_Psfixed">
			<section>
				<form action="/QnAEnroll?option=modify&seq=${list.seq}" enctype="multipart/form-data"
					method="post">
					<div class="bar2">
						<h1 class="write_title2">작성 제목*</h1>
					</div>
					<input name=title type="search-input" class="search-input"
						id="title" value="${list.title}">

					<div class="bar3">
						<h1 class="write_title2">작성 내용*</h1>
					</div>
					<table class="content_table">
						<tr>
							<td><textarea name="content" class="content" id="content">${list.content}</textarea>
							</td>
						</tr>
					</table>


					<input class="data" type='file' name='imgFile' multiple />


					<div class="hr">${id}</div>
					<button class="d-btn" type="submit">등록</button>
				</form>



			</section>



		</div>
		<!-------con-->




	</div>
	<!-------all_tb---3.23------->


</body>

<script>
	
</script>



</html>