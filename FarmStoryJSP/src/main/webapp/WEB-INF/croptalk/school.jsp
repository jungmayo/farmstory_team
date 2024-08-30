<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="/FarmStoryJSP/css/article.css">
	<link rel="stylesheet" href="/FarmStoryJSP/css/farmstory.css">
  <title>Farmstory</title>
  <style>
    main {
      height : auto;
      width: 980px;
      margin: 0 auto;
    }
    .titleEvent {
      width: 980px;
      height: 184px;
      background-image: url("../images/sub_top_bg.jpg");
      position: relative;
    }
    .titleEvent > img {
      position: absolute;
      left : 10px;
      bottom: 10px;
    }
    .mainSection {
      display: flex;
    }
    .mainAside{
      width: 176px;
      height: 650px;
      box-sizing: border-box;
      background-image: url("../images/sub_aside_bg_line.png");
      background-repeat: no-repeat;
      background-position: 169px;
    }
    .mainAside > .eventList {
      margin: 16px 16px;
    }
    .asideList {
      width: 175px;
      height: 233px;
      background-image: url("../images/sub_aside_bg_lnb.png");
      position: relative;
    }
    .asideList > li:nth-child(1){
      display: inline-block;
      margin-top: 30px;
    }
    .articleNav {
      width: 762px;
      height: 72px;
      margin-left: 40px;
      border-bottom: 1px solid #c2c2c2;
      position: relative;
    }
    .articleNav > img {
      position: absolute;
      bottom: 5px;
    }
    .articleNav > p {
      position: absolute;
      bottom: 5px;
      right: 0px;
    }
    .articleNav > p > strong {
      color : rgb(124, 165, 64)
    }
    .mainArticle > p {
      margin-left: 40px;
      margin-top: 40px;
    }
    .mainArticle {
      margin: 0 0;
      width: 802px;
      height : auto;
      box-sizing: border-box;
      
    }
  </style>
</head>
<body>
<%@ include file="/css/_header.jsp"%>
  
  <!-- header 끝-->



  <main>
    <div class="titleEvent"><img src="../images/sub_top_tit3.png" alt=""></div>
    <section class="mainSection">
      <aside class="mainAside">
        <img src="../images/sub_aside_cate3_tit.png" alt="Event" class="eventList">
        <ul class="asideList">
          <li><a href="/FarmStoryJSP/article/list.do?group=croptalk&cate=story"><img src="../images/sub_cate3_lnb1.png" alt=""></a></li>
          <li><a href="/FarmStoryJSP/article/list.do?group=croptalk&cate=grow"><img src="../images/sub_cate3_lnb2.png" alt=""></a></li>
          <li><a href="/FarmStoryJSP/article/list.do?group=croptalk&cate=school"><img src="../images/sub_cate3_lnb3_ov.png" alt=""></a></li>
        </ul>
      </aside>

      <article class="mainArticle">
        <nav class="articleNav">
          <img src="../images/sub_nav_tit_cate3_tit3.png" alt="event">
          <p><img src="../images/sub_page_nav_ico.gif" alt="navIcon"> HOME > 농작물이야기 > <strong>귀농학교</strong></p>
        </nav>
        <c:set var="adminonly" value="false"/>
        <c:choose>
        	<c:when test="${type eq 'list'}">
        		<%@ include file="/css/_article.jsp"%>
        	</c:when>
        	<c:when test="${type eq 'view'}">
        		<%@ include file="/css/_article_view.jsp"%>
        	</c:when>
        	<c:when test="${type eq 'write'}">
        		<%@ include file="/css/_article_write.jsp"%>
        	</c:when>
        </c:choose>
      </article>
    </section>
  </main>


  
  <!-- footer 시작 -->
<%@ include file="/css/_footer.jsp"%>
</body>
</html>