<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link type="text/css" href="${ctxPath}/css/jihaoshi.css" rel="stylesheet">
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
  <title>隨機配餐</title>
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
            <h2 style=" font-size: 25px">請輸入配餐條件</h2><br>
            <form method="post" action="${ctxPath}/meal/mealController" enctype="application/x-www-form-urlencoded"
                  id="formRandom">
              <input type="text" name="action" value="randomAssign" hidden>
              <label>餐點份數：</label><span id="amount_value" style="font-size: 18px">1</span><br>
            <input type="range" value="1" min="1" max="10" name="mealAmount" id="mealAmount">
            <label>是否接受重複餐點：</label>
            <label for="yes">是</label><input type="radio" name="repeat" value="yes" id="yes" checked>
            <label for="no">否</label><input type="radio" name="repeat" value="no" id="no">
              <button type="submit" form="formRandom">隨機配餐</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
<script>
  $(document).ready(function () {
    $('#mealAmount').mousemove(function () {
      $('#amount_value').html($('#mealAmount').val());
    });
    $('#mealAmount').change(function () {
      $('#amount_value').html($('#mealAmount').val());
    });
    $('#mealAmount').click(function () {
      $('#amount_value').html($('#mealAmount').val());
    });

  });
</script>
</body>
</html>
