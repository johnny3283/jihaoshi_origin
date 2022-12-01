<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meal.model.MealVO"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
List<MealVO> meals = (List<MealVO>) request.getAttribute("meals");
%>

<html>
<head>
<title>產品清單</title>
<style>
#search {
	border-style: double;
	border-color: #ecb714;
	border-radius: 10px;
	width: 300px;
	height: 50px;
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

.product {
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 0px 10px;
}

.img {
	width: 60%;
	height: 80%;
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
	border-radius: 1rem;
	border: 1px solid #ccc;
}
</style>
</head>
<body>
	<%@ include file="../navbar.file"%>
	<br>
	<div id="searchArea"
		style="margin: 0px auto; display: flex; justify-content: center; align-items: center;">
		<!--搜尋欄開始-->
		<div id="search">
			<form method="post" class="searchfield"
				action="${ctxPath}/meal/mealController"
				enctype="application/x-www-form-urlencoded" id="searceKeyword">
				<input name="action" value="nameKeywordSearch" hidden> <input
					id="keyword" type="text" class="text ac_input" name="nameKeyword"
					placeholder="請輸入關鍵字">
				<button type="submit" form="searceKeyword" class="button">查找商品</button>
			</form>
		</div>
		<!--搜尋欄結束-->
	</div>
	<br>
	<div id="CONTENT">
		<!--商品欄開始-->
		<div style="margin: 0px auto;">
			<%@ include file="page1.jsp"%>
		</div>
		<br>
		<c:forEach var="meal" items="${meals}" begin="<%= pageIndex %>"
			end="<%= pageIndex+rowsPerPage-1 %>">
			<div class="product">
				<div class="pic">
					<a class="prod_img"
						href="mealController?action=findByprod&mealNo=${meal.mealNo}">
						<img class="img" src="${meal.showPhoto}">
					</a>
				</div>
				<div class="info">
					<h5 class="prod_name">
						<a href="mealController?action=findByprod&mealNo=${meal.mealNo}">${meal.mealName}</a>
					</h5>
					<br> <span style="font-size: 18px">${meal.mealRecipe}</span> <br>
					<span style="font-size: 18px">狀態：${launchStatus[meal.launch]}</span>
					<br>
					<div>
						<c:forEach var="nutrientFeatureDetail"
							items="${meal.nutrientFeatureDetails}">
							<a
								href="${ctxPath}/meal/mealController?action=hashtag&featureName=${nutrientFeatureDetail.featureName }"
								style="font-style: italic">#${nutrientFeatureDetail.featureName}&ensp;</a>
						</c:forEach>
					</div>
				</div>
				<div class="buy">
					<span style="font-size: 18px">價格NT$${meal.mealPrice}</span>
					<form method="post" action="mealController?action=toUpdate"
						enctype="application/x-www-form-urlencoded"
						id="edit${meal.mealNo}">
						<input type="hidden" value="${meal.mealNo}" name="mealNo">

					</form>
					<br>
					<form method="post" action="mealController"
						enctype="application/x-www-form-urlencoded"
						id="launch${meal.mealNo}">
						<input type="hidden" name="mealNo" value="${meal.mealNo}">
						<input type="hidden" name="launch" value="${meal.launch eq 0?1:0}">
						<input type="hidden" name="action" value="launch">
					</form>
					<button type="submit" form="edit${meal.mealNo}">修改商品</button>
					<button type="submit" form="launch${meal.mealNo}"
						class="launchSwitch">${meal.launch eq 0?"上架":"下架"}</button>
				</div>
				<hr>
		</c:forEach>
		<br>
		<%@ include file="page2.jsp"%>
		<!--商品欄結束-->
	</div>
	</div>
	<script></script>
</body>
</html>
