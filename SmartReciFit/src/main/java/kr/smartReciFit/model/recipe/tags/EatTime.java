package kr.smartReciFit.model.recipe.tags;

public enum EatTime implements KoreanNamedEnum {
	BREAKFAST("아침"), LUNCH("점심"), DINNER("저녁"), LATE_NIGHT("야식"), SNACK("간식"), SIDEDISH("반찬"), OTHER("기타");

	private final String koreanName;

	EatTime(String koreanName) {
		this.koreanName = koreanName;
	}

	@Override
	public String getKoreanName() {
		return koreanName;
	}

}