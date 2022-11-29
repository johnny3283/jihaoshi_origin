<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.phyCouPromotionDetail.model.*"%>

<%
    PhyCouService phyCouSvc = new PhyCouService();
    List<PhyCouVO> list = phyCouSvc.getCanSignUp();
 /*    PhyCouPromotionDetailService detailSvc = new PhyCouPromotionDetailService(); */
    
   
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有實體課程資料</title>

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
	width: 1500px;
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
    width:450px;
  }
  #pageHead {
    width: 2000px;
    height: 30%;
  }
</style>

</head>
<body bgcolor='white'>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">

<table id="table-1">
	<tr><td>
		 <h3>所有實體課程開課資料<a href="SignUpCouMgn.jsp"> <br>回首頁</a></h3>
	</td></tr>
</table>

<table>
	<tr>
		<th>報名欄</th>
		<th>課程編號</th>
		<th>諌程名稱</th>
		<th>上課時數</th>
		<th>原價</th>
		<th>促銷價格</th>
		<th>授課老師</th>
		<th>開課日期</th>
		<th>上課地點</th>
		<th id="info">課程簡介</th>
		<th>課程狀態</th>
		<th>報名開始日期</th>
		<th>報名結束日期</th>
		<th>人數上限</th>
		<th>最少開課人數</th>
		<th>目前報名人數</th>
		<th>照片</th>		
	</tr>
	<%-- <%@ include file="page1.file" %>  --%> 
	<jsp:useBean id="detailSvc" class="com.phyCouPromotionDetail.model.PhyCouPromotionDetailService" />
	<c:forEach var="phyCouVO" items="${list}"> 

		<tr>
			<td>
			   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/signup/cou.do" style="margin-bottom: 0px;">
			     <input type="submit" value="報名">
			     <input type="hidden" name="course_no"  value="${phyCouVO.course_no}">
			     <input type="hidden" name="dPrice"  value="${(detailSvc.getMinPrice(phyCouVO.getCourse_no()) * phyCouVO.getCourse_price())/100}">
			     <input type="hidden" name="phyCouVO.current_sign_up_people"  value="${phyCouVO.current_sign_up_people}">
			     <input type="hidden" name="action"	value="apply"></FORM>
			</td>		
			<td>${phyCouVO.course_no}</td>
			<td>${phyCouVO.course_name}</td>
			<td>${phyCouVO.course_hr}</td>
			<td>${phyCouVO.course_price}</td>
			<%-- <td>${(detailSvc.getMinPrice(phyCouVO.getCourse_no()) * phyCouVO.getCourse_price())/100}</td> --%>
			<td><fmt:formatNumber 
                value="${(detailSvc.getMinPrice(phyCouVO.getCourse_no()) * phyCouVO.getCourse_price())/100}" 
                maxFractionDigits="0"/></td>
			<td>${phyCouVO.course_teacher}</td>
			<td>${phyCouVO.course_date}</td> 
			<td>${phyCouVO.course_location}</td>
			<td>${phyCouVO.course_info}</td>
			<td>${phyCouVO.course_status}</td>
			<td>${phyCouVO.sign_up_start_day}</td>
			<td>${phyCouVO.sign_up_end_day}</td>
			<td>${phyCouVO.max_sign_up_people}</td>
			<td>${phyCouVO.min_sign_up_people}</td>
			<td>${phyCouVO.current_sign_up_people}</td>
			<td><img src="<%=request.getContextPath()%>/signup/DBGifReader?course_no=${phyCouVO.course_no}" width="100px"></td>
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>
<script>


</script>

</body>
</html>