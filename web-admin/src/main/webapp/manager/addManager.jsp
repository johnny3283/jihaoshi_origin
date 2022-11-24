<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>

<%
ManagerVO ManagerVO = (ManagerVO) request.getAttribute("ManagerVO");
%>

<html>
<head>

<title>sign up</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/css/form.css" rel="stylesheet">

</head>
<body >
<div class="wrapper fadeInDown">
        <div id="formContent">
            <!-- Tabs Titles -->
            <h2 class="active"> Sign Up </h2><br>


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="ManagerServlet" name="form1">
		<h3 id="form-title">新增管理員:</h3>

			
				
				<input type="TEXT" name="managerAccount" size="45"
					class="fadeIn second" placeholder="account"
					value="<%=(ManagerVO == null) ? "" : ManagerVO.getManagerAccount()%>" />
				
				<input type="password" name="managerPassword" size="45"
					class="fadeIn second" placeholder="password"
					value="<%=(ManagerVO == null) ? "" : ManagerVO.getManagerPassword()%>" />
					
				<input type="TEXT" name="managerName" size="45"
					class="fadeIn second" placeholder="name"
					value="<%=(ManagerVO == null) ? "" : ManagerVO.getManagerName()%>" />
				<input type="TEXT" name="managerIp" size="45"
					class="fadeIn second" placeholder="Ip"
					value="<%=(ManagerVO == null) ? "" : ManagerVO.getManagerIp()%>" />
				
				
			
				<input type="hidden" name="action" value="insert">
					<input type="submit" value="新增" id="submit">
			

	</FORM>
	</div>
	</div>
</body>
<script>
	
</script>
</html>