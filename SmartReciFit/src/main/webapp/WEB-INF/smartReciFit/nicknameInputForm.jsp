<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>

<body>
    <div class="container">
        <h2>닉네임 입력</h2>
        <p>입력하신 닉네임이 이미 사용 중입니다. 새로운 닉네임을 입력해주세요.</p>
        <form action="${pageContext.request.contextPath}/saveSocialLoginInfo.do" method="POST">
            <input type="hidden" name="platform" value="${platform}">
            <input type="hidden" name="email" value="${email}">
            <label for="nickname">새 닉네임:</label>
            <input type="text" id="nickname" name="nickname" required>
            <button type="submit">확인</button>
        </form>
    </div>
</body>
<%@ include file="../../part/footer.jsp"%>