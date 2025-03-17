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
import kr.smartReciFit.controller.board.ReviewWriteController;
import kr.smartReciFit.controller.board.ReviewWriteProcessController;
import kr.smartReciFit.controller.user.LoginCheckController;
import kr.smartReciFit.controller.user.IdCheckController;
import kr.smartReciFit.controller.user.UserJoinController;



public class HandlerMapping {
	private HashMap<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/userJoin.do",  new UserJoinController());
		mappings.put("/idChekc.do",  new IdCheckController());
		
		mappings.put("/login", new LoginCheckController());
		
		// 랭킹 후기 게시판 작업(명보)
		mappings.put("/ranking.do", new RankingController());
		mappings.put("/reviews.do", new ReviewController());
		mappings.put("/reviewDetail.do", new ReviewDetailController());
		mappings.put("/reviewWrite.do", new ReviewWriteController());
		mappings.put("/reviewWriteProcess.do",new ReviewWriteProcessController());
	}

	public Controller getController(String key) {
		return mappings.get(key);
	}
}