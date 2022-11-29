<%@ page import="com.meal.model.MealVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <title>${meal.mealName}</title>
    <link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
</head>
<body>
<img src="../images/JihaoshiPageHead.jpg" id="pageHead">
<p></p>
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
                                    <a href="${ctxPath}/meal/mealController?action=listAll" >菜單商品列表</a>
                                </li>
                                <li>
                                    <a href="${ctxPath}/meal/MealInsert.jsp">新增菜單</a>
                                </li>
                                <li>
                                    <a href="${ctxPath}/nutrient/insert" >新增營養特色</a>
                                </li>
                                <li>
                                    <a href="${ctxPath}/meal/MealManagerIndex.jsp">回菜單商品管理首頁</a>
                                </li>
                                <li>
                                    <a href="${ctxPath}">回首頁</a>
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
                        <div class="div_productPage" style="text-align: left">
                            <span class="mealDescription">
                                  <c:forEach var="nutrientFeatureDetail" items="${meal.nutrientFeatureDetails}">
                                      ${nutrientFeatureDetail.featureName}
                                  </c:forEach>
                            <label>菜單名稱：</label><span>${meal.mealName}</span>
                                </p> <br>
                            <label>內容物：</label><span>${meal.mealContent}</span>
                                </p> <br>
                            <label>熱量：</label><span>${meal.mealCal}</span>
                                </p> <br>
                            <label>營養特色：</label>
                                <span style="font-size: 18px">
                                    <c:forEach var="nutrientFeatureDetail" items="${meal.nutrientFeatureDetails}">
                                        <a href="${ctxPath}/meal/mealController?action=hashtag&featureName=${nutrientFeatureDetail.featureName }" style="font-style: italic">#${nutrientFeatureDetail.featureName}&ensp;</a>
                                    </c:forEach>
                                </span><br><br>
                            <label>過敏源：</label><span>${meal.mealAllergen}</span>
                                </p> <br>
                            <label>價格：</label><span>${meal.mealPrice}</span>
                                </p> <br>
                            <label>食譜：</label><span>${meal.mealRecipe}</span>
                                </p> <br>
                                <label>是否上架：</label><span>${meal.launch==1?"上架中" : "未上架"}</span>
                                </p> <br>
                            <span><label>更新時間：</label><fmt:formatDate value="${meal.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                </p> <br>
                            <form method="post" action="${ctxPath}/meal/mealController" id="form1">
                                <input type="text" name="action" value="toUpdate" hidden>
                                <input type="text" value="${meal.mealNo}" name="mealNo" hidden>
                                <button type="submit" form="form1">修改資料</button>
                            </form>
                                </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<script>

</script>
</body>
</html>
