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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.user.User;
import kr.smartReciFit.model.user.UserDAO;

public class UserFixController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("유저 정보 수정 컨트롤러 진입");
		HttpSession session = request.getSession();
		String ctx=request.getContextPath();
		
		String num=request.getParameter("num");
		System.out.println("userNum="+num);
		Integer userNum=Integer.parseInt(num);
		User vo=UserDAO.getInstance().numGetUser(userNum);
//		System.out.println("테스트옹 vo: "+vo);
		request.setAttribute("userFix", vo);
		
		//입력된 값 받아오기
		String id=request.getParameter("id-new");
		String pw=request.getParameter("pw-new");
		System.out.println(pw);
		String name=request.getParameter("name");
		System.out.println(name);
		String nickName=request.getParameter("nickName");
		System.out.println(nickName);
		String email=request.getParameter("email");
		
		
		if (email==null||session.getAttribute("firstIn")==null||(Boolean)session.getAttribute("firstIn")==false) {
			System.out.println("값업음");
			session.setAttribute("firstIn", true);
			return "userFix";
		}
		
		System.out.println("값있음");
		session.setAttribute("firstIn", false);
		

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
			//업데이트 할 곳
			int pass=UserDAO.getInstance().UserUpdate(userNum,id,pw,name,nickName,email,phone,profileImg);
			System.out.println("pass="+pass);
			if (pass>0) {
				out.println("<script src='https://cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
				out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>");
				out.println("<script>");
				out.println("window.onload = function() {");
				out.println("  Swal.fire({");
				out.println("title: '회원 정보 수정 성공!',");
				//out.println("text: '정보수정을 완료했습니다',");
				out.println("icon: 'success',");
				out.println("confirmButtonText: '확인'}).then(function() {");
				out.println("location.href='" + ctx + "/userContent.do';");
				out.println("  });");
				out.println("};");
				out.println("</script>");
				System.out.println("회원 정보 수정 성공");
			}else {
				out.println("<script src='https://cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
				out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>");
				out.println("<script>");
				out.println("window.onload = function() {");
				out.println("  Swal.fire({");
				out.println("title: '회원 정보 수정 실패!',");
				out.println("text: '오류가 발생했습니다',");
				out.println("icon: 'error,");
				out.println("confirmButtonText: '확인'}).then(function() {");
				out.println("    history.go(-1);");
				out.println("  });");
				out.println("};");
				out.println("</script>");
				System.out.println("회원 정보 수정 실패");
			}
//			return "redirect:" + ctx + "/userInfo.do";
		}catch (Exception e) {
			e.printStackTrace();
			out.println("<script src='https://cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>");
			out.println("<script>");
			out.println("window.onload = function() {");
			out.println("  Swal.fire({");
			out.println("title: '회원 정보 수정 실패!',");
			out.println("text: '오류가 발생했습니다',");
			out.println("icon: 'error,");
			out.println("confirmButtonText: '확인'}).then(function() {");
			out.println("    history.go(-1);");
			out.println("  });");
			out.println("};");
			out.println("</script>");
			System.out.println("회원 가입 실패");
//			return "userJoin.do";
		}
		out.close();
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
