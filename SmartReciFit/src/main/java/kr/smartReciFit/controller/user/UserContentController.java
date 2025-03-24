package kr.smartReciFit.controller.user;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.User;
import kr.smartReciFit.model.user.UserDAO;
import kr.smartReciFit.model.user.UserInfo;
import kr.smartReciFit.model.user.UserInfoDAO;

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
//		System.out.println("테스트옹 vo: "+vo);
		request.setAttribute("userContent", vo);
		
		//num으로 userInfo 테이블 검색
		UserInfo voInfo=UserInfoDAO.getInstance().numGetUserInfo(num);
		request.setAttribute("userInfoContent", voInfo);
		
		//있으면 가져오고 없으면 null로 반환하기
		if (voInfo==null) {
			System.out.println("voInfo 정보없음");
			return "userContent";
		}else {
			int voMealSize=(int)voInfo.getUserMealSize();
			request.setAttribute("userInfoMealSize", voMealSize);
			String voIngredient=voInfo.getIngredient();
			String voCookingStyle=voInfo.getCookingStyle();
			String voCookingMethod=voInfo.getCookingMethod();
			String voEatTime=voInfo.getEatTime();
			System.out.println(voIngredient);
			
			//문자열을 배열로 쪼개기
			String[] voIngredientList = voIngredient.split("\\|");
			System.out.println("voIngredientList: "+Arrays.toString(voIngredientList));
			request.setAttribute("userInfoIngredient", voIngredientList);
			
			return "userContent";
			
		}
	}
}
