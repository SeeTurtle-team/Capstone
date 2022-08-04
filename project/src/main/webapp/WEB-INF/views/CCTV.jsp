<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>차량 모델 : ${cctv.model}</h3>
	<h3>지나간 시간 : ${cctv.time}</h3>
	<img src="${cctv.imgUrl}">
	
</body>
</html>