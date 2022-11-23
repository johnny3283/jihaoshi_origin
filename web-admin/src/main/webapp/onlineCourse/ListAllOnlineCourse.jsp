<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.online_course.model.*"%>





<%
OnlineCourseVO onlinecourseVO = (OnlineCourseVO) request.getAttribute("onlinecourseVO");
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
<body>
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
									<li><a href="<%=request.getContextPath()%>/index.jsp">回首頁</a>
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
		<tr>
			<td>
				<h3>所有線上課程資料</h3>

			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>線上課程編號</th>
			<th>線上課程名稱</th>
			<th>線上課程時數</th>
			<th>線上課程師資</th>
			<th>線上課程簡介</th>
			<th>線上課程價格</th>
			<th>編輯時間</th>
			<th>評論人數</th>
			<th>評論分數</th>
			<th>線上課程狀態</th>

		</tr>

		<c:forEach var="onlinecourseVO" items="${list}" varStatus="status">
			<tr id="${onlinecourseVO.courseNo}">
				<td>${onlinecourseVO.courseNo}</td>
				<td>${onlinecourseVO.courseName}</td>
				<td>${onlinecourseVO.courseHr}</td>
				<td>${onlinecourseVO.courseTeacher}</td>
				<td>${onlinecourseVO.courseInfo}</td>
				<td>${onlinecourseVO.coursePrice}</td>
				<td>${onlinecourseVO.updateDate}</td>
				<td>${onlinecourseVO.commentPeople}</td>
				<td>${onlinecourseVO.commentScore}</td>

				<td>
					<button class="courseSwitch" onclick="switchCourseStatus(${onlinecourseVO.courseNo}, ${onlinecourseVO.courseStatus})">${onlinecourseVO.courseStatus eq 0 ? "上架" : "下架"}</button>
				</td>


				<td>
					<FORM METHOD="post"
						ACTION="onlineCourse"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改">
						<input type="hidden" name="courseNo" value="${onlinecourseVO.courseNo}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a
			href="/OnlineCourse/searchAll"></a>
			<br>
		<br>
		</li>


		<li>
			<FORM METHOD="post"
				ACTION="onlineCourse">
				<b>輸入線上課程編號 (如1):</b> <input type="text" name="courseNo"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="onlinecourseSvc" scope="page"
			class="com.online_course.model.OnlineCourseService" />

		<li>
	</table>


   <script>
   		function switchCourseStatus(courseNo, courseStatus){
   			fetch('courseStatus',{ 
				method:'post',
				headers:{
					'content-type':'application/json'
				},
				body:JSON.stringify({
					courseNo:courseNo,
					courseStatus:courseStatus
				})
			})
                .then(resp => resp.json())
                .then(body=> {
                	alert('成功!');
                	location = "searchAll";
                });
   		}
   </script>
</body>
</html>