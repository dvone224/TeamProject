let isIdValid = 0;//아이디 중복검사
let isIdentityValid = 0;//인증 검사
const form = document.querySelector('form');
const inputs = form.querySelectorAll('input');
const idInput = document.querySelector('#id');
const emailInput = document.querySelector('#email');
const checkIdButton = document.querySelector('#btn-idCheck');
const checkIdentityButton = document.querySelector('#btn-emailCheck');


form.addEventListener('submit', (event) => {
	
	event.preventDefault();
		console.log(isIdValid)
    if (isIdValid !== 1) {
        swal.fire('먼저 아이디 중복 확인을 해주세요');
        idInput.focus();
        return;
    }
    if (isIdentityValid !== 1) {
        swal.fire('이메일 인증을 진행해주세요');
        emailInput.focus();
        return;
    }
    if (validateAll()) {
        form.submit();
    }
});

function startCountdown(duration, display) {
       let timer = duration, minutes, seconds;
       let interval = setInterval(function () {
           minutes = parseInt(timer / 60, 10);
           seconds = parseInt(timer % 60, 10);

           minutes = minutes < 10 ? "0" + minutes : minutes;
           seconds = seconds < 10 ? "0" + seconds : seconds;

           display.textContent = minutes + ":" + seconds;

           if (--timer < 0) {
               clearInterval(interval);
               display.textContent = "시간 초과!"; // 또는 다른 메시지
           }
       }, 1000);
   }

   function email_ok(email) {
       const duration = 60 * 2; // 2분 (120초)
       const display = document.querySelector('#countdown'); // 카운트다운을 표시할 요소

       startCountdown(duration, display);

       // 이메일 인증 요청 로직 (서버로 요청)
       // 예시: fetch API 사용
       fetch('sendEmail.do', { // 서버의 이메일 전송 엔드포인트
           method: 'POST',
           headers: {
               'Content-Type': 'application/x-www-form-urlencoded',
           },
           body: 'email=' + encodeURIComponent(email),
       })
       .then(response => {
           if (!response.ok) {
               throw new Error('Network response was not ok');
           }
           return response.text();
       })
       .then(data => {
           console.log('Email sent:', data);
       })
       .catch(error => {
           console.error('There has been a problem with your fetch operation:', error);
           alert('이메일 전송에 실패했습니다.');
       });
   }


inputs.forEach(input => {
    input.addEventListener('input', () => validateField(input));
});


checkIdButton.addEventListener('click', async () => {
    const id = idInput.value.trim();

    if (!id) {
        swal.fire({
			icon: "error",
			title:"Oops!",
			text:'ID를 입력해주세요',
			confirmButtonColor: "#ff0000"
	});
        idInput.focus();
        idInput.style.border = '2px solid red';
        return;
    }

    try {
        const response = await fetch('idCheck.do', {
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
        swal.fire('아이디 확인 중 오류가 발생했습니다');
        idInput.style.border = '2px solid orange';
    }
});

/*idInput.addEventListener('click', () => {
    if (isIdValid === 1) {
        idInput.removeAttribute('readonly');
        isIdValid = 0;
    }
});

idInput.addEventListener('input', () => {
    if (isIdValid !== 0) {
        isIdValid = 0;
        idInput.removeAttribute('readonly');
    }
});*/

function validateField(input) {
    const value = input.value.trim();
    let isValid = true;
    let message = '';

    switch (input.id) {
        case 'id':
            if (!value) {
                message = '아이디를 입력해주세요';
                isValid = false;
            }else if (!value.match(/^[0-9a-zA-Z]{6,12}$/)) {
				message = '아이디는 영문과 숫자로 이뤄진 6~12글자로 입력 가능합니다.';
				isValid = false;
			}
            break;

        case 'pw':
            if (!value) {
                message = '패스워드를 입력해주세요';
                isValid = false;
            }else if (!value.match(/^[0-9a-zA-Z~!@#$%^&*()_+|{}[\]\\:";'<>,.?/]{6,16}$/)) {
			message = '패스워드는 영문, 숫자, 특수문자로 이뤄진 6~16글자로 입력 가능합니다.';
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
                message = '별명을 입력해주세요';
                isValid = false;
            }
            break;

        case 'email':
            if (!value) {
                message = '이메일을 입력해주세요';
                isValid = false;
            } else if (!value.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)) {
                message = '올바른 이메일 형식이 아닙니다';
                isValid = false;
            }
            break;

        case 'phone':
            if (!value) {
                message = '전화번호를 입력해주세요';
                isValid = false;
            } else if (!value.match(/^010-\d{3,4}-\d{4}$/)) {
                message = '전화번호 형식(010-XXXX-XXXX)이 맞지 않습니다';
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
    return isValid && isIdValid === 1;
}

function handleIdValidationResult(data) {
    const passInput = document.querySelector('#pw');

    switch (data) {
        case 'valid':
            alert('이 아이디는 사용 가능합니다');
            idInput.style.border = '2px solid green';
/*            idInput.setAttribute('readonly', 'true');
*/            passInput.focus();
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