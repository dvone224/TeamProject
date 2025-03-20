package kr.smartReciFit.model.user;

public class User {
	private int userNum;
	private String userName;
	private String userNickName;
	private String userId;
	private String userPw;
	private String userEmail;
	private String userPhone;
	private String userImg;
	private String platformK;
	private String platformMailK;
	private String platformN;
	private String platformMailN;
	private String platformG;
	private String platformMailG;
	
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

	public String getUserPw() {
		return userPw;
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
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	

	public User(String userName, String userNickName, String userId, String userPw, String userEmail, String userPhone,
			String userImg) {
		super();
		this.userName = userName;
		this.userNickName = userNickName;
		this.userId = userId;
		this.userPw = userPw;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userImg = userImg;
	}
	
	//소셜용 생성자 따로 만들기
	


	public User() {
	}

	@Override
	public String toString() {
		return "User [userNum=" + userNum + ", userName=" + userName + ", userNickName=" + userNickName + ", userId="
				+ userId + ", userPw=" + userPw + ", userEmail=" + userEmail + ", userPhone=" + userPhone + ", userImg="
				+ userImg + "]";
	}
	
}
