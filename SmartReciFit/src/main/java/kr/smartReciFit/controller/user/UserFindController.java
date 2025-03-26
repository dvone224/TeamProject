package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;

public class UserFindController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("UserFindController진입");
		String ctx=request.getContextPath();
		
		String id=request.getParameter("id-new");
		
		if(id==null) {
			return "userFind";
		}
		
		int num=Integer.parseInt(request.getParameter("num"));
		if (num==1) {
			System.out.println("아이디 찾기");
			
		}else if(num==2) {
			System.out.println("비밀번호 찾기");
			
		}else {
			System.out.println("오류");
		}
		return null;
	}

}
