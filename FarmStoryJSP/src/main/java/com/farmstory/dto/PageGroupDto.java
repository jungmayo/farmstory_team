package com.farmstory.dto;

public class PageGroupDto {
	private int group;
	private int start;
	private int end;
	
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public PageGroupDto(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public PageGroupDto(int start, int end,int group) {
		this.start = start;
		this.end = end;
		this.group = group;
	}
	@Override
	public String toString() {
		return "PageGroupDto [group=" + group + ", start=" + start + ", end=" + end + "]";
	}
	
	
}
