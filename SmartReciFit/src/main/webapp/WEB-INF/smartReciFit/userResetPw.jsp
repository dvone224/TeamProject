<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<!-- 예쁜 알람창용 스크립트 -->
<%@ include file="../../part/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<h2>비밀번호 재설정하기</h2>
<p>재설정할 비밀번호를 입력해주세요.</p>

<form id="pwResetForm" action="${ctx}/userResetPw.do" method="post" >
<input type="password" name="pw-new" id="pw-new" required>
<button class="btn-submit" name="btn-submit" id="btn-resetPw" >완료</button>
</form>

<script src="${ctx}/js/user/userResetPw.js"> </script>
<%@ include file="../../part/footer.jsp" %>