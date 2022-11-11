<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.online_course.model.*"%>

<%
OnlineCourseVO onlinecourseVO = (OnlineCourseVO) request.getAttribute("onlinecourseVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>線上課程資料修改 - update_onlinecourse_input.jsp</title>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>線上課程資料修改 - update_onlinecourse_input.jsp</h3>
		 <h4><a href="searchAll">回首頁</a></h4>
	</td></tr>
</table>

<h3>線上課程資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="onlineCourse" name="form1"  enctype="multipart/form-data">
<input  type="hidden" name="courseNo" size="45" value= "${onlinecourseVO.courseNo}" />
<table>

<tr>
		<td>線上課程編號:</td>
		<td>${onlinecourseVO.courseNo}</td>
		
	</tr>
<tr>
		<td>線上課程名稱:</td>
		<td><input type="TEXT" name="courseName" size="45" value="${onlinecourseVO.courseName}" /></td>
	</tr>
	<tr>
		<td>線上課程時數:</td>
		<td><input type="TEXT" name="courseHr" size="45" value="${onlinecourseVO.courseHr}" /></td>
	</tr>
	<tr>
		<td>線上課程師資:</td>
		<td><input type="TEXT" name="courseTeacher" size="45"	value="${onlinecourseVO.courseTeacher}" /></td>
	</tr>
	<tr>
		<td>線上課程簡介:</td>
		<td><input type="TEXT" name="courseInfo" size="45"	value="${onlinecourseVO.courseInfo}" /></td>
	</tr>
	<tr>
		<td>線上課程價格:</td>
		<td><input type="TEXT" name="coursePrice" size="45"	value="${onlinecourseVO.coursePrice}" /></td>
	</tr>
	
	<tr>
		<td>評論人數:</td>
		<td><input type="TEXT" name="commentPeople" size="45" value="${onlinecourseVO.commentPeople}" /></td>
	</tr>
	<tr>
		<td>評論分數:</td>
		<td><input type="TEXT" name="commentScore" size="45" value="${onlinecourseVO.commentScore}" /></td>
	</tr>
	
		<tr>
		<td>上架狀態:</td>
		<td><input type="radio" name="courseStatus"  value="0" ${(onlinecourseVO.courseStatus==0)? 'checked':'' }/> 上架
			 <input type="radio" name="courseStatus"  value="1"${(onlinecourseVO.courseStatus==1)? 'checked':'' }/>下架
		</td>
	</tr>

  <tr>
		<td>圖片上傳</td><br>
		<td><input type="file" name="photo"></td>
		</tr>

	<jsp:useBean id="deptSvc" scope="page" class="com.online_course.model.OnlineCourseService" />

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="courseno" value="${onlinecourseVO.courseNo}">
<input type="submit" value="送出修改"></FORM>
</body>




</html>