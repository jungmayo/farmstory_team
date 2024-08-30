package com.farmstory.controller.article;

import java.io.IOException;

import com.farmstory.service.ArticleService;
import com.farmstory.service.CommentService;
import com.farmstory.service.FileService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/delete.do")
public class DeleteController extends HttpServlet{
	
	private static final long serialVersionUID = 8455424136903839372L;
	private ArticleService articleService = ArticleService.INSTANCE;
	private FileService fileService = FileService.INSTANCE;
	private CommentService commentService = CommentService.INSTANCE;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = "list";
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String artNo = req.getParameter("artNo");
		
		
		
		fileService.deleteFiles(req, artNo);
		
		commentService.deleteComments(artNo);
		
		articleService.deleteArticle(artNo);
		
		
		
		req.setAttribute("type", type);
		
		resp.sendRedirect("/FarmStoryJSP/article/list.do?group="+group+"&cate="+cate);
	}
}
