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
<link type="text/css"
	href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
#pageHead {
	width: 100%;
	height: 30%;
}

a {
	font-size: 20px;
}

table {
	width: 1050px;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 8px;
	text-align: center;
}
</style>
</head>
<body>
	<img src="<%=request.getContextPath()%>/images/JihaoshiPageHead.jpg"
		id="pageHead">
	<div class="block_N" style="margin: 0px auto;">
		<!--搜尋欄開始-->
		<div class="Nm"
			style="display: flex; justify-content: center; align-items: center;">
			<FORM class="searchfield" METHOD="post"
				ACTION="<%=request.getContextPath()%>/OnlineCourseCommentReportServlet">
				<select name="reportStatus" class="text ac_input">
					<option disabled selected>請選擇狀態</option>
					<option value=0>未處理</option>
					<option value=1>未通過</option>
					<option value=2>通過</option>
				</select> <input type="hidden" name="action" value="getStatus_For_Display">
				<input class="button" type="submit" value="送出">
			</FORM>
			<br> <br>
		</div>
		<!--搜尋欄結束-->
	</div>
	<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
		<div id="CONTENT" class="layout-wrapper">
			<div class="layout-center" style="text-align: center">
				<!--側邊欄區塊開始-->
				<dl class="block_W">
					<dd id="CategoryContainer">
						<ul class="treeview">
							<li id="cate_D" class="expanded"><H1>功能列表</H1>
								<ul class="main">
									<li><a
										href="<%=request.getContextPath()%>/OnlineCourseCommentReportServlet?action=getAll">線上課程評論檢舉</a>
									</li>
								</ul>
						</ul>
					</dd>
				</dl>
				<!--側邊欄區塊結束-->
				<div class="block_C s_list">
					<div class="Cm">
						<div id="ItemContainer" class="Cm_C">
							<form id="form">
								<br> <label>線上課程評論檢舉編號：<%=onlineCourseCommentReportVO.getReportNo()%></label><br>
								<br> <label>檢舉者帳號：${onlineCourseCommentReportVO.memberVO.memberAccount}</label><br>
								<br> <label>線上課程評論編號：<%=onlineCourseCommentReportVO.getCommentNo()%></label><br>
								<br> <label>線上課程評論內容：<br>
								<br>${onlineCourseCommentReportVO.onlineCourseCommentVO.commentContent}</label><br>
								<br> <label>檢舉原因：<br>
								<br><%=onlineCourseCommentReportVO.getReportReason()%></label><br>
								<br> <label>審核結果 </label>
								<c:forEach var="reportStatus" items="${number}">
									<input type="radio" name="status" value="${reportStatus}"
										${(reportStatus==(onlineCourseCommentReportVO.reportStatus))?'checked':''}>${status.get(reportStatus)}
							</c:forEach>
								<br>
								<br> <input type="hidden" name="action"
									value="updateStatus"> <input type="hidden"
									name="reportNo" value="${onlineCourseCommentReportVO.reportNo}">
								<input id="btn" type="submit" value="確定送出">
							</form>
							<dl class="col3f" id="DRAA0A-A900BUT82"></dl>
						</div>
					</div>
				</div>
			</div>
		</div>
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
