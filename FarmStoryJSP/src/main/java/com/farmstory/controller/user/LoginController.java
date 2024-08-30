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

@WebServlet(urlPatterns = {"/user/login.do"})
public class LoginController extends HttpServlet{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.INSTANCE;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String success = req.getParameter("success");
		req.setAttribute("success", success);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/login.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		
		logger.debug("uid :" + uid + ", pass : " + pass);
		
		UserDto user = service.selectUser(uid,pass);
		logger.debug("user : " +user);
			if(user != null) {
				
				HttpSession session = req.getSession();
				session.setAttribute("sessUser", user);
				logger.debug("Session ID: " + session.getId());
				logger.debug("Session Attribute 'sessUser': " + session.getAttribute("sessUser"));
				resp.sendRedirect("/FarmStoryJSP/");
				
			}else {
				resp.sendRedirect("/FarmStoryJSP/user/login.do?success=100"); //로그인실패
			}

		
	}

}
