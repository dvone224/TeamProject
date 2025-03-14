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
		if(instance == null) instance = new UserDAO();
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
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		try (SqlSession session = Config.getSession().openSession()) {
			list = (ArrayList)session.selectList("getUserList");
		} catch (Exception e) {
			System.out.println("getUserList() 에러");
			e.printStackTrace();
		}
		return list;
	}
	
	//회원가입
	public boolean UserJoin(String id, String pw, String name, String nickName, String email, String phone, String profileImg) {
		User vo=new User(name, nickName, id, pw, email, phone, profileImg);
		try (SqlSession session = sqlSessionFactory.openSession()) {
			session.insert("UserJoin", vo);
			session.commit();
			return true;
		} catch (Exception e) {
			System.out.println("UserJoin에러");
			e.printStackTrace();
			return false;
		}
	}

	//아이디 체크 
	public Integer checkId(String id) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			Integer num=session.insert("IdGetUserNum", id);
			System.out.println(num);
			return num==null?0:num;
		} catch (Exception e) {
			System.out.println("checkId에러");
			e.printStackTrace();
			return null;
		}
	}

	public Integer checkNickName(String nickName) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			Integer num=session.insert("nickNameGetUserNum", nickName);
			System.out.println(num);
			return num==null?0:num;
		} catch (Exception e) {
			System.out.println("checkNickName에러");
			e.printStackTrace();
			return null;
		}
	}
}
