<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Administrator</title>
	<link rel="stylesheet" href="/FarmStoryJSP/css/admin.css">
	<script>
		window.onload = function() {
		    const now = document.querySelector('a#prodlist[href]');
		    if (now) { 
		        now.classList.add("now"); 
		    }
	
		    document.addEventListener('click', function(e) {
		        const selectall = document.querySelector('.selectall');
		        const checkboxes = document.querySelectorAll('.select');
	
		        // 전체 선택 체크박스 클릭 시
		        if (e.target.classList.contains('selectall')) {
		            selectAll(e.target);
		        }
	
		        // 개별 체크박스 클릭 시
		        if (e.target.classList.contains('select')) {
		            updateSelectAllCheckbox();
		        }
	
		        function selectAll(selectAllCheckbox) {
		            checkboxes.forEach(checkbox => {
		                checkbox.checked = selectAllCheckbox.checked;
		            });
		        }
	
		        function updateSelectAllCheckbox() {
		            const allChecked = Array.from(checkboxes).every(checkbox => checkbox.checked);
		            selectall.checked = allChecked;
		        }
	
		        // 삭제 버튼 클릭 시
		        if (e.target.classList.contains('del')) {
		            e.preventDefault();
	
		            const selectedCheckboxes = document.querySelectorAll('.select:checked');
		            let selectedIds = [];
	
		            for (let checkbox of selectedCheckboxes) {
		                const row = checkbox.closest('tr');
		                const productNo = row.querySelector('.no1').value.trim();
		                console.log(productNo);
		                selectedIds.push(productNo);
		            }
	
		            if (selectedIds.length === 0) {
		                alert('삭제하려는 상품을 선택하세요.');
		                return;
		            }
	
		            if (!confirm('선택한 상품을 정말 삭제하시겠습니까?')) {
		                return;
		            }
	
	
		            fetch('/FarmStoryJSP/admin/product/list.do', {
		                method: 'DELETE',
		                headers: {
		                    'Content-Type': 'application/json'
		                  },
		                  body: JSON.stringify(selectedIds)
		            })
		            .then(resp => resp.json())
		            .then(data => {
		                if (data.success) {
		                    alert('삭제되었습니다.');
		                    location.reload();
		                } else {
		                    alert('삭제에 실패했습니다.');
		                }
		            })
		            .catch(err => {
		                console.error('Error:', err);
		                alert('삭제 중 오류가 발생했습니다.');
		            });
		        }
		    }); // EventListener END
		}; // onload END

	</script>
</head>
<body>
	<div id="wrap">
		<%@ include file="../_header.jsp"%>
		<main>
			<%@ include file="../_aside.jsp"%>
			<section class="prodList">
				<section>
					<h3>상품목록</h3>
					<article>
						<table>
							<thead>
								<tr>
									<th><input type="checkbox" class="selectall"></th>
									<th>사진</th>
									<th>상품번호</th>
									<th>상품명</th>
									<th>구분</th>
									<th>가격</th>
									<th>재고</th>
									<th>등록일</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="product" items="${Products}">
								<tr>
									<td><input type="checkbox" class="select"></td>
									<td><img src="${product.proimg1}"></td>
									<td class="no">${product.prono}<input type="hidden" class="no1" value="${product.prono}"></td>
									<td>${product.proname}</td>
									<td>${product.protype}</td>
									<td class="price">${product.proprice}</td>
									<td>${product.prostock}</td>
									<td>${product.prordate}</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</article>
					<article>
						<h3>
							<a href="#" class="del">선택삭제</a> <a href="/FarmStoryJSP/admin/product/register.do" class="btn">상품등록</a>
						</h3>
					</article>
					<article class="paging">
						<p>
							<c:if test="${PageGroup.start > 1}">
								<a href="?page=${PageGroup.start-1}"> &lt; </a>
							</c:if>
							<c:forEach var="i" begin="${PageGroup.start}" end="${PageGroup.end}">
							<c:if test="${i<=LastPage}">
								<a href="?page=${i}" class="num ${i eq Current?'current':'off'}">[${i}]</a>
							</c:if>
							</c:forEach>
							<c:if test="${PageGroup.end < LastPage}">
								<a href="?page=${PageGroup.end+1}"> &gt; </a>
							</c:if>

						</p>
					</article>
				</section>
			</section>
		</main>
	</div>
	<%@ include file="../_footer.jsp"%>
</body>

</html>




