package kr.smartReciFit.model.recipe;

import java.util.Set;

import kr.smartReciFit.model.recipe.tags.CookingStyle;
import kr.smartReciFit.model.recipe.tags.EatTime;

public class Recipe {
	protected String recipeName;
	protected String recipeIngredient;
	protected String recipeSeasoning;
	protected String recipeManual;
	protected Set<String> cookingMethods;
	protected Set<String> ingredients;
	protected EatTime eatTime;
	protected CookingStyle cookingStyle;

	public Recipe(String recipeName, String recipeIngredient, String recipeSeasoning, String recipeManual,
			Set<String> cookingMethods, Set<String> ingredients, EatTime eatTime, CookingStyle cookingStyle) {
		this.recipeName = recipeName;
		this.recipeIngredient = recipeIngredient;
		this.recipeSeasoning = recipeSeasoning;
		this.recipeManual = recipeManual;
		this.cookingMethods = cookingMethods;
		this.ingredients = ingredients;
		this.eatTime = eatTime;
		this.cookingStyle = cookingStyle;
	}

	public EatTime getEatTime() {
		return eatTime;
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

}
