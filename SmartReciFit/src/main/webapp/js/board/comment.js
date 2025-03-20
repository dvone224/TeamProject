function showEdit(commentNum, reviewBoardNum) {
	console.log("showEdit 호출됨", commentNum, reviewBoardNum);

	const commentDiv = document.getElementById(`comment-div-${commentNum}`);
	const originalContent = commentDiv.innerText;
	const editButton = document.getElementById(`comment-btn-${commentNum}`);
	
	editButton.disabled = true; //수정 버튼 비활성

	commentDiv.innerHTML = `<input type="text" id="edit-input-${commentNum}" value="${originalContent}" />
                             <button id='edit-btn-${commentNum}' onclick="updateComment(${commentNum}, ${reviewBoardNum})">수정 완료</button>
                             <button onclick="cancelEdit(${commentNum}, '${originalContent}')">취소</button>`;
							 
							 
	const update = document.querySelector(`#edit-input-${commentNum}`);
	const testbtn = document.querySelector(`#edit-btn-${commentNum}`);
	testbtn.disabled = true;
	
	update.addEventListener('input',function() {
		let updateContent = this.value.trim();
		if(originalContent.trim() === updateContent || !updateContent){
			testbtn.disabled = true;
		}else{
			testbtn.disabled = false;
		}
	})
	
}

function cancelEdit(commentNum, originalContent) {
	const commentDiv = document.getElementById(`comment-div-${commentNum}`);
	commentDiv.innerHTML = originalContent;
	const editButton = document.getElementById(`comment-btn-${commentNum}`);
	editButton.disabled = false;
}

function updateComment(commentNum, reviewBoardNum) { 
      const commentDiv = document.getElementById(`comment-div-${commentNum}`);
      const editInput = document.getElementById(`edit-input-${commentNum}`);
	  const editButton = document.getElementById(`comment-btn-${commentNum}`);
      const newContent = editInput.value;
		
      $.ajax({
         type: "POST",
         url: `${ctx}/commentUpdate.do`,
         data: {
            commentNum: commentNum,
            commentContent: newContent,
            reviewBoardNum: reviewBoardNum // boardNum 값 전달
         },
         success: function(response) {
            alert("댓글이 수정되었습니다.");
			commentDiv.innerHTML = newContent;
			editButton.disabled = false;
            /*window.location.href = `${ctx}/reviewDetail.do?reviewBoardNum=` + reviewBoardNum;*/
         },
         error: function(xhr, status, error) {
            alert("댓글 수정에 실패했습니다.");
         }
      });
   }
   function toggleLike(reviewBoardNum) {
       $.ajax({
           type: "POST",
           url: `${ctx}/like.do`, 
           data: {
               reviewBoardNum: reviewBoardNum
           },
           success: function (response) {
               if (response.success) {
                   let likeCount = response.likeCount; 
                   let liked = response.liked; 

                   $("#like-count").text(likeCount); 
                   let likeButton = $(".like-button");
                   if (liked) {
                       likeButton.html("🤍"); 
                   } else {
                       likeButton.html("❤️"); 
                   }
               } else {
                   alert("좋아요 처리 실패: " + response.message);
               }
           },
           error: function (xhr, status, error) {
               alert("좋아요 요청 실패: " + error);
           }
       });
   }
   
