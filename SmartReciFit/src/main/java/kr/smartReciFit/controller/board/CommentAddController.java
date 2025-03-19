package kr.smartReciFit.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
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

public class CommentAddController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user = (String) request.getSession().getAttribute("user");
		System.out.println(user);
		if(request.getSession().getAttribute("user") == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('로그인 후 이용가능합니다'); history.back();</script>");
			writer.close();
			return null;
		}
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
        String commentContent = request.getParameter("commentContent");
        int userNum = UserDAO.getInstance().checkId(user);
        
        Comment comment = new Comment();
        comment.setUserNum(userNum);
        comment.setBoardNum(boardNum);
        comment.setCommentContent(commentContent);
        
        CommentDAO.getInstance().addComment(comment);
        request.setAttribute("userId", user);
        request.setAttribute("boardNum", boardNum);
		
        String ctx = request.getContextPath();
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println("<script>alert('댓글 게시성공'); location.href='" + ctx + "/reviewDetail.do?reviewBoardNum="+boardNum+"';</script>");
		response.getWriter().flush();
        
        return null;
		
		
		
	}

}
