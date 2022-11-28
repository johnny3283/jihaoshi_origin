<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.onlinecoursecommentreport.model.*"%>
<%@ page import="java.util.*"%>
<%
OnlineCourseCommentReportVO onlineCourseCommentReportVO = (OnlineCourseCommentReportVO) request
		.getAttribute("onlineCourseCommentReportVO");
%>
<%
Map<Integer, String> status = new HashMap<>();
status.put(1, "未通過");
status.put(2, "通過");
Set<Integer> number = status.keySet();
request.setAttribute("status", status);
request.setAttribute("number", number);
%>
<html>
<head>
<title>線上課程評論檢舉</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
#content {
	display: flex; 
	justify-content: center; 
	align-items: center;
	flex-direction: column;
}
#search {
	border-style:double;
	border-color:#ecb714;
	border-radius:10px;
	width:250px;
	height:40px;
	display: flex; 
	justify-content: center; 
	align-items: center;
}
.searchfield {
	margin: 0px auto;
}
#form {
	display: flex;
    justify-content: center;
    flex-direction: column;
    border-radius:0.6rem; 
    border: 3px solid #ccc;
    width:450px;
    height:500px;
    border-style:outset;
}
</style>
</head>
<body>
	<%@ include file="../navbar.file" %>
	<div id="searchArea" style="margin: 0px auto; display: flex; justify-content: center; align-items: center;">
		<!--搜尋欄開始-->
		<div id="search">
			<FORM class="searchfield" METHOD="post" ACTION="<%=request.getContextPath()%>/OnlineCourseCommentReportServlet">
				<select name="reportStatus" class="text ac_input">
					<option disabled selected>請選擇狀態</option>
					<option value=0>未處理</option>
					<option value=1>未通過</option>
					<option value=2>通過</option>
				</select> <input type="hidden" name="action" value="getStatus_For_Display">
				<input class="button" type="submit" value="送出">
			</FORM>
		</div>
		<!--搜尋欄結束-->
	</div>
	<br>
	<div id="CONTENT">	
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OnlineCourseCommentReportServlet">
		<div id="form">
			<br>
			<div style="margin: 0px auto;">		 
			<label>線上課程評論檢舉編號：<%=onlineCourseCommentReportVO.getReportNo()%></label>
			</div>
			<br>
			<div style="margin: 0px auto;">		
			<label>檢舉者帳號：${onlineCourseCommentReportVO.memberVO.memberAccount}</label>
			</div>
			<br>
			<div style="margin: 0px auto;">		
			<label>線上課程評論編號：<%=onlineCourseCommentReportVO.getCommentNo()%></label>
			</div>
			<br>
			<div style="margin: 0px auto;">		
			<label>線上課程評論內容：</label>
			</div>
			<div style="margin: 0px auto;">
			${onlineCourseCommentReportVO.onlineCourseCommentVO.commentContent}			
			</div>
			<br>
			<div style="margin: 0px auto;">		
			<label>檢舉原因：</label>
			</div>
			<div style="margin: 0px auto;">	
			<%=onlineCourseCommentReportVO.getReportReason()%>
			</div>
			<br>
			<div style="margin: 0px auto;">		
			<label>審核結果 </label>
			<c:forEach var="reportStatus" items="${number}">
				<input type="radio" name="status" value="${reportStatus}"
				${(reportStatus==(onlineCourseCommentReportVO.reportStatus))?'checked':''}>${status.get(reportStatus)}
			</c:forEach>
			</div>
			<br>
			<div style="margin: 0px auto;">		
			<input type="hidden" name="action" value="updateStatus"> 
			<input type="hidden" name="reportNo" value="${onlineCourseCommentReportVO.reportNo}">
			<input type="hidden" name="commentNo" value="${onlineCourseCommentReportVO.commentNo}">
			<input id="btn" type="submit" value="確定送出" class="button">
			</div>
		</div>
		</form>
	</div>
	<script type="text/javascript">
		$(function() {
			$("#btn").click(function() {
				var val = $('input:radio[name="status"]:checked').val();
				if (val == null) {
					alert("請選擇審核結果!");
					return false;
				}
			});
		});
	</script>
</body>
</html>
