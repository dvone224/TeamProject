<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<%-- <%@ include file="../../part/header.jsp" %> --%>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
	<h1> 회원가입 </h1>
	
	<form id="userJoinForm" action="${ctx}/userJoin.do" method="post">
	<table>
	<tr>
	<td>아이디</td>
	<td><input type="text" name="id" id="id" required></td>
	<td><button class="btn-checkId" name="btn-checkId" id="btn-checkId">아이디 중복검사</button></td>
	</tr>
	<tr>
	<td>비밀번호</td>
	<td colspan="2"><input type="password" name="pw" id="pw" required></td>
	</tr>
	<tr>
	<td>이름</td>
	<td colspan="2"><input type="text" name="name" id="name" required></td>
	</tr>
	<tr>
	<td>닉네임</td>
	<td><input type="text" name="nickName" id="nickName" required></td>
	<td><button class="btn-checkNickName" name="btn-checkNickName" id="btn-checkNickName">닉네임 중복검사</button></td>
	</tr>
	<tr>
	<td>이메일</td>
	<td><input type="text" name="email" id="email"></td>
	<td><button class="btn-checkEmail" name="btn-checkEmail" id="btn-checkEmail">이메일인증</button></td>
	</tr>
	<tr>
	<td></td>
	<td><input type="text" name="checkEmailOk" id="checkEmailOk"></td>
	<td><button class="btn-checkEmailOk" name="btn-checkEmailOk" id="btn-checkEmailOk">인증완료</button></td>
	</tr>
	<tr>
	<td>전화번호</td>
	<td colspan="2"><input type="text" name="phone" id="phone"></td>
	</tr>
	<tr>
	<td colspan="3"><button class="btn-submit" name="btn-submit" id="btn-submit">회원가입</button></td>
	</tr>
	</table>
	</form>

<script src="${ctx}/js/user/userJoin.js"> </script>
<%@ include file="../../part/footer.jsp" %>