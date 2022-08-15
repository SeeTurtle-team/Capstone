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
	var img = new Array();
	img = url.split("?");
	var i = 0;
	function showImg(){		
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
	<div id="choonDiv"></div>
	<img src="${cctv.imgUrl}">
	<script>showImg();</script>
</body>
</html>