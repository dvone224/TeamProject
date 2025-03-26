document.getElementById('recipeThumbnail').addEventListener('change', function() {
  const filename = this.files[0] ? this.files[0].name : '선택된 파일 없음';
  document.getElementById('thumbnail-filename').textContent = filename;
});

function addIngredientInput() {
  const list = document.getElementById('ingredient-list');
  const newItem = document.createElement('div');
  newItem.className = 'ingredient-item';
  newItem.innerHTML = `
    <input type="text" name="ingredients[]" placeholder="예) 대파 1/2대">
    <button type="button" class="remove-btn" onclick="removeInput(this)">-</button>
  `;
  list.appendChild(newItem);
}


// 재료/양념 입력 필드 제거 (공통 사용)
function removeInput(button) {
  const item = button.parentNode;
  if (item.parentNode.children.length > 1) {
    item.remove();
  } else {
    alert('최소 1개의 입력 필드는 필요합니다.');
  }
}


// 요리 순서 입력 필드 추가
function addStepInput() {
  const list = document.getElementById('steps-list');
  const stepCount = list.children.length + 1;
  const newItem = document.createElement('div');
  newItem.className = 'step-item';

  // 고유 ID 생성 (파일 입력과 라벨 연결용)
  const fileInputId = `step_img_${stepCount}`;

  newItem.innerHTML = `
    <span class="step-number">Step ${stepCount}.</span>
    <div class="step-content">
      <textarea name="steps[]" placeholder="다음 조리 과정을 작성해주세요." required></textarea>
      <div class="step-image-upload">
        <input type="file" id="${fileInputId}" name="steps_img" accept="image/*" style="display: none;">
        <label for="${fileInputId}" class="file-label">사진 추가</label>
        <span class="step-filename">선택된 파일 없음</span>
      </div>
    </div>
    <button type="button" class="remove-btn" onclick="removeStep(this)">-</button>
  `;
  list.appendChild(newItem);
  updateStepNumbers(); // 순서 번호 업데이트
}

// 요리 순서 입력 필드 제거
function removeStep(button) {
  const item = button.parentNode;
  // 최소 1개는 남겨둠
  if (item.parentNode.children.length > 1) {
    item.remove();
    updateStepNumbers(); // 순서 번호 업데이트
  } else {
    alert('최소 1개의 조리 순서는 필요합니다.');
  }
}

// 요리 순서 번호 업데이트
function updateStepNumbers() {
  const list = document.getElementById('steps-list');
  const items = list.children;
  for (let i = 0; i < items.length; i++) {
    const stepNum = i + 1;
    // Step 번호 업데이트
    items[i].querySelector('.step-number').textContent = `Step ${stepNum}.`;

    // 파일 입력 관련 id 및 name, for 속성 업데이트
    const fileInput = items[i].querySelector('input[type="file"]');
    const fileLabel = items[i].querySelector('.file-label');
    const fileInputId = `step_img_${stepNum}`;

    if (fileInput && fileLabel) {
      fileInput.id = fileInputId;
      fileInput.name = `steps_img`; // name 속성도 업데이트
      fileLabel.setAttribute('for', fileInputId);
    }
  }
}
const stepsList = document.getElementById('steps-list');
if (stepsList) {
  // steps-list 영역 내에서 발생하는 change 이벤트를 감지
  stepsList.addEventListener('change', function(event) {
    // 이벤트가 발생한 요소가 파일 입력(input[type="file"])인지 확인
    if (event.target.matches('input[type="file"]')) {
      const fileInput = event.target;
      // 해당 파일 입력 필드와 가장 가까운 .step-filename 요소를 찾음
      const filenameSpan = fileInput.closest('.step-image-upload').querySelector('.step-filename');
      if (filenameSpan) {
        const filename = fileInput.files[0] ? fileInput.files[0].name : '선택된 파일 없음';
        filenameSpan.textContent = filename;
      }
    }
  });
}
// 대표 이미지 파일명 표시 로직 (기존 로직 유지)
const recipeThumbnailInput = document.getElementById('recipeThumbnail');
if (recipeThumbnailInput) {
    recipeThumbnailInput.addEventListener('change', function() {
      const filename = this.files[0] ? this.files[0].name : '선택된 파일 없음';
      document.getElementById('thumbnail-filename').textContent = filename;
    });
}


const recipeForm = document.querySelector('form[action$="/saveRecipe.do"]');

if (recipeForm) { // 폼이 존재할 경우에만 실행
  recipeForm.addEventListener('submit', function(event) {
    const cookingMethodChecked = document.querySelector('input[name="cooking-method"]:checked');
    if (!cookingMethodChecked) { 
      alert('요리 방법을 1개 이상 선택해주세요.');
      event.preventDefault(); 
     
      const firstCookingMethodCheckbox = document.querySelector('input[name="cooking-method"]');
      if (firstCookingMethodCheckbox) {
          firstCookingMethodCheckbox.focus();
      }
      return; 
    }

    const ingredientChecked = document.querySelector('input[name="ingredient"]:checked');
    if (!ingredientChecked) { 
      alert('주재료를 1개 이상 선택해주세요.');
      event.preventDefault(); 
      const firstIngredientCheckbox = document.querySelector('input[name="ingredient"]');
      if (firstIngredientCheckbox) {
          firstIngredientCheckbox.focus();
      }
      return; 
    }

  });
}  else {
    console.warn("레시피 저장 폼을 찾을 수 없습니다. (action='/saveRecipe.do')");
  }
