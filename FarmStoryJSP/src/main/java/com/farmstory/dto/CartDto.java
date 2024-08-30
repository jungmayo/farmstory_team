package com.farmstory.dto;

public class CartDto {
	//카트정보
	private int cartno;
	private String cartuid;
	private int cartprono;
	private int cartstock;
	
	//상품 정보
	private String proimg;
	private String protype;
	private String proname;
	private int prosale;
	private int propoint;
	private int proprice;
	private int prodeliveryfee;
	
	public int getProdeliveryfee() {
		return prodeliveryfee;
	}
	public void setProdeliveryfee(int prodeliveryfee) {
		this.prodeliveryfee = prodeliveryfee;
	}
	public String getProimg() {
		return proimg;
	}
	public void setProimg(String proimg) {
		this.proimg = proimg;
	}
	//상품 정보
	public String getProtype() {
		return protype;
	}
	public void setProtype(String protype) {
		this.protype = protype;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public int getProsale() {
		return prosale;
	}
	public void setProsale(int prosale) {
		this.prosale = prosale;
	}
	public void setProsale(String prosale) {
		if(prosale==null) this.prosale = 0;
		else this.prosale = Integer.parseInt(prosale);
	}
	public int getPropoint() {
		return propoint;
	}
	public void setPropoint(int propoint) {
		this.propoint = propoint;
	}
	public void setPropoint(String propoint) {
		if(propoint==null) this.propoint = 0;
		else this.propoint = Integer.parseInt(propoint);
	}
	public int getProprice() {
		return proprice;
	}
	public void setProprice(int proprice) {
		this.proprice = proprice;
	}
	public void setProprice(String proprice) {
		if(proprice==null) this.proprice = 0;
		else this.proprice = Integer.parseInt(proprice);
	}
	
	
	//카트정보
	public int getCartno() {
		return cartno;
	}
	public void setCartno(int cartno) {
		this.cartno = cartno;
	}
	public void setCartno(String cartno) {
		if(cartno==null) this.cartno = 0;
		else this.cartno = Integer.parseInt(cartno);
	}
	public String getCartuid() {
		return cartuid;
	}
	public void setCartuid(String cartuid) {
		this.cartuid = cartuid;
	}
	public int getCartprono() {
		return cartprono;
	}
	public void setCartprono(int cartprono) {
		this.cartprono = cartprono;
	}
	public void setCartprono(String cartprono) {
		if(cartprono==null) this.cartprono = 0;
		else this.cartprono = Integer.parseInt(cartprono);
	}
	public int getCartstock() {
		return cartstock;
	}
	public void setCartstock(int cartstock) {
		this.cartstock = cartstock;
	}
	public void setCartstock(String cartstock) {
		if(cartstock==null) this.cartstock = 0;
		else this.cartstock = Integer.parseInt(cartstock);
	}
	
	@Override
	public String toString() {
		return "CartDto [cartno=" + cartno + ", cartuid=" + cartuid + ", cartprono=" + cartprono + ", cartstock="
				+ cartstock + "]";
	}
	
	
	
	
}
