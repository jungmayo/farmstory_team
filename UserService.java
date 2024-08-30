package com.farmstory.service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.farmstory.dao.UserDao;
import com.farmstory.dto.UserDto;



public enum UserService {
	INSTANCE;
	private UserDao dao = UserDao.getInstance();
	private static final int MAXVIEW = 30; // 한 페이지에 표시할 인원 수
	
	public int getLastPageNum(int total) {
		int lastPageNum = 0;
			if(total%MAXVIEW==0) lastPageNum = total/MAXVIEW;
			else lastPageNum = total/MAXVIEW + 1;
		return lastPageNum;
	}
	public int getStartNum(int currentPage) {
		return (currentPage-1)*MAXVIEW;
	}
	
	public int getCurrentPage(String pg) {
		int currentPage = 1;
			if(pg != null) currentPage = Integer.parseInt(pg);
		return currentPage;
	}
	
	public int selectCountCheckUser(String type, String value) {
		return dao.selectCountCheckUser(type, value);
	}
	
	public int selectUserCount() { 
		return dao.selectUserCount();
	}

	

	public void insertUser(UserDto user) {
		dao.insertUser(user);
	}
	public UserDto selectUser(String userId) {
		return dao.selectUser(userId);
	}
	public List<UserDto> selectUsers() {
		return dao.selectUsers();
	}
	public void updateUser(UserDto user) {
		dao.updateUser(user);
	}
	public void deleteUser(String userId) {
		dao.deleteUser(userId);
	}

	public String sendEmailCode(String email) {
		
		
		//인증코드 생성
		int code =ThreadLocalRandom.current().nextInt(100000, 1000000);
		
		
		//이메일 기본정보
		String title = "FarmStory 이메일 인증코드입니다.";
		String content = "<h1>인증코드는 " +code+ "입니다.</h1>";
		String sender = "wlgus5946@gmail.com";
		String appPass = "dqys begj jhxb ffhs"; //Google 앱 비밀번호
		
		// gmail SMTP 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		//gmail session 생성
		Session gmailSession = Session.getInstance(props, new Authenticator(){
			
			@Override // sender, appPass 반환
			protected javax.mail.PasswordAuthentication getPasswordAuthentication(){ //서버에 로그인시 사용자의 이메일 주소와 비밀번호를 제공하는 역할
				return new PasswordAuthentication(sender, appPass);
			}
		});
		
		//메일발송
		Message message = new MimeMessage(gmailSession);
		try{
			message.setFrom(new InternetAddress(sender, "FarmStory", "UTF-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=utf-8");
			
			Transport.send(message);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ""+code; //string으로 받기 위해서
	
	}
}
