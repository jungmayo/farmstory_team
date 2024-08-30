<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="/FarmStoryJSP/css/farmstory.css">
<style>
* {
	margin: 0;
	padding: 0;
	font: normal 12px '맑은고딕';
}

* 
        ul, ol {
	list-style: none;
}

a {
	text-decoration: none;
	color: black;
}

input, textarea {
	outline: none
}

*

        #wrapper {
	width: 100%;
	height: 100%;
}

main {
	width: 980px;
	height: auto;
	top: 326px;
	left: 470px;
	margin: 0 auto;
}

main .background {
	position: relative;
	width: 980px;
	height: 184px;
	background-image: url(../images/sub_top_bg.jpg);
}

main .background>img {
	position: absolute;
	top: 137px;
	left: 10px;
}

main>section {
	display: flex;
	width: 980px;
	height: 650px;
}

main section aside {
	position: relative;
	width: 176px;
	height: 650px;
	background-image: url('../images/sub_aside_bg_line.png');
	/* 라인 이미지 경로 */
	background-repeat: no-repeat; /* 반복하지 않도록 설정 */
	background-position: right center; /* 오른쪽 중앙에 배치 */
	background-size: contain; /* 배경 이미지를 조정 */
}

.image {
	position: relative;
	margin: 15px 0;
}

.image>img {
	margin-left: 20px;
	margin-top: 10px;
}

.sideimage {
	position: relative;
	width: 175px;
	height: 233px;
}

.sideimage  img {
	position: absolute;
	left: 0;
	width: 100%;
	height: auto;
}

.sideimage .greet img:nth-child(1) {
	top: 26px;
}

.sideimage .way img:nth-child(1) {
	top: 61px;
}

main section article {
	flex-grow: 1;
	height: 650px;
	position: relative;
}

main section article>div {
	position: relative;
}

main section article>div>img {
	position: absolute;
	top: 112px;
	left: 40px;
}

main section article>div>p:nth-of-type(1) {
	position: absolute;
	top: 130px;
	left: 50px;
	line-height: 14.52px;
	color: #91BA15;
	font-weight: bold;
}

main section article>div>p:nth-of-type(2) {
	position: absolute;
	top: 150px;
	left: 50px;
	line-height: 20px;
}

main section article>div>p:nth-of-type(3) {
	position: absolute;
	top: 200px;
	left: 50px;
	line-height: 14.52px;
	color: #91BA15;
	font-weight: bold;
}

.articleNav {
	width: 762px;
	height: 72px;
	margin-left: 40px;
	border-bottom: 1px solid #c2c2c2;
	position: absolute;
}

.articleNav>img {
	position: absolute;
	bottom: 5px;
}

.articleNav>p {
	position: absolute;
	bottom: 5px;
	right: 0px;
}

.articleNav>p>strong {
	color: rgb(124, 165, 64)
}
.map {
            position: absolute;
            top: 220px;
            left: 50px;
        }
</style>
</head>
<body>
	<div id="wrapper"></div>
	<%@ include file="/css/_header.jsp"%>
	<main>
		<div class="background">
			<img src="../images/sub_top_tit1.png" alt="1">
		</div>
		<section>
			<aside>
				<div class="image">
					<img src="../images/sub_aside_cate1_tit.png" alt="팜스토리 소개">
				</div>
				<div class="sideimage">
					<img src="../images/sub_aside_bg_lnb.png" alt="사이드 배경"> <a
						href="/FarmStoryJSP/introduction/introduction.do" class="greet">
						<img src="../images/sub_cate1_lnb1.png" alt="1">
					</a> <a href="/FarmStoryJSP/introduction/direction.do" class="way">
						<img src="../images/sub_cate1_lnb2_ov.png" alt="4">
					</a>
				</div>
			</aside>
			<article>
				<nav class="articleNav">
					<img src="../images/sub_nav_tit_cate1_tit2.png" alt="event">
					<p>
						<img src="../images/sub_page_nav_ico.gif" alt="navIcon">
						HOME > 팜스토리소개 > <strong>찾아오시는 길</strong>
					</p>
				</nav>
				<div>

					<p>팜스토리</p>
					<p>
						주소 : 부산광역시 부산진구 부전동 123<br> 전화 : 01-234-5678
					</p>
					<p>찾아오시는 길</p>
					<div id="map" style="width: 500px; height: 400px;" class="map"></div>
                </div>

                <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=861eab31626b5ffc3da70a0341aad34e"></script>
                <script>
                    var container = document.getElementById('map');
                    var options = {
                        center: new kakao.maps.LatLng(35.1596124, 129.0601826),
                        level: 2
                    };

                    var map = new kakao.maps.Map(container, options);

                    var markerPosition = new kakao.maps.LatLng(35.1596124, 129.0601826);

                    // 마커를 생성합니다
                    var marker = new kakao.maps.Marker({
                        position: markerPosition
                    });

                    // 마커가 지도 위에 표시되도록 설정합니다
                    marker.setMap(map);
                </script>
			</article>


		</section>

	</main>
	<%@ include file="/css/_footer.jsp"%>
	</div>
</body>
</html>