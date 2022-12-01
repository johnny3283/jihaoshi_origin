<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>營養特色新增</title>
<style>
.errors {
	color: red;
}
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
}
</style>
</head>

<body>
	<%@ include file="../navbar.file"%>
	<br>
	<div id="content">
		<form:form method="post" action="${ctxPath}/nutrient/insert"
			enctype="application/x-www-form-urlencoded" id="formNutrient"
			modelAttribute="nutrientFeature">
			<div id="form">
			<label>營養特色：</label>
			<form:input type="text" name="featureName" path="featureName" />&nbsp;
			<button type="submit" form="formNutrient" class="button">新增</button>
			<form:errors path="featureName" cssClass="errors" />
			<br>
			</div>
		</form:form>
	</div>
</body>
</html>
