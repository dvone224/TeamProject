package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.User;
import kr.smartReciFit.model.user.UserDAO;

public class UserContentController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//user 갖고 와서...
		System.out.println(" 마이페이지 진입");
		
		String ctx=request.getContextPath();
		
		int num =-1;
		String getNum=request.getParameter("num");
		System.out.println("UserContentController의 num"+getNum);
		if(getNum==null){
			return "redirect:"+ctx+"/main.do";
		}else{
			num = Integer.parseInt(getNum);
		}
		User vo=UserDAO.getInstance().numGetUser(num);
		System.out.println("테스트옹 vo: "+vo);
		request.setAttribute("userContent", vo);
		
		return "userContent";
	}

}
