<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.phyCouPromotionDetail.model.*"%>
<%
	List<PhyCouVO> list = (List)request.getAttribute("searchResult");
    pageContext.setAttribute("list",list);
%>

<html>
<head>
    <title>Title</title>
    <%-- <link type="text/css" href="${ctxPath}/css/jihaoshi.css" rel="stylesheet"> --%>
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<style>
		#container { width:100%; height:600px; display:flex; flex-direction:column; flex_wrap:wrap;}
		header { width:100%; height:150px; }
		nav { width:100%; height:40px; text-align: center; display: inline }
		article { width:100%; height:420px;}
		#pageHead {
		    width: 100%;
		    height: 100%;
  		}
  		#searchBar { width:100%; height:50px; justify-content: center; align-items: center;}
  		 table {
			width: 100%;
			background-color: white;
			margin-top: 5px;
			margin-bottom: 5px;
		  }
		  table, th, td {
		    border: 1px solid #CCCCFF;
		  }
		  th, td {
		    padding: 5px;
		    text-align: center;
		  }
		  #info {
		    width:360px;
		  }
  		
	</style>
</head>
<body>
<div id="container">
 <header>
<img src="../images/JihaoshiPageHead.jpg" id="pageHead">
</header>
		 <h2 style="margin: 10px auto">所有實體課程開課資料</h2>
<nav>
<a href="${ctxPath}/course/listAllSignUpCou.jsp" style="text-decoration: none; color: blue; font-weight: 700">查詢己報名課程</a>
<a href="${ctxPath}/index.jsp" style="margin: auto 30px;text-decoration: none; color: blue; font-weight: 700">回首頁 </a>
	<form method="post" action="${ctxPath}/signup/cou.do" enctype="application/x-www-form-urlencoded"
				id="searceKeyword" style="display:inline-block;">	
						<input type="text" name="nameKeyword" id="keyword"  class="text ac_input"  placeholder="請輸入關鍵字">
						<input type="submit" value="搜尋課程">
						<input type="hidden" name="action" value="nameKeywordSearch">                    
	</form>
</nav>


<article>
<table>
							<tr>
							    <th>報名欄</th>
								<th>課程編號</th>
								<th>課程名稱</th>
								<th>照片</th>		
								<th>上課時數</th>
								<th>原價</th>
								<th>促銷價格</th>
								<th>授課老師</th>
								<th>開課日期</th>
								<th>上課地點</th>
								<th id="info">課程簡介</th>
								<th>課程狀態</th>
								<th>報名開始日期</th>
								<th>報名結束日期</th>
								<th>人數上限</th>
								<th>開課人數</th>
								<th>報名人數</th>			
							</tr>
							<jsp:useBean id="detailSvc" class="com.phyCouPromotionDetail.model.PhyCouPromotionDetailService" />
							<c:forEach var="phyCouVO" items="${list}"> 
								
								<tr>
				<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/signup/cou.do" style="margin-bottom: 0px;">
					<input type="submit" value="報名">
					<input type="hidden" name="course_no"  value="${phyCouVO.course_no}">
					<input type="hidden" name="dPrice"  value="${(detailSvc.getMinPrice(phyCouVO.getCourse_no()) * phyCouVO.getCourse_price())/100}">
					<input type="hidden" name="phyCouVO.current_sign_up_people"  value="${phyCouVO.current_sign_up_people}">
					<input type="hidden" name="action"	value="apply"></FORM>
				</td>		
				<td>${phyCouVO.course_no}</td>
				<td>${phyCouVO.course_name}</td>
				<td><img src="<%=request.getContextPath()%>/sign/DBGifReader?course_no=${phyCouVO.course_no}" width="100px"></td>
				<td>${phyCouVO.course_hr}</td>
				<td>${phyCouVO.course_price}</td>
				<%-- <td>${(detailSvc.getMinPrice(phyCouVO.getCourse_no()) * phyCouVO.getCourse_price())/100}</td> --%>
				<td><c:if test="${(detailSvc.getMinPrice(phyCouVO.getCourse_no()) * phyCouVO.getCourse_price())==0}" >
						<fmt:formatNumber 
							value="${phyCouVO.getCourse_price()}" 
							maxFractionDigits="0"
						/>
					</c:if>
					<c:if test="${(detailSvc.getMinPrice(phyCouVO.getCourse_no()) * phyCouVO.getCourse_price())!=0}" >
						<fmt:formatNumber 
							value="${(detailSvc.getMinPrice(phyCouVO.getCourse_no()) * phyCouVO.getCourse_price())/100}" 
							maxFractionDigits="0"
						/>
					</c:if>  
				</td>
				<td>${phyCouVO.course_teacher}</td>
				<td>${phyCouVO.course_date}</td> 
				<td>${phyCouVO.course_location}</td>
				<td>${phyCouVO.course_info}</td>
				<td><c:if test="${phyCouVO.course_status==0}">
								待上架
							</c:if>
							<c:if test="${phyCouVO.course_status==1}">
								上架中
							</c:if>
							<c:if test="${phyCouVO.course_status==2}">
								已下架
							</c:if>
				</td>
				<td>${phyCouVO.sign_up_start_day}</td>
				<td>${phyCouVO.sign_up_end_day}</td>
				<td>${phyCouVO.max_sign_up_people}</td>
				<td>${phyCouVO.min_sign_up_people}</td>
				<td>${phyCouVO.current_sign_up_people}</td>		
			</tr>
							</c:forEach>
						</table>
</article>
</div>
</body>
</html>
