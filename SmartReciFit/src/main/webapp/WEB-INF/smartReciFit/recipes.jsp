<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>
<form class="recipes" action="">
	<c:forEach var="cookingMethod" items="${tagCookingMethod}">
		${cookingMethod }<input type="checkbox" class="cooking-method" name="cooking-method">
	</c:forEach>
	<br>
	<c:forEach var="ingredient" items="${tagIngredient}">
		${ingredient }<input type="checkbox" class="ingredient" name="ingredient">
	</c:forEach>
	<br>
	<c:forEach var="eatTime" items="${tagEatTime}">
		${eatTime }<input type="checkbox" class="eat-time" name="eat-time">
	</c:forEach>
	<br>
	<c:forEach var="cookingStyle" items="${tagCookingStyle}">
		${cookingStyle }<input type="checkbox" class="cooking-style" name="cooking-style">
	</c:forEach>
</form>
<script src="${ctx}/js/recipe/recipes.js"> </script>
<%@ include file="../../part/footer.jsp"%>