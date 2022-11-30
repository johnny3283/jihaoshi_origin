<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
  <title>商品不存在</title>
  <style type="text/css">
  #content {
	display: flex; 
	justify-content: center; 
	align-items: center;
	flex-direction: column;
	width: 95%;
    margin: auto;
}
.button {
 	border-radius:1rem; 
 	border: 1px solid #ccc;
}
  </style>
</head>
<body>
<%@ include file="../navbar.file" %>
<div id="CONTENT">
			<br>
			<div>
				<span style="font-size: 20px;">商品不存在或已下架</span>
				<button class="button"><a href="javascript:history.go(-1)" id="backLastPage" style="text-decoration: none;color:#333;">回前頁</a></button>				
			</div>
</div>
</body>
</html>
