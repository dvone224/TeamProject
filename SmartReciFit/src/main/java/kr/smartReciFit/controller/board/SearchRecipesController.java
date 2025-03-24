package kr.smartReciFit.controller.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

        if (keyword != null && !keyword.trim().isEmpty()) {
            List<HashMap<String,Object>> searchResults = RecipeDAO.getInstance().searchRecipes(keyword);

            // JSON 형식으로 응답
            JSONArray jsonArray = new JSONArray();
            for (HashMap<String,Object> recipe : searchResults) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("apiRecipeNum", recipe.get("api_recipe_id"));
                jsonObject.put("recipeName", recipe.get("recipe_name"));
                jsonObject.put("recipeNum", recipe.get("recipe_num"));
                jsonObject.put("recipeIngredient", recipe.get("recipe_ingredient"));

                jsonArray.add(jsonObject);
            }

            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(jsonArray.toString());
        }
        return null; // 뷰를 사용하지 않음
    }
}