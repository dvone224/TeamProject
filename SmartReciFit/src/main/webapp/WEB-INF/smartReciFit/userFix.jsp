<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<!-- 예쁜 알람창용 스크립트 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<%@ include file="../../part/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
	<h1> 회원정보수정 </h1> 
	
	<form id="userJoinForm" action="${ctx}/userJoin.do" method="post"  enctype="multipart/form-data">
	<table>
	<tr>
	<td colspan="3"><p>*표가 있는 항목은 필수 입력 항목입니다.</p></td>
	</tr>
	<tr>
	<td>아이디*</td>
	<td><input type="text" name="id-new" id="id-new"  value="${vo.userId}" required></td>
	<td><button class="btn-checkId" name="btn-checkId" id="btn-checkId">아이디 중복검사</button></td>
	</tr>
	<tr>
	<td>비밀번호*</td>
	<td colspan="2"><input type="password" name="pw-new" id="pw-new" value="${vo.userPw}"  required></td>
	</tr>
	<tr>
	<td>이름*</td>
	<td colspan="2"><input type="text" name="name" id="name" value="${vo.userName}" required></td>
	</tr>
	<tr>
	<td>닉네임*</td>
	<td><input type="text" name="nickName" id="nickName" value="${vo.userNickName}" required></td>
	<td><button class="btn-checkNickName" name="btn-checkNickName" id="btn-checkNickName">닉네임 중복검사</button></td>
	</tr>
	<tr>
	<td>이메일*</td>
	<td><input type="text" name="email" id="email"></td>
	<td><button class="btn-checkEmail" name="btn-checkEmail" id="btn-checkEmail" value="${vo.userEmail}"  onclick="email_ok(email.value)">이메일인증</button></td>
	</tr>
	<tr>
	<td><div id="countdown"></div></td>
	<td><input type="text" name="checkEmailOk" id="checkEmailOk"></td>
	<td><button class="btn-checkEmailOk" name="btn-checkEmailOk" id="btn-checkEmailOk" placeholder="인증번호를 입력해주세요">인증완료</button></td>
	</tr>
	<tr>
	<td>전화번호</td>
	<td colspan="2"><input type="text" name="phone" id="phone" value="${vo.userPhone }"></td>
	</tr>

	<tr>
	<td>프로필 사진</td>
	<td><input type="file" name="uploadFile" id="uploadFile" accept="image/*" value="${vo.userImg}" onchange="tryImgPreview(event)" ></td>
	<td><button class="btn-imgDel" name="btn-imgDel" id="btn-imgDel">이미지삭제</button></td>
	</tr>
	<tr><td colspan="3"> <div id="imgPreview"></div> </td></tr>
	<tr>
	<td colspan="3"><button class="btn-submit" name="btn-submit" id="btn-submit">회원가입</button></td>
	</tr>
	</table>
	</form>

<script src="${ctx}/js/user/userJoin.js"> </script>
<%@ include file="../../part/footer.jsp" %>