<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../../part/header.jsp" %>

<h1> My Page </h1>

<table>
<tr>
<td colspan="3">
<c:choose>
<c:when test="${not empty userContent.userImg}"><img src="${ctx}/img/${userContent.userImg}" class="photo" id="userImg"/></c:when>
<c:otherwise><img src="${ctx}/img/ProfileBasicImg.png" class="photo" id="default"/></c:otherwise>
</c:choose>
</td>
</tr>
<tr>
<td colspan="3"><button name="fixImg" id="fixImg">수정</button></td>
</tr>
<tr>
<td>닉네임</td><td>${userContent.userNickName}</td><td><button name="btn-fixNickName" id="btn-fixNickName">수정</button></td>
</tr>
</table>

<br>

<!-- 소셜 로그인 아닐때 테이블 -->
<c:if test="${not empty userContent.userId}">
<table>
<tr>
<td>아이디</td><td>${userContent.userId}</td><td><button name="btn-fixId" id="btn-fixId">수정</button></td>
</tr>
<tr>
<td>비밀번호</td><td>${userContent.userPw}</td><td><button name="btn-fixPw" id="btn-fixPw">수정</button></td>
</tr>
<tr>
<td>이름</td><td>${userContent.userName}</td><td><button  name="btn-fixName" id="btn-fixName">수정</button></td>
</tr>
<tr>
<td>E-mail</td>
<c:choose>
<c:when test="${empty userContent.userEmail}">
<td>이메일을 입력하지 않으셨습니다</td><td><button name="btn-fixEmail" id="btn-fixEmail">추가</button></td>
</c:when>
<c:otherwise>
<td>${userContent.userEmail}</td><td><button name="btn-fixEmail" id="btn-fixEmail">수정</button></td>
</c:otherwise>
</c:choose>
</tr>
<tr>
<td>전화번호</td>
<c:choose>
<c:when test="${empty userContent.userPhone}">
<td>전화번호를 입력하지 않으셨습니다</td><td><button name="btn-fixEmail" id="btn-fixEmail">추가</button></td>
</c:when>
<c:otherwise>
<td>${userContent.userPhone}</td><td><button name="btn-fixEmail" id="btn-fixEmail">수정</button></td>
</c:otherwise>
</c:choose>
</tr>
</table>
</c:if>

<!-- 소셜 로그인일때 테이블 -->
<%-- <c:if test="${userContent.platformK==true||userContent.platformN==true||userContent.platformG==true}">
<table>

<c:if test="${userContent.platformK==true}"> <tr><td colspan="2">카카오 로그인 계정입니다</td><td><button id="btn-socialOutK">연동해제</button></td></tr></c:if>
<c:if test="${userContent.platformN==true}"> <tr><td colspan="2">네이버 로그인 계정입니다</td><td><button id="btn-socialOutN">연동해제</button></td></tr></c:if>
<c:if test="${userContent.platformG==true}"> <tr><td colspan="2">구글 로그인 계정입니다</td><td><button id="btn-socialOutG">연동해제</button></td></tr></c:if>

<tr>
<td colspan="3"> <button name="fixImg" id="fixImg">수정</button> </td>
</tr>
<tr>
<td colspan="3">
<div class="userContentImg">
<c:if test="${not empty userContent.userImg}">
<img src="${ctx}/img/${userContent.userImg}" class="photo" id="userImg"/>
</c:if>
<c:if test="${empty userContent.userImg}">
<img src="${ctx}/img/ProfileBasicImg.png" class="photo" id="default"/>
</c:if>
</div>
</td>
</tr>
<tr>
<td colspan="3"><button name="fixImg" id="fixImg">수정</button></td>
</tr>
</table>
</c:if>

<!-- 일만 로그인만 있을때: 소셜로그인 연동 시도 버튼/회원탈퇴 -->
<!-- 소셜 로그인만 있을때: 회원가입 시도 버튼/회원탈퇴 -->
<!-- 일반+소셜 로그인 있을때: 연동해제/회원탈퇴 -->
<div class="btn-box">
<c:choose>
<c:when test="${userContent.platformK==false&&userContent.platformN==false&&userContent.platformG==false}">
<button id="btn-socialInK">카카오 연동</button>
<button id="btn-socialInN">네이버 연동</button>
<button id="btn-socialInG">구글 연동</button>
</c:when>
<c:when test="${empty userContent.userId}">
<button id="btn-UserJoin">회원가입</button>
<!-- 기존 회원가입 페이지로 연결되는데, 로그인 상태인지 아닌지로 판단하기: 로그인 상태에서 넘어가면 추가로직으로, 아니면 가입로직으로 -->
</c:when>
</c:choose> --%>

<c:choose>
<c:when test="${empty voInfo}">
<table>
<tr><td><p>아직 인포를 저장하지 않았습니다.</p></td></tr>
<tr><td><button name="btn-makeInfo" id="btn-makeInfo" onclick="location.href='${ctx}/userInfo.do'">인포만들기</button></td></tr>

</table>
</c:when>
<c:otherwise>
<table>
<tr><td>나의 식사량</td><td>${voMealSize}</td></tr>
<tr><td>내가 선호하는 태그</td><td></td></tr>
<tr><td colspan="2"><button name="btn-makeInfo" id="btn-makeInfo" onclick="location.href='${ctx}/userInfo.do'">인포수정하기</button></td></tr>
</table>
</c:otherwise>
</c:choose>

<button id="btn-UserDel">회원탈퇴</button>
<!-- </div> -->

<script src="${ctx}/js/user/userContent.js"> </script>
<%@ include file="../../part/footer.jsp" %>