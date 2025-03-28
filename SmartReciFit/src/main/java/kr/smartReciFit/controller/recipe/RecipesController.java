package kr.smartReciFit.controller.recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.recipe.Recipe;
import kr.smartReciFit.model.recipe.RecipeDAO;
import kr.smartReciFit.model.recipe.tags.AllCookkingMethodTags;
import kr.smartReciFit.model.recipe.tags.AllIngredientTags;
import kr.smartReciFit.model.recipe.tags.CookingStyle;
import kr.smartReciFit.model.recipe.tags.EatTime;
import kr.smartReciFit.model.recipe.tags.KoreanNamedEnum;

public class RecipesController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RecipeDAO dao = RecipeDAO.getInstance();
		int offset = request.getParameter("offset") == null ? 1 : Integer.parseInt(request.getParameter("offset"));
		ArrayList<Recipe> recipeList = dao.getRecipeByLimitOffest(24, offset);
		Set<String> tagCookingMethod = AllCookkingMethodTags.getInstance().getAllCookkingMethodTags();
		Set<String> tagIngredient = AllIngredientTags.getInstance().getAllIngredientTags();
		ArrayList<String> tagEatTime = (ArrayList<String>) KoreanNamedEnum.getAllKoreanNames(EatTime.class);
		ArrayList<String> tagCookingStyle = (ArrayList<String>) KoreanNamedEnum.getAllKoreanNames(CookingStyle.class);
		request.setAttribute("recipeList", recipeList);
		request.setAttribute("tagCookingMethod", tagCookingMethod);
		request.setAttribute("tagIngredient", tagIngredient);
		request.setAttribute("tagEatTime", tagEatTime);
		request.setAttribute("tagCookingStyle", tagCookingStyle);
		request.setAttribute("offset", offset);
		
		return "recipes";
	}

}
