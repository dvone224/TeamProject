package kr.smartReciFit.model.recipe.tags;

import java.util.HashMap;

public enum CookingStyle {
	WESTERN("양식"), KOREAN("한식"), CHINESE("중식"), JAPANESE("일식"), ASIAN("아시안식"), OTHER("기타");

	private final String koreanName;

	CookingStyle(String koreanName) {
		this.koreanName = koreanName;
	}

	public String getKoreanName() {
		return koreanName;
	}

	private static final HashMap<String, CookingStyle> cacheByName = new HashMap<>();

	static {
		for (CookingStyle cookingStyle : values()) {
			cacheByName.put(cookingStyle.getKoreanName(), cookingStyle);
		}
	}

	public static CookingStyle getEatTimeByName(String name) {
		return cacheByName.get(name);
	}
}
