package com.farmstory.controller.article;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.ArticleDto;
import com.farmstory.dto.CommentDto;
import com.farmstory.service.ArticleService;
import com.farmstory.service.CommentService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/view.do")
public class ViewController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.INSTANCE;
	private CommentService commentService = CommentService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(getClass());
	//private CommentService commentService = CommentService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String type = "view";
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String artNo = req.getParameter("artNo");
		//logger.debug(group+":"+cate);
		
		// 데이터 조회
		ArticleDto articleDto = service.selectArticle(artNo);
		
		//조회수 증가
		service.updateArticleHit(artNo);
		
		// 댓글 조회
		List<CommentDto> comments = commentService.selectComments(artNo);
		
		// 공유 참조
		req.setAttribute("articleDto", articleDto);
		req.setAttribute("comments", comments);
		req.setAttribute("type", type);
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		// 포워드(화면출력)
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/" + group + "/" + cate + ".jsp");
		dispatcher.forward(req, resp);
	}
	
}








