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

<button name="btn-makeInfo" id="btn-makeInfo" onclick="location.href='${ctx}/userFix.do?num='5'"">회원정보수정</button>

<c:choose>
<c:when test="${empty userInfoContent}">
<table>
<tr><td><p>아직 인포를 저장하지 않았습니다.</p></td></tr>
<tr><td><button name="btn-makeInfo" id="btn-makeInfo" onclick="location.href='${ctx}/userInfoFix.do"">인포만들기</button></td></tr>
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
</c:choose>

<script src="${ctx}/js/user/userContent.js">
<%@ include file="../../part/footer.jsp"%>