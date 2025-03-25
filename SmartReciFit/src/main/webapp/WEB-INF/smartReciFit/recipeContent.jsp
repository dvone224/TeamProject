<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="recipe-content">
	<h1>${recipe.recipeName }</h1>
	<img alt="" src="${recipe.recipeThumbnail }" />
	<h1>${recipe.recipeType }</h1>

	<c:forEach items="${fn:split(recipe.recipeIngredient, '|')}"
		var="ingredient">
    	${ingredient}
	</c:forEach>
	<br>
	<c:forEach items="${fn:split(recipe.recipeSeasoning, '|')}"
		var="seasoning">
    	${seasoning}
	</c:forEach>
	<br>
	<c:if test="${recipe.recipeType eq 'API'}">
		<c:set var="splitManual" value="${fn:split(recipe.recipeManual, '|')}" />
		<c:set var="splitRecipeImg"
			value="${fn:split(recipe.apiRecipeImg, '|')}" />
		<c:forEach var="i" begin="0" end="${fn:length(splitManual) - 1}">
			<h1>${splitManual[i]}</h1>
			<img alt="" src="${splitRecipeImg[i]}">
		</c:forEach>
	</c:if>
	<c:if test="${recipe.recipeType eq 'AI'}">
		<c:set var="splitManual" value="${fn:split(recipe.recipeManual, '|')}" />
		<c:forEach var="i" begin="0" end="${fn:length(splitManual) - 1}">
			<h1>${splitManual[i]}</h1>
			<iframe width="560" height="315"
				src="https://www.youtube.com/embed/${videoId}?start=${timeStamp[i]}"
				title="동영상 제목" frameborder="0" allowfullscreen></iframe>
			<h1>${timeStamp[i]}</h1>
		</c:forEach>

	</c:if>

	<br>
</div>
<div>${recipe }</div>
<%@ include file="../../part/footer.jsp"%>