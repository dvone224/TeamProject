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
		</div>

	</div>
</div>

<div class="overlay"></div>

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
                        alert(JSON.stringify(response))
                    },
                    fail: function (error) {
                        alert(JSON.stringify(error))
                    },
                })
            },
            fail: function (error) {
                alert(JSON.stringify(error))
            },
        })
    }
</script>

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



