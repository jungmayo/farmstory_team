package com.farmstory.service;

import java.util.List;

import com.farmstory.dao.CommentDao;
import com.farmstory.dto.CommentDto;

public enum CommentService {
	INSTANCE;
	private CommentDao dao = CommentDao.getInstance();
	
	public int insertComment(CommentDto dto) {
		return dao.insertComment(dto);
	}
	public CommentDto selectComment(int no) {
		return dao.selectComment(no);
	}
	public List<CommentDto> selectComments(String parent) {
		return dao.selectComments(parent);
	}
	public int updateComment(CommentDto dto) {
		return dao.updateComment(dto);
	}
	public void deleteComment(String no) {
		dao.deleteComment(no);
	}
	public void deleteComments(String comParent) {
		dao.deleteComments(comParent);
	}
	
	
}
