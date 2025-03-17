package kr.smartReciFit.controller.board;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.ReviewBoard;
import kr.smartReciFit.model.board.ReviewBoardDAO;

public class ReviewDetailController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int reviewBoardNum = Integer.parseInt(request.getParameter("reviewBoardNum"));
        ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
        dao.viewCount(reviewBoardNum); // 조회수 증가
        ReviewBoard review = dao.getReviewById(reviewBoardNum);
        request.setAttribute("review", review);
        return "reviewDetail";
		
		
		
	}

}
