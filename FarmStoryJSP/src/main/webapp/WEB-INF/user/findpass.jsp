<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/FarmStoryJSP/css/farmstory.css">
<title>Farmstory</title>
<script>

//정규 표현식
const reName  = /^[가-힣]{2,10}$/
const reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

//이메일 유효성
let isEmailOk = false;

window.onload = function(){
	
	const findPassForm = document.getElementsByTagName('form')[0];
	const auth = document.getElementsByClassName('auth')[0];
	const btnSendEmail = document.getElementById("btnSendEmail");
	const btnauth = document.getElementById("btnauth");
	const resultEmail = document.getElementsByClassName('resultEmail')[0];
	
		btnSendEmail.addEventListener('click' , function(){
		
			const email = findPassForm.email.value;
			
			if(!email.match(reEmail)){
				resultEmail.innerText = '유효한 이메일 형식이 아닙니다.';
				resultEmail.style.color = 'red';
				return;
			}
			
			fetch('/FarmStoryJSP/user/checkfindUser.do?type=email&value='+email)
			.then(resp => resp.json())
			.then(data => {
				console.log(data);
				if(data.result > 0){
					resultEmail.innerText = '이메일 인증코드를 확인하세요.';
					resultEmail.style.color = 'green';
					auth.style.display = 'block';
				}else {
					resultEmail.innerText = '없는 이메일입니다.';
					resultEmail.style.color = 'red';
				}
			})
			.catch(err => {
				console.log(err);
			});
			
		});
		
		
		//인증번호 확인
		btnauth.addEventListener('click' , function(){
			const authnum = findPassForm.authnum.value;
			
			fetch('/FarmStoryJSP/user/checkfindUser.do' , {
				method : 'POST',
				body : JSON.stringify({"auth" : authnum})
			})
			.then(resp => resp.json())
			.then(data => {
				console.log(data);
				if(data.result > 0){
					resultEmail.innerText = '이메일이 인증 되었습니다.';
					resultEmail.style.color = 'green';
					isEmailOk = true;
				}else {
					resultEmail.innerText = '이메일이 인증에 실패했습니다.';
					resultEmail.style.color = 'red';
					isEmailOk = false;
				}
			})
			.catch(err => {
				console.log(err);
			})
		})
		
		
	
	
	findPassForm.onsubmit = function(){
		
		if(!isEmailOk){
			alert('이메일이 유효하지 않습니다.');
			return false;
		}
		return true;
		
	}
	

};	//onload 끝
</script>
<style>
main{
    width: 600px;
    margin: 95px auto;
}
h2{
    line-height: 15px;
    height: 14px;
    margin-bottom: 10px;
}
table{
    border-top: 2px solid black;
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 10px;
}
th,td{
    border: 1px solid #E9E9E9;
}
th{
    width: 120px;
    text-align: left;
    padding: 12px;
}
td{
    padding: 10px;
}
button{
    cursor: pointer;
}
input{
    border: 1px solid #E4EAEC;
    box-shadow: none;
    width: 164px;
    height: 24px;
    color: #757575;
    background-color: #F7F7F7;
    outline-style: none;
    appearance: none;
    margin: 5px;
    box-sizing: border-box;
    padding: 5px;
}
button{
    border-radius: 0;
    border: 1px solid #bebebe;
    color: #000;
    background-color: #efefef;
    padding: 5px;
}
section>p{
    line-height: 20px;
}
main>a{
    width: auto;
    height: 35px;
    display: flex;
    float: right;
    border: 1px solid #bebebe;
    padding: 0 10px;
    margin-top: 20px;
    margin-left: 3px;
    align-items: center;
    justify-content: center;
}
a, a:visited{
    color: #000;
    text-decoration: none;
}

.auth {
	display : none;
}
</style>
</head>
<body>
	<%@ include file="/css/_header.jsp"%>

	<!-- header 끝-->



    <main>
        <section>
            <form action="/FarmStoryJSP/user/findpass.do" id="findPassForm" method = "POST">
                <h2>비밀번호 찾기</h2>
                <table>
                    <tbody>
                        <tr>
                            <th><label for="userid">아이디</label></th>
                            <td><input type="text" placeholder="아이디 입력" required autofocus name="uid"></td>
                        </tr>
                        <tr>
                            <th>이메일</th>
                            <td>
                                <input type="email" placeholder="이메일 입력" required name="email">
                                <button id="btnSendEmail" type="button">인증번호 받기</button><br>
                                <span class="resultEmail"></span>
                             
                              <div class="auth">
                                <input type="text" placeholder="인증번호 입력" name="authnum">
                                <button id="btnauth" type="button">확인</button>
                              </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            
            <p>
                비밀번호를 찾고자 하는 아이디와 이메일을 입력해주세요.<br>
                회원가입시 입력한 아이디와 이메일 주소가 같아야, 인증번호를 받을 수 있습니다.<br>
                인증번호를 입력 후 확인 버튼을 누르세요.
            </p>
        <input type="submit" class="btnSubmit" value="다음"/>
        <a href="/FarmStoryJSP/user/login.do">취소</a>
        </form>
        </section>
    </main>



	<!-- footer 시작 -->
	<%@ include file="/css/_footer.jsp"%>
</body>
</html>