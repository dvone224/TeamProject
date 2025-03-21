const recipeFilterDiv = document.querySelector('.recipe-filter');
const checkBoxList = [...recipeFilterDiv.querySelectorAll('input')];


const cookingMethodList = [...recipeFilterDiv.querySelectorAll('.cooking-method')];
const ingredientList = [...recipeFilterDiv.querySelectorAll('.ingredient')];
const eatTimeList = [...recipeFilterDiv.querySelectorAll('.eat-time')];
const cookingStyleList = [...recipeFilterDiv.querySelectorAll('.cooking-style')];


const searchRecipe = document.querySelector('search-recipe');
const recipeContainer = document.querySelector('.recipe-container');

/*searchRecipe.addEventListener('input', function() {

});*/

function getCheckedList(list) {
	return list.filter(e => e.checked);
}

checkBoxList.forEach(e => e.addEventListener('change', function() {
	const checkedCookingMethods = getCheckedList(cookingMethodList);
	const checkedIngredients = getCheckedList(ingredientList);
	const checkedEatTimes = getCheckedList(eatTimeList);
	const checkedCookingStyles = getCheckedList(cookingStyleList);
	fetch(ctx + "/recipeFilter.do", {
		method: 'POST',
		headers: {
			"Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
		},
		body: `cookingMethods=${checkedCookingMethods.map(e => e.value)}
			&ingredients=${checkedIngredients.map(e => e.value)}
			&eatTimes=${checkedEatTimes.map(e => e.value)}
			&cookingStyles=${checkedCookingStyles.map(e => e.value)}`
	}).then(response => response.json())
	.then(data => {
		let html = '';
		data.forEach(e=> {
			html += `<div class="recipe ${e["recipeNum"]}">`;
			html += '<img src="http://placehold.it/300x300" alt="" width="300px" height="300px">';
			html += `${e["recipeName"]}</div>`
		})
		recipeContainer.innerHTML = html;
	})
	.catch(error => console.error('Error:', error));
}));


