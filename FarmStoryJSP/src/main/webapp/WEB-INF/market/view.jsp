<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="/FarmStoryJSP/css/farmstory.css">
    <title>상품상세보기</title>
	<script>
	    window.onload = function () {

	        updateTotal();
	        const stockInput = document.querySelector('input[class="stock"]');
	        stockInput.addEventListener('input', function() {
	            updateTotal();
	        });
	        
	        document.addEventListener('click', function(e) {
	        	const pronoElement = document.getElementsByClassName('prono')[0];
	            const prono = pronoElement.innerText;
	            
	            const stockInput = document.querySelector('input[class="stock"]');
	            const stock = stockInput.value;
	        	
	        	if(e.target.classList.contains('cart')){
	        		e.preventDefault();
	        		
	        		const data = {
	        		        prono: prono,
	        		        stock: stock
	        		    };

	        		    fetch('/FarmStoryJSP/market/view.do', {
	        		        method: 'POST',
	        		        headers: {
	        		            'Content-Type': 'application/json'
	        		        },
	        		        body: JSON.stringify(data)
	        		    })
			            .then(resp => resp.json())
			            .then(data => {
			            	console.log(data);
			            	if(data.result != 0){
			            		if(confirm('장바구니에 상품을 담았습니다.\n 장바구니로 이동하시겠습니까?')){
			            			location.href = "/FarmStoryJSP/market/cart.do";
			            		}else{
			            			updateTotal();
			            		}
			            	}else{
			            		alert('장바구니에 상품을 담지 못했습니다.');
			            	}
			            })
			            .catch(err => {
			            	alert('오류가 발생했습니다. 관리자에게 문의하세요.');
			            	console.log(err);
			            });
	        	} // 장바구니 END
	        	// 바로주문 버튼 클릭
	        	if(e.target.classList.contains('order')){
					e.preventDefault();
					location.href = "/FarmStoryJSP/market/order.do?no="+prono+"&stock="+stock+"&ordercheck=1";
	        	}// 바로주문 END
	        	
	        	
	        });// EventListener END
	        
		    function updateTotal() {
		        const price = ${ProductDto.proprice};
		        const quantity = parseInt(document.querySelector('input[class="stock"]').value, 10);
		
		        if (!isNaN(price) && !isNaN(quantity)) {
		            const total = price * quantity;
		            document.querySelector('.colorRed').textContent = total+`원`;
		        } else {
		            // 가격이나 수량이 올바르지 않은 경우
		            document.querySelector('.colorRed').textContent = '0원';
		        }
		    }
	    } //onload END
	    
	</script>
</head>
<body>
<%@ include file="/css/_header.jsp"%>
	<div class="marketView">
    <main>
        <div class="titleEvent"><img src="../images/sub_top_tit2.png" alt=""></div>
        <section class="mainSection">
            <aside class="mainAside">
                <img src="../images/sub_aside_cate2_tit.png" alt="Event" class="eventList">
                <ul class="asideList">
                    <li><a href="#"><img src="../images/sub_cate2_lnb1_ov.png" alt=""></a></li>
                </ul>
            </aside>
            <div class="container">
                <section>
                    <article class="mainArticle">
                        <nav class="articleNav">
                            <img src="../images/sub_nav_tit_cate2_tit1.png" alt="event">
                            <p><img src="../images/sub_page_nav_ico.gif" alt="navIcon"> HOME > 장보기 >
                                <strong>장보기</strong></p>
                        </nav>
                        <p class="section-title">기본정보</p>
                        <div class="product-info">
                            <img src="${ProductDto.proimg1}" alt="상품 이미지" class="thumb">
                            <div class="product-details">
                            	<form>
	                                <table>
	                                    <tr>
	                                        <th>상품명</th>
	                                        <td>${ProductDto.proname}</td>
	                                    </tr>
	                                    <tr>
	                                        <th>상품코드</th>
	                                        <td class="prono">${ProductDto.prono}</td>
	                                    </tr>
	                                    <tr> 
	                                        <th>배송비</th>
	                                        <td>${ProductDto.prodeliveryfee}원 <span>3만원이상 무료배송</span></td>
	                                    </tr>
	                                    <tr>
	                                        <th>판매가격</th>
	                                        <td>${ProductDto.proprice}원</td>
	                                    </tr>
	                                    <tr>
	                                        <th>구매수량</th>
	                                        <td><input type="number" size="5" max="${ProductDto.prostock}" class="stock" value="1"></td>
	                                    </tr>
	                                    <tr>
	                                        <th>합계</th>
	                                        <td class="colorRed">원</td>
	                                    </tr>
	                                </table>
	                                <div class="button-container">
			                             <!-- 장바구니 버튼 -->
									    <button type="button" class="cart" id="cart-button">장바구니</button>
									    <!-- 바로구매 버튼 -->
									    <button type="button" class="order" id="buy-button">바로구매</button>
	                                </div>
                            	</form>
                            </div>
                        </div>
                        
                        <div>
                            <p class="section-title">상품설명</p>
                            <img src="${ProductDto.proimg2}" alt="상품 기본설명">
                            <img src="${ProductDto.proimg3}" alt="상품 상세설명">
                        </div>
                    </article>
                    <div>
                        <p class="section-title">배송정보</p>
                        <p>입금하신 이후 택배송장버호는 SMS(문자서비스)를 통해 고객님께 안내해드립니다.</p>
                        <p class="section-title">교환/반품</p>
                        <table class="changeInformation">
                            <tr>
                                <th>교환 반품이 가능한 경우</th>
                                <td>팜스토리 상품에 하자가 있거나 불량인 경우<br>
                                    채소, 과일, 양곡등의 심품은 만1일 이내<br>
                                    기타 상품은 수령일로부터 영업일 기준 일주일 이내<br>
                                    받으신 상품이 표시사항과 다른 경우에는 받으신 날로부터 일주일 이내
                                </td>
                            </tr>
                            <tr>
                                <th>교환 반품이 불가능한 경우</th>
                                <td>신선 식품의 경우 단순히 마음에 들지 않는 경우<br>
                                    단순 변심으로 상품의 가치가 훼손되어 판매가 어려운 경우<br>
                                </td>
                            </tr>
                        </table>
                    </div>
                </section>
                
            </div>
           
        </section>
        
    </main>
   </div>
<%@ include file="/css/_footer.jsp"%>
</body>
</html>