package com.farmstory.controller.admin;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.OrderDto;
import com.farmstory.dto.PageGroupDto;
import com.farmstory.dto.ProductDto;
import com.farmstory.dto.UserDto;
import com.farmstory.service.OrderService;
import com.farmstory.service.ProductService;
import com.farmstory.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns ={"/admin/index.do","/admin/"})
public class IndexController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private UserService userService = UserService.INSTANCE;
	private OrderService orderService = OrderService.INSTANCE;
	private ProductService productService = ProductService.INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private final static int VIEW = 5; // 메인에서 출력할 항목 수 제한
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<UserDto> users = userService.selectPagedUsers(new PageGroupDto(1, VIEW));
		List<ProductDto> products = productService.selectProducts(1);
		List<OrderDto> orders = orderService.selectOrdersForAdminPage(1);
		
		for(int i=0; i<10-VIEW;i++) {
			if(products.size()>VIEW) products.removeLast();
			if(orders.size()>VIEW) orders.removeLast();
		}
		
		req.setAttribute("orders", orders);
		req.setAttribute("products", products);
		req.setAttribute("users", users);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/index.jsp");
		dispatcher.forward(req, resp);
	}
}
