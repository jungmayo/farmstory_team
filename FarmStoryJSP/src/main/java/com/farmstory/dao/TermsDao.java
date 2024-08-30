package com.farmstory.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.TermsDto;
import com.farmstory.util.DBHelper;
import com.farmstory.util.SQL;

public class TermsDao extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static TermsDao instance = new TermsDao();
	public static TermsDao getInstance() {
		return instance;
	}
	
	public TermsDto selectTerms() {
		TermsDto dto = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_TERMS);
			if(rs.next()) {
				dto = new TermsDto();
				dto.setTerms(rs.getString(1));
				dto.setPrivacy(rs.getString(2));
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeAll();
		}
		
		return dto;
	}
	

}
