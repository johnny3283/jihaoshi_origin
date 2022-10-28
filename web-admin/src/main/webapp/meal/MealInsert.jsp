<%@ page import="com.meal.model.MealVO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    MealVO meal = (MealVO) request.getAttribute("MealVO");

%>
<html>
<head>
    <title>新增菜單商品</title>
    <link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
    <style>
        #pageHead {
            width: 100%;
            height: 30%;
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
                                    <a href="<%=request.getContextPath()%>/meal/MealInsert.jsp">新增菜單</a>
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
                    <div id="ItemContainer" class="Cm_C">
                        <!--商品欄開始-->
                        <br>
                        <span class="mealDescription">
                        <h1 style="font-size: 18px">新增菜單商品</h1>
                        <c:if test="${not empty errMsgs}">
                            <font style="color:red">請修正以下錯誤:</font>
                            <ul>
                                <c:forEach var="message" items="${errMsgs}">
                                    <li style="color:red">${message}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                                <form method="post" action="mealController" enctype="multipart/form-data" id="form1">

                                    <div>
                                        <input type="text" name="action" value="insert" hidden>
                                        <label>菜單名稱：</label>
                                        <input type="text" name="mealName"
                                               value="<%=(meal==null)? "":meal.getMealName() %>"><br><br>
                                        <label>食材內容：</label>
                                        <input type="text" name="mealContent"
                                               value="<%=(meal==null)? "":meal.getMealContent()%>"><br><br>
                                        <label>食品熱量：</label>
                                        <input type="text" name="mealCal">
                                        <br><br>
                                        <label>過敏物質：</label>
                                        <input type="text" name="mealAllergen"
                                               value="<%=(meal==null)? "":meal.getMealAllergen()%>"><br><br>
                                        <label>輸入售價：</label>
                                        <input type="text" name="maelPrice"><br><br>
                                        <label>輸入食譜：</label>
                                        <textarea name="mealRecipe"
                                                  value="<%=(meal==null)? "":meal.getMealRecipe()%>"></textarea><br><br>
                                        <label>上傳照片：</label><input type="file" name="mealPhoto"
                                                                   accept="image/*"><br><br>
                                        <label>是否上架：</label>
                                        <input type="radio" name="launch" value="1" id="launchYes" checked>
                                        <label for="launchYes">是</label>
                                        <input type="radio" name="launch" value="0" id="launchNo">
                                        <label for="launchNo">否</label>
                                        <br>
                                        <button type="submit" form="form1">送出資料</button>
                                        <button type="reset" form="form1">清除資料</button>
                                    </div>

                                </form>
                            </span>
                        <!--商品欄結束-->
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
