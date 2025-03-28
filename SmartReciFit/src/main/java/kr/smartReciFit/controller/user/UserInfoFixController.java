package kr.smartReciFit.controller.user;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;

public class UserInfoFixController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("유저 인포 수정 컨트롤러 진입");
		HttpSession session = request.getSession();
		String ctx=request.getContextPath();
		
		int userNum=Integer.parseInt(request.getParameter("num"));
		double userMealSize=Double.parseDouble(request.getParameter("userMealSize"));
		System.out.println("userNum="+userNum);
		System.out.println("userMealSize="+userMealSize );
		String[]list=request.getParameterValues("list");
        System.out.println("list: " + Arrays.toString(list));
		
		
		if (session.getAttribute("firstInUserInfoFix")==null||(Boolean)session.getAttribute("firstInUserInfoFix")==false) {
			System.out.println("첫 진입");
			session.setAttribute("firstInUserInfoFix", true);
			return "userInfoFix";
		}
		
		System.out.println("두번째 진입, 리셋");
		session.setAttribute("firstInUserInfoFix", false);
		
		
		
		
		
		
		return null;
	}

}
