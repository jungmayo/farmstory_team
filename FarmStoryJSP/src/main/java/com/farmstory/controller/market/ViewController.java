package com.farmstory.controller.market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.CartDto;
import com.farmstory.dto.ProductDto;
import com.farmstory.dto.UserDto;
import com.farmstory.service.CartService;
import com.farmstory.service.OrderService;
import com.farmstory.service.ProductService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/market/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = -1051308723058841536L;
	private ProductService productservice = ProductService.INSTANCE;
	private CartService cartService = CartService.INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//제품 번호를 전달 받아 어떤 제품의 번호인지 확인
		String no = req.getParameter("no");
		
		ProductDto ProductDto = productservice.selectProduct(no);
		
		req.setAttribute("ProductDto", ProductDto);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/market/view.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();


		StringBuilder sb = new StringBuilder();
	    String line;
	    try (BufferedReader reader = req.getReader()) {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line);
	        }
	    }

	    // JSON 문자열을 JsonObject로 파싱
	    JsonObject jsonObject;
	    try {
	        jsonObject = JsonParser.parseString(sb.toString()).getAsJsonObject();
	    } catch (JsonSyntaxException e) {
	        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        resp.getWriter().print("{\"error\": \"Invalid JSON format.\"}");
	        return;
	    }

	    // JSON 데이터에서 필드 추출
	    String prono = jsonObject.has("prono") ? jsonObject.get("prono").getAsString() : null;
	    String stock = jsonObject.has("stock") ? jsonObject.get("stock").getAsString() : null;
	    String uid = ((UserDto)session.getAttribute("sessUser")).getUserId();
	    
	    logger.debug("prono : "+prono);
	    logger.debug("stock : "+stock);
	    logger.debug("uid : "+uid);
	    
	    CartDto dto = new CartDto();
	    dto.setCartprono(prono);
	    dto.setCartstock(stock);
	    dto.setCartuid(uid);
	    
	    int result = cartService.intsertCart(dto);
	    
	    logger.debug("result : " + result);
	    
		JsonObject json = new JsonObject();
		json.addProperty("result", result);

		
		PrintWriter printWriter = resp.getWriter();
		printWriter.print(json);
	    
	}

	
}
