<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
                  <a href="${ctxPath}/meal/mealController?action=listAll">產品清單</a>
                </li>
                <li>
                  <a href="${ctxPath}/meal/RandomAssign.jsp">隨機配餐</a>
                </li>
                <li>
                  <a href="${ctxPath}/cart/MealCart.jsp">菜單商品購物車<c:if test="${not empty cartProds}"> (${fn:length(cartProds)})</c:if></a>
                </li>
                <li>
                  <a href="${ctxPath}/order/orderController?action=orderList">訂單管理</a>
                </li>
                <li>
                  <a href="${ctxPath}/mealCollect/list">我的收藏</a>
                </li>
                <li>
                  <a href="${ctxPath}/index.jsp">回首頁</a>
                </li>
              </ul>
          </ul>
        </dd>
      </dl>
      <!--側邊欄區塊結束-->
      <div class="block_C s_list">
        <div class="Cm" >

            <div>
              <br>
              <h2 style=" font-size: 25px">商品不存在或已下架</h2><br>
              <a href="javascript:history.go(-1)" id="backLastPage">回前頁</a>
            </div>
          </div>


      </div>
    </div>
  </div>

</div>
</div>
</body>
</html>
