package kr.smartReciFit.frontcontroller;


import java.io.IOException;

import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.controller.board.CommentAddController;
import kr.smartReciFit.controller.board.CommentDeleteController;
import kr.smartReciFit.controller.board.CommentUpdateController;
import kr.smartReciFit.controller.board.LikeController;
import kr.smartReciFit.controller.board.RankingController;
import kr.smartReciFit.controller.board.ReviewController;
import kr.smartReciFit.controller.board.ReviewDeleteController;
import kr.smartReciFit.controller.board.ReviewDetailController;
import kr.smartReciFit.controller.board.ReviewUpdateController;
import kr.smartReciFit.controller.board.ReviewUpdateProcessController;
import kr.smartReciFit.controller.board.ReviewWriteController;
import kr.smartReciFit.controller.board.ReviewWriteProcessController;
import kr.smartReciFit.controller.recipe.RecipeFilterController;
import kr.smartReciFit.controller.recipe.RecipesController;
import kr.smartReciFit.controller.user.LoginCheckController;
import kr.smartReciFit.controller.user.MailSend;
import kr.smartReciFit.controller.user.LoginSuccessController;
import kr.smartReciFit.controller.user.CheckIdController;
import kr.smartReciFit.controller.user.CheckNickNameController;
import kr.smartReciFit.controller.user.UserJoinController;
import kr.smartReciFit.controller.user.VaildIdAjaxController;

import kr.smartReciFit.controller.user.LogOutController;
import kr.smartReciFit.controller.user.MainController;
import kr.smartReciFit.controller.user.SaveSocialLoginInfo;
import kr.smartReciFit.controller.user.UserContentController;
import kr.smartReciFit.controller.user.UserInfoController;


public class HandlerMapping {
	private HashMap<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		
		//회원가입 관련 맵핑
		mappings.put("/userJoin.do",  new UserJoinController());
		mappings.put("/checkId.do",  new CheckIdController());
		mappings.put("/checkNickName.do",  new CheckNickNameController());
		mappings.put("/vaildIdAjax.do",  new VaildIdAjaxController());
		mappings.put("/mailSend.do",  new MailSend());
		//마이페이지 관련 맵핑
		mappings.put("/userContent.do",  new UserContentController());
		mappings.put("/userInfo.do",  new UserInfoController());
		
		
		// 로그인 관련 작업 (나경)
		//로그인아웃 관련 맵핑
		mappings.put("/login.do", new LoginCheckController());
		mappings.put("/logout.do", new LogOutController());
		mappings.put("/main.do", new MainController());		
		mappings.put("/loginSuccess.do", new LoginSuccessController());
		mappings.put("/saveSocialLoginInfo.do", new SaveSocialLoginInfo());
		
		
		// 랭킹 후기 게시판 작업(명보)
		mappings.put("/ranking.do", new RankingController());
		mappings.put("/reviews.do", new ReviewController());
		mappings.put("/reviewDetail.do", new ReviewDetailController());
		mappings.put("/commentAdd.do",new CommentAddController());
		mappings.put("/commentUpdate.do", new CommentUpdateController());
		mappings.put("/commentDelete.do", new CommentDeleteController());
		mappings.put("/reviewWrite.do", new ReviewWriteController());
		mappings.put("/reviewWriteProcess.do",new ReviewWriteProcessController());
		mappings.put("/reviewUpdate.do", new ReviewUpdateController());
		mappings.put("/reviewUpdateProcess.do", new ReviewUpdateProcessController());
		mappings.put("/reviewDelete.do", new ReviewDeleteController());
		mappings.put("/like.do", new LikeController());
		
		// 레시피 페이지
		mappings.put("/recipes.do", new RecipesController());
		mappings.put("/recipeFilter.do", new RecipeFilterController());
		
	}

	public Controller getController(String key) {
		return mappings.get(key);
	}
}