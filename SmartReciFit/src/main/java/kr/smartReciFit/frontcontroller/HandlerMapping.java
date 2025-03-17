package kr.smartReciFit.frontcontroller;


import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.controller.board.RankingController;
import kr.smartReciFit.controller.board.ReviewController;
import kr.smartReciFit.controller.board.ReviewDetailController;
import kr.smartReciFit.controller.user.LoginCheckController;
import kr.smartReciFit.controller.user.IdCheckController;
import kr.smartReciFit.controller.user.UserJoinController;

import kr.smartReciFit.controller.user.LogOutController;
import kr.smartReciFit.controller.user.LoginCheckController;
import kr.smartReciFit.controller.user.MainController;


public class HandlerMapping {
	private HashMap<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/userJoin.do",  new UserJoinController());
		mappings.put("/idChekc.do",  new IdCheckController());
		
		mappings.put("/login", new LoginCheckController());
		mappings.put("asdasd", new Controller() {
			@Override
			public String requestHandler(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
				return null;
			}
		});
		
		// 게시판 작업(명보)
		mappings.put("/ranking.do", new RankingController());
		mappings.put("/review.do", new ReviewController());
		mappings.put("/reviewDetail.do", new ReviewDetailController());
		mappings.put("/login.do", new LoginCheckController());
		mappings.put("/logout.do", new LogOutController());
		mappings.put("/main.do", new MainController());		
			
			
	}

	public Controller getController(String key) {
		return mappings.get(key);
	}
}