package com.farmstory.controller.mainIndex;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;


import com.farmstory.dto.ArticleDto;
import com.farmstory.dto.ProductDto;
import com.farmstory.service.ArticleService;
import com.farmstory.service.ProductService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index.do")
public class IndexController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private ProductService service = ProductService.INSTANCE;
	private ArticleService articleService = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String pg = req.getParameter("pg");
	    String artCate = req.getParameter("artCate");
	    
	    if (artCate != null) {
            artCate = URLDecoder.decode(artCate, "UTF-8");
        }
	    

	    int currentPage = service.getCurrentPage(pg);
	    int arCurrentPage = articleService.getCurrentPage(pg);
	    
	    int total = service.selectCountTotal();

	    int start = service.getStartNum(currentPage);
	    int arStart = articleService.getStartNum(arCurrentPage);

	    List<ProductDto> products = service.selectProducts(start);
	  
	    int limit = 6;
	    if (products.size() > limit) {
	        products = products.subList(0, limit);
	    }
	    
	    List<ArticleDto> storiArticles = articleService.selectArticles(arStart, "story");
	    List<ArticleDto> growArticles = articleService.selectArticles(arStart, "grow");
	    List<ArticleDto> SchoolArticles = articleService.selectArticles(arStart, "school");
	    List<ArticleDto> noticeArticles = articleService.selectArticles(arStart, "notice");
	    int articleLimit = 5;
	    if (storiArticles.size() > articleLimit) {
            storiArticles = storiArticles.subList(0, articleLimit);
        }
        if (growArticles.size() > articleLimit) {
            growArticles = growArticles.subList(0, articleLimit);
        }
        if (SchoolArticles.size() > articleLimit) {
            SchoolArticles = SchoolArticles.subList(0, articleLimit);
        }
        if (noticeArticles.size() > articleLimit) {
        	noticeArticles = noticeArticles.subList(0, articleLimit);
        }
        
	    int PageStartNo = total - (currentPage - 1) * 10;
	    for (ProductDto product : products) {
	        product.setStartno(PageStartNo--);

	        int originalPrice = (int) product.getProprice();
	        int discountPercent = (int) product.getProsale();
	        int discountPrice = (int) (originalPrice * (1 - discountPercent / 100.0));
	        product.setSaleprice(discountPrice);
	    }

	    req.setAttribute("products", products);
	    req.setAttribute("storiArticles", storiArticles);
	    req.setAttribute("growArticles", growArticles);
	    req.setAttribute("SchoolArticles", SchoolArticles);
	    req.setAttribute("noticeArticles", noticeArticles);

	    
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
	    dispatcher.forward(req, resp);
	}

}
