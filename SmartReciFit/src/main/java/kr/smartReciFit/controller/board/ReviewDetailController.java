package kr.smartReciFit.controller.board;

    import java.io.IOException;

    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import jakarta.servlet.http.HttpSession;
    import kr.smartReciFit.controller.Controller;
    import kr.smartReciFit.model.board.CommentDAO;
    import kr.smartReciFit.model.board.ReviewBoard;
    import kr.smartReciFit.model.board.ReviewBoardDAO;

    public class ReviewDetailController implements Controller {

        @Override
        public String requestHandler(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        	 HttpSession session = request.getSession();
             Integer userNum = (Integer) session.getAttribute("log");

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

             ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
             dao.viewCount(reviewBoardNum); // 조회수 증가
             ReviewBoard review = dao.getReviewById(reviewBoardNum);

             boolean isLiked = false;
             if (userNum != null) {
                 isLiked = dao.isLiked(reviewBoardNum, userNum);
             }

             int totalLikes = dao.getTotalLikes(reviewBoardNum);

             request.setAttribute("totalLikes", totalLikes);
             request.setAttribute("liked", isLiked);
             request.setAttribute("review", review);
             request.setAttribute("comments", CommentDAO.getInstance().getCommentsByBoardNum(reviewBoardNum));
             request.setAttribute("userNum", userNum);
             request.setAttribute("userNickname", userNickname);

             return "reviewDetail";
        }
    }