<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
/* ----- 기본 및 전체 레이아웃 ----- */
body {
  font-family: sans-serif;
  /* 읽기 좋은 기본 폰트 */
  line-height: 1.6;
  /* 줄 간격 */
  margin: 0;
  padding: 20px;
  background-color: #f8f8f8;
  /* 약간의 배경색 */
  color: #333;
  /* 기본 글자색 */
}

.recipe-content {
  max-width: 800px;
  /* 콘텐츠 최대 너비 설정 */
  margin: 0 auto;
  /* 페이지 중앙 정렬 */
  background-color: #fff;
  /* 콘텐츠 영역 배경색 */
  padding: 30px;
  border-radius: 8px;
  /* 약간 둥근 모서리 */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  /* 부드러운 그림자 효과 */
}

/* ----- 제목 및 메인 이미지 ----- */
.recipe-title {
  text-align: center;
  /* 제목 중앙 정렬 */
  margin-top: 0;
  margin-bottom: 20px;
  color: #d9534f;
  /* 포인트 색상 (예: 토마토 색) */
}

/* .recipe-content 바로 아래의 첫 이미지 (메인 레시피 이미지) */
.recipe-content>img {
  display: block;
  /* 이미지를 블록 요소로 만들어 margin auto 적용 */
  max-width: 100%;
  /* 컨테이너 너비에 맞춤 */
  height: auto;
  /* 가로세로 비율 유지 */
  margin: 0 auto 30px auto;
  /* 위쪽 마진 0, 좌우 auto(중앙), 아래쪽 마진 */
  border-radius: 5px;
  /* 이미지 모서리 살짝 둥글게 */
}

/* ----- 재료 및 양념 섹션 ----- */
.ingredient-container,
.seasoning-container {
  margin-bottom: 25px;
  padding: 15px;
  border: 1px solid #eee;
  /* 섹션 구분선 */
  border-radius: 5px;
}

.ingredient-container::before,
.seasoning-container::before {
  /* 각 섹션 제목 역할 (옵션) */
  display: block;
  font-weight: bold;
  margin-bottom: 10px;
  color: #555;
}

.ingredient-container::before {
  content: "재료";
}

.seasoning-container::before {
  content: "양념";
}

.ingredient,
.seasoning {
  margin-bottom: 8px;
  /* 항목 간 간격 */
  padding-left: 15px;
  /* 약간의 들여쓰기 효과 */
  position: relative;
  /* 가상 요소 기준점 */
}

/* 각 항목 앞에 작은 점 추가 (옵션) */
.ingredient::before,
.seasoning::before {
  content: "•";
  position: absolute;
  left: 0;
  color: #d9534f;
  /* 포인트 색상 */
}

/* ----- 인분 조절 슬라이더 ----- */
.range-container {
  display: flex;
  /* 슬라이더와 출력값을 가로로 배치 */
  align-items: center;
  /* 세로 중앙 정렬 */
  justify-content: center;
  /* 가로 중앙 정렬 (선택 사항) */
  margin-bottom: 30px;
  padding: 15px;
  background-color: #f0f0f0;
  /* 배경 구분 */
  border-radius: 5px;
}

.range-container label {
  /* 만약 label 태그를 사용한다면 */
  margin-right: 10px;
}

#range {
  flex-grow: 1;
  /* 슬라이더가 남은 공간 차지 */
  max-width: 300px;
  /* 슬라이더 최대 너비 */
  margin-right: 15px;
  cursor: pointer;
}

.output {
  font-weight: bold;
  min-width: 30px;
  /* 최소 너비 확보 */
  text-align: right;
  background-color: #fff;
  padding: 5px 8px;
  border-radius: 4px;
  border: 1px solid #ccc;
}

/* ----- 변환된 레시피 표시 영역 ----- */
.recipe-convert {
  margin-bottom: 30px;
  padding: 20px;
  background-color: #eef;
  /* 변환된 내용임을 구분하는 배경색 */
  border: 1px dashed #aab;
  /* 점선 테두리 */
  border-radius: 5px;
}

.recipe-convert::before {
  content: "변환된 레시피 ("attr(data-serving-size) " 인분)";
  /* JS로 data-serving-size 업데이트 필요 */
  display: block;
  font-weight: bold;
  margin-bottom: 15px;
  color: #336;
}

.convert-ingredient,
.convert-seasoning {
  margin-bottom: 8px;
  padding-left: 15px;
  position: relative;
}

/* 변환된 항목 앞에 다른 표시 (옵션) */
.convert-ingredient::before,
.convert-seasoning::before {
  content: "›";
  position: absolute;
  left: 0;
  color: #336;
  /* 다른 포인트 색상 */
}


/* ----- 레시피 순서 (매뉴얼) ----- */
.recipe-manual-container {
  margin-top: 40px;
}

.recipe-manual {
  margin-bottom: 40px;
  /* 각 단계 사이의 간격 */
  padding-bottom: 20px;
  /* 단계 내부의 아래쪽 패딩 */
  border-bottom: 1px solid #eee;
  /* 단계 구분선 */
}

.recipe-manual:last-child {
  border-bottom: none;
  /* 마지막 단계에는 구분선 제거 */
  margin-bottom: 0;
}

.recipe-manual h1 {
  font-size: 1.2em;
  /* 단계 설명 글자 크기 */
  margin-bottom: 15px;
  color: #444;
}

.recipe-manual img {
  display: block;
  max-width: 100%;
  height: auto;
  margin: 10px auto 0 auto;
  /* 위쪽 여백, 좌우 중앙, 아래 0 */
  border-radius: 4px;
}

/* ----- 숨겨진 입력 필드 ----- */
input[type="hidden"] {
  display: none;
  /* 화면에 보이지 않도록 함 */
}
</style>
<div class="recipe-content">
	<input class="recipe-type" type="hidden" value="${recipe.recipeType}">
	<c:if test="${recipe.recipeType eq 'API' or recipe.aiRecipeBoolean}">
		<input class="meal-size" type="hidden"
			value="${recipe.recipeMealSize }" />
		<h1 class="recipe-title">${recipe.recipeName }</h1>
		<img alt="" src="${recipe.recipeThumbnail }" />
		<div class="ingredient-container">
			<c:forEach items="${fn:split(recipe.recipeIngredient, '|')}"
				var="ingredient">
				<div class="ingredient">${ingredient}</div>
			</c:forEach>
		</div>
		<div class="seasoning-container">
			<c:forEach items="${fn:split(recipe.recipeSeasoning, '|')}"
				var="seasoning">
				<div class="seasoning">${seasoning}</div>
			</c:forEach>
		</div>
		<div class="range-container">
			<input type="range" name="range" id="range" value="1" min="0" max="5"
				oninput="output.value = this.value" step="0.1">
			<output type="number" id="output" class="output">1</output>
		</div>
		<div class="recipe-convert"></div>
	</c:if>
	<div class="recipe-manual-container">
		<c:if test="${recipe.recipeType eq 'API'}">
			<c:set var="splitManual"
				value="${fn:split(recipe.recipeManual, '|')}" />
			<c:set var="splitRecipeImg"
				value="${fn:split(recipe.apiRecipeImg, '|')}" />
			<c:forEach var="i" begin="0" end="${fn:length(splitManual) - 1}">
				<div class="recipe-manual ${i }">
					<h1>${splitManual[i]}</h1>
					<img alt="" src="${splitRecipeImg[i]}">
				</div>
			</c:forEach>
		</c:if>

		<c:if test="${recipe.recipeType eq 'AI'}">
			<input type="hidden" class="ai-recipe-boolean"
				value="${recipe.aiRecipeBoolean}">
			<c:choose>
				<c:when test="${recipe.aiRecipeBoolean}">
					<c:set var="splitManual"
						value="${fn:split(recipe.recipeManual, '|')}" />
					<c:forEach var="i" begin="0" end="${fn:length(splitManual) - 1}">
						<h1 class='recipe-manual'>${splitManual[i]}</h1>
						<iframe width="560" height="315"
							src="https://www.youtube.com/embed/${videoId}?start=${timeStamp.get(i)}"
							title="동영상 제목" frameborder="0" allowfullscreen></iframe>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<h1>레시피 동영상이 아닙니다</h1>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>
</div>
<script src="${ctx}/js/recipe/recipeConverter.js"></script>
<%@ include file="../../part/footer.jsp"%>