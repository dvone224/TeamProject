const form = document.querySelector('.recipes');
const checkBoxList = [...form.querySelectorAll('input')];
console.log(checkBoxList);
checkBoxList.forEach(e => e.addEventListener('change', function(){
	checkBoxList.filter(box => console.log(box.checked));
} ));
