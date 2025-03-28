<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="kr.smartReciFit.model.user.User" %>
<!DOCTYPE html>
<%@ include file="../../part/header.jsp"%>
<c:if test="${userContent eq null}">
	<c:redirect url="/userContent.do?num=${sessionScope.log}" />
</c:if>

<h1>My Page</h1>

<table>
	<tr>
		<td colspan="2"><c:choose>
				<c:when test="${not empty userContent.userImg}">
					<img src="${ctx}/img/${userContent.userImg}" class="photo"
						id="userImg" />
				</c:when>
				<c:otherwise>
					<img src="${ctx}/img/ProfileBasicImg.png" class="photo"
						id="default" />
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td>닉네임</td>
		<td>${userContent.userNickName}</td>
	</tr>
</table>

<br>

<!-- 소셜 로그인 아닐때 테이블 -->
<c:if test="${not empty userContent.userId}">
	<table>
		<tr>
			<td>아이디</td>
			<td>${userContent.userId}</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>${userContent.userPw}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${userContent.userName}</td>
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

<button name="btn-fixUser" id="btn-fixUser" onclick="location.href='${ctx}/userFix.do?num=${userContent.userNum}'">회원정보수정</button>

<c:choose>
<c:when test="${empty userInfoContent}">
<table>
<tr><td><p>아직 인포를 저장하지 않았습니다.</p></td></tr>
<tr><td><button name="btn-makeInfo" id="btn-makeInfo" onclick="location.href='${ctx}/userInfoFix.do">인포만들기</button></td></tr>
</table>
</c:when>

<c:otherwise>
<table>
<tr><td>나의 식사량</td><td>${userInfoMealSize}</td></tr>
<tr><td>나의 선호 TAG</td><td>
<c:forEach var="info" items="${totalInfo}">${info}</c:forEach>
</td></tr>
<!-- 이거 이렇게 냅다 연결시키면 냅다 입력이 되니까 이거 막아주는거 하나 장치 마련하기  -->
<tr><td colspan="2">
<form id="userInfoFixForm" action="${ctx}/userInfoFix.do" method="post">
    <input type="hidden" name="num" value="${userContent.userNum}">
    <input type="hidden" name="userMealSize" value="${userInfoMealSize}">
    <c:forEach var="item" items="${totalInfo}">
        <input type="hidden" name="list" value="${item}">
    </c:forEach>
<button type="submit" name="btn-makeInfo" id="btn-makeInfo" >인포수정하기</button>
</form>

</td></tr>
</table>
</c:otherwise>
</c:choose>

<script src="${ctx}/js/user/userContent.js">
<!-- ============= 소셜 계정 연동 ================= -->
 <%
    // 로그인 상태인지 확인
    User user = (User) session.getAttribute("user");
    // 로그인된 상태일 때만 소셜 계정 연동 버튼 표시
    if (user != null) {
        // 세션에서 'linkedAccounts' 가져오고, 없으면 빈 HashMap 생성
        Map<String, Boolean> linkedAccounts = (Map<String, Boolean>) session.getAttribute("linkedAccounts");
        if (linkedAccounts == null) {
            linkedAccounts = new HashMap<>();
        }
%>

<table border="1">
    <tr>
        <td>카카오 계정 연동</td>
        <td>
            <form action="${ctx}/linkSocial.do" method="POST">
                <input type="hidden" name="platform" value="kakao">
                <input type="hidden" name="email" value="${sessionScope.user.userEmail}">
                <button type="submit" 
                    <% if (linkedAccounts.getOrDefault("kakao", false)) { %> disabled <% } %>>
                    <%= linkedAccounts.getOrDefault("kakao", false) ? "연동됨" : "연동" %>
                </button>
            </form>
        </td>
    </tr>
    <tr>
        <td>네이버 계정 연동</td>
        <td>
            <form action="${ctx}/linkSocial.do" method="POST">
                <input type="hidden" name="platform" value="naver">
                <input type="hidden" name="email" value="${sessionScope.user.userEmail}">
                <button type="submit" 
                    <% if (linkedAccounts.getOrDefault("naver", false)) { %> disabled <% } %>>
                    <%= linkedAccounts.getOrDefault("naver", false) ? "연동됨" : "연동" %>
                </button>
            </form>
        </td>
    </tr>
    <tr>
        <td>구글 계정 연동</td>
        <td>
            <form action="${ctx}/linkSocial.do" method="POST">
                <input type="hidden" name="platform" value="google">
                <input type="hidden" name="email" value="${sessionScope.user.userEmail}">
                <button type="submit" 
                    <% if (linkedAccounts.getOrDefault("google", false)) { %> disabled <% } %>>
                    <%= linkedAccounts.getOrDefault("google", false) ? "연동됨" : "연동" %>
                </button>
            </form>
        </td>
    </tr>
</table>


<%
    // 세션에서 메시지 가져오기
    String message = (String) session.getAttribute("message");
    if (message != null && !message.isEmpty()) {
%>
    <script>
        alert("<%= message %>");
    </script>
    
<%
        session.removeAttribute("message"); // 메시지 삭제 (새로고침 시 alert 안 뜨게)
    }
    
%>
<% 
    }
%>
<p style="font-size: 12px; color: #555; margin-top: 10px;">
   ⚠️ 회원가입한 이메일을 사용하는 계정을 연동해주세요. 연동시 기존 로그인으로 접속됩니다.
</p> 

</c:if>
<button name="btn-userFix" id="btn-userFix" onclick="location.href='${ctx}/userFix.do'">회원정보수정</button>


<c:choose>
   <c:when test="${empty userInfoContent}">
      <table>
         <tr>
            <td><p>아직 인포를 저장하지 않았습니다.</p></td>
         </tr>
         <tr>
            <td><button name="btn-makeInfo" id="btn-makeInfo"
                  onclick="location.href='${ctx}/userInfo.do'">인포만들기</button></td>
         </tr>

      </table>
   </c:when>
   <c:otherwise>
      <table>
         <tr>
            <td>나의 식사량</td>
            <td>${userInfoMealSize}</td>
         </tr>
         <tr>
            <td>나의 선호 TAG</td>
            <td><c:forEach var="info" items="${totalInfo}">${info}</c:forEach>
            </td>
         </tr>
         <!-- 이거 이렇게 냅다 연결시키면 냅다 입력이 되니까 이거 막아주는거 하나 장치 마련하기  -->
         <tr>
            <td colspan="2"><button name="btn-makeInfo" id="btn-makeInfo"
                  onclick="location.href='${ctx}/userInfo.do'">인포수정하기</button></td>
         </tr>
      </table>
   </c:otherwise>
</c:choose>
<button name="btn-userDel" id="btn-userDel"
   onclick="location.href='${ctx}/userDel.do'">회원탈퇴</button>

<script src="${ctx}/js/user/userContent.js">
   
</script>
<%@ include file="../../part/footer.jsp"%>