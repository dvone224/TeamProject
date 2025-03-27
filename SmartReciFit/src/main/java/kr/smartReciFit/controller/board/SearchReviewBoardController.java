package kr.smartReciFit.controller.board;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.ReviewBoardDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchReviewBoardController implements Controller {

    @Override
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String searchName = request.getParameter("searchName");
        String keyword = request.getParameter("keyword");
        String user = (String) session.getAttribute("nickName");

        int page = 1;
        int pageSize = 10;
        int pageGroupSize = 10;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
        List<HashMap<String, Object>> allSearchResults = dao.searchReviewBoard(searchName, keyword);

        // JSON 첫번째 이미지 추출
        Gson gson = new Gson();
        for (HashMap<String, Object> review : allSearchResults) {
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
                    review.put("review_board_img", null); //오류 발생 시 null 저장
                }
            }
        }

        int totalCnt = allSearchResults.size();
        int totalPages = (int) Math.ceil((double) totalCnt / pageSize);

        int start = (page - 1) * pageSize + 1;
        int end = Math.min(page * pageSize, totalCnt);
        List<HashMap<String, Object>> searchResults = allSearchResults.subList(Math.max(0, start - 1), Math.max(end, 0));

        int startPage = ((page - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);


        request.setAttribute("user", user);
        request.setAttribute("searchResults", searchResults);
        request.setAttribute("page", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalCnt", totalCnt);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("searchName", searchName);
        request.setAttribute("keyword", keyword);
        return "searchAdminResult";
    }
}