package com.rkrua.dto;

public class ItemVo {
	String userid;
	int code;
	String pictureurl;
	String coordinate;
	int equip;
	
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
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	public int getEquip() {
		return equip;
	}
	public void setEquip(int equip) {
		this.equip = equip;
	}
	
	@Override
	public String toString() {
		return "ItemVo [userid=" + userid + ", code=" + code + ", pictureurl=" + pictureurl + ", coordinate="
				+ coordinate + ", equip=" + equip + "]";
	}
	
}
