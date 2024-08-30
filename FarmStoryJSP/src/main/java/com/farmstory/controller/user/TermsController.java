package com.farmstory.controller.user;

import java.io.IOException;

import com.farmstory.dto.TermsDto;
import com.farmstory.service.TermsService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/terms.do")
public class TermsController extends HttpServlet{

	private TermsService service = TermsService.INSTANCE;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TermsDto dto = service.selectTerms();
		req.setAttribute("TermsDto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/terms.jsp");
		dispatcher.forward(req, resp);
	}

}
