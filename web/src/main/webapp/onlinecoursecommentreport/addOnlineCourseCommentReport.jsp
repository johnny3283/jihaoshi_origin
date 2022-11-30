<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.onlinecoursecommentreport.model.*"%>
<html>
<head>
<title>線上課程評論檢舉</title>
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
    border-radius:0.6rem; 
    border: 3px solid #ccc;
    width:450px;
    height:500px;
    border-style:outset;
}
</style>
</head>
<body>
<%@ include file="../navbar.file" %>
	<br>
	<div id="content">
	<br>
	<div>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font><br>
		<c:forEach var="message" items="${errorMsgs}">
			<span style="color:red">${message}</span><br>
		</c:forEach>
		</c:if>
	</div>	
	<br>
	
	<form method="post"	action="<%=request.getContextPath()%>/OnlineCourseCommentReportServlet" id="addOnlineCourseCommentReport" style="margin: 0px auto;">
	<div id="form">		
		<div style="margin: 0px auto;">
		<label>線上課程編號:${param.commentNo}</label>
		<input type="hidden" name="commentNo" value="${param.commentNo}">
		</div>
		<br>
		<div style="margin: 0px auto;">
		<label>線上課程評論檢舉原因：</label><br>
		</div>
		<br>
		<div style="margin: 0px auto;">
		<textarea name="reportReason"></textarea>
		</div>
		<br><br> 
		<div style="margin: 0px auto;">
		<input type="hidden" name="action" value="add">
		<input type="submit" name="送出資料" form="addOnlineCourseCommentReport" onclick="confirm(`確定送出?`)" style="border-radius:1rem; border: 1px solid #ccc;"> 
		<input type="reset" name="清除資料" form="addOnlineCourseCommentReport" style="border-radius:1rem; border: 1px solid #ccc;">
		</div>
	</div>
	</form>							
</div>					
	<script type="text/javascript">		
	</script>
</body>
</html>
