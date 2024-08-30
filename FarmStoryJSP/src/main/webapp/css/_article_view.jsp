<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="view">
                <table>
                    <tr>
                        <td>제목</td>
                        <td><input type="text" name="title" value="${articleDto.artTitle}" readonly/></td>
                    </tr>
                    <c:if test="${articleDto.artFile > 0}">
                    <tr>
                        <td>첨부파일</td>
                        <td>
                        <c:forEach var="file" items="${articleDto.artFiles}">
	                        <p style="margin:4px 0">
	                            <a href="/FarmStoryJSP/file/download.do?fno=${file.fno}">${file.oName}</a>
	                            <span>${file.download}회 다운로드</span>
	                        </p>
                        </c:forEach>
                        </td>
                    </tr>
                    </c:if>
                    <tr>
                        <td>내용</td>
                        <td>
                            <textarea name="content" readonly>${articleDto.artContent}</textarea>
                        </td>
                    </tr>
                </table>
                <div>
                	<c:if test="${(sessUser.userId eq articleDto.artWriter) || (sessUser.userRole eq 'admin')}">
	                	<a href="/FarmStoryJSP/article/delete.do?group=${group}&cate=${cate}&artNo=${articleDto.artNo}" class="btnDelete">삭제</a>
	                    <a href="#" class="btnModify">수정</a>
                	</c:if>
                    
                    <a href="/FarmStoryJSP/article/list.do?group=${group}&cate=${cate}" class="btnList">목록</a>
                </div>  
                
                <!-- 댓글리스트 -->
                <section class="commentList">
                    <h3>댓글목록</h3>
                    <c:forEach var="comment" items="${comments}">
	                    <article class="comment">
	                        <span>
	                            <span>${comment.comRdate}</span>
	                            <span>${comment.nick}</span>
	                        </span>
	                        <textarea name="comment" readonly>${comment.comContent}</textarea>
	                        
	                        <c:if test="${(sessUser.userId eq comment.comWriter) || (sessUser.userRole eq 'admin')}">
		                        <div>
		                        	<!-- HTML 사용자 정의 속성을 이용한 삭제/수정 -->
		                            <a href="/FarmStoryJSP/comment/delete.do?group=${group}&cate=${cate}&artNo=${articleDto.artNo}&comNo=${comment.comNo}" class="commentRemove" data-no="${comment.comNo}">삭제</a>
		                            <a href="#" class="commentCancel" data-no="${comment.comNo}">취소</a><!-- style.css 364라인 display: none; 처리하기 -->
		                            <a href="#" class="commentModify" data-no="${comment.comNo}">수정</a>
		                        </div>
	                        </c:if>
	                    </article>
                    </c:forEach>
                    <c:if test="${empty comments}">
	                    <p class="empty">등록된 댓글이 없습니다.</p>
                    </c:if>
                </section>
                <!-- 댓글입력폼 -->
                 <c:if test="${not empty sessUser.userId}">
                <section class="commentForm">
                    <h3>댓글쓰기</h3>
                    <form action="/FarmStoryJSP/comment/write.do" method="post" name="commentForm">
                    	<input type="hidden" name="parent" value="${articleDto.artNo}"/>
                    	<input type="hidden" name="writer" value="${sessUser.userId}"/>
                    	<input type="hidden" name="group" value="${group}"/>
                    	<input type="hidden" name="cate" value="${cate}"/>
                    	<input type="hidden" name="artNo" value="${articleDto.artNo}"/>
                        <textarea name="comment"></textarea>
                        <div>
                            <a href="#" class="btnCancel">취소</a>
                            <input type="submit" class="btnWrite" value="작성완료"/>
                        </div>
                    </form>
                </section>
    			</c:if>
            </section>