package com.rkrua.dto;

import java.sql.Timestamp;

public class ProductVo {
	int code;
	String name;
	int price;
	String pictureurl;
	int category;
	String Coordinate;
	Timestamp reg_date;
	
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getCoordinate() {
		return Coordinate;
	}
	public void setCoordinate(String coordinate) {
		Coordinate = coordinate;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getCode() {
		return code;
	}
	public Timestamp getReg_date() {
		return reg_date;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	@Override
	public String toString() {
		return "ProductVo [code=" + code + ", name=" + name + ", price=" + price + ", pictureurl=" + pictureurl
				+ ", category=" + category + ", reg_date=" + reg_date + "]";
	}


}
