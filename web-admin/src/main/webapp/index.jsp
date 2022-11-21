<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Jihaoshi</title>
    <link type="text/css" href="./css/jihaoshi.css" rel="stylesheet">
    <style>
        #pageHead {
            width: 100%;
            height: 30%;
        }

        a {
            font-size: 20px;
        }
    </style>
</head>
<body>

<img src="images/JihaoshiPageHead.jpg" id="pageHead">
<div class="block_N" style="margin: 0px auto;">

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
                                <li><a href="${ctxPath}/meal/MealManagerIndex.jsp">菜單管理</a>
                                </li>
                                <li><a
                                        href="${pageContext.request.contextPath}/onlineCourse/AddOnlineCourse.jsp">新增線上課程</a>
                                </li>

                                <li>
                                    <a href="<%=request.getContextPath()%>/faqservlet?action=getAll" >FAQ列表</a>
                                </li>
                                <li>

                                    <a href="<%=request.getContextPath()%>/OnlineCourseCommentReportServlet?action=getAll" >線上課程評論檢舉</a>

                                <li>
                                  <a href="${pageContext.request.contextPath}/onlineCourse/searchAll">線上課程管理</a>
                                </li>
                                   <li>
                                  <a href="${pageContext.request.contextPath}/onlineCourseOrderServlet?action=orderlist">訂單管理</a>

                         			<a href="<%=request.getContextPath()%>/OnlineCourseCommentReportServlet?action=getAll" >線上課程評論檢舉</a>
                                <li>
                                	<a href="${pageContext.request.contextPath}/onlineCourse/searchAll">線上課程管理</a>
                                </li>
								<li>
                                	<a href="<%=request.getContextPath()%>/onlinecustomerservice/customerService.jsp">線上客服</a>

                                </li>
                            </ul>
                    </ul>
                </dd>
            </dl>
            <!--側邊欄區塊結束-->
            <div class="block_C s_list">
                <div class="Cm">
                    <div id="ItemContainer" class="Cm_C">
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
