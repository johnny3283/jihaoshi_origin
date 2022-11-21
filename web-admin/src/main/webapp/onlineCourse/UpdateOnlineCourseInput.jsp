<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.online_course.model.*"%>

<%
OnlineCourseVO onlinecourseVO = (OnlineCourseVO) request.getAttribute("onlinecourseVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up Form</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link href='https://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/main.css">

<link type="text/css"  href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
<style>

#pageHead {
	width: 100%;
	height: 30%;
}
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
  *,
        *:before,
        *:after {
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }

        body {
            font-family: 'Nunito', sans-serif;
            color: #384047;
        }

        form {
            max-width: 300px;
            margin: 10px auto;
            padding: 10px 20px;
            background: #f4f7f8;
            border-radius: 8px;
        }

        h1 {
            margin: 0 0 30px 0;
            text-align: center;
        }

		.input{
			border-radius: 50px;
		}
		

        input[type="text"],
        input[type="password"],
        input[type="date"],
        input[type="datetime"],
        input[type="email"],
        input[type="number"],
        input[type="search"],
        input[type="tel"],
        input[type="time"],
        input[type="url"],
        textarea,
        select {
            background: rgba(255, 255, 255, 0.1);
            border: none;
            font-size: 16px;
            height: auto;
            margin: 0;
            outline: 0;
            padding: 15px;
            width: 100%;
            background-color: #e8eeef;
            color: #8a97a0;
            box-shadow: 0 1px 0 rgba(0, 0, 0, 0.03) inset;
            margin-bottom: 30px;
        }

        input[type="radio"],
        input[type="checkbox"] {
            margin: 0 4px 8px 0;
        }
        
        textarea {
        	border-radius: 10px;
        }

        select {
            padding: 6px;
            height: 32px;
            border-radius: 2px;
        }

        button {
            padding: 19px 39px 18px 39px;
            color: #FFF;
            background-color: #4bc970;
            font-size: 18px;
            text-align: center;
            font-style: normal;
            border-radius: 5px;
            width: 100%;
            border: 1px solid #3ac162;
            border-width: 1px 1px 3px;
            box-shadow: 0 -1px 0 rgba(255, 255, 255, 0.1) inset;
            margin-bottom: 10px;
        }

        fieldset {
            margin-bottom: 30px;
            border: none;
        }

        legend {
            font-size: 1.4em;
            margin-bottom: 10px;
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        label.light {
            font-weight: 300;
            display: inline;
        }

        .number {
            background-color: #5fcf80;
            color: #fff;
            height: 30px;
            width: 30px;
            display: inline-block;
            font-size: 0.8em;
            margin-right: 4px;
            line-height: 30px;
            text-align: center;
            text-shadow: 0 1px 0 rgba(255, 255, 255, 0.2);
            border-radius: 100%;
        }

		.submit-input{
			border-radius: 10px; 
			width: 500px; 
			height: 100px; 
			background: #4bc970;
			 border: 0 solid; 
			 color: white; 
			 font-weight: 700; 
			font-size: 1rem;

box-shadow: 5px 7px 5px #333333;
position: relative;
		}
		
		.submit-input:hover{
			background: lightpink;
			transition: .5s;
			top: 5px;
			left: 5px;
		}
		
		
        @media screen and (min-width: 480px) {

            form {
                max-width: 480px;
            }

        }
   


</style>

</head>
<body bgcolor='white'>
	<img src="../images/JihaoshiPageHead.jpg" id="pageHead">
	
		<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
    <div id="CONTENT" class="layout-wrapper">
        <div class="layout-center" style="text-align:center">
            <!--側邊欄區塊開始-->
            <dl class="block_W">
                <dd id="CategoryContainer">
                    <ul class="treeview">
                        <li id="cate_D" class="expanded"><H1>功能列表</H1>
                            <ul class="main">
                                <li>
                                    <a href="<%=request.getContextPath()%>/index.jsp">回首頁</a>
                                </li>
                            </ul>
                    </ul>
                </dd>
            </dl>
			<!--側邊欄區塊結束-->



<h3>線上課程資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="onlineCourse" name="form1"  enctype="multipart/form-data">
<input  type="hidden" name="courseNo" size="45" value= "${onlinecourseVO.courseNo}" />
<table>

<tr>
		<td>線上課程編號:</td>
		<td>${onlinecourseVO.courseNo}</td>
		
	</tr>
	
	       <fieldset>
            <legend style="position: relative; top: 10%; left: -40%;"><span class="number">1</span>修改:</legend>
            <label for="name"></label>
            <input type="text" id="name" name="courseName" placeholder="線上課程名稱" value="${onlinecourseVO.courseName}" /></td>
            
            <label for="price"></label>
            <input type="text" id="price" name="courseHr" placeholder="1小時30分鐘"value="${onlinecourseVO.courseHr}" /></td>

            <label for="hr"></label>
            <input type="text" id="hr" name="courseTeacher" placeholder="線上課程師資" value="${onlinecourseVO.courseTeacher}" /></td>

            <label for="teacher"></label>
            <input type="text" id="teacher" name="coursePrice" placeholder="線上課程價格" value="${onlinecourseVO.coursePrice}" /></td>

            <label for="people"></label>
            <input type="text" id="people" name="commentPeople" placeholder="評論人數" value="${onlinecourseVO.commentPeople}" /></td>

            <label for="score"></label>
            <input type="text" id="score" name="commentScore" placeholder="評論分數" value="${onlinecourseVO.commentScore}" /></td>
 
            <label for="video"></label>
            <input type="text" id="video" name="courseVideo" placeholder="影片串流字串" value="${onlinecourseVO.courseVideo}" /></td>
        </fieldset>

        <fieldset>
            <legend style="position: relative; top: 10%; left: -33%;"><span class="number">2</span>線上課程簡介:</legend>
            <label for="courseInfo" ></label>
            <textarea id="courseInfo" name="courseInfo" placeholder="內容"></textarea>
        </fieldset>

	<div class="photoupload" style="width:100%; margin: 30px auto">
        <tr>
		<td>圖片上傳</td><br>
		<td><input type="file" name="photo"></td>
		</tr>
	</div>

	<div class="status" style="width:100%;">	
		<td>上架狀態</td>
	    <td><input type="radio" name="courseStatus" value="0"
		${(onlinecourseVO == null || onlinecourseVO.courseStatus==0)? 'checked':'' } />
	    上架 <input type="radio" name="courseStatus" value="1"
		${(onlinecourseVO.courseStatus==1)? 'checked':'' } />下架</td>
		</tr>
	</div>	
	
	<div style="width:100%;margin: 30px auto">

	<br>
	
		
		
		<input type="hidden" name="action" value="update">
        <input type="hidden" name="courseno" value="${onlinecourseVO.courseNo}">
        <input class="submit-input" type="submit" value="送出修改">
   </FORM>
					
	</div>



<!-- 	<tr> -->
<!-- 		<td>線上課程簡介:</td> -->
<%-- 		<td><input type="TEXT" name="courseInfo" size="45"	value="${onlinecourseVO.courseInfo}" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->	
<!-- 		<tr> -->
<!-- 		<td>上架狀態:</td> -->
<%-- 		<td><input type="radio" name="courseStatus"  value="0" ${(onlinecourseVO.courseStatus==0)? 'checked':'' }/> 上架 --%>
<%-- 			 <input type="radio" name="courseStatus"  value="1"${(onlinecourseVO.courseStatus==1)? 'checked':'' }/>下架 --%>
<!-- 		</td> -->
<!-- 	</tr> -->

<!--   <tr> -->
<!-- 		<td>圖片上傳</td><br> -->
<!-- 		<td><input type="file" name="photo"></td> -->
<!-- 		</tr> -->

	<jsp:useBean id="deptSvc" scope="page" class="com.online_course.model.OnlineCourseService" />

</table>
<br>

</body>




</html>