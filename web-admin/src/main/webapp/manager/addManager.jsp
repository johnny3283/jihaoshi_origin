<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.employee.model.*" %>

<%
    EmployeeVO empVO = (EmployeeVO) request.getAttribute("employeeVO");
%>

<html>
<head>

    <title>sign up</title>
    <style>
        #content {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }

        #form {
            display: flex;
            justify-content: center;
            flex-direction: column;
            border-radius: 0.6rem;
            border: 3px solid #ccc;
            width: 600px;
            height: 300px;
            border-style: outset;
        }
    </style>

</head>
<body>
<%@ include file="../navbar.file" %>
<br>
<c:if test="${not empty errorMsgs}">
    <font style="color: red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color: red">${message}</li>
        </c:forEach>
    </ul>
</c:if>
<div id="content">

    <FORM METHOD="post" ACTION="EmployeeServlet" name="form1">
        <div id="form">
            <h2 class="active">新增員工:</h2>
            <div style="margin: 0 auto;">員工帳號:
                <label>
                    <input type="TEXT" name="managerAccount" size="45"
                           value="<%=(empVO == null) ? "" : empVO.getManagerAccount()%>"/>
                </label></div>
            <div style="margin: 0 auto;">員工密碼:
                <label>
                    <input type="password" name="managerPassword" size="45"
                           value="<%=(empVO == null) ? "" : empVO.getManagerPassword()%>"/>
                </label></div>
            <div style="margin: 0 auto;">員工姓名:
                <label>
                    <input type="TEXT" name="managerName" size="45"
                           value="<%=(empVO == null) ? "" : empVO.getManagerName()%>"/>
                </label></div>
            <div style="margin: 0 auto;">
                <input type="hidden" name="action" value="insert">
                <input type="submit" value="新增" id="submit" style="border-radius:1rem; border: 1px solid #ccc;">
            </div>
        </div>
    </FORM>
</div>
</body>

</html>