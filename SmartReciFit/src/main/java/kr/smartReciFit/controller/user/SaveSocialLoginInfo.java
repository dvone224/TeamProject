package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.SocialLoginDTO;
import kr.smartReciFit.model.user.User;
import kr.smartReciFit.model.user.UserDAO;

public class SaveSocialLoginInfo implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
/*	       // 소셜 로그인 정보 가져오기
        String platform = request.getParameter("platform");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        System.out.println(platform);
        System.out.println(nickname);
        System.out.println(email);

        // SocialDTO 객체 생성 및 플랫폼별 이메일 저장
        SocialDTO socialDTO = new SocialDTO();
        if ("kakao".equals(platform)) {
            socialDTO.setKakao(email);
        } else if ("naver".equals(platform)) {
            socialDTO.setNaver(email);
        } else if ("google".equals(platform)) {
            socialDTO.setGoogle(email);
        }

        // User 객체 생성 및 닉네임 저장
        User user = new User();
        user.setUserNickName(nickname);

        //기존회원 확인 로직 -> User table에 소셜로그인한 이메일이 있는지 확인
        User existingUser = UserDAO.getInstance().getUserByEmail(email);

        HttpSession session = request.getSession();

        if(existingUser != null) { //기존 회원이면
            //User table에 social login column 업데이트 -> kakao, naver, google
            if ("kakao".equals(platform)) {
                existingUser.setPlatformK("kakao");
                existingUser.setPlatformMailK(email);
            } else if ("naver".equals(platform)) {
                existingUser.setPlatformN("naver");
                existingUser.setPlatformMailN(email);
            } else if ("google".equals(platform)) {
                existingUser.setPlatformG("google");
                existingUser.setPlatformMailG(email);
            }

            //기존회원 정보 세션에 저장
            session.setAttribute("user", existingUser);
            session.setAttribute("log", existingUser.getUserNum());
            //session.removeAttribute("socialInfo");
        } else { //기존 회원이 아니면
            //새로운 회원으로 DB에 저장
            //SocialLoginDTO에 담아서 DB에 넘겨주기

            // 세션에 SocialDTO와 User 객체 저장
            session.setAttribute("socialInfo", socialDTO);
            session.setAttribute("nickName", nickname); //세션에 닉네임 저장

        }


        // 세션에 SocialDTO와 User 객체 저장

        return "main";

}	

}*/
		// 소셜 로그인 정보 가져오기
		String platform = request.getParameter("platform");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		System.out.println(platform);
		System.out.println(nickname);
		System.out.println(email);

		// SocialDTO 객체 생성 및 플랫폼별 이메일 저장
		SocialDTO socialDTO = new SocialDTO();
		if ("kakao".equals(platform)) {
		socialDTO.setKakao(email);
		} else if ("naver".equals(platform)) {
		socialDTO.setNaver(email);
		} else if ("google".equals(platform)) {
		socialDTO.setGoogle(email);
		}

		// User 객체 생성 및 닉네임 저장
		User user = new User();
		user.setUserNickName(nickname);

		// 세션에 SocialDTO와 User 객체 저장
		HttpSession session = request.getSession();
		session.setAttribute("socialInfo", socialDTO);
		session.setAttribute("user", user);

		return "main";

		}

		}