<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>
<div class="recipe-content">
	<h1>${recipe.recipeName }</h1>
	<%-- <img alt="" src="${recipe.recipeThumbnail }"/> --%>
</div>
<div>${recipe }</div>
<%@ include file="../../part/footer.jsp"%>