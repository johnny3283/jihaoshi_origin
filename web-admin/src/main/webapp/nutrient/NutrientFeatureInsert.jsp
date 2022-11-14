<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>營養特色新增</title>
    <style>
        .errors {
            color: red;
        }
    </style>
</head>

<body>
<form:form method="post" action="${ctxPath}/nutrient/insert"
           enctype="application/x-www-form-urlencoded"
           id="formNutrient" modelAttribute="nutrientFeature">
    <label>營養特色：</label><form:input type="text" name="featureName" path="featureName"/>
    <button type="submit" form="formNutrient">新增</button>
    <form:errors path="featureName" cssClass="errors"/><br>

</form:form>
</body>
</html>
