<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
	<link rel="stylesheet" href="/FarmStoryJSP/css/farmstory.css">
    <style>
                *{
            margin: 0;
            padding: 0;
            font: normal 12px '맑은고딕';
        }
        * 
        ul,ol {list-style: none;}
        a {text-decoration: none; color: black;}
        input,textarea {outline:none}*

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
        main .background > img {
            position: absolute;
            top: 137px;
            left: 10px;
        }
        main > section {
            display: flex;
            width: 980px;
            height: 650px;

        }
        main section aside {
            position: relative;
            width: 176px;
            height: 650px;
            background-image: url('../images/sub_aside_bg_line.png'); /* 라인 이미지 경로 */
            background-repeat: no-repeat;
            background-position: right center;
            background-size: contain;
        }
        .intro {
            margin: 16px 16px;
        }
        .image > img {
            margin-left: 20px;
            margin-top: 10px;
            
        }
        .sideimage {
            position: relative;
            width: 175px;
            height: 233px;
            background-image: url("../images/sub_aside_bg_lnb.png");
        }

        .sideimage .greet img:nth-child(1) { position:absolute; top :26px; } 
        .sideimage .way img:nth-child(1) { position:absolute; top: 61px; }
        

        main section article {
            flex-grow: 1;
            height: 650px;
            position: relative;
        
        }
        main section article > nav {
            position: absolute;
            width: 762px;
            height: 72px;
            left: 40px;
            border-bottom: 1px solid #C2C2C2;
        }
        main section article > div {
            position: relative;
        }
        main section article > div >img {
            position: absolute;
            top: 112px;
            left: 40px;       
        }
        main section article > div > p:nth-of-type(1) {
            position: absolute;
            top: 288px;
            left: 40px;
            line-height: 20px;
        }
        main section article > div > p:nth-of-type(2) {
            position: absolute;
            top: 348px;
            left: 40px;
            line-height: 20px;
        }
        main section article > div > p:nth-of-type(3) {
            position: absolute;
            top: 431px;
            left: 40px;
            line-height: 14.52px;
            color: #91BA15;
            font-weight: bold;
            
        }
        main section article > div > p:nth-of-type(4) {
            position: absolute;
            top: 448px;
            left: 40px;
            line-height: 20px;
            
        }
        main section article > div > p:nth-of-type(5) {
            position: absolute;
            top: 491px;
            left: 40px;
            line-height: 14.52px;
            color: #91BA15;
            font-weight: bold;
        }
        main section article > div > p:nth-of-type(6) {
            position: absolute;
            top: 508px;
            left: 40px;
            line-height: 20px;
        }
        nav {
            position: relative;
        }
        nav > img:nth-of-type(1) {
            position: absolute;
            top: 41px;
        }
        nav > img:nth-of-type(2) {
            position: absolute;
            bottom:10px;
            right: 190px;
        }
        nav > p:nth-of-type(1){position: absolute; right: 135px; bottom:5px;}
        nav > p:nth-of-type(2){position: absolute; right: 43px; bottom:5px;}
        nav > p:nth-of-type(3){position: absolute; right: 3px; bottom:5px;}

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
                    <img src="../images/sub_aside_cate1_tit.png" alt="팜스토리 소개" class="intro">
                <ul class="sideimage">
                    
                    <li><a href="/FarmStoryJSP/introduction/introduction.do" class="greet"><img src="../images/sub_cate1_lnb1_ov.png" alt="2"></a></li>
                    <li><a href="/FarmStoryJSP/introduction/direction.do" class="way"><img src="../images/sub_cate1_lnb2.png" alt="3"></a></li>
                </ul>
            </aside>
            <article>
                <nav>
                    <img src="../images/sub_nav_tit_cate1_tit1.png" alt="인사말">
                    <img src="../images/sub_page_nav_ico.gif" alt="나브 아이콘">
                    <p>HOME ></p>
                    <p>팜스토리 소개 ></p>
                    <p>인사말</p>
                </nav>
                <div>
                    <img src="../images/sub_page1_article_txt.png" alt="건강한 먹거리">
                    <p>항상 저희 팜스토리를 성원해 주시고 관심을 가져주시는 모든 분들께 감사의 인사를 드리며<br>
                        가정에 건강과 행복이 가득하시길 기원합니다.
                    </p>
                    <p>팜스토리는 신선하고 안전한 먹거리로 건강한 삶 만들기에 기여합니다.<br>
                    보다 좋은 농산품을 공급하기 위해 화학비료를 쓰지 않는 건강한 흙에서 유기농업으로 정성을 다해 지은 농사를 통해<br>
                    믿고 먹을 수 있는 먹거리 제공에 앞장서겠습니다.
                    </p>
                    <p>친환경 농장</p>
                    <p>팜스토리는 경기도 이천에 위치한 10만평 규모의 유기농 재배단지입니다.</p>
                    <p>친환경 캠페인</p>
                    <p>팜스토리는 2차 포장재 사용을 줄임으로써 친환경적인 포장과 친환경적인 소비문화 정착을 위해 노력합니다.</p>
                </div>
            </article>


        </section>
    
    </main>
<%@ include file="/css/_footer.jsp"%>
</body>
</html>