package com.rkrua.dto;

import java.sql.Timestamp;

public class TrendVo {
	private int num;
	private String userid;
	private String pictureUrl;
	private String title;
	private String text;
	private Timestamp writedate;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}
	
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getWritedate() {
		return writedate;
	}
	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}
	@Override
	public String toString() {
		return "TrendVo [num=" + num + ", userid=" + userid + ", pictureUrl=" + pictureUrl + ", title=" + title
				+ ",  text=" + text + ", writedate=" + writedate + "]";
	}
}
