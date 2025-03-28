package kr.smartReciFit.controller.board;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.recipe.RecipeDAO;

public class SearchRecipesController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyword = request.getParameter("keyword");
		Gson gson = new Gson();
		String jsonArray = gson.toJson(RecipeDAO.getInstance().searchRecipes(keyword));

		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(jsonArray.toString());

		return null; // 뷰를 사용하지 않음
	}
}