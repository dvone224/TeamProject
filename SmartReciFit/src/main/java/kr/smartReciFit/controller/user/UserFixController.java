package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.User;

public class UserFixController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("유저 정보 수정 진입");
		HttpSession session = request.getSession();
		
		Integer userNum=(Integer)session.getAttribute("log");
		User vo=(User)session.getAttribute("user");
		request.setAttribute("userFix", vo);
		System.out.println("userFix"+vo.toString());
		
		if (session.getAttribute("firstIn")==null) {
			System.out.println("값업음");
			session.setAttribute("firstIn", "done");
			return "userFix";
		}
		
		System.out.println("값있음");
		//User findUser= (User) session.getAttribute("findUser");
		//System.out.println(findUser.toString());
		session.setAttribute("firstIn", null);
		
		return null;
	}

}
