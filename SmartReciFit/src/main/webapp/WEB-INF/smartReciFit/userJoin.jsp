<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../../part/header.jsp" %>
<script src="
https://cdn.jsdelivr.net/npm/sweetalert2@11.17.2/dist/sweetalert2.all.min.js
">
/* 메일인증 관련 https://zero-week.tistory.com/87 */
</script>
<link href="
https://cdn.jsdelivr.net/npm/sweetalert2@11.17.2/dist/sweetalert2.min.css
" rel="stylesheet">
<h1> 회원가입 </h1>
<form action="${ctx}/userJoin.do" method="post">
	<table>
		<tr>
		<th>Id</th><td><input type="text" name="id" id="id" required><button class="btn-idCheck" id="btn-idCheck">ID중복검사</button></td> 
		</tr>
		<tr>
		<th>Password</th><td><input type="text" name="pw" id="pw" required></td> 
		</tr>
		<tr>
		<th>Name</th><td><input type="text" name="name" id="name" required> </td>
		</tr>
		<tr>
		<th>NickName</th><td><input type="text" name="nickName" id="nickName" required><button class="btn-nickNameCheck" id="btn-nickNameCheck">닉네임중복검사</button></td>
		</tr>
			<tr>
			<th>E-mail</th><td><input type="text" name="email" id="email"><button class="btn-emailCheck" id="btn-emailCheck" value="메일인증" onclick="email_ok(email.value)">E-mail인증</button></td>
			</tr>
			<tr>
    		<th></th>
   			 <td>
       		 <span id="countdown">2:00</span>
      		  <input type="text" name="emailOk" id="emailOk" placeholder="인증번호를 입력하세요">
       		 <button class="btn-emailOk" id="btn-emailOk">인증 완료</button>
  		 	 </td>
			</tr>
			<tr>
			<th>Phone</th><td><input type="text" name="phone" id="phone"></td>
			</tr>
		<tr>
		<th>프로필 사진</th><td><input type="file" name="uploadFile" accept="image/*"><button class="btn-FileCancel" id="btn-FileCancel">업로드 취소</button></td>
		</tr>
		<tr>
		<td colspan="2"><button class="btn-sudmit" id="btn-sudmit">가입하기</button></td>
		</tr>
	</table>
</form>	
<script src="${ctx}/js/user/insert.js"> </script>
<%@ include file="../../part/footer.jsp" %>
