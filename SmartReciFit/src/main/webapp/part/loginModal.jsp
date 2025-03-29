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
			<div id="g_id_onload"
				data-client_id="231194762579-nbasfr2j9k5nrb2nu78t6r6ou03c3btk.apps.googleusercontent.com"
				data-login_uri="http://localhost:8084/SmartReciFit/main.do"
				data-auto_prompt="false"></div>
			<div class="g_id_signin" data-type="standard" data-size="large"
				data-theme="outline" data-text="sign_in_with"
				data-shape="rectangular" data-logo_alignment="left"></div>
			<!-- 구글 로그인 버튼 노출 영역 -->
		</div>
		<div>
			<a href="${ctx}/userFind.do">아이디 찾기 또는 비밀번호 찾기</a>
		</div>


	</div>
</div>

<div class="overlay"></div>
<!------------------------ 카카오 script ------------------------>
<script type="text/javascript"
	src="https://developers.kakao.com/sdk/js/kakao.js"></script>


<!------------------------ 네이버 script ------------------------>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>


<!------------------------ 구글 script loginOut.js 에 있음 ------------------------>

<script src="${ctx}/js/user/userSocialLogin.js"></script>
