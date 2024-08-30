<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="list">
                <article>
                    <table border="0">
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>글쓴이</th>
                            <th>날짜</th>
                            <th>조회</th>
                        </tr>
                        <c:forEach var="article" items="${articles}">
	                        <tr>
	                            <td>${pageStartNum}</td>
	                            <td><a href="/FarmStoryJSP/article/view.do?group=${group}&cate=${cate}&artNo=${article.artNo}">${article.artTitle}</a>&nbsp;[${article.artComment}]</td>
	                            <td>${article.nick}</td>
	                            <td>${article.artRdate}</td>
	                            <td>${article.artHit}</td>
	                        </tr>
	                        <!-- 한번 반복할때마다 pageStartNum을 1씩 차감 -->
	                        <c:set var="pageStartNum" value="${pageStartNum-1}"/>
                        </c:forEach>
                    </table>
                </article>

                <!-- 페이지 네비게이션 -->
                <div class="paging">
                	<c:if test="${pageGroup.start > 1}">
                    	<a href="/FarmStoryJSP/article/list.do?group=${group}&cate=${cate}&pg=${pageGroup.start-1}" class="prev">이전</a>
                    </c:if>
                    <c:forEach var="i" begin="${pageGroup.start}" end="${pageGroup.end}">
                    	<a href="/FarmStoryJSP/article/list.do?group=${group}&cate=${cate}&pg=${i}" class="num ${currentPage == i ? 'current':'off'}">${i}</a>
                    </c:forEach>
                    <c:if test="${pageGroup.end < lastPageNum}">
                    	<a href="/FarmStoryJSP/article/list.do?group=${group}&cate=${cate}&pg=${pageGroup.end+1}" class="next">다음</a>
                    </c:if>
                </div>
				
				<c:if test="${((adminonly eq 'false') || (sessUser.userRole eq 'admin')) && not empty sessUser.userId}">
				<!-- 글쓰기 버튼 -->
                <a href="/FarmStoryJSP/article/write.do?group=${group}&cate=${cate}" class="btnWrite">글쓰기</a>
				</c:if>
                
            </section>