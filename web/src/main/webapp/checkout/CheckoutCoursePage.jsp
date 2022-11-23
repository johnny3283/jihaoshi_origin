<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>導向結帳頁面</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
	<h1 style="text-align: center">請稍後，正將您導向結帳頁面...</h1>
	<div id="checkout">
		${checkoutCoursePage}
	</div>

</body>

</html>
