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

#info {
	background: transparent;
	border: 0;
	font-size: 13px;
}

.drop {
	position: relative;
}

.submenu {
	position: relative;
	opacity: 0;
	width: 100%;
	z-index: 8;
	transition: opacity 0.5s ease;
}

.submenu-item {
	display: block;
	height: 0px;
	overflow: hidden;
	transition: height 0.5s ease;
}

.drop:hover .submenu {
	opacity: 1;
	border: solid 1px black;
}

.drop:hover .submenu-item {
	overflow: visible;
	height: 30px;
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
                    <li>管理員:<font color=red> ${manager.managerName} </font><br>
									權限編號:<font> <c:forEach var="manager" items="${list}">${manager.authorityNo}</c:forEach> </font>
									</li>
						<li id="logout"><a
								href="./manager/ManagerServlet?action=Logout">登出</a></li>
									<li><a
										href="./manager/login.jsp">登入</a></li>
                        <li id="cate_D" class="expanded"><H1>功能列表</H1>
                            <ul class="main">
                            	  <li class="drop"><a href="./manager/managerIndex.jsp">員工管理</a>
										            </li>
									              <li class="drop"><a href="./manager/memberIndex.jsp">會員管理</a>
									             	</li>
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
