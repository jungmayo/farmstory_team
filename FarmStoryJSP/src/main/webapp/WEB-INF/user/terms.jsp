<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="/FarmStoryJSP/css/farmstory.css">
	<title>Farmstory</title>
	<script>

	window.onload = function(){
		const btnNext = document.getElementsByClassName('btnNext')[0];
	
		
			btnNext.addEventListener('click', function(e){
				
				const chk1 = document.getElementById('chk1').checked;
				const chk2 = document.getElementById('chk2').checked;
				
				if(!(chk1 && chk2)){
					alert('모두 동의하셔야 합니다.');
					e.preventDefault();
				};	
			
		});
	}
	
	</script>
	<style>
main {
	height: auto;
	width: 980px;
	margin: 0 auto;
	background-color: #white;
	position: relative;
}

main>p {
	position: absolute;
	float: left;
	left: 50%;
	margin-left: -69px;
	top: 50%;
}
	.terms {
	  position: relative;
      width: 600px;
      height: 600px;
      margin: 0 auto;
      box-sizing: border-box;
    }
    .terms>table {
      width: 100%;
      height: auto;
      border-collapse: collapse;
      border-spacing: 0;
      border-top: 2px solid #111;
    }
    .terms>table>caption {
      text-align: left;
      font-weight: bold;
      padding: 10px 0;
      box-sizing: border-box;
    }
    .terms>table tr {}
    .terms>table td {
      padding: 6px;
      border: 1px solid #E9E9E9;
      box-sizing: border-box;
    }
    .terms>table td>textarea {
      width: 100%;
      height: 200px;
      resize: none;
      background: #EFEFEF;
      border: 1px solid #E4EAEC;
      padding: 2px;
      box-sizing: border-box;
      overflow-y:auto;
      
    }
    .terms>table td>label {
      float: right;
    }
    .terms>table td>label>input[type=checkbox] {}
    .terms>table p {
      float: right;
    }
    .terms>div {
      float: right;
      margin-top: 10px;
    }
    .btnCancel {
      margin-right : 10px;
      }
		
	</style>
</head>
<body>
	<%@ include file="/css/_header.jsp"%>



  <main>
    <section class="terms">
      <table>
        <caption>사이트 이용약관</caption>
        <tr>
          <td>
            <textarea readonly>${TermsDto.terms}</textarea>
            <p>
              <label><input type="checkbox" id="chk1" name="chk1" />동의합니다.</label>
            </p>
          </td>
        </tr>
      </table>
      <table>
        <caption>개인정보 취급방침</caption>
        <tr>
          <td>
            <textarea readonly>${TermsDto.privacy}</textarea>
            <p>
              <label><input type="checkbox" id="chk2" name="chk2" />동의합니다.</label>
            </p>
          </td>
        </tr>
      </table>
      <div>
        <a href="/FarmStoryJSP/user/login.do" class="btnCancel">취소</a>
        <a href="/FarmStoryJSP/user/register.do" class="btnNext">다음</a>
      </div>
    </section>
  </main>



	<%@ include file="/css/_footer.jsp"%>
</body>
</html>