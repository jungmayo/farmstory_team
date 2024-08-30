package com.farmstory.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.CommentDto;
import com.farmstory.util.DBHelper;
import com.farmstory.util.SQL;

public class CommentDao extends DBHelper{
	private static CommentDao instance = new CommentDao();
	public static CommentDao getInstance() {
		return instance;
	}
	private CommentDao() {}
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public int insertComment(CommentDto dto) {
		int pk = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT, Statement.RETURN_GENERATED_KEYS);
			psmt.setInt(1, dto.getComParent());
			psmt.setString(2, dto.getComContent());
			psmt.setString(3, dto.getComWriter());
			psmt.setString(4, dto.getComRegip());
			psmt.executeUpdate();
			
			rs = psmt.getGeneratedKeys();
			if(rs.next()) {
				pk = rs.getInt(1);
			}
					
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		finally {
			closeAll();
		}
		
		return pk;
	}
	public CommentDto selectComment(int artNo) {
		CommentDto dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COMMENT);
			psmt.setInt(1, artNo);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new CommentDto();
				dto.setComNo(rs.getInt(1));
				dto.setComParent(rs.getInt(2));
				dto.setComContent(rs.getString(3));
				dto.setComWriter(rs.getString(4));
				dto.setComRegip(rs.getString(5));
				dto.setComRdate(rs.getString(6));
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return dto;
	}
	public List<CommentDto> selectComments(String comParent){
		List<CommentDto> comments = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COMMENTS);
			psmt.setString(1, comParent);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CommentDto dto = new CommentDto();
				dto.setComNo(rs.getInt(1));
				dto.setComParent(rs.getInt(2));
				dto.setComContent(rs.getString(3));
				dto.setComWriter(rs.getString(4));
				dto.setComRegip(rs.getString(5));
				dto.setComRdateSubstring(rs.getString(6));
				dto.setNick(rs.getString(7));
				comments.add(dto);
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		finally {
			closeAll();
		}
		return comments;
	}
	public int updateComment(CommentDto dto) {
		return 0;
	}
	public void deleteComment(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("DELETE from `comment` where `ComNo`=?");
			psmt.setString(1, no);
			
			psmt.executeUpdate();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
	}
	public void deleteComments(String comParent) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("DELETE from `comment` where `comparent`=?");
			psmt.setString(1, comParent);
			
			psmt.executeUpdate();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
	}
}
