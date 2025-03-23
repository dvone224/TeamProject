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
import kr.smartReciFit.model.user.UserDAO;

public class SaveSocialLoginInfo implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("SaveSocialLoginInfo 진입");
		// 소셜 로그인 정보 가져오기
		String platform = request.getParameter("platform");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		System.out.println("======소셜로그인 정보 확인구간=====");
		System.out.println("소셜로그인 정보 확인구간");
	    System.out.println("플랫폼: " + platform);
	    System.out.println("닉네임: " + nickname);
	    System.out.println("이메일: " + email);
	    System.out.println("=============================");
	    
	    HttpSession session = request.getSession();
	    UserDAO userDAO = UserDAO.getInstance();

	    // ✅ social 테이블에서 이메일 확인 (기존 소셜 계정인지 체크)
	    SocialDTO existingSocial = userDAO.getSocialByEmail(email);
	    
	    int userNum;
	    if (existingSocial != null) {
	    	 userNum = existingSocial.getUserNum();
	    } else {
	    	 User newUser = new User();
	         newUser.setUserNickName(nickname);
	         userNum = userDAO.insertUserTableBySocial(newUser);
	         newUser.setUserNum(userNum);
	         
	      // 🔹 신규 userNum을 social 테이블에도 저장
	         SocialDTO socialDTO = new SocialDTO();
	         socialDTO.setUserNum(userNum);
	         if ("kakao".equals(platform)) socialDTO.setKakao(email);
	         if ("naver".equals(platform)) socialDTO.setNaver(email);
	         if ("google".equals(platform)) socialDTO.setGoogle(email);
	         
	         userDAO.InsertSocialInfo(socialDTO);
	    }

	    // ✅ 세션 저장 (로그인 처리)
	    session.setAttribute("user", userDAO.getUserById(userNum));
	    session.setAttribute("log", userNum);
	    session.setAttribute("nickName", nickname);

	    System.out.println("세션 저장 완료: " + session.getAttribute("log") + " / " + session.getAttribute("nickName"));

	    return "main";

		}

	}