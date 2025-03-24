package kr.smartReciFit.model.user;

public class UserInfo {
	private int userNum;
	private double userMealSize;
	private String ingredient;
	private String cookingStyle;
	private String cookingMethod;
	private String eatTime;
	
	public UserInfo() {
		
	}
	
	public UserInfo(int userNum, double userMealSize, String ingredient, String cookingStyle, String cookingMethod,
			String eatTime) {
		super();
		this.userNum = userNum;
		this.userMealSize = userMealSize;
		this.ingredient = ingredient;
		this.cookingStyle = cookingStyle;
		this.cookingMethod = cookingMethod;
		this.eatTime = eatTime;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public double getUserMealSize() {
		return userMealSize;
	}

	public void setUserMealSize(double userMealSize) {
		this.userMealSize = userMealSize;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getCookingStyle() {
		return cookingStyle;
	}

	public void setCookingStyle(String cookingStyle) {
		this.cookingStyle = cookingStyle;
	}

	public String getCookingMethod() {
		return cookingMethod;
	}

	public void setCookingMethod(String cookingMethod) {
		this.cookingMethod = cookingMethod;
	}

	public String getEatTime() {
		return eatTime;
	}

	public void setEatTime(String eatTime) {
		this.eatTime = eatTime;
	}

	@Override
	public String toString() {
		return "UserInfo [userNum=" + userNum + ", userMealSize=" + userMealSize + ", ingredient=" + ingredient
				+ ", cookingStyle=" + cookingStyle + ", cookingMethod=" + cookingMethod + ", eatTime=" + eatTime + "]";
	}
	
}
