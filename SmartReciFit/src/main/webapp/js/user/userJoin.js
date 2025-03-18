let isIdValid = 0;//아이디 중복검사
let isNicknameValid = 0;//닉네임 중복검사
let isidentityValid = 0;//인증 검사

//const form = document.querySelector('form');
const form = document.querySelector('#userJoinForm');
const inputs = form.querySelectorAll('input');

const idInput = document.querySelector('#id');
const nicknameInput = document.querySelector('#nickName');
const emailInput = document.querySelector('#email');
const emailOkInput = document.querySelector('#checkEmailOk');

const checkIdButton = document.querySelector('#btn-checkId');
const checkNickNameButton = document.querySelector('#btn-checkNickName');
const checkEmailButton = document.querySelector('#btn-checkEmail');
const checkcheckEmailOkButton = document.querySelector('#btn-checkEmailOk');
const submitButton = document.querySelector('#btn-submit');

//어떤 이벤트를 명시적으로 처리하지 않은 경우, 해당 이벤트에 대한 사용자 에이전트의 기본 동작을 실행하지 않도록 지정
form.addEventListener('submit', (event) => {
	event.preventDefault();
});

submitButton.addEventListener('click', (event) => {
	console.log("아이디 중복 확인 test="+isIdValid);
	console.log("닉네임 중복 확인 test="+isNicknameValid);
	console.log("모든 입력값 확인 test="+validateAll());

	if (isIdValid !== 1) {
	    alert('아이디 중복 확인을 해주세요');
	    console.log("아이디 중복 확인="+isIdValid);
	    idInput.focus();
	    return;
	}

	if (isNicknameValid !== 1) { // 닉네임 중복 확인을 하지 않았다면 경고창 표시
	    alert('닉네임 중복 확인을 해주세요');
	    console.log("닉네임 중복 확인="+isNicknameValid);
	    nicknameInput.focus();
	    return;
	}

	if (!validateAll()) { // 모든 입력값이 유효한지 검사
	    console.log("모든 입력값 확인="+validateAll());
	    alert('입력값을 확인해주세요.');
	    return;
	}else{
		console.log("모든 입력값 확인="+validateAll());
		alert('회원가입이 완료되었습니다.');
		form.submit();
	}
});

inputs.forEach(input => {
    input.addEventListener('input', () => validateField(input));
});

//아이디 중복검사
checkIdButton.addEventListener('click', async () => {
    const id = idInput.value.trim();

    if (!id) {
        alert('ID를 입력해주세요');
        idInput.focus();
        idInput.style.border = '2px solid red';
        return;
    }

    try {
        const response = await fetch('checkId.do', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            },
            body: new URLSearchParams({ id }).toString(),
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.text();
        handleIdValidationResult(result);
    } catch (error) {
        console.error('ID validation error:', error);
        alert('아이디 확인 중 오류가 발생했습니다');
        idInput.style.border = '2px solid orange';
    }
});

//닉네임 중복검사
checkNickNameButton.addEventListener('click', async () => {
    const nickName = nicknameInput.value.trim();

    if (!nickName) {
        alert('닉네임을 입력해주세요');
        idInput.focus();
        idInput.style.border = '2px solid red';
        return;
    }

    try {
        const response = await fetch('checkNickName.do', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            },
            body: new URLSearchParams({ nickName }).toString(),
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.text();
        handleNickNameValidationResult(result);
    } catch (error) {
        console.error('NickName validation error:', error);
        alert('닉네임 확인 중 오류가 발생했습니다');
        idInput.style.border = '2px solid orange';
    }
});

function validateField(input) {
    const value = input.value.trim();
    let isValid = true;
    let message = '';

    switch (input.id) {
        case 'id':
            if (!value) {
                message = '아아디는 영문 대소문자, 숫자, 기호로 이루어신 6-16자 사이로 입력해주세요';
                isValid = false;
            }else if (!value.match(/^[a-zA-Z0-9!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{4,14}$/)) {
			message = '올바른 아이디 형식이 아닙니다';
			isValid = false;
			}
            break;

        case 'pw':
            if (!value) {
                message = '비밀번호는 영문 대소문자, 숫자, 기호로 이루어신 6-16자 사이로 입력해주세요';
                isValid = false;
            }else if (!value.match(/^[a-zA-Z0-9!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{6,14}$/)) {
			message = '올바른 비밀번호 형식이 아닙니다';
			isValid = false;
			}
            break;

        case 'name':
            if (!value) {
                message = '이름을 입력해주세요';
                isValid = false;
            }
            break;
			
        case 'nickName':
            if (!value) {
                message = '닉네임은 2~8자 사이로 입력해주세요';
                isValid = false;
            }else if(!value.match(/^[a-zA-Z0-9가-힣!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{2,8}$/)){
			message = '올바른 닉네임 형식이 아닙니다';
			isValid = false;
			}
            break;
			

    }

    input.style.border = isValid ? '2px solid green' : '2px solid red';
    showError(input, message);
    if (!isValid && document.activeElement !== input) {
        input.focus();
    }
    return isValid;
}

function validateAll() {
    let isValid = true;
    inputs.forEach(input => {
        if (!validateField(input)) {
            isValid = false;
        }
    });
    return isValid&&isIdValid === 1&&isNicknameValid === 1;
}

function handleIdValidationResult(data) {
    const pwInput = document.querySelector('#pw');

    switch (data) {
        case 'valid':
            alert('이 아이디는 사용 가능합니다');
            idInput.style.border = '2px solid green';
            pwInput.focus();
            isIdValid = 1;
            break;

        case 'notValid':
            alert('이 아이디는 사용 불가능합니다');
            idInput.style.border = '2px solid red';
            idInput.value = '';
            idInput.focus();
            isIdValid = -1;
            break;

        default:
            alert('예상치 못한 응답입니다');
            idInput.style.border = '2px solid orange';
            isIdValid = 0;
    }
}

function handleNickNameValidationResult(data) {

    switch (data) {
        case 'valid':
            alert('이 닉네임은 사용 가능합니다');
            nicknameInput.style.border = '2px solid green';
            emailInput.focus();
            isNicknameValid = 1;
            break;

        case 'notValid':
            alert('이 닉네임은 사용 불가능합니다');
            nicknameInput.style.border = '2px solid red';
            nicknameInput.value = '';
            nicknameInput.focus();
            isNicknameValid = -1;
            break;

        default:
            alert('예상치 못한 응답입니다');
            nicknameInput.style.border = '2px solid orange';
            isNicknameValid = 0;
    }
}

function showError(input, message) {
    let errorElement = input.nextElementSibling;
    if (!errorElement || !errorElement.classList.contains('error-message')) {
        errorElement = document.createElement('div');
        errorElement.classList.add('error-message');
        input.parentNode.insertBefore(errorElement, input.nextSibling);
    }
    errorElement.textContent = message;
    errorElement.style.color = 'red';
}

const style = document.createElement('style');
style.textContent = `
    input[readonly] {
        background-color: #f0f0f0;
    }
    .error-message {
        font-size: 0.8em;
        margin: 2px 0;
    }
`;
document.head.appendChild(style);