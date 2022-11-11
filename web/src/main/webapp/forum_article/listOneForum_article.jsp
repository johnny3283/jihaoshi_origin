<%@ page import="com.forum_article.model.Forum_articleVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="com.forum_article.model.*"%>
<%@ page import="com.forum_comment.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
Forum_articleVO forum_articleVO = (Forum_articleVO) request.getAttribute("forum_articleVO"); //Forum_articleServlet.java(Concroller), 存入req的latest_newsVO物件
Forum_commentService forum_commentSvc = new Forum_commentService();
Integer article_no = forum_articleVO.getArticle_no();
List<Forum_commentVO> list= forum_commentSvc.getAll(article_no);
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>論壇文章資料</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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
</style>

<style>
  table {
	width: 1000px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
   width: 700px;
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
<style>
 div.comments {
            display: grid;
            grid-template-columns: 60px 80px auto;
            padding: 10px;
        }

        div.comments>div {
            text-align: left;
        }

        div.comments>div:first-child {
            /* 	background: #1E90FF; */
            grid-row-start: 1;
            grid-row-end: 3;
        }
</style>
</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>論壇文章資料</h3>
		 <h4><a href="<%= request.getContextPath() %>/forum_article/forum_article_select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
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
    		
    		<div class="comment">
            <c:forEach var="forum_commentVO" items="${list}">

                <div class="comments">
                    <div class="cname">&ensp;${forum_commentVO.member_no}</div>
                    <div class="ccontent">${forum_commentVO.comment_content}</div>
                    <div class="ctime">&ensp;${forum_commentVO.comment_time}</div>
                    <div class="c"></div>
              	</div>
              	<div class="addForum_comment_report">
                <form method="post" action="/web/Forum_comment_reportServlet">
                    <input type="hidden" name="comment_no" value="${param.article_no}">
                    <input type="hidden" name="article_no" value="${param.article_no}">
                    <input type="hidden" name="member_no" value=5>
                    <input type="hidden" name="action" value="insert">
                    <input type="text" class="form-control insert" name="report_reason" value="${param.report_reason}"
                        placeholder="這留言我覺得不行">&ensp;
                    <button type="submit" class="btn btn-info">送出</button>
                </form>
                </div>
            </c:forEach>

            <div class="addComment">
                <form method="post" action="/web/Forum_commentServlet">
                    <input type="hidden" name="article_no" value="${param.article_no}">
                    <input type="hidden" name="member_no" value=5>
                    <input type="hidden" name="action" value="insert">
                    <input type="text" class="form-control insert" name="comment_content" value="${param.comment_content}"
                        placeholder="跟版主交交流XD~">&ensp;
                    <button type="submit" class="btn btn-info">送出</button>
                </form>
                </div>
                
                 <div class="addForum_article_report">
                <form method="post" action="/web/Forum_article_reportServlet">
                
                 
                    <input type="hidden" name="article_no" value="${param.article_no}">
                    <input type="hidden" name="member_no" value=5>
                    <input type="hidden" name="action" value="insert">
                    <input type="text" class="form-control insert" name="report_reason" value="${param.report_reason}"
                        placeholder="檢舉魔人就是我">&ensp;
                    <button type="submit" class="btn btn-info">送出</button>
                </form>
                </div>
                
                
                
			</div>
</body>
</html>