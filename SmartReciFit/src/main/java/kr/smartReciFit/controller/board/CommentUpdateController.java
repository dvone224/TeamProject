package kr.smartReciFit.controller.board;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.Comment;
import kr.smartReciFit.model.board.CommentDAO;


public class CommentUpdateController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 int commentNum = Integer.parseInt(request.getParameter("commentNum"));
	        String commentContent = request.getParameter("commentContent");
	        String reviewBoardNum = request.getParameter("reviewBoardNum"); // reviewBoardNum 파라미터 추가

	        Comment comment = new Comment();
	        comment.setCommentNum(commentNum);
	        comment.setCommentContent(commentContent);

	        CommentDAO commentDAO = CommentDAO.getInstance();
	        commentDAO.updateComment(comment);
	        return	null;
//	        String redirectURL = "reviewDetail.do?reviewBoardNum=" + reviewBoardNum;
//
//	        return "redirect:" + redirectURL; 
	    }

}
