package com.newIns.model.redeemCode;

import java.io.Serializable;
import java.util.Date;

public class RedeemCode implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id,awardPoolId,userId,grantStatus;
	private String redeemCode,provider,phoneNum,comment;
	private Date grantTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAwardPoolId() {
		return awardPoolId;
	}
	public void setAwardPoolId(Integer awardPoolId) {
		this.awardPoolId = awardPoolId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getGrantStatus() {
		return grantStatus;
	}
	public void setGrantStatus(Integer grantStatus) {
		this.grantStatus = grantStatus;
	}
	public String getRedeemCode() {
		return redeemCode;
	}
	public void setRedeemCode(String redeemCode) {
		this.redeemCode = redeemCode;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getGrantTime() {
		return grantTime;
	}
	public void setGrantTime(Date grantTime) {
		this.grantTime = grantTime;
	}
	
	
	
}
