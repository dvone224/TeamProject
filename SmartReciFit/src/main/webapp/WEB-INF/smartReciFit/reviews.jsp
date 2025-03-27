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

/* 전체 목록 컨테이너 */
.review-list {
    max-width: 1500px; /* 게시판 최대 너비 */
    margin: 30px auto; /* 상하 여백 및 중앙 정렬 */
    padding: 20px;
}

/* 게시판 제목 */
.review-list h2 {
    text-align: center;
    margin-bottom: 25px;
    font-size: 1.8rem;
    color: #333;
}

/* 게시판 테이블 */
.review-list table {
    width: 100%;
    border-collapse: collapse; /* 테두리 합치기 */
    border-top: 2px solid #333; /* 상단 굵은 테두리 */
    margin-bottom: 20px; /* 테이블과 하단 요소 간격 */
    font-size: 0.95rem;
}

/* 테이블 헤더 (thead th) */
.review-list thead th {
    background-color: #f8f9fa; /* 헤더 배경색 */
    padding: 12px 10px;
    border-bottom: 1px solid #ddd; /* 헤더 아래 구분선 */
    text-align: center;
    font-weight: 600;
    color: #495057;
    white-space: nowrap; /* 줄바꿈 방지 */
}

/* 테이블 본문 (tbody td) */
.review-list tbody td {
    padding: 12px 10px;
    border-bottom: 1px solid #eee; /* 각 행 아래 구분선 */
    text-align: center;
    vertical-align: middle;
    color: #333;
}

/* 이미지 스타일 */
.review-list tbody img {
    max-width: 80px;
    max-height: 80px;
    object-fit: cover;
    border-radius: 4px;
    vertical-align: middle; /* 이미지가 셀 중앙에 오도록 */
}
.review-list tbody .no-image-text { /* '이미지 없음' 텍스트 스타일 */
    color: #999;
    font-size: 0.9em;
}

/* 제목 링크 스타일 */
.review-list tbody td a {
    color: #333;
    text-decoration: none;
}
.review-list tbody td a:hover {
    color: #2E8B57; /* 호버 시 색상 변경 */
    text-decoration: underline;
}
/* 제목 셀 왼쪽 정렬 */
	.review-list tbody td:nth-child(3) { /* 제목이 3번째 컬럼일 경우 */
	    text-align: left;
	    padding-left: 15px; /* 왼쪽 여백 추가 */
	}

/* 글쓴이, 등록일, 조회수, 좋아요 텍스트 스타일 */
.review-list tbody td:nth-child(4), /* 글쓴이 */
.review-list tbody td:nth-child(5), /* 등록일 */
.review-list tbody td:nth-child(6), /* 조회 */
.review-list tbody td:nth-child(7) { /* 좋아요 */
    color: #666;
    font-size: 0.9em;
}
/* 등록일 셀 줄바꿈 방지 */
.review-list tbody td:nth-child(5) {
     white-space: nowrap;
}


/* 글쓰기 버튼 영역 */
.post-area {
    text-align: right; /* 버튼 오른쪽 정렬 */
    margin-bottom: 20px; /* 버튼과 페이징 사이 간격 */
}

/* 글쓰기 버튼 */
button.review-write {
    padding: 8px 15px;
    background-color: #2E8B57;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 0.95rem;
}
button.review-write:hover {
    background-color: #3CB371;
}

/* 데이터 없을 때 스타일 */
.review-list tbody tr.no-data td {
    text-align: center;
    padding: 50px 0;
    color: #999;
    font-size: 1rem;
    border-bottom: 1px solid #eee;
}

</style>

<div class="review-list">
	<h2>Review</h2>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>게시글 사진</th>
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
					<td><c:choose>
							<c:when test="${not empty review.review_board_img}">
								<img src="${ctx}/img/${review.review_board_img}"
									alt="userReview-Image" width="100" height="100">
							</c:when>
							<c:otherwise> 
								이미지 없음
							</c:otherwise>
						</c:choose>
						</td>
					<td><a
						href="${ctx}/reviewDetail.do?reviewBoardNum=${review.review_board_num}&user=${user}&userNickname=${review.user_nickname}">
							${review.review_board_title}</a></td>
					<td>${review.user_nickname}</td>
					<td>${review.review_board_created_at}</td>
					<td>${review.review_board_views}</td>
					<td>${review.review_board_likes}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="post-area">
		<button type="button" class="review-write"
			onclick="location.href='${ctx}/reviewWrite.do?user=${user}'">글쓰기</button>
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

<%@ include file="../../part/footer.jsp"%>