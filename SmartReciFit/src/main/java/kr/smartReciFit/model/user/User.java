package kr.smartReciFit.model.user;

public class User {
	private int userNum;
	private String userName;
	private String userNickName;
	private String userId;
	private String userNickname;
	private String userPw;
	private String userEmail;
	private String userPhone;
	private String userImg;
	
	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserImg() {
		return userImg;
	}

	
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	
	public User() {
		super();
	}

	public User(String userName, String userNickName, String userId, String userPw, String userEmail,
			String userPhone, String userImg) {
		super();
		this.userName = userName;
		this.userNickName = userNickName;
		this.userId = userId;
		this.userPw = userPw;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userImg = userImg;
	}

	
}
