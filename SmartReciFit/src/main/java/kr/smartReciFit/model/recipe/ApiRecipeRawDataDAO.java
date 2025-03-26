package kr.smartReciFit.model.recipe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

	private void updateRecipeImgs() {
		try {
			URL url = new URL("http://openapi.foodsafetykorea.go.kr/api/19d26f44e8d0469d9269/COOKRCP01/json/1/1000");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");

			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuilder response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				String jsonString = response.toString();
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
				
				// JSON 데이터 추출
				JSONObject cookRcp01 = (JSONObject) jsonObject.get("COOKRCP01");
				JSONArray row = (JSONArray) cookRcp01.get("row");
				for (Object item : row) {
					HashMap<String, Object> parameter = new HashMap<String, Object>(); 
					JSONObject recipe = (JSONObject) item;
					String recipeThumbnail = (String) recipe.get("ATT_FILE_NO_MAIN");
					int apiRecipeNum = Integer.parseInt((String) recipe.get("RCP_SEQ"));
					String recipeName = (String) recipe.get("RCP_NM");
					String allRecipeImg = "";
					for(int i = 1; i<=20;i++) {
						String recipeImg = (String) recipe.get(String.format("MANUAL_IMG%02d", i));
						if(recipeImg.length() == 0) {
							break;
						}
						allRecipeImg += recipeImg + "|";
					}
					allRecipeImg = allRecipeImg.length() > 1 ? allRecipeImg.substring(0,allRecipeImg.length()-1) : allRecipeImg;
					
					parameter.put("thumbnail", recipeThumbnail);
					parameter.put("apiRecipeNum", apiRecipeNum);
					parameter.put("recipeName", recipeName);
					parameter.put("apiRecipeImg", allRecipeImg);
					updateThumbnail(parameter);
					updateImg(parameter);
				}
				System.out.println("done");
			} else {
				System.out.println("Failed to retrieve data");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void updateThumbnail(HashMap<String, Object> parameter) {
		try (SqlSession session = Config.getSession().openSession()){
			session.update("updateThumbnail",parameter);
			session.commit();
		} catch (Exception e) {
			System.out.println("updateThumbnail() 오류");
			e.printStackTrace();
		}
	}
	
	private void updateImg(HashMap<String, Object> parameter) {
		try (SqlSession session = Config.getSession().openSession()){
			session.update("updateImg",parameter);
			session.commit();
		} catch (Exception e) {
			System.out.println("updateImg() 오류");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ApiRecipeRawDataDAO dao = new ApiRecipeRawDataDAO();
		ArrayList<ApiRecipeRawData> rawDataList = dao.getApiRecipeRawDataList();
		dao.refinerRecipeRawData(rawDataList);
		dao.updateRecipeImgs();
//		dao.printInsertData();
		

	}

}
