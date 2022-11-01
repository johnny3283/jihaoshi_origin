<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.latest_news.model.*"%>

<%
	Latest_newsVO latest_newsVO = (Latest_newsVO) request.getAttribute("latest_newsVO"); //Latest_newsVOServlet.java (Concroller) 存入req的latest_newsVO物件 (包括幫忙取出的latest_newsVO, 也包括輸入資料錯誤時的latest_newsVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>最新消息修改</title>

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
	width: 450px;
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
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>最新消息修改</h3>
		 <h4><a href="<%= request.getContextPath() %>/latest_news/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/third/Latest_newsServlet" name="form1">
<table>
<!-- 	<tr> -->
<!-- 		<td>消息編號:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=latest_newsVO.getNews_no()%></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>消息標題:</td>
		<td><input type="TEXT" name="news_name" size="45" value="<%=latest_newsVO.getNews_name()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>編輯時間:</td> -->
<!-- 		<td><input  name="update_date" id="f_date1" type="text"></td> -->
<!-- 	</tr> -->
	<tr>
		<td>消息內文:</td>
		<td><textarea type="TEXT" name="news_content" size="45"	value="<%=latest_newsVO.getNews_content()%>" /></textarea></td>
	</tr>

	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="news_no" value="<%=latest_newsVO.getNews_no()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=latest_newsVO.getUpdate_date()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
</script>
</html>