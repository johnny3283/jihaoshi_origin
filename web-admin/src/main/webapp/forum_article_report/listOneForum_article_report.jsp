<%@ page import="com.forum_article_report.model.Forum_article_reportVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.forum_article_report.model.*"%>

<%
Forum_article_reportVO forum_article_reportVO = (Forum_article_reportVO) request.getAttribute("forum_article_reportVO"); //Forum_article_reportServlet.java(Concroller), 存入req的forum_article_reportVO物件
%>

<html>
<head>
<title>論壇文章檢舉資料</title>

<link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
    <style>
        #pageHead { 
            width: 100%;
            height: 30%; 
        }
        div.divflex{
        display:flex;
        width:100%;
        margin:0;
        height:100vh-30%;
        }
        body{
        height: 100vh;
        background-color:#FFFAF0;
        }
        div.formdiv{
        style="width:80%%;
        background: #FFFAF0;
        }
    </style>


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
	width: 1280px;
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
<img src="<%=request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">



<table id="table-1">
	<tr><td>
		 <h3>論壇文章檢舉資料</h3>
		 <h4><a href="<%= request.getContextPath() %>/forum_article_report/forum_article_report_select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>論壇文章檢舉編號</th>
			<th>論壇文章編號</th>
			<th>會員編號</th>
			<th>檢舉事由</th>
			<th>檢舉狀態</th>
	</tr>
	<tr>
		<td><%=forum_article_reportVO.getArticle_report_no()%></td>
		<td><%=forum_article_reportVO.getArticle_no()%></td>
		<td><%=forum_article_reportVO.getMember_no()%></td>
		<td><%=forum_article_reportVO.getReport_reason()%></td>
		<td><%=forum_article_reportVO.getReport_status()%></td>
		
	</tr>
</table>

</body>
</html>