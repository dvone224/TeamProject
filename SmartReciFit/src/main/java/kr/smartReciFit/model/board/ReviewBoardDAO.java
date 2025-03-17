package kr.smartReciFit.model.board;

import java.util.ArrayList;
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
//	public List<ReviewBoard> getTop3RecipesByViews() {
//		List<ReviewBoard> list = new ArrayList<>();
//		try (SqlSession session = Config.getSession().openSession()) {
//			list = session.selectList("getTop3RecipesByViews");
//		} catch (Exception e) {
//			System.out.println("getTop3RecipesByViews() 에러");
//			e.printStackTrace();
//		}
//		return list; 
//	} 일단 오류나서 보류 DAO 따로 만들어야함
	
	//리뷰 관련
	
	// 모든 후기 가져오기
	public List<ReviewBoard> getAllReviews(){
		List<ReviewBoard> list = new ArrayList<>();
		try (SqlSession session = Config.getSession().openSession()) {
			list = session.selectList("getAllReviews");
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
			review = session.selectOne("getReviewById",reviewBoardNum);
		} catch (Exception e) {
			System.out.println("getReviewById() 에러");
			e.printStackTrace();
		}
		return review;
	}
	
	// 조회수 증가하는거
	public void viewCount(int reviewBoardNum) {
		try (SqlSession session = Config.getSession().openSession()) {
			session.update("viewCount",reviewBoardNum);
			session.commit();
		} catch (Exception e) {
			System.out.println("viewCount() 에러");
			e.printStackTrace();
		}
	}
	
	
}
