package com.farmstory.service;

import java.util.List;

import com.farmstory.dao.OrderDao;
import com.farmstory.dto.OrderDto;
import com.farmstory.dto.PageGroupDto;
import com.farmstory.dto.ProductDto;

public enum OrderService {
	INSTANCE;
	private OrderDao dao = OrderDao.getInstance();

	
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

	// 현재 페이지 그룹 구하기
	public PageGroupDto getCurrentPageGroup(int currentPage) {
		int currentPageGroup = (int) Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		int total = countTotal();
		int pageGroupGroup = (int) Math.ceil(total / 10.0);

		if (pageGroupEnd > pageGroupGroup) {
			pageGroupEnd = pageGroupGroup;
		}
		return new PageGroupDto(pageGroupStart, pageGroupEnd, pageGroupGroup);
	}
	
	public int countTotal() {
		return dao.countTotal();
	}
	public int insertOrder(OrderDto dto) {
		return dao.insertOrder(dto);
	}
	public OrderDto selectOrder(String order) {
		return dao.selectOrder(order);
	}
	public List<OrderDto> selectOrdersForAdminPage(int start){
		return dao.selectOrdersForAdminPage(start);
	}
	public List<OrderDto> selectOrders(){
		return dao.selectOrders();
	}
	public void updateOrder(OrderDto order) {
		dao.updateOrder(order);
	}
	public int deleteOrder(String orderno) {
		return dao.deleteOrder(orderno);
	}
}
