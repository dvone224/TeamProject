const mealSize = document.querySelector('.meal-size');
const ingredientList = [...document.querySelectorAll('.ingredient')];
const seasoningList = [...document.querySelectorAll('.seasoning')];
const manualList = [...document.querySelectorAll('.recipe-manual')];
const targeMealSzie = document.querySelector('.output');
const recipeType = document.querySelector('.recipe-type').value;
const convertRecipe = document.querySelector('.recipe-convert');
let timeoutId = null;

function getRecipeConverter() {
	if (recipeType === 'AI') {
		console.log("AI");
		let aiRcipeBoolean = document.querySelector('.ai-recipe-boolean').value;
		if (!JSON.parse(aiRcipeBoolean)) {
			return;
		}
	}

	let jsonData = {
		mealSize: mealSize.value,
		targeMealSzie: targeMealSzie.innerText,
		ingredients: ingredientList.map(e => e.innerText),
		seasonings: seasoningList.map(e => e.innerText),
		manuals: manualList.map(e => e.innerText)
	};
	console.log("ext = " + ingredientList[0].innerText);
	fetch(ctx + "/recipeConverter.do", {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(jsonData)
	}).then(response => response.json())
		.then(data => {
			console.log(data["recipeIngredient"]);
			let ingredients = data["recipeIngredient"].split("|");
			let seasonings = data["recipeSeasoning"].split("|");

			let html = '';
			ingredients.forEach(e => {
				html += `<div class="convert-ingredient">${e}</div>`;

			});
			seasonings.forEach(e => {
				html += `<div class="convert-seasoning">${e}</div>`;
			});
			convertRecipe.innerHTML = html;
		})
		.catch(error => console.error('Error:', error));
}

document.addEventListener('DOMContentLoaded', getRecipeConverter);

document.getElementById('range').addEventListener('input', function() {

	// 이전에 설정된 타이머가 있다면 취소
	if (timeoutId !== null) {
		clearTimeout(timeoutId);
	}

	// 2초 후에 함수 실행
	timeoutId = setTimeout(getRecipeConverter, 1000); // 2000ms = 2초

});

