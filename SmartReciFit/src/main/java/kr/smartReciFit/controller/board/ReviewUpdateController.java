package kr.smartReciFit.controller.board;

import java.io.IOException;



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.ReviewBoard;
import kr.smartReciFit.model.board.ReviewBoardDAO;
import kr.smartReciFit.model.user.UserDAO;
import kr.smartReciFit.util.FileUtil;

public class ReviewUpdateController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String reviewBoardNumParam = request.getParameter("reviewBoardNum");
		int reviewBoardNum = Integer.parseInt(reviewBoardNumParam);
		ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
		ReviewBoard review = dao.getReviewById(reviewBoardNum);
		
		request.setAttribute("review", review);
	
	    return "reviewUpdate";
		
		
		
	}

}
