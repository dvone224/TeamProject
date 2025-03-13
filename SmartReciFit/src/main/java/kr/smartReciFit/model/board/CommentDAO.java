package kr.smartReciFit.model.board;

public class CommentDAO {
	
	private CommentDAO() {
		
	}
	
	private static CommentDAO instance;
	
	public static CommentDAO getInstance() {
		if(instance == null) instance = new CommentDAO();
		return instance;
	}
	
}
