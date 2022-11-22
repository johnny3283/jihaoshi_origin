<%@ page import="com.forum_comment_report.model.Forum_comment_reportVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.forum_comment_report.model.*"%>

<%
Forum_comment_reportVO forum_comment_reportVO = (Forum_comment_reportVO) request.getAttribute("forum_comment_reportVO"); //Forum_comment_reportServlet.java(Concroller), 存入req的forum_comment_reportVO物件
%>

<html>
<head> 
<title>論壇留言檢舉資料</title>
 
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


<table id="table-1">
	<tr><td>
		 <h3>論壇留言檢舉資料</h3>
		 <h4><a href="<%= request.getContextPath() %>/forum_comment_report/forum_comment_report_select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>論壇文章檢舉編號</th>
			<th>論壇文章留言編號</th>
			<th>論壇文章編號</th>
			<th>會員編號</th>
			<th>檢舉事由</th>
			<th>檢舉狀態</th>	
	</tr>
	<tr>
		<td><%=forum_comment_reportVO.getComment_report_no()%></td>
		<td><%=forum_comment_reportVO.getComment_no()%></td>
		<td><%=forum_comment_reportVO.getArticle_no()%></td>
		<td><%=forum_comment_reportVO.getMember_no()%></td>
		<td><%=forum_comment_reportVO.getReport_reason()%></td>
		<td><%=forum_comment_reportVO.getReport_status()%></td>
		
	</tr>
</table>

</body>
</html>