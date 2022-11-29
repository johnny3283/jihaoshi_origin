<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.latest_news.model.*"%>

<%
Latest_newsVO latest_newsVO = (Latest_newsVO) request.getAttribute("latest_newsVO"); //Latest_newsServlet.java(Concroller), 存入req的latest_newsVO物件
%>
 
 
<html>
<head>
<title>最新消息資料</title>
<style>
  table#table-1 {
	background-color: #F0E68C;
    border: 2px solid black;
    text-align: center;
}
  table {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: 0;
  padding: 0;
  width: 95%;
  table-layout: fixed;
}

table caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

table tr {
  background-color: #f8f8f8;
  border: 1px solid #ddd;
  padding: .35em;
}

table th,
table td {
  padding: .625em;
  text-align: center;
}

table th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
}
.button {
	border-radius:1rem; 
 	border: 1px solid #ccc;
}
#content {
	display:flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;"
}
#head {
	display:flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;"
}       
</style>

</head>

<body bgcolor='white'>
<%@ include file="../navbar.file" %>
<br>
<div id="head">
	<table id="table-1">
	<tr><td>
		 <h3>最新消息資料</h3>
		 <button class="button">
			<a href="<%=request.getContextPath()%>/latest_news/select_page.jsp" style="text-decoration: none;color:#333;">回最新消息查詢</a>
		</button>
	</td></tr>
</table>
</div>
<br>
<div id="content">
<table>
	<tr>
		<th>消息編號</th>
		<th>消息標題</th>
		<th>編輯時間</th>
		<th>消息內文</th>
		<th>消息圖片</th>
	</tr>
	<tr>
		<td><%=latest_newsVO.getNews_no()%></td>
		<td><%=latest_newsVO.getNews_name()%></td>
		<td><%=latest_newsVO.getUpdate_date()%></td>
		<td><%=latest_newsVO.getNews_content()%></td>
		<td><img src="${latest_newsVO.showPhoto}"></td>	
	</tr>
</table>
</div>
</body>
</html>