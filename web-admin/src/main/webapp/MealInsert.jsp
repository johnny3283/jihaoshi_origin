<%@ page import="com.meal.model.MealVO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    MealVO meal = (MealVO) request.getAttribute("MealVO");

%>
<html>
<head>
    <title>新增菜單商品</title>
</head>
<body>
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
        <label>輸入菜單名稱：</label>
        <input type="text" name="mealName" value="<%=(meal==null)? "":meal.getMealName() %>"><br><br>
        <label>輸入食材內容：</label>
        <input type="text" name="mealContent" value="<%=(meal==null)? "":meal.getMealContent()%>"><br><br>
        <label>輸入熱量：</label>
        <input type="text" name="mealCal">
        <br><br>
        <label>輸入可能過敏源：</label>
        <input type="text" name="mealAllergen" value="<%=(meal==null)? "":meal.getMealAllergen()%>"><br><br>
        <label>輸入售價：</label>
        <input type="text" name="maelPrice" ><br><br>
        <label>輸入食譜：</label>
        <textarea name="mealRecipe" value="<%=(meal==null)? "":meal.getMealRecipe()%>"></textarea><br><br>
        <label>上傳照片：</label><input type="file" name="mealPhoto" accept="image/*"><br><br>
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
<script>

</script>
</body>
</html>
