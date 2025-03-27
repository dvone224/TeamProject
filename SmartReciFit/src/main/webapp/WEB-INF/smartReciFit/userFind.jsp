<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<!-- 예쁜 알람창용 스크립트 -->
<%@ include file="../../part/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<h2>내 계정 찾기</h2>
<p>회원정보에 등록된 이메일로 본인인증을 진행해주세요. 만약 이메일을 등록하지 않았다면 해당 메뉴는 사용할 수 없습니다.</p>
<table>
    <tr>
        <td><input type="text" name="email" id="email"></td></td>
        <td><button class="btn-checkEmail" name="btn-checkEmail" id="btn-checkEmail" onclick="email_ok(email.value)">이메일인증</button></td>
    </tr>
    <tr>
    <td colspan="2"><div class="foldCount" id="countdown" style="display: none;"></div></td>
    </tr>
    <tr>
        <td><input type="text" name="checkEmailOk" id="checkEmailOk"  placeholder="인증번호를 입력해주세요"></td>
        <td><button class="btn-checkEmailOk" name="btn-checkEmailOk" id="btn-checkEmailOk">인증완료</button></td>
    </tr>
</table>

<div class="foldBox" style="display: none;">
    <p>인증이 완료되었습니다. 원하는 메뉴를 선택하세요.</p>
    <button class="btn-submit" name="btn-submit" id="btn-findId" onclick="location.href='${ctx}/userFindId.do'">아이디찾기</button>
    <button class="btn-submit" name="btn-submit" id="btn-resetPw" onclick="location.href='${ctx}/userResetPw.do'" >비밀번호 재설정</button>
</div>

<script src="${ctx}/js/user/userFind.js"> </script>
<%@ include file="../../part/footer.jsp" %>