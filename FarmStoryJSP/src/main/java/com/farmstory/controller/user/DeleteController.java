package com.farmstory.controller.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.UserDto;
import com.farmstory.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user/delete.do")
public class DeleteController extends HttpServlet{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.INSTANCE;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }

        // 3. JSON 요청 본문을 파싱
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(requestBody.toString(), JsonObject.class);
        String userId = jsonObject.get("userId").getAsString(); // JSON에서 userId 추출

        // 4. userId 로그로 출력
        logger.debug("User ID: " + userId);

        // 5. 사용자 삭제 및 결과 가져오기
        int result = service.deleteUser(userId);
        logger.debug("Result: " + result);

        // 6. JSON 응답 생성
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("result", result);

        // 7. JSON 응답 전송
        try (PrintWriter writer = resp.getWriter()) {
            writer.print(responseJson.toString());
        }
        
        
		HttpSession session = req.getSession();
		session.removeAttribute("sessUser");
		session.invalidate();
        
    }
}
