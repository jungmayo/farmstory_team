package com.farmstory.service;

import com.farmstory.dao.TermsDao;
import com.farmstory.dto.TermsDto;


public enum TermsService {
	INSTANCE;
	
	private TermsDao dao = TermsDao.getInstance();
	
	public TermsDto selectTerms() {
		return dao.selectTerms();
	}
	

}
