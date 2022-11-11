<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Course : Home</title>
<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>Online Course : Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for Online Course : Home</p>

	<h3>線上課程資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a
			href="${pageContext.request.contextPath}/OnlineCourse/searchAll">List</a>
			all Online Courses. <br>
		<br></li>


		<li>
			<FORM METHOD="post"
				ACTION="${pageContext.request.contextPath}/OnlineCourse">
				<b>輸入線上課程編號 (如1):</b> <input type="text" name="courseNo"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="onlinecourseSvc" scope="page"
			class="com.online_course.model.OnlineCourseService" />

		<li>
			<%--      <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/OnlineCourse" > --%>
			<!--        <b>選擇課程名稱:</b> --> <!--        <select size="1" name="courseNo"> -->
			<%--          <c:forEach var="onlinecourseVO" items="${onlinecourseSvc.all}" >  --%>
			<%--           <option value="${onlinecourseVO.courseNo}">${onlinecourseVO.courseName} --%>
			<%--          </c:forEach>    --%> <!--        </select> --> <!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
			<!--        <input type="submit" value="送出"> --> <!--     </FORM> -->

			<form action="${pageContext.request.contextPath}/OnlineCourse/search"
				method="POST">
				<b>輸入課程名稱:</b> <input name="courseName"> <input
					type="submit" value="送出">
			</form>
		</li>


	</ul>


	<h3>線上課程管理</h3>

	<ul>
		<li><a href='addOnlineCourse.jsp'>Add</a> a new Online Course.</li>
	</ul>


</body>
</html>