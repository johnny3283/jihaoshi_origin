<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
    <title>營養特色新增</title>
    <style>
        .errors {
            color: red;
        }
    </style>
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
                                    <a href="${ctxPath}/meal/mealController?action=listAll" >菜單商品列表</a>
                                </li>
                                <li>
                                    <a href="${ctxPath}/meal/MealInsert.jsp">新增菜單</a>
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
                    <div id="ItemContainer" class="Cm_C">
                        <br>
                        <span class="mealDescription">
                        <div>
                        <form:form method="post" action="${ctxPath}/nutrient/insert"
                                   enctype="application/x-www-form-urlencoded"
                                   id="formNutrient" modelAttribute="nutrientFeature">
                            <label>營養特色：</label><form:input type="text" name="featureName" path="featureName"/>
                            <button type="submit" form="formNutrient">新增</button>
                            <form:errors path="featureName" cssClass="errors"/><br>
                        </form:form>
                        </div>
                        </span>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>
