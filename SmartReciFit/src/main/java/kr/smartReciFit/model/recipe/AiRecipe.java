package kr.smartReciFit.model.recipe;

public class AiRecipe extends Recipe {
	String aiRecipeUrl;
	String recipeManualTimeStamp;
	boolean aiRecipeBoolean;

	public String getAiRecipeUrl() {
		return aiRecipeUrl;
	}

	public String getRecipeManualTimeStamp() {
		return recipeManualTimeStamp;
	}

	public void setAiRecipeUrl(String aiRecipeUrl) {
		this.aiRecipeUrl = aiRecipeUrl;
	}

	public boolean isAiRecipeBoolean() {
		return aiRecipeBoolean;
	}
	
}
