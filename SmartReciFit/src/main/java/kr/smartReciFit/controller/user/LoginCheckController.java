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

	        User user = new User();
	        user.setUserId(id);
	        user.setUserPw(pw);

	        String result = UserDAO.getInstance().userLogin(user);

	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/plain");

	        if (result != null) {
	        	  HttpSession session = request.getSession();
	        	   session.setAttribute("user", id); // 세션에 사용자 아이디 저장
	        	   int userNum=(int)UserDAO.getInstance().checkId(id);
	        	   session.setAttribute("log", userNum); // 로그에 사용자 num 저장
	        	   System.out.println(session.getAttribute("user"));
	        	   System.out.println(session.getAttribute("log"));
	            response.getWriter().write("success");
	        } else {
	            response.getWriter().write("failure");
	        }

	        return null; // 응답을 직접 보냈으므로 뷰를 반환하지 않음
	    }
	}


