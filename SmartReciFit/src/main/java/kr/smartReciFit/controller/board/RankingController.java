package kr.smartReciFit.controller.board;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.ReviewBoard;
import kr.smartReciFit.model.board.ReviewBoardDAO;

public class RankingController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
//	    List<ReviewBoard> topRecipes = dao.getTop3RecipesByViews();
//	    request.setAttribute("topRecipes", topRecipes); 오류나서 잠시 보류
	    return "ranking";
		
		
		
	}

}
