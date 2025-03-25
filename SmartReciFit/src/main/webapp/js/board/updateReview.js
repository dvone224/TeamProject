function previewImage(input, index) {
	let previewImg = document.getElementById('previewImg' + index);
	let fileInput = document.querySelector(`input[name="img${index}"]`);
	let fileNameElement = document.getElementById('filename' + index);
	let deleteButton = document.querySelector(`button[onclick="deleteImage(this,${index})"]`);
	//수정
	if (input.files && input.files[0]) {
		let file = input.files[0];
		let reader = new FileReader();

		reader.onload = function(e) {
			previewImg.src = e.target.result;
			previewImg.style.display = "inline-block";

			fileNameElement.textContent = file.name;
			fileNameElement.style.display = 'block';//초기화 display none 되돌리기

			fileInput.style.display = 'none';
			deleteButton.style.display = 'inline-block';

		}
		reader.readAsDataURL(file);
	} else {
		//파일이 없을경우
		previewImg.src = "";
		previewImg.style.display = "none";
		fileNameElement.textContent = "";
		fileInput.style.display = 'block';
		deleteButton.style.display = 'none';
	}
}

function deleteImage(el, index) {
	//이미지 삭제 
	let imageDiv = document.getElementById('imageDiv' + index);
	let previewImg = document.getElementById('previewImg' + index);
	let fileInput = document.querySelector(`input[name="img${index}"]`);
	let hiddenInput = document.querySelector(`input[name="existingImg${index}"]`);
	let fileNameElement = document.getElementById('filename' + index);
	let deleteButton = document.querySelector(`button[onclick="deleteImage(this,${index})"]`);
	//초기화
	previewImg.src = "";
	previewImg.style.display = "none";

	fileInput.style.display = "block"; // 파일 선택 보이게 하기

	fileNameElement.textContent = "";
	fileNameElement.style.display = "none";//초기화 display none

	fileInput.value = "";
	hiddenInput.value = "";
	console.log(deleteButton);
	deleteButton.style.display = "none"

}