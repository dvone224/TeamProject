package kr.smartReciFit.controller.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
 
import jakarta.mail.Address;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
 
 
//@WebServlet("/mailSend")
public class MailSend implements Controller {
	private static final long serialVersionUID = 1L;
       
   
//    public MailSend() {
//        super();
//    }
// 
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	}
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//	}

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		 
		System.out.println("mailSend 진입");
		
		String num = request.getParameter("num");
		System.out.println("mailSend num:" + num);
		String receiver = request.getParameter("email");
		System.out.println("mailSend email:" + receiver);
		
		//String subject = request.getParameter("subject");
		String subject ="테스트 입니다.";
		String content = "인증번호"+num;
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			Properties p = System.getProperties(); //서버 정보를 p객체에 저장
			
			p.put("mail.smtp.starttls.enable","true");
			p.put("mail.smtp.host","smtp.gmail.com"); //gmail.com
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.port", "587"); //gmail 포트번호
			
			Authenticator auth = new GoogleAuthentication(); //인증정보 생성
			Session s = Session.getInstance(p, auth); //메일 전송 역할하는 객체 생성
			Message m = new MimeMessage(s); //s객체를 사용하여 전송할 m객체 생성
			
			Address receiver_address = new InternetAddress(receiver); //받는 사람

			//메일 전송에 필요한 설정 부분
			m.setHeader("content-type", "text/html;charset=utf-8");
			m.addRecipient(Message.RecipientType.TO, receiver_address);
			m.setSubject(subject);
			m.setContent(content, "text/html;charset=utf-8");
			m.setSentDate(new Date());
			
			Transport.send(m); //메세지를 메일로 전송
			
			out.print("메일 전송");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
