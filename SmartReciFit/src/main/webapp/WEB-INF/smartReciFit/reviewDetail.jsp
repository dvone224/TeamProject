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

.like-button {
	background: none;
	border: none;
	padding: 0;
	font-size: 20px;
	color: red;
	cursor: pointer;
}

.like-button.active {
	color: darkred;
}
</style>

<div class="review-detail">
    <h1>${review.reviewBoardTitle}</h1>
    <p>글쓴이: ${userNickname}</p>
    <img src="${review.reviewBoardImg}" alt="${review.reviewBoardTitle}"
         width="400">
    <p>내용: ${review.reviewBoardContent}</p>
    <p>조회수: ${review.reviewBoardViews}</p>
    <p>
        좋아요: <span id="like-count">
            ${totalLikes}
        </span>
        <button class="like-button" onclick="toggleLike(${review.reviewBoardNum})">
            <c:choose>
                <c:when test="${liked}">
                    ❤️
                </c:when>
                <c:otherwise>
                    🤍
                </c:otherwise>
            </c:choose>
        </button>
    </p>

    <p>작성일: ${review.reviewBoardCreated}</p>
    <c:if test="${review.userNum == userNum}">
    <div class="review-update-delete">
    	<button onclick="location.href='${ctx}/reviewUpdate.do?reviewBoardNum=${review.reviewBoardNum}'">수정하기</button>
    	<button onclick="location.href='${ctx}/reviewDelete.do?reviewBoardNum=${review.reviewBoardNum}'">삭제하기</button>
    </div>
    </c:if>
    <h3>댓글</h3>
    <div class="comment-form">
        <form action="${ctx}/commentAdd.do" method="post">
            <input type="hidden" name="boardNum" value="${review.reviewBoardNum}">
            <textarea name="commentContent" placeholder="댓글을 입력하세요" required
                      style="height: 100px; width: 610px; resize: none"></textarea>
            <button type="submit">댓글 작성</button>
        </form>
    </div>

    <div class="comment-list">
        <c:forEach var="comment" items="${comments}">
            <div class="comment-item" id="comment-item-${comment.commentNum}">
                <div class="comment-div" id="comment-div-${comment.commentNum}">${comment.commentContent}</div>
                <small>작성자: ${comment.userNickname}, 작성일:
                        ${comment.commentCreated}</small>
                <c:if test="${userNum eq comment.userNum or userNum eq 1}">
                    <button class="comment-btn" id="comment-btn-${comment.commentNum}"
                            onclick="showEdit(${comment.commentNum}, ${review.reviewBoardNum})">수정</button>
                    <form action="${ctx}/commentDelete.do" method="post"
                          style="display: inline;">
                        <input type="hidden" name="commentNum"
                               value="${comment.commentNum}">
                        <input type="hidden" name="boardNum"
                               value="${review.reviewBoardNum}">
                        <button type="submit">삭제</button>
                    </form>
                </c:if>
            </div>
        </c:forEach>
    </div>

</div>


<script src="${ctx}/js/board/comment.js"></script>
<%@ include file="../../part/footer.jsp"%>