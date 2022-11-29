<%@ page language="java" pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://www.youtube.com/iframe_api"></script>

    <style>

/*         div.divflex2 { */
/*             display: flex; */
/*             position: absolute; */
/*             top: 41%; */
/*             left: 50%; */
/*             translate: -50%; */
/*             gap: 5rem; */
/*             width: 100%; */
/*             justify-content: center; */
/*             background-color: #fffcec; */
/*             z-index: -999; */
/*             min-height: 200vh; */
/*             padding-top: 20px; */
/*             flex-wrap: wrap; */
/*         } */
    </style>
</head>
<body>
	<%@ include file="../navbar.file" %>
	<div class="block_N" style="margin: 0px auto;"></div>
	<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
	    <div id="CONTENT" class="layout-wrapper">
	        <div class="layout-center" style="text-align: center">
	         
	            <div class="block_C s_list">
	                <div class="Cm">
	                    <div id="ItemContainer" class="Cm_C">
	                        <dl class="col3f" id="DRAA0A-A900BUT82">
	                            <div id="player" style="float: left;">
	                                <iframe id="frameYoutube" width="550" height="390" src="https://www.youtube.com/embed/_Fv_M9--cu8"
	                                        title="YouTube video player" frameborder="0" style="margin-left:80px"
	                                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
	                                        allowfullscreen>
									</iframe>
	                            </div>
								<div class="onlineCourse_info" style="position:absolute; left:55%;top:21.9%;border:3px solid #f4f5e3;border-radius:10px;padding:20px ;text-align:left;box-shadow: 2px 2px 2px 1px rgb(0 0 0 / 20%);">
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