//function formatPhoneNumber(phoneNumber) {
 // if (!phoneNumber) return ''; // 전화번호가 없는 경우 빈 문자열 반환

  //const parts = phoneNumber.split('-');
 // if (parts.length !== 3) return phoneNumber; // 형식이 맞지 않으면 원래 번호 반환

//  return `${parts[0]}-0***-0***`;
//}

// 예시: userContent.user_phone을 010-0***-0*** 형식으로 변환
//const formattedPhoneNumber = formatPhoneNumber(userContent.user_phone);

// HTML 테이블에 적용
document.querySelector('#phoneNumberCell').textContent = formattedPhoneNumber;

// ------------------------------------------------------- 연동 함수
function linkSocial(platform) {
    // 소셜 계정 연동 로직
    alert(platform + " 계정 연동 요청");
}
function unlinkSocial(platform) {
    // 소셜 계정 연동 해제 로직
    alert(platform + " 계정 연동 해제 요청");
}


document.addEventListener('DOMContentLoaded', function() {
       loadSocialStatus();
   });

   function loadSocialStatus() {
       fetch('${ctx}/getSocialStatus.do')
           .then(response => response.json())
           .then(data => {
               displaySocialStatus('naver', data.naver);
               displaySocialStatus('kakao', data.kakao);
               displaySocialStatus('google', data.google);
               displaySocialStatus('apple', data.apple);
           })
           .catch(error => console.error('Error:', error));
   }

   function displaySocialStatus(platform, isLinked) {
       const statusElement = document.getElementById(platform + 'Status');
       if (isLinked) {
           statusElement.innerHTML = '연동 중입니다. <button class="link-button" onclick="unlinkSocial(\'' + platform + '\')">연동 해지</button>';
       } else {
           statusElement.innerHTML = '<button class="link-button" onclick="linkSocial(\'' + platform + '\')">계정 연동</button>';
       }
   }

   function linkSocial(platform) {
       fetch('${ctx}/linkSocial.do', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/x-www-form-urlencoded',
           },
           body: 'platform=' + platform + '&email=${sessionScope.user.userEmail}'
       })
           .then(response => response.text())
           .then(message => {
               alert(message);
               loadSocialStatus();
           })
           .catch(error => console.error('Error:', error));
   }

   function unlinkSocial(platform) {
       fetch('${ctx}/unlinkSocial.do', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/x-www-form-urlencoded',
           },
           body: 'platform=' + platform
       })
           .then(response => response.text())
           .then(message => {
               alert(message);
               loadSocialStatus();
           })
           .catch(error => console.error('Error:', error));
   }
//document.querySelector('#phoneNumberCell').textContent = formattedPhoneNumber;
