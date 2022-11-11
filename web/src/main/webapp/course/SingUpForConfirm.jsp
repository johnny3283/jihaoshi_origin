<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="com.course.model.*"%>

<%
  PhyCouVO phyCouVO = (PhyCouVO) request.getAttribute("phyCouVO");
%>

<html>
<head>

<title>報名確認</title>

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

  #pageHead {
    width: 600px;
    height: 24%;
  }

</style>

</head>
<body bgcolor='white'>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">
<table id="table-1">
	<tr><td>
		 <h3>請確認報名資訊 <a href="<%=request.getContextPath()%>/course/SignUpCouMgn.jsp"> <br>回首頁</a></h3>
	</td></tr>
</table>

<table>
	    <tr><th>course_no</th><td><%=phyCouVO.getCourse_no()%></td></tr>
		<tr><th>course_name</th><td><%=phyCouVO.getCourse_name()%></td></tr>
		<tr><th>course_hr</th><td><%=phyCouVO.getCourse_hr()%></td></tr>
		<tr><th>course_price</th><td><%=phyCouVO.getCourse_price()%></td></tr>
		<tr><th>course_teacher</th><td><%=phyCouVO.getCourse_teacher()%></td></tr>
		<tr><th>course_date</th><td><%=phyCouVO.getCourse_date()%></td></tr>
		<tr><th>course_location</th><td><%=phyCouVO.getCourse_location()%></td></tr>
		<tr><th>pic</th><td><img src="http://localhost:8081/myproject/course/DBGifReader?course_no=<%=phyCouVO.getCourse_no()%>"></td></tr>
		<tr><td>		
			   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/signup/cou.do" style="margin-bottom: 0px;">
			     <input type="submit" value="確認報名">
			     <input type="hidden" name="course_no"  value="<%=phyCouVO.getCourse_no()%>">
			     <input type="hidden" name="member_no"  value="1">
			     <input type="hidden" name="order_price"  value="<%=phyCouVO.getCourse_price()%>">
			     <input type="hidden" name="action"	value="confirm"></FORM>
			</td></tr>
</table>

</body>
</html>