package kr.smartReciFit.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.UserDAO;

public class UserJoinController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("UserJoinController진입");
		String ctx=request.getContextPath();
		
		String id=request.getParameter("id-new");
		
		if(id==null) {
			return "userJoin";
		}
		
		String pw=request.getParameter("pw-new");
		System.out.println(pw);
		String name=request.getParameter("name");
		System.out.println(name);
		String nickName=request.getParameter("nickName");
		System.out.println(nickName);
		String email=request.getParameter("email");
		if (email.trim().equals("")) {
			email=null;
		}
		System.out.println(email);
		String phone=request.getParameter("phone");
		if (phone.trim().equals("")) {
			phone=null;
		}
		System.out.println(phone);
		
		
		
		
		String profileImg=null;
		
		String saveDirectory = request.getServletContext().getRealPath("/img");
		Path saveDirPath = Paths.get(saveDirectory);
		if (!Files.isDirectory(saveDirPath)) {
			Files.createDirectories(saveDirPath);
		}
		System.out.println("saveDirectory = " + saveDirectory);
		
		String sFileName = null;
		String oFileName = null;
		Part filePart = request.getPart("uploadFile");
        if (filePart != null && filePart.getSize() > 0) {
         	oFileName = extractFileName(filePart);
            sFileName =  System.currentTimeMillis() +"_"+oFileName ;
            
            filePart.write(saveDirPath.resolve(sFileName).toString());
            String fileType = filePart.getContentType();
            System.out.println("fileType= " + fileType);
        }

		profileImg=sFileName;
		
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		try {
			//유저 가입완료 알람창 + 정보 입력 할지
			UserDAO.getInstance().UserJoin(id,pw,name,nickName,email,phone,profileImg);
			System.out.println("회원 가입 성공");
//			out.println("<script>alert('회원 가입 성공.'); location.href='" + ctx + "/userInfo.do';</script>");
			out.println("<script>location.href='" + ctx + "/userInfo.do';</script>");
			out.close();
//			return "redirect:" + ctx + "/userInfo.do";
		}catch (Exception e) {
			e.printStackTrace();
			out.println("<script> alert('회원 가입 실패');");
			out.println("history.go(-1); </script>"); 
			System.out.println("회원 가입 실패");
			out.close();
//			return "userJoin.do";
		}
		return null;
	}

	private String extractFileName(Part filePart) {
        String contentDisposition = filePart.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        
        System.out.println("items =" + Arrays.toString(items));
        
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                String fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
                return Paths.get(fileName).getFileName().toString(); // Extract just the filename
            }
        }
        return null;
	}

}


