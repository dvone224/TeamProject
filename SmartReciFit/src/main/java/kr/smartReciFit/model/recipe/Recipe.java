package kr.smartReciFit.model.recipe;

import java.util.Set;

import org.json.simple.JSONArray;

import kr.smartReciFit.model.recipe.tags.CookingStyle;
import kr.smartReciFit.model.recipe.tags.EatTime;

/**
 * 
 */
public class Recipe {
	protected int recipeNum;
	protected String recipeName;
	protected String recipeIngredient;
	protected String recipeSeasoning;
	protected String recipeManual;
	protected Set<String> cookingMethods;
	protected Set<String> ingredients;
	protected EatTime eatTime;
	protected CookingStyle cookingStyle;

	public Recipe(int recipeNum, String recipeName, String recipeIngredient, String recipeSeasoning, String recipeManual,
			Set<String> cookingMethods, Set<String> ingredients, EatTime eatTime, CookingStyle cookingStyle) {
		this.recipeNum = recipeNum;
		this.recipeName = recipeName;
		this.recipeIngredient = recipeIngredient;
		this.recipeSeasoning = recipeSeasoning;
		this.recipeManual = recipeManual;
		this.cookingMethods = cookingMethods;
		this.ingredients = ingredients;
		this.eatTime = eatTime;
		this.cookingStyle = cookingStyle;
	}

	public int getRecipeNum() {
		return recipeNum;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public String getRecipeIngredient() {
		return recipeIngredient;
	}

	public String getRecipeSeasoning() {
		return recipeSeasoning;
	}

	public String getRecipeManual() {
		return recipeManual;
	}

	public String getCookingMethods() {
		JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(cookingMethods);
        String jsonString = jsonArray.toJSONString();
		return jsonString;
	}

	public String getIngredients() {
		JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(ingredients);
        String jsonString = jsonArray.toJSONString();
		return jsonString;
	}

	public String getEatTime() {
		return eatTime.getKoreanName();
	}

	public String getCookingStyle() {
		return cookingStyle.getKoreanName();
	}

}
