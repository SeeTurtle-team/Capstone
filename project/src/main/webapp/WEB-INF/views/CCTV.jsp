<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	
	
	var url = "${list}";
	console.log(url);
	
	var img = new Array();
	img = url.split("?");
	console.log(img[2]);
	var i = 0;
	var s = "<img src="+img[0]+">";
	console.log(s);
	function showImg(){
		console.log(img[i]);
		if(img[i]===undefined){
			alert("cctv가 끝났습니다");
			location.href="/carList";
		}
		else{
			document.all.choonDiv.innerHTML = "<img src="+img[i]+">";
		    i++;
		    
			setTimeout("showImg()",1000);
		}
		
	}
	
	
</script>
<body>
	<h3>차량 모델 : ${cctv.model}</h3>
	<h3>지나간 시간 : ${cctv.time}</h3>
	<div id="choonDiv"></div>
	<img src="${cctv.imgUrl}">
	<script>showImg();</script>
</body>
</html>