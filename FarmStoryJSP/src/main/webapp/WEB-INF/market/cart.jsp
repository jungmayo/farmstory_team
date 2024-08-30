<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
	<link rel="stylesheet" href="/FarmStoryJSP/css/farmstory.css">
	<script>
		window.onload = function(){

			function updateTotal() {
				const selectedCheckboxes1 = document.querySelectorAll('.select:checked');
				let totalItemCount = 0;
	            let totalProductPrice = 0;
	            let totalDiscount = 0;
	            let totalPoints = 0;
	            let maxDeliveryFee = 0;
				for (let checkbox of selectedCheckboxes1) {
	                const row = checkbox.closest('tr');
	                const quantity_column = parseInt(row.querySelector('.quantity-column').innerText.trim(), 10);
	                const discount_column = parseInt(row.querySelector('.discount-column').innerText.replace('%', '').trim(), 10);
	                const points_column = parseInt(row.querySelector('.points-column').innerText.replace('p', '').trim(), 10);
	                const price_column = parseInt(row.querySelector('.price-column').innerText.replace('원', '').trim(), 10);
	                const delivery = parseInt(row.getElementsByClassName('delivery')[0].value.trim(), 10);
	                
	                // 합계 계산
	                const itemTotalPrice = quantity_column * price_column;
	                const itemDiscount = itemTotalPrice * (discount_column / 100);
	                
	                totalItemCount += quantity_column;
	                totalProductPrice += itemTotalPrice;
	                totalDiscount += itemDiscount;
	                totalPoints += points_column;
	                maxDeliveryFee = Math.max(maxDeliveryFee, delivery);
	            }
				const totalOrderAmount = totalProductPrice - totalDiscount + maxDeliveryFee;
				// UI 업데이트
	            document.getElementById('total-item-count').innerText = totalItemCount;
	            document.getElementById('total-product-price').innerText = totalProductPrice + "원";
	            document.getElementById('total-discount').innerText = totalDiscount+ "원";
	            document.getElementById('total-delivery-fee').innerText = maxDeliveryFee+ "원";
	            document.getElementById('total-points').innerText = totalPoints +"p";
	            document.getElementById('total-order-amount').innerText = totalOrderAmount+ "원";
		    }
			document.addEventListener('click', function(e) {
		        const selectall = document.querySelector('.selectall');
		        const checkboxes = document.querySelectorAll('.select');
	
		        // 전체 선택 체크박스 클릭 시
		        if (e.target.classList.contains('selectall')) {
		            selectAll(e.target);
		            updateTotal();
		        }
	
		        // 개별 체크박스 클릭 시
		        if (e.target.classList.contains('select')) {
		            updateSelectAllCheckbox();
		            updateTotal();
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
		            console.log(selectedCheckboxes);
		            let selectedIds = [];
	
		            for (let checkbox of selectedCheckboxes) {
		                const row = checkbox.closest('tr');
		                const productNo = row.querySelector('.no').value.trim();
		                selectedIds.push(productNo);
		            }
	
		            if (selectedIds.length === 0) {
		                alert('삭제하려는 상품을 선택하세요.');
		                return;
		            }
	
		            if (!confirm('선택한 상품을 정말 삭제하시겠습니까?')) {
		                return;
		            }
	
	
		            fetch('/FarmStoryJSP/market/cart.do', {
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
		        }//del
		        if (e.target.classList.contains('order')) {
		            e.preventDefault();
		            const selectedCheckboxes = document.querySelectorAll('.select:checked');
		            console.log(selectedCheckboxes)
		            let selectedIds = [];
	
		            for (let checkbox of selectedCheckboxes) {
		                const row = checkbox.closest('tr');
		                const productNo = row.querySelector('.no').value.trim();
		                selectedIds.push(productNo);
		            }
	
		            if (selectedIds.length === 0) {
		                alert('결제하려는 상품을 선택하세요.');
		                return;
		            }
		            
		            location.href="/FarmStoryJSP/market/order.do?ordercheck=2&ids="+selectedIds;
		            
		            
		        }//order
		    }); // EventListener END
		};
	
	</script>
</head>
<body>
<%@ include file="/css/_header.jsp"%>
<div class="marketCart">
    <main>
        <div class="background">
            <img src="../images/sub_top_tit2.png" alt="1">
        </div>
        <section>
            <aside>
                <img src="../images/sub_aside_cate2_tit.png" alt="장보기" class="intro">
            <ul class="sideimage">
                <li><a href="./introduction.html" class="greet"><img src="../images/sub_cate2_lnb1_ov.png" alt="2"></a></li>
            </ul>
        </aside>
            <article>
                <nav class="articleNav">
                    <img src="../images/sub_nav_tit_cate2_tit1.png" alt="장보기">
                    <p><img src="../images/sub_page_nav_ico.gif" alt="navIcon"> HOME > 장보기 > <strong>장보기</strong></p>
                </nav>
                <div class="all"><a href="#">장바구니 전체(${cartDto.size()})</a></div>
                <table class="cart">
                    <thead>
                        <tr>
                            <th id="tt"><input type="checkbox" class="selectall"></th>
                            <th>이미지</th>
                            <th id="tt">종류</th>
                            <th>상품명</th>
                            <th id="tt">수량</th>
                            <th id="tt">할인</th>
                            <th id="tt">포인트</th>
                            <th id="tt">가격</th>
                            <th id="tt">소계</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<!-- 장바구니가 비어있을 때 -->
                        <c:if test="${empty cartDto}">
				            <tr>
				                <td colspan="9" style="color: #8d8d8d;">장바구니에 상품이 없습니다.</td>
				            </tr>
				        </c:if>
                        <c:if test="${not empty cartDto}">
				            <c:forEach var="cart" items="${cartDto}">
				                <tr>
				                    <!-- 체크박스 -->
				                    <td id="dd">
				                        <input type="checkbox" class="select">
				                    </td>
				                    <!-- 상품 이미지 -->
				                    <td>
				                        <a href="#"><img src="${cart.proimg}" alt="${cart.proname}"></a>
				                    </td>
				                    <!-- 상품 유형 -->
				                    <td id="dd">${cart.protype}</td>
				                    <!-- 상품 이름 -->
				                    <td>
				                        <a href="/FarmStoryJSP/market/view?no=${cart.cartprono}" style="font-weight: bold;">${cart.proname}</a>
				                        <input type="hidden" class="no" value="${cart.cartprono}">
				                    </td>
				                    <!-- 수량 -->
									<td id="dd" class="quantity-column">${cart.cartstock}</td>
									<!-- 할인율 -->
									<td id="dd" class="discount-column">${cart.prosale}%</td>
									<!-- 포인트 -->
									<td id="dd" class="points-column">${cart.propoint}p</td>
									<!-- 가격 -->
									<td id="dd" class="price-column">${cart.proprice}원
									<input type="hidden" class="delivery" value="${cart.prodeliveryfee}">
									</td>
									
				                    <!-- 합계 -->
				                    <td id="dd">
				                        <span style="font-weight: bold;">
				                            ${cart.cartstock * cart.proprice - (cart.cartstock * cart.proprice * cart.prosale / 100)}
				                        </span>원
				                    </td>
				                </tr>
				            </c:forEach>
				        </c:if>
                        
                    </tbody>
                </table>
                <a href=""><input class="del" type="button" value="선택삭제"></a>
                <div class="total">
                    <table class="downtable">
        				<caption>전체합계</caption>
				        <tbody>
				            <tr>
				                <td>상품수</td>
				                <td id="total-item-count">0</td>
				            </tr>
				            <tr>
				                <td>상품금액</td>
				                <td id="total-product-price">0원</td>
				            </tr>
				            <tr>
				                <td>할인금액</td>
				                <td id="total-discount">0원</td>
				            </tr>
				            <tr>
				                <td>배송비</td>
				                <td id="total-delivery-fee">0원</td>
				            </tr>
				            <tr>
				                <td>포인트</td>
				                <td id="total-points">0원</td>
				            </tr>
				            <tr>
				                <td>전체주문금액</td>
				                <td id="total-order-amount">0원</td>
				            </tr>
				        </tbody>
				    </table>
                    <a href="#"><input type="button" class="order" value="주문하기"></a>
                </div>
            </article>
        </section>
    </main>
    </div>
<%@ include file="/css/_footer.jsp"%>
</body>
</html>