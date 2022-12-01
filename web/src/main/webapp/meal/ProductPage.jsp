<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.meal.model.MealVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%
    MealVO meal = (MealVO) request.getAttribute("meal");

%>
<html>
<head>
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>${meal.mealName}</title>
<style type="text/css">
#content {
	display: flex; 
	justify-content: center; 
	align-items: center;
}
.prod {
	display:flex;
	flex-wrap:wrap;
	justify-content: space-around;  
}
.button {
 	border-radius:1rem; 
 	border: 1px solid #ccc;
}
.img {
	margin-right: 50px;
	width:45%;
}
.productPage {
	text-align: left;
	line-height: 35px; 
	margin:auto 0;
	width:45%
}
</style>
</head>
<body>
<%@ include file="../navbar.file" %>
<br>
<div id="CONTENT">
<!--商品欄開始-->
	<div class="prod">
		<div class="img">
			<img src="${meal.showPhoto}" class="productPhoto" style="width:100%">
        </div>
        <div class="productPage">
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
             </span>
             <br>
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
                <label style="font-size: 18px">請輸入購買數量：</label><span id="amount_value" style="font-size: 18px">1</span>
                <br>
                <input name="amount" type="range" min="1" max="99" value="1" id="amount">
               </form>
               <%--@elvariable id="collectionDetail" type="com.mealCollectionDetail.model.CollectionDetailVO"--%>
               <form:form method="post" action="${ctxPath}/mealCollect/insert" enctype="application/x-www-form-urlencoded"
                id="formCollect" modelAttribute="collectionDetail" >
               		<input type="text" name="memberNo" value="${member.memberNo}" hidden>
                    <input type="text" name="mealNo" value="${meal.mealNo}" hidden>
                    <input type="text" name="mealName" value="${meal.mealName}" hidden>
               </form:form>
                    <button type="submit" form="formCart" class="button">加入購物車</button>
                    <button type="submit" form="formCollect" class="button">加入收藏</button><br>
                    <span style="color: red">${empty collectionResult?"":collectionResult}</span>
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
