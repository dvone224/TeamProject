package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.User;
import kr.smartReciFit.model.user.UserDAO;

public class UserResetPwController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("비밀번호 재설정하기 진입");
		String ctx=request.getContextPath();
		String pw=request.getParameter("pw-new");
		
		if (pw==null) {
			return "userResetPw";
		}
		
		HttpSession session = request.getSession();
		User findUser= (User) session.getAttribute("findUser");
		System.out.println(findUser.toString());
		//유저 넘을 가지고 바꾸는거 해야지
		int userNum=findUser.getUserNum();
		int pass=UserDAO.getInstance().updatePwByUserNum(userNum, pw);
		if (pass>0) {
			System.out.println("바꾸기 성공");
			return "redirect:" + ctx + "/index.jsp";
		}else {
			System.out.println("바꾸기 실패");
			return "userResetPw";
		}
	}
}