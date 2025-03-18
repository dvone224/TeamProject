package kr.smartReciFit.model.user;

import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.smartReciFit.util.Config;

public class UserDAO {

	
	private UserDAO() {}
	
	private static UserDAO instance;

	public static UserDAO getInstance() {
		if (instance == null)
			instance = new UserDAO();
		return instance;
	}

	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			String resource = "kr/basic/mybatis/config.xml";
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	
	//회원가입
	public boolean UserJoin(String id, String pw, String name, String nickName, String email, String phone, String profileImg) {
		User vo=new User(name, nickName, id, pw, email, phone, profileImg);
		int cnt=0;
		SqlSession session = Config.getSession().openSession();
		cnt=session.insert("userJoin", vo);
		session.commit();
		session.close();
		return cnt>0?true:false;
	}


	//아이디 체크 
	public Integer checkId(String id) {
		SqlSession session = Config.getSession().openSession();
		Integer num=session.selectOne("IdGetUserNum", id);
	    session.close();
	    System.out.println("아이디 체크 num="+num);
	    return num;
	}
	
	//닉네임 체크
	public Integer checkNickName(String nickName) {
		SqlSession session = Config.getSession().openSession();
		Integer num=session.selectOne("nickNameGetUserNum", nickName);
	    session.close();
	    System.out.println("아이디 체크 num="+num);
	    return num;
	}
}
