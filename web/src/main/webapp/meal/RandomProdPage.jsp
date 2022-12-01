<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meal.model.MealVO"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
List<MealVO> meals = (List<MealVO>) request.getAttribute("meals");
%>

<html>
<head>
<title>自動配餐</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<style type="text/css">
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
}
#box {
	width: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
}
.prod {
	width: 90%;
	display: flex;
	justify-content: Space-around;
	align-items: center;
	border-style: outset;
}
.button {
 	border-radius:1rem;
 	border: 1px solid #ccc;
}
.img {
	width:80%;
}
.pic {
	padding-left: 10px;
	width:30%;
}
.info {
	width:70%;
	padding-bottom: 40px;
	padding-top: 20px;
}
</style>
</head>
<body>
<%@ include file="../navbar.file" %>
	<br>
	<div id="searchArea"
		style="margin: 0px auto; display: flex; justify-content: center; align-items: center;">
		<!--搜尋欄開始-->
		<div id="search">
			<form method="post" class="searchfield"
				action="${ctxPath}/meal/mealController"
				enctype="application/x-www-form-urlencoded" id="searceKeyword">
				<input name="action" value="nameKeywordSearch" type="hidden">
				<input id="keyword" type="text" class="text ac_input"
					name="nameKeyword" placeholder="請輸入關鍵字">
				<button type="submit" form="searceKeyword"
					style="border-radius: 1rem; border: 1px solid #ccc;">查找商品</button>
			</form>
		</div>
		<!--搜尋欄結束-->
	</div>
	<br>
	<div id="CONTENT">
		<!--商品欄開始-->
		<form method="post" action="${ctxPath}/cart/cartController" enctype="application/x-www-form-urlencoded" id="goCart">
		<div id="box">
			<c:forEach var="meal" items="${meals}" varStatus="loop">
				<div class="prod">
					<div class="pic">
						<a href="${ctxPath}/meal/mealController?action=findByprod&mealNo=${meal.mealNo}">
							<img class="img" src="${not empty meal.showPhoto? meal.showPhoto:"../images/noImg.jpg"}">
						</a>
					</div>
					<div class="info">						
						<h5 class="prod_name">
							<a href="${ctxPath}/meal/mealController?action=findByprod&mealNo=${meal.mealNo}">${meal.mealName}</a>
						</h5>
						<br> <span style="font-size: 18px">簡易食譜：${meal.mealRecipe}</span>
						<br> <span style="font-size: 18px">熱量：${meal.mealCal}</span>
						<br> <span style="font-size: 18px">可能過敏原：${meal.mealAllergen}</span>
						<br> <span style="font-size: 18px">價格NT$${meal.mealPrice}</span>
						<br>
						<br>
					</div>
				</div>
				<input type="hidden" name="mealNos" value="${meal.mealNo}">
			</c:forEach>
			<input type="hidden" name="action" value="randomAdd" >
		</div>
		</form>
		<div style="font-size: 18px; text-align: left">
			<label>商品總價為：</label>${totalPrice}元，是否需要重新幫您配餐？

			<button type="submit" form="goCart" class="button">否，請幫我加入購物車</button>
			<button type="button" id="reAsssign" class="button" >是</button>
			<div id="reAssignArea" hidden>
				<form method="post" action="${ctxPath}/meal/mealController" enctype="application/x-www-form-urlencoded" id="formRandom">
					<input type="hidden" name="action" value="randomAssign" >
					<label>餐點份數：</label>
					<span id="amount_value" style="font-size: 18px">${param.mealAmount}</span>
					<br>
					<input type="range" value="${param.mealAmount}" min="1" max="10" name="mealAmount" id="mealAmount">
					<br> 
					<label>是否接受重複餐點：</label>
					<label for="yes">是</label>
					<input type="radio" name="repeat" value="yes" id="yes" checked> 
					<label for="no">否</label><input type="radio" name="repeat" value="no" id="no">
					<button type="submit" form="formRandom">隨機配餐</button>
				</form>
			</div>
		</div>
</div>
	<script>

		$(document).ready(function() {

			$('#reAsssign').click(function() {
				$('#reAssignArea').removeAttr("hidden");

			});
			$('#mealAmount').mousemove(function() {
				$('#amount_value').html($('#mealAmount').val());
			});
			$('#mealAmount').change(function() {
				$('#amount_value').html($('#mealAmount').val());
			});
			$('#mealAmount').click(function() {
				$('#amount_value').html($('#mealAmount').val());
			});

		});
	</script>
</body>

</html>
