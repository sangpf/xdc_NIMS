package com.newIns.model;

import java.io.Serializable;

public class NiOaManageUser implements Serializable{
	
	private int oaUserId;
	private String oaUserName;
	private String oaPassword;
	private int isValid;
	private String comment;
	
	public int getOaUserId() {
		return oaUserId;
	}
	public void setOaUserId(int oaUserId) {
		this.oaUserId = oaUserId;
	}
	public String getOaUserName() {
		return oaUserName;
	}
	public void setOaUserName(String oaUserName) {
		this.oaUserName = oaUserName;
	}
	public String getOaPassword() {
		return oaPassword;
	}
	public void setOaPassword(String oaPassword) {
		this.oaPassword = oaPassword;
	}
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
