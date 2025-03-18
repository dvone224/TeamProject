package kr.smartReciFit.model.recipe;

import java.util.Arrays;
import java.util.Set;

import kr.smartReciFit.model.recipe.tags.CookingStyle;
import kr.smartReciFit.model.recipe.tags.EatTime;

public class ApiRecipe extends Recipe {

	public ApiRecipe(int recipeNum, String recipeName, String recipeIngredient, String recipeSeasoning, String recipeManual,
			Set<String> cookingMethods, Set<String> ingredients, EatTime eatTime, CookingStyle cookingStyle,
			String apiRecipeImg) {
		super(recipeNum, recipeName, recipeIngredient, recipeSeasoning, recipeManual, cookingMethods, ingredients, eatTime,
				cookingStyle);
		this.apiRecipeImg = apiRecipeImg;
	}

	private String apiRecipeImg;

	public String getApiRecipeImg() {
		return apiRecipeImg;
	}

	@Override
	public String toString() {
		return "ApiRecipe [recipeNum=" + recipeNum + ", recipeName=" + recipeName
				+ ",\n recipeIngredient=" + recipeIngredient + ",\n recipeSeasoning=" + recipeSeasoning + ",\n recipeManual="
				+ recipeManual + ",\n cookingMethods=" + cookingMethods + ", ingredients=" + ingredients + ", eatTime="
				+ getEatTime() + ", cookingStyle=" + getCookingStyle() + "]";
	}
	
	
	
}
