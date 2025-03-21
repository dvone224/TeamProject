package kr.smartReciFit.controller.recipe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.recipe.Recipe;
import kr.smartReciFit.model.recipe.RecipeDAO;

public class RecipeFilterController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cookingMethods = request.getParameter("cookingMethods").trim();
		String ingredients = request.getParameter("ingredients").trim();
		String eatTimes = request.getParameter("eatTimes").trim();
		String cookingStyles = request.getParameter("cookingStyles").trim();
		HashMap<String, ArrayList<String>> filterMap = new HashMap<String, ArrayList<String>>();

		filterMap.put("cookingMethods", splitString(cookingMethods));
		filterMap.put("ingredients", splitString(ingredients));
		filterMap.put("eatTimes", splitString(eatTimes));
		filterMap.put("cookingStyles", splitString(cookingStyles));
		ArrayList<Recipe> recipeList = RecipeDAO.getInstance().getRecipeByFilter(filterMap);
		System.out.println("fliterMap = " + recipeList);
		Gson gson = new GsonBuilder().serializeNulls().create();
        String jsonString = gson.toJson(recipeList);
		// JSON-Simple을 사용하여 JSON으로 변환
		

//		request.setAttribute(cookingStyles, jsonObject);

		// 응답 설정
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// JSON 응답 전송
		PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
		return null;
	}

	private ArrayList<String> splitString(String str) {
		ArrayList<String> list = List.of(str.split(",")).stream().filter(s -> !s.isEmpty())
				.collect(Collectors.toCollection(ArrayList::new));
		return list;
	}

}
