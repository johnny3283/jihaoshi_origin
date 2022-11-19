<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的收藏</title>
    <link type="text/css" href="${ctxPath}/css/jihaoshi.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<img src="../images/JihaoshiPageHead.jpg" id="pageHead">
<div class="block_N" style="margin:0px auto;">
    <!--搜尋欄開始-->
    <div class="Nm" style="display: flex; justify-content: center; align-items: center; ">
        <form method="post" action="${ctxPath}/meal/mealController" enctype="application/x-www-form-urlencoded" id="keywordSearch">
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
                                    <a href="${ctxPath}/meal/RandomAssign.jsp">隨機配餐</a>
                                </li>
                                <li>
                                    <a href="${ctxPath}/cart/MealCart.jsp">菜單商品購物車
                                        <c:if test="${not empty cartProds}"> (${fn:length(cartProds)})</c:if></a>
                                </li>
                                <li>
                                    <a href="${ctxPath}/order/orderController?action=orderList">訂單管理</a>
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
                                    <td style="width: 100px" class="order">收藏編號</td>
                                    <td style="width: 150px" class="order">商品名稱</td>
                                    <td style="width: 100px" class="order">刪除商品</td>
                                </tr>
                                <c:forEach var="detail" items="${collectionDetails}" varStatus="loop">
                                    <tr>
                                        <td class="order">${loop.count}</td>
                                        <td class="order"><a href="${ctxPath}/meal/mealController?action=findByprod&mealNo=${detail.mealNo}">${detail.mealName}</a></td>
                                        <td class="order">
                                            <form method="post" action="${ctxPath}/mealCollect/delete"
                                                  id="form${loop.index}">
                                                <input type="text" name="detailNo" value="${detail.detailNo}" hidden>
                                            </form>
                                            <button type="submit" form="form${loop.index}">刪除收藏</button>
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
