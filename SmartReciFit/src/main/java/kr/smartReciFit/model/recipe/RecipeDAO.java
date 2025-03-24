package kr.smartReciFit.model.recipe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import kr.smartReciFit.server.JavaServer;
import kr.smartReciFit.util.Config;

public class RecipeDAO {
	private static RecipeDAO instance;

	private RecipeDAO() {
	}

	public static RecipeDAO getInstance() {
		if (instance == null)
			instance = new RecipeDAO();
		return instance;
	}

	public ArrayList<ApiRecipe> getApiRecipeListByLimit() {
		ArrayList<ApiRecipe> list = new ArrayList<ApiRecipe>();
		try (SqlSession sessoin = Config.getSession().openSession()) {
			list = (ArrayList) sessoin.selectList("getApiRecipeListByLimit", 10);
		} catch (Exception e) {
			System.out.println("getApiRecipeListByLimit() 오류");
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Recipe> getRecipeByFilter(HashMap<String, ArrayList<String>> parameter) {
		ArrayList<Recipe> list = new ArrayList<Recipe>();
		try (SqlSession sessoin = Config.getSession().openSession()) {
			list = (ArrayList) sessoin.selectList("getRecipeListByFilter", parameter);
		} catch (Exception e) {
			System.out.println("getRecipeByFilter() 오류");
			e.printStackTrace();
		}
		return list;
	}

	public String getRecipe(String URL) {
		String message = "";
		JavaServer server = JavaServer.getInstance();
		server.reconnectSocket();
		try (BufferedReader br = new BufferedReader(server.getReader());
				BufferedWriter bw = new BufferedWriter(server.getWriter())) {

			bw.write(URL); // 데이터 전송
			bw.newLine(); // 줄바꿈 문자를 추가하여 데이터의 끝을 표시
			bw.flush();

			String line = "";
			while ((line = br.readLine()) != null) {
				message += line + "\n";
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());

		}
		return message;
	}

	public ApiRecipe getRecipeByRecipeNum(int recipeNum) {
		ApiRecipe apiRecipe = null;
		try (SqlSession session = Config.getSession().openSession()) {
			apiRecipe = session.selectOne("getRecipeByRecipeNum", recipeNum);
		} catch (Exception e) {
			System.out.println("getRecipeByRecipeNum() 오류");
			e.printStackTrace();
		}
		return apiRecipe;
	}
}
