package kr.smartReciFit.model.board;

public class ReviewBoard {
	private int reviewBoardNum;
	private int userNum;
	private int reviewBoardRecipeId;
	private String reviewBoardTitle;
	private String reviewBoardContent;
	private String reviewBoardImg;
	private int reviewBoardViews;
	private int reviewBoardLikes;
	private String reviewBoardCreatedAt;
	
	private Integer recipeNum;
	private String recipeName;
	private String recipeThumbnail;
	
	
	
	
	public String getRecipeThumbnail() {
		return recipeThumbnail;
	}

	public void setRecipeThumbnail(String recipeThumbnail) {
		this.recipeThumbnail = recipeThumbnail;
	}

	public Integer getRecipeNum() {
		return recipeNum;
	}

	public void setRecipeNum(Integer recipeNum) {
		this.recipeNum = recipeNum;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public int getReviewBoardRecipeId() {
		return reviewBoardRecipeId;
	}

	public void setReviewBoardRecipeId(int reviewBoardRecipeId) {
		this.reviewBoardRecipeId = reviewBoardRecipeId;
	}

	public void setReviewBoardNum(int reviewBoardNum) {
		this.reviewBoardNum = reviewBoardNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}


	public void setReviewBoardTitle(String reviewBoardTitle) {
		this.reviewBoardTitle = reviewBoardTitle;
	}

	public void setReviewBoardContent(String reviewBoardContent) {
		this.reviewBoardContent = reviewBoardContent;
	}

	
	public void setReviewBoardImg(String reviewBoardImg) {
		this.reviewBoardImg = reviewBoardImg;
	}

	public void setReviewBoardViews(int reviewBoardViews) {
		this.reviewBoardViews = reviewBoardViews;
	}

	public void setReviewBoardLikes(int reviewBoardLikes) {
		this.reviewBoardLikes = reviewBoardLikes;
	}

	public void setReviewBoardCreatedAt(String reviewBoardCreatedAt) {
		this.reviewBoardCreatedAt = reviewBoardCreatedAt;
	}

	public int getReviewBoardNum() {
		return reviewBoardNum;
	}

	public int getUserNum() {
		return userNum;
	}



	public String getReviewBoardTitle() {
		return reviewBoardTitle;
	}

	public String getReviewBoardContent() {
		return reviewBoardContent;
	}

	public String getReviewBoardImg() {
		return reviewBoardImg;
	}

	public int getReviewBoardViews() {
		return reviewBoardViews;
	}

	public int getReviewBoardLikes() {
		return reviewBoardLikes;
	}

	public String getReviewBoardCreated() {
		return reviewBoardCreatedAt;
	}
}
