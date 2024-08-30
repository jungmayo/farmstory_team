<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/FarmStoryJSP/css/farmstory.css">
<title>Farmstory</title>
<script>
const success = ${success};

if(success==100){
	alert('로그인에 실패했습니다. 다시 한번 아이디와 비밀번호를 확인 하시기 바랍니다.');
}else if(success==200){
	alert('성공적으로 로그아웃 되었습니다.');
}else if(success==300){
	alert('회원가입을 축하합니다. 새로 로그인 해주세요.');
}else if(success==700){
	alert('비밀번호가 변경되었습니다. 새로 로그인 해주세요.');
}else if(success==800){
	alert('일치하는 정보가 없습니다.');
}
//else if(success==102){
	//alert('먼저 로그인을 하셔야 합니다.');
//}
</script>
<style>
main {
	height: 400px;
	width: 980px;
	margin : 0 auto;
	background-color: #white;

}

main>p {
	position: absolute;
	float: left;
	left: 50%;
	margin-left: -69px;
	top: 50%;
}

    .login {
      position: relative;
      width: 360px;
      height: auto;
      border: 1px solid #CFDED8;
      box-sizing: border-box;
      margin: 100px auto;
    }
    .login>form {}
    .login table {
      margin-left: 52px;
      margin-top: 30px;
      border-collapse: collapse;
      border-spacing: 0;
    }
    .login table tr {}
    .login table tr>td {}
    .login table img {
      width: 20px;
    }
    .login table input {
      width: 162px;
      height: 22px;
      margin-left: 6px;
      margin-bottom: 4px;
      border: 1px solid #E4EAEC;
      background-color: #F7F7F7;
    }
    .login .btnLogin {
      position: absolute;
      right: 50px;
      top: 30px;
      width: 60px;
      height: 52px;
      background-color: #FDFDFD;
      border: 1px solid #BEBEBE;
    }
    .login>div {
      width: 100%;
      height: 104px;
      background: #F5F6FA;
      border-top: 1px solid #CFDED8;
      margin-top: 20px;
      padding: 20px;
      box-sizing: border-box;
    }
    .login>div>p {
      margin: 10px 0;
    }
    .login>div>a {
      float: right;
	  margin-left : 7px;
    }
</style>
</head>
<body>
	<%@ include file="/css/_header.jsp"%>

	<!-- header 끝-->



  <main>
    <section class="login">
      <form action="/FarmStoryJSP/user/login.do" method="post">
        <table border="0">
          <tr>
            <td><img src="../images/login_ico_id.png" alt="아이디"></td>
            <td><input type="text" name="uid" placeholder="아이디 입력"></td>
          </tr>
          <tr>
            <td><img src="../images/login_ico_pw.png" alt="비밀번호"></td>
            <td><input type="password" name="pass" placeholder="비밀번호 입력"></td>
          </tr>
        </table>
        <input type="submit" value="로그인" class="btnLogin">
      </form>
      <div>
        <h3>회원 로그인 안내</h3>
        <p>
          아직 회원이 아니시면 회원으로 가입하세요.
        </p>
        <a href="/FarmStoryJSP/user/findpass.do">비밀번호 찾기</a>
        <a href="/FarmStoryJSP/user/findid.do">아이디 찾기</a>
        <a href="/FarmStoryJSP/user/terms.do">회원가입</a>
      </div>
    </section>
  </main>



	<!-- footer 시작 -->
	<%@ include file="/css/_footer.jsp"%>
</body>
</html>