package kr.smartReciFit.model.user;
// 고유넘버 userNum , platform이 뭔지
public class SocialDTO {
	private int userNum;
	private String kakao;
	private String naver;
	private String google;
	
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getKakao() {
		return kakao;
	}
	public void setKakao(String kakao) {
		this.kakao = kakao;
	}
	@Override
	public String toString() {
		return "SocialDTO [userNum=" + userNum + ", kakao=" + kakao + ", naver=" + naver + ", google=" + google + "]";
	}
	public String getNaver() {
		return naver;
	}
	public void setNaver(String naver) {
		this.naver = naver;
	}
	public String getGoogle() {
		return google;
	}
	public void setGoogle(String google) {
		this.google = google;
	}
	
	
}
