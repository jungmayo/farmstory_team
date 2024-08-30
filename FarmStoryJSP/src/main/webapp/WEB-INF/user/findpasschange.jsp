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
const rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;

let isPassOk  = false;


window.onload = function(){
	
	const findPasschangeForm = document.getElementsByTagName('form')[0];
	const resultPass = document.getElementsByClassName('resultPass')[0];
	

	findPasschangeForm.pass2.addEventListener('focusout' , function(){
		
		const pass1 = findPasschangeForm.pass1.value;
		const pass2 = findPasschangeForm.pass2.value;
		
		if(!pass1.match(rePass)){
			resultPass.innerText = '유효하지 않은 비밀번호입니다.';
			resultPass.style.color = 'red';
			return;
		}
		if(pass1 === pass2){
			resultPass.innerText = '비밀번호가 일치합니다.'
			resultPass.style.color = 'green';
			isPassOk = true;
		}else{
			resultPass.innerText = '비밀번호가 일치하지않습니다.'
			resultPass.style.color = 'red';
			isPassOk = false;
		}
		
	});
		
		
	
	
	findPasschangeForm.onsubmit = function(){
		
		if(!isPassOk){
			alert('비밀번호가 유효하지 않습니다.');
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
            <form action="/FarmStoryJSP/user/findpasschange.do" id="findPasschangeForm" method = "POST">
                <h2>비밀번호 변경</h2>
                <table>
                    <tbody>
                        <tr>
                            <th>아이디</th>
                            <td>${FindId.getUserId()}</td>
                        </tr>
                        <tr>
                            <th><label for="pass">새 비밀번호</label></th>
                            <td>
                                <input type="password" placeholder="새 비밀번호 입력" required name="pass1">
                            </td>
                        </tr>
                        <tr>
                            <th><label for="passagain">새 비밀번호 확인</label></th>
                            <td>
                                <input type="password" placeholder="새 비밀번호 입력" required name="pass2">
                                <span class="resultPass"></span>
                            </td>
                        </tr>
                    </tbody>
                </table>
            
            <p>
                비밀번호를 변경해 주세요.<br>
                영문, 숫자, 특수문자를 사용하여 8자 이상 입력해 주세요.
            </p>
        
        <input type="submit" class="btnSubmit" value="다음"/>
        <a href="FarmStoryJSP/user/login.do?success=500">취소</a>
        </form>
        </section>
    </main>



	<!-- footer 시작 -->
	<%@ include file="/css/_footer.jsp"%>
</body>
</html>