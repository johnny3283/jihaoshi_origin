<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>Title</title>
<style type="text/css">
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
		<form method="post" action="detailSave" id="detailInsert">
			<input type="hidden" name="mealNo" id="mealNo${param.mealNo}" value="${param.mealNo}">
			<c:forEach var="nutrientFeature" items="${nutrientFeatures}" varStatus="loop">
				<input type="checkbox" name="featureNo" id="feature${loop.count}" value="${loop.count}">
				<label for="feature${loop.count}">${nutrientFeature.featureName}</label>
				<br>
			</c:forEach>
		</form>
		<button type="submit" form="detailInsert" class="button">確定新增</button>
	</div>

</body>
</html>
