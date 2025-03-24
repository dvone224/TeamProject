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
			System.out.println("LoginCheckController 진입");
			String id = request.getParameter("id");
	        String pw = request.getParameter("pw");

	        User user = new User();
	        user.setUserId(id);
	        user.setUserPw(pw);

	        String result = UserDAO.getInstance().userLogin(user);
	        System.out.println("result = "+result);

	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/plain");

	        if (result != null) {
	        	  HttpSession session = request.getSession();
	        	  String nickName = UserDAO.getInstance().getNickName(id);
	        	   int userNum=(int)UserDAO.getInstance().checkId(id);
	        	   session.setAttribute("user", user); // User 객체를 세션에 저장
	        	   session.setAttribute("log", userNum); // 로그에 사용자 num 저장
	        	   session.setAttribute("nickName", nickName);
	        	   System.out.println(session.getAttribute("nickName"));
	        	   System.out.println(session.getAttribute("log"));
	            response.getWriter().write("success");
	        } else {
	            response.getWriter().write("failure");
	        }
	        return null; // 응답을 직접 보냈으므로 뷰를 반환하지 않음
	    }
	}


