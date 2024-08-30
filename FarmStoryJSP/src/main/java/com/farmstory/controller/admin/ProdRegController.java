package com.farmstory.controller.admin;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.ProductDto;
import com.farmstory.service.ImageService;
import com.farmstory.service.ProductService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/product/register.do"})
@MultipartConfig
public class ProdRegController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ImageService imageService = ImageService.INSTANCE;
	private ProductService productService = ProductService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String type = req.getParameter("type");
		String price = req.getParameter("price");
		String point = req.getParameter("point");
		String sale = req.getParameter("sale");
		String fee = req.getParameter("fee");
		String stock = req.getParameter("stock");
		String etc = req.getParameter("etc");
		
		List<String> files = imageService.imageUpload(req);
		logger.debug("files : " +files);
		
		ProductDto dto = new ProductDto();
        dto.setProname(name);
        dto.setProtype(type);
        dto.setProprice(price);
        dto.setPropoint(point);
        dto.setProsale(sale);
        dto.setProdeliveryfee(fee);
        dto.setProstock(stock);
        if (files.size() > 0) dto.setProimg1(files.get(0));
        if (files.size() > 1) dto.setProimg2(files.get(1));
        if (files.size() > 2) dto.setProimg3(files.get(2));
        dto.setProetc(etc);
        int result = productService.insertProduct(dto);
        logger.debug("result : "+result);
		req.setAttribute("result", result);
		resp.sendRedirect("/FarmStoryJSP/admin/product/list.do");
	}
}
