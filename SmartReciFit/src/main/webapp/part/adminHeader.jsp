<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자 페이지</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/adminStyle.css">
    <script type="text/javascript" src="${ctx}/js/user/loginOut.js" defer></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
    <header>
        <div class="header-container">
            <div class="logo">
                <a href="${ctx}/adminMain.do">
                    <img src="${pageContext.request.contextPath}/img/logo.png" alt="스마트 레시핏 로고">
                </a>
            </div>
            <nav class="navigation">
                <ul>
                    <li><a href="${ctx}/recipes.do">레시피</a></li>
                    <li><a href="${ctx}/ranking.do">랭킹</a></li>
                    <li><a href="${ctx}/reviews.do?page=1">후기</a></li>
                    <li><a href="${ctx}/events.do">EVENT</a></li>
                    <li>
                        <form action="${ctx}/recipeURL.do" method="post">
                            <input class="youtube-url" type="text" name="youtube-url"/>
                            <button>전송</button>
                        </form>
                    </li>
                </ul>
            </nav>
            <div class="user-actions">
                <a href="${ctx}/logout.do" class="logout-btn">로그아웃</a>
                <button class="admin-nick-btn">adminNick</button>
            </div>
        </div>
    </header>