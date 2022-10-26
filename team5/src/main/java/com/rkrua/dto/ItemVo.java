package com.rkrua.dto;

public class ItemVo {
	String userid;
	String name;
	int code;
	int category;
	String pictureurl;
	int equip;
	
	
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public int getEquip() {
		return equip;
	}
	public void setEquip(int equip) {
		this.equip = equip;
	}
	
	@Override
	public String toString() {
		return "ItemVo [userid=" + userid + ", name=" + name + ", code=" + code + ", category=" + category
				+ ", pictureurl=" + pictureurl + ", equip=" + equip + "]";
	}
	
}
