<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.meal.model.MealVO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<MealVO> meals = (List<MealVO>) request.getAttribute("meals");
%>

<html>
<head>
<title>產品清單</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
#search {
	border-style:double;
	border-color:#ecb714;
	border-radius:10px;
	width:300px;
	height:50px;
	display: flex; 
	justify-content: center; 
	align-items: center;
}
.searchfield {
	margin: 0px auto;
}
#content {
	display: flex; 
	justify-content: center; 
	align-items: center;
	flex-direction: column;
	width: 95%;
    margin: auto;
}
.product{
	display: flex; 
	justify-content: center; 
	align-items: center;
	padding:0px 10px;
}
.img{
	width:60%;
	height:80%;
}
.pic {
	width: 30%;
}
.info {
	width: 40%;
	display: flex; 
	justify-content: center; 
	align-items: center;
	flex-direction: column;
}
.buy {
	width: 30%;
	display: flex; 
	justify-content: center; 
	align-items: center;
	flex-direction: column;
}
.button {
 	border-radius:1rem; 
 	border: 1px solid #ccc;
}
</style>
</head>
<body>
<%@ include file="../navbar.file" %>
<br>
<div id="searchArea" style="margin: 0px auto; display: flex; justify-content: center; align-items: center;">
    <!--搜尋欄開始-->
    <div id="search">
        <form method="post" class="searchfield" action="${ctxPath}/meal/mealController" enctype="application/x-www-form-urlencoded" id="searceKeyword">
                <input name="action" value="nameKeywordSearch" type="hidden">
                <input id="keyword" type="text" class="text ac_input" name="nameKeyword" placeholder="請輸入關鍵字">            
                <button type="submit" form="searceKeyword" style="border-radius:1rem; border: 1px solid #ccc;">查找商品</button>           
        </form>
    </div>
    <!--搜尋欄結束-->
</div>
<br>
<div id="CONTENT">
<!--商品欄開始-->
	<div style="margin: 0px auto;">
    <%@ include file="page1.jsp" %>
    </div>
    <br>
    <div>
   	<span style="color: red">${empty collectionResult?"":collectionResult}</span>
   	</div>
   	<div>
       <c:forEach var="meal" items="${meals}" begin="<%= pageIndex %>" end="<%= pageIndex+rowsPerPage-1 %>" varStatus="loop">
             <div class="product">
             	<div class="pic">
             		<a class="prod_img" href="${ctxPath}/meal/mealController?action=findByprod&mealNo=${meal.mealNo}">
                	<img class="img" src="${not empty meal.showPhoto? meal.showPhoto:"../images/noImg.jpg"}"></a>
                </div>
                <div class="info">               	
                    <h5 class="prod_name">
                    <a href="${ctxPath}/meal/mealController?action=findByprod&mealNo=${meal.mealNo}">${meal.mealName}</a>
                    </h5>
                    <br>
                    <span style="font-size: 18px">簡易食譜：${meal.mealRecipe}</span>
                    <br>
                    <span style="font-size: 18px">熱量：${meal.mealCal}</span>
                    <br>
                    <span style="font-size: 18px">可能過敏原：${meal.mealAllergen}</span><br>
                    <div>
                    <c:forEach var="nutrientFeatureDetail" items="${meal.nutrientFeatureDetails}">
                    <a href="${ctxPath}/meal/mealController?action=hashtag&featureName=${nutrientFeatureDetail.featureName }"style="font-style: italic">#${nutrientFeatureDetail.featureName}&ensp;</a>
                    </c:forEach>
                    </div>
                 </div>
                 <div class="buy">          
                    <form method="post" action="${ctxPath}/cart/cartController" enctype="application/x-www-form-urlencoded" id="cart${meal.mealNo}">                   
                    <input name="action" value="cartAdd" type="hidden">
                    <input name="mealName" value="${meal.mealName}" type="hidden">
                    <input value="${meal.mealNo}" name="mealNo" type="hidden">
                    <input name="quantityCart" id="quantityCart" value="1" type="hidden">
                    
                    <span style="font-size: 18px">價格NT$${meal.mealPrice}</span><br>
                    <label style="font-size: 18px">請輸入購買數量：
                    <span id="amount_value_${loop.index}">1</span></label><br>
                    <input name="amount" type="range" min="1" max="99" value="1" id="amount${loop.index}">
                    <p>(若需調整份量，請進入商品頁面)</p>
                    </form>
	                <%--@elvariable id="collectionDetail" type="com.mealCollectionDetail.model.CollectionDetailVO"--%>
                    <form:form method="post" action="${ctxPath}/mealCollect/insert" enctype="application/x-www-form-urlencoded"
                     id="fromCollect${meal.mealNo}" modelAttribute="collectionDetail">
                    <input name="memberNo" value="${member.memberNo}" type="hidden">
                    <input name="mealNo" value="${meal.mealNo}" type="hidden">
                    <input name="mealName" value="${meal.mealName}" type="hidden">
                    </form:form>
                    <div>
                    <button type="submit" form="cart${meal.mealNo}" class="button">加入購物車</button>
                   	&nbsp;
                    <button type="submit" form="fromCollect${meal.mealNo}" class="button">加入收藏</button>
                    </div>
                  </div>
              </div>
              <hr>              
              </c:forEach>
              <br>
              <%@ include file="page2.jsp" %>
              <!--商品欄結束-->
      </div>
</div>
<script>
    $(document).ready(function () {
        <c:forEach var="meal" items="${meals}" varStatus="loop">
        $('#amount${loop.index}').mousemove(function () {
            $('#amount_value_${loop.index}').html($('#amount${loop.index}').val());
        });
        $('#amount${loop.index}').change(function () {
            $('#amount_value_${loop.index}').html($('#amount${loop.index}').val());
        });
        </c:forEach>
    });
</script>
</body>
</html>
