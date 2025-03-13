package kr.smartReciFit.frontcontroller;


import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;


public class HandlerMapping {
	private HashMap<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		
		mappings.put("asdasd", new Controller() {
			
			@Override
			public String requestHandler(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
	}

	public Controller getController(String key) {
		return mappings.get(key);
	}
}