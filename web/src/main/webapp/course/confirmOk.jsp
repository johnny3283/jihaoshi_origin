<%@ page import="com.signupcourse.model.PhyCouSignUpVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="com.course.model.*"%>


<%
/*   PhyCouVO phyCouVO = (PhyCouVO) request.getAttribute("phyCouVO");  */
  PhyCouSignUpVO phyCouSignUpVO = (PhyCouSignUpVO) request.getAttribute("phyCouSignUpVO"); 
  PhyCouService phyCouSvc = new PhyCouService();
  PhyCouVO phyCouVO = phyCouSvc.getOneCou(phyCouSignUpVO.getCourse_no());
  
/*   
  pageContext.setAttribute("phyCouSignUpVO", phyCouSignUpVO);
  pageContext.setAttribute("phyCouVO", phyCouVO); */

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
  h1 {
    color: red;
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
<img src="<%=request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">

<table id="table-1">
	<tr><td>
		 <h1>報名成功  ！！<a href="<%=request.getContextPath()%>/course/SignUpCouMgn.jsp"> <br>回首頁</a></h1>
	</td></tr>
</table>

<table>
		<tr><th>訂單編號</th><td><%=phyCouSignUpVO.getOrder_no()%></td></tr>
	    <tr><th>課程編號</th><td><%=phyCouVO.getCourse_no()%></td></tr>
		<tr><th>課程名稱</th><td><%=phyCouVO.getCourse_name()%></td></tr>
		<tr><th>上課時數</th><td><%=phyCouVO.getCourse_hr()%></td></tr>
		<tr><th>課程費用</th><td><%=phyCouSignUpVO.getOrder_price()%></td></tr>
		<tr><th>授課老師</th><td><%=phyCouVO.getCourse_teacher()%></td></tr>
		<tr><th>上課日期</th><td><%=phyCouVO.getCourse_date()%></td></tr>
		<tr><th>上課地點</th><td><%=phyCouVO.getCourse_location()%></td></tr>
</table>

</body>
</html>