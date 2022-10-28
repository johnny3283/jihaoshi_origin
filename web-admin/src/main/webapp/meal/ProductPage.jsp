<%@ page import="com.meal.model.MealVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    MealVO meal = (MealVO) request.getAttribute("meal");
%>
<html>
<head>
    <title><%=meal.getMealName()%>
    </title>
    <link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
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
                    <div class="prod_area">
                        <div class="div_productPage">
                            <img src="data:image/png;base64,<%= meal.getShowPhoto()%>" id="productPhoto"/>
                        </div>

                        <div class="div_productPage" style="text-align: left">
                            <p class="mealDescription"><label>菜單名稱：</label><%=meal.getMealName()%>
                            </p> <br>
                            <p class="mealDescription"><label>內容物：</label><%=meal.getMealContent()%>
                            </p> <br>
                            <p class="mealDescription"><label>熱量：</label><%=meal.getMealCal()%>
                            </p> <br>
                            <p class="mealDescription"><label>過敏源：</label><%=meal.getMealAllergen()%>
                            </p> <br>
                            <p class="mealDescription"><label>價格：</label><%=meal.getMealPrice()%>
                            </p> <br>
                            <p class="mealDescription"><label>食譜：</label><%=meal.getMealRecipe()%>
                            </p> <br>
                            <p class="mealDescription"><label>銷售量：</label><%=meal.getSaleVolume()%>
                            </p> <br>
                            <p class="mealDescription"><label>評論人數：</label><%=meal.getCommentPeople()%>
                            </p> <br>
                            <p class="mealDescription"><label>評分：</label><%=meal.getCommentScore()%>
                            </p> <br>
                            <p class="mealDescription">
                                <label>是否上架：</label><%=(meal.getLaunch() == 1) ? "上架中" : "未上架"%>
                            </p> <br>
                            <p class="mealDescription"><label>更新時間：</label><%=meal.getUpdateTime()%>
                            </p> <br>
                            <form method="post" action="mealController?action=toUpdate" id="form1">
                                <input type="text" value="<%=meal.getMealNo()%>" name="mealNo" hidden>
                                <button type="submit" form="form1">修改資料</button>
                            </form>
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
