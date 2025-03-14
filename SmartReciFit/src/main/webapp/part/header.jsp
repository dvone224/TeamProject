<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">

<title>Smart ReciFit</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
<script type="text/javascript" src="${ctx}/js/user/loginOut.js" defer></script>

</head>
<body onload="init('${userId}')">
	<header>
		<a class="login-open"> 로그인을 해볼까용? </a>
		<a class="ranking-board" href="${ctx}/ranking.do">랭킹</a>
		<p>여기는 헤더 ~~~~~~~~~~~~~<p/>
	</header>
	<main>

		<%@ include file="./loginModal.jsp"%>
		<%@ include file="./msgModal.jsp"%>

