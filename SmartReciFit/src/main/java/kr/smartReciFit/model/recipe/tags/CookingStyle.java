package kr.smartReciFit.model.recipe.tags;

public enum CookingStyle implements KoreanNamedEnum {
	WESTERN("양식"), KOREAN("한식"), CHINESE("중식"), JAPANESE("일식"), ASIAN("아시안식"), OTHER("기타");

	private final String koreanName;

	CookingStyle(String koreanName) {
		this.koreanName = koreanName;
	}

	@Override
	public String getKoreanName() {
		return koreanName;
	}
}
