<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <%@ include file="../_header.jsp"%>
        <main>
        <%@ include file="../_aside.jsp"%>
        	<section class="prodReg">
	            <section>
	                <h3>상품등록</h3>
	                <article>
	                    <form action="/FarmStoryJSP/admin/product/register.do" method="post" enctype="multipart/form-data">
	                        <table>
	                            <tbody>
	                                <tr>
	                                    <td>상품명</td>
	                                    <td><input type="text" name="name" required></td>
	                                </tr>
	                                <tr>
	                                    <td>종류</td>
	                                    <td>
	                                    	<select name="type">
	                                            <option selected disabled>종류</option>
	                                            <option value="과일">과일</option>
	                                            <option value="곡류">곡류</option>
	                                            <option value="야채">야채</option>
	                                    	</select>
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>가격</td>
	                                    <td><input type="number" name="price" required></td>
	                                </tr>
	                                <tr>
	                                    <td>포인트</td>
	                                    <td><input type="number" name="point" required>&nbsp;포인트는 가격의 1%</td>
	                                </tr>
	                                <tr>
	                                    <td>할인</td>
	                                    <td>
	                                        <select name="sale">
	                                            <option value="0">없음</option>
	                                            <option value="5" selected>5%</option>
	                                            <option value="10">10%</option>
	                                            <option value="15">15%</option>
	                                            <option value="20">20%</option>
	                                        </select>
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>배송비</td>
	                                    <td>
	                                        <label><input type="radio" name="fee" value="2000" checked> 2,000원</label>&nbsp;
	                                        <label><input type="radio" name="fee" value="3000"> 3,000원</label>&nbsp;
	                                        <label><input type="radio" name="fee" value="5000"> 5,000원</label>&nbsp;
	                                        <label><input type="radio" name="fee" value="0"> 무료</label>
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>재고</td>
	                                    <td><input type="number" name="stock" required></td>
	                                </tr>
	                                <tr>
	                                    <td>상품이미지</td>
	                                    <td>
	                                        <p>상품목록 이미지(약 120 x 120)</p>
	                                        <input type="file" name="img1" required>
	                                        <p>기본정보 이미지(약 240 x 240)</p>
	                                        <input type="file" name="img2" required>
	                                        <p>상품설명 이미지(약 750 x auto)</p>
	                                        <input type="file" name="img3" required>
	                                    
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>기타</td>
	                                    <td><textarea name="etc"></textarea></td>
	                                </tr>
	                            </tbody>
	                        </table>
	                        <h3>
	                            <a href="./list.do" class="btn">취소</a> &nbsp;
	                            <input type="submit" value="상품등록"></input>
	                        </h3>
	                    </form>
	                </article>
	            </section>
        	</section>
        </main>
    </div>
        <%@ include file="../_footer.jsp"%>
</body>
<script>
        const now = document.querySelector('a#prodreg[href]');
        if (now) {
            now.classList.add("now");
        }
</script>
</html>