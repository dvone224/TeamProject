package kr.smartReciFit.controller.board;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.Comment;
import kr.smartReciFit.model.board.CommentDAO;
import kr.smartReciFit.model.board.ReviewBoard;
import kr.smartReciFit.model.board.ReviewBoardDAO;

public class ReviewDetailController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int reviewBoardNum = Integer.parseInt(request.getParameter("reviewBoardNum"));
		String userId = request.getParameter("user");
        ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
        dao.viewCount(reviewBoardNum); // 조회수 증가
        ReviewBoard review = dao.getReviewById(reviewBoardNum);
        
        List<Comment>comment = CommentDAO.getInstance().getCommentsByBoardNum(reviewBoardNum);
        request.setAttribute("review", review);
        request.setAttribute("comments", comment);
        request.setAttribute("user", userId);
        
        return "reviewDetail";
		
		
		
	}

}
