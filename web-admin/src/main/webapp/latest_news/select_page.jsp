<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>最新消息首頁: Home</title> 


<link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
    <style>
        #pageHead { 
            width: 100%;
            height: 30%; 
        }
        div.divflex{
        display:flex;
        width:100%;
        margin:0;
        height:100vh-30%;
        }
        body{
        height: 100vh;
        background-color:#FFFAF0;
        }
        div.formdiv{
        style="width:80%%;
        background: #FFFAF0;
        }
    </style>

</head>
<body bgcolor='white'>
<img src="<%=request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">

<div id="WRAPPER" class="">
    <div class="divflex">
        <div class="" style="text-align:center;background-color:#FFFAF0;widtH:13%; height:100vw; background-color:#F3E3C3;">
            <!--側邊欄區塊開始-->
                    <ul class="treeview">
                        <li id="cate_D" class="expanded"><H1>功能列表</H1>
                            <ul class="main">
                                <li>
                                   <a href="<%=request.getContextPath()%>/index.jsp">回即好食首頁</a>
                                </li>
                            </ul>
                      </li>
                 </ul>
          </div>   
     </div>
  </div>

            <!--側邊欄區塊結束-->

<div style="position:absolute;top:32%;left:15%;">
<table id="table-1">
   <tr><td><h3>最新消息首頁: Home</h3><h4>( Latest_news )</h4></td></tr>
</table>

<br>



<br>

<h3>資料查詢:</h3>
	

	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%= request.getContextPath() %>/latest_news/listAllLatest_news.jsp'>列出</a> 全部最新消息!  <br><br></li>
  
  
<!--   <li> -->
<!--     <FORM METHOD="post" ACTION="/web-admin/Latest_newsServlet" > -->
<!--         <b>輸入消息編號 (如:1):</b> -->
<!--         <input type="text" name="news_no"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->

  <jsp:useBean id="latest_newsSvc" scope="page" class="com.latest_news.model.Latest_newsService" />
   
  <li>
     <FORM METHOD="post" ACTION="/web-admin/Latest_newsServlet" >
       <b>選擇消息編號:</b>
       <select size="1" name="news_no">
         <c:forEach var="latest_newsVO" items="${latest_newsSvc.all}" > 
          <option value="${latest_newsVO.news_no}">${latest_newsVO.news_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="/web-admin/Latest_newsServlet" >
       <b>選擇消息內文:</b>
       <select size="1" name="news_no">
         <c:forEach var="latest_newsVO" items="${latest_newsSvc.all}" > 
          <option value="${latest_newsVO.news_no}">${latest_newsVO.news_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>
<br>

<h3>最新消息管理</h3>

<ul>
  <li><a href='<%= request.getContextPath() %>/latest_news/InsertLatest_news.jsp'>新增</a> 最新消息.</li>
</ul>

</div>
</body>
</html>