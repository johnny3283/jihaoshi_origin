<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    PhyCouService phyCouSvc = new PhyCouService();
    List<PhyCouVO> list = phyCouSvc.getAll();
    pageContext.setAttribute("list",list);
    
%>


<html>
<head>
<title>所有course資料 - listAllCou.jsp</title>

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
	width: 800px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有course資料 - listAllCou.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>course_no</th>
		<th>course_name</th>
		<th>course_hr</th>
		<th>course_price</th>
		<th>course_teacher</th>
		<th>course_date</th>
		<th>course_location</th>
		<th>course_info</th>
		<th>course_status</th>
		<th>create_date</th>
		<th>update_time</th>
		<th>sign_up_start_day</th>
		<th>sign_up_end_day</th>
		<th>max_sign_up_people</th>
		<th>min_sign_up_people</th>
		<th>current_sign_up_people</th>
		<th>pic</th>
		
	</tr>
	<%-- <%@ include file="page1.file" %>  --%> 
	<c:forEach var="phyCouVO" items="${list}"> 
		
		<tr>
			<td>${phyCouVO.course_no}</td>
			<td>${phyCouVO.course_name}</td>
			<td>${phyCouVO.course_hr}</td>
			<td>${phyCouVO.course_price}</td>
			<td>${phyCouVO.course_teacher}</td>
			<td>${phyCouVO.course_date}</td> 
			<td>${phyCouVO.course_location}</td>
			<td>${phyCouVO.course_info}</td>
			<td>${phyCouVO.course_status}</td>
			<td>${phyCouVO.create_date}</td>
			<td>${phyCouVO.update_time}</td>
			<td>${phyCouVO.sign_up_start_day}</td>
			<td>${phyCouVO.sign_up_end_day}</td>
			<td>${phyCouVO.max_sign_up_people}</td>
			<td>${phyCouVO.min_sign_up_people}</td>
			<td>${phyCouVO.current_sign_up_people}</td>
			<td><img src="<%=request.getContextPath()%>/course/DBGifReader?course_no=${phyCouVO.course_no}" width="100px"></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course/cou.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="course_no"  value="${phyCouVO.course_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course/cou.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="course_no"  value="${phyCouVO.course_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>