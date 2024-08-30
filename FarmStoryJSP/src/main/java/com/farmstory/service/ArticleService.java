package com.farmstory.service;

import java.util.List;

import com.farmstory.dao.ArticleDao;
import com.farmstory.dto.ArticleDto;
import com.farmstory.dto.PageGroupDto;

public enum ArticleService {
	
	INSTANCE;
	
	private ArticleDao dao = ArticleDao.getInstance();
	
	//전체 게시물 갯수에서 마지막 페이지 번호 구하기
	public int getLastPageNum(int total) {
		int lastpageNum = 0;
		if(total %10==0) {
			lastpageNum = total / 10;
		}else {
			lastpageNum = total / 10 + 1;
		}
		return lastpageNum;
	}
	
	//페이지 시작번호(limit용)
	public int getStartNum(int currentpage) {
		return (currentpage -1)*10;
	}
	
	//현재 페이지번호 구하기
	public int getCurrentPage(String pg) {
		int currentPage =1;
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	//현재 페이지의 글번호 구하기
	public int getCurrentNumber(int total, int currentPage) {
		return (total - (currentPage-1)*10);
	}
	
	/*
	//현재 페이지 그룹 구하기
	public  PageGroupDto getCurrentPageGroup(int currentPage) {
		int currentPageGroup = (int)Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup -1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		
		return  new PageGroupDto(pageGroupStart,pageGroupEnd);
	}
	*/
	
	
	public int insertArticle(ArticleDto dto) {
		return dao.insertArticle(dto);
	}
	
	public ArticleDto selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	public List<ArticleDto> selectArticles(int start, String cate) {
		return dao.selectArticles(start, cate);
	}
	
	public void updateArticle(ArticleDto dto) {
		dao.updateArticle(dto);
	}	
	
	public void updateArticleHit(String artNo) {
		dao.updateArticleHit(artNo);
	}	
	
	public void deleteArticle(String no) {
		dao.deleteArticle(no);
	}

	public int selectCountTotal(String group, String cate) {
		return dao.selectCountTotal(group, cate);
	}

	public int getPageStartNum(int total, int currentPage) {
		int start = (currentPage - 1) * 10;
		return total - start;
	}

	public PageGroupDto getCurrentPageGroup(int currentPage, int lastPageNum) {
		int currentPageGroup = (int) Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		
		if(pageGroupEnd > lastPageNum){
			pageGroupEnd = lastPageNum;
		}
		
		return new PageGroupDto(pageGroupStart, pageGroupEnd);
	}

	public void UpdateComment(String artNo) {
		dao.updateComment(artNo);
	}

	public void DowndateComment(String artNo) {
		dao.DowndateComment(artNo);
	}
}
