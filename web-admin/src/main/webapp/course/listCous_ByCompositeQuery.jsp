<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.course.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listCous_ByCompositeQuery" scope="request" type="java.util.List<PhyCouVO>" />
<jsp:useBean id="deptSvc" scope="page" class="com.course.model.PhyCouService" />


<html>
<head><title>複合查詢 - listCous_ByCompositeQuery.jsp</title>

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

<h4>
☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br>
☆此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有course資料 - listAllCou.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
			<td><img src="<%=request.getContextPath()%>/course/DBGifReader?course_no=${phyCouVO.course_no}"></td>
			<td>


	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>職位</th>
		<th>雇用日期</th>
		<th>薪水</th>
		<th>獎金</th>
		<th>部門</th>
	</tr>
	<c:forEach var="empVO" items="${listEmps_ByCompositeQuery}">
		<tr align='center' valign='middle'>
			<td>${empVO.empno}</td>
			<td>${empVO.ename}</td>
			<td>${empVO.job}</td>
			<td>${empVO.hiredate}</td>
			<td>${empVO.sal}</td>
			<td>${empVO.comm}</td>			
			<td><c:forEach var="deptVO" items="${deptSvc.all}">
                    <c:if test="${empVO.deptno==deptVO.deptno}">
	                    ${deptVO.deptno}【${deptVO.dname} - ${deptVO.loc}】
                    </c:if>
                </c:forEach>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>