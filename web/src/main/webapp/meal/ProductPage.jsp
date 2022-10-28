<%@ page import="com.meal.model.MealVO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  MealVO meal =(MealVO) request.getAttribute("meal");
%>
<html>
<head>
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Title</title>
    <style>
        #productPhoto {
            width: 500px;
            height: 500px;
        }
        #pageHead{
            width: 100%;
            height: 30%;
        }
        #div1{
            width:500px;
            height: 500px;
            margin: 20px;
            float:left;
        }
        #div2{
            width: 500px;
            height: 500px;
            margin: 20px;
            float:left;
        }
        p{
            display: inline-block;
            font-size: 18px;
            line-height: 5px;
        }
        label{
            font-size: 18px;
        }

    </style>

</head>
<body>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">
<div id="div1">
    <img src="data:image/png;base64,<%= meal.getShowPhoto()%>" id="productPhoto"/>
</div>
<div id="div2">
    <label>菜單名稱：</label><p><%=meal.getMealName()%></p> <br>
    <label>內容物：</label><p><%=meal.getMealContent()%></p> <br>
    <label>熱量：</label><p id="cal"><%=meal.getMealCal()%></p> <br>
    <label>過敏源：</label> <p><%=meal.getMealAllergen()%></p> <br>
    <label>價格：</label><p id="price"><%=meal.getMealPrice()%></p> <br>
    <label>食譜：</label><p><%=meal.getMealRecipe()%></p> <br>
    <label>評論人數：</label><p><%=meal.getCommentPeople()%></p> <br>
    <label>評分：</label><p><%=(meal.getCommentPeople()!=0)?(meal.getCommentScore()/meal.getCommentPeople()):"尚無人評分"%></p> <br>
    <label>調整分量：</label>

    <input type="radio" name="quantity" value="1" id="quantity1" class="quantity">
    <label for="quantity1">原始分量</label>
    <input type="radio" name="quantity" value="1.2" id="quantity1.2" class="quantity">
    <label for="quantity1.2">1.2倍</label>
    <input type="radio" name="quantity" value="1.5" id="quantity1.5" class="quantity">
    <label for="quantity1.5">1.5倍</label>
    <form method="post" action="#" id="formCart">
        <input type="text" value="<%=meal.getMealNo()%>" name="mealNo" hidden>

    </form>
    <form method="post" action="#" id="formCheckout">
        <input type="text" value="<%=meal.getMealNo()%>" name="mealNo" hidden>

    </form>
    <button type="submit" form="formCart">加入購物車</button>
    <button type="submit" form="formCheckout" >直接購買</button>
</div>
<script>
    $(document).ready(function () {
        $('#quantity1').click(function () {
            let cal=<%=meal.getMealCal()%> * $('#quantity1').val()
            let price=<%=meal.getMealPrice()%> * $('#quantity1').val()
            $('#cal').text(cal);
            $('#price').text(price);
        });
        $('#quantity1\\.2').click(function () {
            let cal=<%=meal.getMealCal()%> * $('#quantity1\\.2').val()
            let price=<%=meal.getMealPrice()%> * $('#quantity1\\.2').val()
            $('#cal').text(cal);
            $('#price').text(price);
        });
        $('#quantity1\\.5').click(function () {
            let cal=<%=meal.getMealCal()%> * $('#quantity1\\.5').val()
            let price=<%=meal.getMealPrice()%> * $('#quantity1\\.5').val()
            $('#cal').text(cal);
            $('#price').text(price);
        });
    });
</script>
</body>
</html>
