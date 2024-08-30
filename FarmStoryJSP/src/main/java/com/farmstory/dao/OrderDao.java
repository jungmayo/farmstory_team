package com.farmstory.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.OrderDto;
import com.farmstory.dto.ProductDto;
import com.farmstory.util.DBHelper;
import com.farmstory.util.SQL;

public class OrderDao extends DBHelper{
	private static OrderDao instance = new OrderDao();
	private Logger logger = LoggerFactory.getLogger(getClass());
	public static OrderDao getInstance() {
		return instance;
	}
	private OrderDao() {}
	
	public int countTotal() {
		int total = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ORDERS_COUNT);
			rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return total;
	}
	public int insertOrder(OrderDto dto) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_ORDER);
			psmt.setString(1, dto.getOrderuid());
			psmt.setInt(2, dto.getOrderprodno());
			psmt.setInt(3, dto.getOrderstock());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return result;
	}
	public OrderDto selectOrder(String order) {
		return null;
	}
	public List<OrderDto> selectOrders(){
		return null;
	}
	public List<OrderDto> selectOrdersForAdminPage(int start){
		List<OrderDto> orders = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ORDERS_PAGED);
			psmt.setInt(1, start);
			rs = psmt.executeQuery();
			while(rs.next()){
				OrderDto order = new OrderDto();
				order.setOrderno(rs.getInt(1));
				order.setOrderproname(rs.getString(2));
				order.setOrderproprice(rs.getInt(3));
				order.setOrderstock(rs.getInt(4));
				order.setOrderprodeliveryfee(rs.getInt(5));
				order.setOrderpayment(rs.getInt(6));
				order.setOrderusername(rs.getString(7));
				order.setOrderdatetime(rs.getString(8));
				orders.add(order);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return orders;
	}
	public void updateOrder(OrderDto order) {
		
	}
	public int deleteOrder(String orderno) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_ORDER);
			psmt.setString(1, orderno);
			result = psmt.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return result;
	}
}
