<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.online_course.model.*"%>
    
<%
OnlineCourseVO onlinecourseVO = (OnlineCourseVO) request.getAttribute("onlinecourseVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>    
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
  
  .tablediv{
   display:flex;
   gap:10px; 
   justify-content: center;
   flex-direction: column;
   align-items: center;
   
  }

  th{
  padding:5px;
  }
  
  table{
  box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
  padding:5px;
  
  }



</style>
</head>
<body>
<%@ include file="../navbar.file" %>

		 
		 <div class="tablediv">
		 <h3 style="color:grey;font-size:2.5rem">單筆線上課程資料</h3>
<table style="text-align:center">

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
			<img style="width:100px;margin:10px" src="data:image/png;base64, ${onlinecourseVO.onlineCoursePhotoBaseStr64}" >
		</td>
		
		
	</tr>
</table>
</div>


</body>
</html>