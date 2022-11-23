<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.online_course.model.*"%>
    
<%
OnlineCourseVO onlinecourseVO = (OnlineCourseVO) request.getAttribute("onlinecourseVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>    
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.1/font/bootstrap-icons.css">
 	<link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
	<style>
	#pageHead {
		width: 100%;
		height: 30%;
	}
	</style>
</head>
<body bgcolor='white'>
<img src="../images/JihaoshiPageHead.jpg" id="pageHead">
	<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
		<div id="CONTENT" class="layout-wrapper">
			<div class="layout-center" style="text-align: center">
				<!--側邊欄區塊開始-->
				<dl class="block_W">
					<dd id="CategoryContainer">
						<ul class="treeview">
							<li id="cate_D" class="expanded"><H1>功能列表</H1>
								<ul class="main">
									<li><a
										href="<%=request.getContextPath()%>/meal/MealInsert.jsp">新增菜單</a>
									</li>
									<li><a href="<%=request.getContextPath()%>/onlineCourse/searchAll">回線上課程管理</a>
									</li>
								</ul>
						</ul>
					</dd>
				</dl>
				<!--側邊欄區塊結束-->
				<div style="width: 85.4%; float: right; background: #FFFAF0">
					<div style="width: 63%; float: right; margin: 3%;">
						<div>
							


<table id="table-1">
	<tr><td>
		 <h3>單筆線上課程資料新增完成</h3>
	</td></tr>
</table>

<table>
	<tr>
		    
			<th>線上課程編號</th>
			<th>線上課程名稱</th>
			<th>線上課程時數</th>
			<th>線上課程師資</th>
			<th>線上課程簡介</th>
			<th>線上課程價格</th>
			<th>線上課程狀態</th>
			<th>評論人數</th>
			<th>評論分數</th>
			<th>課程圖片</th>
	</tr>
	<tr>
		
		<td>${onlinecourseVO.courseNo}</td>
		<td>${onlinecourseVO.courseName}</td>
		<td>${onlinecourseVO.courseHr}</td>
		<td>${onlinecourseVO.courseTeacher}</td>
		<td>${onlinecourseVO.courseInfo}</td>
		<td>${onlinecourseVO.coursePrice}</td>
		<td>${onlinecourseVO.courseStatus}</td>
		<td>${onlinecourseVO.commentPeople}</td>
		<td>${onlinecourseVO.commentScore}</td>
		<td>
			<img src="data:image/png;base64, ${onlinecourseVO.onlineCoursePhotoBaseStr64}" >
		</td>
		
		
	</tr>
</table>


</body>
</html>