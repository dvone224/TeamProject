<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal login-modal">
	<div class="modal-content">
		<button class="btn-close login-close">&times;</button> 
		<form id="loginForm">
			<label for="id">아이디</label> <input type="text" id="id" name="id"
				required placeholder="아이디를 입력하세요."> <label for="pw">비밀번호</label>
			<input type="password" id="pw" name="pw" required
				placeholder="비밀번호를 입력하세요.">
			<button type="submit">로그인</button>
		</form>

		<div>
			<!-- 카카오 로그인 버튼 노출 영역 -->
			<a href="javascript:kakaoLogin()"><img
				src="<c:url value='/img/kakao_login_large_wide.png'/>"
				style="width: 200px"></a>
			<!-- 카카오 로그인 버튼 노출 영역 -->

			<!-- 네이버 로그인 버튼 노출 영역 -->
			<div id="naver_id_login"></div>
			<!-- //네이버 로그인 버튼 노출 영역 -->

			<!-- 구글 로그인 버튼 노출 영역 -->
<div id="g_id_onload" data-client_id="231194762579-nbasfr2j9k5nrb2nu78t6r6ou03c3btk.apps.googleusercontent.com"
  data-login_uri="http://localhost:8084/SmartReciFit/main.do" data-auto_prompt="false">
</div>
<div class="g_id_signin" data-type="standard" data size="large"
  data-theme="outline" data-text="sign_in_with" data-shape="rectangular" data-logo_alignment="left">
</div>		<!-- 구글 로그인 버튼 노출 영역 -->
		</div>

	</div>
</div>

<div class="overlay"></div>
<!------------------------ 카카오 script ------------------------>
<script type="text/javascript"
	src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript">
    Kakao.init('c73862c0803a70accb9cd1840b0c6bcb');
    function kakaoLogin() {
        Kakao.Auth.login({
            success: function (response) {
                Kakao.API.request({
                    url: '/v2/user/me',
                    success: function (response) {
                    	 const nickname = response.properties.nickname; // 사용자 닉네임
                         const email = response.kakao_account.email; // 사용자 이메일
                    	
                        sendUserInfoToServer('kakao', nickname, email);
                         
                        alert('로그인 성공')
                         location.href = ctx + "/main.do";
                    },
                    fail: function (error) {
                    	console.error(error);
                    },
                })
            },
            fail: function (error) {
            	console.error(error);
            },
        })
    }
    
    function sendUserInfoToServer(platform, nickname, email) {
        $.ajax({
            type: 'POST',
            url: `${ctx}/saveSocialLoginInfo.do`,
            data: { platform, nickname, email },
            success: function () {
                location.href = `${ctx}/main.do`; // 메인 페이지로 이동
            },
            error: function (error) {
                console.error('Error sending user info:', error);
            },
        });
    }
</script>

<!------------------------ 네이버 script ------------------------>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript">
    var naver_id_login = new naver_id_login("Kc4oajEGWigub1aElsL9", "http://localhost:8084/SmartReciFit/loginSuccess.do");
    var state = naver_id_login.getUniqState();
    naver_id_login.setButton("white", 2, 40);
    let domain = window.location.pathname;
    console.log(domain);
    naver_id_login.setDomain("http://localhost:8084/SmartReciFit/main.do");
    naver_id_login.setState(state);
    naver_id_login.setPopup();
    naver_id_login.init_naver_id_login();
    
  </script>
  
<!------------------------ 구글 script loginOut.js 에 있음 ------------------------>
