<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrator</title>
    <link rel="stylesheet" href="/FarmStoryJSP/css/admin.css">
</head>
<body>
    <div id="wrap">
        <%@ include file="./_header.jsp"%>
        <main>
            <%@ include file="./_aside.jsp"%>
            <section>
                <h3>관리자 메인</h3>
                <article>
                    <h3>
                        <a href="./product/list.do">상품현황</a>
                        <a href="./product/list.do" class="more">더보기</a>
                    </h3>
                    <table>
                        <thead>
                            <tr>
                                <th>상품번호</th>
                                <th>상품명</th>
                                <th>구분</th>
                                <th>가격</th>
                                <th>재고</th>
                                <th>등록일</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.prono}</td>
                                <td>${product.proname}</td>
                                <td>${product.protype}</td>
                                <td class="price">${product.proprice}</td>
                                <td>${product.prostock}</td>
                                <td>${product.prorday}</td>
                            </tr>
                        	</c:forEach>
                        </tbody>
                    </table>
                </article>
                <article>
                    <h3>
                        <a href="./order/list.do">주문현황</a>
                        <a href="./order/list.do" class="more">더보기</a>
                    </h3>
                    <table>
                        <thead>
                            <tr>
                                <th>주문번호</th>
                                <th>상품명</th>
                                <th>판매가격</th>
                                <th>수량</th>
                                <th>배송비</th>
                                <th>합계</th>
                                <th>주문자</th>
                                <th>주문일</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="order" items="${orders}">
                            <tr>
                                <td>${order.orderno}</td>
                                <td>${order.orderproname}</td>
                                <td class="price">${order.orderproprice}</td>
                                <td class="stock">${order.orderstock}</td>
                                <td class="price">${order.orderprodeliveryfee}</td>
                                <td class="price">${order.orderpayment}</td>
                                <td>${order.orderusername}</td>
                                <td>${order.orderdate}</td>
                            </tr>
                        	</c:forEach>
                        </tbody>
                    </table>
                </article>
                <article>
                    <h3>
                        <a href="./user/list.do">회원현황</a>
                        <a href="./user/list.do" class="more">더보기</a>
                    </h3>
                    <table>
                        <thead>
                            <tr>
                                <th>회원아이디</th>
                                <th>이름</th>
                                <th>닉네임</th>
                                <th>휴대폰</th>
                                <th>이메일</th>
                                <th>등급</th>
                                <th>회원가입일</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.getUserId()}</td>
                                <td>${user.getUserName()}</td>
                                <td>${user.getUserNick()}</td>
                                <td>${user.getUserHp()}</td>
                                <td>${user.getUserEmail()}</td>
                                <td>${user.getUserGrade()}</td>
                                <td>${user.getUserRegdate()}</td>
                            </tr>
                        	</c:forEach>
                        </tbody>
                    </table>
                
                </article>
            </section>
        </main>
    </div>
    <%@ include file="./_footer.jsp"%>
</body>
</html>




