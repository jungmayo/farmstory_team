package com.farmstory.dto;

public class CommentDto {
	private int comNo;
	private int comParent;
	private String comContent;
	private String comWriter;
	private String comRegip;
	private String comRdate;
	
	//추가필드
	private String nick;

	public int getComNo() {
		return comNo;
	}

	public void setComNo(int comNo) {
		this.comNo = comNo;
	}

	public int getComParent() {
		return comParent;
	}

	public void setComParent(int comParent) {
		this.comParent = comParent;
	}
	public void setComParent(String comParent) {
		this.comParent = Integer.parseInt(comParent);
	}

	public String getComContent() {
		return comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public String getComWriter() {
		return comWriter;
	}

	public void setComWriter(String comWriter) {
		this.comWriter = comWriter;
	}

	public String getComRegip() {
		return comRegip;
	}

	public void setComRegip(String comRegip) {
		this.comRegip = comRegip;
	}

	public String getComRdate() {
		return comRdate;
	}

	public void setComRdate(String comRdate) {
		this.comRdate = comRdate;
	}
	public void setComRdateSubstring(String comRdate) {
		this.comRdate = comRdate.substring(0,10);
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		return "CommentDto [comNo=" + comNo + ", comParent=" + comParent + ", comContent=" + comContent + ", comWriter="
				+ comWriter + ", comRegip=" + comRegip + ", comRdate=" + comRdate + ", nick=" + nick + "]";
	}
	
	
}
