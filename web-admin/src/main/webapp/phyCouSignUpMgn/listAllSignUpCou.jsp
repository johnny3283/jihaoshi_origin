<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.signupcourse.model.*"%>
<%@ page import="com.signupcourse.model.*"%>
<%@ page import="com.mem.model.MemberVO"%>


<%
    PhyCouSignUpService phyCouSignUpSvc = new PhyCouSignUpService();
	List<PhyCouSignUpVO> list = phyCouSignUpSvc.getAll();
    pageContext.setAttribute("list",list);

%>


<html>
<head>
<title>所有已報名實體課程</title>

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
	width: 950px;
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
    width: 950px;
    height: 24%;
  }
</style>

</head>
<body bgcolor='white'>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">

<table id="table-1">
	<tr><td>
		 <h3>所有已報名實體課程<a href="<%=request.getContextPath()%>/index.jsp"> <br>回首頁</a></h3>
	</td></tr>
</table>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">錯誤!!</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<table>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>日期</th>
		<th>訂單狀態</th>
		<th>課程編號</th>
		<th>課程名稱</th>
		<th>上課日期</th>
		<th>訂單價格</th>
		<th>授課老師</th>
		<th></th>

	</tr>
	<%-- <%@ include file="page1.file" %>  --%> 
	<c:forEach var="phyCouSignUpVO" items="${list}"> 
		
		<tr>
		
			<td>${phyCouSignUpVO.order_no}</td>
			<td>${phyCouSignUpVO.member_no}</td>
			<td>${phyCouSignUpVO.update_time}</td>
			<td><c:if test="${phyCouSignUpVO.order_status==0}">
				待確認
			</c:if>
			<c:if test="${phyCouSignUpVO.order_status==1}">
				報名成功
			</c:if>
			<c:if test="${phyCouSignUpVO.order_status==2}">
				報名取消
			</c:if>
      </td>
			<td>${phyCouSignUpVO.course_no}</td>
			<td>${phyCouSignUpVO.phyCouVO.course_name}</td>
			<td>${phyCouSignUpVO.phyCouVO.course_date}</td> 
			<td>${phyCouSignUpVO.order_price}</td>
			<td>${phyCouSignUpVO.phyCouVO.course_teacher}</td>
		    <td>			    
				   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course/cou.do" style="margin-bottom: 0px;">
				     <input type="submit" value="取消報名">
				     <input type="hidden" name="order_no"  value="${phyCouSignUpVO.order_no}">
				     <input type="hidden" name="course_no"  value="${phyCouSignUpVO.course_no}">
				     <input type="hidden" name="order_status"  value="${phyCouSignUpVO.order_status}">
     			     <input type="hidden" name="action"	value="cancle"></FORM>
				</td>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>