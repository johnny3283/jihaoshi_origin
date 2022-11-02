<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="com.course.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  PhyCouVO phyCouVO = (PhyCouVO) request.getAttribute("phyCouVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
--<%= phyCouVO==null %>--${phyCouVO.course_no}--

<html>
<head>
<title>Course 資料 - listOneCou.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>course 資料 - ListOneCou.jsp</h3>
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
	<tr>
		<td><%=phyCouVO.getCourse_no()%></td>
		<td><%=phyCouVO.getCourse_name()%></td>
		<td><%=phyCouVO.getCourse_hr()%></td>
		<td><%=phyCouVO.getCourse_price()%></td>
		<td><%=phyCouVO.getCourse_teacher()%></td>
		<td><%=phyCouVO.getCourse_date()%></td>
		<td><%=phyCouVO.getCourse_location()%></td>
		<td><%=phyCouVO.getCourse_info()%></td>
		<td><%=phyCouVO.getCourse_status()%></td>
		<td><%=phyCouVO.getCreate_date()%></td>
		<td><%=phyCouVO.getUpdate_time()%></td>
		<td><%=phyCouVO.getSign_up_start_day()%></td>
		<td><%=phyCouVO.getSign_up_end_day()%></td>
		<td><%=phyCouVO.getMax_sign_up_people()%></td>
		<td><%=phyCouVO.getMin_sign_up_people()%></td>
		<td><%=phyCouVO.getCurrent_sign_up_people()%></td>
		<td><img src="http://localhost:8081/myproject/course/DBGifReader?course_no=<%=phyCouVO.getCourse_no()%>"></td>
		  
</table>

</body>
</html>