document.addEventListener('DOMContentLoaded', function() {
    const popup = document.getElementById('image-preview-popup');
    const previewImage = document.getElementById('preview-image');
    const titleLinks = document.querySelectorAll('.review-title-link');

    titleLinks.forEach(link => {
        link.addEventListener('mouseover', function(event) {
            const imgSrc = this.dataset.imgSrc; // data-img-src 값 가져오기

            if (imgSrc) { // 이미지 있을 경우에만
                previewImage.src = `${ctx}/img/` + imgSrc; 
                popup.style.display = 'block'; // 팝업 보이기

                // 팝업 위치 설정 (마우스 포인터 기준 약간 오른쪽 아래)
                const x = event.pageX + 10;
                const y = event.pageY + 10;
                popup.style.left = x + 'px';
                popup.style.top = y + 'px';
            }
        });

        link.addEventListener('mouseout', function() {
            popup.style.display = 'none'; // 마우스 벗어나면 팝업 숨기기
            previewImage.src = ''; 
        });

       
    });
});