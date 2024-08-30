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

@WebServlet("/user/register.do")
public class RegisterController extends HttpServlet{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.INSTANCE;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass1");
		String name = req.getParameter("name");
		String nick = req.getParameter("nick");
		String email = req.getParameter("email");
		String hp = req.getParameter("hp");
		String zip = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String regip = req.getRemoteAddr();
		
		UserDto dto = new UserDto();
		dto.setUserId(uid);
		dto.setUserPass(pass);
		dto.setUserName(name);
		dto.setUserNick(nick);
		dto.setUserEmail(email);
		dto.setUserHp(hp);
		dto.setUserZip(zip);
		dto.setUserAddr1(addr1);
		dto.setUserAddr2(addr2);
		dto.setUserRegip(regip);
		
		int result = service.insertUser(dto);
		logger.debug("result : " +result);
		
		resp.sendRedirect("/FarmStoryJSP/user/login.do?success=300");
	}

}
