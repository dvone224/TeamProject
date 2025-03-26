<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="recipe-content">
	<input class="recipe-type" type="hidden" value="${recipe.recipeType}">
	<c:if test="${recipe.recipeType eq 'API' or recipe.aiRecipeBoolean}">
		<h1>${recipe.recipeName }</h1>
		<div class="meal-size">${recipe.recipeMealSize}</div>
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
		<input type="hidden" class="ai-recipe-boolean" value="${recipe.aiRecipeBoolean}">
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