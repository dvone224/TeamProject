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

<table border="1">
    <tr>
        <td>카카오 계정 연동</td>
        <td>
            <form action="${ctx}/linkSocial.do" method="POST">
                <input type="hidden" name="platform" value="kakao">
                <input type="hidden" name="email" value="${sessionScope.socialLogin.kakao}">
                <button type="submit">연동</button>
            </form>
        </td>
    </tr>
    <tr>
        <td>네이버 계정 연동</td>
        <td>
            <form action="${ctx}/linkSocial.do" method="POST">
                <input type="hidden" name="platform" value="naver">
                <input type="hidden" name="email" value="${sessionScope.socialLogin.naver}">
                <button type="submit">연동</button>
            </form>
        </td>
    </tr>
    <tr>
        <td>구글 계정 연동</td>
        <td>
            <form action="${ctx}/linkSocial.do" method="POST">
                <input type="hidden" name="platform" value="google">
                <input type="hidden" name="email" value="${sessionScope.socialLogin.google}">
                <button type="submit">연동</button>
            </form>
        </td>
    </tr>
</table>
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