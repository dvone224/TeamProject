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

public class ReviewWriteProcessController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String title = request.getParameter("title"); // 타이틀
		String content = request.getParameter("content"); // 콘텐트
		String user = (String) request.getSession().getAttribute("nickName");
		int recipeNum;
		if(request.getParameter("recipeNum") == null) {
			System.out.println("해당레시피가 없음");
			recipeNum = 0;
		}else {
			recipeNum = Integer.parseInt(request.getParameter("recipeNum"));
			System.out.println(recipeNum);
		}
		
		String[] fileInfo = FileUtil.uploadFile(request,"img");
        if (fileInfo == null || fileInfo.length < 2) {
            response.setContentType("text/html; charset=UTF-8");
            String ctx = request.getContextPath();
            response.getWriter().println("<script>alert('이미지 업로드에 실패했습니다.'); location.href='" + ctx + "/reviewWrite.do';</script>"); // 리다이렉트
            return null;
        }
		  String originalFileName = fileInfo[0];
	      String savedFileName = fileInfo[1];
	      int userNum = UserDAO.getInstance().checkNickName(user);// 유저 넘버 (o)
	      // 리뷰 레시피 아이디 (나중)
	      int view = 0; // 리뷰 보드 뷰
	      int like = 0; // 리뷰 보드 좋아요
	      
	      
	      ReviewBoard vo = new ReviewBoard();
	      vo.setUserNum(userNum);
	      vo.setReviewBoardTitle(title);
	      vo.setReviewBoardContent(content);
	      vo.setReviewBoardImg(savedFileName);
	      vo.setReviewBoardViews(view);
	      vo.setReviewBoardLikes(like);
	      vo.setReviewBoardRecipeId(recipeNum);
	      
	      
	      ReviewBoardDAO.getInstance().addReview(vo);
	      String ctx = request.getContextPath();
	      response.setContentType("text/html; charset=UTF-8");
	      
	      response.getWriter().println("<script>alert('게시글이 작성되었습니다.'); location.href='" + ctx + "/reviews.do';</script>");
	      return null;
		
		
		
	}

}
