package kr.smartReciFit.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.UserDAO;
import kr.smartReciFit.model.user.UserInfoDAO;

public class UserDelController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("UserDelController진입");
		String ctx=request.getContextPath();
		
		String check = request.getParameter("check");
		System.out.println("check:"+check);
		if(check==null) {
			System.out.println("값없음");
			return "userDel";
		}
		
		if (check.equals("false")) {
			System.out.println("오류");
		}else {
			System.out.println("값들어옴");
			HttpSession session = request.getSession();
			int userNum=(int)session.getAttribute("log");
			System.out.println("현재 로그인 상태: "+userNum);
			//회원 삭제 로직 진행
			//user테이블 삭제
			UserDAO.getInstance().delUserbyUserNum(userNum);
			//social테이블 삭제
			UserDAO.getInstance().delSocialbyUserNum(userNum);
			//userInfo테이블 삭제
			UserInfoDAO.getInstance().delUserInfobyUserNum(userNum);
			System.out.println("삭제완료");
			session.invalidate();
			//return "redirect:" + ctx + "/index.do";
		}
		return null;
	}
}
