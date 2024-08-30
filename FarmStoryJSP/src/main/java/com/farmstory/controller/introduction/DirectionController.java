package com.farmstory.controller.introduction;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/introduction/direction.do")
public class DirectionController extends HttpServlet{

	private static final long serialVersionUID = -6581499784021355124L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 포워드(화면출력)
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/introduction/direction.jsp");
		dispatcher.forward(req, resp);
	}
}
