package kr.smartReciFit.model.user;

import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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

	// 회원가입
	public boolean UserJoin(String id, String pw, String name, String nickName, String email, String phone,
			String profileImg) {
		User vo = new User(name, nickName, id, pw, email, phone, profileImg);
		int cnt = 0;
		SqlSession session = Config.getSession().openSession();
		cnt = session.insert("userJoin", vo);
		session.commit();
		session.close();
		return cnt > 0 ? true : false;
	}

	// 아이디 체크
	public Integer checkId(String id) {
		SqlSession session = Config.getSession().openSession();
		Integer num = session.selectOne("IdGetUserNum", id);
		session.close();
		System.out.println("아이디 체크 num=" + num);
		return num;
	}

	// 닉네임 체크
	public Integer checkNickName(String nickName) {
		SqlSession session = Config.getSession().openSession();
		Integer num = session.selectOne("nickNameGetUserNum", nickName);
		session.close();
		System.out.println("아이디 체크 num=" + num);
		return num;
	}

	public boolean isValidId(String id) {
		try (SqlSession session = Config.getSession().openSession()) {
			String pass = session.selectOne("isValidId", id);
			// 비밀번호가 있으면 true, 없어서 null이면 false 반환
			return pass != null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// id에 맞는 nickName 가져오기
	public String getNickName(String id) {
		SqlSession session = Config.getSession().openSession();
		String nickName = session.selectOne("IdGetNickName", id);
		session.close();
		System.out.println("아이디 체크 nickName = " + nickName);
		return nickName;
	}

	// num을 넣어서 User 반환하기
	public User numGetUser(int num) {
		Integer num2 = (Integer) num;
		SqlSession session = Config.getSession().openSession();
		User vo = session.selectOne("numGetUser", num2);
		session.close();
		System.out.println("집어 넣은 num=" + num);
		System.out.println("가져온 User=" + vo);
		return vo;
	}

	// 소셜 로그인 시 social 테이블에 추가 ( userNum, email 넣는다 )
	public void InsertSocialInfo(SocialDTO socialDTO) {
		SqlSession session = Config.getSession().openSession();
		try {
			int cnt = session.insert("insertSocialInfo", socialDTO);
			if (cnt > 0) {
				session.commit(); // 변경사항 저장
				System.out.println("소셜 로그인 정보 삽입 성공(social table)");
			} else {
				System.out.println("삽입할 정보가 없습니다.");
			}
		} catch (Exception e) {
			System.out.println("소셜 로그인 정보 삽입 중 오류 발생: " + e.getMessage());
		} finally {
			session.close(); // 세션 종료
		}
	}

	// 마지막 Num 가져오기
	public int getLastUserNum() {
		SqlSession session = Config.getSession().openSession();
		Integer num = session.selectOne("getLastInsertuserNum");
		session.close();
		System.out.println("getLastUserNum()에서 가져온 num: " + num);
		return (int) num;
	}

	// 소셜 로그인 시 user 테이블에 추가 ( userNum , 닉네임만 넣는다 )
	public int insertUserTableBySocial(User user) {
		SqlSession session = Config.getSession().openSession();
		try {
			session.insert("insertUserNickname", user);
			session.commit(); // 변경사항 저장
			System.out.println("user table에 신규멤버 저장 완료");
			// 마지막으로 삽입된 user_num 반환
			return session.selectOne("getLastInsertuserNum");
		} catch (Exception e) {
			System.out.println("소셜 로그인 정보 삽입 중 오류 발생: " + e.getMessage());
			return -1; // 오류 발생 시 -1 반환
		} finally {
			session.close(); // 세션 종료
		}
	}

	public SocialDTO getSocialByEmail(String email) {
		SqlSession session = Config.getSession().openSession();
		try {
			return session.selectOne("getSocialByEmail", email);
		} finally {
			session.close();
		}
	}

	public User getUserByEmail(String email) {
		SqlSession session = Config.getSession().openSession();
		try {
			return session.selectOne("getUserByEmail", email);
		} finally {
			session.close();
		}
	}

	public void updateSocialInfo(SocialDTO socialDTO) {
		SqlSession session = Config.getSession().openSession();
		try {
			session.update("updateSocialInfo", socialDTO);
		} catch (Exception e) {
			System.out.println("소셜 정보 업데이트 중 오류 발생: " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void updateUser(User user) {
		SqlSession session = Config.getSession().openSession();
		try {
			session.update("updateUser", user);
			session.commit(); // 변경사항 저장
		} catch (Exception e) {
			System.out.println("사용자 정보 업데이트 중 오류 발생: " + e.getMessage());
		} finally {
			session.close(); // 세션 종료
		}
	}

	public User getUserById(int userNum) {
		SqlSession session = Config.getSession().openSession();
		try {
			return session.selectOne("getUserById", userNum);
		} finally {
			session.close();
		}
	}

	public Integer checkPw(String pw) {
		SqlSession session = Config.getSession().openSession();
		Integer num = session.selectOne("pwGetUserNum", pw);
		session.close();
		System.out.println("비밀번호 체크 num=" + num);
		return num;
	}

	public void delUserbyUserNum(int userNum) {
		SqlSession session = Config.getSession().openSession();
		session.selectOne("delUserbyUserNum", userNum);
		session.close();
		System.out.println("유저 삭제");
	}

	public void delSocialbyUserNum(int userNum) {
		SqlSession session = Config.getSession().openSession();
		session.selectOne("delSocialbyUserNum", userNum);
		session.close();
		System.out.println("소셜 삭제");
	}

	/*
	 * public boolean linkSocialAccount(int userNum, String platform, String email)
	 * { SqlSession session = Config.getSession().openSession(); boolean success =
	 * false;
	 * 
	 * try { // 1️ 기존 소셜 이메일이 이미 등록되어 있는지 확인 Map<String, Object> params = new
	 * HashMap<>(); params.put("email", email); int count =
	 * session.selectOne("checkExistingSocialEmail", params);
	 * 
	 * if (count > 0) { System.out.println("이미 연동된 소셜 계정입니다."); return false; }
	 * 
	 * // 2️ 기존 social 테이블에 user_num이 있는지 확인 params.put("userNum", userNum); int
	 * existingSocial = session.selectOne("checkExistingSocialByUserNum", userNum);
	 * 
	 * if (existingSocial > 0) { // 3️⃣ 기존 데이터가 있으면 업데이트 params.put("platform",
	 * platform); int updated = session.update("linkSocialAccount", params); if
	 * (updated > 0) { session.commit(); success = true;
	 * System.out.println("소셜 계정이 기존 유저 계정과 성공적으로 연동되었습니다."); } } else { // 4️ 기존
	 * 데이터가 없으면 새로 INSERT SocialDTO socialDTO = new SocialDTO();
	 * socialDTO.setUserNum(userNum); if ("kakao".equals(platform))
	 * socialDTO.setKakao(email); if ("naver".equals(platform))
	 * socialDTO.setNaver(email); if ("google".equals(platform))
	 * socialDTO.setGoogle(email);
	 * 
	 * int inserted = session.insert("insertSocialLink", socialDTO); if (inserted >
	 * 0) { session.commit(); success = true;
	 * System.out.println("새로운 소셜 계정이 추가되었습니다."); } } } catch (Exception e) {
	 * e.printStackTrace(); } finally { session.close(); }
	 * 
	 * return success; }
	 */
	public User getUserByNum(int userNum) {
		SqlSession session = Config.getSession().openSession();
		try {
			return session.selectOne("getUserByNum", userNum);
		} finally {
			session.close();
		}
	}

	public String findUserId(String name, String email) {
		SqlSession session = Config.getSession().openSession();
		User user = new User();
		user.setUserName(name);
		user.setUserEmail(email);

		String userId = session.selectOne("findUserId", user);
		session.close();
		return userId;
	}

	public boolean isSocialLinked(int userNum, String platform) {
		SqlSession session = Config.getSession().openSession();
		try {
			int count = session.selectOne("isSocialLinked", Map.of("userNum", userNum, "platform", platform));
			return count > 0;
		} finally {
			session.close();
		}
	}

	        String userId = session.selectOne("findUserId", user);
	        session.close();
	        return userId;
	    }

	    //아이디비밀번호찾기용 유저이메일로 UserNum 찾기
		public Integer checkEmail(String email) {
			SqlSession session = Config.getSession().openSession();
			Integer num= session.selectOne("findUserNumByEmail", email);
			session.close();
			System.out.println("유저 이메일 찾기");
			return num;
		}

		public int updatePwByUserNum(int userNum, String pw) {
			SqlSession session = Config.getSession().openSession();
			Map<String, Object> userMap = new HashMap<>();
			userMap.put("userNum", userNum);
			userMap.put("pw", pw);
			System.out.println(userMap);
			
			int cnt= session.update("updatePwByUserNum", userMap);
			session.commit();
			session.close();
			System.out.println("유저 PW 업데이트 완료");
			return cnt;
		}

	    
	    



	 
	public boolean linkSocialAccount(int userNum, String platform, String email) {
		SqlSession session = Config.getSession().openSession();
		try {
			int count = session.selectOne("isSocialLinked", Map.of("userNum", userNum, "platform", platform));
			if (count > 0) {
				return false; // 이미 연동된 경우
			}
			int rowsInserted = session.insert("linkSocialAccount",
					Map.of("userNum", userNum, "platform", platform, "email", email));
			session.commit();
			return rowsInserted > 0;
		} finally {
			session.close();
		}
	}

	public void unlinkSocialAccount(int userNum, String platform) {
		SqlSession session = Config.getSession().openSession();
		try {
			session.delete("unlinkSocialAccount", Map.of("userNum", userNum, "platform", platform));
			session.commit();
		} finally {
			session.close();
		}
	}

}
