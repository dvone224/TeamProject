package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.User;
import kr.smartReciFit.model.user.UserDAO;

public class CheckEmailController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("이메일 검사");
  	  HttpSession session = request.getSession();
		String email=request.getParameter("str"); // { "id" : id  }
		Integer userNum = UserDAO.getInstance().checkEmail(email);
		
		if (userNum!=null) {
			User user = UserDAO.getInstance().numGetUser(userNum);
			session.setAttribute("findUser", user); // User 객체를 세션에 저장
		}
		
		String passData=userNum==null?"notValid":"valid";
		System.out.println(passData);
		response.getWriter().print(passData);
		//여기서 UserNum이 null이 아니면 시즌에 저장해볼까...그럼 불러와지나??
		
		return null;
	}

}
