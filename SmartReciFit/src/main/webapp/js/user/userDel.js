const form = document.querySelector('#userDelForm');
const checkbox = document.querySelector('#confirmCheckbox');
const submitButton = document.querySelector('#btn-submit');
let check=false;

confirmCheckbox.addEventListener('change', function() {
    submitButton.disabled = !this.checked;
	check=true;
});

submitButton.addEventListener('click', function() {
console.log('check:'+check);
console.log(new URLSearchParams({ checkbox }).toString());
console.log(checkbox);
  fetch('userDel.do', {
    method: 'POST',
    headers: {
         'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
    },
            body: new URLSearchParams({ check}).toString(),
  })
  .then(response => {
    if (response.ok) {
		swal.fire({
					icon: "success",
					title: "Okay!",
					text:"이용해주셔서 감사합니다",
					confirmButtonColor: "#F7C525",
				}).then((result) => {
					if(result.isConfirmed) {
						window.location.href = 'index.jsp'; // index.jsp로 이동
					}
				});
    } else {
		swal.fire({ //탈퇴실패
			icon: "error",
			title: "Error!",
			text:"탈퇴에 실패했습니다",
			confirmButtonColor: "#F7C525",
		});
    }
  })
  .catch(error => {
	swal.fire({
		icon: "error",
		title: "Error!",
		text:"탈퇴 중 오류가 발생했습니다",
		confirmButtonColor: "#F7C525",
	});
  });
});
