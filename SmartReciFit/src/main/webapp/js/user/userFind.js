let isidentityValid = 0;//이메일 인증 검사 0 인증검사 전 -1 인증검사 중 1 인증검사 완료
const verifyButton = document.getElementById('verifyButton');

//접었다 펼 상자들
const foldCount = document.querySelector('.foldCount');
const foldBox = document.querySelector('.foldBox');
const foldIdBox = document.querySelector('.foldIdBox');
const foldPwBox = document.querySelector('.foldPwBox');

let identityCode = null;//인증번호 저장
let emailChickTimeout = null;//카운트 저장
const checkEmailButton = document.querySelector('#btn-checkEmail');
const checkcheckEmailOkButton = document.querySelector('#btn-checkEmailOk');
const findIdButton = document.querySelector('#btn-findId');
const resetPwButton = document.querySelector('#btn-resetPw');

const inputMail = document.querySelector('#email');

const countdownDisplay = document.getElementById('countdown');
let countdownTime;
let timerInterval;

const emailInput = document.getElementById('email');
const emailError = document.createElement('p'); // 오류 메시지 요소 생성

emailError.style.color = 'red'; // 오류 메시지 색상 설정
emailInput.parentNode.insertBefore(emailError, emailInput.nextSibling); // 오류 메시지 요소 삽입

// 초기 상태 설정: 이메일 입력란이 비어있거나 유효하지 않으면 버튼 비활성화
updateEmailValidation(emailInput.value);
checkcheckEmailOkButton.disabled=true;//인증버튼 비활성화

// 이메일 입력란의 값이 변경될 때마다 유효성 검사 수행
emailInput.addEventListener('input', function() {
  updateEmailValidation(this.value);
});

// 이메일 유효성 검사 및 오류 메시지 업데이트 함수
function updateEmailValidation(email) {
  if (email.trim() === '') { // 입력 값이 비어있는지 확인
    emailError.textContent = ''; // 오류 메시지 숨김
    checkEmailButton.disabled = true; // 버튼 비활성화
    return;
  }
  const isValid = isValidEmail(email);
  checkEmailButton.disabled = !isValid;
  emailError.textContent = isValid ? '' : '올바른 이메일 형식을 입력해주세요.';
}
// 이메일 유효성 검사 함수
function isValidEmail(email) {
  const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  return emailRegex.test(email);
}

//카운트 다운 함수
function startCountdown() {
    let minutes = 2;
    let seconds = 0;

    countdownTime = minutes * 60 + seconds;

    timerInterval = setInterval(() => {
        minutes = Math.floor(countdownTime / 60);
        seconds = countdownTime % 60;

        // 시간을 00:00 형식으로 표시
        countdownDisplay.textContent = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
		
		// 남은 시간이 30초 이하일 때 글씨색 변경
		if (countdownTime <= 30) {
		    countdownDisplay.style.color = 'red';
		} else {
		    countdownDisplay.style.color = ''; // 기본 색상으로 복원
		}

        countdownTime--;

        if (countdownTime < 0) {
            clearInterval(timerInterval);
            countdownDisplay.textContent = '시간 종료!';
        }
    }, 1000);
}

async function email_ok(str){
	
	//여기서 기존에 저장된 메일이 있는지 확인하는 법 한 번 먼저 해야해
	console.log(str);//받아온 이메일
	
	//이메일 체크하기
	try {
	    const response = await fetch('checkEmail.do', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
	        },
	        body: new URLSearchParams({ str }).toString(),
	    });

	    if (!response.ok) {
	        throw new Error(`HTTP error! status: ${response.status}`);
	    }

	    const result = await response.text();
		console.log("emailCheckResult="+emailCheckResult(result));
		if (emailCheckResult(result) === false) { // emailCheckResult에서 false를 반환하면 함수 종료
		    return;
		}
	} catch (error) {
	    console.error('Email validation error:', error);
		swal.fire({
			icon: "error",
			title: "Error!",
			text:"메일 확인 중 오류가 발생했습니다",
			confirmButtonColor: "#F7C525",
		});
	    inputMail.style.border = '2px solid orange';
		return;
	}
	
	checkEmailButton.disabled=false; //이메일 인증버튼 비활성화
	checkcheckEmailOkButton.disabled=true; //인증번호 버튼 활성화
	
	foldCount.style.display = 'block'; // foldBox 표시
	const ctx = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	let num = parseInt(Math.random() * 100000);
	console.log(num);
	isidentityValid=-1;//인증검사 진행 중
	
	identityCode=num; //인증번호 저장
	clearTimeout(emailChickTimeout); //이전 타임아웃 제거
	checkEmailButton.disabled=true; //인증버튼 비활성화
	checkcheckEmailOkButton.disabled=false; //인증번호 버튼 활성화
	
	clearInterval(timerInterval); // 기존 타이머가 실행 중이면 초기화
	startCountdown();
	
	emailChickTimeout = setTimeout(() => {
	  identityCode = null; // 2분 후 인증번호 초기화
	  swal.fire({
	    icon: "error",
	    title: "인증 시간 초과",
	    text: "인증 시간이 만료되었습니다. 다시 인증해주세요.",
	    confirmButtonColor: "#F7C525",
	  });
	  isidentityValid=0;//인증검사 진행 전
	  checkEmailButton.disabled=false; //이메일 인증버튼 활성화
	  checkcheckEmailOkButton.disabled=true; //인증번호 버튼 활성화
	}, 120000); // 2분 (120000 밀리초) 후 타임아웃 설정

	$.ajax({
		url: ctx + "/mailSend.do", //전송받을 페이지 경로 //서블릿
		type: "post", //데이터 읽어오는 방식 //데이터 전송방식
		dataType: "text", //데이터 방식
		data: "num="+num+"&email="+str,
		success:function(text){ //성공일 경우
			//$("#id_result").html(text);
			swal.fire({
				icon: "success",
				title: "Check yout E-mail!",
				text:"인증번호를 확인해 입력하세요.",
				confirmButtonColor: "#F7C525",
				})
			
		},
		error:function(){ //실패일 경우
			swal.fire({
				icon: "error",
				title: "Error!",
				text:"메일 발송에 실패했습니다.",
				confirmButtonColor: "#F7C525",
				})
			isidentityValid=0;//인증검사 진행 전
		}
	});
}

//이메일 있는지 없는지 확인한 결과 출력
function emailCheckResult(data) {
    const identityInput = document.querySelector('#checkEmailOk');
    switch (data) {
        case 'valid':
			identityInput,focus;
			return true;
            break;

        case 'notValid':
			swal.fire({
				icon: "error",
				title: "Error!",
				text:"해당 이메일을 사용하는 계정이 없습니다.",
				confirmButtonColor: "#F7C525",
			});
			return false;
            break;

        default:
			swal.fire({
				icon: "error",
				title: "Error!",
				text:"예상치 못한 응답입니다",
				confirmButtonColor: "#F7C525",
			});
			return false;
    }
}

checkcheckEmailOkButton.addEventListener("click", function () {
  const enteredCode = document.querySelector('#checkEmailOk');
  let enterCode =parseInt(enteredCode.value);
  //이거 일단 input을 number로 해서 int 로 변환하긴 했는데 혹시나 나중에 문자 섞어서 인증할거면 String으로 바꾸기
  console.log("내가 입력한 인증번호:"+enterCode);
  console.log("저장한 인증번호:"+identityCode);
  console.log(enterCode == identityCode);
  
  if (identityCode !== null && enterCode == identityCode) {
    clearTimeout(emailChickTimeout); // 인증 성공 시 타임아웃 제거
    identityCode = null; // 인증 성공 후 인증번호 초기화
    swal.fire({
      icon: "success",
      title: "인증 성공",
      text: "이메일 인증이 완료되었습니다.",
      confirmButtonColor: "#F7C525",
    });
	isidentityValid=1;//인증검사 완료
	foldBox.style.display = 'block'; // foldBox 표시
	
	clearInterval(timerInterval);
	countdownDisplay.textContent = '인증완료';
	
	checkEmailButton.disabled=false; //이메일 인증버튼 활성화
	checkcheckEmailOkButton.disabled=true; //인증번호 버튼 활성화
	
	
  } else {
    swal.fire({
      icon: "error",
      title: "인증 실패",
      text: "인증번호가 일치하지 않습니다.",
      confirmButtonColor: "#F7C525",
    });
  }
});

findIdButton.addEventListener("click", function(){
/*	foldIdBox.style.display = 'block'; // foldBox 표시
	foldPwBox.style.display = 'none'; */
})
resetPwButton.addEventListener("click", function(){
/*	foldPwBox.style.display = 'block'; 
	foldIdBox.style.display = 'none'; */
})
