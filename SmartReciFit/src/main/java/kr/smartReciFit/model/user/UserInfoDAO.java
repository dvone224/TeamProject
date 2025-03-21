package kr.smartReciFit.model.user;

import org.apache.ibatis.session.SqlSession;

import kr.smartReciFit.util.Config;

public class UserInfoDAO {
	
	private UserInfoDAO() {}
	
	private static UserInfoDAO instance;
	
	public static UserInfoDAO getInstance() {
		if(instance == null) instance = new UserInfoDAO();
		return instance;
	}
	
	//info삽입하기
	
	public int insertUserInfo(int num, double mealSize, String ingredient, String cookingStyle, String cookingMethod, String eatTime) {
		SqlSession session = Config.getSession().openSession();
		UserInfo vo = new UserInfo(num, mealSize, ingredient, cookingStyle, cookingMethod, eatTime); 
		int cnt=0;
		cnt=session.insert("insertInfo", vo);
		System.out.println("insertUserInfo()에서 만든 vo: "+vo);
	    session.close();
		return cnt;
	}
	
	
}
