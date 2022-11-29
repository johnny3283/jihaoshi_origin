<%@page import="com.course.model.PhyCouVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<title>JiHaoJa Physical Course : Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
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
  #pageHead {
    width: 450px;
    height: 19%;
  }
</style>

</head>
<body bgcolor='white'>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">

<table id="table-1">
   <tr><td><h3>JiHaoJa 實體課程<a href="${pageContext.request.contextPath}/index.jsp"> ~回首頁</a></h3></td></tr>
</table>

<h3>課程資料查詢:</h3>
	
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
  <li><a href='listAllCou.jsp'>列出</a> 所有實體課程  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="cou.do" >
        <b>輸入實體課程編號(如3):</b>
        <input type="text" name="course_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="phyCouSvc" scope="page" class="com.course.model.PhyCouService" />
   
  <li>
     <FORM METHOD="post" ACTION="cou.do" >
       <b>選擇實體課程編號:</b>
       <select size="1" name="course_no">
         <c:forEach var="phyCouVO" items="${phyCouSvc.all}" > 
          <option value="${phyCouVO.course_no}">${phyCouVO.course_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="cou.do" >
       <b>選擇實體課程名稱:</b>
       <select size="1" name="course_no">
         <c:forEach var="phyCouVO" items="${phyCouSvc.all}" > 
          <option value="${phyCouVO.course_no}">${phyCouVO.course_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
<%-- <ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course/cou.do" name="form1">
        <b><font color=blue>萬用複合查詢:</font></b> <br>
        <b>input course no:</b>
        <input type="text" name="course_no" value="1"><br>
           
       <b>input course name:</b>
       <input type="text" name="course_name" value="三杯雞"><br>
       
       <b>input course location:</b>
	   <input name="course_location"  value="A001" type="text">
		        
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listCous_ByCompositeQuery">
     </FORM>
  </li>
</ul> --%>
<h3>新增課程資料：</h3>

<ul>
  <li><a href='addCou.jsp'>新增</a>  一個實體課程</li>
</ul>

<h3>課程促銷資料管理：</h3>

<ul>
  <li><a href='${pageContext.request.contextPath}/phyCouPromotion/select_page.jsp'>管理</a>課程促銷資料</li>
</ul>

</body>
</html>
