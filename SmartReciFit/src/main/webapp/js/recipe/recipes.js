const recipeFilterDiv = document.querySelector('.recipe-filter');
const checkBoxList = [...recipeFilterDiv.querySelectorAll('input')];

const cookingMethodList = [...recipeFilterDiv.querySelectorAll('.cooking-method')];
const ingredientList = [...recipeFilterDiv.querySelectorAll('.ingredient')];
const eatTimeList = [...recipeFilterDiv.querySelectorAll('.eat-time')];
const cookingStyleList = [...recipeFilterDiv.querySelectorAll('.cooking-style')];

const searchRecipe = document.querySelector('search-recipe');
const recipeContainer = document.querySelector('.recipe-container');

let moreData = false;

function getCheckedList(list) {
	return list.filter(e => e.checked);
}

function getFilteredRecipe() {
	const checkedCookingMethods = getCheckedList(cookingMethodList);
	const checkedIngredients = getCheckedList(ingredientList);
	const checkedEatTimes = getCheckedList(eatTimeList);
	const checkedCookingStyles = getCheckedList(cookingStyleList);
	const offset = document.querySelector(".offset");
	console.log('offset.value = ' + offset.value);
	console.log('checkedCookingMethods = ' + checkedCookingMethods.map(e => e.value));
	console.log('checkedIngredients = ' + checkedIngredients.map(e => e.value));
	console.log('checkedEatTimes = ' + checkedEatTimes.map(e => e.value));
	console.log('checkedCookingStyles = ' + checkedCookingStyles.map(e => e.value));
	fetch(ctx + "/recipeFilter.do", {
		method: 'POST',
		headers: {
			"Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
		},
		body: `cookingMethods=${checkedCookingMethods.map(e => e.value)} &ingredients=${checkedIngredients.map(e => e.value)} &eatTimes=${checkedEatTimes.map(e => e.value)} &cookingStyles=${checkedCookingStyles.map(e => e.value)} &offset=${offset.value}`
	}).then(response => response.json())
		.then(data => {
			let html = '';
			data.forEach(e => {
				html += `<div class="recipe ${e["recipeNum"]}">`;
				html += `<div class="recipe-img">`;
				html += `<img class="img ${e["recipeNum"]}" src="${e["recipeThumbnail"]}" alt="" width="300px" height="auto">`;
				html += '</div>';
				html += `${e["recipeName"]}</div>`;
			})
			recipeContainer.innerHTML = recipeContainer.innerHTML + html;
			let recipeImgs = recipeContainer.querySelectorAll('img')
			recipeImgs.forEach(e => e.addEventListener('click', function() {
				location.href = `${ctx}/recipeContent.do?rn=${this.classList[1]}`;
			}))
			offset.value = parseInt(offset.value) + 24;
		})
		.catch(error => console.error('Error:', error));
}

checkBoxList.forEach(e => e.addEventListener('change', function() {
	document.querySelector(".offset").value = 1;
	recipeContainer.innerHTML = '';
	getFilteredRecipe();
}));

window.addEventListener('scroll', () => {
	const scrollTop = document.documentElement.scrollTop;
	const scrollHeight = document.documentElement.scrollHeight;
	const clientHeight = document.documentElement.clientHeight;

	if (scrollHeight - scrollTop <= clientHeight + 200 && !moreData) { // 버퍼값 설정
		moreData = true;
		getFilteredRecipe();
		moreData = false;
	}
});

document.addEventListener('DOMContentLoaded', getFilteredRecipe);