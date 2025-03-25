package kr.smartReciFit.controller.board;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.ReviewBoardDAO;

public class ReviewController implements Controller {

    @Override
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("nickName");

        int page = 1;
        int pageSize = 10;
        int pageGroupSize = 10; // 한 번에 표시할 페이지 수

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        ReviewBoardDAO dao = ReviewBoardDAO.getInstance();

        int totalCnt = dao.getAllReviewsAndUser().size();
        int totalPages = (int) Math.ceil((double) totalCnt / pageSize);

        int start = (page - 1) * pageSize + 1;
        int end = page * pageSize;
        List<HashMap<String, Object>> reviews = dao.getReviewsByPage(start, end);
        Gson gson = new Gson();
        for (HashMap<String, Object> review : reviews) {
            String reviewBoardImg = (String) review.get("review_board_img");
            if (reviewBoardImg != null) {
                try {
                    List<String> imagePaths = gson.fromJson(reviewBoardImg, List.class);
                    if (imagePaths != null && !imagePaths.isEmpty()) {
                        review.put("review_board_img", imagePaths.get(0)); // 첫 번째 이미지만 저장
                    } else {
                        review.put("review_board_img", null); // 이미지가 없으면 null 저장
                    }
                } catch (com.google.gson.JsonSyntaxException e) {
                    System.err.println("JSON 파싱 오류: " + e.getMessage());
                    review.put("review_board_img", null); // 파싱 오류 발생 시 null 저장
                }
            }
        }
        
        int startPage = ((page - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        request.setAttribute("reviews", reviews);
        request.setAttribute("page", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalCnt", totalCnt);
        request.setAttribute("user", user);	
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        return "reviews";
    }
}