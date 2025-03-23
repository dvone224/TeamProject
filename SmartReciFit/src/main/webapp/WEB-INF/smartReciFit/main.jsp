
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>

<main>
	<div class="inner">
		<h2>여기는 메인페이지</h2>
<!-- 로그인 상태 확인 -->
<%-- <c:choose> --%>
        <!-- 로그인된 사용자의 경우 소셜 로그인 연동 버튼 표시 -->
        <form action="${ctx}/linkSocial.do" method="POST">
            <input type="hidden" name="platform" value="kakao">
            <input type="hidden" name="email" value="${sessionScope.socialLogin.kakao}">
            <button type="submit">카카오 계정 연동</button>
        </form>

        <form action="${ctx}/linkSocial.do" method="POST">
            <input type="hidden" name="platform" value="naver">
            <input type="hidden" name="email" value="${sessionScope.socialLogin.naver}">
            <button type="submit">네이버 계정 연동</button>
        </form>

        <form action="${ctx}/linkSocial.do" method="POST">
            <input type="hidden" name="platform" value="google">
            <input type="hidden" name="email" value="${sessionScope.socialLogin.google}">
            <button type="submit">구글 계정 연동</button>
        </form>

<%--     <c:otherwise>
        <!-- 로그인되지 않은 경우 -->
        <p>로그인 후 소셜 계정을 연동할 수 있습니다.</p>
    </c:otherwise> --%>
<%-- </c:choose> --%>

<!-- 메시지 처리 -->
<c:if test="${not empty sessionScope.message}">
    <script>
        alert("${sessionScope.message}");
    </script>
    <c:remove var="message" scope="session" /> <!-- 메시지를 출력한 후 세션에서 제거 -->
</c:if>

	</div>
</main>
<%@ include file="../../part/footer.jsp"%>