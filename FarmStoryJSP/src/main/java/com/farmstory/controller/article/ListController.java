package com.farmstory.controller.article;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.ArticleDto;
import com.farmstory.dto.PageGroupDto;
import com.farmstory.service.ArticleService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleService service = ArticleService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String type = "list";
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		
		String pg = req.getParameter("pg");
		
		// 현재 페이지 번호 구하기 - 페이지 번호가 null이면 1페이지로 이동
		int currentPage = service.getCurrentPage(pg);
		
		// 전체 게시물 갯수 구하기
		int total = service.selectCountTotal(group, cate);
		
		// 마지막 페이지 번호 구하기
		int lastPageNum = service.getLastPageNum(total);
		
		// 현재 페이지 그룹 구하기
		PageGroupDto pageGroup = service.getCurrentPageGroup(currentPage, lastPageNum);
		
		// Limit용 시작 번호 구하기
		int start = service.getStartNum(currentPage);
		
		// 페이지 시작 번호 구하기(목록에서 순서번호로 활용)
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		// 데이터 조회
		List<ArticleDto> articles = service.selectArticles(start,cate);
		
		// 공유 참조
		req.setAttribute("articles", articles);
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		
		req.setAttribute("lastPageNum", lastPageNum);
		//페이지 그룹
		req.setAttribute("pageGroup", pageGroup);
		//페이지 시작번호
		req.setAttribute("pageStartNum", pageStartNum);
		//현재 페이지
		req.setAttribute("currentPage", currentPage);
		
		//페이지 타입
		req.setAttribute("type", type);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/" + group + "/" + cate + ".jsp");
		dispatcher.forward(req, resp);
	}
}








