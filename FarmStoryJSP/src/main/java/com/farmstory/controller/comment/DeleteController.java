package com.farmstory.controller.comment;

import java.io.IOException;

import com.farmstory.service.ArticleService;
import com.farmstory.service.CommentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/comment/delete.do")
public class DeleteController extends HttpServlet{
	private CommentService commentService = CommentService.INSTANCE;
	private ArticleService articleService = ArticleService.INSTANCE;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String type = "view";
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String artNo = req.getParameter("artNo");
		String comNo = req.getParameter("comNo");
		
		
		commentService.deleteComment(comNo);
		articleService.DowndateComment(artNo);
		
		req.setAttribute("type", type);
		
		resp.sendRedirect("/FarmStoryJSP/article/view.do?group="+group+"&cate="+cate+"&artNo=" + artNo);
	}
}
