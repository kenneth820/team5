package com.rkrua.dto;

import java.sql.Timestamp;

public class CommentVo {
	String userid;
	int replynumber;
	String replier;
	String reply;
	
	Timestamp writedate;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getReplynumber() {
		return replynumber;
	}
	public void setReplynumber(int replynumber) {
		this.replynumber = replynumber;
	}
	public String getReplier() {
		return replier;
	}
	public void setReplier(String replier) {
		this.replier = replier;
	}
	public Timestamp getWritedate() {
		return writedate;
	}
	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	@Override
	public String toString() {
		return "CommentVo [userid=" + userid + ", replynumber=" + replynumber + ", replier=" + replier + ", reply="
				+ reply + ", writedate=" + writedate + "]";
	}
}
