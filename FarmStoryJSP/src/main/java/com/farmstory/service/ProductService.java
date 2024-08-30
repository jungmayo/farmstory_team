
package com.farmstory.service;

import java.util.List;

import com.farmstory.dao.ProductDao;
import com.farmstory.dto.PageGroupDto;
import com.farmstory.dto.ProductDto;

public enum ProductService {
	INSTANCE;

	private ProductDao dao = ProductDao.getInstance();

	public int getLastPageNum(int total) {
		int lastPageNum = 0;
		if (total % 10 == 0) {
			lastPageNum = total / 10;
		} else {
			lastPageNum = total / 10 + 1;
		}
		return lastPageNum;
	}

	// 페이지 시작번호(limit)
	public int getStartNum(int currentPage) {
		return (currentPage - 1) * 10;

	}

	public int getCurrentPage(String pg) {
		int currentPage = 1;

		if (pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}

	public int getProuctnO(String prono) {
		int getProuctnO = 1;
		
		if (prono != null) {
			getProuctnO = Integer.parseInt(prono);
		}
		return getProuctnO;
	}

	// 현재 페이지 그룹 구하기

	public PageGroupDto getCurrentPageGroup(int currentPage) {
		int currentPageGroup = (int) Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		int total = selectCountTotal();
		int pageGroupGroup = (int) Math.ceil(total / 10.0);

		if (pageGroupEnd > pageGroupGroup) {
			pageGroupEnd = pageGroupGroup;
		}
		return new PageGroupDto(pageGroupStart, pageGroupEnd, pageGroupGroup);
	}

	public int insertProduct(ProductDto dto) {

		return dao.insertProduct(dto);
	}

	public ProductDto selectProduct(String prono) {
		return dao.selectProduct(prono);
	}

	public int selectCountTotal() {
		return dao.selectCountTotal();
	}

	public List<ProductDto> selectProducts(int start) {
		return dao.selectProducts(start);
	}

	public void updateProduct(ProductDto dto) {
		dao.updateProduct(dto);
	}

	public int deleteProduct(String prono) {
		return dao.deleteProduct(prono);
	}
}
