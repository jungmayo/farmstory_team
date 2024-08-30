package com.farmstory.dto;

public class UserDto {
	private String UserId;
	private String UserPass;
	private String UserName;
	private String UserNick;
	private String UserEmail;
	private String UserHp;
	private String UserRole;
	private int UserGrade;
	private String UserZip;
	private String UserAddr1;
	private String UserAddr2;
	private int UserPoint;
	private String UserRegip;
	private String UserRegdate;
	
	// 추가필드
	private int UserCart;
	private String UserRegday;
	
	public int getUserCart() {
		return UserCart;
	}
	public void setUserCart(int userCart) {
		UserCart = userCart;
	}
	public void setUserCart(String userCart) {
		if(userCart != null) UserCart = Integer.parseInt(userCart);
		else this.UserCart = 0;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getUserPass() {
		return UserPass;
	}
	public void setUserPass(String userPass) {
		UserPass = userPass;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserNick() {
		return UserNick;
	}
	public void setUserNick(String userNick) {
		UserNick = userNick;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public String getUserHp() {
		return UserHp;
	}
	public void setUserHp(String userHp) {
		UserHp = userHp;
	}
	public String getUserRole() {
		return UserRole;
	}
	public void setUserRole(String userRole) {
		UserRole = userRole;
	}
	public int getUserGrade() {
		return UserGrade;
	}
	public void setUserGrade(int userGrade) {
		UserGrade = userGrade;
	}
	public void setUserGrade(String userGrade) {
		UserGrade = Integer.parseInt(userGrade);
	}
	public String getUserZip() {
		return UserZip;
	}
	public void setUserZip(String userZip) {
		UserZip = userZip;
	}
	public String getUserAddr1() {
		return UserAddr1;
	}
	public void setUserAddr1(String userAddr1) {
		UserAddr1 = userAddr1;
	}
	public String getUserAddr2() {
		return UserAddr2;
	}
	public void setUserAddr2(String userAddr2) {
		UserAddr2 = userAddr2;
	}
	public int getUserPoint() {
		return UserPoint;
	}
	public void setUserPoint(int userPoint) {
		UserPoint = userPoint;
	}
	public void setUserPoint(String userPoint) {
		if(userPoint==null) {
			UserPoint = 0;
		}else {
			UserPoint = Integer.parseInt(userPoint);
		}
	}
	public String getUserRegip() {
		return UserRegip;
	}
	public void setUserRegip(String userRegip) {
		UserRegip = userRegip;
	}
	public String getUserRegdate() {
		return UserRegdate;
	}
	public void setUserRegdate(String userRegdate) {
		UserRegdate = userRegdate;
		UserRegday = userRegdate.substring(0, 10);
	}

	public String getUserRegday() {
		return UserRegday;
	}
	@Override
	public String toString() {
		return "UserDto [UserId=" + UserId + ", UserPass=" + UserPass + ", UserName=" + UserName + ", UserNick="
				+ UserNick + ", UserEmail=" + UserEmail + ", UserHp=" + UserHp + ", UserRole=" + UserRole
				+ ", UserGrade=" + UserGrade + ", UserZip=" + UserZip + ", UserAddr1=" + UserAddr1 + ", UserAddr2="
				+ UserAddr2 + ", UserPoint=" + UserPoint + ", UserRegip=" + UserRegip + ", UserRegdate=" + UserRegdate
				+ "]";
	}
	
	
}
