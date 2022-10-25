package com.rkrua.dto;

public class VisitVo {
	String userid;
	String name;
	String selfcomment;
	String pictureurl;
	String showroompicture;
	String trendpicture;
	
	public String getSelfcomment() {
		return selfcomment;
	}
	public void setSelfcomment(String selfcomment) {
		this.selfcomment = selfcomment;
	}
	public String getShowroompicture() {
		return showroompicture;
	}
	public void setShowroompicture(String showroompicture) {
		this.showroompicture = showroompicture;
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
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public String getShowpicture() {
		return showroompicture;
	}
	public void setShowpicture(String showpicture) {
		this.showroompicture = showpicture;
	}
	public String getTrendpicture() {
		return trendpicture;
	}
	public void setTrendpicture(String trendpicture) {
		this.trendpicture = trendpicture;
	}
	@Override
	public String toString() {
		return "VisitVo [userid=" + userid + ", name=" + name + ", selfcomment=" + selfcomment + ", pictureurl="
				+ pictureurl + ", showroompicture=" + showroompicture + ", trendpicture=" + trendpicture + "]";
	}
	
}
