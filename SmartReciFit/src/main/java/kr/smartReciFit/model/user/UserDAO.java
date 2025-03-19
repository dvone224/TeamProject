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
	
	public boolean isValidId(String id) {
		try (SqlSession session = Config.getSession().openSession()) {
            String pass = session.selectOne("isValidId", id);
            // 비밀번호가 있으면 true, 없어서 null이면 false 반환
            return pass!=null; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	//num을 넣어서 User 반환하기
	public User numGetUser(int num) {
		Integer num2=(Integer)num;
		SqlSession session = Config.getSession().openSession();
		User vo=session.selectOne("numGetUser", num2);
		String test=session.selectOne("numGetName", num2);
	    session.close();
	    System.out.println("집어 넣은 num="+num);
	    System.out.println("가져온 User="+vo);
	    System.out.println("가져온 testName="+test);
		return vo;
	}
	
	
	
}
