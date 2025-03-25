package kr.smartReciFit.model.board;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public ArrayList<ReviewBoard> getTop3RecipesByLike() {
		ArrayList<ReviewBoard> list = new ArrayList<ReviewBoard>();
		try (SqlSession session = Config.getSession().openSession()) {
			list = (ArrayList) session.selectList("getTop3RecipesByLike");
		} catch (Exception e) {
			System.out.println("getTop3RecipesByLike() 에러");
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

	// 유저 이름 가져오기
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

	// 리뷰 추가
	public void addReview(ReviewBoard vo) {
		try (SqlSession session = Config.getSession().openSession()) {
			session.insert("addReview", vo);
			session.commit();
		} catch (Exception e) {
			System.out.println("addReview 에러");
			e.printStackTrace();
		}
	}

	 public void likeCount(int reviewBoardNum, int like) {
	        try (SqlSession session = Config.getSession().openSession()) {
	            Map<String, Object> map = new HashMap<>();
	            map.put("reviewBoardNum", reviewBoardNum);
	            map.put("like", like);
	            session.update("likeCount", map);
	            session.commit();
	        } catch (Exception e) {
	            System.out.println("likeCount() 에러");
	            e.printStackTrace();
	            throw e; 
	        }
	    }
	 
	 public void insertLike(int reviewBoardNum, int userNum) {
	        try (SqlSession session = Config.getSession().openSession()) {
	            Map<String, Object> map = new HashMap<>();
	            map.put("reviewBoardNum", reviewBoardNum);
	            map.put("userNum", userNum);
	            session.insert("insertLike", map);
	            session.commit();
	        } catch (Exception e) {
	            System.out.println("insertLike() 에러");
	            e.printStackTrace();
	        }
	    }
	 public void deleteLike(int reviewBoardNum, int userNum) {
	        try (SqlSession session = Config.getSession().openSession()) {
	            Map<String, Object> map = new HashMap<>();
	            map.put("reviewBoardNum", reviewBoardNum);
	            map.put("userNum", userNum);
	            session.delete("deleteLike", map);
	            session.commit();
	        } catch (Exception e) {
	            System.out.println("deleteLike() 에러");
	            e.printStackTrace();
	        }
	    }
	 
	 
	 public boolean isLiked(int reviewBoardNum, int userNum) {
		    SqlSession session = null;
		    try {
		        session = Config.getSession().openSession();
		        Map<String, Object> map = new HashMap<>();
		        map.put("reviewBoardNum", reviewBoardNum);
		        map.put("userNum", userNum);
		        Integer count = session.selectOne("isLiked", map);
		        return count != null && count > 0;  // 좋아요가 있으면 true 없으면 false
		    } catch (Exception e) {
		        System.out.println("isLiked() 에러");
		        e.printStackTrace();
		        return false;
		    } finally {
		        if (session != null) {
		            session.close();
		        }
		    }
		}
//	 public void updateLikeCount(int reviewBoardNum) {
//		    SqlSession session = null;
//		    try {
//		        session = Config.getSession().openSession();
//		        int totalLikes = getTotalLikes(reviewBoardNum); // likes 테이블에서 좋아요 수를 가져옴
//		        Map<String, Object> map = new HashMap<>();
//		        map.put("reviewBoardNum", reviewBoardNum);
//		        map.put("totalLikes", totalLikes);
//		        session.update("updateReviewBoardLikes", map);
//		        session.commit();
//		    } catch (Exception e) {
//		        System.out.println("updateLikeCount() 에러");
//		        e.printStackTrace();
//		    } finally {
//		        if (session != null) {
//		            session.close();
//		        }
//		    }
//		}
	 public void increaseLikeCount(int reviewBoardNum) {
	        try (SqlSession session = Config.getSession().openSession()) {
	            session.update("increaseLikeCount", reviewBoardNum);
	            session.commit();
	        } catch (Exception e) {
	            System.out.println("increaseLikeCount() 에러");
	            e.printStackTrace();
	        }
	    }

	    public void decreaseLikeCount(int reviewBoardNum) {
	        try (SqlSession session = Config.getSession().openSession()) {
	            session.update("decreaseLikeCount", reviewBoardNum);
	            session.commit();
	        } catch (Exception e) {
	            System.out.println("decreaseLikeCount() 에러");
	            e.printStackTrace();
	        }
	    }
	 
	  public Integer getTotalLikes(int reviewBoardNum) {
	        try (SqlSession session = Config.getSession().openSession()) {
	            return session.selectOne("getTotalLikes", reviewBoardNum);
	        } catch (Exception e) {
	            System.out.println("getTotalLikes() 에러");
	            e.printStackTrace();
	            return 0; 
	        }
	    }

	public List<HashMap<String, Object>> getReviewsByPage(int start, int end) {
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String, Object>>();
		try (SqlSession session = Config.getSession().openSession()){
			Map<String,Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			list = (ArrayList)session.selectList("getReviewsByPage", map);
		} catch (Exception e) {
			System.out.println("getReviewsByPage() 에러");
			e.printStackTrace();
		}
		return list;
	}
	
	public int updateReview(ReviewBoard vo) {
	    int cnt = 0;
	    try (SqlSession session = Config.getSession().openSession()) {
	        cnt = session.update("updateReview", vo);
	        session.commit();
	    } catch (Exception e) {
	        System.out.println("updateReview() 에러");
	        e.printStackTrace();
	    }
	    return cnt;
	}

	public void deleteReview(int reviewBoardNum) {
		SqlSession session = Config.getSession().openSession();
		try {
			 session.getConnection().setAutoCommit(false);
			 session.delete("deleteCommentsByBoardNum", reviewBoardNum);
			 session.delete("deleteLikesByReviewBoardNum", reviewBoardNum);
			session.delete("deleteReview",reviewBoardNum);
			session.commit();
		} catch (Exception e) {
			System.out.println("deleteReview() 에러");
			e.printStackTrace();
		}
		finally {
	        try {
	            session.getConnection().setAutoCommit(true);
	        } catch (Exception ignored) {} 
	        session.close();
	    }
	}

	public List<HashMap<String, Object>> searchReviewBoard(String searchName, String keyword) {
		List<HashMap<String,Object>> list = new ArrayList<>();
		 try (SqlSession session = Config.getSession().openSession()) {
	            Map<String, Object> map = new HashMap<>();
	            map.put("searchName", searchName);
	            map.put("keyword", keyword);
	            list = session.selectList("searchReviewBoard", map);
	        } catch (Exception e) {
	            System.out.println("searchReviewBoard() error");
	            e.printStackTrace();
	        }
	        return list;
	}
	 

}
