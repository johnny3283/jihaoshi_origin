<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
  <title>Title</title>
</head>
<body>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">
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
                  <a href="<%=request.getContextPath()%>/index.jsp">回首頁</a>
                </li>
              </ul>
          </ul>
        </dd>
      </dl>
      <!--側邊欄區塊結束-->
      <div class="block_C s_list">
        <div class="Cm">
          <div class="prod_area">
            <div class="div_productPage">

            </div>
            <div>
              <br>
              <h2 style="text-align: left; font-size: 25px;">商品不存在或已下架</h2><br>
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
