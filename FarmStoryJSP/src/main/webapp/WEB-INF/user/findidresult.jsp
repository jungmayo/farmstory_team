<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/FarmStoryJSP/css/farmstory.css">
<title>Farmstory</title>
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
</style>
</head>
<body>
	<%@ include file="/css/_header.jsp"%>

	<!-- header 끝-->



    <main>
        <section>
            <form>
                <h2>아이디 찾기 결과</h2>
                <table>
                    <tbody>
                        <tr>
                            <th>이름</th>
                            <td><span>${FindId.getUserName()}</span></td>
                        </tr>
                        <tr>
                            <th>아이디</th>
                            <td><span>${FindId.getUserId()}</span></td>
                        </tr>
                        <tr>
                            <th>이메일</th>
                            <td>
                                <span id="mail">${FindId.getUserEmail()}</span>
                            </td>
                        </tr>
                        <tr>
                            <th>가입일</th>
                            <td>
                                <span id="rdate">${FindId.getUserRegdate()}</span>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <p>
                고객님의 정보와 일치하는 아이디 입니다.
            </p>
        </section>
        <a href="/FarmStoryJSP/user/findpass.do">비밀번호 찾기</a> 
        <a href="/FarmStoryJSP/user/login.do">로그인</a> 
    </main>



	<!-- footer 시작 -->
	<%@ include file="/css/_footer.jsp"%>
</body>
</html>