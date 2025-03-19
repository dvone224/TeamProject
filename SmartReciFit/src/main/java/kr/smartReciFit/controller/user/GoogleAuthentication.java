package kr.smartReciFit.controller.user;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
 
public class GoogleAuthentication extends Authenticator{
	PasswordAuthentication passAuth;
 
	public GoogleAuthentication() { //생성자
		//gmail 아이디(메일주소), 앱 비밀번호(2단계 인증) 발급받은 16자리
//		passAuth = new PasswordAuthentication("아이디@gmail.com","16자리 비밀번호");
        // 앱 비밀번호 대신 일반 비밀번호 사용 (권장되지 않음)
        passAuth = new PasswordAuthentication("smartrecifit@gmail.com", "dzvc hcxx nnrr owun");
	}
	public PasswordAuthentication getPasswordAuthentication() {
		return passAuth;
	}
}
