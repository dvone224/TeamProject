<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.List" %>

<div class="review-page">
	<form action="${ctx}/reviewUpdateProcess.do?reviewBoardNum=${review.reviewBoardNum}&userNickname=${userNickname}" method="post"
		enctype="multipart/form-data">
		<table class="update-table">
			<tr class="update-row">
				<th>후기 제목</th>
				<td><input type="text" name="title" required
					value="${review.reviewBoardTitle}" class="input-field"></td>
			</tr>
			<tr class="update-row">
				<th>글 내용</th>
				<td><textarea name="content" required
						class="textarea-field"
						style="height: 100px; width: 610px; resize: none;">${review.reviewBoardContent}</textarea></td>
			</tr>
			<tr class="update-row">
    <th>이미지</th>
    <td>
        <!-- 기존 이미지 표시 -->
        <div class="review-image">
            <c:if test="${not empty imagePaths}">
                <c:forEach var="imagePath" items="${imagePaths}" varStatus="status">
                    <div>
                        <img id="previewImg${status.index + 1}" src="${ctx}/img/${imagePath}" alt="Image ${status.index + 1}" style="width: 100px; height: 100px; object-fit: cover; margin: 5px;">
                        <input type="file" name="img${status.index + 1}"
                               accept="image/*" class="input-field" onchange="previewImage(this, ${status.index + 1})">
                        <input type="hidden" name="existingImg${status.index + 1}"
                               value="${imagePath}">
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </td>
</tr>
			<tr class="write-row">
				<td colspan="2" class="update-Allbtn">
					<button type="submit" class="update-btn">수정하기</button>
					<button type="button" class="update-cancel-btn" onclick="history.back()">돌아가기</button>
				</td>
			</tr>
		</table>
		<input type="hidden" name="reviewBoardNum" value="${review.reviewBoardNum}">
	</form>
</div>

	
<script src="${ctx}/js/board/updateReview.js"></script>
<%@ include file="../../part/footer.jsp"%>
