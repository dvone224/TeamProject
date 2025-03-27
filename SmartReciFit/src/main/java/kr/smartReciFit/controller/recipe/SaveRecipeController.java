package kr.smartReciFit.controller.recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays; // String 배열 -> Set 변환 시 사용
import java.util.Collection;
import java.util.HashSet; // Set 구현체로 사용
import java.util.List;
import java.util.Set; // Set 인터페이스 사용
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.recipe.Recipe; // Recipe VO 임포트
import kr.smartReciFit.model.recipe.RecipeDAO; // Recipe DAO 임포트
import kr.smartReciFit.model.recipe.tags.CookingStyle; // Enum 임포트
import kr.smartReciFit.model.recipe.tags.EatTime; // Enum 임포트
import kr.smartReciFit.model.recipe.tags.KoreanNamedEnum;
import kr.smartReciFit.model.recipe.tags.RecipeType; // Enum 임포트

@MultipartConfig
public class SaveRecipeController implements Controller {

    @Override
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 HttpSession session = request.getSession();
         Integer userNumObject = (Integer) session.getAttribute("log");
         if (userNumObject == null) {
             response.sendRedirect(request.getContextPath() + "/login.do");
             return null; 
         }
         int userNum = userNumObject;

         String recipeName = null;
         String tagEatTimeStr = null; 
         String tagCookingStyleStr = null; 
         String recipeThumbnailFilename = null; 
         List<String> ingredientsList = new ArrayList<>(); 
         List<String> stepsList = new ArrayList<>(); 
         List<String> stepImageFilenames = new ArrayList<>(); 
         String[] cookingMethodsArr = null; 
         String[] ingredientTagsArr = null; 

         // --- 요청 인코딩 설정 (한글 처리) ---
         request.setCharacterEncoding(StandardCharsets.UTF_8.name());

         try {
             // --- Multipart 요청 ---
             Collection<Part> parts = request.getParts();
             for (Part part : parts) {
                 String fieldName = part.getName(); // 폼 필드의 name 속성
                 String submittedFileName = part.getSubmittedFileName(); // 파일 업로드 시 원본 파일명

                 // 파일 파트 처리
                 if (submittedFileName != null && !submittedFileName.trim().isEmpty()) {
                     if ("recipeThumbnail".equals(fieldName)) {
                         recipeThumbnailFilename = submittedFileName; // 썸네일 파일명 저장
                     } else if ("steps_img".equals(fieldName)) {
                          stepImageFilenames.add(submittedFileName);
                     }
                 } else {
                     String fieldValue = readPartContent(part); 
                     if (fieldValue == null) continue; // 내용 없는 파트 건너뛰기

                     switch (fieldName) {
                         case "recipeName": recipeName = fieldValue; break;
                         case "tagEatTime": tagEatTimeStr = fieldValue; break; // Enum 변환 전 저장
                         case "tagCookingStyle": tagCookingStyleStr = fieldValue; break; // Enum 변환 전 저장
                         case "ingredients[]": // 재료 (여러 개 가능)
                             // 빈 값이 아니면 리스트에 추가
                             if (!fieldValue.trim().isEmpty()) ingredientsList.add(fieldValue);
                             break;
                         case "steps[]": // 조리 순서 (여러 개 가능)
                             // 빈 값이 아니면 리스트에 추가
                             if (!fieldValue.trim().isEmpty()) stepsList.add(fieldValue);
                             break;
                     }
                 }
             }

             // --- 체크박스 값 가져오기 ---
             cookingMethodsArr = request.getParameterValues("cooking-method"); // 요리 방법들
             ingredientTagsArr = request.getParameterValues("ingredient"); // 주재료 태그들

             // --- 데이터 가공 및 Recipe 객체 생성 ---
             // Recipe 객체는 DB 테이블 구조와 매핑됨
             Recipe recipe = new Recipe();
             recipe.setRecipeName(recipeName);
             recipe.setRecipeThumbnail(recipeThumbnailFilename != null ? recipeThumbnailFilename : ""); // Null 방지

             // 리스트에 저장된 값들을 '|' 구분자로 합쳐서 VO에 설정 (sql 컬럼 형식에 맞춤)
             String recipeIngredientCombined = String.join("|", ingredientsList);
             String recipeManualCombined = String.join("|", stepsList);
             // 테이블에 저장될 단계별 이미지 파일명 목록도 | 로 합침
             String userRecipeImgCombined = String.join("|", stepImageFilenames);

             recipe.setRecipeIngredient(recipeIngredientCombined);
             recipe.setRecipeManual(recipeManualCombined);
             recipe.setRecipeSeasoning(""); // 폼에 양념 입력 없으므로 빈 값 설정
             recipe.setRecipeMealSize(1.0); 

           
             try {
                 recipe.setRecipeType(RecipeType.valueOf("USER")); 
             } catch (IllegalArgumentException e) {
                 System.err.println("RecipeType Enum에 'USER' 값이 없습니다. 확인 필요.");
                 recipe.setRecipeType(null); 
             }
             try {
                 if (tagEatTimeStr != null && !tagEatTimeStr.isEmpty()) {
                	 
                     recipe.setEatTime(KoreanNamedEnum.getEnumByKoreanName(EatTime.class, tagEatTimeStr)); // 문자열 -> EatTime Enum
                 }
             } catch (IllegalArgumentException e) {
                 System.err.println("잘못된 EatTime 값(from Form): " + tagEatTimeStr);
             }
             try {
                
                 if (tagCookingStyleStr != null && !tagCookingStyleStr.isEmpty()) {
                	 
                     recipe.setCookingStyle(KoreanNamedEnum.getEnumByKoreanName(CookingStyle.class, tagCookingStyleStr)); // 문자열 -> CookingStyle Enum
                 }
             } catch (IllegalArgumentException e) {
                 System.err.println("잘못된 CookingStyle 값(from Form): " + tagCookingStyleStr);
             }

             // 체크박스에서 받은 문자열 배열(String[])을 Set<String>으로 변환하여 VO에 설정
             // DAO에서 이 Set을 JSON 문자열로 변환하여 sql에 저장할 예정
             Set<String> cookingMethodsSet = (cookingMethodsArr != null) ? new HashSet<>(Arrays.asList(cookingMethodsArr)) : new HashSet<>();
             Set<String> ingredientTagsSet = (ingredientTagsArr != null) ? new HashSet<>(Arrays.asList(ingredientTagsArr)) : new HashSet<>();
             recipe.setCookingMethods(cookingMethodsSet); // 요리 방법 태그 Set 설정
             recipe.setIngredients(ingredientTagsSet); // 주재료 태그 Set 설정

             // --- DAO 호출하여 데이터베이스에 저장 ---
             RecipeDAO dao = RecipeDAO.getInstance();
             boolean success = dao.insertUserRecipeData(recipe, userNum, userRecipeImgCombined);

             if (success) {
                 request.setAttribute("saveMessage", "레시피가 성공적으로 등록되었습니다.");
             } else {
                 request.setAttribute("errorMessage", "레시피 등록 중 오류가 발생했습니다.");
             }

         } catch (IOException | ServletException e) {
             System.err.println("Multipart 요청 처리 중 오류 발생: " + e.getMessage());
             e.printStackTrace();
             request.setAttribute("errorMessage", "요청 처리 중 오류가 발생했습니다: " + e.getMessage());
             return "createRecipe"; 
         } catch (Exception e) {
              System.err.println("레시피 저장 중 일반 오류 발생: " + e.getMessage());
              e.printStackTrace();
              request.setAttribute("errorMessage", "레시피 저장 중 오류가 발생했습니다.");
              return "createRecipe"; 
         }

         return "main";
     }

   
     private String readPartContent(Part part) throws IOException {
         if (part.getSubmittedFileName() == null || part.getSubmittedFileName().trim().isEmpty()) {
             try (InputStream inputStream = part.getInputStream();
                  InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8); 
                  BufferedReader bufferedReader = new BufferedReader(reader)) {
                  String content = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
                  return content;
             }
         }
         return null; 
     }
 }