<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Farmstory</title>
  	<link rel="stylesheet" href="/FarmStoryJSP/css/farmstory.css">
  <style>
    main {
      height : 600px;
      width: auto;
      background-color: #white;
      position: relative;
    }
    main > p{
      position: absolute;
      float: left;
      left: 50%;
      margin-left: -69px;
      top: 50%;
    }
    
      .register {
      position: relative;
      width: 600px;
      height: 980px;
      top : 50px;
      margin: 0 auto;
      overflow: hidden;
    }
    .btnSubmit {
      cursor: pointer;
      border: 1px solid white;
    }
    .register table {
      width: 100%;
      border-collapse: collapse;
      border-spacing: 0;
      border-top: 2px solid #111;
      margin-bottom: 10px;
    }
    .register table caption {
      text-align: left;
      padding: 10px 0;
      font-weight: bold;
      box-sizing: border-box;
    }
    .register table tr>td {
      padding: 6px 12px;
      border: 1px solid #E9E9E9;
      box-sizing: border-box;
    }
    .register table tr>td:nth-child(1) {
      width: 105px;
      background: #F5F8F9;
    }
    .register table tr .nickInfo {
      margin-bottom: 6px;
    }
    .register table input {
      width: 162px;
      height: 22px;
      border: 1px solid #E4EAEC;
      background: #F7F7F7;
      text-indent: 6px;
    }
    .register table input[name=addr1] {
      display: block;
      width: 360px;
      margin: 4px 0;
    }
    .register table input[name=addr2] {
      display: block;
      width: 360px;
    }
    .register table button {
      border: none;
      vertical-align: bottom;
    }
    .register>form>div {
      float: right;
    }
    .register>form .auth {
      display: none;
      margin-top: 4px;
    }
    #btns {
    	margin-top : 20px;
    }
    .btnCancel {
    	margin-right : 20px;	
    }
  </style>
  <script>
function postcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.

            var addr = ''; // 주소 변수

            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
               // document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                //document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zip').value = data.zonecode;
            document.getElementById("addr1").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addr2").focus();
        }
    }).open();
}
//유효성 검사에 사용할 정규 표현식
const reUid   = /^[a-z]+[a-z0-9]{4,19}$/g;
const rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
const reName  = /^[가-힣]{2,10}$/ 
const reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
const reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
const reHp = /^01[0-9]-\d{4}-\d{4}$/;
//const reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

//유효성 검사에 사용할 상태변수
let isUidOk   = false;
let isPassOk  = false;
let isNameOk  = false;
let isNickOk  = false;
let isEmailOk = false;
let isHpOk    = false;


window.onload = function(){
	const registerForm = document.getElementsByTagName('form')[0];
	const btnCheckUid = document.getElementById('btnCheck');
	const btnSendEmail = document.getElementById('btnSendEmail');
	const btnAuthEmail = document.getElementById('btnAuthEmail');
	const resultId = document.getElementsByClassName('resultId')[0];
	const resultPass = document.getElementsByClassName('resultPass')[0];
	const resultName = document.getElementsByClassName('resultName')[0];
	const resultNick = document.getElementsByClassName('resultNick')[0];
	const resultEmail = document.getElementsByClassName('resultEmail')[0];
	const auth = document.getElementsByClassName('auth')[0];
	const resultHp = document.getElementsByClassName('resultHp')[0];

	
	
	

	btnCheckUid.onclick = function(){
		
		const uid = registerForm.uid.value;
		
		if(!uid.match(reUid)){
			resultId.innerText = '유효하지 않은 아이디입니다.';
			resultId.style.color = 'red';
			return;
		};
		
		fetch('/FarmStoryJSP/user/checkUser.do?type=uid&value='+uid)
		.then(resp => resp.json())
		.then(data => {
			console.log(data);
			if(data.result > 0){
				resultId.innerText = '이미 사용중인 아이디입니다.';
				resultId.style.color = 'red';
				isUidOk = false;
			}else {
				resultId.innerText = '사용 가능한 아이디입니다.';
				resultId.style.color = 'green';
				isUidOk = true;
			}
		})
		.catch(err => {
			console.log(err);
		});

		
		
	} // id onclick 끝
	
	registerForm.pass2.addEventListener('focusout' , function(){
		
		const pass1 = registerForm.pass1.value;
		const pass2 = registerForm.pass2.value;
		
		if(!pass1.match(rePass)){
			resultPass.innerText = '유효하지 않은 비밀번호입니다.';
			resultPass.style.color = 'red';
			return;
		}
		if(pass1 == pass2){
			resultPass.innerText = '비밀번호가 일치합니다.'
			resultPass.style.color = 'green';
			isPassOk = true;
		}else{
			resultPass.innerText = '비밀번호가 일치하지않습니다.'
			resultPass.style.color = 'red';
			isPassOk = false;
		}
		
	}); // pass 검사 끝
	
	registerForm.name.addEventListener('focusout' , function(){
		
		const name = registerForm.name.value;
		
		if(!name.match(reName)){
			resultName.innerText = '유효하지 않은 이름입니다.';
			resultName.style.color = 'red';
			isNameOk = false;
		}else{
			resultName.innerText = "";
			isNameOk = true;
		}
		
	}); // 이름 검사 끝
	
	registerForm.nick.addEventListener('focusout' , function(){
		
		const nick = registerForm.nick.value;
		if(!nick.match(reNick)){
			resultNick.innerText = '유효하지 않은 닉네임입니다.';
			resultNick.style.color = 'red';
			return
		};
		
		fetch('/FarmStoryJSP/user/checkUser.do?type=nick&value='+nick)
		.then(resp => resp.json())
		.then(data => {
			console.log(data);
			if(data.result != 0){
				resultNick.innerText = '이미 사용중인 닉네임입니다.';
				resultNick.style.color = 'red';
				isNickOk = false;
			}else {
				resultNick.innerText = '사용 가능한 닉네임입니다.';
				resultNick.style.color = 'green';
				isNickOk = true;
			}
		})
		.catch(err => {
			console.log(err);
		});
		
		
	}); // 닉네임 검사 끝
	
	
	//이메일 검사
	let preventdblClick = false;
	
	btnSendEmail.onclick = function(){
		const email = registerForm.email.value;
		
		
		if(preventdblClick){ //이중클릭방지
			console.log('here1');
			return;
		}
		
		
		if(!email.match(reEmail)){
			resultEmail.innerText = '유효하지 않은 이메일 형식 입니다.';
			resultEmail.style.color = 'red';
			return;
		}
		
			preventdblClick = true; //여기서 true로 만들어주기 클릭방지
			console.log('here2');
			
		fetch('/FarmStoryJSP/user/checkUser.do?type=email&value='+email)
		.then(resp => resp.json())
		.then(data => {
			console.log(data);
			if(data.result > 0){
				resultEmail.innerText = '이미 사용중인 이메일입니다.';
				resultEmail.style.color = 'red';
			}else {
				resultEmail.innerText = '이메일 인증코드를 확인하세요.';
				resultEmail.style.color = 'green';
				auth.style.display = 'block';
			}
		})
		.catch(err => {
			console.log(err);
			
		})
        .finally(() => {
            preventdblClick = false; // 클릭 방지 비활성화 (원하는 경우)
        });
		
		
	}; //이메일 검사
	
	
	//인증번호 확인
	
	btnAuthEmail.onclick = function(){
		
		const auth = registerForm.auth.value;
		fetch('/FarmStoryJSP/user/checkUser.do' , {
			method : 'POST',
			body : JSON.stringify({"auth" : auth})
		})
		.then(resp => resp.json())
		.then(data => {
			console.log(data);
				if(data.result > 0){
					resultEmail.innerText = '이메일이 인증 되었습니다.';
					resultEmail.style.color = 'green';
					isEmailOk = true;
				}else {
					resultEmail.innerText = '이메일 인증에 실패하였습니다.';
					resultEmail.style.color = 'red';
					isEmailOk = false;
				}
			
		})
		.catch(err => {
			console.log(err);
		});
	
	
	}; //인증번호 끝
	
	//휴대폰검사
	registerForm.hp.addEventListener('focusout' , function(){
		
		const hp = registerForm.hp.value;
		if(!hp.match(reHp)){
			resultHp.innerText = '유효하지 않은 핸드폰번호입니다.';
			resultHp.style.color = 'red';
			return;
		}
		
		fetch('/FarmStoryJSP/user/checkUser.do?type=hp&value='+hp)
		.then(resp => resp.json())
		.then(data => {
			console.log(data);
			if(data.result > 0){
				resultHp.innerText = '이미 사용중인 핸드폰번호입니다.';
				resultHp.style.color = 'red';
				isHpOk = false;
			}else {
				resultHp.innerText = '사용 가능한 핸드폰번호입니다.';
				resultHp.style.color = 'green';
				isHpOk = true;
			}
		})
		.catch(err => {
			console.log(err);
		});
	
	
	});
	

	
	
	
	//최종 폼 전송 유효성 검사
	registerForm.onsubmit = function() {
          const zip = document.getElementById('zip').value.trim();
          const addr1 = document.getElementById('addr1').value.trim();
          const addr2 = document.getElementById('addr2').value.trim();
		
		//아이디
			if(!isUidOk){
				alert('아이디가 유효하지 않습니다.');
				return false;
			}
		//비번
			if(!isPassOk){
				alert('비밀번호가 유효하지 않습니다.');
				return false;
			}
		
		// 이름
			if(!isNameOk){
				alert('이름이 유효하지 않습니다.');
				return false;
			}
		// 닉넴
			if(!isNickOk){
				alert('닉네임이 유효하지 않습니다.');
				return false;
			}
		// 이메일
			if(!isEmailOk){
				alert('이메일이 유효하지 않습니다.');
				return false;
			}
		// 휴대폰
			if(!isHpOk){
				alert('휴대폰번호가 유효하지 않습니다.');
				return false;
			}
		// 주소	
			if(!zip){
				alert('우편번호를 검색하세요.');
				return false;
			}
			if(!addr1){
				alert('주소를 검색하세요.');
				return false;
			}
			if(!addr2){
				alert('상세주소를 입력하세요.');
				return false;
			}
		return true; // 다 되면 폼전송
	
	}

} //onload 끝
</script>
</head>
<body>
<%@ include file="/css/_header.jsp"%>
  <!-- header 끝-->

  <main>
    <p>
    <section class="register">
      <form action="/FarmStoryJSP/user/register.do" method="post">
        <table border="1">
          <caption>사이트 이용정보 입력</caption>
          <tr>
            <td>아이디</td>
            <td><input type="text" name="uid" placeholder="아이디 입력" />
              <button type="button" id="btnCheck"><img src="../images/chk_id.gif" alt=""></button> 
              <span class="resultId"></span>
            </td>
          </tr>
          
          <tr>
            <td>비밀번호</td>
            <td><input type="password" name="pass1" placeholder="비밀번호 입력" /></td>
          </tr>
          
          <tr>
            <td>비밀번호 확인</td>
            <td>
            <input type="password" name="pass2" placeholder="비밀번호 확인 입력" />
            <span class="resultPass"></span>
            </td>
          </tr>
        </table>
        
        <table border="1">
          <caption>개인정보 입력</caption>
          
          <tr>
            <td>이름</td>
            <td><input type="text" name="name" placeholder="이름 입력" />
            <span class="resultName"></span>
            </td>
          </tr>
          
          <tr>
            <td>별명</td>
            <td>
              <p class="infoNick">공백없이 한글, 영문, 숫자만 입력가능</p>
              <input type="text" name="nick" placeholder="별명 입력" />
              <span class="resultNick"></span>
            </td>
          </tr>
          
          <tr>
            <td>E-Mail</td>
            <td>
             <input type="email" name="email" placeholder="이메일 입력" />
             <button id="btnSendEmail" type="button">
             <img src="../images/chk_auth.gif" alt="인증번호 받기" />
             </button> 
             <span class="resultEmail"></span>
              
              <div class="auth">
                <input type="text" name="auth" placeholder="인증번호 입력" />
                <button type="button" id="btnAuthEmail">
                <img src="../images/chk_confirm.gif" alt="확인" />
                </button>
              </div>
            </td>
          </tr>
          
          <tr>
            <td>휴대폰</td>
            <td><input type="text" name="hp" placeholder="- 포함 13자리 입력" minlength="13" maxlength="13" />
            <span class="resultHp"></span>
            </td>
          </tr>
          
          <tr>
            <td>주소</td>
            <td>
              <div>
                <input type="text" id="zip" name="zip" placeholder="우편번호" readonly />
                <button type="button" class="btnZip" onclick="postcode()">
                  <img src="../images/chk_post.gif" alt="">
                </button>
              </div>
              <div>
                <input type="text" id="addr1" name="addr1" placeholder="주소를 검색하세요." readonly />
              </div>
              <div>
                <input type="text" id="addr2" name="addr2" placeholder="상세주소를 입력하세요." />
              </div>
            </td>
          </tr>
        
        </table>
        <div id=btns>
          <a href="/FarmStoryJSP/user/terms.do" class="btnCancel">취소</a>
          <input type="submit" class="btnSubmit" value="회원가입" />
        </div>
      </form>
    </section>
    </p>
  </main>

  <!-- footer 시작 -->
    <%@ include file="/css/_footer.jsp"%>
</body>
</html>