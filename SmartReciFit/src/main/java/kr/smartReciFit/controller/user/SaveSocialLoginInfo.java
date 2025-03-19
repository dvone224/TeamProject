package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.SocialLoginDTO;

public class SaveSocialLoginInfo implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        // 소셜 로그인 정보 가져오기
        String platform = request.getParameter("platform");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");

        // SocialLoginDTO 객체 생성 및 값 설정
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


