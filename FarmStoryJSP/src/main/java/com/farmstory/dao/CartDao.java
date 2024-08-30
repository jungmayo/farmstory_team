package com.farmstory.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.CartDto;
import com.farmstory.util.DBHelper;
import com.farmstory.util.SQL;

public class CartDao extends DBHelper{
	private static CartDao instance = new CartDao();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public static CartDao getInstance() {
		return instance;
	}

	private CartDao() {	}
	public int intsertCart(CartDto dto) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_CART);
			psmt.setString(1, dto.getCartuid());
			psmt.setInt(2, dto.getCartprono());
			psmt.setInt(3, dto.getCartstock());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return result;
	}
	public List<CartDto> selectCarts(String cartuid){
		List<CartDto> carts = new ArrayList<CartDto>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.GET_CARTS_PRODUCT);
			psmt.setString(1, cartuid);
			rs = psmt.executeQuery();
			while(rs.next()) {
				CartDto dto = new CartDto();
				dto.setProtype(rs.getString(1));
				dto.setProname(rs.getString(2));
				dto.setCartstock(rs.getInt(3));
				dto.setProsale(rs.getInt(4));
				dto.setPropoint(rs.getInt(5));
				dto.setProprice(rs.getInt(6));
				carts.add(dto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return carts;
	}
	public int deleteCart(String cartno,String cartuid) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_CART);
			psmt.setString(1, cartno);
			psmt.setString(2, cartuid);
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return result;
	}
	public List<CartDto> selectUserCart(String uid){
		List<CartDto> cart = new ArrayList<CartDto>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USER_CART);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			while(rs.next()) {
				CartDto dto = new CartDto();
				dto.setProimg(rs.getString(1));
				dto.setProtype(rs.getString(2));
				dto.setProname(rs.getString(3));
				dto.setCartstock(rs.getInt(4));
				dto.setProsale(rs.getInt(5));
				dto.setPropoint(rs.getInt(6));
				dto.setProprice(rs.getInt(7));
				dto.setCartprono(rs.getInt(8));
				dto.setProdeliveryfee(rs.getInt(9));
				cart.add(dto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return cart;
	}
	public CartDto selectUserCartForPay(String uid, String prono){
		CartDto cart = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USER_CART_FOR_PAY);
			psmt.setString(1, uid);
			psmt.setString(2, prono);
			rs = psmt.executeQuery();
			if(rs.next()) {
				cart = new CartDto();
				cart.setProimg(rs.getString(1));
				cart.setProtype(rs.getString(2));
				cart.setProname(rs.getString(3));
				cart.setCartstock(rs.getInt(4));
				cart.setProsale(rs.getInt(5));
				cart.setPropoint(rs.getInt(6));
				cart.setProprice(rs.getInt(7));
				cart.setCartprono(rs.getInt(8));
				cart.setProdeliveryfee(rs.getInt(9));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return cart;
	}
	
}
