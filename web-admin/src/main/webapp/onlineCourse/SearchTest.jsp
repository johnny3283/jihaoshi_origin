<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="onlineCourse.do" method="post" style="margin-bottom:2%">
		線上課程編號
		<input type="text" name="course_no">
		<input type="hidden" name="action" value="SearchByNumber">
		<input type="submit">
	</form>
	<!-- 加上這方法not empty之後，這段就不會顯示(如果不是空值這段就會顯示)   -->
	<c:if test="${not empty onlineCourse}">
		<table border="1">
			<thead>
				<tr>
					<th>線上課程編號</th>
					<th>線上課程名稱</th>
					<th>線上課程時數</th>
					<th>線上課程師資</th>
					<th>線上課程簡介</th>
					<th>線上課程價格</th>
					<th>線上課程狀態</th>
					<th>編輯時間</th>
					<th>評論人數</th>
					<th>評論分數</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${onlineCourse.courseNo}</td>
					<td>${onlineCourse.courseName}</td>
					<td>${onlineCourse.courseHr}</td>
					<td>${onlineCourse.courseTeacher}</td>
					<td>${onlineCourse.courseInfo}</td>
					<td>${onlineCourse.coursePrice}</td>
					<td>${onlineCourse.courseStatus}</td>
					<td>${onlineCourse.updateDate}</td>
					<td>${onlineCourse.commentPeople}</td>
					<td>${onlineCourse.commentScore}</td>
				</tr>
			</tbody>
		</table>
	</c:if>
	
	<c:if test="${not empty onlineCourseList}">
		<table border="1">
			<thead>
				<tr>
					<th>線上課程編號</th>
					<th>線上課程名稱</th>
					<th>線上課程時數</th>
					<th>線上課程師資</th>
					<th>線上課程簡介</th>
					<th>線上課程價格</th>
					<th>線上課程狀態</th>
					<th>編輯時間</th>
					<th>評論人數</th>
					<th>評論分數</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="onlineCourse" items="${onlineCourseList}">
					<tr>
						<td>${onlineCourse.CourseNo}</td>
						<td>${onlineCourse.CourseName}</td>
						<td>${onlineCourse.CourseHr}</td>
						<td>${onlineCourse.CourseTeacher}</td>
						<td>${onlineCourse.CourseInfo}</td>
						<td>${onlineCourse.CoursePrice}</td>
						<td>${onlineCourse.CourseStatus}</td>
						<td>${onlineCourse.UpdateDate}</td>
						<td>${onlineCourse.CommentPeople}</td>
						<td>${onlineCourse.CommentScore}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>