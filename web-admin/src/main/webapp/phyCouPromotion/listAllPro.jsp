<%@page import="com.phyCouPromotion.model.PhyCouPromotionService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.phyCouPromotion.model.*"%>

<%
    PhyCouPromotionService phyCouPromotionSvc = new PhyCouPromotionService();
    List<PhyCouPromotionVO> list = phyCouPromotionSvc.getAll();
    pageContext.setAttribute("list",list);
    
%>


<html>
<head>
<title>所有促銷資料</title>

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
	width: 1100px;
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
    width: 1100px;
    height: 28%;
  }
</style>

</head>
<body bgcolor='white'>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">

<table id="table-1">
	<tr><td>
		 <h3>所有促銷資料 <a href="select_page.jsp"> ~回首頁</a></h3> 
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
	<%-- <%@ include file="page1.file" %>  --%> 
	<c:forEach var="phyCouPromotionVO" items="${list}"> 
		
		<tr>
			<td>${phyCouPromotionVO.project_no}</td>
			<td>${phyCouPromotionVO.project_name}</td>
			<td>${phyCouPromotionVO.start_date}</td>
			<td>${phyCouPromotionVO.end_date}</td>
			<td>${phyCouPromotionVO.prom_description}</td>
			<td>${phyCouPromotionVO.prom_status}</td> 
			<td>${phyCouPromotionVO.update_time}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/phyCouPromotion/promotion" style="margin-bottom: 0px;">
			     <input type="submit" value="修改主檔">
			     <input type="hidden" name="project_no"  value="${phyCouPromotionVO.project_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/phyCouPromotion/promotion" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除整個專案">
			     <input type="hidden" name="project_no"  value="${phyCouPromotionVO.project_no}">
<%-- 			     <input type="hidden" name="course_no"  value="${phyCouPromotionDetailVO.phyCouVO.course_no}"> --%>
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			<td> 
				<a href="listAllProDetail.jsp">修改明細</a>
			</td>
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>
