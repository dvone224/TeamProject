console.log("recipeConverter");

document.addEventListener('DOMContentLoaded', function() {
	console.log("addEventListener");
	let recipeType = document.querySelector('.recipe-type').value;
	if (recipeType === 'AI') {
		console.log("AI");
		let aiRcipeBoolean = document.querySelector('.ai-recipe-boolean').value;
		if (!JSON.parse(aiRcipeBoolean)) {
			return;
		}
	}

	let mealSize = document.querySelector('.meal-size');
	let ingredientList = [...document.querySelectorAll('.ingredient')];
	let seasoningList = [...document.querySelectorAll('.seasoning')];
	let manualList = [...document.querySelectorAll('.recipe-manual')];
	let jsonData = {
		mealSize: mealSize.innerText,
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
	})
});

