<%--
  Created by IntelliJ IDEA.
  User: ryokoon
  Date: 2022/11/23
  Time: 下午 08:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form id="ECPayReturn" action="${ctxPath}/order/orderController" method="post">
    <input type="text" name="action" value="orderInsert" hidden>
    <input type="text" name="TradeNo" value="${param.TradeNo}" hidden>
    <input type="text" name="MerchantTradeNo" value="${param.MerchantTradeNo}" hidden>
    <input type="text" name="TradeDesc" value="${param.TradeDesc}" hidden>
    <input type="text" name="TradeAmt" value="${param.TradeAmt}" hidden>
    <script language="JavaScript">ECPayReturn.submit()</script>
</form>

</body>
</html>
