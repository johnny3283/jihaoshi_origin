<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.online_course.model.*"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
OnlineCourseJDBCDAO course = new OnlineCourseJDBCDAO();
List<OnlineCourseVO> list = course.getAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>線上課程瀏覽專區</title>

<style>

div.divflex {
	display: flex;
	width: 100%;
	margin: 0;
	height: 100vh-30%;
}

body {
	height: 100vh;
	background-color: #FFFAF0;
}

div.formdiv {
	style ="width: 80%%;
	background: #FFFAF0;
}
</style>


<style>
.table {
	display: flex;
	width: 100%;
	justify-content: space-around;
	flex-wrap: wrap;
	/* 	border: 1px solid black; */
	border-radius: 10px;
	
}

.block {
	margin: 2% auto;
	border: 2px #f4f5e3 solid;
	width: 30%;
	height: auto%;
	text-align: center;
	border-radius: 10px;
	box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
    /* 	background:#F3E3C3; */
	/*  line-height: 100px; */
}

.block:hover {
	background: #FFFAF0;
	transition: 1s;
	position: relative;
	left: 5px;
	bottom: 5px;
}

.photo {
	width: 100%;
	height: auto;
	border-radius: 10px;
	line-height: 150px;
}

.content {
	width: 100%;
	height: auto;
	border-radius: 10px;
	line-height: 30px;
}

.table>h1 {
	width: 100%;
	text-align: center;
}

img {
	width: 100%;
}

#search {
	width: 30%;
	height: 25px;
}

#find {
	width: 30%;
	height: 25px;
}

</style>



</head>

<body>	
	<%@ include file="../navbar.file" %>
    <div
		style="display: flex; flex-direction: column; width: 100%; text-align: center">
	  <div>
	<h1 style="align-self:center;margin: 2rem 0;text-align: center;font-size:3rem;color:grey;">線上課程瀏覽專區</h1>
	<input id='find' type='text' placeholder="  請輸入課程名稱或編號..." style="border-radius: 50px; border: .5px solid #F3E3C3; margin-left: 20px">
	<button id='search' style="margin-left: 5x;width:auto;border-radius: 10px; border: .5px solid #f4f5e3;background: #F3E3C3"><svg style="" xmlns="http://www.w3.org/2000/svg" width="10" height="10" fill="grey" class="bi bi-search" viewBox="0 0 16 16">
    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
    </svg></button>
	  </div>
	          <div class="table" id="cardWrapper"></div>
	</div>
			
			
			
			
			
<script>
	document.querySelector('#search').addEventListener('click', searchByName);
    const find = document.querySelector('#find');
	searchByName();
	  function searchByName(){
		      const cardWrapper = document.querySelector('#cardWrapper');
                const id = sessionStorage.getItem('courseNo');
				const findText = find.value;
              
				if (findText) {
					fetch('http://localhost:8081/web-admin/onlineCourse/search',{ 
					method:'post',
					headers:{
						'content-type':'application/json'
					},
					body:JSON.stringify({
						courseName:findText,
						flag:'queryPic'
					})
				}
					)
                    .then(resp => resp.json())
                    .then(listAll);				
				}else{
					 fetch('../onlineCourse/searchAll')
                    .then(resp => resp.json())
                    .then(listAll);

				}
									
					function listAll(list){
						cardWrapper.innerHTML='';
						for(onlineCourse of list){
                            cardWrapper.insertAdjacentHTML('beforeend', `
						<div class="block" onclick="showDetail(\${onlineCourse.courseNo})">
							<div class="photo">
								<img style="border-radius:10px" src="data:image/png;base64, \${onlineCourse.onlineCoursePhotoBaseStr64}">
							</div>
							<div class="content">
								<div style="text-align:left;margin-left:10px;font-weight:bolder">課程編號 : \${onlineCourse.courseNo}</div>
								<div style="text-align:left;margin-left:10px;font-weight:bolder">課程名稱 : \${onlineCourse.courseName}</div>
								<div style="text-align:right;margin-right:20px;font-weight:bolder;color:red">課程價格 : $\${onlineCourse.coursePrice}</div>
                              
							</div>
						</div>
					`)}

					}
	  }
			// coursePhoto

			function showDetail(courseNo) {
				sessionStorage.setItem('courseNo', courseNo);
				location = 'OnlineCourseDetail.html';
			}
	</script>
</body>
</html>