package kr.smartReciFit.model.board;

public class Comment {
	private int CommentNum;
	private int UserNum;
	private int BoardNum;
	private String CommentContent;
	private String CommentCreated;
	public int getCommentNum() {
		return CommentNum;
	}
	public int getUserNum() {
		return UserNum;
	}
	public int getBoardNum() {
		return BoardNum;
	}
	public String getCommentContent() {
		return CommentContent;
	}
	public String getCommentCreated() {
		return CommentCreated;
	}
	
}
