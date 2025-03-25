package kr.smartReciFit.model.recipe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

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
		server.reconnectSocket();
		try (BufferedReader br = new BufferedReader(server.getReader());
				BufferedWriter bw = new BufferedWriter(server.getWriter())) {

			bw.write(URL); // 데이터 전송
			bw.newLine(); // 줄바꿈 문자를 추가하여 데이터의 끝을 표시
			bw.flush();

			String line = "";
			while ((line = br.readLine()) != null) {
				message += line + "\n";
			}

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
	   public ArrayList<HashMap<String,Object>> searchRecipes(String keyword) {
		   ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
	        try (SqlSession session = Config.getSession().openSession()) {
	           list = (ArrayList)session.selectList("searchRecipes", keyword);
	           System.out.println("검색결과" + list);
	           return list;
	        } catch (Exception e) {
	            System.out.println("searchRecipes 에러");
	            e.printStackTrace();
	            return null;
	        }
	    }
		//apiRecipeNum을 이용해서 레시피 정보 가져오기(명보)
		 public Recipe getRecipeByNum(int recipeNum) {
	         Recipe recipe = null;
	         System.out.println("recipeNum = " + recipeNum);
	         try (SqlSession session = Config.getSession().openSession()) {
	             recipe = session.selectOne("getRecipeByNum", recipeNum);
	             System.out.println("값 뭐야"+recipe);
	         } catch (Exception e) {
	             System.out.println("getRecipeByNum() 에러");
	             e.printStackTrace();
	         }
	         return recipe;
	     }
	   
	
}
