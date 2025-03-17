<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>
<style>
.review-detail {
    max-width: 800px;
    margin: 20px auto;
    padding: 20px;
}
.review-detail img {
    display: block;
    margin: 20px auto;
}
</style>

<div class="review-detail">
    <h1>${review.reviewBoardTitle}</h1>
    <img src="${review.reviewBoardImg}" alt="${review.reviewBoardTitle}" width="400">
    <p>${review.reviewBoardContent}</p>
    <p>조회수: ${review.reviewBoardViews}</p>
    <p>좋아요: ${review.reviewBoardLikes}</p>
    <p>작성일: ${review.reviewBoardCreated}</p>
</div>

<%@ include file="../../part/footer.jsp"%>

