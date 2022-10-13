package com.rkrua.dto;

public class CartVo {
	int cartid;
	String userid;
	String name;
	int code;
	String pictureurl;
	int price;
	

	
	@Override
	public String toString() {
		return "CartVo [cartid=" + cartid + ", userid=" + userid + ", name=" + name + ", code=" + code + ", pictureurl="
				+ pictureurl + ", price=" + price + "]";
	}
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
