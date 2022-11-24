<%@ page import="com.cart.model.CartProdVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.onlinecoursecomment.model.*"%>
<html>
<head>
  <title>我的實體課程評價</title>
  <link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
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
<img src="<%=request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">
<div class="block_N" style="margin:0px auto;">
</div>
<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
  <div id="CONTENT" class="layout-wrapper">
    <div class="layout-center" style="text-align:center">
      <!--側邊欄區塊開始-->
      <dl class="block_W">
        <dd id="CategoryContainer">
          <ul class="treeview">
            <li id="cate_D" class="expanded"><H1>功能列表</H1>
              <ul class="main">
                <li>
                  <a href="#">會員專區</a>
                </li>
                <li>
                  <a href="<%=request.getContextPath()%>/index.jsp">回首頁</a>
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
			<th>實體課程評論編號</th>
			<th>實體課程編號 | 名稱</th>
			<th>評論內容</th>
			<th>修改</th>
			<th>刪除</th>
		<c:forEach var="phyCourseCommentVO" items="${getOne_For_Display}">
			<tr>
				<td>${phyCourseCommentVO.commentNo}</td>
				<td>${phyCourseCommentVO.courseNo} </td> 
<%-- 				| ${phyCourseCommentVO.PhyCouVO.courseName} --%>
				<td>${phyCourseCommentVO.commentContent}</td>

				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/phyCourseCommentServlet" style="margin-bottom: 0px;">
					<input type="submit" value="修改"> 
					<input type="hidden" name="commentNo" value="${phyCourseCommentVO.commentNo}"> 
					<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/phyCourseCommentServlet" style="margin-bottom: 0px;">
					<input type="submit" value="刪除">
					<input type="hidden" name="commentNo" value="${phyCourseCommentVO.commentNo}"> 
					<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
            <dl class="col3f" id="DRAA0A-A900BUT82">

            </dl>

          </div>
        </div>
      </div>
    </div>

  </div>
</div>
</body>
</html>
