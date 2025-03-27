package kr.smartReciFit.controller.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.recipe.RecipeDAO;

public class AdminRecipeDeleteController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int recipeNum = Integer.parseInt(request.getParameter("recipe-num"));
		RecipeDAO.getInstance().deleteRecipeByRecipeNum(recipeNum);
		return "adminRecipe";
	}

}
