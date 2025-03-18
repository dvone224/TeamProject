package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.UserDAO;

public class CheckIdController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("아이디 중복 검사");
		
		String id=request.getParameter("id"); // { "id" : id  }
		Integer userNum = UserDAO.getInstance().checkId(id);
		String passData=null;
		passData = userNum==null? "valid" : "notValid";
		System.out.println(passData);
		response.getWriter().print(passData);
		return null;
	}

}
