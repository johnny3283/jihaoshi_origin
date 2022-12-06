<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.phyCouPromotion.model.*"%>

<%-- <%
  PhyCouVO phyCouVO = (PhyCouVO) request.getAttribute("phyCouVO");
%>
 --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>實體課程促銷專案新增</title>

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
	width: 600px;
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
    width: 600px;
    height: 24%;
  }
</style>

</head>
<body bgcolor='white'>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">

<table id="table-1">
	<tr><td>
		 <h3>實體課程促銷專案資料新增</h3></td><td>
		 
	</td></tr>
	<tr><td>
		<h4><a href="select_page.jsp">回課程促銷首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if> --%>

<FORM METHOD="post" ACTION="promotion" name="form1">
<table>
	<tr>
		<td>促銷專案名稱:</td>
		<td><input type="TEXT" name="project_name" size="45" 
		value="${param.project_name}"/></td><td>${errorMsgs.project_name}</td>
	</tr>
	<tr>
		<td>開始日期:</td>
		<td><input type="text" id="f_date1" class="mydate" name="start_date" size="45" 
	    value="${param.start_date}"/></td><td>${errorMsgs.start_date}</td>
	</tr>
	<tr>
		<td>結束日期:</td>
		<td><input type="text" id="f_date2" class="mydate" name="end_date" size="45" 
		value="${param.end_date}"/></td><td>${errorMsgs.end_date}</td>
	</tr>
	<tr>
		<td>促銷活動敘述:</td>
		<td><input type="TEXT" name="prom_description" size="45" 
		value="${param.prom_description}"/></td><td>${errorMsgs.prom_description}</td>
	</tr>
	<tr>
		<td>促銷活動狀態:</td>
		<td><input type="TEXT" name="prom_status" size="45" 
		value="${param.prom_status}"/></td><td>${errorMsgs.prom_status}</td>
	</tr>
	<tr>
		<td>促銷課程清單（如：1,2）</td>
		<td><input type="TEXT" name="proCous" size="45" 
		value="${param.proCous}"/></td><td>${errorMsgs.proCous}</td>
	</tr>
	<tr>
		<td>促銷打折內容（如：9折請輸入90）</td>
		<td><input type="TEXT" name="prom_price" size="45" 
		value="${param.prom_price}"/></td><td>${errorMsgs.prom_price}</td>
	</tr>
	
</table>

<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%-- <% 
  java.sql.Date sign_up_start_day = null;
  PhyCouVO
try {				
	sign_up_start_day = java.sql.Date.valueOf(${param.sign_up_start_day});
} catch (IllegalArgumentException e) {
	sign_up_start_day = new java.sql.Date(System.currentTimeMillis());
}
%> --%>
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
		   value: new Date(), // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
//         $.datetimepicker.setLocale('zh');
//        $('#f_date2').datetimepicker({
//	       theme: '',              //theme: 'dark',
//	       timepicker:false,       //timepicker:true,
//	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
//		   value: '${param["start_date"]}', // value:   new Date(), 
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        var somedate1 = new Date('${param["start_date"]}');
        $('#f_date2').datetimepicker({
 	        theme: '',              //theme: 'dark',
	        timepicker:false,       //timepicker:true,
	        step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	        format:'Y-m-d',         //format:'Y-m-d H:i:s',
	        beforeShowDay: function(date) {
	            if (  date.getYear() <  somedate1.getYear() || 
	        	    (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	        	    (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	            ) {
	                  return [false, ""]
	              }
	                  return [true, ""];
	        }
        });
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>
