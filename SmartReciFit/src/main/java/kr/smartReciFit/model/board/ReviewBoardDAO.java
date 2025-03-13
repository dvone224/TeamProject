package kr.smartReciFit.model.board;

public class ReviewBoardDAO {
	private ReviewBoardDAO() {
		
	}
	
	private static ReviewBoardDAO instance;
	
	public static ReviewBoardDAO getInstance() {
		if(instance == null) instance = new ReviewBoardDAO();
		return instance;
	}
}
