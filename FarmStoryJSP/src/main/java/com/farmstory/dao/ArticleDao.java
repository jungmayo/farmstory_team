package com.farmstory.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.ArticleDto;
import com.farmstory.dto.FileDto;
import com.farmstory.util.DBHelper;
import com.farmstory.util.SQL;


public class ArticleDao extends DBHelper{
	private static ArticleDao instance = new ArticleDao();
	public static ArticleDao getInstance() {
		return instance;
	}
	private ArticleDao() {}

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int insertArticle(ArticleDto dto) {
		int no = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getArtGroup());
			psmt.setString(2, dto.getArtCate());
			psmt.setString(3, dto.getArtTitle());
			psmt.setString(4, dto.getArtContent());
			psmt.setInt(5, dto.getArtFile());
			psmt.setInt(6, dto.getArtHit());
			psmt.setInt(7, dto.getArtComment());
			psmt.setString(8, dto.getArtWriter());
			psmt.setString(9, dto.getArtRegip());
			psmt.executeUpdate();


			rs = stmt.executeQuery(SQL.SELECT_MAX_NO_ARTICLE);
			if(rs.next()) {
				no = rs.getInt(1);
			}

			conn.commit();

		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}

		return no;
	}

	public ArticleDto selectArticle(String artNo) {

		ArticleDto dto = null;
		List<FileDto> files = new ArrayList<FileDto>();

		try {
			conn = getConnection();
			psmt = conn.prepareStatement("SELECT * FROM `article` AS a LEFT JOIN `file` AS b ON a.`artno` = b.ano WHERE `artno`=?");
			psmt.setString(1, artNo);
			rs = psmt.executeQuery();

			while(rs.next()) {
				if(dto == null) {
					dto = new ArticleDto();
					dto.setArtNo(rs.getInt(1));
					dto.setArtGroup(rs.getString(2));
					dto.setArtCate(rs.getString(3));
					dto.setArtTitle(rs.getString(4));
					dto.setArtContent(rs.getString(5));
					dto.setArtFile(rs.getInt(6));
					dto.setArtHit(rs.getInt(7));
					dto.setArtComment(rs.getInt(8));
					dto.setArtWriter(rs.getString(9));
					dto.setArtRegip(rs.getString(10));
					dto.setRdateSubString(rs.getString(11));
				}
				FileDto fileDto = new FileDto();
				fileDto.setFno(rs.getInt(12));
				fileDto.setAno(rs.getInt(13));
				fileDto.setoName(rs.getString(14));
				fileDto.setsName(rs.getString(15));
				fileDto.setDownload(rs.getInt(16));
				fileDto.setRdate(rs.getString(17));
				files.add(fileDto);
			}
			dto.setArtFiles(files);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return dto;
	}
	public List<ArticleDto> selectArticles(int start, String cate){

		List<ArticleDto> articles = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("SELECT a.*, b.usernick  from `article` AS a LEFT JOIN `user` AS b ON a.artwriter = b.userid "
					+ "WHERE `artcate` = ?"
					+ "ORDER BY `artno` DESC "
					+ "LIMIT ?, 10");
			psmt.setString(1, cate);
			psmt.setInt(2, start);
			rs = psmt.executeQuery();

			while(rs.next()) {
				ArticleDto dto = new ArticleDto();
				dto.setArtNo(rs.getInt(1));
				dto.setArtGroup(rs.getString(2));
				dto.setArtCate(rs.getString(3));
				dto.setArtTitle(rs.getString(4));
				dto.setArtContent(rs.getString(5));
				dto.setArtFile(rs.getInt(6));
				dto.setArtHit(rs.getInt(7));
				dto.setArtComment(rs.getInt(8));
				dto.setArtWriter(rs.getString(9));
				dto.setArtRegip(rs.getString(10));
				dto.setRdateSubString(rs.getString(11));
				dto.setNick(rs.getString(12));
				articles.add(dto);
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}

		return articles;
	}
	public void updateArticle(ArticleDto dto) {
		try {
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
	}
	public void updateArticleHit(String artNo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_HIT_COUNT);
			psmt.setString(1, artNo);
			psmt.executeUpdate();

		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
	}
	public void deleteArticle(String artNo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_ARTICLE);
			psmt.setString(1, artNo);
			psmt.executeUpdate();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
	}
	public int selectCountTotal(String group, String cate) {
		int total = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE_COUNT_TOTAL);
			psmt.setString(1, group);
			psmt.setString(2, cate);
			rs = psmt.executeQuery();

			if(rs.next()) {
				total =rs.getInt(1);
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}

		return total;
	}
	public void updateComment(String artNo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("update `ARTICLE` set `artcomment` = `artcomment` + 1 where `artNo`=?");
			psmt.setString(1, artNo);
			psmt.executeUpdate();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
	}
	public void DowndateComment(String artNo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("update `ARTICLE` set `artcomment` = `artcomment` - 1 where `artNo`=?");
			psmt.setString(1, artNo);
			psmt.executeUpdate();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
	}

}
