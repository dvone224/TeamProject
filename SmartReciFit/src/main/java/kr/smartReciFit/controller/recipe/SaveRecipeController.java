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

public class SaveRecipeController implements Controller {

    @Override
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Set<String> set = Set.of(request.getParameterValues("cooking-method"));
    	String test = String.join("|", request.getParameterValues("steps_img"));
    	
 
    	System.out.println(set);
    	System.out.println("test = " + test);
   
    	
        return "main";
    }
}