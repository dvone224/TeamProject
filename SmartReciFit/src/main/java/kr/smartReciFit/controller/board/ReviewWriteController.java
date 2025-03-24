package kr.smartReciFit.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.recipe.RecipeDAO;
import kr.smartReciFit.model.recipe.ApiRecipe;

public class ReviewWriteController implements Controller {

    @Override
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("nickName") == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('로그인 후 이용가능합니다'); history.back();</script>");
            writer.close();
            return null;
        }

        String user = request.getParameter("nickName");

        request.setAttribute("user", user);
        return "reviewWrite"; // reviewWrite.jsp로 forward
    }
}