package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.User;
import kr.smartReciFit.model.user.UserDAO;

public class LinkSocialLoginController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

/*		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		System.out.println(user);

		if (user == null) {
			// 로그인하지 않은 경우, 메시지를 세션에 저장하고 main 페이지로 리다이렉트
			session.setAttribute("message", "로그인 후 소셜 계정을 연동할 수 있습니다.");
			return "main"; // main.do로 리다이렉트
		}

		String platform = request.getParameter("platform");
		String email = request.getParameter("email");
		System.out.println(platform);
		System.out.println(email);

		boolean linked = UserDAO.getInstance().linkSocialAccount(user.getUserNum(), platform, email);

		if (linked) {
			session.setAttribute("message", "소셜 계정이 연동되었습니다.");
		} else {
			session.setAttribute("message", "이미 연동된 계정입니다.");
		}

		return "userContent";
	}

}*/
	       HttpSession session = request.getSession();
	        User user = (User) session.getAttribute("user");

	        if (user == null) {
	            session.setAttribute("message", "로그인 후 소셜 계정을 연동할 수 있습니다.");
	            return "main";
	        }

	        String platform = request.getParameter("platform");
	        String email = request.getParameter("email");

	        boolean linked = UserDAO.getInstance().linkSocialAccount(user.getUserNum(), platform, email);

	        if (linked) {
	            session.setAttribute("message", "소셜 계정이 연동되었습니다.");
	        } else {
	            session.setAttribute("message", "이미 연동된 계정입니다.");
	        }

	        return "userContent"; // userContent.jsp로 이동
	    }
	}