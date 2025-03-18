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

.comment-list {
    margin-top: 20px;
}
.comment-item {
    border-bottom: 1px solid #ddd;
    padding: 10px 0;
}
}
</style>

<div class="review-detail">
    <h1>${review.reviewBoardTitle}</h1>
    <img src="${review.reviewBoardImg}" alt="${review.reviewBoardTitle}" width="400">
    <p>${review.reviewBoardContent}</p>
    <p>조회수: ${review.reviewBoardViews}</p>
    <p>좋아요: ${review.reviewBoardLikes}</p>
    <p>작성일: ${review.reviewBoardCreated}</p>
   <div class="comment-form">
        <form action="${ctx}/commentAdd.do" method="post">
            <input type="hidden" name="boardNum" value="${review.reviewBoardNum}">
            <input type="hidden" name="user" value="${user}">
            <textarea name="commentContent" placeholder="댓글을 입력하세요" required style="height: 100px; width: 610px; resize: none"></textarea>
    <c:if test="${not empty user}">
            <button type="submit">댓글 작성</button>
        </form>
    </c:if>

</div>


   <div class="comment-list">
    <c:forEach var="comment" items="${comments}">
        <div class="comment-item">
            <p>${comment.commentContent}</p>
            <small>작성자: ${comment.userNum}, 작성일: ${comment.commentCreated}</small>
            <%-- <c:if test="${sessionScope.userNum == comment.userNum}">
                <form action="${ctx}/commentDelete.do" method="post" style="display:inline;">
                    <input type="hidden" name="commentNum" value="${comment.commentNum}">
                    <input type="hidden" name="boardNum" value="${review.reviewBoardNum}">
                    <button type="submit">삭제</button>
                </form>
            </c:if> --%>
        </div>
    </c:forEach>
</div>
    
</div>

<%@ include file="../../part/footer.jsp"%>

