package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.SocialDTO;
import kr.smartReciFit.model.user.SocialLoginDTO;
import kr.smartReciFit.model.user.User;

public class LoginSocialLogin implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        // 소셜 로그인 정보 가져오기
        String platform = request.getParameter("platform");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        
        // SocialDTO에 userNum, 각 맞는 플랫폼에 맞는 이메일 넣는다
        SocialDTO socialDTO = new SocialDTO();
        if(platform == "kakao") {
        	socialDTO.setKakao(email);
        }else if(platform == "naver") {
        	socialDTO.setNaver(email);
        }else {
        	socialDTO.setGoogle(email);
        }
        
        // User에 userNum, nickName 넣기 전에 회원가입 되있던 이메일 확인
        User user = new User();
        user.setUserNickName(nickname);
        
        
        
        
        SocialLoginDTO socialLoginDTO = new SocialLoginDTO();
        socialLoginDTO.setPlatform(platform);
        socialLoginDTO.setNickname(nickname);
        socialLoginDTO.setEmail(email);

        // 세션에 SocialLoginDTO 객체 저장
        HttpSession session = request.getSession();
        session.setAttribute("socialLogin", socialLoginDTO);

        
        return "main";
    }
}	


