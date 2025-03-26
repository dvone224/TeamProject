package kr.smartReciFit.controller.board;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.Event;
import kr.smartReciFit.model.board.EventDAO;

public class EventDetailController implements Controller {

    @Override
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String eventIdStr = request.getParameter("id");
    	int eventId = 0;
    	
    	if(eventIdStr == null || eventIdStr.trim().isEmpty()) {
    		System.out.println("Event Id가 없음");
    		
    	}else {
            try {
                eventId = Integer.parseInt(eventIdStr);
            } catch (NumberFormatException e) {
                System.err.println("잘못된 Event ID 형식입니다: " + eventIdStr);
            }
    	}
    	
    	if(eventId > 0) {
    		EventDAO dao = EventDAO.getInstance();
    		Event event = dao.getEventById(eventId);
    		
    		if(event != null) {
    			request.setAttribute("event", event);
    			System.out.println("startDate = "+event.getStartDate());
    			System.out.println("endDate = "+event.getEndDate());
    		}else {
                System.out.println("ID가 " + eventId + "인 이벤트를 찾을 수 없습니다.");
            }
    	}

        return "eventDetail";
    }
}