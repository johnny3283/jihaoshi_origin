<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="com.phyCouPromotion.model.*"%>

<%
  PhyCouPromotionVO phyCouPromotionVO = (PhyCouPromotionVO) request.getAttribute("phyCouPromotionVO"); 
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
	width: 1700px;
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
  #info {
    width:400px;
  }
  #pageHead {
    width: 1700px;
    height: 23%;
  }
</style>

</head>
<body bgcolor='white'>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">

<table id="table-1">
	<tr><td>
		 <h3>促銷專案資料</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>促銷專案編號</th>
		<th>促銷專案名稱</th>
		<th>開始日期</th>
		<th>結束日期</th>
		<th>促銷活動敘述</th>
		<th>促銷活動狀態</th>
		<th>編輯時間</th>
	
	</tr>
	<tr>
		<td><%=phyCouPromotionVO.getProject_no()%></td>
		<td><%=phyCouPromotionVO.getProject_name()%></td>
		<td><%=phyCouPromotionVO.getStart_date()%></td>
		<td><%=phyCouPromotionVO.getEnd_date()%></td>
		<td><%=phyCouPromotionVO.getProm_description()%></td>
		<td><%=phyCouPromotionVO.getProm_status()%></td>
		<td><%=phyCouPromotionVO.getUpdate_time()%></td>
</table>

</body>
</html>
