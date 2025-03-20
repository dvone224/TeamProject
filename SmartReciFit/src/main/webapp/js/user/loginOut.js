const ctx = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

document.addEventListener('DOMContentLoaded', () => {
	// 모달 활성화
	const activateLoginModal = () => {
		document.querySelector('.login-modal').classList.add('active');
		document.querySelector('.overlay').classList.add('active');
	};

	// 모달 비활성화
	const deactivateLoginModal = () => {
		document.querySelector('.login-modal').classList.remove('active');
		document.querySelector('.overlay').classList.remove('active');
	};

	// 로그인 요청 처리
	const loginAjax = (form) => {
		const id = form.id.value.trim();
		const pw = form.pw.value.trim();

		if (!id || !pw) {
			alert("아이디와 비밀번호를 입력해주세요.");
			return;
		}

		fetch(ctx + "/login.do", {
			method: "POST",
			headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8" },
			body: `id=${id}&pw=${pw}`
		})
			.then(response => response.text())
			.then(data => {
				if (data === "success") {
					alert("로그인 성공");
					location.href = ctx + "/main.do";
				} else {
					alert("아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.");
				}
			})
			.catch(error => console.error("로그인 요청 에러:", error));
	};

	// 이벤트 리스너 등록
	const loginOpenButton = document.querySelector('.login-open');
	if (loginOpenButton) {
		loginOpenButton.addEventListener('click', activateLoginModal);
	}

	const loginCloseButton = document.querySelector('.login-close');
	if (loginCloseButton) {
		loginCloseButton.addEventListener('click', deactivateLoginModal);
	}

	// 폼 제출 이벤트 리스너 추가
	const loginForm = document.querySelector('#loginForm');
	if (loginForm) {
		loginForm.addEventListener('submit', (event) => {
			event.preventDefault();
			loginAjax(loginForm);
		});
	}
});

document.addEventListener('DOMContentLoaded', () => {
	const logoutBtn = document.querySelector('.logout-btn');
	if (logoutBtn) { // 로그아웃 버튼이 존재할 경우에만 이벤트 리스너 추가
		logoutBtn.addEventListener('click', (event) => {
			event.preventDefault(); // 기본 링크 동작 방지
			const logoutUrl = event.target.href; // href 속성 값 가져오기

			fetch(logoutUrl, {
				method: "GET",
			})
				.then(response => response.text())
				.then(data => {
					if (data === "done") {
						alert("로그아웃 성공");
						setTimeout(() => {
							location.href = ctx + "/main.do";
						}, 500);
					} else {
						alert("로그아웃 실패");
					}
				})
				.catch(error => console.error('Error:', error));
		});
	}
});

// 네이버 script
window.addEventListener('message', function(event) {
	if (event.origin !== "http://localhost:8084") return; // 팝업 창의 도메인
	if (event.data.type === 'naverLogin') {
		var accessToken = event.data.accessToken;
		// 네이버 사용자 프로필 정보 가져오기
		getNaverProfile(accessToken);
	}
}, false);

function getNaverProfile(accessToken) {
	// 네이버 사용자 프로필 정보를 가져오는 코드
	$.ajax({
		url: 'https://openapi.naver.com/v1/nid/me',
		type: 'GET',
		headers: {
			"Authorization": "BEARER " + accessToken
		},
		success: function(data) {
			console.log(data);
			// 사용자 정보를 세션에 저장하거나 데이터베이스에 등록하는 코드
		},
		error: function(error) {
			console.error('Error:', error);
		}
	});
}

// 구글 script 
function handleCredentialResponse(response) {
       const jwtToken = response.credential;
       const payload = JSON.parse(Base64.decode(jwtToken.split('.')[1]));

       console.log('ID: ' + payload.sub);
       console.log('Full Name: ' + payload.name);
       console.log('Given Name: ' + payload.given_name);
       console.log('Family Name: ' + payload.family_name);
       console.log('Image URL: ' + payload.picture);
       console.log('Email: ' + payload.email);

       const nickname = payload.name;
       const email = payload.email;

       sendUserInfoToServer('google', nickname, email);
   }

   function sendUserInfoToServer(platform, nickname, email) {
       $.ajax({
           type: 'POST',
           url: `${ctx}/saveSocialLoginInfo.do`,
           data: { platform: platform, nickname: nickname, email: email },
           success: function () {
               window.location.href = `${ctx}/main.do`;
           },
           error: function (error) {
               console.error('Error sending user info:', error);
           }
       });
   }

   window.onload = function () {
       google.accounts.id.initialize({
           client_id: "231194762579-nbasfr2j9k5nrb2nu78t6r6ou03c3btk.apps.googleusercontent.com",  // 여기에 클라이언트 ID를 입력하세요
           callback: handleCredentialResponse,
           login_uri: "http://localhost:8084/SmartReciFit/main.do" // 로그인 URI 설정
       });
       google.accounts.id.renderButton(
           document.querySelector(".g_id_signin"),
           { theme: "outline", size: "large", shape: "rectangular", logo_alignment: "left" }
       );
       google.accounts.id.prompt();
   }
