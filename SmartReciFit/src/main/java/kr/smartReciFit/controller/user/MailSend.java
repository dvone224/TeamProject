package kr.smartReciFit.controller.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		 
		System.out.println("mailSend 진입");
		
		String num = request.getParameter("num");
		System.out.println("mailSend num:" + num);
		String receiver = request.getParameter("email");
		System.out.println("mailSend email:" + receiver);
    	
		//오늘 날짜
		LocalDate today = LocalDate.now();
		//지금 시간
		LocalTime now= LocalTime.now();
		// 2분 뒤 시간
		LocalDateTime timeLimitDateTime = LocalDateTime.of(today, now).plusMinutes(2);
		LocalDate timeLimitDate = timeLimitDateTime.toLocalDate();
		LocalTime timeLimitTime = timeLimitDateTime.toLocalTime();
		
		//형식 정하기
		DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
		
		String dayForm=today.format(dayFormatter);
		String timeForm=now.format(timeFormatter);
		String timeLimitDayForm = timeLimitDate.format(dayFormatter);
		String timeLimitTimeForm = timeLimitTime.format(timeFormatter);
		
		//String subject = request.getParameter("subject");
		String subject ="SmartReciFit에서 발송한 인증번호입니다 "+num;
		String content2 = "<table style='border-collapse: collapse; width: 100%;'>" +
		        "<tr><td style='padding: 8px;'>인증번호</td><td style='padding: 8px;'>" + num + "</td></tr>" +
		        "<tr><td style='padding: 8px;'>발송 시간</td><td style='padding: 8px;'>" + dayForm + " " + timeForm + "</td></tr>" +
		        "<tr><td style='padding: 8px;'>인증번호 유효 시간</td><td style='padding: 8px;'>" + timeLimitDayForm + " " + timeLimitTimeForm + "</td></tr>" +
		        "</table>";
		
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
			m.setContent(content2, "text/html;charset=utf-8");
			m.setSentDate(new Date());
			
			Transport.send(m); //메세지를 메일로 전송
			
			out.print("메일 전송");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
