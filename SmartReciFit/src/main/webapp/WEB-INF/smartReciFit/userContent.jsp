<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../../part/header.jsp"%>
<style>
    table {
        border-collapse: collapse; /* 테두리 병합 */
        width: 300px; /* 테이블 너비 조정 */
    }

    th, td {
        border: 1px solid #ddd; /* 얇은 테두리 */
        padding: 8px; /* 셀 간격 조정 */
        text-align: center; /* 텍스트 가운데 정렬 */
    }
</style>


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

<button name="btn-userFix" id="btn-userFix" onclick="location.href='${ctx}/userFix.do'">회원정보수정</button>

<table border="1">
<%-- <table border="1">
	<tr>
		<td>카카오 계정 연동</td>
		<td>
			<form action="${ctx}/linkSocial.do" method="POST">
				<input type="hidden" name="platform" value="kakao"> <input
					type="hidden" name="email"
					value="${sessionScope.user.userEmail}">
				<button type="submit">연동</button>
			</form>
		</td>
	</tr>
	<tr>
		<td>네이버 계정 연동</td>
		<td>
			<form action="${ctx}/linkSocial.do" method="POST">
				<input type="hidden" name="platform" value="naver"> <input
					type="hidden" name="email"
					value="${sessionScope.user.userEmail}">
				<button type="submit">연동</button>
			</form>
		</td>
	</tr>
	<tr>
		<td>구글 계정 연동</td>
		<td>
			<form action="${ctx}/linkSocial.do" method="POST">
				<input type="hidden" name="platform" value="google"> <input
					type="hidden" name="email"
					value="${sessionScope.user.userEmail}">
				<button type="submit">연동</button>
			</form>
		</td>
	</tr>
</table>
<p style="font-size: 12px; color: gray; margin-top: 5px;">
	※ 현재 연동된 이메일 주소로 로그인하시면 기존 계정으로 접속됩니다.
</p> --%>
<table>
    <tr>
        <td>네이버</td>
        <td id="naverStatus">
            연동 중입니다. <button class="link-button" onclick="unlinkSocial('naver')">연동 해지</button>
        </td>
        <td>카카오</td>
        <td id="kakaoStatus">
            연동 중입니다. <button class="link-button" onclick="unlinkSocial('kakao')">연동 해지</button>
        </td>
    </tr>
    <tr>
        <td>구글</td>
        <td id="googleStatus">
            <button class="link-button" onclick="linkSocial('google')">계정 연동</button>
        </td>
    </tr>
</table>


<c:choose>
<c:when test="${empty userInfoContent}">
<table>
<tr><td><p>아직 인포를 저장하지 않았습니다.</p></td></tr>
<tr><td><button name="btn-makeInfo" id="btn-makeInfo" onclick="location.href='${ctx}/userInfoFix.do'">인포만들기</button></td></tr>

</table>
</c:when>
<c:otherwise>
<table>
<tr><td>나의 식사량</td><td>${userInfoMealSize}</td></tr>
<tr><td>나의 선호 TAG</td><td>
<c:forEach var="info" items="${totalInfo}">${info}</c:forEach>
</td></tr>
<!-- 이거 이렇게 냅다 연결시키면 냅다 입력이 되니까 이거 막아주는거 하나 장치 마련하기  -->
<tr><td colspan="2"><button name="btn-makeInfo" id="btn-makeInfo" onclick="location.href='${ctx}/userInfoFix.do'">인포수정하기</button></td></tr>
</table>
</c:otherwise>
<%-- <%
    String message = (String) session.getAttribute("message");
    if (message != null) {
%>
    <script>
        alert("<%= message %>");
    </script>
<%
        session.removeAttribute("message"); // 메시지 삭제 (새로고침해도 alert 안 뜨게!)
    }
%> --%>
<script type="text/javascript">
function linkSocial(platform) {
    // 소셜 계정 연동 로직
    alert(platform + " 계정 연동 요청");
}
function unlinkSocial(platform) {
    // 소셜 계정 연동 해제 로직
    alert(platform + " 계정 연동 해제 요청");
}


document.addEventListener('DOMContentLoaded', function() {
       loadSocialStatus();
   });

   function loadSocialStatus() {
       fetch('${ctx}/getSocialStatus.do')
           .then(response => response.json())
           .then(data => {
               displaySocialStatus('naver', data.naver);
               displaySocialStatus('kakao', data.kakao);
               displaySocialStatus('google', data.google);
               displaySocialStatus('apple', data.apple);
           })
           .catch(error => console.error('Error:', error));
   }

   function displaySocialStatus(platform, isLinked) {
       const statusElement = document.getElementById(platform + 'Status');
       if (isLinked) {
           statusElement.innerHTML = '연동 중입니다. <button class="link-button" onclick="unlinkSocial(\'' + platform + '\')">연동 해지</button>';
       } else {
           statusElement.innerHTML = '<button class="link-button" onclick="linkSocial(\'' + platform + '\')">계정 연동</button>';
       }
   }

   function linkSocial(platform) {
       fetch('${ctx}/linkSocial.do', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/x-www-form-urlencoded',
           },
           body: 'platform=' + platform + '&email=${sessionScope.user.userEmail}'
       })
           .then(response => response.text())
           .then(message => {
               alert(message);
               loadSocialStatus();
           })
           .catch(error => console.error('Error:', error));
   }

   function unlinkSocial(platform) {
       fetch('${ctx}/unlinkSocial.do', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/x-www-form-urlencoded',
           },
           body: 'platform=' + platform
       })
           .then(response => response.text())
           .then(message => {
               alert(message);
               loadSocialStatus();
           })
           .catch(error => console.error('Error:', error));
   }
</script>
<button name="btn-userFix" id="btn-userFix"
	onclick="location.href='${ctx}/userFix.do'">회원정보수정</button>

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