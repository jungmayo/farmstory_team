package com.farmstory.controller.comment;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.CommentDto;
import com.farmstory.service.ArticleService;
import com.farmstory.service.CommentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/comment/write.do")
public class WriteController extends HttpServlet{
	private static final long serialVersionUID = -5472389795616633914L;
	
	private CommentService service = CommentService.INSTANCE;
	private ArticleService articleService = ArticleService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String parent = req.getParameter("parent");
		String comment = req.getParameter("comment");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String artNo = req.getParameter("artNo");
		
		
		
		CommentDto dto = new CommentDto();
		dto.setComParent(parent);
		dto.setComContent(comment);
		dto.setComWriter(writer);
		dto.setComRegip(regip);
		
		int pk = service.insertComment(dto);
		articleService.UpdateComment(artNo);
		
		
		resp.sendRedirect("/FarmStoryJSP/article/view.do?group=" + group +"&cate=" + cate +"&artNo="+artNo);
		
	}
}
