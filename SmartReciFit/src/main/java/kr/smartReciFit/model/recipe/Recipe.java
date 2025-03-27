package kr.smartReciFit.model.recipe;

import java.util.Set;

import org.json.simple.JSONArray;

import kr.smartReciFit.model.recipe.tags.CookingStyle;
import kr.smartReciFit.model.recipe.tags.EatTime;
import kr.smartReciFit.model.recipe.tags.RecipeType;

/**
 * 
 */
/**
 * 
 */
public class Recipe {
	protected int recipeNum;
	protected String recipeName;
	protected double recipeMealSize = 1.0;
	protected RecipeType recipeType;
	protected String recipeIngredient;
	protected String recipeSeasoning;
	protected String recipeManual;
	protected String recipeThumbnail;
	protected Set<String> cookingMethods;
	protected Set<String> ingredients;
	protected EatTime eatTime;
	protected CookingStyle cookingStyle;

	public Recipe() {

	}

	public Recipe(int recipeNum, String recipeName, RecipeType recipeType, String recipeIngredient,
			String recipeSeasoning, String recipeManual, Set<String> cookingMethods, Set<String> ingredients,
			EatTime eatTime, CookingStyle cookingStyle) {
		this.recipeNum = recipeNum;
		this.recipeName = recipeName;
		this.recipeType = recipeType;
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

	public RecipeType getRecipeType() {
		return recipeType;
	}

	public String getRecipeThumbnail() {
		return recipeThumbnail;
	}

	public Set<String> getCookingMethods() {
		return cookingMethods;
	}

	public Set<String> getIngredients() {
		return ingredients;
	}

	public EatTime getEatTime() {
		return eatTime;
	}

	public CookingStyle getCookingStyle() {
		return cookingStyle;
	}

	public void setRecipeThumbnail(String recipeThumbnail) {
		this.recipeThumbnail = recipeThumbnail;
	}

	public void setRecipeType(RecipeType recipeType) {
		this.recipeType = recipeType;
	}

	public void setRecipeNum(int recipeNum) {
		this.recipeNum = recipeNum;
	}

	public double getRecipeMealSize() {
		return recipeMealSize;
	}
	

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public void setRecipeMealSize(double recipeMealSize) {
		this.recipeMealSize = recipeMealSize;
	}

	public void setRecipeIngredient(String recipeIngredient) {
		this.recipeIngredient = recipeIngredient;
	}

	public void setRecipeSeasoning(String recipeSeasoning) {
		this.recipeSeasoning = recipeSeasoning;
	}

	public void setRecipeManual(String recipeManual) {
		this.recipeManual = recipeManual;
	}

	public void setCookingMethods(Set<String> cookingMethods) {
		this.cookingMethods = cookingMethods;
	}

	public void setIngredients(Set<String> ingredients) {
		this.ingredients = ingredients;
	}

	public void setEatTime(EatTime eatTime) {
		this.eatTime = eatTime;
	}

	public void setCookingStyle(CookingStyle cookingStyle) {
		this.cookingStyle = cookingStyle;
	}

	@Override
	public String toString() {
		return "Recipe [recipeNum=" + recipeNum + ", recipeName=" + recipeName + ", recipeType=" + recipeType
				+ ", recipeIngredient=" + recipeIngredient + ", recipeSeasoning=" + recipeSeasoning + ", recipeManual="
				+ recipeManual + ", recipeThumbnail=" + recipeThumbnail + ", cookingMethods=" + cookingMethods
				+ ", ingredients=" + ingredients + ", eatTime=" + eatTime + ", cookingStyle=" + cookingStyle + "]";
	}

}
