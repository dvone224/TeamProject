<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>

<style>
.review-grid {
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	gap: 20px;
	padding: 20px;
}

.review-item {
	text-align: center;
}

.review-item img {
	cursor: pointer;
}
</style>
<!-- 임시로 여기에 넣엇습니다 -->
<div class="review-list">
	<h2>Review</h2>
	<table>
		<tr>
			<th>번호</th>
			<th>게시글 사진</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>등록일</th>
			<th>조회</th>
			<th>좋아요</th>
		</tr>

		<div class="review-grid">

			<c:forEach var="review" items="${reviews}">
				<tr>
					<div class="review-item">
						<td>${review.review_board_num}</td>
						<td><img src="${review.review_board_img}"
							alt="userReview-Image"></td>
						<td><a
							href="${ctx}/reviewDetail.do?reviewBoardNum=${review.review_board_num}&user=${user}&userNickname=${review.user_nickname}">
								${review.review_board_title}</a></td>
						<td>${review.user_nickname}</td>
						<td>${review.review_board_created_at}</td>
						<td>${review.review_board_views}</td>
						<td>${review.review_board_likes}</td>
					</div>
				</tr>
			</c:forEach>

		</div>
	</table>
	<div class="paging">
		<c:if test="${page > 1}">
			<a href="${ctx}/reviews.do?page=${page - 1}">[이전]</a>
		</c:if>
		<c:forEach begin="1" end="${totalPages}" var="i">
			<a href="${ctx}/reviews.do?page=${i}">${i}</a>
		</c:forEach>
		<c:if test="${page < totalPages}">
			<a href="${ctx}/reviews.do?page=${page + 1}">[이후]</a>
		</c:if>
	</div>
	<div class="post-area">
		<button type="button" class="review-write"
			onclick="location.href='${ctx}/reviewWrite.do?user=${user}'">글쓰기</button>
	</div>
</div>


<%@ include file="../../part/footer.jsp"%>
