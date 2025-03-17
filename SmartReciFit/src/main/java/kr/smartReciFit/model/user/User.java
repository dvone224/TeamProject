package kr.smartReciFit.model.user;

public class User {
	private int userNum;
	private String userName;
	private String userId;
	private String userNickname;
	private String userPw;
	private String userEmail;
	private String userPhone;
	private String userImg;
	
	public int getUserNum() {
		return userNum;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	
	
}
