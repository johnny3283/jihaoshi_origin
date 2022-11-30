<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.latest_news.model.*"%>

<%
Latest_newsVO latest_newsVO = (Latest_newsVO) request.getAttribute("latest_newsVO"); //Latest_newsServlet.java(Concroller), 存入req的latest_newsVO物件
%>
 
 
<html>
<head>
<title>最新消息資料</title>

    <style>
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
	background-color: #F0E68C;
	border: 2px solid f4f5e3;
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
  table#table-1 {
	background-color: #F0E68C;
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
	
	background-color: white;
	margin-top: 50px;
	margin-bottom: 5px;
  }
  
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

<style>

.orderDetail{
width: 80%;
margin: auto;	
}
.orderDetail table tr {

    	box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
    }
    
    .orderDetail table th {
    	min-width: 130px;
    	text-align: center;
    }
    
    .orderDetail table td {
    	width:auto;
    }
    
    ul,li {
    	list-style:none;
    }
    
    li > form {
    	margin-left: 50%;
    	translate:-50%;
    }
    
    h1 {
    	text-align: center;
    	font-size: 2.5rem;
    	color: grey;
    }
</style>

</head>

<body>
<%@ include file="../navbar.file" %>

<div class="orderDetail">

<h1>單筆最新消息</h1>

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

</body>
</html>