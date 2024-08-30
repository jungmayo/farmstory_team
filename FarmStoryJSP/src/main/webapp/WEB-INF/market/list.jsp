<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/FarmStoryJSP/css/farmstory.css">
<title>장보기</title>
</head>
<style>
.num.current {
    color: #d3d3d3; 
    font-size: 16px; 
    transition: font-size 0.3s ease, color 0.3s ease;
}

.thumb a {
  display: inline-block; /* 또는 block */
  width: 120px;
  height: 120px;
  text-decoration: none;
}




</style>
<body>
	<%@ include file="/css/_header.jsp"%>
<div class="marketList">
	<main>
		<div class="titleEvent">
			<img src="../images/sub_top_tit2.png" alt="">
		</div>
		<section class="mainSection">
			<aside class="mainAside">
				<img src="../images/sub_aside_cate2_tit.png" alt="Event"
					class="eventList">
				<ul class="asideList">
					<li><a href="#"><img src="../images/sub_cate2_lnb1_ov.png"
							alt=""></a></li>
				</ul>
			</aside>
			<div class="container">
				<section>
					<article class="mainArticle">
						<nav class="articleNav">
							<img src="../images/sub_nav_tit_cate2_tit1.png" alt="event">
							<p>
								<img src="../images/sub_page_nav_ico.gif" alt="navIcon">
								HOME > 장보기 > <strong>장보기</strong>
							</p>
						</nav>
						<p>전체(10) | 과일 | 야채 | 곡류</p>

						<table>
							<thead>
								<tr>
									<th>이미지</th>
									<th>종류</th>
									<th>상품명</th>
									<th>할인</th>
									<th>포인트</th>
									<th>판매가격</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="product" items="${products}">
									<tr>
										<!-- 이미지 클릭해도 가지도록 -->
										<td class="thumb">
										<a href="/FarmStoryJSP/market/view.do?no=${product.prono}">
										<img src="${product.proimg1}">
										</a>
										</td>
										<td>${product.protype}</td>
										<td><a href="/FarmStoryJSP/market/view.do?no=${product.prono}">${product.proname}</a></td>
										<td>${product.prosale}%</td>
										<td>${product.propoint}p</td>
										<td><strong>${product.saleprice}</strong>
											<del>${product.proprice}<br>
											</del> <del>원</del></td>
									</tr>
								</c:forEach>
								</tr>
							</tbody>
						</table>
						<c:if test="${pageGroup.start > 1 }">
							<a href="/FarmStoryJSP/market/list.do?pg=${pageGroup.start - 1}"
								class="prev"><</a>
						</c:if>
						<div class="pagination">
							<c:forEach var="i" begin="${pageGroup.start}"
								end="${pageGroup.end}">
								<a href="/FarmStoryJSP/market/list.do?pg=${i}"
									class="num ${currentPage == i ? 'current':'off'}">[${i}]</a>
							</c:forEach>
						</div>
						<c:if test="${pageGroup.end < pageGroup.group}">
							<a href="/FarmStoryJSP/market/list.do?pg=${pageGroup.end - 1}"
								class="next">></a>
						</c:if>

					</article>

				</section>

			</div>

		</section>

	</main>
</div>
	<%@ include file="/css/_footer.jsp"%>
</body>
</html>