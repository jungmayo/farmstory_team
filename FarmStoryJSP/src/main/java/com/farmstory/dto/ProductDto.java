package com.farmstory.dto;

public class ProductDto {
	private int prono;
	private String proname;
	private String protype;
	private int proprice;
	private int propoint;
	private int prosale;
	private int prodeliveryfee;
	private int prostock;
	private String proimg1;
	private String proimg2;
	private String proimg3;
	private String proetc;
	private String prordate;
	
	private String prorday;
	private int startno;
	private int saleprice;
	private int cartstock;
	
	
	
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
		return "ProductDto [prono=" + prono + ", proname=" + proname + ", protype=" + protype + ", proprice=" + proprice
				+ ", propoint=" + propoint + ", prosale=" + prosale + ", prodeliveryfee=" + prodeliveryfee
				+ ", prostock=" + prostock + ", proImg1=" + proimg1 + ", proImg2=" + proimg2 + ", proImg3=" + proimg3
				+ ", proETC=" + proetc + ", proRdate=" + prordate + ", startNo=" + startno + ", salePrice=" + saleprice
				+ "]";
	}

	public int getStartno() {
		return startno;
	}

	public void setStartno(int startNo) {
		this.startno = startNo;
	}

	public int getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(int salePrice) {
		this.saleprice = salePrice;
	}

	public int getProno() {
		return prono;
	}
	public void setProno(int prono) {
		this.prono = prono;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public String getProtype() {
		return protype;
	}
	public void setProtype(String protype) {
		this.protype = protype;
	}
	public int getProprice() {
		return proprice;
	}
	public void setProprice(int proprice) {
		this.proprice = proprice;
	}
	public void setProprice(String proprice) {
		if(proprice!=null) this.proprice = Integer.parseInt(proprice);
		else this.proprice = 0;
	}
	public int getPropoint() {
		return propoint;
	}
	public void setPropoint(int propoint) {
		this.propoint = propoint;
	}
	public void setPropoint(String propoint) {
		if(propoint!=null) this.propoint = Integer.parseInt(propoint);
		else this.propoint = 0;
	}
	public int getProsale() {
		return prosale;
	}
	public void setProsale(int prosale) {
		this.prosale = prosale;
	}
	public void setProsale(String prosale) {
		if(prosale!=null) this.prosale = Integer.parseInt(prosale);
		else this.prosale = 0;
	}
	public int getProdeliveryfee() {
		return prodeliveryfee;
	}
	public void setProdeliveryfee(int prodeliveryfee) {
		this.prodeliveryfee = prodeliveryfee;
	}
	public void setProdeliveryfee(String prodeliveryfee) {
		if(prodeliveryfee!=null) this.prodeliveryfee = Integer.parseInt(prodeliveryfee);
		else this.prodeliveryfee = 0;
	}
	public int getProstock() {
		return prostock;
	}
	public void setProstock(int stock) {
		this.prostock = stock;
	}
	public void setProstock(String prostock) {
		if(prostock!=null) this.prostock = Integer.parseInt(prostock);
		else this.prostock = 0;
	}
	public String getProimg1() {
		return proimg1;
	}
	public void setProimg1(String proImg1) {
		this.proimg1 = proImg1;
	}
	public String getProimg2() {
		return proimg2;
	}
	public void setProimg2(String proImg2) {
		this.proimg2 = proImg2;
	}
	public String getProimg3() {
		return proimg3;
	}
	public void setProimg3(String proImg3) {
		this.proimg3 = proImg3;
	}
	public String getProetc() {
		return proetc;
	}
	public void setProetc(String proETC) {
		this.proetc = proETC;
	}
	public String getPrordate() {
		return prordate;
	}
	public void setPrordate(String proRdate) {
		this.prordate = proRdate;
		prorday = proRdate.substring(0, 10);
	}

	public String getProrday() {
		return prorday;
	}

	
}