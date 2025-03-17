package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;

public class LogOutController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("logout컨트롤러 진입");
		HttpSession session = request.getSession();
		session.invalidate();
		response.getWriter().print("done");
		return null;
	}

}
