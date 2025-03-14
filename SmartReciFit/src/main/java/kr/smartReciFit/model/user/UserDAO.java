package kr.smartReciFit.model.user;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import kr.smartReciFit.util.Config;

public class UserDAO {

	private UserDAO() {

	}

	private static UserDAO instance;

	public static UserDAO getInstance() {
		if (instance == null)
			instance = new UserDAO();
		return instance;
	}

	public ArrayList<HashMap<String, Object>> getUserList() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try (SqlSession session = Config.getSession().openSession()) {
			list = (ArrayList) session.selectList("getUserList");
		} catch (Exception e) {
			System.out.println("getUserList() 에러");
			e.printStackTrace();
		}
		return list;
	}

	// 로그인
	public String userLogin(User user) {
		SqlSession session = Config.getSession().openSession();
		String userId = session.selectOne("userLogin", user);
		session.close();
		return userId;
	}

}
