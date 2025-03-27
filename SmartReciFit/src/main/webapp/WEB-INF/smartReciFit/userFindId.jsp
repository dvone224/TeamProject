<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<!-- 예쁜 알람창용 스크립트 -->
<%@ include file="../../part/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<h2>ID 찾기</h2>
<p>입력한 정보로 가입된 계정의 아이디입니다.</p>
<p>${findUser.userId}</p>

<p>비밀번호가 기억나지 않으신다면</p>
<button class="btn-submit" name="btn-submit" id="btn-resetPw" onclick="location.href='${ctx}/userResetPw.do'" >비밀번호 재설정</button>

<%@ include file="../../part/footer.jsp" %>