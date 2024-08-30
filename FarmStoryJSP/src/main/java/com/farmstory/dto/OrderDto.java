package com.farmstory.dto;

public class OrderDto {
	private int orderno;
	private int orderprodno;
	private String orderuid;
	private int orderstock;
	private String orderrdate;

	
	// 추가필드
	private String orderproname;
	private int orderproprice;
	private int orderprodeliveryfee;
	private int orderpayment;
	private String orderusername;

	private String orderdate;
	private String ordertime;
	
	public void setOrderdatetime(String str) {
		String[] dt = str.split(" "); 
		this.orderdate = dt[0];
		this.ordertime = dt[1];
	}
	
	public String getOrderdate() {
		return orderdate;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public String getOrderproname() {
		return orderproname;
	}
	public void setOrderproname(String orderproname) {
		this.orderproname = orderproname;
	}
	public int getOrderproprice() {
		return orderproprice;
	}
	public void setOrderproprice(int orderproprice) {
		this.orderproprice = orderproprice;
	}
	public int getOrderprodeliveryfee() {
		return orderprodeliveryfee;
	}
	public void setOrderprodeliveryfee(int orderprodeliveryfee) {
		this.orderprodeliveryfee = orderprodeliveryfee;
	}
	public int getOrderpayment() {
		return orderpayment;
	}
	public void setOrderpayment(int orderpayment) {
		this.orderpayment = orderpayment;
	}
	public String getOrderusername() {
		return orderusername;
	}
	public void setOrderusername(String orderusername) {
		this.orderusername = orderusername;
	}
	public int getOrderno() {
		return orderno;
	}
	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	public int getOrderprodno() {
		return orderprodno;
	}
	public void setOrderprodno(int orderprodno) {
		this.orderprodno = orderprodno;
	}
	public void setOrderprodno(String orderprodno) {
		if(orderprodno==null)this.orderprodno = 0;
		else this.orderprodno = Integer.parseInt(orderprodno);
	}
	public String getOrderuid() {
		return orderuid;
	}
	public void setOrderuid(String orderuid) {
		this.orderuid = orderuid;
	}
	public int getOrderstock() {
		return orderstock;
	}
	public void setOrderstock(int orderstock) {
		this.orderstock = orderstock;
	}
	public void setOrderstock(String orderstock) {
		if(orderstock==null)this.orderstock = 0;
		else this.orderstock = Integer.parseInt(orderstock);
	}
	public String getOrderrdate() {
		return orderrdate;
	}
	public void setOrderrdate(String orderrdate) {
		this.orderrdate = orderrdate;
	}
	@Override
	public String toString() {
		return "OrderDto [orderno=" + orderno + ", orderprodno=" + orderprodno + ", orderuid=" + orderuid
				+ ", orderstock=" + orderstock + ", orderrdate=" + orderrdate + "]";
	}
	
}
