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

@WebServlet("/user/findpass.do")
public class FindpassController extends HttpServlet{
	
	private UserService service = UserService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/findpass.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String email = req.getParameter("email");
		
		UserDto dto = service.selectFindPass(uid, email);
		
		if(dto != null) {
		HttpSession session = req.getSession();
		session.setAttribute("FindId", dto);
		resp.sendRedirect("/FarmStoryJSP/user/findpasschange.do");
		logger.debug(dto.toString());
		
		}else {
			//일치하는 값이 없을 때
			resp.sendRedirect("/FarmStoryJSP/user/login.do?success=800");
		}
	}

}

