package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.User;
import kr.smartReciFit.model.user.UserDAO;

public class UnlinkSocialLoginController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            session.setAttribute("message", "로그인 후 소셜 계정을 연동 해제할 수 있습니다.");
            return "main";
        }

        String platform = request.getParameter("platform");

        UserDAO.getInstance().unlinkSocialAccount(user.getUserNum(), platform);
        session.setAttribute("message", "소셜 계정 연동이 해제되었습니다.");

        return "userContent"; // userContent.jsp로 이동
    }
}