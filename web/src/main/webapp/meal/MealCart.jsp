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
    <title>購物車</title>
</head>
<body>
<h1>我是購物車喔</h1>
<%--<%@ include file="page1.jsp" %>--%>
<c:forEach var="cartProd" items="${cartProds}" >
    名稱：${cartProd.meal.mealName} <br>
    分量：${cartProd.quantity}<br>
    數量：${cartProd.amount}<br>

</c:forEach>
<%--<%@ include file="page2.jsp" %>--%>
</body>
</html>
