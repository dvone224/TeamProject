package kr.smartReciFit.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.UserDAO;

//Asynchronous Javascript And XML의 약어: 비동기 통신
//A의 작업이 끝나면 B가 시작한다: 동기
//A의 작업과 관계 없이 B가 시작한다: 비동기


public class VaildIdAjaxController implements Controller {

	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// $.ajax();
		//아이디 받아오기
		String id=request.getParameter("id"); // { "id" : id  }
		//아이디 검사 항목 돌려서 아이디 유효성 검사하기
		//notVaild: 이미 사용중인 아이디
		//vaild: 사용 가능한 아이디
		String passData = UserDAO.getInstance().isValidId(id)? "notValid" : "valid";
	
		// 검사 결과를 보내기
		// ajax() 함수에 만들어놓은 callback함수 응답
		// response에서 응답에 따라 UI 업데이트
		response.getWriter().print(passData); // "notValid" : "valid";
		
		return null;
	}

}
