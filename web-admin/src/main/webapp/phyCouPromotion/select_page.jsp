<%@ page import="com.phyCouPromotion.model.PhyCouPromotionVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Jihaoshi Physical Course promotion: Home</title>

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
   <tr><td><h3>JiHaoShi 實體課程促銷</h3></td></tr>
	<tr><td><a href="${pageContext.request.contextPath}/course/select_page.jsp" style="text-decoration: none; color: blue; font-weight: 700">回課程管理頁</a>
	<a href="${pageContext.request.contextPath}/index.jsp" style="margin: auto 30px;text-decoration: none; color: blue; font-weight: 700">回首頁</a></td></tr> 
</table>

<h3>實體課程促銷資料查詢:</h3>
	
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
  <li><a href='listAllPro.jsp'>列出</a> 所有實體課程促銷專案 <br><br></li>
  <li><a href='listAllProDetail.jsp'>列出</a> 所有實體課程促銷明細 <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="promotion" >
        <b>輸入實體課程促銷專案編號(如3):</b>
        <input type="text" name="project_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="phyCouPromotionSvc" scope="page" class="com.phyCouPromotion.model.PhyCouPromotionService" />
   
  <li>
     <FORM METHOD="post" ACTION="promotion" >
       <b>選擇實體課程促銷專案編號:</b>
       <select size="1" name="project_no">
         <c:forEach var="phyCouPromotionVO" items="${phyCouPromotionSvc.all}" > 
          <option value="${phyCouPromotionVO.project_no}">${phyCouPromotionVO.project_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="promotion" >
       <b>選擇實體課程促銷專案名稱:</b>
       <select size="1" name="project_no">
         <c:forEach var="phyCouPromotionVO" items="${phyCouPromotionSvc.all}" > 
          <option value="${phyCouPromotionVO.project_no}">${phyCouPromotionVO.project_name}
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

<ul>
  <li><a href='addPro.jsp'>新增</a>  一個實體課程促銷專案</li>
</ul>

</body>
</html>
