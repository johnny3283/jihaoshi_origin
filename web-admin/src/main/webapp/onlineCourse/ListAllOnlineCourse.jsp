<%@ page language="java" pageEncoding="UTF-8"%>
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
	<style>
	    ul,li {
    	list-style:none;
    }
      body {
            background: #fafafa url(https://jackrugile.com/images/misc/noise-diagonal.png);
            color: #444;
            font: 100%/30px 'Helvetica Neue', helvetica, arial, sans-serif;
            
        }

        strong {
            font-weight: bold;
        }

        em {
            font-style: italic;
        }

        table {
            background: #FFFCEC;
            border-collapse: separate;
            font-size: 12px;
            line-height: 24px;
            margin: 30px auto;
            text-align: center;
          
        }

        th {
            background-color:#faedcd;          
            font-weight: bold;
            padding: 10px 15px;
            position: relative;          
            color:black;
        }

           
        td {
            border-right: 1px solid #fff;
            border-left: 1px solid #e8e8e8;
            border-top: 1px solid #fff;
            border-bottom: 1px solid #e8e8e8;
            padding: 10px 15px;
            position: relative;
            transition: all 300ms;
        }

        td:first-child {
            box-shadow: inset 1px 0 0 #fff;
        }

        td:last-child {
            border-right: 1px solid #e8e8e8;
            box-shadow: inset -1px 0 0 #fff;
        }

        tr {
            background: url(https://jackrugile.com/images/misc/noise-diagonal.png);
        
        }

        tr:nth-child(odd) td {
            background: white url(https://jackrugile.com/images/misc/noise-diagonal.png);
               
        }

        tr:last-of-type td {
            box-shadow: inset 0 -1px 0 #fff;
        }

        tr:last-of-type td:first-child {
            box-shadow: inset 1px -1px 0 #fff;
        }

        tr:last-of-type td:last-child {
            box-shadow: inset -1px -1px 0 #fff;
        }

        tbody:hover td {
            color: transparent;
            text-shadow: 0 0 3px #aaa;
        }

        tbody:hover tr:hover td {
            color: #444;
            text-shadow: 0 1px 0 #fff;
        }
        .h3,h3 {
            font-size: calc(2rem + .8vw) !important;
            line-height: 15px;
            margin: auto;
            text-align: center;
            color:grey;      
            background-color:white;
            html, body {
    height: 100%;
    }
</style>
<style>
.wrap {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}


  
  .courseSwitch{
  width: 50px;
  height: 30px;
  font-family: 'Roboto', sans-serif;
  font-size: 11px;
  text-transform: uppercase;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: #000;
  background-color: #fff;
  border: none;
  border-radius: 45px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
  
  }
  
	
	
	
	</style>

</head>
<body>
<%@ include file="../navbar.file" %>

			<h3>所有線上課程資料</h3>
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
			<th></th>

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
						<input class="wrap courseSwitch" type="submit" value="修改" >						
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


		<li style="width:35%;margin:auto;">
			<FORM METHOD="post"	ACTION="onlineCourse">
				<b>輸入線上課程編號 (例1):</b> 
				<input type="text" name="courseNo">
<!-- 					<input	type="submit" value="送出"> -->
				 <input type="hidden" name="action" value="getOne_For_Display"> 
					 <button onclick="searchByMemberId()" id='search' name="action" value="getOne_For_Display" style="margin-left: 5px;width:auto;border-radius: 10px; border: .5px solid #f4f5e3;background: #F3E3C3; height:28px"><svg style="" xmlns="http://www.w3.org/2000/svg" width="10" height="10" fill="grey" class="bi bi-search" viewBox="0 0 16 16">
    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
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