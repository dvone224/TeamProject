<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../../part/header.jsp" %>

<h1> My Page </h1>

<table>
<tr>
<td colspan="2">
<c:choose>
<c:when test="${not empty userContent.userImg}"><img src="${ctx}/img/${userContent.userImg}" class="photo" id="userImg"/></c:when>
<c:otherwise><img src="${ctx}/img/ProfileBasicImg.png" class="photo" id="default"/></c:otherwise>
</c:choose>
</td>
</tr>
<tr>
<td>닉네임</td><td>${userContent.userNickName}</td>
</tr>
</table>

<br>

<!-- 소셜 로그인 아닐때 테이블 -->
<c:if test="${not empty userContent.userId}">
<table>
<tr>
<td>아이디</td><td>${userContent.userId}</td>
</tr>
<tr>
<td>비밀번호</td><td>${userContent.userPw}</td>
</tr>
<tr>
<td>이름</td><td>${userContent.userName}</td>
</tr>
<tr>
<td>E-mail</td>
<c:choose>
<c:when test="${empty userContent.userEmail}">
<td>이메일을 입력하지 않으셨습니다</td>
</c:when>
<c:otherwise>
<td>${userContent.userEmail}</td>
</c:otherwise>
</c:choose>
</tr>
<tr>
<td>전화번호</td>
<c:choose>
<c:when test="${empty userContent.userPhone}">
<td>전화번호를 입력하지 않으셨습니다</td>
</c:when>
<c:otherwise>
<td>${userContent.userPhone}</td>
</c:otherwise>
</c:choose>
</tr>
</table>
</c:if>

<button name="btn-userFix" id="btn-userFix" onclick="location.href='${ctx}/userFix.do'">회원정보수정</button>

<c:choose>
<c:when test="${empty userInfoContent}">
<table>
<tr><td><p>아직 인포를 저장하지 않았습니다.</p></td></tr>
<tr><td><button name="btn-makeInfo" id="btn-makeInfo" onclick="location.href='${ctx}/userInfo.do'">인포만들기</button></td></tr>

</table>
</c:when>
<c:otherwise>
<table>
<tr><td>나의 식사량</td><td>${userInfoMealSize}</td></tr>
<tr><td>나의 선호 TAG</td><td>
<c:forEach var="info" items="${totalInfo}">${info}</c:forEach>
</td></tr>
<!-- 이거 이렇게 냅다 연결시키면 냅다 입력이 되니까 이거 막아주는거 하나 장치 마련하기  -->
<tr><td colspan="2"><button name="btn-makeInfo" id="btn-makeInfo" onclick="location.href='${ctx}/userInfo.do'">인포수정하기</button></td></tr>
</table>
</c:otherwise>
</c:choose>
<button name="btn-userDel" id="btn-userDel" onclick="location.href='${ctx}/userDel.do'">회원탈퇴</button>

<script src="${ctx}/js/user/userContent.js"> </script>
<%@ include file="../../part/footer.jsp" %>