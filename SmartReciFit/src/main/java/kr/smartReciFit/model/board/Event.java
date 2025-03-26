package kr.smartReciFit.model.board;

import java.sql.Date;

public class Event {
	private int eventId; 
    private String eventTitle; 
    private String eventImg; 
    private String introductionText; 
    private Date startDate; 
    private Date endDate; 
    private String selectionCriteria; 
    private String winnerCount; 
    private String guidelines;
    
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventImg() {
		return eventImg;
	}
	public void setEventImg(String eventImg) {
		this.eventImg = eventImg;
	}
	public String getIntroductionText() {
		return introductionText;
	}
	public void setIntroductionText(String introductionText) {
		this.introductionText = introductionText;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSelectionCriteria() {
		return selectionCriteria;
	}
	public void setSelectionCriteria(String selectionCriteria) {
		this.selectionCriteria = selectionCriteria;
	}
	public String getWinnerCount() {
		return winnerCount;
	}
	public void setWinnerCount(String winnerCount) {
		this.winnerCount = winnerCount;
	}
	public String getGuidelines() {
		return guidelines;
	}
	public void setGuidelines(String guidelines) {
		this.guidelines = guidelines;
	} 
    
    

}
