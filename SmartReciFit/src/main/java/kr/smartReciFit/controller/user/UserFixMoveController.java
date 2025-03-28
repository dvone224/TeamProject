package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.User;
import kr.smartReciFit.model.user.UserDAO;

public class UserFixMoveController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("UserFixMoveController 진입");
		String ctx=request.getContextPath();
		
		int num =-1;
		String getNum=request.getParameter("num");
		System.out.println("UserFixMoveController의 num"+getNum);
//		if(getNum==null){
//			return "redirect:"+ctx+"/main.do";
//		}else{
			num = Integer.parseInt(getNum);
//		}
		User vo=UserDAO.getInstance().numGetUser(num);
//		System.out.println("테스트옹 vo: "+vo);
		request.setAttribute("user", vo);
    	return "userFix";
	}

}
