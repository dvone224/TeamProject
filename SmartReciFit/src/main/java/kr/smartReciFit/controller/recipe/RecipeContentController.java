package kr.smartReciFit.controller.recipe;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.recipe.RecipeDAO;

public class RecipeContentController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int recipeNum = Integer.parseInt(request.getParameter("rn"));
		request.setAttribute("recipe", RecipeDAO.getInstance().getRecipeByRecipeNum(recipeNum));
		return "recipeContent";
	}

}
