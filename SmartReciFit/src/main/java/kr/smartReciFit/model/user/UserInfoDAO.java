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
		System.out.println("insertUserInfo()에서 만든 vo: "+vo.toString());
		int cnt=0;
		cnt=session.insert("insertInfo", vo);
		System.out.println("cnt: "+cnt);
		session.commit();
	    session.close();
		return cnt;
	}

	public UserInfo numGetUserInfo(int num) {
		Integer num2 = (Integer) num;
		SqlSession session = Config.getSession().openSession();
		UserInfo voInfo = session.selectOne("numGetUserInfo", num2);
		session.close();
		
		System.out.println("집어 넣은 num=" + num);
		System.out.println("가져온 UserInfo=" + voInfo);
		
		return voInfo;
	}
	
	
}
