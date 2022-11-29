<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.forum_article.model.Forum_articleVO"%>

<%
Forum_articleVO forum_articleVO = (Forum_articleVO) request.getAttribute("forum_articleVO"); //Forum_articleServlet.java (Concroller) 存入req的forum_articleVO物件 (包括幫忙取出的forum_articleVO, 也包括輸入資料錯誤時的forum_articleVO物件)
%>
 
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>論壇文章修改</title>
 
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
		<tr>
			<td>
				<h3>論壇文章修改</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp">
						<img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="/web/Forum_articleServlet" name="form1">
		<table>


			<tr>
				<td>文章標題:</td>
				<td><input type="TEXT" name="article_name" size="45"
					value="<%=forum_articleVO.getArticle_name()%>"  required/></td>
			</tr>

			<tr>
				<td>文章內容:</td>
				<td><textarea type="TEXT" name="article_content" size="45"
						value="<%=forum_articleVO.getArticle_content()%>"  required/></textarea></td>
			</tr>

		
<!-- 			<tr> -->
<!-- 				<td>文章狀態:</td> -->
<!-- 				<td><input type="TEXT" name="article_status" size="45" -->
<%-- 						value="<%=forum_articleVO.getArticle_status()%>" /></td> --%>
<!-- 			</tr> -->


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="article_no"
			value="<%=forum_articleVO.getArticle_no()%>"> <input
			type="submit" value="送出修改">
	</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=forum_articleVO.getArticle_time()%>
	', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>
</html>