<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../../part/header.jsp" %>

<h1> My Page </h1>
<table>
<tr>
<td colspan="3"> 내프로필 </td>
</tr>
<tr>
<td>아이디</td><td>${userContent.userId}</td><td><button name="fixId" id="fixId">수정</button></td>
</tr>
<tr>
<td>비밀번호</td><td>${userContent.userPw}</td><td><button name="fixPw" id="fixPw">수정</button></td>
</tr>
<tr>
<td>이름</td><td>${userContent.userName}</td><td><button  name="fixName" id="fixName">수정</button></td>
</tr>
<tr>
<td>E-mail</td>
<c:choose>
<c:when test="${empty userContent.userEmail}">
<td>이메일을 입력하지 않으셨습니다</td><td><button name="fixEmail" id="fixEmail">추가</button></td>
</c:when>
<c:otherwise>
<td>${userContent.userEmail}</td><td><button name="fixEmail" id="fixEmail">수정</button></td>
</c:otherwise>
</c:choose>
</tr>
<tr>
<td>전화번호</td>
<c:choose>
<c:when test="${empty userContent.userPhone}">
<td>전화번호를 입력하지 않으셨습니다</td><td><button name="fixEmail" id="fixEmail">추가</button></td>
</c:when>

<c:otherwise>
<td>${userContent.userPhone}</td><td><button name="fixEmail" id="fixEmail">수정</button></td>
</c:otherwise>
</c:choose>
</tr>
</table>
<script src="${ctx}/js/user/userContent.js"> </script>
<%@ include file="../../part/footer.jsp" %>