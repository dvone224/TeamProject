package kr.smartReciFit.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.UserDAO;

public class AdminUserController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO userDAO = UserDAO.getInstance();
		ArrayList<HashMap<String, Object>> userList = userDAO.getUserList();
		request.setAttribute("userList", userList);
		return "userList";
	}

}
