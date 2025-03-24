<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>

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
				<div class="review-image">
					<img src="${ctx}/img/${review.reviewBoardImg}">
				</div>
				<input type="file" name="img" accept="image/*" class="input-field">
				<input type="hidden" name="reiveImg" value="${review.reviewBoardImg}">
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

	

<%@ include file="../../part/footer.jsp"%>
