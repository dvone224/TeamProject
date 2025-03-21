package kr.smartReciFit.controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        String user = (String) session.getAttribute("user");

        int page = 1;
        int pageSize = 10;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        ReviewBoardDAO dao = ReviewBoardDAO.getInstance();

        int totalCnt = dao.getAllReviewsAndUser().size();
        int totalPages = (int) Math.ceil((double) totalCnt / pageSize);

        int start = (page - 1) * pageSize + 1;
        int end = page * pageSize;
        List<HashMap<String, Object>> reviews = dao.getReviewsByPage(start, end);

        request.setAttribute("reviews", reviews);
        request.setAttribute("page", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalCnt", totalCnt);
        request.setAttribute("user", user);

        return "reviews";
    }
}