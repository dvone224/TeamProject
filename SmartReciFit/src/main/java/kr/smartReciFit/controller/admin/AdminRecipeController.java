package kr.smartReciFit.controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.recipe.Recipe;
import kr.smartReciFit.model.recipe.RecipeDAO;

public class AdminRecipeController implements Controller {

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

		RecipeDAO dao = RecipeDAO.getInstance();

		int totalCnt = dao.getRecipeCount();
		int totalPages = (int) Math.ceil((double) totalCnt / pageSize);

		int start = (page - 1) * pageSize + 1;
		int end = page * pageSize;
		ArrayList<Recipe> recipes = dao.getRecipeByStartEnd(start,pageGroupSize);
		System.out.println(recipes);

		int startPage = ((page - 1) / pageGroupSize) * pageGroupSize + 1;
		int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

		request.setAttribute("recipes", recipes);
		request.setAttribute("page", page);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("user", user);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		return "adminRecipe";

	}

}
