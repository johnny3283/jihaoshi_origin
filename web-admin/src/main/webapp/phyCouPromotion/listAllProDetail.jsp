<%@ page import="com.phyCouPromotionDetail.model.PhyCouPromotionDetailService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.phyCouPromotionDetail.model.*"%>

<%
/*     PhyCouPromotionDetailService phyCouPromotionDetailSvc = new PhyCouPromotionDetailService();
    List<PhyCouPromotionDetailVO> list = phyCouPromotionDetailSvc.getAll();
    pageContext.setAttribute("list",list); */
    
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
		 <h3>所有促銷明細資料 <a href="<%= request.getContextPath()%>/phyCouPromotion/select_page.jsp"> ~回課程促銷首頁</a></h3> 
	</td></tr>
</table>

<table>
	<tr>
		<th>促銷專案編號</th>
		<th>促銷專案名稱</th>
		<th>促銷課程編號</th>
		<th>促銷課程名稱</th>
		<th>促銷折扣</th>

	</tr>
	<%-- <%@ include file="page1.file" %>  --%> 
	<jsp:useBean id="detailSvc" class="com.phyCouPromotionDetail.model.PhyCouPromotionDetailService"></jsp:useBean>
	<c:forEach var="phyCouPromotionDetailVO" items="${detailSvc.all}"> 
		
		<tr>
			<td>${phyCouPromotionDetailVO.phyCouPromotionVO.project_no}</td>
			<td>${phyCouPromotionDetailVO.phyCouPromotionVO.project_name}</td>
			<td>${phyCouPromotionDetailVO.phyCouVO.course_no}</td>
			<td>${phyCouPromotionDetailVO.phyCouVO.course_name}</td>
			<td>${phyCouPromotionDetailVO.prom_price}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/phyCouPromotionDetail/promotionDetail" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="project_no"  value="${phyCouPromotionDetailVO.phyCouPromotionVO.project_no}">
			     <input type="hidden" name="course_no"  value="${phyCouPromotionDetailVO.phyCouVO.course_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/phyCouPromotionDetail/promotionDetail" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除該課程">
			     <input type="hidden" name="project_no"  value="${phyCouPromotionDetailVO.phyCouPromotionVO.project_no}">
			     <input type="hidden" name="course_no"  value="${phyCouPromotionDetailVO.phyCouVO.course_no}">
			     <input type="hidden" name="action" value="deleteOneCou"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/phyCouPromotionDetail/promotionDetail" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除整個專案">
			     <input type="hidden" name="project_no"  value="${phyCouPromotionDetailVO.phyCouPromotionVO.project_no}">
			     <input type="hidden" name="course_no"  value="${phyCouPromotionDetailVO.phyCouVO.course_no}">
			     <input type="hidden" name="action" value="deleteOnePro"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>
