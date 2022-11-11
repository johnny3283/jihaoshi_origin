<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.meal.model.MealVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<MealVO> meals = (List<MealVO>) request.getAttribute("listMeals");
    request.setAttribute("meals", meals);

%>

<html>
<head>
    <title>Title</title>
    <link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">

</head>
<body>
<img src="../images/JihaoshiPageHead.jpg" id="pageHead">
<div class="block_N" style="margin:0px auto;">
    <!--搜尋欄開始-->
    <div class="Nm" style="display: flex; justify-content: center; align-items: center; ">
        <form method="post" action="mealController" enctype="application/x-www-form-urlencoded" id="keywordSearch">
            <ul class="searchfield">

                <li><input id="keyword" type="text" class="text ac_input" placeholder="請輸入關鍵字" autocomplete="off"></li>
                <li><input type="text" name="action" value="keywordSearch" hidden></li>
                <li><button id="btn_search" type="submit" class="button" form="keywordSearch">搜尋</button></li>

            </ul>

        </form>

    </div>
    <!--搜尋欄結束-->
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
                                    <a href="<%=request.getContextPath()%>/meal/MealCart.jsp">菜單商品購物車</a>
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
                        <!--商品欄開始-->
                        <%@ include file="page1.jsp" %>
                        <c:forEach var="meal" items="${meals}" begin="<%= pageIndex %>"
                                   end="<%= pageIndex+rowsPerPage-1 %>">
                            <dl class="col3f" id="DRAA0A-A900BUT82">
                                <dd class="c1f"><a class="prod_img"
                                                   href="mealController?action=findByprod&mealNo=${meal.mealNo}">
                                    <img src="data:image/png;base64,${meal.showPhoto}"></a></dd>
                                <dd class="c2f">
                                    <ul class="tag_box s_label"></ul>
                                    <h5 class="prod_name"><a
                                            href="mealController?action=findByprod&mealNo=${meal.mealNo}">${meal.mealName}</a>
                                    </h5>
                                    <br>
                                    <span style="font-size: 18px">簡易食譜：${meal.mealRecipe}</span>
                                    <br>
                                    <span style="font-size: 18px">熱量：${meal.mealCal}</span>
                                    <br>
                                    <span style="font-size: 18px">可能過敏原：${meal.mealAllergen}</span>
                                    <ul id="bookInfo"></ul>
                                </dd>
                                <dd class="c3f" id="button_DRAA0A-A900BUT82">
                                    <ul class="price_box">
                                        <li><span style="font-size: 18px">價格NT$${meal.mealPrice}</span>
                                        </li>
                                    </ul>

                                    <form method="post" action="#" enctype="application/x-www-form-urlencoded"
                                          id="cart${meal.mealNo}">
                                        <input type="text" value="${meal.mealNo}" name="mealNo" hidden>
                                    </form>
                                    <br>
                                    <form method="post" action="#" enctype="application/x-www-form-urlencoded"
                                          id="checkout${meal.mealNo}">
                                        <input type="text" value="${meal.mealNo}" name="mealNo" hidden>

                                    </form>
                                    <button type="submit" form="cart${meal.mealNo}">加入購物車</button>
                                    <button type="submit" form="checkout${meal.mealNo}">直接購買</button>
                                    <br>
                                    <P>評論人數：${meal.commentPeople}</P>
                                    <p>產品評價：${meal.commentPeople==0?"尚無人評分":(meal.commentScore/meal.commentPeople)}</p>
                                </dd>
                            </dl>
                        </c:forEach>
                        <%@ include file="page2.jsp" %>
                        <!--商品欄結束-->
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>