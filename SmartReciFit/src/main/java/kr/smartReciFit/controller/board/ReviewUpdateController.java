package kr.smartReciFit.controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.ReviewBoard;
import kr.smartReciFit.model.board.ReviewBoardDAO;
import kr.smartReciFit.model.user.UserDAO;
import kr.smartReciFit.util.FileUtil;

public class ReviewUpdateController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	       String reviewBoardNumParam = request.getParameter("reviewBoardNum");
	        int reviewBoardNum = Integer.parseInt(reviewBoardNumParam);
	        ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
	        ReviewBoard review = dao.getReviewById(reviewBoardNum);
	        String userNickname = request.getParameter("userNickname");

	       Gson gson = new Gson();
	        List<String> imagePaths = new ArrayList<>();
	          if (review.getReviewBoardImg() != null) {
	        	  try {
	        		  imagePaths = gson.fromJson(review.getReviewBoardImg(), List.class);
	        	  }catch (Exception e) {
	        		  System.err.println("JSON 파싱 오류: " + e.getMessage());
	        		imagePaths = new ArrayList<>();
	                  imagePaths.add(review.getReviewBoardImg());
	        	  }
	          }
	        
	        request.setAttribute("review", review);
	        request.setAttribute("imagePaths", imagePaths);
	        request.setAttribute("userNickname", userNickname);

	        return "reviewUpdate";
	    }
	}
