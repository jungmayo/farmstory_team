<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header>
        <div>
          <article class="top">
            <img src="/FarmStoryJSP/images/head_top_line.png" alt="">
            <p>
              <a href="/FarmStoryJSP/index.do">HOME |</a>  
                    <c:choose>
                        <c:when test="${sessUser == null}">
                            <a href="/FarmStoryJSP/user/login.do">로그인 |</a>
                            <a href="/FarmStoryJSP/user/terms.do">회원가입 |</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/FarmStoryJSP/user/logout.do" class="logout">로그아웃 |</a>
                            <a href="/FarmStoryJSP/user/myinfo.do" class="myinfos">나의설정 |</a>
                            <c:choose>
                            	<c:when test="${sessUser.getUserRole() == 'user'}">
                            		<a href="/FarmStoryJSP/market/cart.do">장바구니(${sessUser.getUserCart()}) |</a>
                            	</c:when>
                            	<c:otherwise>
              						<a href="/FarmStoryJSP/admin/index.do">관리자 |</a>
                            	</c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
              
              <a href="/FarmStoryJSP/article/list.do?group=community&cate=faq">고객센터</a>

            </p>
            <a href="/FarmStoryJSP/index.do" class="headerLogo"><img src="/FarmStoryJSP/images/logo.png" alt="팜스토리 로고"></a>
            <a href="/FarmStoryJSP/market/list.do" class="headerTxt"><img src="/FarmStoryJSP/images/head_txt_img.png" alt="3만원이상 무료배송"></a>
          </article>
        </div>
        
        <div>
          <nav>
            <ul class="nav">
              <li><a href="/FarmStoryJSP/introduction/introduction.do" class="navInfo"><img src="/FarmStoryJSP/images/head_menu1.png" alt="headerMenu1"></a></li>
              <li><a href="/FarmStoryJSP/market/list.do" class="navShop"><img src="/FarmStoryJSP/images/head_menu2.png" alt="headerMenu2"><img src="/FarmStoryJSP/images/head_menu_badge.png" alt="" class="sale"></a></li>
              <li><a href="/FarmStoryJSP/article/list.do?group=croptalk&cate=story" class="navFarm"><img src="/FarmStoryJSP/images/head_menu3.png" alt="headerMenu3"></a></li>
              <li><a href="/FarmStoryJSP/article/list.do?group=event&cate=info" class="navEven"><img src="/FarmStoryJSP/images/head_menu4.png" alt="headerMenu4"></a></li>
              <li><a href="/FarmStoryJSP/article/list.do?group=community&cate=notice" class="navComm"><img src="/FarmStoryJSP/images/head_menu5.png" alt="headerMenu5"></a></li>
            </ul>
          </nav>
        </div>
      
</header>