package com.rkrua.dto;

public class TrendVo {
	private int num;
	private String name;
	private String pictureUrl;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	@Override
	public String toString() {
		return "TrandVo [num=" + num + ", name=" + name + ", pictureUrl=" + pictureUrl + "]";
	}
}
