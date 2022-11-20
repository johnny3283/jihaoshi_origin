<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜單商品購物車</title>
    <link type="text/css" href="${ctxPath}/css/jihaoshi.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<img src="../images/JihaoshiPageHead.jpg" id="pageHead">
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
                                    <a href="${ctxPath}/order/orderController?action=orderList">訂單管理</a>
                                </li>
                                <li>
                                    <a href="${ctxPath}/mealCollect/list">我的收藏</a>
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
                    <div id="ItemContainer" class="Cm_C">
                        <c:forEach var="cartProd" items="${cartProds}" varStatus="loop">
                            <dl class="col3f" id="DRAA0A-A900BUT82">
                                <dd class="c1f"><a class="prod_img"
                                                   href="${ctxPath}/meal/mealController?action=findByprod&mealNo=${cartProd.meal.mealNo}">
                                    <img src="${cartProd.meal.showPhoto}"></a></dd>
                                <dd class="c2f">

                                    <ul class="tag_box s_label"></ul>
                                    <h5 class="prod_name"><a
                                            href="${ctxPath}/meal/mealController?action=findByprod&mealNo=${cartProd.meal.mealNo}">${cartProd.meal.mealName}</a>
                                    </h5>
                                    <br>
                                    <span style="font-size: 18px">分量：${cartProd.quantity}</span>
                                    <br>
                                    <span style="font-size: 18px">熱量：${cartProd.cal}</span>
                                    <br>
                                    <span style="font-size: 18px">簡易食譜：${cartProd.meal.mealRecipe}</span>

                                </dd>
                                <dd class="c3f" id="button_DRAA0A-A900BUT82">
                                    <ul class="price_box">
                                        <li>
                                            <span style="font-size: 18px">單價NT$${cartProd.meal.mealPrice}</span>
                                        </li>
                                        <li>
                                            <span style="font-size: 18px">總價NT$${cartProd.price}</span>
                                        </li>
                                        <li>
                                            <span style="font-size: 18px">數量：</span>
                                            <span style="font-size: 18px"
                                                  id="amount_value_${loop.index}">${cartProd.amount}</span>
                                        </li>
                                    </ul>

                                    <form method="post" action="${ctxPath}/cart/cartController"
                                          enctype="application/x-www-form-urlencoded" id="cart${loop.index}">
                                        <input name="action" type="text" value="cartModify" hidden>
                                        <input name="amount" type="range" min="1" max="99" value="${cartProd.amount}"
                                               id="amount${loop.index}">
                                        <input name="cartIndex" type="text" value="${loop.index}" hidden>
                                    </form>
                                    <br>
                                    <form method="post" action="${ctxPath}/cart/cartController"
                                          enctype="application/x-www-form-urlencoded" id="cartDelete${loop.index}">
                                        <input name="action" type="text" value="cartDelete" hidden>
                                        <input name="cartIndex" type="text" value="${loop.index}" hidden>
                                    </form>
                                    <button type="submit" form="cart${loop.index}">修改數量</button>
                                    <button type="submit" form="cartDelete${loop.index}">從購物車刪除</button>
                                    <br><br>
                                </dd>
                            </dl>

                        </c:forEach>
                        <div id="totalPrice">
                            <c:choose>
                                <c:when test="${empty cartProds}">
                                    <span style="font-size: 16px;">購物車中還沒有東西喔</span>
                                    <span style="font-size: 16px;"><a
                                            href="${ctxPath}/meal/mealController?action=listAll">去選購</a></span><br>
                                </c:when>
                                <c:otherwise>
                                    <span style="font-size: 16px;">商品總價：${totalPrice}元 </span>
                                    <form method="post" action="${ctxPath}/checkout/checkoutController" id="checkout"
                                          enctype="application/x-www-form-urlencoded">
                                        <input type="text" name="action" value="checkout" hidden>
                                    </form>
                                    <form method="post" action="${ctxPath}/cart/cartController"
                                          enctype="application/x-www-form-urlencoded" id="clearCart">
                                        <input type="text" value="clearCart" name="action" hidden>
                                    </form>
                                    <button type="submit" form="checkout">去結帳</button>
                                    <button type="submit" form="clearCart">清空購物車</button>

                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<script>
    $(document).ready(function () {
        <c:forEach var="cartProd" items="${cartProds}" varStatus="loop">
        $('#amount${loop.index}').on('change mousemove', function () {
            $('#amount_value_${loop.index}').html($(this).val());
        });

        </c:forEach>
    });
</script>
</body>
</html>
