package kr.smartReciFit.model.recipe.tags;

import java.util.HashSet;
import java.util.Set;

public class AllCookkingMethodTags {
	private Set<String> allCookkingMethodTags;

	private static AllCookkingMethodTags instance;

	private AllCookkingMethodTags() {
		String[] cookkingMethods = { "튀김","찜","탕","구이","볶음","조림","샐러드","기타" };
		allCookkingMethodTags = new HashSet<>();
		for (String cookkingMethod : cookkingMethods) {
			allCookkingMethodTags.add(cookkingMethod);
		}
	}

	public static AllCookkingMethodTags getInstance() {
		if (instance == null)
			instance = new AllCookkingMethodTags();
		return instance;
	}

	public Set<String> getAllCookkingMethodTags() {
		return allCookkingMethodTags;
	}
}
