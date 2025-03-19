package kr.smartReciFit.model.recipe;

public class ApiRecipeRawData {
	private int recipeNum;
	private int apiRecipeNum;
	private String recipeName;
	private String recipeIngredient;
	private String recipeSeasoning;
	private String recipeManual;
	private String tagEatTime;
	private String tagIngredient;
	private String tagCookingStyle;
	private String tagCookingMethod;
	private String recipeImg;

	public int getApiRecipeNum() {
		return apiRecipeNum;
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

	public String getTagEatTime() {
		return tagEatTime;
	}

	public String getTagIngredient() {
		return tagIngredient;
	}

	public String getTagCookingStyle() {
		return tagCookingStyle;
	}

	public String getTagCookingMethod() {
		return tagCookingMethod;
	}

	public String getRecipeImg() {
		return recipeImg;
	}

}
