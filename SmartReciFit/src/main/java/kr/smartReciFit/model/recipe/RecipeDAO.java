package kr.smartReciFit.model.recipe;

import java.util.ArrayList;

public class RecipeDAO {
	private static RecipeDAO instance;
	private RecipeDAO() {
	}
	
	public RecipeDAO getInstance() {
		if(instance == null) instance = new RecipeDAO();
		return instance;
	}
	
	public ArrayList<Recipe> getRecipeListByLimit(){
		
		
		return null;
	}
}
