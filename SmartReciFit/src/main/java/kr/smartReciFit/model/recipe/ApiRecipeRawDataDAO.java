package kr.smartReciFit.model.recipe;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import kr.smartReciFit.model.recipe.tags.AllCookkingMethodTags;
import kr.smartReciFit.model.recipe.tags.AllIngredientTags;
import kr.smartReciFit.model.recipe.tags.CookingStyle;
import kr.smartReciFit.model.recipe.tags.EatTime;
import kr.smartReciFit.model.recipe.tags.KoreanNamedEnum;
import kr.smartReciFit.model.recipe.tags.RecipeType;
import kr.smartReciFit.util.Config;

public class ApiRecipeRawDataDAO {

	public void refinerRecipeRawData(ArrayList<ApiRecipeRawData> list) {
		for (ApiRecipeRawData rawData : list) {
			int recipeNum = rawData.getRecipeNum();
			int apiRecipeNum = rawData.getApiRecipeNum();
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
			ApiRecipe apiRecipe = new ApiRecipe(recipeNum, apiRecipeNum, recipeName, RecipeType.valueOf("API"),
					recipeIngredient, recipeSeasoning, recipeManual, tagCookingMethod, tagIngredient,
					KoreanNamedEnum.getEnumByKoreanName(EatTime.class, tagEatTime),
					KoreanNamedEnum.getEnumByKoreanName(CookingStyle.class, tagCookingStyle), null);
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
		try (SqlSession session = Config.getSession().openSession()) {
			session.insert("insertRecipe", (Recipe) apiRecipe);
			session.insert("insertApiRecipe", apiRecipe);
			session.insert("insertTag", (Recipe) apiRecipe);
			session.commit();
		} catch (Exception e) {
			System.out.println("insertApiRecipe()오류");
			e.printStackTrace();
		}
	}

	private void printInsertData() {
		Field[] fields = ApiRecipeRawData.class.getDeclaredFields();
	    ArrayList<ApiRecipeRawData> list = getApiRecipeRawDataList();
	    
	    // 텍스트 파일에 쓰기
	    try (PrintWriter writer = new PrintWriter("insert_data.sql")) {
	        for (ApiRecipeRawData data : list) {
	            String printStr = "INSERT INTO recipe_raw_data VALUES(";
	            for (Field field : fields) {
	                try {
	                    field.setAccessible(true);
	                    Object fieldValue = field.get(data);
	                    if (fieldValue instanceof String) {
	                        printStr += "'" + fieldValue + "', ";
	                    } else {
	                        printStr += fieldValue + ", ";
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            printStr = printStr.substring(0, printStr.length() - 2) + ");";
	            writer.println(printStr);
	        }
	    } catch (FileNotFoundException e) {
	        System.err.println("파일을 찾을 수 없습니다.");
	    }
	}

	public static void main(String[] args) {
		ApiRecipeRawDataDAO dao = new ApiRecipeRawDataDAO();
		ArrayList<ApiRecipeRawData> rawDataList = dao.getApiRecipeRawDataList();
		dao.refinerRecipeRawData(rawDataList);
//		dao.printInsertData();

	}

}
