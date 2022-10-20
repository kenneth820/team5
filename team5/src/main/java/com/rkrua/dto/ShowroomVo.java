package com.rkrua.dto;

public class ShowroomVo {
	private int code;
	private String name;
	private String pictureUrl;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
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
		return "ShowroomVo [code=" + code + ", name=" + name + ", pictureUrl=" + pictureUrl + "]";
	}	
}
