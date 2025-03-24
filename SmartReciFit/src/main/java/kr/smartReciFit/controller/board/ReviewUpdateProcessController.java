package kr.smartReciFit.controller.board;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.ReviewBoard;
import kr.smartReciFit.model.board.ReviewBoardDAO;
import kr.smartReciFit.util.FileUtil;

public class ReviewUpdateProcessController implements Controller {

    @Override
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int reviewBoardNum = Integer.parseInt(request.getParameter("reviewBoardNum"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String reiveImg = request.getParameter("reiveImg");
        String ctx = request.getContextPath(); // 컨텍스트 경로
        String userNickname = request.getParameter("userNickname");

        String filename = "";
        FileUtil fileUtil = new FileUtil();
        String[] fileInfo = fileUtil.uploadFile(request, "img");
        if (fileInfo == null) {
            filename = reiveImg;
        } else {
        filename = fileInfo[1];
        }
        ReviewBoard review = new ReviewBoard();
        review.setReviewBoardNum(reviewBoardNum);
        review.setReviewBoardTitle(title);
        review.setReviewBoardContent(content);
        review.setReviewBoardImg(filename);

        ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
        int cnt = dao.updateReview(review);

        if (cnt > 0) {
        	response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('게시글이 수정되었습니다.'); location.href='" + ctx + "/reviewDetail.do?reviewBoardNum=" + reviewBoardNum + "&userNickname="+userNickname+"';</script>");
        } else {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('게시글 수정에 실패했습니다.'); history.back();</script>");
        }

        return null;
    }
}