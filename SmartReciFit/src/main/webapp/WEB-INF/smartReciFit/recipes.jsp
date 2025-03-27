<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>
<div class="recipe-search">
	<input type="text" id="keyword" placeholder="참고 레시피 검색"
		class="search-input" required onkeyup="searchRecipes()">
</div>
<div id="searchResults" class="search-results"></div>
<div class="recipe-filter">
	<c:forEach var="cookingMethod" items="${tagCookingMethod}">
		<input type="checkbox" class="cooking-method"
			id="cooking-method-${cookingMethod }" name="cooking-method">
		<label for="cooking-method-${cookingMethod }">${cookingMethod }</label>
	</c:forEach>
	<br>
	<c:forEach var="ingredient" items="${tagIngredient}">
		<input type="checkbox" class="ingredient"
			id="ingredient-${ingredient }" name="ingredient">
		<label for="ingredient-${ingredient }">${ingredient }</label>
	</c:forEach>
	<br>
	<c:forEach var="eatTime" items="${tagEatTime}">
		<input type="checkbox" class="eat-time" id="eat-time-${eatTime }"
			name="eat-time">
		<label for="eat-time-${eatTime }">${eatTime }</label>
	</c:forEach>
	<br>
	<c:forEach var="cookingStyle" items="${tagCookingStyle}">
		<input type="checkbox" class="ingredient"
			id="cookingStyle-${cookingStyle }" name="cookingStyle">
		<label for="cookingStyle-${cookingStyle }">${cookingStyle }</label>
	</c:forEach>
</div>
<div class="recipe-container">
	<c:forEach var="recipe" items="${recipeList }">
		<div class="recipe ${recipe.recipeNum }">
			<div class="recipe-img">
				<a href="${ctx }/recipeContent.do?rn=${recipe.recipeNum }"> <img
					class="img ${recipe.recipeNum }" src="${recipe.recipeThumbnail }"
					alt="" width="300px" height="300px">
				</a>
			</div>
			${recipe.recipeName}
		</div>
	</c:forEach>
</div>
<script src="${ctx}/js/board/searchRecipe.js"></script>
<script src="${ctx}/js/recipe/recipes.js"></script>
<%@ include file="../../part/footer.jsp"%>