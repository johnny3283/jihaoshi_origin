<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.latest_news.model.*"%>

<%
Latest_newsVO latest_newsVO = (Latest_newsVO) request.getAttribute("latest_newsVO"); //Latest_newsVOServlet.java (Concroller) 存入req的latest_newsVO物件 (包括幫忙取出的latest_newsVO, 也包括輸入資料錯誤時的latest_newsVO物件)
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>最新消息修改</title>

<style>

table#table-1 {
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
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}

.button {
	border-radius: 1rem;
	border: 1px solid #ccc;
}

#content {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

#head {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	"
}
</style>

</head>
<body bgcolor='white'>
	<%@ include file="../navbar.file"%>
	<br>
<!-- 	<div id="head"> -->
<!-- 		<table id="table-1"> -->
<!-- 			<tr> -->
<!-- 				<td> -->
<!-- 					<h3>最新消息修改</h3> -->
<!-- 					<button class="button"> -->
<%-- 						<a href="<%=request.getContextPath()%>/index.jsp" style="text-decoration: none; color: #333;">回首頁</a> --%>
<!-- 					</button> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 	</div> -->
	<div id="content">
		<br>
		<h3>資料修改</h3>
		<br>
		<div style="text-align: center">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<br>
				<c:forEach var="message" items="${errorMsgs}">
					<span style="color: red">${message}</span>
				</c:forEach>
			</c:if>
		</div>
		<div style="text-align: center; display:flex;flex-direction: column;justify-content: center;align-items: center;">
		<FORM METHOD="post" ACTION="/web-admin/Latest_newsServlet" enctype="multipart/form-data" name="form1">
			
				<!-- 	<tr> -->
				<!-- 		<td>消息編號:<font color=red><b>*</b></font></td> -->
				<%-- 		<td><%=latest_newsVO.getNews_no()%></td> --%>
				<!-- 	</tr> -->
				<label>消息標題:</label><br><br>
				<input type="TEXT" name="news_name" size="45" value="<%=latest_newsVO.getNews_name()%>" required /><br><br>
				<!-- 	<tr> -->
				<!-- 		<td>編輯時間:</td> -->
				<!-- 		<td><input  name="update_date" id="f_date1" type="text"></td> -->
				<!-- 	</tr> -->
				<label>消息內文:</label><br><br>
				<textarea type="TEXT" name="news_content" size="45" value="<%=latest_newsVO.getNews_content()%>" required /></textarea><br><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="file" name="news_pic" id="the_file" multiple> 
				 <ul class="picture_list"></ul>
				<input type="hidden" name="action" value="update">
				<br><br> 
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="news_no" value="<%=latest_newsVO.getNews_no()%>"> 
				<input type="submit" value="送出修改" class="button">
		</FORM>
		</div>
		
		
   
    <script>
        window.addEventListener("load", function () {

            var the_file_element = document.getElementById("the_file");
            the_file_element.addEventListener("change", function (e) {


                let my_ul = document.getElementsByClassName("picture_list")[0];
                my_ul.innerHTML = "";
                // 寫在這
                // console.log(this.file[0]);

                for (let i = 0; i < this.files.length; i++) {
                    let reader = new FileReader(); // 用來讀取檔案的物件

                    reader.readAsDataURL(this.files[i]); // 讀取檔案

                    // 檔案讀取完畢時觸發
                    reader.addEventListener("load", function () {

                        // 可以透過 reader.result 取得圖片讀取完成時的 Base64 編碼格式
                        // console.log(reader.result);
                        // ... other code ...
                        let li_str = '<li><img src="' + reader.result + '" class="preview"></li>'
                        // let li_str = `<li></li>`
                        let ul_el = document.getElementsByClassName("picture_list")[0];

                        ul_el.insertAdjacentHTML("beforeend", li_str);

                    });
                };


            });


        });
    </script>
</body>




</html>