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

public class ReviewDeleteController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String reviewBoardNumParam = request.getParameter("reviewBoardNum");
		int reviewBoardNum = Integer.parseInt(reviewBoardNumParam);
		ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
		dao.deleteReview(reviewBoardNum);
		
		String ctx = request.getContextPath();
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println("<script>alert('게시물 삭제성공'); location.href='" + ctx + "/reviews.do';</script>");
		response.getWriter().flush();
		
	    return null;
		
		
		
	}

}
