package kr.smartReciFit.controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.CommentDAO;


public class CommentDeleteController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = (String) request.getSession().getAttribute("user");
		System.out.println("userId?="+userId);
		int commentNum = Integer.parseInt(request.getParameter("commentNum"));
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		CommentDAO.getInstance().deleteComment(commentNum,boardNum);
		String ctx = request.getContextPath();
		String reviewBoardNum = request.getParameter("boardNum");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert('삭제 되었습니다'); location.href='"+ctx+"/reviewDetail.do?reviewBoardNum="+reviewBoardNum+"&user="+userId+"';</script>");
		writer.close();
		
		return null;
	    }
	}
