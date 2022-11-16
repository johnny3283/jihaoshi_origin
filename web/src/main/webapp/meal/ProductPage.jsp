<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.meal.model.MealVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    MealVO meal = (MealVO) request.getAttribute("meal");

%>
<html>
<head>
    <link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>${meal.mealName}</title>
</head>
<body>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">
<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
    <div id="CONTENT" class="layout-wrapper">
        <div class="layout-center" style="text-align:center">
            <!--側邊欄區塊開始-->
            <dl class="block_W">
                <dd id="CategoryContainer">
                    <ul class="treeview">
                        <li id="cate_D" class="expanded"><H1>功能列表</H1></li>
                        <ul class="main">
                            <li>
                                <a href="mealController?action=listAll">產品清單</a>
                            </li>
                            <li>
                                <a href="<%=request.getContextPath()%>/cart/MealCart.jsp">菜單商品購物車<c:if
                                        test="${not empty cartProds}"> (${fn:length(cartProds)})</c:if></a>
                            </li>
                            <li>
                                <a href="<%=request.getContextPath()%>/index.jsp">回首頁</a>
                            </li>
                        </ul>
                    </ul>
                </dd>
            </dl>
            <!--側邊欄區塊結束-->
            <div class="block_C s_list">
                <div class="Cm">
                    <div class="prod_area">
                        <div class="div_productPage">
                            <img src="${meal.showPhoto}" id="productPhoto"/>
                        </div>

                        <div class="div_productPage" style="text-align: left;line-height: 35px">
                            <span class="mealDescription">
                            <label>菜單名稱：</label><span>${meal.mealName}</span>
                            <br>
                            <label>內容物：</label><span>${meal.mealContent}</span>
                            <br>
                            <label>熱量：</label><span id="cal">${meal.mealCal}</span>
                            <br>
                                <label>營養特色：</label><span style="font-size: 18px">
                                    <c:forEach var="nutrientFeatureDetail" items="${meal.nutrientFeatureDetails}">
                                        <a href="${ctxPath}/meal/mealController?action=hashtag&featureName=${nutrientFeatureDetail.featureName }"
                                           style="font-style: italic">#${nutrientFeatureDetail.featureName}&ensp;</a>
                                    </c:forEach>
                                </span><br><br>
                            <label>過敏源：</label><span>${meal.mealAllergen}</span>
                            <br>
                            <label>價格：</label><span id="price">${meal.mealPrice}</span>
                            <br>
                            <label>食譜：</label><span>${meal.mealRecipe}</span>
                            <br>
                            <label>評論人數：</label><span>${meal.commentPeople}</span>
                            <br>
                            <label>評分：</label><span>${meal.commentPeople==0?"尚無人評分":(meal.commentScore/meal.commentPeople)}</span>
                           <br>
                            <label>調整分量：</label><br>
                            <input type="radio" name="quantity" value="1" id="quantity1" class="quantity" checked>
                            <label for="quantity1">原始分量</label>
                            <input type="radio" name="quantity" value="1.2" id="quantity1.2" class="quantity">
                            <label for="quantity1.2">1.2倍</label>
                            <input type="radio" name="quantity" value="1.5" id="quantity1.5" class="quantity">
                            <label for="quantity1.5">1.5倍</label>
                            </span><br>
                            <form method="post" action="${ctxPath}/cart/cartController" id="formCart">
                                <input type="text" name="action" value="cartAdd" hidden>
                                <input type="text" name="mealNo" value="${meal.mealNo}" hidden>
                                <input type="text" name="quantityCart" id="quantityCart" value="1" hidden>
                                <label style="font-size: 18px">請輸入購買數量：</label><span id="amount_value"
                                                                                     style="font-size: 18px">1</span>
                                <br>
                                <input name="amount" type="range" min="1" max="99" value="1" id="amount">
                            </form>
                            <form method="post" action="#" id="formCheckout">
                                <input type="text" value="<%=meal.getMealNo()%>" name="mealNo" hidden>
                                <input type="text" name="action" value="checkout" hidden>
                                <input type="text" name="quantityCheckout" id="quantityCheckout" value="1" hidden>
                            </form>

                            <button type="submit" form="formCart">加入購物車</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<script>
    $(document).ready(function () {
        $('#amount').mousemove(function () {
            $('#amount_value').html($('#amount').val());
        });
        $('#amount').change(function () {
            $('#amount_value').html($('#amount').val());
        });
        $('#quantity1').click(function () {
            let cal =
            <%=meal.getMealCal()%> *
            $('#quantity1').val()
            let price =
            <%=meal.getMealPrice()%> *
            $('#quantity1').val()
            $('#cal').text(cal);
            $('#price').text(price);
            $('#quantityCart').attr('value', $('#quantity1').val());
            $('#quantityCheckout').attr('value', $('#quantity1').val());
        });
        $('#quantity1\\.2').click(function () {
            let cal =
            <%=meal.getMealCal()%> *
            $('#quantity1\\.2').val()
            let price =
            <%=meal.getMealPrice()%> *
            $('#quantity1\\.2').val()
            $('#cal').text(cal);
            $('#price').text(price);
            $('#quantityCart').attr('value', $('#quantity1\\.2').val());
            $('#quantityCheckout').attr('value', $('#quantity1\\.2').val());
        });
        $('#quantity1\\.5').click(function () {
            let cal =
            <%=meal.getMealCal()%> *
            $('#quantity1\\.5').val()
            let price =
            <%=meal.getMealPrice()%> *
            $('#quantity1\\.5').val();
            $('#cal').text(cal);
            $('#price').text(price);
            $('#quantityCart').attr('value', $('#quantity1\\.5').val());
            $('#quantityCheckout').attr('value', $('#quantity1\\.5').val());
        });

    });
</script>
</body>
</html>
