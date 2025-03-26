const verifyButton = document.getElementById('verifyButton');
const foldBox = document.querySelector('.foldBox');

verifyButton.addEventListener('click', function() {
    // 인증 확인 로직 (예시)
    const verificationCode = document.getElementById('verificationCode').value;
    if (verificationCode === '1234') { // 예시: 인증 코드 '1234'
        foldBox.style.display = 'block'; // foldBox 표시
    } else {
        alert('인증 코드가 일치하지 않습니다.');
    }
});


const countdownDisplay = document.getElementById('countdown');
let countdownTime;
let timerInterval;

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




function email_ok(str){
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
				text:"인증번호를 확인해 입력하세요. \n혹시 오지 않았다면 메일 주소를 확인해주세요.",
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