<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>

<style>
/* --- 기존 스타일 (페이징, 검색 등) --- */
.paging { text-align: center; margin-top: 20px; }
.paging a, .paging span { display: inline-block; padding: 5px 10px; margin: 0 3px; border: 1px solid #ccc; text-decoration: none; color: #333; transition: background-color 0.3s ease, color 0.3s ease; }
.paging a.current { background-color: #2E8B57; color: white; border-color: #2E8B57; }
.paging a:hover { background-color: #3CB371; color: white; }
.paging span { color: #999; border-color: transparent; }
.search-area { text-align: center; margin-top: 20px; }
.search-area select, .search-area input { padding: 5px; border: 1px solid #ccc; margin-right: 5px; vertical-align: middle; }
.search-btn { padding: 5px 10px; background-color: #3CB371; color: white; border: none; cursor: pointer; vertical-align: middle; }

/* --- 게시판 스타일 추가 --- */

.review-list {
    max-width: 1500px;
    margin: 30px auto;
    padding: 20px;
}

.review-list h2 {
    text-align: center;
    margin-bottom: 25px;
    font-size: 1.8rem;
    color: #333;
}

.review-list table {
    width: 100%;
    border-collapse: collapse; /* 합치기 유지 */
    border: none;
    margin-bottom: 20px;
    font-size: 0.95rem;
    border-spacing: 0;
}

.review-list thead th {
    background-color: #c4fbc150; /* 연한 회색 배경 */
    padding: 15px 10px; /* 헤더 패딩 증가 */
    border: none; /* 헤더의 모든 테두리 제거 */
    border-bottom: 1px solid #dee2e6; /* 헤더 아래 구분선만 다시 추가 */
    text-align: center;
    font-weight: 600;
    color: #6c757d; /* 헤더 글자색 약간 연하게 */
    white-space: nowrap;
    font-size: 0.9rem;
}

/* 모든 tbody td 스타일 변경 - 모든 선 제거! */
.review-list tbody td {
    padding: 16px 10px; /* 상하 패딩 늘려서 행 간격 확보 */
    border: none; /* 모든 테두리 제거! */
    text-align: center;
    vertical-align: middle;
    color: #343a40; /* 본문 글자색 약간 진하게 */
}

/* 제목 링크 스타일 */
.review-title-link {
    color: #343a40;
    text-decoration: none;
    cursor: pointer;
    font-weight: 500;
}
.review-title-link:hover {
    color: #2E8B57;
    text-decoration: underline;
}

/* 특정 컬럼 스타일 */
.review-list tbody td:nth-child(3), /* 글쓴이 */
.review-list tbody td:nth-child(4), /* 등록일 */
.review-list tbody td:nth-child(5), /* 조회 */
.review-list tbody td:nth-child(6) { /* 좋아요 */
    color: #6c757d;
    font-size: 0.9em;
}
.review-list tbody td:nth-child(4) { /* 등록일 */
     white-space: nowrap;
}

/* 이미지 아이콘 스타일 */
.image-icon {
    margin-right: 6px;
    font-size: 1em;
    vertical-align: -2px;
    cursor: default;
    display: inline-block;
    opacity: 0.7;
}

/* 댓글 수 스타일 */
.comment-count {
    color: #2E8B57;
    font-size: 0.85em;
    margin-left: 6px;
    font-weight: bold;
    vertical-align: middle;
}

/* 글쓰기 버튼 영역 */


.action-buttons-area {
    text-align: right; /* 버튼들을 오른쪽으로 정렬 */
    margin-top: 20px; /* 테이블과의 간격 */
    margin-bottom: 20px; /* 페이징과의 간격 */
}

/* 글쓰기 버튼 스타일 */
.action-buttons-area .review-write { /* 선택자 구체화 */
    padding: 8px 15px;
    background-color: #2E8B57;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 0.95rem;
    margin-left: 10px; /* 다른 버튼과의 간격 */
}
.action-buttons-area .review-write:hover { background-color: #3CB371; }

/* 목록 버튼 스타일 */
.action-buttons-area .list-btn { /* 클래스 이름 변경 또는 기존 .list-btn-area button 선택자 활용 */
    padding: 8px 15px;
    background-color: #6c757d;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 0.95rem;
    margin-left: 10px; /* 글쓰기 버튼과의 간격 (글쓰기 없을 때도 적용됨) */
}
.action-buttons-area .list-btn:hover { background-color: #5a6268; }





/* 이미지 미리보기 팝업 */
#image-preview-popup {
    display: none;
    position: absolute;
    border: 1px solid #ccc;
    background-color: white;
    padding: 5px;
    box-shadow: 2px 2px 5px rgba(0,0,0,0.2);
    z-index: 1000;
    max-width: 100px;
    max-height: 100px;
    overflow: hidden;
}
#image-preview-popup img {
    display: block;
    max-width: 100%;
    max-height: 90px;
    object-fit: contain;
}

/* 데이터 없을 때 */
.review-list tbody tr.no-data td {
    text-align: center;
    padding: 50px 0;
    color: #999;
    font-size: 1rem;
    border: none; /* 데이터 없을 때도 테두리 없음 */
}

</style>

<div class="review-list">
	<h2>Review</h2>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>등록일</th>
				<th>조회</th>
				<th>좋아요</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="review" items="${reviews}">
				<tr>
					<td>${review.review_board_num}</td>
					
					<td>
					<c:if test="${not empty review.review_board_img}">
						<span class="image-icon" title="이미지 있음">🖼️</span>
					</c:if>
					
					<a
						href="${ctx}/reviewDetail.do?reviewBoardNum=${review.review_board_num}&user=${user}&userNickname=${review.user_nickname}" class="review-title-link"
						data-img-src="${not empty review.review_board_img ? review.review_board_img : ''}">
							${review.review_board_title}
							</a>
						<c:if test="${review.comment_count > 0}">
							<span class="comment-count">[${review.comment_count}]</span>
						</c:if>
					</td>
					<td>${review.user_nickname}</td>
					<td>${review.review_board_created_at}</td>
					<td>${review.review_board_views}</td>
					<td>${review.review_board_likes}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		
       	<div class="action-buttons-area">
			<c:if test="${not empty user}">
           <button type="button" class="review-write"
               onclick="location.href='${ctx}/reviewWrite.do?user=${user}'">글쓰기</button>
  		 </c:if>
       <button class="list-btn" onclick="location.href='${ctx}/reviews.do?page=1'">목록</button>
       </div>

	<div class="paging">
		<c:if test="${page > 1}">
			<a href="${ctx}/reviews.do?page=${page - 1}">< 이전</a>
		</c:if>
		<c:if test="${page <= 1}">
			<span>< 이전</span>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<c:choose>
				<c:when test="${i == page}">
					<a class="current" href="${ctx}/reviews.do?page=${i}">${i}</a>
				</c:when>
				<c:otherwise>
					<a href="${ctx}/reviews.do?page=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${page < totalPages}">
			<a href="${ctx}/reviews.do?page=${page + 1}">다음 ></a>
		</c:if>
		<c:if test="${page >= totalPages}">
			<span>다음 ></span>
		</c:if>
	</div>
	<div class="search-area">
		<form action="${ctx}/searchReviewBoard.do" method="post">
			<select name="searchName">
				<option value="title" selected>제목</option>
				<option value="titleAndContent">제목+내용</option>
			</select> <input type="text" name="keyword" class="search-area-input">
			<button type="submit" class="search-btn">검색</button>

		</form>
	</div>
</div>

<div id="image-preview-popup">
    <img id="preview-image" src="" alt="미리보기">
</div>
<script src="${ctx}/js/board/previewImage.js"></script>
<%@ include file="../../part/footer.jsp"%>