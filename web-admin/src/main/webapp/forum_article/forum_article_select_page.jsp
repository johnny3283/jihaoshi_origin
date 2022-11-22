<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>論壇文章首頁: Home</title>

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

<style>
  table#table-1 {
	width: 1120px;
	background-color: #F0E68C;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
            <!--側邊欄區塊結束-->
<div style="display:flex;flex-direction:column;width:100%">
<table id="table-1">
   <tr><td><h3 >論壇文章首頁: Home</h3><h4>( Forum_article )</h4></td></tr>
</table>

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
<br>
<ul>
  <li><a href='<%= request.getContextPath() %>/forum_article/listAllForum_article.jsp'>列出</a> 全部文章狀態與文章檢舉  <br><br></li>
  <li><a href='<%= request.getContextPath() %>/forum_comment/listAllForum_comment.jsp'>列出</a> 全部留言狀態與留言檢舉  <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="/web-admin/Forum_articleServlet" >
        <b>輸入論壇文章編號 (如:1):</b>
        <input type="text" name="article_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="forum_articleSvc" scope="page" class="com.forum_article.model.Forum_articleService" />
   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="/web-admin/Forum_articleServlet" > -->
<!--        <b>選擇論壇文章編號:</b> -->
<!--        <select size="1" name="article_no"> -->
<%--          <c:forEach var="Forum_articleVO" items="${forum_articleSvc.all}" >  --%>
<%--           <option value="${forum_articleVO.article_no}">${forum_articleVO.article_no} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
  <br>
  <li>
     <FORM METHOD="post" ACTION="/web-admin/Forum_articleServlet" >
       <b>選擇文章標題:</b>
       <select size="1" name="article_no">
         <c:forEach var="forum_articleVO" items="${forum_articleSvc.all}" > 
          <option value="${forum_articleVO.article_no}">${forum_articleVO.article_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<br>
<h3>論壇文章管理</h3>

<ul>
  <li><a href='<%= request.getContextPath() %>/forum_article/InsertForum_article.jsp'>新增</a> 論壇文章</li>
</ul>

</body>
</html>