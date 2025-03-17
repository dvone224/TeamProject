package kr.smartReciFit.frontcontroller;


import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.controller.user.LogOutController;
import kr.smartReciFit.controller.user.LoginCheckController;
import kr.smartReciFit.controller.user.MainController;


public class HandlerMapping {
	private HashMap<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		
		mappings.put("/login.do", new LoginCheckController());
		mappings.put("/logout.do", new LogOutController());
		mappings.put("/main.do", new MainController());		
			
			
	}

	public Controller getController(String key) {
		return mappings.get(key);
	}
}