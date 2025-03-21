package kr.smartReciFit.model.recipe;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import kr.smartReciFit.util.Config;

public class RecipeDAO {
	private static RecipeDAO instance;
	private RecipeDAO() {
	}
	
	public static RecipeDAO getInstance() {
		if(instance == null) instance = new RecipeDAO();
		return instance;
	}
	
	public ArrayList<ApiRecipe> getApiRecipeListByLimit(){
		ArrayList<ApiRecipe> list = new ArrayList<ApiRecipe>();
		try (SqlSession sessoin = Config.getSession().openSession()){
			list = (ArrayList) sessoin.selectList("getApiRecipeListByLimit",10);
		} catch (Exception e) {
			System.out.println("getApiRecipeListByLimit() 오류");
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Recipe> getRecipeByFilter(HashMap<String, ArrayList<String>> parameter) {
		ArrayList<Recipe> list = new ArrayList<Recipe>();
		try (SqlSession sessoin = Config.getSession().openSession()){
			list = (ArrayList) sessoin.selectList("getRecipeListByFilter", parameter);
		} catch (Exception e) {
			System.out.println("getRecipeByFilter() 오류");
			e.printStackTrace();
		}
		return list;
	}
}
