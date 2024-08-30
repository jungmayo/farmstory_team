package com.farmstory.service;

import java.util.List;

import com.farmstory.dao.CartDao;
import com.farmstory.dto.CartDto;

public enum CartService {
	INSTANCE;
	private CartDao dao = CartDao.getInstance();
	public int intsertCart(CartDto dto) {
		return dao.intsertCart(dto);
	}
	public List<CartDto> selectCarts(String cartuid){
		return dao.selectCarts(cartuid);
	}
	public List<CartDto> selectUserCart(String uid){
		return dao.selectUserCart(uid);
	}
	public int deleteCart(String cartno,String cartuid) {
		return dao.deleteCart(cartno, cartuid);
	}
	public CartDto selectUserCartForPay(String uid, String prono){
		return dao.selectUserCartForPay(uid, prono);
	}
}
