package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.UserDAO;

public class CheckEmailDoubleController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("이메일 중복 검사");
		
		String email=request.getParameter("str"); 
		System.out.println("email:"+email);
		Integer userNum = UserDAO.getInstance().checkEmail(email);
		String passData=null;
		
		System.out.println("userNum:"+userNum);
		passData = userNum==null? "valid" : "notValid";
		System.out.println(passData);
		response.getWriter().print(passData);
		return null;
		
	}

}
