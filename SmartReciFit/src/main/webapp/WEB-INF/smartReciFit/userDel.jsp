<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<!-- 예쁜 알람창용 스크립트 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<table>
<tr><td><h2>정말 탈퇴하시겠습니까?</h2></td></tr>
<tr><td><p> 탈퇴를 원하신다면 다시 한 번 비밀번호를 입력해주세요.</p></td></tr>
<tr><td><input type="text" name="pw-del" id="pw-del" /></td></tr>
<tr><td><button class="btn-submit" name="btn-submit" id="btn-submit">탈퇴</button></td></tr>
</table>
<script src="${ctx}/js/user/userDel.js"> </script>
<%@ include file="../../part/footer.jsp" %>