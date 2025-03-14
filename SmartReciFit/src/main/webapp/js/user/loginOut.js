// 페이지가 로드된 후에 스크립트 실행

// Context path를 추출하는 함수
const getContextPath = () => window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
console.log(getContextPath);

// 로그인 모달 활성화
const activateLoginModal = () => {
	document.querySelector('.login-modal').classList.add('active');
	document.querySelector('.overlay').classList.add('active');
};

const deactivateLoginModal = () => {
	document.querySelector('.login-modal').classList.remove('active');
	document.querySelector('.overlay').classList.remove('active');
};



// 메시지 모달 비활성화
const deactivateMsgModal = () => {
	document.querySelector('.msg-modal').classList.remove('active');
	document.querySelector('.overlay').classList.remove('active');
};

// 로그인 버튼 클릭 시 이벤트
document.querySelector('.login-open').addEventListener('click', activateLoginModal);

// 로그인 모달 닫기 버튼 클릭 시 이벤트
document.querySelector('.login-close').addEventListener('click', deactivateLoginModal);

// 메시지 모달 닫기 버튼 클릭 시 이벤트
document.querySelector('.msg-close').addEventListener('click', deactivateMsgModal);

// 로그인 요청 함수
const loginAjax = (form) => {
	const id = form.id.value.trim();
	const pw = form.pw.value.trim();

	// 입력값 유효성 검사
	if (id.length === 0 || pw.length === 0) {
		alert('아이디와 비밀번호를 모두 입력해주세요');
		return;
	}

	fetch("login.do", {
		method: "POST",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
		},
		body: `id=${id}&pw=${pw}`
	})
		.then(response => response.text())
		.then(data => {
			deactivateLoginModal(); // 로그인 모달 닫기

			// 로그인 성공 여부에 따른 처리
			if (data !== "null") {
				activateMsgModal(`${data}님 로그인하셨습니다.`);

				// 로그인 상태에 따른 UI 변경
				document.querySelector('.login-user').style.display = "block";
				document.querySelector('.all-user').style.display = "none";
				const loginUserElement = document.querySelector('.login-user').children[0];
				loginUserElement.innerHTML = `[ ${data} ] 님`;
				loginUserElement.href = `${getContextPath()}/userInfo.do`;
			} else {
				activateMsgModal("로그인 실패");
			}
		})
		.catch(error => console.error('로그인 요청 에러:', error));

	// 폼 초기화
	form.id.value = "";
	form.pw.value = "";
};

// 로그인 여부에 따라 UI 초기화
const init = (userId) => {
	if (!userId) {
		document.querySelector('.login-user').style.display = "none";
		document.querySelector('.all-user').style.display = "block";
	} else {
		document.querySelector('.login-user').style.display = "block";
		document.querySelector('.all-user').style.display = "none";
	}
};

// 로그아웃 버튼 클릭 시 이벤트
/*document.querySelector('.logout-btn').addEventListener('click', () => {
	fetch("loginOut.do", { method: "GET" })
		.then(response => response.text())
		.catch(error => console.error('로그아웃 요청 에러:', error));

	activateMsgModal("로그아웃 성공");

	// 로그아웃 후 메인 페이지로 리다이렉트
	setTimeout(() => {
		location.href = `${getContextPath()}/main.do`;
	}, 500);
});*/