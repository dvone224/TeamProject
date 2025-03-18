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

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<header>
	<div class="header-container">
		<div class="logo">
			<a href="${ctx}/main.do"> <img
				src="${pageContext.request.contextPath}/img/logo.png"
				alt="스마트 레시핏 로고">
			</a>
		</div>
		<nav class="navigation">
			<ul>
				<li><a href="${ctx}/recipes.do">레시피</a></li>
				<li><a href="${ctx}/ranking.do">랭킹</a></li>
				<li><a href="${ctx}/reviews.do">후기</a></li>
				<li><a href="${ctx}/events.do">EVENT</a></li>
			</ul>
		</nav>
		<div class="user-actions">
			<c:choose>
				<c:when test="${empty sessionScope.user}">
					<!-- 로그인 상태가 아닐 때 -->
					<button class="login-open">로그인</button>
					<button class="btn-userJoin" onclick="location.href='${ctx}/userJoin.do'">회원가입</button>
				</c:when>
				<c:otherwise>
					<!-- 로그인 상태일 때 -->
					<a href="${ctx}/logout.do" class="logout-btn">로그아웃</a>
					<a href="${ctx}/userInfo.do">${sessionScope.user}님</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>



<%@ include file="./loginModal.jsp"%>
