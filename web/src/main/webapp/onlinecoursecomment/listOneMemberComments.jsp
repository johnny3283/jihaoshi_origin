<%@ page import="com.cart.model.CartProdVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.onlinecoursecomment.model.*"%>
<html>
<head>
  <title>我的線上課程評價</title>
  <style>
  #content {
	display: flex; 
	justify-content: center; 
	align-items: center;
}
#Commenttable {
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

  </style>
</head>
<body>
	<%@ include file="navbar.file" %>
    <br>
	<div id="CONTENT">
		<div id="Commenttable">
			<table style="margin: 0px auto;">   
				<tr>
					<th>線上課程評論編號</th>
					<th>線上課程編號 | 名稱</th>
					<th>評論內容</th>
					<th>評分</th>
					<th>修改</th>
					<th>刪除</th>
			<c:forEach var="onlineCourseCommentVO" items="${getOne_For_Display}">
				<tr>
					<td>${onlineCourseCommentVO.commentNo}</td>
					<td>${onlineCourseCommentVO.courseNo} | ${onlineCourseCommentVO.onlineCourseVO.courseName}</td> 
					<td>${onlineCourseCommentVO.commentContent}</td>
					<td>${onlineCourseCommentVO.commentScore}</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OnlineCourseCommentServlet" style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="commentNo" value="${onlineCourseCommentVO.commentNo}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OnlineCourseCommentServlet" style="margin-bottom: 0px;">
						<input type="submit" value="刪除">
						<input type="hidden" name="commentNo" value="${onlineCourseCommentVO.commentNo}"> 
						<input type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
			</table>            
          </div>
        </div>
</body>
</html>
