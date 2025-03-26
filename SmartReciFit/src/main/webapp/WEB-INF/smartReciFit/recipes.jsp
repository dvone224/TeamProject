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
		${cookingMethod }<input type="checkbox" class="cooking-method"
			name="cooking-method" value="${cookingMethod }">
	</c:forEach>
	<br>
	<c:forEach var="ingredient" items="${tagIngredient}">
		${ingredient }<input type="checkbox" class="ingredient"
			name="ingredient" value="${ingredient }">
	</c:forEach>
	<br>
	<c:forEach var="eatTime" items="${tagEatTime}">
		${eatTime }<input type="checkbox" class="eat-time" name="eat-time"
			value="${eatTime }">
	</c:forEach>
	<br>
	<c:forEach var="cookingStyle" items="${tagCookingStyle}">
		${cookingStyle }<input type="checkbox" class="cooking-style"
			name="cooking-style" value="${cookingStyle }">
	</c:forEach>
</div>
<div class="recipe-container">
	<c:forEach var="recipe" items="${recipeList }">
		<div class="recipe ${recipe.recipeNum }">
			<a href="${ctx }/recipeContent.do?rn=${recipe.recipeNum }"> <img
				class="recipe-img ${recipe.recipeNum }"
				src="${recipe.recipeThumbnail }" alt="" width="300px" height="300px">
			</a> ${recipe.recipeName}
		</div>
	</c:forEach>
</div>
<script src="${ctx}/js/board/searchRecipe.js"></script>
<script src="${ctx}/js/recipe/recipes.js"></script>
<%@ include file="../../part/footer.jsp"%>