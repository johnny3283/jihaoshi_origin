<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO"%>
<%@ page import="java.util.Set"%>
<%@ page import="com.phyCouPromotion.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
   Integer project_no = (Integer) request.getAttribute("project_no");
   Integer course_no = (Integer) request.getAttribute("course_no");
   Integer prom_price = (Integer) request.getAttribute("prom_price");
   
/*    pageContext.setAttribute("sCous",sCous);
   pageContext.setAttribute("prom_price",prom_price); */
  
  
%>
<html>
<head>
<title>促銷專案資料</title>

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
	width: 1000px;
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
    width: 1000px;
    height: 23%;
  }
</style>

</head>
<body bgcolor='white'>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">

<table id="table-1">
	<tr><td>
		 <h3>促銷專案明細資料</h3>
		 <h4><a href="<%= request.getContextPath()%>/phyCouPromotion/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>促銷專案編號</th>

		<th>促銷課程編號</th>

		<th>促銷折扣</th>	
	</tr>
	<tr>
		<td>${project_no}</td>

		<td>${course_no}</td>

		<td>${prom_price}</td>
</table>

</body>
</html>
