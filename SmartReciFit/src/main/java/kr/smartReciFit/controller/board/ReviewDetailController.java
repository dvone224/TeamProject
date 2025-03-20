package kr.smartReciFit.controller.board;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.Comment;
import kr.smartReciFit.model.board.CommentDAO;
import kr.smartReciFit.model.board.ReviewBoard;
import kr.smartReciFit.model.board.ReviewBoardDAO;
import kr.smartReciFit.model.user.UserDAO;

public class ReviewDetailController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String reviewBoardNumParam = request.getParameter("reviewBoardNum");
        int reviewBoardNum;
        String userNickname = request.getParameter("userNickname");

        if (reviewBoardNumParam == null || reviewBoardNumParam.isEmpty()) {
            response.sendRedirect("reviews.do");
            return null;
        } else {
            try {
                reviewBoardNum = Integer.parseInt(reviewBoardNumParam);
            } catch (NumberFormatException e) {
                response.sendRedirect("reviews.do");
                return null;
            }
        }

        HttpSession session = request.getSession();
        Integer userNum = (Integer) session.getAttribute("log"); 

        ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
        dao.viewCount(reviewBoardNum); // 조회수 증가
        ReviewBoard review = dao.getReviewById(reviewBoardNum);

        dao.updateLikeCount(reviewBoardNum);

        List<Comment> comment = CommentDAO.getInstance().getCommentsByBoardNum(reviewBoardNum);

        boolean isLiked = false;

        if (userNum != null) {
            isLiked = dao.isLiked(reviewBoardNum, userNum);
        }
        request.setAttribute("liked", isLiked);

        request.setAttribute("review", review);
        request.setAttribute("comments", comment);
        request.setAttribute("userNum", userNum);
        request.setAttribute("userNickname", userNickname);

        return "reviewDetail";
	    }

}
