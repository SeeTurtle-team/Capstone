<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="건호" content="width=device-width, initial-scale=1.0">
    <title>QnA</title>
    <link rel="stylesheet" href="resources/css/Free_view.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Electrolize&display=swap" rel="stylesheet">
<script
     src="https://kit.fontawesome.com/af4e1eff79.js"
     crossorigin="anonymous"></script>

</head>

<script>

	var seq = "${list.seq}";
	function enroll(){
		
		var text=document.getElementById("comment-input").value;
		var time= new Date();
		var timeString = time.toLocaleString();
		
		console.log(seq);
		console.log(text);
		console.log(timeString);
		
		alert("등록이 완료되었습니다");
		location.href = "/QnA"+"?option=enroll&seq="+seq+"&text="+text+"&&time="+timeString;
	
	}
	
	function del(num){  //QnA 답글 삭제
		if("${id}"!="manage1234"){
			alert("권한이 없습니다.");
		}
		else{
			var num = num;  //답글 번호
			console.log(num);
			
			var key="delete";
			
			alert("답글이 삭제 되었습니다");
			location.href = "/QnA"+"?option=read&del="+key+"&seq="+seq+"&num="+num;
		}
	}
	
	function delQnA(){ //QnA 글 자체 삭제
		if("${id}"!="${list.userId}"){
			alert("권한이 없습니다");
		}
		else{
			console.log(seq);
			alert("게시글이 삭제되었습니다");
			location.href = "/QnA"+"?option=delQnA&seq="+seq;
		}
		
	}
	
	function modify(){ //QnA 글 수정
		if("${id}"!="${list.userId}"){
			alert("권한이 없습니다");
		}
		else{
			location.href = "/QnA"+"?option=modify&seq="+seq;
		}
		
	}
</script>
<body>
      
        <ul class="bar_menu">
            <li class="bar_logo">
                <i class="fa-solid fa-car-crash"></i>
                <a href="/"><b>HansungProject</b></a>
            </li>
            <li><a href="/carList">CCTV_analysis</a></li>
            <li><a href="/CarModel">Car_model </a></li>
            <li><a href="/QnA">QnA</a></li>
            <li><a href="/free">Free_Board</a></li>
    		<li><a href="/developer">Developer</a></li>
        </ul>    
     


<div class="BackGround"></div>
<div class="Main">
    <div class="title">
        <h2>1</h2>
        <h1>QnA_View</h1>
    </div>
    
    <div class="bar2"><h1 class="write_title2">작성 제목</h1></div>
    <div class="search-input">${list.title}</div>

    <div class="bar3"> <h1 class="write_title2">작성 내용</h1></div>
    <div class="content_table">
		${list.content}
		<div>
			<img src="${imgSrc}">
		</div>
	</div>
	<div class="wab">
	    <div class="WriterAndbtn">
	    <div id="revise" onclick = "modify()" >수정</div>
	    <div id="delete" onclick = "delQnA()">삭제</div>
	    </div>
	</div>
	<div class="Writer">${list.userId}</div>
	
	<c:forEach items="${manage}" var="dataVO">
		<div id="form-commentInfo"> 
		<input id="comment-input" placeholder="댓글을 입력해 주세요."> 
		<button onclick="enroll()">등록</button> </div>
		<div id=comments> </div>
	</c:forEach>


	<!-- QnA 답글 -->
	<c:forEach items="${rlist}" var="dataVO">
		<div class="coment_writer">
		    <div class="inline">
		    관리자</div>
		     <div class="inline">${dataVO.time}</div>
		        
		    <div class="conment_box">
				${dataVO.text}
			</div>
		    <div class="pull-right">
		       
		        <div id="delete" onclick="del('${dataVO.commentNum}')">삭제</div>
		    </div>
		</div>
	
	</c:forEach>






</body>

<script>

</script>



</html>