package kr.smartReciFit.controller.board;

    import java.io.IOException;
import java.util.ArrayList;
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
import kr.smartReciFit.model.recipe.ApiRecipeRawData;
import kr.smartReciFit.model.recipe.Recipe;
import kr.smartReciFit.model.recipe.RecipeDAO;

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

             int totalLikes = review.getReviewBoardLikes();
              System.out.println("totalLikes: " + totalLikes); // 로그 출력
              
              
             
             int commentPage = 1; // 기본 코멘트 페이지
             int commentPageSize = 10; // 한 페이지당 보여줄 코멘트 수
             int pageGroupSize = 10;

             if (request.getParameter("commentPage") != null) {
                 commentPage = Integer.parseInt(request.getParameter("commentPage"));
             }
             List<Comment> allComments = CommentDAO.getInstance().getCommentsByBoardNum(reviewBoardNum);
             int totalComments = allComments.size();
             int totalCommentPages = (int) Math.ceil((double) totalComments / commentPageSize);
             
             
             int commentStart = (commentPage - 1) * commentPageSize + 1;
             int commentEnd = commentPage * commentPageSize;
             List<Comment> comments = allComments.subList(Math.max(0, commentStart - 1), Math.min(commentEnd, totalComments));
             
             int startPage = ((commentPage - 1) / pageGroupSize) * pageGroupSize + 1;
             int endPage = Math.min(startPage + pageGroupSize - 1, totalCommentPages);

             request.setAttribute("totalLikes", totalLikes);
             request.setAttribute("liked", isLiked);
             request.setAttribute("review", review);
             request.setAttribute("comments",comments);
             request.setAttribute("userNum", userNum);
             request.setAttribute("userNickname", userNickname);
             request.setAttribute("commentPage", commentPage);
             request.setAttribute("totalCommentPages", totalCommentPages);
             request.setAttribute("startPage", startPage);
             request.setAttribute("endPage", endPage);
             
             Recipe recipe = null;
             int recipeNum = review.getReviewBoardRecipeId();
             if(recipeNum > 0) {
            	 recipe = RecipeDAO.getInstance().getRecipeByNum(recipeNum);
            	 request.setAttribute("recipe", recipe);
             }

             return "reviewDetail";
        }
    }