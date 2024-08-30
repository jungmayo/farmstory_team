package com.farmstory.controller.user;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.UserDto;
import com.farmstory.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user/myinfo.do")
public class MyinfoController extends HttpServlet{

	private UserService service = UserService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/myinfo.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	    HttpSession session = req.getSession();
	    UserDto user = (UserDto) session.getAttribute("sessUser");
		
	    if (user != null) {
		String uid = user.getUserId();
		String pass = req.getParameter("pass1");
		String name = req.getParameter("name");
		String nick = req.getParameter("nick");
		String email = req.getParameter("email");
		String hp = req.getParameter("hp");
		String zip = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String regip = req.getRemoteAddr();
		
		UserDto updatedto = new UserDto();
		updatedto.setUserId(uid);
		updatedto.setUserPass(pass);
		updatedto.setUserName(name);
		updatedto.setUserNick(nick);
		updatedto.setUserEmail(email);
		updatedto.setUserHp(hp);
		updatedto.setUserZip(zip);
		updatedto.setUserAddr1(addr1);
		updatedto.setUserAddr2(addr2);
		updatedto.setUserRegip(regip);
		
		int result = service.updateUser(updatedto);
		logger.debug("result : " + result);
		
		resp.sendRedirect("/FarmStoryJSP/");
	    } else {
	        // UserDto가 없을 경우의 처리 로그인 페이지로 리다이렉트)
	        resp.sendRedirect("/FarmStoryJSP/user/login.do");
	    }
	}
}