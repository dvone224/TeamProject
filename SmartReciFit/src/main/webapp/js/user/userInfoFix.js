const form = document.querySelector('#userInfoForm');
const cancelButton = document.querySelector('#btn-infoCancel');
const submitButton = document.querySelector('#btn-infoSubmit');

//어떤 이벤트를 명시적으로 처리하지 않은 경우, 해당 이벤트에 대한 사용자 에이전트의 기본 동작을 실행하지 않도록 지정
form.addEventListener('submit', (event) => {
	event.preventDefault();
});

cancelButton.addEventListener('click', (event) => {
	console.log("cancelButton 클릭");
	window.location.href = "index.jsp";
});

submitButton.addEventListener('click', (event) => {
	console.log("submitButton 클릭");
	document.getElementById("userInfoForm").submit();
});

$(document).ready(function() {
	
    const checkboxes = $('input[type="checkbox"]');
    const submitButton = $('#btn-infoSubmit');

    async function updateSubmitButtonState() {
        const checkedCount = checkboxes.filter(':checked').length;
        // 비동기적으로 버튼 상태 업데이트
        await new Promise(resolve => setTimeout(resolve, 0));
        submitButton.prop('disabled', checkedCount < 1 || checkedCount > 5);
    }
    // 초기 상태 설정
    updateSubmitButtonState();
    // 체크박스 변경 시 상태 업데이트
    checkboxes.on('change', updateSubmitButtonState);
});
