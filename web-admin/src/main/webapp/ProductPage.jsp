<%@ page import="com.meal.model.MealVO" %>
<%@ page import="com.meal.model.MealDAO" %>
<%@ page import="com.meal.model.MealDAOImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  MealVO meal =(MealVO) request.getAttribute("Meal");
  if(meal==null){
      MealDAO dao=new MealDAOImpl();
      meal=dao.findByMealNo(Integer.valueOf(request.getParameter("mealNo")));
  }
%>
<html>
<head>
    <title>Title</title>
    <style>
        img {
            width: 500px;
            height: 500px;
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

<div id="div1">
    <img src="ShowMealPhoto?mealNo=<%=meal.getMealNo()%>" >
</div>
<div id="div2">
    <p ><label >菜單名稱：</label><%=meal.getMealName()%></p> <br>
    <p ><label >內容物：</label><%=meal.getMealContent()%></p> <br>
    <p ><label >熱量：</label><%=meal.getMealCal()%></p> <br>
    <p ><label >過敏源：</label><%=meal.getMealAllergen()%></p> <br>
    <p ><label >價格：</label><%=meal.getMealPrice()%></p> <br>
    <p ><label >食譜：</label><%=meal.getMealRecipe()%></p> <br>
    <p ><label >銷售量：</label><%=meal.getSaleVolume()%></p> <br>
    <p ><label >評論人數：</label><%=meal.getCommentPeople()%></p> <br>
    <p ><label >評分：</label><%=meal.getCommentScore()%></p> <br>
    <p ><label >是否上架：</label><%=meal.getLaunch()%></p> <br>
    <p ><label >更新時間：</label><%=meal.getUpdateTime()%></p> <br>
    <form method="post" action="MealUpdate.jsp" id="form1">
        <input type="text" value="<%=meal.getMealNo()%>" name="mealNo" hidden>
        <button type="submit" form="form1">修改資料</button>
    </form>
</div>

</body>
</html>
