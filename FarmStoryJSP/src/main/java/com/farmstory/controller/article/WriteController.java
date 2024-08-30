package com.farmstory.controller.article;

import java.io.IOException;
import java.util.List;

import com.farmstory.dto.ArticleDto;
import com.farmstory.dto.FileDto;
import com.farmstory.service.ArticleService;
import com.farmstory.service.FileService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/write.do")
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ArticleService articleService = ArticleService.INSTANCE;
	private FileService fileService = FileService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String type = "write";
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		
		req.setAttribute("type", type);
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/" + group + "/" + cate + ".jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 데이터 수신
		
		String artGroup = req.getParameter("artGroup");
		String artCate = req.getParameter("artCate");
		String artTitle = req.getParameter("artTitle");
		String artContent = req.getParameter("artContent");
		String artWriter = req.getParameter("artWriter");
		String artRegip = req.getRemoteAddr();
		
		// 파일 업로드
		List<FileDto> files = fileService.fileUpload(req);
		
		// 글 등록을 위한 DTO 생성
		ArticleDto dto = new ArticleDto();
		dto.setArtGroup(artGroup);
		dto.setArtCate(artCate);
		dto.setArtTitle(artTitle);
		dto.setArtContent(artContent);
		dto.setArtFile(files.size());
		dto.setArtWriter(artWriter);
		dto.setArtRegip(artRegip);
		
		
		
		// 글 등록
		int no = articleService.insertArticle(dto);
		
		
		// 파일 등록
		for(FileDto fileDto : files) {
			fileDto.setAno(no);
			fileService.insertFile(fileDto);
		}
		
		
		resp.sendRedirect("/FarmStoryJSP/article/list.do?group=" + artGroup + "&cate=" + artCate);
	}
}
