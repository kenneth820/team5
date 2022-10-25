package com.rkrua.dto;

//DTV(VO) ��� ����
// 1. �߿��� ���� ���� ��ȭ (�ʵ� ���� ���� ����)
// 2. ������ ������ �Ͽ�ȭ
// 3. ������ �� ����(��ȿ�� ���� ���)
public class MemberVo {
	private String name;
	private String userid;
	private String pwd;
	private String email;
	private String phone;
	private int admin;
	private String pictureurl;
	private String selfcomment;
	private int point;
	private String showroompictureurl;
	
	public String getShowroompictureurl() {
		return showroompictureurl;
	}
	public void setShowroompictureurl(String showroompictureurl) {
		this.showroompictureurl = showroompictureurl;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public String getSelfcomment() {
		return selfcomment;
	}
	public void setSelfcomment(String selfcomment) {
		this.selfcomment = selfcomment;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "MemberVo [name=" + name + ", userid=" + userid + ", pwd=" + pwd + ", email=" + email + ", phone="
				+ phone + ", admin=" + admin + ", pictureurl=" + pictureurl + ", selfcomment=" + selfcomment
				+ ", point=" + point + ", showroompictureurl=" + showroompictureurl + "]";
	}
	
	
}
