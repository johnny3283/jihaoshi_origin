<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> 假的購物車</h3>

<span style="font-size: 16px;">商品總價：100元 </span>
<button type="submit" form="checkout">去結帳</button>
<form method="post" action="testCheckout" id="checkout" enctype="application/x-www-form-urlencoded">
    <input type="text" name="action" value="checkout" hidden>
</form>
</body>
</html>