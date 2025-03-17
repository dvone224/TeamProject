package kr.smartReciFit.controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.ReviewBoard;
import kr.smartReciFit.model.board.ReviewBoardDAO;

public class ReviewController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
        ArrayList<HashMap<String, Object>> reviews = dao.getAllReviewsAndUser();
        request.setAttribute("reviews", reviews);
        return "reviews";
		
		
		
	}

}
