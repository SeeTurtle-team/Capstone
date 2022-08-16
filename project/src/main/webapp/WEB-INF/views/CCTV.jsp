<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<div id="choonDiv">
	<img id="cctv_img" src="">
	</div>

</body>

<script>
	const url = "${list}";
	let img = new Array();
	img = url.split("?");
	let i;
	function show(){

		for(let i=1; i<img.length; i++){
			(x => {
			setTimeout(()=> {
				console.log(img[i]);
				document.getElementById("cctv_img").setAttribute("src",img[i]);
			},3000*x)
			})(i)
		}
	
}
	// show()랑 showImg() 중 구현하시려는 거 이용해서 구현하시면 될 듯.
	
	/*var url = "${list}";
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
	}*/
	
	show();
</script>

</html>

