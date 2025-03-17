package kr.smartReciFit.model.recipe.tags;

import java.util.HashSet;
import java.util.Set;

public class AllIngredientTags {
	private Set<String> allIngredientTags;
	
	private static AllIngredientTags instance;
	
	private AllIngredientTags() {
		String[] ingredients = {
	            "육류", "해산물", "채소", "곡류", "소고기", "돼지고기", 
	            "양고기", "닭고기", "생선", "새우", "게", "조개", 
	            "오징어", "유제품", "가공식품", "기타"
	        };
	        allIngredientTags = new HashSet<>();
	        for (String ingredient : ingredients) {
	        	allIngredientTags.add(ingredient);
	        }
	}
	
	public static AllIngredientTags getInstance() {
		if(instance == null) instance = new AllIngredientTags();
		return instance;
	}
	
	public Set<String> getAllIngredientTags() {
		return allIngredientTags;
	}
}
