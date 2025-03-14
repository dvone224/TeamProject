package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.User;
import kr.smartReciFit.model.user.UserDAO;

public class LoginCheckController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		UserDAO dao = UserDAO.getInstance();
		User vo = new User();
		vo.setUserId(id);
		vo.setUserPw(pw);
		
		String userId = dao.userLogin(vo);
		System.out.println("userId = "+userId);
		
		HttpSession session = request.getSession();
		if(userId!=null) {
			session.setAttribute("userId", id);
			if(userId.equals("admin")) {
				session.setAttribute("admin", id);
			}
			response.getWriter().print(userId);
		}else {
			response.getWriter().print("null");
			
		}
		return null;
	}

}
