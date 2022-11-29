<%@page import="com.phyCouPromotion.model.PhyCouPromotionVO"%>
<%@page import="com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO"%>
<%@page import="java.util.Set"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.course.model.PhyCouVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%

  Integer project_no = (Integer) request.getAttribute("project_no");
  Integer course_no = (Integer) request.getAttribute("course_no");
  Integer prom_price = (Integer) request.getAttribute("prom_price");
   
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>實體課程促銷明細資料修改 - update_cou_input.jsp</title>

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

	width: 480px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  #pageHead {
    width: 480px;
    height: 20%;
  }
</style>

</head>
<body bgcolor='white'>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">

<table id="table-1">
	<tr><td>
		 <h3>實體課程促銷明細資料修改</h3>
		 <h4><a href="select_page.jsp">回課程促銷首頁</a></h4>
	</td></tr>
</table>

<h3>實體課程促銷明細資料修改:</h3>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if> --%>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/phyCouPromotionDetail/promotionDetail" name="form1">
<table>
	<tr>
		<td>促銷專案編號:<font color=red><b>*</b></font></td>
		<td>${param.project_no}</td>
	</tr>
<%-- 	<tr>
		<td>促銷專案名稱:</td>
		<td>${project_name}</td>
	</tr> --%>
	<tr>
		<td>促銷課程編號:</td>
		<td>${param.course_no}</td>
	</tr>
<%-- 	<tr>
		<td>促銷課程名稱:</td>
		<td>${course_name}</td>
	</tr> --%>
	<td>促銷折扣:</td>
		<td><input type="TEXT" name="prom_price" size="45" 
		value="${param.prom_price}"/></td><td>${errorMsgs.prom_price}</td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="project_no" value="${param.project_no}">
<input type="hidden" name="prom_price" value="${param.prom_price}">
<input type="hidden" name="course_no" value="${param.course_no}">
<input type="submit" value="送出修改">
</FORM>
</body>


        
</script>
</html>