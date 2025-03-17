package kr.smartReciFit.model.board;

public class ReviewBoard {
	private int reviewBoardNum;
	private int userNum;
	private int apiRecipeNum;
	private String reviewBoardTitle;
	private String reviewBoardContent;
	private String reviewBoardImg;
	private int reviewBoardViews;
	private int reviewBoardLikes;
	private String reviewBoardCreatedAt;
	
	



	public int getReviewBoardNum() {
		return reviewBoardNum;
	}

	public int getUserNum() {
		return userNum;
	}

	public int getApiRecipeNum() {
		return apiRecipeNum;
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
