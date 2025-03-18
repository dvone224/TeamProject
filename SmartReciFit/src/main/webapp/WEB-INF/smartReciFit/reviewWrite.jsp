<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>

<div class="review-page">
	<form action="${ctx}/reviewWriteProcess.do" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="user" value="${user}">
		<table class="write-table">
			<tr class="write-row">
				<th>후기 제목</th>
				<td><input type="text" name="title" required
					placeholder="예)소고기 미역국끓이기" class="input-field"></td>
			</tr>
			<tr class="write-row">
				<th>글 내용</th>
				<td><textarea name="content" required
						placeholder="후기 내용을 작성해주세요. 예) 어머니 생신을 맞아 소고기 미역국을 끓여봤어요 너무너무 좋아하셨습니다!"
						class="textarea-field"
						style="height: 100px; width: 610px; resize: none;"></textarea></td>
			</tr>
			<tr class="write-row">
				<th>이미지</th>
				<td><input type="file" name="img" accept="image/*" required
					class="input-field"></td>
			</tr>
			<tr class="write-row">
				<td colspan="2" class="write-Allbtn">
					<button type="submit" class="write-btn">작성하기</button>
					<button type="button" class="cancel-btn" onclick="history.back()">목록으로
						돌아가기</button>
				</td>
			</tr>

		</table>
	</form>
</div>




<%@ include file="../../part/footer.jsp"%>