package kr.smartReciFit.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.User;
import kr.smartReciFit.model.user.UserDAO;

public class GetSocialStatusController  implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return null; // 로그인하지 않은 경우 null 반환
        }

        Map<String, Boolean> status = new HashMap<>();
        status.put("naver", UserDAO.getInstance().isSocialLinked(user.getUserNum(), "naver"));
        status.put("kakao", UserDAO.getInstance().isSocialLinked(user.getUserNum(), "kakao"));
        status.put("google", UserDAO.getInstance().isSocialLinked(user.getUserNum(), "google"));
        status.put("apple", UserDAO.getInstance().isSocialLinked(user.getUserNum(), "apple"));

        Gson gson = new Gson();
        String json = gson.toJson(status);

        response.setContentType("application/json");
        response.getWriter().write(json);

        return null; // JSON 응답을 직접 작성했으므로 null 반환
    }
}