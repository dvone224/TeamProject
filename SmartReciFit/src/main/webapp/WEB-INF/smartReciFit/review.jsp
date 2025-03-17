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
</style> <!-- 임시로 여기에 넣엇습니다 -->
<div class="review-list">
    <h2>리뷰 게시판</h2>
    <div class="review-grid">
        <c:forEach var="review" items="${reviews}">
            <div class="review-item">
                <a href="${ctx}/reviewDetail.do?reviewBoardNum=${review.reviewBoardNum}">
                    <img src="${review.reviewBoardImg}" alt="${review.reviewBoardTitle}" width="200" height="150">
                </a>
                <h3>${review.reviewBoardTitle}</h3>
                <p>조회수: ${review.reviewBoardViews}</p>
            </div>
        </c:forEach>
    </div>
</div>


<%@ include file="../../part/footer.jsp"%>

