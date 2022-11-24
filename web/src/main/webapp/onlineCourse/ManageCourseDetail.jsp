<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://www.youtube.com/iframe_api"></script>
    <title>Document</title>
    <link type="text/css" href="../css/jihaoshi.css" rel="stylesheet">
    <style>
        #pageHead {
            width: 100%;
            height: 30%;
        }

        div.divflex2 {
            display: flex;
            position: absolute;
            top: 41%;
            left: 50%;
            translate: -50%;
            gap: 5rem;
            width: 100%;
            justify-content: center;
            background-color: #fffcec;
            z-index: -999;
            min-height: 200vh;
            padding-top: 20px;
            flex-wrap: wrap;
        }
    </style>
</head>
<body bgcolor='white'>
	<img src="../images/JihaoshiPageHead.jpg" id="pageHead">
	<div class="block_N" style="margin: 0px auto;"></div>
	<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
	    <div id="CONTENT" class="layout-wrapper">
	        <div class="layout-center" style="text-align: center">
	            <!--側邊欄區塊開始-->
	            <dl class="block_W">
	                <dd id="CategoryContainer">
	                    <ul class="treeview">
	
	                        <li id="logout"><a
	                                href="./manager/ManagerServlet?action=Logout">登出</a></li>
	                        <li><a href="./manager/login.jsp">登入</a></li>
	                        <li id="cate_D" class="expanded"><H1>功能列表</H1>
	                            <ul class="main">
	                               <li>歡迎:<font color=blue> ${Guest} </font><font color=blue> ${member.memberAccount} </font>您好 <br> <font>會員編號:
									${member.memberNo} </font> <br> <font>會員姓名: ${member.memberName} </font></li>
						
            <li id="cate_D" class="expanded"><H1>功能列表</H1>
              <ul class="main">
                <li class="drop"><a href="#">會員專區</a>
										<ul class="submenu">
										
											<li class="submenu-item"><a href="#">訂單查詢</a></li>
											<li class="submenu-item"><a
												href="phyCourComment/addPhyComment.jsp">新增實體課程評價</a></li>
											<li class="submenu-item"><a
												href="phyCourComment/listAllMemberComments.jsp">我的實體課程評價</a></li>
										</ul></li>
                <li class="drop">
                  <a href="<%=request.getContextPath()%>/OnlineCourseCommentServlet?action=getMember_For_Display">我的線上課程評價</a>
                </li>
                <li class="drop"> 
                	<a href="<%=request.getContextPath()%>/onlinecoursecomment/addOnlineCourseComment.jsp">我要評價(線上課程)</a>
                </li>
                <li class="drop">
                  <a href="<%=request.getContextPath()%>/onlinecoursecommentreport/addOnlineCourseCommentReport.jsp">我要檢舉(線上課程評論)</a>
                </li>
                <li class="drop">
                  <a href="#">網站簡介</a>
                </li>

                <li class="drop">
                  <a href="${pageContext.request.contextPath}/latest_news/select_page.jsp">最新消息</a>         
                </li>
                <li class="drop">
                  <a href="${ctxPath}/meal/MealProductIndex.jsp">好食產品專區</a>
                </li>

                <li class="drop">
                  <a href="${pageContext.request.contextPath}/onlineCourse/ListAllOnlineCourse.jsp">線上課程瀏覽專區</a>
                </li>
                <li class="drop">
                  <a href="${pageContext.request.contextPath}/onlineCourse/ManageCourse.jsp">會員線上課程管理</a>
                </li>
                 <li class="drop">
                  <a href="${pageContext.request.contextPath}/onlineCourseOrderServlet?action=orderlist">會員線上課程訂單管理</a>
                </li>
            


                <li class="drop">
                	<c:if test="${not empty member}">
                		<a href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp">康健新知論壇</a>
                	</c:if>
                	<c:if test="${empty member}">
                		<a href="<%=request.getContextPath()%>/forum_article/listAllForum_articlevistor.jsp">康健新知論壇</a>
                	</c:if>
                  
                </li>

                <li class="drop">
                  <a href="<%=request.getContextPath()%>/faqservlet?action=getAll">FAQ及客服專區</a>
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
	                            <div id="player" style="float: left;">
	                                <iframe id="frameYoutube" width="550" height="390" src="https://www.youtube.com/embed/_Fv_M9--cu8"
	                                        title="YouTube video player" frameborder="0"
	                                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
	                                        allowfullscreen>
									</iframe>
	                            </div>
								<div class="onlineCourse_info" style="position:absolute; left:55%;top:10%;border:1px solid black;border-radius:10px;padding:20px ;text-align:left">
									<b>線上課程編號: </b>
									<span id="courseNo">courseNo</span>
									<br><br>
									<span>線上課程名稱: </span>
									<span id="courseName">courseName</span>
									<br><br>
									<span>線上課程價格: </span>
									<span id="coursePrice">coursePrice</span>
									<br><br>
									<span>線上課程時數: </span>
									<span id="courseHr">courseHr</span>
									<br><br>
									<span>線上課程老師: </span>
									<span id="courseTeacher">courseTeacher</span>
									<br><br>
									<span>線上課程資訊: </span>
									<span id="courseInfo">courseInfo</span>
									<br>
								</div>
	                        </dl>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<script type="text/javascript">
		const frameYoutube = document.querySelector('#frameYoutube');
		const courseNo = document.querySelector('#courseNo');
		const courseName = document.querySelector('#courseName');
		const coursePrice = document.querySelector('#coursePrice');
		const courseHr = document.querySelector('#courseHr');
		const courseTeacher = document.querySelector('#courseTeacher');
		const courseInfo = document.querySelector('#courseInfo');
	
		const id = sessionStorage.getItem('courseNo');
		const url = `../onlineCourse/detail?courseNo=\${id}`;
		fetch(url)
			.then(resp => resp.json())
			.then(courses => {
				frameYoutube.src = `https://www.youtube.com/embed/\${courses[0].courseVideo}`;
				courseNo.textContent = courses[0].courseNo;
				courseName.textContent = courses[0].courseName;
				coursePrice.textContent = courses[0].coursePrice;
				courseHr.textContent = courses[0].courseHr;
				courseTeacher.textContent = courses[0].courseTeacher;
				courseInfo.textContent = courses[0].courseInfo;
			});
	</script>
</body>
</html>