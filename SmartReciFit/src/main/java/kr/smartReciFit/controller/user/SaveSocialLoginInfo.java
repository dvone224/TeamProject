package kr.smartReciFit.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

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
	        System.out.println("플랫폼: " + platform);
	        System.out.println("닉네임: " + nickname);
	        System.out.println("이메일: " + email);
	        System.out.println("=============================");

	        HttpSession session = request.getSession();
	        UserDAO userDAO = UserDAO.getInstance();

	        // ✅ social 테이블에서 이메일 확인 (기존 소셜 계정인지 체크)
	        SocialDTO existingSocial = userDAO.getSocialByEmail(email);
	        System.out.println(existingSocial); // NULL이면 신규, NULL이 아니면 기존
	        response.setContentType("text/plain");
	        PrintWriter out = response.getWriter();
	        
	        int userNum;
	        if (existingSocial != null) { // 소셜로그인 한번이라도 한 자면,
	            userNum = existingSocial.getUserNum(); // 기존 유저면 그대로 로그인 진행
	        } else { // 소셜로그인 안한 유저
	            // ✅ 닉네임 중복 검사 (모든 신규 유저에 대해 실행)
	            if (userDAO.checkNickName(nickname) != null) {
	                request.setAttribute("nicknameExists", true);
	                request.setAttribute("platform", platform);
	                request.setAttribute("email", email);
	                request.setAttribute("nickname", nickname);
	                session.setAttribute("platform", platform);
	                session.setAttribute("email", email);
	                System.out.println("닉네임 중복 if문");
	                out.print("닉네임 중복");
	                out.close();
	                return null; // 닉네임 입력 폼으로 이동
	                
	            }
	            // 닉네임이 중복되지 않으면 새로운 유저 생성
	            User newUser = new User();
	            newUser.setUserNickName(nickname);
	            userNum = userDAO.insertUserTableBySocial(newUser);
	            System.out.println("userNum : "+userNum);
	            newUser.setUserNum(userNum);

	            // 🔹 신규 userNum을 social 테이블에도 저장
	            SocialDTO socialDTO = new SocialDTO();
	            socialDTO.setUserNum(userNum);
	            if ("kakao".equals(platform))
	                socialDTO.setKakao(email);
	            else if ("naver".equals(platform))
	                socialDTO.setNaver(email);
	            else if ("google".equals(platform))
	                socialDTO.setGoogle(email);
	            
	            userDAO.InsertSocialInfo(socialDTO);
	            
	        }
            System.out.println("세션 저장하는 곳");
	        // ✅ 세션 저장 (로그인 처리)
	        User loggedInUser = userDAO.getUserByNum(userNum);
	        System.out.println(loggedInUser);
	        session.setAttribute("user", loggedInUser);
	        session.setAttribute("log", userNum);
	        session.setAttribute("nickName", loggedInUser.getUserNickName());

	        System.out.println("세션 저장 완료: " + session.getAttribute("log") + " / " + session.getAttribute("nickName"));
	        return "main";
	    }
	}