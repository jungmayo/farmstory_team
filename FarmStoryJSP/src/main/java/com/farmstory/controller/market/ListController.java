package com.farmstory.controller.market;

import java.io.IOException;
import java.util.List;


import com.farmstory.dto.PageGroupDto;
import com.farmstory.dto.ProductDto;
import com.farmstory.service.ProductService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/market/list.do")
public class ListController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private ProductService service = ProductService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String pg = req.getParameter("pg");
		// 현재 페이지 그룹 구하기
		int currentPage = service.getCurrentPage(pg);
		
		// 현재 페이지 그룹 구하기
		PageGroupDto pageGroup = service.getCurrentPageGroup(currentPage);
		

		// 전체 계시물 겟수 구하기
		int total = service.selectCountTotal();
		
		// 마지막 페이지 번호 구하기
		int lastPageNum = service.getLastPageNum(total);
		
		// 페이지 시작번호 구하기
		int start = service.getStartNum(currentPage);
		
		
		// 데이터 조회
		List<ProductDto> products = service.selectProducts(start);
		int PageStartNo = total - (currentPage - 1) * 10;
		for(ProductDto product : products) {
			product.setStartno(PageStartNo --);
			
			int originalPrice = (int) product.getProprice(); // 원래 가격
			int discountPercent = (int) product.getProsale(); // 할인 비율

			// 할인된 가격 계산
			int discountPrice = (int) (originalPrice * (1 - discountPercent / 100.0));
	        product.setSaleprice(discountPrice);
		}
		
		
				
		req.setAttribute("products", products);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroup", pageGroup);
		req.setAttribute("currentPage", currentPage);
		    
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/market/list.jsp");
		dispatcher.forward(req, resp);
		
	}

}
