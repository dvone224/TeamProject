package kr.smartReciFit.frontcontroller;


import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.controller.user.LoginCheckController;
import kr.smartReciFit.controller.user.CheckIdController;
import kr.smartReciFit.controller.user.CheckNickNameController;
import kr.smartReciFit.controller.user.UserJoinController;
import kr.smartReciFit.controller.user.VaildIdAjaxController;



public class HandlerMapping {
	private HashMap<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/userJoin.do",  new UserJoinController());
		mappings.put("/checkId.do",  new CheckIdController());
		mappings.put("/checkNickName.do",  new CheckNickNameController());
		mappings.put("/vaildIdAjax.do",  new VaildIdAjaxController());
		
		mappings.put("/login", new LoginCheckController());

	}

	public Controller getController(String key) {
		return mappings.get(key);
	}
}