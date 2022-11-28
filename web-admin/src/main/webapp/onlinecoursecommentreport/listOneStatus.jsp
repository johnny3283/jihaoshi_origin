<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.onlinecoursecommentreport.model.*"%>

<html>
<head>
<title>線上課程評論檢舉</title>
<style>
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
#content {
	display: flex; 
	justify-content: center; 
	align-items: center;
}
#table {
	display: flex;
    justify-content: center;
    flex-direction: column;
}
table {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: 0;
  padding: 0;
  width: 95%;
  table-layout: fixed;
}

table caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

table tr {
  background-color: #f8f8f8;
  border: 1px solid #ddd;
  padding: .35em;
}

table th,
table td {
  padding: .625em;
  text-align: center;
}

table th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
}
.button {
	border-radius:1rem; 
	border: 1px solid #ccc;
}

@media screen and (max-width: 600px) {
  table {
    border: 0;
  }

  table caption {
    font-size: 1.3em;
  }
  
  table thead {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
  }
  
  table tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
  }
  
  table td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: right;
  }
  
  table td::before {
    /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  table td:last-child {
    border-bottom: 0;
  }
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
		<div id="table">
			<table style="margin: 0px auto;">
				<tr>
					<th>線上課程評論檢舉編號</th>
					<th>檢舉者帳號</th>
					<th>線上課程評論編號</th>
					<th>線上課程評論內容</th>
					<th>檢舉原因</th>
					<th>檢舉狀態</th>
					<th>審核修改</th>
				</tr>
				<c:forEach var="onlineCourseCommentReportVO" items="${list}">
				<tr>
					<td>${onlineCourseCommentReportVO.reportNo}</td>
					<td>${onlineCourseCommentReportVO.memberVO.memberAccount}</td>
					<td>${onlineCourseCommentReportVO.commentNo}</td>
					<td>${onlineCourseCommentReportVO.onlineCourseCommentVO.commentContent}</td>
					<td>${onlineCourseCommentReportVO.reportReason}</td>
					<td>${reportStatus[onlineCourseCommentReportVO.reportStatus]}</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OnlineCourseCommentReportServlet" style="margin-bottom: 0px;">
						<input type="submit" value="審核修改" class="button">
						<input type="hidden" name="reportNo" value="${onlineCourseCommentReportVO.reportNo}">
						<input type="hidden" name="action" value="getOne_For_Update">			
						</FORM>
					</td>
				</tr>
				</c:forEach>
			</table>
			<c:if test="${empty list}">
			<br>
			<div style="margin: 0px auto;">
			<span>查無資料</span>
			</div>
			</c:if>							
		</div>
	</div>
	<script type="text/javascript">	
	</script>
</body>
</html>
