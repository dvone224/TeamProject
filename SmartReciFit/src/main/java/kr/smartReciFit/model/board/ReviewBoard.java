package kr.smartReciFit.model.board;

public class ReviewBoard {
	private int ReviewBoardNum;
	private int UserNum;
	private int ApiRecipeNum;
	private String ReviewBoardTitle;
	private String ReviewBoardContent;
	private String ReviewBoardImg;
	private int ReviewBoardViews;
	private int ReviewBoardLikes;
	private String ReviewBoardCreated;

	public int getReviewBoardNum() {
		return ReviewBoardNum;
	}

	public int getUserNum() {
		return UserNum;
	}

	public int getApiRecipeNum() {
		return ApiRecipeNum;
	}

	public String getReviewBoardTitle() {
		return ReviewBoardTitle;
	}

	public String getReviewBoardContent() {
		return ReviewBoardContent;
	}

	public String getReviewBoardImg() {
		return ReviewBoardImg;
	}

	public int getReviewBoardViews() {
		return ReviewBoardViews;
	}

	public int getReviewBoardLikes() {
		return ReviewBoardLikes;
	}

	public String getReviewBoardCreated() {
		return ReviewBoardCreated;
	}

}
