<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cart.model.CartProdVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CartProdVO> cartProds = (List<CartProdVO>) session.getAttribute("cartProds");
    request.setAttribute("cartProds",cartProds);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>我是購物車喔</h1>
<c:forEach var="cartProd" items="${cartProds}" >
    編號：</br>${cartProd.meal.mealNo} <br>
    名稱：${cartProd.meal.mealName} <br>
    分量：${cartProd.quantity}<br>
    數量：${cartProd.amount}<br>

</c:forEach>
</body>
</html>
