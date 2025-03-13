package kr.smartReciFit.model.user;

public class UserInfoDAO {
	
	private UserInfoDAO() {
		
	}
	
	private static UserInfoDAO instance;
	
	public static UserInfoDAO getInstance() {
		if(instance == null) instance = new UserInfoDAO();
		return instance;
	}
	
}
