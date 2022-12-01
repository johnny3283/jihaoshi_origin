<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.latest_news.model.*"%>
<!DOCTYPE html>
<html lang="en">
 
<head>

    <meta charset="UTF-8">
    <title>最新消息新增</title>
    <style>
 		.button {
 			border-radius:1rem; 
 			border: 1px solid #ccc;
		}
		#content {
			display:flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
		}
    </style>
    
</head>

<body bgcolor='white'>
<%@ include file="../navbar.file" %>
<br>
<div id="content">
	<div>
	<h1 style="align-self:center;margin: 2rem 0">最新消息新增</h1>
	</div>
	<div>
	<button class="button">
	   <a href="<%=request.getContextPath()%>/index.jsp" style="text-decoration: none;color:#333;">回首頁</a>
	</button>
	</div>
	<br>
	<div style="text-align: center; display:flex;flex-direction: column;justify-content: center;align-items: center;">
    <form method="post" action="/web-admin/Latest_newsServlet" enctype="multipart/form-data" id="form1">	
		<!--  <label>輸入消息編號：</label><input type="text" name="news_no"><br><br> -->
		         <label>輸入消息標題：</label><br>
		         <input type="text" name="news_name" value="${param.news_name}" required><br><br>
		<!--  <label>輸入編輯時間：</label><input type="text" name="update_date"><br><br> -->
		         <label>輸入消息內文：</label><br>
		         <textarea name="news_content" value="${param.news_content}" required></textarea><br><br>
		         <div>			
		         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		         <input type="file" name="news_pic" id="the_file">
		         <ul class="picture_list"></ul>
		         </div>
		         <br>
				 <input type="hidden" name="action" value="insert" multiple>
		<!--  <input type="hidden" name="news_no" value=1> -->
		         <button type="submit" form="form1" value="送出" class="button">送出新增</button>
		<!--  <button type="reset" form="form1">清除資料</button> -->
	</form>	
	</div> 	
</div>


    <ul class="picture_list"></ul>

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