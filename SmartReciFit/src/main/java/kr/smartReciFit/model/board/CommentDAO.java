package kr.smartReciFit.model.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.smartReciFit.util.Config;

public class CommentDAO {
	
	private CommentDAO() {
		
	}
	
	private static CommentDAO instance;
	
	public static CommentDAO getInstance() {
		if(instance == null) instance = new CommentDAO();
		return instance;
	}
	
	public void addComment(Comment comment) {
		try (SqlSession session = Config.getSession().openSession()) {
			session.insert("addComment",comment);
			session.commit();
		} catch (Exception e) {
			System.out.println("addComment() 에러");
			e.printStackTrace();
		}
	}
	
	public List<Comment> getCommentsByBoardNum(int boardNum) {
		System.out.println("들어옴?");
		ArrayList<Comment> list = new ArrayList<Comment>();
        try (SqlSession session = Config.getSession().openSession()) {
            list = (ArrayList)session.selectList("getCommentsByBoardNum", boardNum);
        }catch(Exception e) {
        	System.out.println("getCommentsByBoardNum() 에러");
        }
        return list;
        
    }
	
	public void updateComment(Comment comment) {
		try (SqlSession session = Config.getSession().openSession()) {
			session.update("updateComment",comment);
			session.commit();
        }catch(Exception e) {
        	System.out.println("updateComment() 에러");
        }
	}

	public void deleteComment(int commentNum, int boardNum) {
		try (SqlSession session = Config.getSession().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("commentNum",commentNum);
			map.put("boardNum",boardNum);
			session.delete("deleteComment",map);
			session.commit();
        }catch(Exception e) {
        	System.out.println("updateComment() 에러");
        }
	}
		
	}
	
	

