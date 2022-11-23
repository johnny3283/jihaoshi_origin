<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<title>Jihaoshi</title>
<title>最新消息首頁: Home</title> 


<link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
    <style>
        #pageHead { 
            width: 100%;
            height: 30%; 
        }
        a {
            font-size: 20px;
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
<div class="block_N" style="margin:0px auto;"></div>
<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
  <div id="CONTENT" class="layout-wrapper">
    <div class="layout-center" style="text-align:center">
      <!--側邊欄區塊開始-->
      <dl class="block_W">
        <dd id="CategoryContainer">
          <ul class="treeview">
            <li id="cate_D" class="expanded"><H1>功能列表</H1>
              <ul class="main">
               <li><a href="<%=request.getContextPath()%>/index.jsp">回首頁</a></li>               
        </dd>
      </dl>
      <!--側邊欄區塊結束-->

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
  <li><a href='<%= request.getContextPath() %>/latest_news/listAllLatest_news.jsp'>List</a> all Latest_news.  <br><br></li>
  
  
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
     <FORM METHOD="post" ACTION="/web/Latest_newsServlet" >
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
     <FORM METHOD="post" ACTION="/web/Latest_newsServlet" >
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




</body>
</html>