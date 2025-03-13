package kr.smartReciFit.model.recipe;

public class ApiRecipeDAO {
	
	private ApiRecipeDAO() {
		
	}
	
	private static ApiRecipeDAO instance;
	
	public static ApiRecipeDAO getInstance() {
		if(instance == null) instance = new ApiRecipeDAO();
		return instance;
	}
	
}
