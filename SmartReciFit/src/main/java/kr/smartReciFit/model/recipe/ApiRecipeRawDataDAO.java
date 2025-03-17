package kr.smartReciFit.model.recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import kr.smartReciFit.model.recipe.tags.AllIngredientTags;
import kr.smartReciFit.util.Config;

public class ApiRecipeRawDataDAO {

	public void refinerRecipeRawData(ArrayList<ApiRecipeRawData> list) {
		for (ApiRecipeRawData rawData : list) {
			int apiRecipeId = rawData.getApiRecipeId();
			int recipeNum = rawData.getRecipeNum();
			String recipeName = rawData.getRecipeName();
			// split nullPoint 오류 해결 해야함
//			String[] recipeIngredient = rawData.getRecipeIngredient().split("|");
//			String[] recipeSeasoning = rawData.getRecipeSeasoning().split("|");
//			String[] recipeManual = rawData.getRecipeManual().split("|");
			String tagEatTime = rawData.getTagEatTime();
			Set<String> tagIngredient = refinerJson(rawData.getTagIngredient(),
					AllIngredientTags.getInstance().getAllIngredientTags());
			String tagCookingStyle = rawData.getTagCookingStyle();
//			Set<String> tagCookingMethod = refinerJson(rawData.getTagCookingMethod());
			System.out.println("apiRecipeId = " + apiRecipeId);
			System.out.println("tagIngredient = " + tagIngredient);
//			System.out.println("tagCookingMethod = " + tagCookingMethod);
			System.out.println();

		}
	}

	private Set<String> refinerJson(String json, Set<String> tagElementals) {
		if (json == null)
			return null;
		json = json.replace(":", ",").replace("|", ",");

		String[] temp = json.substring(json.indexOf("\""), json.lastIndexOf("\"") + 1).split(",");
		Set<String> result = new HashSet<String>();

		for (String str : temp) {
			String elemental = str.trim().replace("\"", "");
			if (tagElementals.contains(elemental))
				result.add(elemental);
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

	public static void main(String[] args) {
		ApiRecipeRawDataDAO dao = new ApiRecipeRawDataDAO();
		ArrayList<ApiRecipeRawData> rawDataList = dao.getApiRecipeRawDataList();
		dao.refinerRecipeRawData(rawDataList);
	}

}
