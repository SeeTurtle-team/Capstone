<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>����� �޴� �Խ���</title>
</head>
<body>
	<div class="contents">
			<div class="header">
	            <h1>menu</h1>
	        </div>
	        
	        <!-- ������ ���� -->
	        <div class="contents-body">
	            <div class="body-board">
	
	                <div class="filter">
	                	 <h2>${id}�� �ȳ��ϼ���</h2>
	                     
	                </div>
	                
	                <table border="1">
	                	<th>����</th>
	                	<tr>
	                		<td><a href="">�ǽð� ���� ��Ʈ����</a></td>
	                	</tr>
	                	<tr>
	                		<td><a href="/list">���� ������ ���</a></td>
	                	</tr>
	                	<tr>
	                		<td><a href="/QnAList">QnA</a></td>
	                	</tr>
	                </table>
	         	</div>
            </div>
    </div>
</body>
</html>