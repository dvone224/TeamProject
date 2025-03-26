package kr.smartReciFit.controller.recipe;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.recipe.RecipeDAO;

public class RecipeConverterController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonObject jsonData = null;
		Gson gson = new Gson();
		try (BufferedReader br = request.getReader()) {
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			String jsonString = sb.toString();
			jsonData = gson.fromJson(jsonString, JsonObject.class);
		}
		String jsonString = gson.toJson(jsonData);
		String result = RecipeDAO.getInstance().getRecipeConverter(jsonString);
		System.out.println("result = "+result);
		return null;
	}

}
