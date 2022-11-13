<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.onlinecoursecommentreport.model.*"%>

<html>
<head>
<title>線上課程評論檢舉</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
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
		<div class="Nm" style="display: flex; justify-content: center; align-items: center;">
			<FORM class="searchfield" METHOD="post" ACTION="<%=request.getContextPath()%>/OnlineCourseCommentReportServlet">
				<select name="reportStatus" class="text ac_input">
					<option disabled selected>請選擇狀態</option>
					<option value="0">未處理</option>
					<option value="1">未通過</option>
					<option value="2">通過</option>
				</select>
				<input type="hidden" name="action" value="getStatus_For_Display"> 
				<input class="button" type="submit" value="送出">
			</FORM><br><br>
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
							<table>
								<tr>
									<th>線上課程評論檢舉編號</th>
									<th>會員編號 | 帳號</th>
									<th>線上課程評論編號</th>
									<th>線上課程評論內容</th>
									<th>檢舉原因</th>
									<th>檢舉狀態</th>
									<th>審核修改</th>
									<c:forEach var="onlineCourseCommentReportVO" items="${list}">
										<tr>
											<td>${onlineCourseCommentReportVO.reportNo}</td>
											<td>${onlineCourseCommentReportVO.memberNo}|
												${onlineCourseCommentReportVO.memberVO.memberAccount}</td>
											<td>${onlineCourseCommentReportVO.commentNo}</td>
											<td>${onlineCourseCommentReportVO.onlineCourseCommentVO.commentContent}</td>
											<td>${onlineCourseCommentReportVO.reportReason}</td>
											<td>${reportStatus[onlineCourseCommentReportVO.reportStatus]}</td>
											<td>
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OnlineCourseCommentReportServlet" style="margin-bottom: 0px;">
													<input type="submit" value="審核修改"> 
													<input type="hidden" name="reportNo" value="${onlineCourseCommentReportVO.reportNo}">
													<input type="hidden" name="action" value="getOne_For_Update">
												</FORM>
											</td>
										</tr>
									</c:forEach>
							</table>
							<c:if test="${empty list}">
									<br>
									<h3>查無資料</h3>
							</c:if>
							<dl class="col3f" id="DRAA0A-A900BUT82">
							</dl>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		
	</script>
</body>
</html>
