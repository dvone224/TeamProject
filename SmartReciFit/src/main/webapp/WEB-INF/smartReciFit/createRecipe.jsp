<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/header.jsp"%>
<style>
/* 폼 스타일링 */
.create-recipe-container {
	max-width: 800px;
	margin: 40px auto;
	padding: 30px;
	border: 1px solid #ddd;
	border-radius: 8px;
	background-color: #fff;
}

.create-recipe-container h2 {
	text-align: center;
	margin-bottom: 30px;
	color: #333;
	font-size: 1.8rem;
}

.form-group {
	margin-bottom: 25px;
}

.form-group label {
	display: block;
	font-weight: 600;
	margin-bottom: 8px;
	color: #555;
}

/* 일반 입력 필드, 텍스트 영역, 셀렉트 박스 스타일 */
.form-group input[type="text"],
.form-group textarea,
.form-group select {
	width: 100%;
	padding: 10px 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 1rem;
	box-sizing: border-box; 
}

.form-group textarea {
	min-height: 100px;
	resize: vertical;
}

.form-group input[type="file"] {
	display: none; 
}

/* 파일 선택 버튼 스타일 */
.form-group .file-label {
	display: inline-block;
	padding: 8px 15px;
	background-color: #eee;
	border: 1px solid #ccc;
	border-radius: 4px;
	cursor: pointer;
	font-size: 0.9rem;
	margin-top: 5px; 
}

.form-group .file-label:hover {
	background-color: #ddd;
}

/* 파일명 표시 영역 스타일 */
#thumbnail-filename, .step-filename {
    margin-left: 10px;
    font-size: 0.9rem;
    color: #666;
}


/* 카테고리 선택 그룹 */
.category-group {
	display: flex;
	flex-wrap: wrap;
	gap: 15px;
	align-items: center;
	padding: 15px; 
    border: 1px solid #eee; 
    border-radius: 4px;
}

.category-group > label { 
	margin-bottom: 0;
	width: 80px;
	flex-shrink: 0;
}

.category-group select {
	flex-grow: 1;
	min-width: 150px;
}

/* 체크박스 그룹 스타일 */
.checkbox-group {
    width: 100%; 
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    gap: 10px 15px; /* 세로, 가로 간격 */
    margin-top: 10px;
}
.checkbox-group > label { 
    width: auto; 
    margin-right: 5px;
    font-weight: normal;
    color: #777;
}
.checkbox-group label { 
    margin-right: 10px;
    font-weight: normal;
    cursor: pointer;
    display: inline-flex; /* 체크박스와 텍스트 정렬 */
    align-items: center;
}
.checkbox-group input[type="checkbox"] {
    margin-right: 5px;
}

.category-group label[for^="cooking-method-"],
.category-group label[for^="ingredient-"] {
    font-weight: normal; 
    color: #555;
    cursor: pointer; 
}


.ingredient-item {
	display: flex;
	gap: 10px;
	margin-bottom: 10px;
	align-items: center;
}

.ingredient-item input {
	flex-grow: 1;
}



.step-item {
    display: flex;
    gap: 15px;
    margin-bottom: 20px;
    align-items: flex-start;
    border: 1px solid #eee;
    padding: 15px;
    border-radius: 5px;
    background-color: #fcfcfc; 
}

.step-number {
    font-weight: bold;
    color: #2E8B57;
    margin-top: 10px;
    flex-shrink: 0;
    width: 50px; 
}

.step-content {
    flex-grow: 1;
    display: flex;
    gap: 10px; 
    flex-direction: column;
}

/* 요리 순서 텍스트 영역 높이 조절 */
.step-content textarea {
    width: 100%;
    min-height: 100px; 
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 1rem;
    resize: vertical;
    box-sizing: border-box;
}

.step-image-upload {
    display: flex;
    align-items: center;
    gap: 10px;
}
.step-image-upload .file-label {
    margin-top: 0;
}
.step-item .remove-btn {
   align-self: center;
   margin-left: auto;
   flex-shrink: 0;
}


/* 추가/삭제 버튼 공통 스타일 */
.add-btn, .remove-btn {
	padding: 5px 10px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 0.9rem;
    margin-left: 5px; 
}


.add-btn {
	background-color: #2E8B57;
	color: white;
}

.remove-btn {
	background-color: #dc3545;
	color: white;
}


.add-btn:hover { background-color: #256d45; }
.remove-btn:hover { background-color: #c82333; }



.submit-btn {
	display: block;
	width: 100%;
	padding: 12px;
	background-color: #3CB371;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 1.1rem;
	font-weight: 600;
	cursor: pointer;
	transition: background-color 0.2s ease;
	margin-top: 30px;
}

.submit-btn:hover {
	background-color: #2E8B57;
}


</style>

<div class="create-recipe-container">
	<h2>나만의 레시피 등록</h2>

	<form action="${ctx}/saveRecipe.do" method="post"
		enctype="multipart/form-data">

		<input type="hidden" name="userNum" value="${sessionScope.log}">

		<div class="form-group">
			<label for="recipeName">레시피 제목</label> <input type="text"
				id="recipeName" name="recipeName" placeholder="예) 초간단 김치볶음밥"
				required>
		</div>

		<div class="form-group">
			<label>대표 이미지 (썸네일)</label> <input type="file" id="recipeThumbnail"
				name="recipeThumbnail" accept="image/*" style="display: none;" required>
			<label for="recipeThumbnail" class="file-label">파일 선택</label> <span
				id="thumbnail-filename">선택된 파일 없음</span>
		</div>

		<div class="form-group category-group" required>
			<label>카테고리</label> <select name="tagEatTime">
				<option value="">종류별</option>
				<option value="아침">아침</option>
				<option value="점심">점심</option>
				<option value="저녁">저녁</option>
				<option value="야식">야식</option>
				<option value="간식">간식</option>
				<option value="반찬">반찬</option>
				<option value="기타">기타</option>
			</select> <select name="tagCookingStyle" required>
				<option value="">상황별</option>
				<option value="양식">양식</option>
				<option value="한식">한식</option>
				<option value="중식">중식</option>
				<option value="일식">일식</option>
				<option value="아시안식">아시안식</option>
				<option value="기타">기타</option>
			</select>
			<c:forEach var="cookingMethod" items="${tagCookingMethod}">
				<input type="checkbox" class="cooking-method" id="cooking-method-${cookingMethod}"
					name="cooking-method" value="${cookingMethod}">
				<label for="cooking-method-${cookingMethod}">${cookingMethod}</label>
			</c:forEach>

			<c:forEach var="ingredient" items="${tagIngredient}">
				<input type="checkbox" class="ingredient" id="ingredient-${ingredient}"
					name="ingredient" value="${ingredient}">
				<label for="ingredient-${ingredient}">${ingredient}</label>	
			</c:forEach>

		</div>

		<div class="form-group">
			<label>재료</label>
			<div id="ingredient-list">
				<div class="ingredient-item">
					<input type="text" name="ingredients[]" placeholder="예) 김치 1/4포기" required>
					<button type="button" class="remove-btn"
						onclick="removeInput(this)">-</button>
				</div>
			</div>
			<button type="button" class="add-btn" onclick="addIngredientInput()">+</button>
		</div>


		<%-- 요리 순서 섹션 수정 --%>
		<div class="form-group">
			<label>요리 순서</label>
			<div id="steps-list">
				<div class="step-item">
					<span class="step-number">Step 1.</span>
					<div class="step-content">
						<textarea name="steps[]" placeholder="조리 과정을 순서대로 작성해주세요." required></textarea>
						<div class="step-image-upload">
							<input type="file" id="step_img_1" name="steps_img" accept="image/*" style="display: none;">
							<label for="step_img_1" class="file-label">사진 추가</label>
							<span class="step-filename">선택된 파일 없음</span>
						</div>
					</div>
					<button type="button" class="remove-btn" onclick="removeStep(this)">-</button>
				</div>
			</div>
			<button type="button" class="add-btn" onclick="addStepInput()">+</button>
		</div>

		<%-- 등록 버튼 --%>
		<button type="submit" class="submit-btn">레시피 등록하기</button>

	</form>
</div>

<script src="${ctx}/js/recipe/createRecipes.js">
	
</script>
<%@ include file="../../part/footer.jsp"%>