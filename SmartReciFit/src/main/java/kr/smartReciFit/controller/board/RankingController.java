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
		System.out.println("랭킹컨트롤러");
		ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
	    List<ReviewBoard> topRecipesByViews = dao.getTop3RecipesByViews();
	    List<ReviewBoard> topRecipesByLike = dao.getTop3RecipesByLike();
	    
	    request.setAttribute("topRecipesByViews", topRecipesByViews);
	    request.setAttribute("topRecipesByLike", topRecipesByLike);
	    return "ranking";
		
		
		
	}

}
