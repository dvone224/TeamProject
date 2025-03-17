package kr.smartReciFit.model.recipe;

import java.util.Set;

import kr.smartReciFit.model.recipe.tags.CookingStyle;
import kr.smartReciFit.model.recipe.tags.EatTime;

public class ApiRecipe extends Recipe{


	public ApiRecipe(String recipeName, String recipeIngredient, String recipeSeasoning, String recipeManual,
			Set<String> cookingMethods, Set<String> ingredients, EatTime eatTime, CookingStyle cookingStyle) {
		super(recipeName, recipeIngredient, recipeSeasoning, recipeManual, cookingMethods, ingredients, eatTime, cookingStyle);
		// TODO Auto-generated constructor stub
	}

	private int apiRecipeNum;
	private String apiRecipeImg;

	public int getApiRecipeNum() {
		return apiRecipeNum;
	}

	public String getApiRecipeImg() {
		return apiRecipeImg;
	}


}
