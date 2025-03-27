<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">

<style>
/* 메인 컬러 정의*/
:root {
    --primary-color: #2E8B57;
    --secondary-color: #3CB371;
    --accent-color: #FFD700; /* 금색 (1등 강조) */
    --silver-color: #C0C0C0; /* 은색 (2등) */
    --bronze-color: #CD7F32; /* 동색 (3등) */
    --background-color: #f9f9f9;
    --card-background: #ffffff;
    --text-color: #333;
    --subtle-text-color: #666;
    --shadow-color: rgba(46, 139, 87, 0.15);
    --border-color: #e0e0e0;
    --badge-text-color: #ffffff;
}

/* 전체 페이지 스타일*/
body {
    background-color: var(--background-color);
    font-family: 'Poppins', sans-serif;
    color: var(--text-color);
}

.main-content {
    padding: 40px 20px;
    max-width: 1200px;
    margin: 0 auto;
}

/* 랭킹 섹션 스타일*/
.ranking-section {
    margin-bottom: 50px;
}

/* 랭킹 제목 스타일*/
.ranking-section .title {
    font-size: 2rem;
    font-weight: 700;
    color: var(--primary-color);
    text-align: center;
    margin-bottom: 35px;
    padding-bottom: 15px;
    border-bottom: 3px solid var(--secondary-color);
    display: inline-block;
    position: relative;
    left: 50%;
    transform: translateX(-50%);
}

/* 랭킹 항목 컨테이너 스타일 */
.ranking-entries {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 30px;

}

/* 각 랭킹 항목 (카드) 스타일 */
.ranking-entry {
    display: flex;
    flex-direction: column;
    border-radius: 15px;
    background-color: var(--card-background);
    box-shadow: 0 6px 20px var(--shadow-color);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    text-decoration: none;
    color: var(--text-color);
    border: 1px solid var(--border-color);
    position: relative;
    order: 4; 
}

/* --- 등수별 순서 및 스타일 --- */
.ranking-entry.rank-1 {
    order: 2; /* 1등은 시각적으로 2번째 위치 (가운데) */
}
.ranking-entry.rank-2 {
    order: 1; /* 2등은 시각적으로 1번째 위치 (왼쪽) */
}
.ranking-entry.rank-3 {
    order: 3; /* 3등은 시각적으로 3번째 위치 (오른쪽) */
}

/* --- 등수별 호버 효과 --- */
.ranking-entry.rank-1:hover {
    box-shadow: 0 12px 30px rgba(255, 215, 0, 0.4); /* 금색 그림자 */
    transform: translateY(-8px); 
}

.ranking-entry.rank-2:hover {
    box-shadow: 0 12px 30px rgba(192, 192, 192, 0.4); /* 은색 그림자 */
    transform: translateY(-8px);
}

.ranking-entry.rank-3:hover {
    box-shadow: 0 12px 30px rgba(205, 127, 50, 0.4); /* 동색 그림자 */
    transform: translateY(-8px); 
}




/* 랭킹 배지 (1, 2, 3등 표시 아이콘) */
.rank-badge {
    position: absolute;
    top: -18px; /* 카드 위로 더 올림 */
    left: 15px;
    width: 45px;
    height: 45px;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.4rem;
    color: var(--badge-text-color);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    z-index: 2; /* 이미지 위에 확실히 보이도록 */
    border: 2px solid white; 
}

/* 등수별 배지 색상 및 아이콘  */
.rank-1 .rank-badge { background-color: var(--accent-color); }
.rank-2 .rank-badge { background-color: var(--silver-color); }
.rank-3 .rank-badge { background-color: var(--bronze-color); }


/* 레시피 이미지 */
.recipe-image-container {
    width: 100%;
    height: 200px;
    overflow: hidden; 
    border-top-left-radius: 15px; 
    border-top-right-radius: 15px; 
}
.ranking-entry img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: transform 0.4s ease;
    display: block; /* 이미지 하단 여백 제거 */
}

.ranking-entry:hover img {
    transform: scale(1.1);
}

/* 레시피 정보 (제목,조회수,좋아요) */
.recipe-info {
    padding: 25px 15px 15px 15px; /*뱃지 공간 확보*/
    text-align: center;
    flex-grow: 1;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

/* 레시피 제목*/
.recipe-title {
    font-size: 1.1rem;
    font-weight: 600;
    color: var(--text-color);
    margin-bottom: 10px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

/* 조회수/좋아요 상세 정보*/
.ranking-details {
    display: flex;
    justify-content: center;
    gap: 15px;
    margin-top: auto;
    padding-top: 10px;
    border-top: 1px solid var(--border-color);
}
.ranking-details p {
    font-size: 0.9rem;
    color: var(--subtle-text-color);
    margin: 0;
    display: flex;
    align-items: center;
}
.ranking-details i {
    margin-right: 5px;
    font-size: 1rem;
    color: var(--secondary-color);
}
.ranking-details .fa-heart { color: #E91E63; }



</style>

<div class="main-content">
    <!-- 레시피 조회수 랭킹 -->
    <div class="ranking-section">
        <div class="title">레시피 조회수 랭킹 Top 3</div>
        <div class="ranking-entries">
            <%-- c:forEach 루프 --%>
            <c:forEach var="recipe" items="${topRecipesByViews}" varStatus="status">
                <a href="${ctx}/recipeContent.do?rn=${recipe.reviewBoardRecipeId}"
                   class="ranking-entry ${status.index == 0 ? 'rank-1' : (status.index == 1 ? 'rank-2' : (status.index == 2 ? 'rank-3' : ''))}">
                    <div class="rank-badge">
                        <c:choose>
                            <c:when test="${status.index == 0}"><i class="fas fa-crown"></i></c:when>
                            <c:when test="${status.index == 1}"><i class="fas fa-medal"></i></c:when>
                            <c:when test="${status.index == 2}"><i class="fas fa-award"></i></c:when>
                        </c:choose>
                    </div>
                    <div class="recipe-image-container">
                         <img src="${recipe.recipeThumbnail}" alt="${recipe.recipeName} 이미지">
                    </div>
                    <div class="recipe-info">
                        <div class="recipe-title" title="${recipe.recipeName}">${recipe.recipeName}</div>
                        <div class="ranking-details">
                            <p> <i class="fas fa-eye"></i>${recipe.reviewBoardViews} </p>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>

    <!-- 레시피 좋아요 랭킹 -->
    <div class="ranking-section">
        <div class="title">레시피 좋아요 랭킹 Top 3</div>
        <div class="ranking-entries">
            <%-- c:forEach 루프 --%>
             <c:forEach var="recipe" items="${topRecipesByLike}" varStatus="status">
                 <a href="${ctx}/recipeContent.do?rn=${recipe.reviewBoardRecipeId}"
                   class="ranking-entry ${status.index == 0 ? 'rank-1' : (status.index == 1 ? 'rank-2' : (status.index == 2 ? 'rank-3' : ''))}">
                    <div class="rank-badge">
                         <c:choose>
                            <c:when test="${status.index == 0}"><i class="fas fa-crown"></i></c:when>
                            <c:when test="${status.index == 1}"><i class="fas fa-medal"></i></c:when>
                            <c:when test="${status.index == 2}"><i class="fas fa-award"></i></c:when>
                        </c:choose>
                    </div>
                     <div class="recipe-image-container">
                         <img src="${recipe.recipeThumbnail}" alt="${recipe.recipeName} 이미지">
                     </div>
                    <div class="recipe-info">
                        <div class="recipe-title" title="${recipe.recipeName}">${recipe.recipeName}</div>
                        <div class="ranking-details">
                            <p> <i class="fas fa-heart"></i>${recipe.reviewBoardLikes} </p>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</div>

<%@ include file="../../part/footer.jsp"%>