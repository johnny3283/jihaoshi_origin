<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link type="text/css" href="${ctxPath}/css/jihaoshi.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        .order {
            border-collapse: collapse;
            text-align: center;
            font-size: 14px;
            line-height:25px;
        }

       .order{
            border: 1px solid black;
        }
    </style>
</head>
<body>
<img src="../images/JihaoshiPageHead.jpg" id="pageHead">
<div class="block_N" style="margin:0px auto;">
    <!--搜尋欄開始-->
    <div class="Nm" style="display: flex; justify-content: center; align-items: center; ">
        <form method="post" action="mealController" enctype="application/x-www-form-urlencoded" id="keywordSearch">
            <ul class="searchfield">

                <li><input id="keyword" type="text" class="text ac_input" placeholder="請輸入關鍵字" autocomplete="off"></li>
                <li><input type="text" name="action" value="keywordSearch" hidden></li>
                <li>
                    <button id="btn_search" type="submit" class="button" form="keywordSearch">搜尋</button>
                </li>

            </ul>

        </form>

    </div>
    <!--搜尋欄結束-->
</div>
<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
    <div id="CONTENT" class="layout-wrapper">
        <div class="layout-center" style="text-align:center">
            <!--側邊欄區塊開始-->
            <dl class="block_W">
                <dd id="CategoryContainer">
                    <ul class="treeview">
                        <li id="cate_D" class="expanded"><H1>功能列表</H1>
                            <ul class="main">
                                <li>
                                    <a href="${ctxPath}/meal/mealController?action=listAll">產品清單</a>
                                </li>
                                <li>
                                    <a href="${ctxPath}/cart/MealCart.jsp">菜單商品購物車
                                        <c:if test="${not empty cartProds}"> (${fn:length(cartProds)})</c:if></a>
                                </li>
                                <li>
                                    <a href="${ctxPath}/index.jsp">回首頁</a>
                                </li>
                            </ul>
                    </ul>
                </dd>
            </dl>
            <!--側邊欄區塊結束-->
            <div class="block_C s_list">
                <div class="Cm">
                    <div id="ItemContainer" class="Cm_C" style="margin:auto">
                        <br>
                        <div id="orderTable" style="margin:auto">
                            <table class="order">
                                <tr>
                                    <td style="width: 200px" class="order">訂單編號</td>
                                    <td style="width: 150px" class="order">購買時間</td>
                                    <td style="width: 100px" class="order">訂單總價</td>
                                    <td style="width: 100px" class="order">訂單狀態</td>
                                    <td style="width: 100px" class="order">查看訂單明細</td>
                                </tr>
                                <c:forEach var="order" items="${orders}" varStatus="loop">
                                    <tr>
                                        <td class="order">${order.orderNo}</td>
                                        <td class="order"><fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td class="order">${order.price}</td>
                                        <td class="order">${OrderStatus[order.status]}</td>
                                        <td class="order">
                                            <form method="get" action="orderDetailController"
                                                  id="form${loop.index}">
                                                <input type="text" name="action" value="listOrderDetail" hidden>
                                                <input type="text" name="orderNo" value="${order.orderNo}" hidden>
                                            </form>
                                            <button type="submit" form="form${loop.index}">查看明細</button>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>