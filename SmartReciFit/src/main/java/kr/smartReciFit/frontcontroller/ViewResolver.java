package kr.smartReciFit.frontcontroller;

public class ViewResolver {
   public static String makeView(String nextPage) {
	   
	   return "/WEB-INF/smartReciFit/"+nextPage+".jsp";
   }
}