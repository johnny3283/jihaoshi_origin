<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.signupcourse.model.*"%>

<%
	
%>


<html>
<head>
 <!--  <link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet"> -->
<title>管理已報名實體課程</title>

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
	width: 300px;
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
/*     width: 100%; */
    width: 600px;
    height: 20%;
  }
</style>

</head>
<body bgcolor='white'>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">

<h3>實體課程開課資訊</h3>

<ul>
  <li><a href='listAllCou.jsp'>查詢</a>實體課程開課資訊</li>
</ul>
<h3>管理已報名實體課程</h3>
<ul>
  <li><a href='listAllSignUpCou.jsp'>查詢</a>已報名實體課程資訊</li>
</ul>


</body>
</html>