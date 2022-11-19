<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.meal.model.MealVO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<MealVO> meals = (List<MealVO>) request.getAttribute("meals");
%>

<html>
<head>
    <title>Title</title>
    <link type="text/css" href="${ctxPath}/css/jihaoshi.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<img src="../images/JihaoshiPageHead.jpg" id="pageHead">
<div class="block_N" style="margin:0px auto;">
    <!--搜尋欄開始-->
    <div class="Nm" style="display: flex; justify-content: center; align-items: center; ">
        <form method="post" action="${ctxPath}/meal/mealController" enctype="application/x-www-form-urlencoded"
              id="searceKeyword">
            <ul class="searchfield">
                <li>
                    <input name="action" value="nameKeywordSearch" hidden>
                    <input id="keyword" type="text" class="text ac_input" name="nameKeyword" placeholder="請輸入關鍵字">

                </li>
                <li>
                    <button type="submit" form="searceKeyword">查找商品</button>
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
                                    <a href="${ctxPath}/cart/MealCart.jsp">菜單商品購物車
                                        <c:if test="${not empty cartProds}"> (${fn:length(cartProds)})</c:if></a>
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
                        <br>
                        <form method="post" action="${ctxPath}/cart/cartController" enctype="application/x-www-form-urlencoded" id="goCart">
                        <c:forEach var="meal" items="${meals}"  varStatus="loop">
                            <dl class="col3f" id="DRAA0A-A900BUT82">
                                <dd class="c1f"><a class="prod_img"
                                                   href="${ctxPath}/meal/mealController?action=findByprod&mealNo=${meal.mealNo}">
                                    <img src="${not empty meal.showPhoto? meal.showPhoto:"../images/noImg.jpg"}"></a>
                                </dd>
                                <dd class="c2f">
                                    <ul class="tag_box s_label"></ul>
                                    <h5 class="prod_name"><a
                                            href="${ctxPath}/meal/mealController?action=findByprod&mealNo=${meal.mealNo}">${meal.mealName}</a>
                                    </h5>
                                    <br>
                                    <span style="font-size: 18px">簡易食譜：${meal.mealRecipe}</span>
                                    <br>
                                    <span style="font-size: 18px">熱量：${meal.mealCal}</span>
                                    <br>
                                    <span style="font-size: 18px">可能過敏原：${meal.mealAllergen}</span><br>
                                </dd>
                                <dd class="c3f" id="button_DRAA0A-A900BUT82">
                                    <ul class="price_box">
                                        <li><span style="font-size: 18px">價格NT$${meal.mealPrice}</span>
                                        </li>
                                    </ul>
                                    <br><br>
                                </dd>
                            </dl>
                            <input type="checkbox" name="mealNos" value="${meal.mealNo}" checked hidden>
                        </c:forEach>
                            <input type="text" name="action" value="randomAdd" hidden>
                        </form>
                        <div style="font-size: 18px;text-align: left">
                        <label>商品總價為：</label>${totalPrice}元，是否需要重新幫您配餐？

                        <button type="submit" form="goCart">否，請幫我加入購物車</button>
                        <button type="button" id="reAsssign">是</button>
                        <div id="reAssignArea" hidden>
                            <form method="post" action="${ctxPath}/meal/mealController" enctype="application/x-www-form-urlencoded"
                                  id="formRandom" >
                                <input type="text" name="action" value="randomAssign" hidden>
                                <label>餐點份數：</label><span id="amount_value" style="font-size: 18px">${param.mealAmount}</span><br>
                                <input type="range" value="${param.mealAmount}" min="1" max="10" name="mealAmount" id="mealAmount" ><br>
                                <label>是否接受重複餐點：</label>
                                <label for="yes">是</label><input type="radio" name="repeat" value="yes" id="yes" checked>
                                <label for="no">否</label><input type="radio" name="repeat" value="no" id="no">
                                <button type="submit" form="formRandom">隨機配餐</button>
                            </form>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<script >
    $(document).ready(function () {

        $('#reAsssign').click(function () {

            $('#reAssignArea').show();

        });
        $('#mealAmount').mousemove(function () {
            $('#amount_value').html($('#mealAmount').val());
        });
        $('#mealAmount').change(function () {
            $('#amount_value').html($('#mealAmount').val());
        });
        $('#mealAmount').click(function () {
            $('#amount_value').html($('#mealAmount').val());
        });

    });
</script>
</body>
</html>
