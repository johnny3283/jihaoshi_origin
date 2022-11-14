<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" href="${ctxPath}/css/jihaoshi.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Title</title>
</head>
<body>
<img src="${ctxPath}/images/JihaoshiPageHead.jpg" id="pageHead">
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
                                    <a href="${ctxPath}/index.jsp">回首頁</a>
                                </li>
                            </ul>
                    </ul>
                </dd>
            </dl>
            <!--側邊欄區塊結束-->
            <div class="block_C s_list">
                <div class="Cm">

                    <div>
                        <form method="post" action="detailSave" id="detailInsert">
                            <input type="text" name="mealNo" id="mealNo${param.mealNo}" value="${param.mealNo}" hidden>
                            <c:forEach var="nutrientFeature" items="${nutrientFeatures}" varStatus="loop">
                                <input type="checkbox" name="featureNo" id="feature${loop.count}" value="${loop.count}">
                                <label for="feature${loop.count}">${nutrientFeature.featureName}</label><br>
                            </c:forEach>
                        </form>
                        <button type="submit" form="detailInsert">確定新增</button>
                    </div>
                </div>


            </div>
        </div>
    </div>

</div>
</div>
</body>
</html>
