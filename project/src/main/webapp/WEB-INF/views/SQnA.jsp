<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="건호" content="width=device-width, initial-scale=1.0">
    <title>QnA</title>
    <link rel="stylesheet" href="resources/css//Free.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Electrolize&display=swap" rel="stylesheet">
<script
     src="https://kit.fontawesome.com/af4e1eff79.js"
     crossorigin="anonymous"></script>

</head>

<script>
	function QnAView(seq){
		var seq = seq;
		console.log(seq);
		
		location.href = "/QnA"+"?option=read&seq="+seq;
	}
	
	function enroll(){
		console.log("hoho");
		location.href = "/QnA"+"?option=goToenroll";
	}
	
	function search(){
		var keyWord = document.getElementById("keyword").value;
		var selection = document.getElementById("selection").value;
		
		console.log(selection);
		console.log(keyWord);
		
		location.href = "/QnA"+"?option=search&keyWord="+keyWord+"&select="+selection;
	}
	
	function logOut(){
		console.log("logOut!!");
		
		alert("로그아웃 되었습니다!");
		location.href = "/"+"?option=logOut";
	}
</script>
<body>
      

    
<div class="all">
    <header class="back_color"></header><!----3.23--->
    <footer class="back_color2"></footer><!---3.23-->
        <ul class="bar_menu">
            <li class="bar_logo">
                <i class="fa-solid fa-car-crash"></i>
                <a class="project" href="/"><b>HansungProject</b></a>
            </li>
            <li><a href="/carList">CCTV_analysis</a></li>
            <li><a href="/CarModel">Car_model </a></li>
            <li><a href="/free">Free_Board</a></li>
            <li><a href="/QnA">QnA</a></li>
            <li><a href="/developer">Developer</a></li>
    
        </ul>    
     
<br>

 <div class="all_tb">
    <div class="Login_menu" onclick="logOut()"> <a >Logout</a></div> 
    <div class="title">
        <h1>QnA</h1>
    </div>
    <div class="search-wrap">
    <select id="selection">
        <option value="title">제목</option>
        <option value="userId">작성자</option>
        <option value="multi">제목+작성자</option>
    </select>  
    <input type="text" class="search-input" placeholder="Please Enter Text" id="keyword" value = "" autocomplete="off" >

     <button  class="search-btn" onclick="search()">검색</button>
    </div>

    <div class ="board_list_wrap">
        <table class="board_list">
        
            <thead>
                <tr>
                	<th>번호</th>
	                <th>제목</th>
	                <th>작성자</th>
	                <th>시간</th>                
                </tr>
         </thead>
            <tbody>
				<c:forEach items="${list}" var="dataVO"><!--  begin="${firstIndex}" end="${lastIndex}" step="1" varStatus="status"> -->
                      <tr>
                      	  <td><c:out value="${dataVO.seq}"/></td>
	                      <td onclick="QnAView(${dataVO.seq})"><c:out value="${dataVO.title}"/></td>
	                      <td><c:out value="${dataVO.userId}"/></td>
	                      <td><c:out value="${dataVO.time}"/></td>
	         
                       </tr>
                 </c:forEach>
            </tbody>
        </table>
        <div class="paging">
	<c:if test="${prev}">
		 <span>[ <a href="/SQnA?page=${startPageNum - 1}">이전</a> ]</span>
		</c:if>
		
		<c:forEach begin="${startPageNum}" end="${endPageNum}" var="page">
		 <span>
		 
		  <c:if test="${select != page}">
		   <a href="/SQnA?page=${page}">${page}</a>
		  </c:if>    
		  
		  <c:if test="${select == page}">
		   <a>${page}</a>
		  </c:if>
		    
		 </span>
		</c:forEach>
		
		<c:if test="${next}">
		 <span>[ <a href="/SQnA?page=${endPageNum + 1}">다음</a> ]</span>
		</c:if>
	</div>

    </div>
    
 </div>    <!-------all_tb---3.23------->

 
</div>
</body>

<script>

</script>



</html>