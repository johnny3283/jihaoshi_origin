<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.meal.model.MealVO" %>
<%@ page import="java.util.List" %>
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
        <form method="post" action="${ctxPath}/meal/mealController" enctype="application/x-www-form-urlencoded" id="searceKeyword">
            <ul class="searchfield">
                <li>
                    <input name="action" value="nameKeywordSearch" hidden>
                    <input id="keyword" type="text" class="text ac_input" name="nameKeyword" placeholder="請輸入關鍵字" >

                </li>
                <li>
                    <button type="submit" form="searceKeyword" >查找商品</button>
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
                        <!--商品欄開始-->
                        <%@ include file="page1.jsp" %>
                        <c:forEach var="meal" items="${meals}" begin="<%= pageIndex %>"
                                   end="<%= pageIndex+rowsPerPage-1 %>" varStatus="loop">
                            <dl class="col3f" id="DRAA0A-A900BUT82">
                                <dd class="c1f"><a class="prod_img"
                                                   href="mealController?action=findByprod&mealNo=${meal.mealNo}">
                                    <img src="${not empty meal.showPhoto? meal.showPhoto:"../images/noImg.jpg"}"></a></dd>
                                <dd class="c2f">
                                    <ul class="tag_box s_label"></ul>
                                    <h5 class="prod_name"><a
                                            href="mealController?action=findByprod&mealNo=${meal.mealNo}">${meal.mealName}</a>
                                    </h5>
                                    <br>
                                    <span style="font-size: 18px">簡易食譜：${meal.mealRecipe}</span>
                                    <br>
                                    <span style="font-size: 18px">熱量：${meal.mealCal}</span>
                                    <br>
                                    <span style="font-size: 18px">可能過敏原：${meal.mealAllergen}</span><br>
                                    <c:forEach var="nutrientFeatureDetail" items="${meal.nutrientFeatureDetails}">
                                        <a href="${ctxPath}/meal/mealController?action=hashtag&featureName=${nutrientFeatureDetail.featureName }" style="font-style: italic">#${nutrientFeatureDetail.featureName}&ensp;</a>
                                    </c:forEach>
                                </dd>
                                <dd class="c3f" id="button_DRAA0A-A900BUT82">
                                    <ul class="price_box">
                                        <li><span style="font-size: 18px">價格NT$${meal.mealPrice}</span>
                                        </li>
                                    </ul>

                                    <form method="post" action="${ctxPath}/cart/cartController"
                                          enctype="application/x-www-form-urlencoded" id="cart${meal.mealNo}">
                                        <input type="text" name="action" value="cartAdd" hidden>
                                        <input type="text" value="${meal.mealNo}" name="mealNo" hidden>
                                        <input type="text" name="quantityCart" id="quantityCart" value="1" hidden>
                                        <label style="font-size: 18px">請輸入購買數量：<span id="amount_value_${loop.index}">1</span></label>
                                        <input name="amount" type="range" min="1" max="99" value="1" id="amount${loop.index}">
                                        <p>(若需調整份量，請進入商品頁面)</p>
                                    </form>
                                    <br>
                                    <button type="submit" form="cart${meal.mealNo}">加入購物車</button>
                                    <br><br>
                                    <P>評論人數：${meal.commentPeople}</P>
                                    <p>產品評價：${meal.commentPeople==0?"尚無人評分":(meal.commentScore/meal.commentPeople)}</p>
                                </dd>
                            </dl>
                        </c:forEach>
                        <%@ include file="page2.jsp" %>
                        <!--商品欄結束-->
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<script>
    $(document).ready(function () {
    <c:forEach var="meal" items="${meals}" varStatus="loop">
        $('#amount${loop.index}').mousemove(function () {
            $('#amount_value_${loop.index}').html($('#amount${loop.index}').val());
        });
        $('#amount${loop.index}').change(function () {
            $('#amount_value_${loop.index}').html($('#amount${loop.index}').val());
        });
    </c:forEach>
    });
</script>
</body>
</html>
