<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<div class="modal login-modal">
   <div class="modal-content">
      <button class="btn-close login-close">&times;</button>
      <form id="loginForm">
         <label for="id">아이디</label> <input type="text" id="id" name="id"
            required placeholder="아이디를 입력하세요."> <label for="pw">비밀번호</label>
         <input type="password" id="pw" name="pw" required
            placeholder="비밀번호를 입력하세요.">
         <button type="submit">로그인</button>
      </form>
      
      <div>
         <a href="javascript:kakaoLogin()"><img
            src="<c:url value='/img/kakao_login_large_wide.png'/>"
            style="width: 200px"></a>
      </div>

   </div>
</div>

<div class="overlay"></div>

<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript">
    Kakao.init('c73862c0803a70accb9cd1840b0c6bcb');
    function kakaoLogin() {
        Kakao.Auth.login({
            success: function (response) {
                Kakao.API.request({
                    url: '/v2/user/me',
                    success: function (response) {
                        alert(JSON.stringify(response))
                    },
                    fail: function (error) {
                        alert(JSON.stringify(error))
                    },
                })
            },
            fail: function (error) {
                alert(JSON.stringify(error))
            },
        })
    }
</script>


