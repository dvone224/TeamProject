package kr.smartReciFit.controller.recipe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.recipe.RecipeDAO;
import kr.smartReciFit.model.recipe.tags.AllCookkingMethodTags;
import kr.smartReciFit.model.recipe.tags.AllIngredientTags;
import kr.smartReciFit.model.recipe.ApiRecipe;

public class CreateRecipeController implements Controller {

    @Override
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	int userNum = (int) request.getSession().getAttribute("log");
    	Set<String> tagCookingMethod = AllCookkingMethodTags.getInstance().getAllCookkingMethodTags();
		Set<String> tagIngredient = AllIngredientTags.getInstance().getAllIngredientTags();
    	
		request.setAttribute("tagCookingMethod", tagCookingMethod);
		request.setAttribute("tagIngredient", tagIngredient);
    	
        return "createRecipe";
    }
}