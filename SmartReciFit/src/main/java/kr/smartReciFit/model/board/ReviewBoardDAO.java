package kr.smartReciFit.model.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.smartReciFit.util.Config;

public class ReviewBoardDAO {
	private ReviewBoardDAO() {

	}

	private static ReviewBoardDAO instance;

	public static ReviewBoardDAO getInstance() {
		if (instance == null)
			instance = new ReviewBoardDAO();
		return instance;
	}

	// 랭킹 부분
	public ArrayList<ReviewBoard> getTop3RecipesByViews() {
		ArrayList<ReviewBoard> list = new ArrayList<ReviewBoard>();
		try (SqlSession session = Config.getSession().openSession()) {
			list = (ArrayList) session.selectList("getTop3RecipesByViews");
		} catch (Exception e) {
			System.out.println("getTop3RecipesByViews() 에러");
			e.printStackTrace();
		}
		return list;
	}

	// 리뷰 관련

	// 모든 후기 가져오기
	public ArrayList<ReviewBoard> getAllReviews() {

		ArrayList<ReviewBoard> list = new ArrayList<ReviewBoard>();
		try (SqlSession session = Config.getSession().openSession()) {
			list = (ArrayList) session.selectList("getAllReviews");
		} catch (Exception e) {
			System.out.println("getAllReviews() 에러");
			e.printStackTrace();
		}
		return list;
	}
	
	// 리뷰 게시판 후기 가져올때 유저 네임도 가져오기위해 씀
	public ArrayList<HashMap<String, Object>> getAllReviewsAndUser() {

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try (SqlSession session = Config.getSession().openSession()) {
			list = (ArrayList) session.selectList("getAllReviewsAndUser");
		} catch (Exception e) {
			System.out.println("getAllReviews() 에러");
			e.printStackTrace();
		}
		return list;
	}

	// 특정 후기글 가져오기
	public ReviewBoard getReviewById(int reviewBoardNum) {
		ReviewBoard review = null;
		try (SqlSession session = Config.getSession().openSession()) {
			review = session.selectOne("getReviewById", reviewBoardNum);
		} catch (Exception e) {
			System.out.println("getReviewById() 에러");
			e.printStackTrace();
		}
		return review;
	}
	// 조회수 증가하는거

	public void viewCount(int reviewBoardNum) {
		try (SqlSession session = Config.getSession().openSession()) {
			session.update("viewCount", reviewBoardNum);
			session.commit();
		} catch (Exception e) {
			System.out.println("viewCount() 에러");
			e.printStackTrace();
		}
	}

	public String getUserName() {
		String name = null;
		try (SqlSession session = Config.getSession().openSession()) {
			name = session.selectOne("getUserName");
		} catch (Exception e) {
			System.out.println("getUserName 에러");
			e.printStackTrace();
		}
		return name;
	}

	// 유저 이름 가져오기

}
