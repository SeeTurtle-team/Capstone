<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="건호" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/resources/css/Free.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Electrolize&display=swap" rel="stylesheet">
<title>carList</title>
<script
     src="https://kit.fontawesome.com/af4e1eff79.js"
     crossorigin="anonymous"></script>

</head>
<script>
	function logOut(){
		console.log("logOut!!");
		
		alert("로그아웃 되었습니다!");
		location.href = "/"+"?option=logOut";
	}
	function search(){
		var car = document.getElementById("search").value;
		console.log(car);
		
		var sel = document.getElementById("sel").value;
		console.log(sel);
		
		location.href = "/carList"+"?option=search&name="+car+"&sel="+sel;
	}
	
	function image(seq){
		var seq = seq;
		
		location.href = "/carList"+"?option=image&seq="+seq;
	} 
	
	function CCTV(){
		if("${id}" == "manage1234"){
			alert("CCTV 저장 스토리지로 이동합니다 \n 인덱스를 통해 조회할 수 있습니다");
			
			location.href="https://drive.google.com/drive/folders/1-VtPonDD8UEOrdKokqe4Oj2mx930Hgke?usp=sharing";
		}
		else
			alert("관리자 아이디가 필요합니다");
	}
</script>
<body>
      

    
<div class="all">
    <header class="back_color"></header><!----3.23--->
    <footer class="back_color2"></footer><!---3.23-->
        <ul class="bar_menu">
            <li class="bar_logo">
                <i class="fa-solid fa-car-crash"></i>
                <a href="/"><b>HansungProject</b></a>
            </li>
            <li><a href="/carList">CCTV_analysis</a></li>
            <li><a href="/CarModel">Car_model </a></li>
            <li><a href="/free">Free_Board</a></li>
            <li><a href="/QnA">QnA</a></li>
            <li><a href="/developer">Developer</a></li>
    
        </ul>    
     
<br>

 <div class="all_tb">
    <div class="Login_menu" onclick="logOut()"> <a href="/">Logout</a></div> 
    <div class="title">
        <h1>CCTV_analysis</h1>
    </div>
    <div class="search-wrap">
    <select id=sel>
        <option value="model">차종</option>
        <option value="time">시간</option>
        
    </select>  
    <input type="text" class="search-input" id="search" value="" placeholder="Please Enter Text" autocomplete="off">

     <button onclick="search()" class="search-btn">검색</button>
    </div>

    <div class ="board_list_wrap">
        <table class="board_list">
        
            <thead>
                <tr>
                <th>인덱스</th>
                <th>차종</th>
                <th>시간</th>                
                </tr>
         </thead>
         <tbody>
            <c:forEach items="${list}" var="dataVO"><!--   begin="${firstIndex}" end="${lastIndex}" step="1" varStatus="status">-->
                <tr>
                  <td><c:out value="${dataVO.seq}"/></td>
                  <td onclick = "image(${dataVO.seq})"><c:out value="${dataVO.model}"/></td>
                  <td><c:out value="${dataVO.time}"/></td>
                        
                 </tr>
             </c:forEach>
             </tbody>               
        </table>

		<div class="paging">
	<c:if test="${prev}">
		 <span>[ <a href="/ScarList?page=${startPageNum - 1}">이전</a> ]</span>
		</c:if>
		
		<c:forEach begin="${startPageNum}" end="${endPageNum}" var="page">
		 <span>
		 
		  <c:if test="${select != page}">
		   <a href="/ScarList?page=${page}">${page}</a>
		  </c:if>    
		  
		  <c:if test="${select == page}">
		   <a>${page}</a>
		  </c:if>
		    
		 </span>
		</c:forEach>
		
		<c:if test="${next}">
		 <span>[ <a href="/ScarList?page=${endPageNum + 1}">다음</a> ]</span>
		</c:if>
	</div>
        <button class="d-btn"onclick="CCTV()">CCTV 확인</button >

    </div>
    
 </div>    <!-------all_tb---3.23------->

 
</div>



</body>

<script>

</script>



</html>