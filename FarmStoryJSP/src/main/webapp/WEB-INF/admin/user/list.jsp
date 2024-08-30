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
		<%@ include file="../_header.jsp"%>
		<main>
			<%@ include file="../_aside.jsp"%>
			<section class="userlist">
				<section>
					<h3>회원목록</h3>
					<article>
						<table>
							<thead>
								<tr>
									<th><input type="checkbox" name="selectall"></th>
									<th>아이디</th>
									<th>이름</th>
									<th>별명</th>
									<th>이메일</th>
									<th>휴대폰</th>
									<th>등급</th>
									<th>가입일</th>
									<th>확인</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="user" items="${Users}">
								<tr>
									<td><input type="checkbox" name="select"></td>
									<td>${user.getUserId()}</td>
									<td>${user.getUserName()}</td>
									<td>${user.getUserNick()}</td>
									<td>${user.getUserEmail()}</td>
									<td>${user.getUserHp()}</td>
									<td>
							            <select class="usergrade" data-id="${user.getUserId()}">
							                <option value="1" <c:if test="${user.getUserGrade() eq 1}">selected</c:if>>1</option>
							                <option value="2" <c:if test="${user.getUserGrade() eq 2}">selected</c:if>>2</option>
							                <option value="3" <c:if test="${user.getUserGrade() eq 3}">selected</c:if>>3</option>
							                <option value="4" <c:if test="${user.getUserGrade() eq 4}">selected</c:if>>4</option>
							            </select>
							        </td>
									<td>${user.getUserRegdate()}</td>
									<td><a href="/FarmStoryJSP/user/myinfo.do?uid=${user.getUserId()}">[상세확인]</a></td>
								</tr>
							</c:forEach>
								
							</tbody>
						</table>
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
<script>
	const now = document.querySelector('a#userlist[href]');
	if (now) {
		now.classList.add("now");
	}
	
	function checkSelectAll(checkbox)  {
		  const selectall 
		    = document.querySelector('input[name="selectall"]');
		  
		  if(checkbox.checked === false)  {
		    selectall.checked = false;
		  }
		}

		function selectAll(selectAll)  {
		  const checkboxes 
		     = document.getElementsByName('select');
		  
		  checkboxes.forEach((checkbox) => {
		    checkbox.checked = selectAll.checked
		  })
		}
	
		function updateUserGrade(id, grade) {
			console.log(`User ID: ${id}`);  // 디버깅 로그
		    console.log(`New Grade: ${grade}`);  // 디버깅 로그
	    	
	    	const data = {
	   		        id: id,
	   		        grade: grade
   		    };

	    	
	        fetch('/FarmStoryJSP/admin/user/list.do', {
	            method: 'POST',
		        headers: {
		            'Content-Type': 'application/json'
		        },
		        body: JSON.stringify(data)
	        })
	        	.then(resp=>resp.json())
	        	.then(data=>{
	        		console.log(data);
	    	        if (data.result != 0) {
	    	            alert('정보가 성공적으로 수정되었습니다.');
	    	        } else {
	    	            alert('정보 수정에 실패했습니다.');
	    	        }
	        	})
	        	.catch(err => {
		        console.error(err);
		        alert('업데이트에 실패했습니다.');
		    });
	}
	document.addEventListener('DOMContentLoaded', function() {
	    // 모든 select 요소에 대해 이벤트 리스너 등록
	    document.querySelectorAll('.usergrade').forEach(selectElement => {
	        selectElement.addEventListener('input', async function() {
	            const id = this.dataset.id;  // data-user-id에서 값 가져오기
	            const grade = this.value;

	    		console.log(`User ID: ${id}`);  // 디버깅 로그
	    	    console.log(`New Grade: ${grade}`);  // 디버깅 로그
	            // 사용자 등급 업데이트
	            if(confirm('정말 변경하시겠습니까?')){
	            	updateUserGrade(id, grade);
	            }else{
	            	 return;
	            }
	        });
	    });
	});
</script>
</html>




