package kr.smartReciFit.model.recipe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set; 

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.smartReciFit.soketServer.JavaServer;
import kr.smartReciFit.util.Config;

public class RecipeDAO {
	private static RecipeDAO instance;

	private RecipeDAO() {
	}

	public static RecipeDAO getInstance() {
		if (instance == null)
			instance = new RecipeDAO();
		return instance;
	}

	public ArrayList<ApiRecipe> getApiRecipeListByLimit() {
		ArrayList<ApiRecipe> list = new ArrayList<ApiRecipe>();
		try (SqlSession sessoin = Config.getSession().openSession()) {
			list = (ArrayList) sessoin.selectList("getApiRecipeListByLimit", 10);
		} catch (Exception e) {
			System.out.println("getApiRecipeListByLimit() 오류");
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Recipe> getRecipeByFilter(HashMap<String, ArrayList<String>> parameter) {
		ArrayList<Recipe> list = new ArrayList<Recipe>();
		try (SqlSession sessoin = Config.getSession().openSession()) {
			list = (ArrayList) sessoin.selectList("getRecipeListByFilter", parameter);
		} catch (Exception e) {
			System.out.println("getRecipeByFilter() 오류");
			e.printStackTrace();
		}
		return list;
	}

	public String getRecipe(String URL) {
		String message = "";
		JavaServer server = JavaServer.getInstance();
		server.startServer("PythonClient");
		try (BufferedReader br = new BufferedReader(server.getReader());
				BufferedWriter bw = new BufferedWriter(server.getWriter())) {

			bw.write(URL); // 데이터 전송
			bw.newLine(); // 줄바꿈 문자를 추가하여 데이터의 끝을 표시
			bw.flush();

			String line = "";
			while ((line = br.readLine()) != null) {
				message += line + "\n";
			}
			server.closeSocket();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());

		}
		return message;
	}
	
	public String getRecipeConverter(String json) {
		String message = "";
		JavaServer server = JavaServer.getInstance();
		server.startServer("RecipeConverter");
		try (BufferedReader br = new BufferedReader(server.getReader());
				BufferedWriter bw = new BufferedWriter(server.getWriter())) {

			bw.write(json); // 데이터 전송
			bw.newLine(); // 줄바꿈 문자를 추가하여 데이터의 끝을 표시
			bw.flush();

			String line = "";
			while ((line = br.readLine()) != null) {
				message += line + "\n";
			}
			server.closeSocket();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());

		}
		return message;
	}

	public ApiRecipe getRecipeByRecipeNum(int recipeNum) {
		ApiRecipe apiRecipe = null;
		try (SqlSession session = Config.getSession().openSession()) {
			apiRecipe = session.selectOne("getRecipeByRecipeNum", recipeNum);
		} catch (Exception e) {
			System.out.println("getRecipeByRecipeNum() 오류");
			e.printStackTrace();
		}
		return apiRecipe;
	}

	// 후기글 작성중 레시피 검색 (명보)
	public ArrayList<HashMap<String, Object>> searchRecipes(String keyword) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try (SqlSession session = Config.getSession().openSession()) {
			list = (ArrayList) session.selectList("searchRecipes", keyword);
			System.out.println("검색결과" + list);
			return list;
		} catch (Exception e) {
			System.out.println("searchRecipes 에러");
			e.printStackTrace();
			return null;
		}
	}

	// apiRecipeNum을 이용해서 레시피 정보 가져오기(명보)
	public Recipe getRecipeByNum(int recipeNum) {
		Recipe recipe = null;
		System.out.println("recipeNum = " + recipeNum);
		try (SqlSession session = Config.getSession().openSession()) {
			recipe = session.selectOne("getRecipeByNum", recipeNum);
			System.out.println("값 뭐야" + recipe);
		} catch (Exception e) {
			System.out.println("getRecipeByNum() 에러");
			e.printStackTrace();
		}
		return recipe;
	}

	public String getVideoId(String url) {
		URI uri = null;
		String videoId = null;
		try {
			uri = new URI(url);
			if (uri.getHost().equals("youtu.be")) {
				String[] pathParts = uri.getPath().split("\\?");
				videoId = pathParts[0].replaceFirst("/", "");
			} else if (uri.getQuery() != null && uri.getQuery().contains("v=")) {
				// v= 형식의 URL에서 비디오 ID 추출
				String[] params = uri.getQuery().split("&");
				for (String param : params) {
					if (param.startsWith("v=")) {
						videoId = URLDecoder.decode(param.substring(2), "UTF-8");
					}
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return videoId;
	}
	
	public ArrayList<Integer> getRecipeTimeStamp(String rawData) {
		ArrayList<Integer> timeStamp = new ArrayList<Integer>();
		String[] temp = rawData.split("\\|");
		for(String str : temp) {
			String[] splitTemp = str.split(":");
			int time = Integer.parseInt(splitTemp[0]) * 60 + Integer.parseInt(splitTemp[1]);
			timeStamp.add(time);
		}
		return timeStamp;
	}
	
	public void insertAiRecipe(AiRecipe aiRecipe) {
		try (SqlSession session = Config.getSession().openSession()) {
			session.insert("insertRecipe", (Recipe) aiRecipe);
			int lastId = session.selectOne("getLastInsertedId");
			aiRecipe.setRecipeNum(lastId);
			session.insert("insertAiRecipe", aiRecipe);
			session.insert("insertTag", (Recipe) aiRecipe);
			session.commit();
		} catch (Exception e) {
			System.out.println("insertAiRecipe()오류");
			e.printStackTrace();
		}
	}
	
	public AiRecipe getAiRecipeByUrl(String url) {
		AiRecipe aiRecipe = null;
		try (SqlSession session = Config.getSession().openSession()) {
			aiRecipe = session.selectOne("getAiRecipeByUrl",url);
		} catch (Exception e) {
			System.out.println("getAiRecipeByUrl()오류");
			e.printStackTrace();
		}
		return aiRecipe;
	}
	
	public int getRecipeCount() {
		int count = 0;
		try (SqlSession session = Config.getSession().openSession()){
			count = session.selectOne("getRecipeCount");
		} catch (Exception e) {
			System.out.println("getRecipeCount() 오류");
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<Recipe> getRecipeByStartEnd(int start, int limit) {
		HashMap<String, Integer> parameter = new HashMap<String, Integer>();
		parameter.put("start", start-1);
		parameter.put("limit", limit);
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
		try (SqlSession session = Config.getSession().openSession()){
			recipeList = (ArrayList)session.selectList("getRecipeByStartEnd", parameter);
		} catch (Exception e) {
			System.out.println("getRecipeByStartEnd() 오류");
			e.printStackTrace();
		}
		return recipeList;
	}

	public void deleteRecipeByRecipeNum(int recipeNum) {
		try (SqlSession session = Config.getSession().openSession()){
			session.delete("deleteRecipeByRecipeNum", recipeNum);
			session.commit();
		} catch (Exception e) {
			System.out.println("deleteRecipeByRecipeNum() 오류");
			e.printStackTrace();
		}
		
	}

	//명보가 추가함 레시피등록할때 밑에까지====================================================== 
	public boolean insertUserRecipeData(Recipe recipe, int userNum, String userRecipeImg) {
		 boolean success = false;
		try (SqlSession session = Config.getSession().openSession(false)){
			int insertedRowsRecipe = session.insert("insertRecipe", recipe);
			 if (insertedRowsRecipe == 0) {
	                throw new Exception("Recipe 테이블 삽입 실패 (0 rows affected)");
	         }
			 int lastId = session.selectOne("getLastInsertedId");
	            recipe.setRecipeNum(lastId);
	            int insertedRowsTag = session.insert("insertTag", recipe);
	            if (insertedRowsTag == 0) {
	                 throw new Exception("Tag 테이블 삽입 실패 (0 rows affected)");
	             }
	            Map<String, Object> userRecipeParams = new HashMap<>();
	            userRecipeParams.put("recipeNum", lastId); 
	            userRecipeParams.put("userNum", userNum); 
	            userRecipeParams.put("userRecipeImg", userRecipeImg); 
	            int insertedRowsUserRecipe = session.insert("insertUserRecipe", userRecipeParams);
	            if (insertedRowsUserRecipe == 0) {
	                throw new Exception("User Recipe 테이블 삽입 실패 (0 rows affected)");
	            }
	            session.commit();
	            success = true; 
	            System.out.println("사용자 레시피 저장 성공 (Recipe Num: " + lastId + ")");
		} catch (Exception e) {
				System.out.println("insertUserRecipeDate() 오류");
				e.printStackTrace();
		} 
		return success;
	}
	
	 private Map<String, Object> createTagParams(Recipe recipe) {
		 	final Gson gson = new Gson();
	        Map<String, Object> tagParams = new HashMap<>();
	        tagParams.put("recipeNum", recipe.getRecipeNum()); 

	        tagParams.put("recipeType", recipe.getRecipeType()); 
	        tagParams.put("eatTime", recipe.getEatTime());       
	        tagParams.put("cookingStyle", recipe.getCookingStyle()); 

	        tagParams.put("ingredientsJson", recipe.getIngredients() != null ? gson.toJson(recipe.getIngredients()) : "[]");
	        tagParams.put("cookingMethodsJson", recipe.getCookingMethods() != null ? gson.toJson(recipe.getCookingMethods()) : "[]");

	        return tagParams; // 생성된 Map 반환
	    }
	
}
