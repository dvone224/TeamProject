package kr.smartReciFit.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.ReviewBoardDAO;

public class ReviewAdminDeleteController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reviewBoardNumParam = request.getParameter("reviewBoardNum");
		int reviewBoardNum = Integer.parseInt(reviewBoardNumParam);
		ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
		dao.deleteReview(reviewBoardNum);
		
		String ctx = request.getContextPath();
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println("<script>alert('게시물 삭제성공'); location.href='" + ctx + "/trendInfo.do';</script>");
		response.getWriter().flush();
		
	    return null;
    }
}
