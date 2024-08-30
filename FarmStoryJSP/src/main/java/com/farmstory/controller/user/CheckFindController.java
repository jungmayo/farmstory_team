package com.farmstory.controller.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user/checkfindUser.do")
public class CheckFindController extends HttpServlet {
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.INSTANCE;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		String value = req.getParameter("value");
		
		
		logger.debug("value : " + value);
		logger.debug("type : " + type);
		
		int result = service.selectCountCheckUser(type, value);
		
		// session에 code 번호 저장해야됨 / 고유 번호이기 때문에
		if(type.equals("email")&& result == 1) {
			String code = service.sendEmailCode(value);
			HttpSession session = req.getSession();
			session.setAttribute("code", code);
		}
		
		
		
		//JSON으로 만들어서 전송
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		//JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//POST로 전송한 정보 JSON으로 받기
		BufferedReader reader = req.getReader();
		StringBuilder requestbody = new StringBuilder();
		
		String line;
		while((line = reader.readLine()) != null) {
			requestbody.append(line);
		}
		reader.close();
		
		
		//JSON 파싱
		Gson gson = new Gson();
		Properties prop = gson.fromJson(requestbody.toString(), Properties.class); //JSON문자열을 프로퍼티 객체로 변환
		String auth = prop.getProperty("auth"); //입력한 인증코드
		logger.debug(auth);
		
		//인증코드 세션에서 가져오기
		HttpSession session = req.getSession();
		String code = (String)session.getAttribute("code"); //발급한 인증코드
		
		//입력한 auth랑 발급한 인증코드랑 일치하는지 확인여부 1 반환
		JsonObject json = new JsonObject();
		if(code.equals(auth)) {
			json.addProperty("result", 1);
		}else {
			json.addProperty("result", 0);
		}
		PrintWriter writer = resp.getWriter();
		writer.print(json);
	}

}
