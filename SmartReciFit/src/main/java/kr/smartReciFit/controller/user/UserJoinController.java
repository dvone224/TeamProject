package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.UserDAO;

public class UserJoinController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("UserJoinController진입");
		String ctx=request.getContextPath();
		
		String id=request.getParameter("id");
		
		if(id==null) {
			return "userJoin";
		}
		
		String pw=request.getParameter("pw");
		System.out.println(pw);
		String name=request.getParameter("name");
		System.out.println(name);
		String nickName=request.getParameter("nickName");
		System.out.println(nickName);
		String email=request.getParameter("email");
		if (email.trim().equals("")) {
			email=null;
		}
		System.out.println(email);
		String phone=request.getParameter("phone");
		if (phone.trim().equals("")) {
			phone=null;
		}
		System.out.println(phone);
		String profileImg=null;
		
		UserDAO.getInstance().UserJoin(id,pw,name,nickName,email,phone,profileImg);
		return "redirect:" + ctx + "/index.jsp";
	}

}
