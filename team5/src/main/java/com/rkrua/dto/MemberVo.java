package com.rkrua.dto;

//DTV(VO) 사용 장점
// 1. 중요한 정보 보안 강화 (필드 직접 접근 차단)
// 2. 데이터 관리의 일원화
// 3. 데이터 값 검증(유효한 값만 사용)
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
				+ phone + ", admin=" + admin + ", pictureurl=" + pictureurl + ", point=" + point + ", selfcomment="+ selfcomment +"]";
	}
	
	
}
