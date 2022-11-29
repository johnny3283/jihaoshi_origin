<%@ page import="com.forum_article.model.Forum_articleVO"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_article.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
// Forum_articleVO forum_articleVO = (Forum_articleVO) request.getAttribute("forum_articleVO"); //Forum_articleServlet.java(Concroller), 存入req的Forum_articleVO物件
Forum_articleService forum_articleSvc = new Forum_articleService();
List<Forum_articleVO> list = forum_articleSvc.getAll();
pageContext.setAttribute("list", list);
%> 

<html>
<head>
<title>論壇文章資料</title>
<style>
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
#head {
	display:flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;"
}
#content {
	display:flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;"
}       
table#table-1 {
	border: 2px solid black;
	text-align: center;
}

h4 {
	color: blue;
	display: inline;
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
table#table-1 {	
	border: 2px solid black;
	text-align: center;
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
<body bgcolor='white'>
<%@ include file="../navbar.file" %>
<br>
	<div id="head">
	<table id="table-1">
		<tr>
			<td>
			<h3>論壇文章資料</h3>
			<button style="border-radius:1rem; border: 1px solid #ccc;">
		 	<a href="<%= request.getContextPath() %>/forum_article/forum_article_select_page.jsp" style="text-decoration: none;color:#333;">回首頁</a>
		 	</button>
			</td>
		</tr>
	</table>
	</div>
<div id="content">
	<table>
		<tr>
			<th>論壇文章編號</th>
			<th>文章標題</th>
			<th>會員編號</th>
			<th>編輯時間</th>
			<th>文章內容</th>
			
		</tr>
		<div style="margin: 0px auto;">
		<%@ include file="forum_article_page1.file"%>
		</div>
		<c:forEach var="forum_articleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${forum_articleVO.article_no}</td>
				<td><a href="/web/Forum_articleServlet?article_no=${forum_articleVO.article_no}&action=getOne_For_Display">${forum_articleVO.article_name} </a></td>
				<td>${forum_articleVO.member_no}</td>
				<td>${forum_articleVO.article_time}</td>
				<td>${forum_articleVO.article_content}</td>				
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" ACTION="/web/Forum_article_reportServlet" -->
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="檢舉">  -->
<%-- 						<input type="hidden" name="article_no" value="${forum_articleVO.article_no}"> --%>
<!-- 						<input type="hidden" name="action" value="insert"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" ACTION="/web/Forum_article_reportServlet" -->
<!-- 						style="margin-bottom: 0px;"> -->
<%-- 						<input type="hidden" name="article_no" value="${forum_articleVO.article_no}"> --%>
<!-- 						<input type="hidden" name="action" value="123"> -->
<!-- 						<input type="submit" value="留言"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
<!-- 			</tr> -->

		</c:forEach>
	</table>
	<br>
	
	<%@ include file="forum_article_page2.file"%>
	
</div>
</body>
</html>