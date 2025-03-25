package kr.smartReciFit.controller.recipe;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.recipe.Recipe;
import kr.smartReciFit.model.recipe.RecipeDAO;
import kr.smartReciFit.model.recipe.tags.RecipeType;

public class RecipeURLController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getParameter("youtube-url");
		if (url == null || url.length() == 0) {
			return "recipes";
		}
		RecipeDAO dao = RecipeDAO.getInstance();

		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String videoId = null;

		if (uri.getHost().equals("youtu.be")) {
			String[] pathParts = uri.getPath().split("\\?");
			videoId = pathParts[0].replaceFirst("/", "");
		} else if (uri.getQuery() != null && uri.getQuery().contains("v=")) {
			// v= 형식의 URL에서 비디오 ID 추출
			String[] params = uri.getQuery().split("&");
			for (String param : params) {
				if (param.startsWith("v=")) {
					videoId = URLDecoder.decode(param.substring(2), "UTF-8");
				}
			}
		}
		System.out.println("videoId = " + videoId);
		request.setAttribute("rn", 1400);
		String aiRecipe = dao.getRecipe(videoId);
		System.out.println("aiRecipe = " + aiRecipe);
		Gson gson = new GsonBuilder().create();
		Recipe recipe = gson.fromJson(aiRecipe, Recipe.class);
		String thumbnailUrl = "https://img.youtube.com/vi/" + videoId + "/maxresdefault.jpg";
		recipe.setRecipeThumbnail(thumbnailUrl);
		recipe.setRecipeType(RecipeType.AI);
		request.setAttribute("recipe", recipe);
		String[] temp = recipe.getRecipeManual().split("\\|");
		ArrayList<String> timeStamp = new ArrayList<String>();
		Pattern pattern = Pattern.compile("\\(*\\)");
		for (String step : temp) {
			Matcher matcher = pattern.matcher(step);
			if (matcher.find()) {
				timeStamp.add(matcher.group(0).replace("(", "").replace(")", "").split("-")[0]);
			}
		}
		request.setAttribute("timeStamp", timeStamp);
		request.setAttribute("videoId", videoId);
		return "recipeContent";
	}

}
