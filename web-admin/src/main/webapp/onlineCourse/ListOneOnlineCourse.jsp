<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.online_course.model.*"%>
    
<%
OnlineCourseVO onlinecourseVO = (OnlineCourseVO) request.getAttribute("onlinecourseVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>    
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>單筆線上課程資料 - listOneOnlineCourse.jsp</title>
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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>單筆線上課程資料 - listOneOnlineCourse.jsp</h3>
		 <h4><a href="searchAll">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		    
			<th>線上課程編號</th>
			<th>線上課程名稱</th>
			<th>線上課程時數</th>
			<th>線上課程師資</th>
			<th>線上課程簡介</th>
			<th>線上課程價格</th>
			<th>線上課程狀態</th>
			<th>評論人數</th>
			<th>評論分數</th>
	</tr>
	<tr>
		
		<td>${onlinecourseVO.courseNo}</td>
		<td>${onlinecourseVO.courseName}</td>
		<td>${onlinecourseVO.courseHr}</td>
		<td>${onlinecourseVO.courseTeacher}</td>
		<td>${onlinecourseVO.courseInfo}</td>
		<td>${onlinecourseVO.coursePrice}</td>
		<td>${onlinecourseVO.courseStatus}</td>
		<td>${onlinecourseVO.commentPeople}</td>
		<td>${onlinecourseVO.commentScore}</td>
	</tr>
</table>


</body>
</html>