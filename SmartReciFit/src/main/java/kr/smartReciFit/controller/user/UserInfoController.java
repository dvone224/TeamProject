package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.UserDAO;
import kr.smartReciFit.model.user.UserInfoDAO;

public class UserInfoController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//user 갖고 와서...
		System.out.println("유저 인포 진입");
		
		String ctx=request.getContextPath();
        String mealSize= request.getParameter("mealSize");
        String[]itemMain=request.getParameterValues("itemMain");
        String[]itemMeat=request.getParameterValues("itemMeat");
        String[]itemVegitable=request.getParameterValues("itemVegitable");
        String[]itemContry=request.getParameterValues("itemContry");
        String[]itemCook=request.getParameterValues("itemCook");
        String[]itemTime=request.getParameterValues("itemTime");
        
        System.out.println(mealSize);
        System.out.println(itemMain);
        System.out.println(itemMeat);
        System.out.println(itemVegitable);
        System.out.println(itemContry);
        System.out.println(itemCook);
        System.out.println(itemTime);
        
        if (mealSize==null) {
        	System.out.println("값이 아무것도 없는 상태");
        	return "userInfo";
		}
        
        System.out.println("값이 들어온 상태");
        double mealSizeInt= Double.parseDouble("mealSize");
        System.out.println("mealSizeInt="+mealSizeInt);
        
        String[] arr = {"one", "two", "three"};
        String itemMainString = String.join("|", itemMain);
        String itemMeatString = String.join("|", itemMeat);
        String itemVegitableString = String.join("|", itemVegitable);
        
        String ingredient=itemMainString+"|"+itemMeatString+"|"+itemVegitableString;
        String cookingStyle = String.join("|", itemContry);
        String cookingMethod = String.join("|", itemCook);
        String eatTime = String.join("|", itemTime);
        
		//user DAO에서 제일 마지막에 추가된 user_Num 받아오기
        int userNum=UserDAO.getInstance().getLastUserNum();
        //받아온 userNum 넣어서 나머지 데이터 입력해 userInfo 넣기
        int result=UserInfoDAO.getInstance().insertUserInfo(userNum, mealSizeInt, ingredient, cookingStyle, cookingMethod, eatTime);
        if (result==0) {
			System.out.println("UserInfoInsert 오류");
        	return "userInfo";
		}else {
			System.out.println("UserInfo 삽입완료");
			return "redirect:" + ctx + "/index.jsp";
		}
	}
}
