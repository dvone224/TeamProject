package kr.smartReciFit.controller.user;

import java.io.IOException;
import java.util.Map;
<<<<<<< Updated upstream

import org.json.simple.JSONObject;
=======
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		System.out.println(user);

		String platform = request.getParameter("platform");
		String email = request.getParameter("email");
		System.out.println(platform);
		System.out.println(email);

		boolean linked = UserDAO.getInstance().linkSocialAccount(user.getUserNum(), platform, email);

<<<<<<< Updated upstream
		if (linked) {
		    Map<String, Boolean> linkedAccounts = UserDAO.getInstance().getLinkedSocialAccounts(user.getUserNum());
		    session.setAttribute("linkedAccounts", linkedAccounts);
		    System.out.println("연동 성공");
		}else {
			session.setAttribute("message", "이미 연동된 계정입니다.");
		}
=======
	    if (linked) {
	        // ✅ 성공 메시지 설정
	        session.setAttribute("message", platform + "와 연동되었습니다.");
	        Map<String, Boolean> linkedAccounts = UserDAO.getInstance().getLinkedSocialAccounts(user.getUserNum());
	        session.setAttribute("linkedAccounts", linkedAccounts);
	    } else {
	        // ✅ 실패 메시지 설정
	        session.setAttribute("message", "이미 연동된 계정입니다.");
	    }
>>>>>>> Stashed changes

		return "userContent";
	}

}