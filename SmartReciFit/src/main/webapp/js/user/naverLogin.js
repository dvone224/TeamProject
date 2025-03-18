// naverLogin.js
var naver_id_login = new naver_id_login("Kc4oajEGWigub1aElsL9", "http://localhost:8084/SmartReciFit/loginSuccess.do");
var state = naver_id_login.getUniqState();
naver_id_login.setButton("white", 2, 40);
naver_id_login.setDomain("http://localhost:8084/SmartReciFit");
naver_id_login.setState(state);
naver_id_login.setPopup(); // 팝업 모드 활성화
naver_id_login.init_naver_id_login();

// 접근 토큰 값 출력
console.log(naver_id_login.oauthParams.access_token);

// 네이버 사용자 프로필 조회
naver_id_login.get_naver_userprofile("naverSignInCallback()");

// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
function naverSignInCallback() {
    console.log(naver_id_login.getProfileData('email'));
    console.log(naver_id_login.getProfileData('nickname'));
    console.log(naver_id_login.getProfileData('age'));
}
