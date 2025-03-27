const pwInput = document.querySelector('#pw-new');
const pwError = document.createElement('p'); // 오류 메시지 요소 생성
const resetPwButton = document.querySelector('#btn-resetPw');

pwError.style.color = 'red'; // 오류 메시지 색상 설정
pwInput.parentNode.insertBefore(pwError, pwInput.nextSibling); // 오류 메시지 요소 삽입

// 초기 상태 설정: 이메일 입력란이 비어있거나 유효하지 않으면 버튼 비활성화
updatePwValidation(pwInput.value);

// 이메일 입력란의 값이 변경될 때마다 유효성 검사 수행
pwInput.addEventListener('input', function() {
  updatePwValidation(this.value);
});

// 이메일 유효성 검사 및 오류 메시지 업데이트 함수
function updatePwValidation(pw) {
  if (pw.trim() === '') { // 입력 값이 비어있는지 확인
    pwError.textContent = ''; // 오류 메시지 숨김
    resetPwButton.disabled = true; // 버튼 비활성화
    return;
  }
  const isValid = isValidPw(pw);
  resetPwButton.disabled = !isValid;
  pwError.textContent = isValid ? '' : '비밀번호는 영문 대소문자, 숫자, 기호로 이루어진 6-16자 사이로 입력해주세요';
}

// 이메일 유효성 검사 함수
function isValidPw(pw) {
  const pwRegex = /^[a-zA-Z0-9!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{6,14}$/;
  return pwRegex.test(pw);
}