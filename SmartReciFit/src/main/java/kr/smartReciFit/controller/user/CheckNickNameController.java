package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.UserDAO;

public class CheckNickNameController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("닉네임 중복 검사");
		
		String nickName=request.getParameter("nickName"); // { "id" : id  }
		Integer userNum = UserDAO.getInstance().checkNickName(nickName);
		String passData=null;
		passData = userNum==null? "valid" : "notValid";
		System.out.println(passData);
		response.getWriter().print(passData);
		return null;
	}

}
