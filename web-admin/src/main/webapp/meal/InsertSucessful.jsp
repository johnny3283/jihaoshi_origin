<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" href="${ctxPath}/css/jihaoshi.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Title</title>
</head>
<body>
<img src="${ctxPath}/images/JihaoshiPageHead.jpg" id="pageHead">
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
                                    <a href="${ctxPath}/meal/MealInsert.jsp">新增菜單商品</a>
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
                <div class="Cm" >


                        <div>
                            <br>
                            <h2 style=" font-size: 25px">${meal.mealName}新增成功，是否繼續新增商品營養特色？</h2><br>
                            <form method="post" action="${ctxPath}/nutrient/detailInsert" id="insert">
                                <input type="text" value="${meal.mealName}" name="mealName" hidden>
                                <input type="text" value="${meal.mealNo}" name="mealNo" hidden>
                            </form>
                            <form method="post" action="mealController?action=findByprod&mealNo=${meal.mealNo}" id="no">
                            </form>
                            <button type="submit" form="insert">是</button>
                            <button type="submit" form="no">否(跳轉商品頁面)</button>
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>