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
		<!--搜尋欄開始-->
		<div class="Nm"
			style="display: flex; justify-content: center; align-items: center;">
			<ul class="searchfield">
				<li><input id="keyword" type="text" class="text ac_input"
					placeholder="請輸入關鍵字" autocomplete="off"></li>
				<li><input id="btn_search" type="button" class="button"
					value="找菜單"></li>
			</ul>
		</div>
		<!--搜尋欄結束-->
	</div>
	<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
		<div id="CONTENT" class="layout-wrapper">
			<div class="layout-center" style="text-align: center">
				<!--側邊欄區塊開始-->
				<dl class="block_W">
					<dd id="CategoryContainer">
						<ul class="treeview">
						<li>管理員:<font color=red>  ${manager.managerName} </font> 
						
							<li id="cate_D" class="expanded"><H1>商品管理</H1>
								<ul class="main">
									<li class="drop"><a href="#">商品管理</a>
										</li>
									<li class="drop"><a href="#">菜單管理</a>
										</li>
									<li class="drop"><a href="#">會員管理</a>
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
