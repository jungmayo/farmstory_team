
package com.farmstory.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.ProductDto;
import com.farmstory.util.DBHelper;
import com.farmstory.util.SQL;

public class ProductDao extends DBHelper {

	private static ProductDao instance = new ProductDao();
	public static ProductDao getInstance() {
		return instance;
	}
	private ProductDao() { }
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public int insertProduct(ProductDto dto) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_PRODUCT);
			psmt.setString(1, dto.getProname());
			psmt.setString(2, dto.getProtype());
			psmt.setInt(3, dto.getProprice());
			psmt.setInt(4, dto.getPropoint());
			psmt.setInt(5, dto.getProsale());
			psmt.setInt(6, dto.getProdeliveryfee());
			psmt.setInt(7, dto.getProstock());
			psmt.setString(8, dto.getProimg1());
			psmt.setString(9, dto.getProimg2());
			psmt.setString(10, dto.getProimg3());
			psmt.setString(11, dto.getProetc());
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeAll();
		}
		return result;
	}

	public int selectCountTotal() {
		int total = 0;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_COUNT_TOTALS);

			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeAll();
		}
		return total;
	}

	public ProductDto selectProduct(String prono) {
		ProductDto dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCT);
			psmt.setString(1, prono);

			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new ProductDto();
				dto.setProno(rs.getInt(1));
				dto.setProname(rs.getString(2));
				dto.setProtype(rs.getString(3));
				dto.setProprice(rs.getInt(4));
				dto.setPropoint(rs.getInt(5));
				dto.setProsale(rs.getInt(6));
				dto.setProdeliveryfee(rs.getInt(7));
				dto.setProstock(rs.getInt(8));
				dto.setProimg1(rs.getString(9));
				dto.setProimg2(rs.getString(10));
				dto.setProimg3(rs.getString(11));
				dto.setProetc(rs.getString(12));
				dto.setPrordate(rs.getString(13));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeAll();
		}
		return dto;
	}
	

	public List<ProductDto> selectProducts(int start) {
		List<ProductDto> products = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_LIMIT);
			psmt.setInt(1, start);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setProno(rs.getInt(1));
				dto.setProname(rs.getString(2));
				dto.setProtype(rs.getString(3));
				dto.setProprice(rs.getInt(4));
				dto.setPropoint(rs.getInt(5));
				dto.setProsale(rs.getInt(6));
				dto.setProdeliveryfee(rs.getInt(7));
				dto.setProstock(rs.getInt(8));
				dto.setProimg1(rs.getString(9));
				dto.setProimg2(rs.getString(10));
				dto.setProimg3(rs.getString(11));
				dto.setProetc(rs.getString(12));
				dto.setPrordate(rs.getString(13));
				products.add(dto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeAll();
		}
		return products;
	}

	public void updateProduct(ProductDto dto) {

	}

	public int deleteProduct(String prono) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_PRODUCT);
			psmt.setString(1, prono);
			result = psmt.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeAll();
		}
		return result;
	}
}
