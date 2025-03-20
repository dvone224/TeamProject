package kr.smartReciFit.model.recipe.tags;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface KoreanNamedEnum {
	String getKoreanName();

	// 공통 캐시 저장소
	Map<Class<?>, Map<String, ?>> CACHE = new HashMap<>();

	// 공통 로직: 캐시 생성 및 저장
	@SuppressWarnings("unchecked")
	static <E extends Enum<E> & KoreanNamedEnum> Map<String, E> getOrCreateCache(Class<E> enumType) {
		// 이미 캐시가 존재하면 반환
		if (CACHE.containsKey(enumType)) {
			return (Map<String, E>) CACHE.get(enumType);
		}

		// 새 캐시 생성 및 저장
		Map<String, E> cache = new HashMap<>();
		for (E constant : enumType.getEnumConstants()) {
			cache.put(constant.getKoreanName(), constant);
		}
		CACHE.put(enumType, cache);
		return cache;
	}

	// 한글 이름으로 Enum 상수 조회
	static <E extends Enum<E> & KoreanNamedEnum> E getEnumByKoreanName(Class<E> enumType, String koreanName) {
		Map<String, E> cache = getOrCreateCache(enumType);
		return cache.get(koreanName);
	}

    static <E extends Enum<E> & KoreanNamedEnum> List<String> getAllKoreanNames(Class<E> enumType) {
        return Arrays.stream(enumType.getEnumConstants())
                .map(KoreanNamedEnum::getKoreanName)
                .collect(Collectors.toList());
    }
	
}
