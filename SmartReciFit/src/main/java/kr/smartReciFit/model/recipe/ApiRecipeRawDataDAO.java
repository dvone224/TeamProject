package kr.smartReciFit.model.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import kr.smartReciFit.model.recipe.tags.AllCookkingMethodTags;
import kr.smartReciFit.model.recipe.tags.AllIngredientTags;
import kr.smartReciFit.model.recipe.tags.CookingStyle;
import kr.smartReciFit.model.recipe.tags.EatTime;
import kr.smartReciFit.util.Config;

public class ApiRecipeRawDataDAO {

	public void refinerRecipeRawData(ArrayList<ApiRecipeRawData> list) {
		for (ApiRecipeRawData rawData : list) {
			int apiRecipeId = rawData.getApiRecipeId();
			int recipeNum = rawData.getRecipeNum();
			String recipeName = rawData.getRecipeName();
			String recipeIngredient = rawData.getRecipeIngredient();
			String recipeSeasoning = rawData.getRecipeSeasoning();
			String recipeManual = rawData.getRecipeManual();
			String tagEatTime = rawData.getTagEatTime();
			String tagCookingStyle = rawData.getTagCookingStyle();
			Set<String> tagIngredient = refinerJsonData(rawData.getTagIngredient(),
					AllIngredientTags.getInstance().getAllIngredientTags());
			Set<String> tagCookingMethod = refinerJsonData(rawData.getTagCookingMethod(),
					AllCookkingMethodTags.getInstance().getAllCookkingMethodTags());
			ApiRecipe apiRecipe = new ApiRecipe(apiRecipeId, recipeName, recipeIngredient, recipeSeasoning, recipeManual,
					tagCookingMethod, tagIngredient, EatTime.getEatTimeByName(tagEatTime),
					CookingStyle.getEatTimeByName(tagCookingStyle), null);
			insertApiRecipe(apiRecipe);
//			System.out.println(apiRecipe);
//			System.out.println();
		}
		System.out.println("done");
	}

	private Set<String> refinerJsonData(String data, Set<String> tags) {
		if (data == null)
			return null;
		String[] temp = data.substring(data.indexOf("\""), data.lastIndexOf("\"") + 1).split(",");
		Set<String> result = new HashSet<String>();

		for (String str : temp) {
			String elemental = str.trim().replace("\"", "");
			if (elemental.matches(".*[가-힣]+.*") && tags.contains(elemental)) {
				result.add(elemental);
			}

		}
		return result;
	}

	private ArrayList<ApiRecipeRawData> getApiRecipeRawDataList() {
		ArrayList<ApiRecipeRawData> list = new ArrayList<ApiRecipeRawData>();
		try (SqlSession session = Config.getSession().openSession(true);) {
			list = (ArrayList) session.selectList("getApiRecipeRawDataList");
		} catch (Exception e) {
			System.out.println("getApiRecipeRawDataList() 오류");
			e.printStackTrace();
		}
		return list;
	}
	
	private void insertApiRecipe(ApiRecipe apiRecipe) {
		try (SqlSession session = Config.getSession().openSession()){
			session.insert("insertRecipe", (Recipe) apiRecipe);
			session.insert("insertApiRecipe", apiRecipe);
			session.insert("insertTag", (Recipe) apiRecipe);
			session.commit();
		} catch (Exception e) {
			System.out.println("insertApiRecipe()오류");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ApiRecipeRawDataDAO dao = new ApiRecipeRawDataDAO();
		ArrayList<ApiRecipeRawData> rawDataList = dao.getApiRecipeRawDataList();
		dao.refinerRecipeRawData(rawDataList);
	}

}
