<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>

<style>
/* 메인 컬러 정의 */
:root {
	--primary-color: #2E8B57; /* 진한 녹색 */
	--secondary-color: #3CB371; /* 중간 녹색 */
	--accent-color: #90EE90; /* 밝은 녹색 */
	--background-color: #f8f8f8; /* 연한 회색 배경 */
	--text-color: #333; /* 기본 글자 색상 */
	--shadow-color: rgba(0, 0, 0, 0.1); /* 그림자 색상 */
}

/* 전체 페이지 스타일 */
.main-content {
	padding: 20px;
	font-family: Arial, sans-serif;
}

/* 랭킹 섹션 스타일 */
.ranking-section {
	margin-bottom: 30px;
	border-radius: 15px;
	overflow: hidden;
	box-shadow: 0 5px 15px var(--shadow-color);
	border: 1px solid var(--accent-color); /* 테두리 추가 */
}

/* 랭킹 제목 스타일 */
.ranking-section .title {
	font-size: 24px;
	font-weight: bold;
	color: white;
	text-align: center;
	padding: 15px;
	background-color: var(--primary-color);
	letter-spacing: 1px; /* 글자 간격 조정 */
}

/* 랭킹 항목 스타일 */
.ranking-entries {
	display: flex;
	justify-content: center;
	flex-wrap: wrap;
	padding: 15px;
}

/* 각 랭킹 항목 (카드) 스타일 */
.ranking-entry {
	display: flex;
	flex-direction: column;
	width: 220px; /* 너비 조정 */
	margin: 10px; /* 여백 조정 */
	border-radius: 10px;
	overflow: hidden;
	box-shadow: 0 4px 8px var(--shadow-color);
	transition: transform 0.3s ease, box-shadow 0.3s ease;
	text-decoration: none;
	color: var(--text-color);
	background-color: white;
	border: 1px solid var(--accent-color); /* 테두리 추가 */
}

.ranking-entry:hover {
	transform: translateY(-3px); /* 호버 시 약간 위로 이동 */
	box-shadow: 0 6px 12px var(--shadow-color);
}

.ranking-entry img {
	width: 100%;
	height: 180px; /* 높이 조정 */
	object-fit: cover;
	transition: transform 0.3s ease;
}

.ranking-entry:hover img {
	transform: scale(1.05); /* 호버 시 이미지 확대 */
}

/* 랭킹 숫자 및 제목 스타일 */
.ranking-entry .rank-and-title {
	padding: 10px;
	text-align: center;
}

.ranking-entry .rank {
	font-size: 26px;
	font-weight: bold;
	color: var(--secondary-color);
	margin-bottom: 5px;
}

.ranking-entry .recipe-title {
	font-size: 17px;
	color: var(--text-color);
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	padding: 0 5px; /* 제목 양쪽 패딩 추가 */
}

/* 조회수/좋아요 스타일 */
.ranking-entry .ranking-details {
	padding: 8px 12px;
	background-color: var(--accent-color);
	text-align: center;
	border-top: 1px solid #eee;
	display: flex;
	justify-content: space-around;
}

.ranking-entry .ranking-details p {
	font-size: 14px;
	color: var(--text-color);
	margin: 0;
	display: flex;
	align-items: center;
	justify-content: center; /* 아이콘과 텍스트를 세로로 중앙 정렬 */
}

.ranking-entry .ranking-details i {
	margin-right: 3px; /* 아이콘과 텍스트 간 간격 조정 */
	font-size: 16px;
}

/* 반응형 스타일 */
@media (max-width: 768px) {
	.ranking-entries {
		flex-direction: column;
		align-items: center;
	}

	.ranking-entry {
		width: 90%;
	}
}
</style>

<div class="main-content">
	<!-- API 조회수 랭킹 -->
	<div class="ranking-section">
		<div class="title">레시피 조회수 랭킹</div>
		<div class="ranking-entries">
			<c:forEach var="recipe" items="${topRecipesByViews}" varStatus="status">
				<a href="${ctx}/recipeContent.do?rn=${recipe.reviewBoardRecipeId}"
					class="ranking-entry"> <img src="${recipe.recipeThumbnail}"
					alt="Recipe Image">
					<div class="rank-and-title">
						<div class="rank">${status.index + 1}</div>
						<div class="recipe-title">${recipe.recipeName}</div>
					</div>
					<div class="ranking-details">
						<p>
							<i class="fas fa-eye"></i>${recipe.reviewBoardViews}
						</p>
					</div>
				</a>
			</c:forEach>
		</div>
	</div>

	<!-- 유저 랭킹 -->
	<div class="ranking-section">
		<div class="title">레시피 좋아요 랭킹</div>
		<div class="ranking-entries">
			<c:forEach var="recipe" items="${topRecipesByLike}" varStatus="status">
				<a href="${ctx}/recipeContent.do?rn=${recipe.reviewBoardRecipeId}"
					class="ranking-entry"> <img src="${recipe.recipeThumbnail}"
					alt="Recipe Image">
					<div class="rank-and-title">
						<div class="rank">${status.index + 1}</div>
						<div class="recipe-title">${recipe.recipeName}</div>
					</div>
					<div class="ranking-details">
						<p>
							<i class="fas fa-heart"></i>${recipe.reviewBoardLikes}
						</p>
					</div>
				</a>
			</c:forEach>
		</div>
	</div>
</div>

<%@ include file="../../part/footer.jsp"%>