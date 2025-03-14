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

//	public List<ReviewBoard> getTop3RecipesByViews() {
//		List<ReviewBoard> list = new ArrayList<>();
//		try (SqlSession session = Config.getSession().openSession()) {
//			list = session.selectList("getTop3RecipesByViews");
//		} catch (Exception e) {
//			System.out.println("getTop3RecipesByViews() 에러");
//			e.printStackTrace();
//		}
//		return list; 
//	} 일단 오류나서 보류

}
