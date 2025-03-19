package kr.smartReciFit.model.board;

public class Comment {
	private int commentNum;
	private int userNum;
	private int boardNum;
	private String commentContent;
	private String commentCreated;
	private String userNickname;
	
	
	
	
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickName) {
		this.userNickname = userNickName;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public void setCommentCreated(String commentCreated) {
		this.commentCreated = commentCreated;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public int getUserNum() {
		return userNum;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public String getCommentCreated() {
		return commentCreated;
	}
	
}
