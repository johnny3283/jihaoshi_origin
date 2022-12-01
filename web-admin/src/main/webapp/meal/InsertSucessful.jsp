<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>Title</title>
<style>
#content {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.button {
	border-radius: 1rem;
	border: 1px solid #ccc;
}

#form {
	display: flex;
	justify-content: center;
	align-items: center;
	text-align: center;
}
</style>
</head>
<body>
	<%@ include file="../navbar.file"%>
	<br>
	<div id="content">
		<br>
		<div>
			<h2 style="font-size: 25px">${meal.mealName}新增成功，是否繼續新增商品營養特色？</h2>
		</div>
		<div id="form">
			<form method="post" action="${ctxPath}/nutrient/detailInsert" id="insert">
				<input type="hidden" value="${meal.mealName}" name="mealName">
				<input type="hidden" value="${meal.mealNo}" name="mealNo">				
			</form>
			<form method="post" action="mealController?action=findByprod&mealNo=${meal.mealNo}" id="no">
			</form>
			<button type="submit" form="insert" class="button">&nbsp;是&nbsp;</button>&nbsp;			
			<button type="submit" form="no" class="button">否(跳轉商品頁面)</button>
		</div>
	</div>
</body>
</html>