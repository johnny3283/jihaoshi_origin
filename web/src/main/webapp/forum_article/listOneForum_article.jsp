<%@ page import="com.forum_article.model.Forum_articleVO"%>
<%@ page pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="com.forum_article.model.*"%>
<%@ page import="com.forum_comment.model.*"%>
<%@ page import="com.mem.model.MemberVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
Forum_articleVO forum_articleVO = (Forum_articleVO) request.getAttribute("forum_articleVO"); //Forum_articleServlet.java(Concroller), 存入req的forum_articleServletVO物件
Forum_commentService forum_commentSvc = new Forum_commentService();
Integer article_no = forum_articleVO.getArticle_no();
List<Forum_commentVO> list = forum_commentSvc.catch_display(article_no);
 
session = request.getSession();
MemberVO memberVO = ((MemberVO)session.getAttribute("member"));
Integer memberNo = Integer.valueOf(memberVO.getMemberNo());

pageContext.setAttribute("list", list);
%>
 
<html>
<head>
<title>論壇文章資料</title>

<style>
 #content {
	display: flex; 
	justify-content: center; 
	align-items: center;
	flex-direction: column;
}
.Forumtable {
	display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}
.div1 {
	display: flex;
    justify-content: center;
    flex-direction: column;
}

.comment{
	display: flex;
    justify-content: center;
    flex-direction: column;
}
.comments {
	width:1200px;
	display: flex;
	justify-content: space-between;
}
#content {
	display: flex;
	justify-content: center; 
	align-items: center;
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

<div id="content">
	<table id="table-1">
		<tr>
			<td>
			<h3>論壇文章資料</h3>
			<button class="button">
		 	<a href="<%= request.getContextPath() %>/forum_article/forum_article_select_page.jsp" style="text-decoration: none;color:#333;">回首頁</a>
		 	</button>
			</td>
		</tr>
	</table>
	<br>
	<div class="Forumtable">
		<table >
			<tr>
				<th>論壇文章編號</th>
				<th>文章標題</th>
				<th>會員編號</th>
				<th>編輯時間</th>
				<th>文章內容</th>		
			</tr>
			<tr>
				<td><%=forum_articleVO.getArticle_no()%></td>
				<td><%=forum_articleVO.getArticle_name()%></td>
				<td><%=forum_articleVO.getMember_no()%></td>
				<td><%=forum_articleVO.getArticle_time()%></td>
				<td>${forum_articleVO.article_content}</td>	
			</tr>
		</table>
	</div>
	<br>		
    <div class="comment">
         <c:forEach var="forum_commentVO" items="${list}">
			<div class="comments">
            	<div class="cname" style="width:20%">會員編號:${forum_commentVO.member_no}</div>
            	<div class="ctime" style="width:20%">${forum_commentVO.comment_time}</div>
            	<div class="ccontent" style="width:30%">${forum_commentVO.comment_content}</div>                      		              	
            <div>
                <form method="post" action="/web/Forum_comment_reportServlet">
                    <input type="hidden" name="comment_no" value="${param.article_no}">
                    <input type="hidden" name="article_no" value="${param.article_no}">
                    <input type="hidden" name="member_no" value="<%=memberNo%>">
                    <input type="hidden" name="action" value="insert">
                    <input type="text" name="report_reason" value="${param.report_reason}" placeholder="這留言我覺得不行">
                    <button type="submit" class="button">送出</button>
                </form> 
             </div>
             </div>
             <hr style="width:100%">
           </c:forEach>
				</br>
                </br>  
            <div style="margin: 0px auto;">
                <form method="post" action="/web/Forum_commentServlet">
                    <input type="hidden" name="article_no" value="${param.article_no}">
                    <input type="hidden" name="member_no" value="<%=memberNo%>">
                    <input type="hidden" name="action" value="insert">
                    <input type="text" name="comment_content" value="${param.comment_content}"
                        placeholder="跟版主交交流XD~">&ensp;
                    <button type="submit" class="button">送出</button>
                </form>
             </div>
             
             <div style="margin: 0px auto;">
                <form method="post" action="/web/Forum_article_reportServlet">
                    <input type="hidden" name="article_no" value="${param.article_no}">
                    <input type="hidden" name="member_no" value=<%=memberNo%>>
                    <input type="hidden" name="action" value="insert">
                    <input type="text" name="report_reason" value="${param.report_reason}"
                        placeholder="文章檢舉魔人就是我">&ensp;
                    <button type="submit" class="button">送出</button>
                </form>
             </div> 
		</div>
	
</div>
</body>
</html>