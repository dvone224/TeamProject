package kr.smartReciFit.model.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.smartReciFit.util.Config;

public class EventDAO {
	
	private EventDAO() {
		
	}
	
	private static EventDAO instance;
	
	public static EventDAO getInstance() {
		if(instance == null) instance = new EventDAO();
		return instance;
	}
	
	public Event getEventById(int eventId) {
		Event event = null;
		try (SqlSession session = Config.getSession().openSession()){
			event = session.selectOne("getEventById",eventId);
			 System.out.println("DAO - StartDate: " + event.getStartDate()); // 날짜 값 확인
			 System.out.println("DAO - EndDate: " + event.getEndDate());
			
		} catch (Exception e) {
			System.out.println("getEventById() 에러");
			e.printStackTrace();
		}
		
		return event;
	}
	
		
	}
	
	

