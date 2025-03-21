package kr.smartReciFit.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.ReviewBoardDAO;

public class LikeController implements Controller {

    @Override
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        JSONObject json = new JSONObject();

        Integer userNum = (Integer) session.getAttribute("log");

        if (userNum == null) {
            json.put("success", false);
            json.put("message", "로그인이 필요합니다.");
            out.print(json.toJSONString());
            return null;
        }

        int reviewBoardNum = Integer.parseInt(request.getParameter("reviewBoardNum"));
        ReviewBoardDAO dao = ReviewBoardDAO.getInstance();

        boolean isLiked = dao.isLiked(reviewBoardNum, userNum);

        if (isLiked) {
            dao.deleteLike(reviewBoardNum, userNum);
        } else {
            dao.insertLike(reviewBoardNum, userNum);
        }

        dao.updateLikeCount(reviewBoardNum); 

        int totalLikes = dao.getTotalLikes(reviewBoardNum);

        json.put("success", true);
        json.put("liked", !isLiked); 
        json.put("likeCount", totalLikes);

        out.print(json.toJSONString());
        return null;
    }
}