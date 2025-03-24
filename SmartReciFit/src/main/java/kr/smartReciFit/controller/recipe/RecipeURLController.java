package kr.smartReciFit.controller.recipe;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.recipe.RecipeDAO;

public class RecipeURLController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getParameter("youtube-url");
		if(url == null || url.length() == 0) {
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
		request.setAttribute("rn", 1400);
		String aiRecipe = dao.getRecipe(videoId);
		System.out.println(aiRecipe);
		return "recipeContent";
	}

}
