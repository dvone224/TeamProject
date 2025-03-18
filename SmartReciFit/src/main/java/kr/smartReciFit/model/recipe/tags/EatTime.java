package kr.smartReciFit.model.recipe.tags;

import java.util.HashMap;

public enum EatTime {
	BREAKFAST("아침"), LUNCH("점심"), DINNER("저녁"), LATE_NIGHT("야식"), SNACK("간식"), SIDEDISH("반찬"), OTHER("기타");

	private final String koreanName;

	EatTime(String koreanName) {
		this.koreanName = koreanName;
	}

	public String getKoreanName() {
		return koreanName;
	}

	private static final HashMap<String, EatTime> cacheByName = new HashMap<>();

	static {
		for (EatTime eatTime : values()) {
			cacheByName.put(eatTime.getKoreanName(), eatTime);
		}
	}

	public static EatTime getEatTimeByName(String name) {
		return cacheByName.get(name);
	}
}