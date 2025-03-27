<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="recipe-content">
	<input class="recipe-type" type="hidden" value="${recipe.recipeType}">
	<c:if test="${recipe.recipeType eq 'API' or recipe.aiRecipeBoolean}">
		<input class="meal-size" type="hidden" value="${recipe.recipeMealSize }"/>
		<h1>${recipe.recipeName }</h1>
		<br>
		<img alt="" src="${recipe.recipeThumbnail }" />

		<c:forEach items="${fn:split(recipe.recipeIngredient, '|')}"
			var="ingredient">
			<div class="ingredient">${ingredient}</div>
		</c:forEach>
		<br>
		<c:forEach items="${fn:split(recipe.recipeSeasoning, '|')}"
			var="seasoning">
			<div class="seasoning">${seasoning}</div>
		</c:forEach>
		<div class="range-container">
			<input type="range" name="range" id="range" value="1" min="0"
				max="5" oninput="output.value = this.value" step="0.1">
			<output type="number" id="output" class="output">1</output>
		</div>
		<div class="recipe-convert">
			
		</div>
	</c:if>
	<br>
	<c:if test="${recipe.recipeType eq 'API'}">
		<c:set var="splitManual" value="${fn:split(recipe.recipeManual, '|')}" />
		<c:set var="splitRecipeImg"
			value="${fn:split(recipe.apiRecipeImg, '|')}" />
		<c:forEach var="i" begin="0" end="${fn:length(splitManual) - 1}">
			<h1 class='recipe-manual'>${splitManual[i]}</h1>
			<img alt="" src="${splitRecipeImg[i]}">
		</c:forEach>
	</c:if>

	<c:if test="${recipe.recipeType eq 'AI'}">
		<input type="hidden" class="ai-recipe-boolean"
			value="${recipe.aiRecipeBoolean}">
		<c:choose>
			<c:when test="${recipe.aiRecipeBoolean}">
				<c:set var="splitManual"
					value="${fn:split(recipe.recipeManual, '|')}" />
				<c:forEach var="i" begin="0" end="${fn:length(splitManual) - 1}">
					<h1 class='recipe-manual'>${splitManual[i]}</h1>
					<iframe width="560" height="315"
						src="https://www.youtube.com/embed/${videoId}?start=${timeStamp.get(i)}"
						title="동영상 제목" frameborder="0" allowfullscreen></iframe>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<h1>레시피 동영상이 아닙니다</h1>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>
<script src="${ctx}/js/recipe/recipeConverter.js"></script>
<%@ include file="../../part/footer.jsp"%>