package com.farmstory.dto;

import java.util.List;

public class ArticleDto {
	private int artNo;
	private String artGroup;
	private String artCate;
	private String artTitle;
	private String artContent;
	private int artFile;
	private int artHit;
	private int artComment;
	private String artWriter;
	private String artRegip;
	private String artRdate;
	
	//외부 필드
	private String nick;
	private List<FileDto> artFiles;
	
	
	public int getArtNo() {
		return artNo;
	}
	public void setArtNo(int artNo) {
		this.artNo = artNo;
	}
	public String getArtGroup() {
		return artGroup;
	}
	public void setArtGroup(String artGroup) {
		this.artGroup = artGroup;
	}
	public String getArtCate() {
		return artCate;
	}
	public void setArtCate(String artCate) {
		this.artCate = artCate;
	}
	public String getArtTitle() {
		return artTitle;
	}
	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}
	public String getArtContent() {
		return artContent;
	}
	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}
	public int getArtFile() {
		return artFile;
	}
	public void setArtFile(int artFile) {
		this.artFile = artFile;
	}
	public int getArtHit() {
		return artHit;
	}
	public void setArtHit(int artHit) {
		this.artHit = artHit;
	}
	public int getArtComment() {
		return artComment;
	}
	public void setArtComment(int artComment) {
		this.artComment = artComment;
	}
	public String getArtWriter() {
		return artWriter;
	}
	public void setArtWriter(String artWriter) {
		this.artWriter = artWriter;
	}
	public String getArtRegip() {
		return artRegip;
	}
	public void setArtRegip(String artRegip) {
		this.artRegip = artRegip;
	}
	public String getArtRdate() {
		return artRdate;
	}
	public void setArtRdate(String artRdate) {
		this.artRdate = artRdate;
	}
	public void setRdateSubString(String artRdate) {
		this.artRdate = artRdate.substring(0, 10);
	}
	@Override
	public String toString() {
		return "ArticleDto [글 번호 =" + artNo + ", 글 그룹 =" + artGroup + ", 글 카테고리 =" + artCate + ", 글 제목 ="
				+ artTitle + ", 글 내용 =" + artContent + ", 글 파일 수 =" + artFile + ", 글 조회수 =" + artHit
				+ ", 글 댓글 수 =" + artComment + ", 글 작성자=" + artWriter + ", 글 작성 아이피=" + artRegip + ", 글 작성 날짜 ="
				+ artRdate + "]";
	}
	public List<FileDto> getArtFiles() {
		return artFiles;
	}
	public void setArtFiles(List<FileDto> artFiles) {
		this.artFiles = artFiles;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
}
