<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="건호" content="width=device-width, initial-scale=1.0">
    <title>CarModel</title>
    <link rel="stylesheet" href="/resources/css/Free.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Electrolize&display=swap" rel="stylesheet">
<script
     src="https://kit.fontawesome.com/af4e1eff79.js"
     crossorigin="anonymous"></script>
     <script src="https://code.jquery.com/jquery-3.6.0.min.js"
     integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
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
	
	location.href = "/CarModel"+"?option=search&name="+car+"&sel="+sel;
}
console.log("${pageSize}");
console.log("${page}");
console.log("${nowBlock}");
</script>
<body>
      

    
<div class="all">
    <header class="back_color"></header><!----3.23--->
    <footer class="back_color2"></footer><!---3.23-->
        <ul class="bar_menu">
            <li class="bar_logo">
                <i class="fa-solid fa-car-crash"></i>
                <a href="/">HANSUNG</a>
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
        <h1>Car_Model</h1>
    </div>
    <div class="search-wrap">
    <select id="sel">
        <option value="carkind">차종</option>
        <option value="carmaker">제조사</option>
    </select>  
    <input type="text" id="search" class="search-input" placeholder="Please Enter Text" autocomplete="off">

     <button onclick="search()" class="search-btn">검색</button>
    </div>
<!--  -->
    <div class="imgList1">
    	<div class="imgC">
		<c:forEach items="${list}" var="dataVO">
		<div class="imgPlusp">
		<c:set var="img" value="${dataVO.carImage}"/>
		<img class="cropped" src="<c:out value='${img}'/>"/>
		<p class="kind"><c:out value="${dataVO.carKind}"/></p>
		<p class="detail" style="color:transparent">가격 : <c:out value="${dataVO.carPrice}"/> | 제조사 : <c:out value="${dataVO.carMaker}"/> | 연비 : <c:out value="${dataVO.carFuel}"/> </p>
		<p class="detail2" style="color:transparent">차 엔진 : <c:out value="${dataVO.carEngine}"/> | 적재용량 : <c:out value="${dataVO.carRoad}"/></p>
		</div>
		</c:forEach>
		</div>
	</div>


	<!-- 팝업 될 곳 -->
	<div class="modal">
		<button>&times;</button>
		<div class="modalBox">
			<img src="" alt=""><!--이미지 받아오는 곳-->
            <strong><p></p></strong>
            <div class="modal_content">
                <pre class="info" id="set1">
                	
                </pre>
                
            </div>
            
		</div>
	</div>



    <div class ="board_list_wrap">
		
		<div class="paging">
	<c:if test="${prev}">
		 <span>[ <a href="/SCarModel?page=${startPageNum - 1}">이전</a> ]</span>
		</c:if>
		
		<c:forEach begin="${startPageNum}" end="${endPageNum}" var="page">
		 <span>
		 
		  <c:if test="${select != page}">
		   <a href="/SCarModel?page=${page}">${page}</a>
		  </c:if>    
		  
		  <c:if test="${select == page}">
		   <a>${page}</a>
		  </c:if>
		    
		 </span>
		</c:forEach>
		
		<c:if test="${next}">
		 <span>[ <a href="/SCarModel?page=${endPageNum + 1}">다음</a> ]</span>
		</c:if>
	</div>
		
        <div class="modal_admin">
            <button class="x">&times;</button>
            <div class="modalBox_admin"> 
                <form class="modal_admin_input">
 

                    <div class="modal_input_area">
                        <label for="modal_input">차 이름</label>
                        <input type="text" name="modal_input" id="modal_input"
                        autocomplete="off" required>
                       
                    </div>

                    <div class="modal_input_area">
                        <label for="modal_input">차 가격</label>
                        <input type="text" name="modal_input" id="modal_input"
                        autocomplete="off" required>
                        
                    </div>

                    <div class="modal_input_area">
                        <label for="modal_input">차 연비</label>
                        <input type="text" name="modal_input" id="modal_input"
                        autocomplete="off" required>
                        
                    </div>

                    <div class="modal_input_area">
                        <label for="modal_input">차 수용량</label>
                        <input type="text" name="modal_input" id="modal_input"
                        autocomplete="off" required>
                    </div>

                    <div class="modal_input_upload">
                    <input type="file" class="mdoal_input_upload" accept="image/*" required multiple>
                    </div>
                    
                    <select>
                        <option>경형</option>
                        <option>소형</option>
                        <option>준중형</option>
                        <option>중형</option>
                        <option>중대형</option>
                        <option>대형</option>
                        <option>스포츠카</option>
                    </select> 

                </form>
            </div>
            <button class="f-btn">등록</button ><!--여기가 업로드 부분-->
        </div>

       <!-- <button class="d-btn">등록</button > 이 버튼은 밖에서 보이기 위함 장식 및 페이드 인 하기 위함-->

    </div>
    
 </div>    <!-------all_tb---3.23------->

 
</div>



</body>

<script>
$(function(){
    // 	이미지 클릭시 해당 이미지 모달
        $(".imgPlusp").click(function(){
            $(".modal").show();
            // 해당 이미지 가져오기
            var imgSrc = $(this).children("img").attr("src");
            var imgAlt = $(this).children("img").attr("alt");
            $(".modalBox img").attr("src", imgSrc);
            $(".modalBox img").attr("alt", imgAlt);
            
            // 해당 이미지 텍스트 가져오기
            var imgTit =  $(this).children(".kind").text();
            $(".modalBox p").text(imgTit);
            var srcText = $(this).children(".detail").text();
            
            var srcText2 = $(this).children(".detail2").text();
            
            var srcText3 = srcText + "\n" + srcText2;
            $(".modalBox pre").text(srcText3);
       // 해당 이미지에 alt값을 가져와 제목으로
            //$(".modalBox p").text(imgAlt);
        });
        
        //.modal안에 button을 클릭하면 .modal닫기
        $(".modal button").click(function(){
            $(".modal").hide();
        });
        
        //.modal밖에 클릭시 닫힘
        $(".modal").click(function (e) {
        if (e.target.className != "modal") {
          return false;
        } else {
          $(".modal").hide();
        }
      });
    });</script>

<script>$(function(){ 

    $(".d-btn").click(function(){
      $(".modal_admin").fadeIn();

    });
    
    /*$(".modalBox_admin").click(function(){
      $(".modal_admin").fadeOut();
    }); */// 박스 클릭시 페이드 아웃
    
    $(".modal_admin button").click(function(){
            $(".modal_admin").fadeOut();
        });

    $(".modal_admin button.f-btn").click(function(){
            $(".modal_admin").fadeOut();
        });
  });</script>

</html>