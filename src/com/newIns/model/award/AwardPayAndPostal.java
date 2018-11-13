package com.newIns.model.award;

import java.sql.Date;

/**
 * @Description  用于导出所有实物奖励的excel表格，包含了AwardPay的全部信息和邮寄信息
 * @author Lijz
 */
public class AwardPayAndPostal {
	int userId,awardId,orderId,postalId;
	String userName,qnName,awardName,qnType,recipientName,recipientPhone,awardGetStatus,awardPayStatus,mailAddress,postCode,province,comment,email;
	Date awardGetTime,awardPayTime;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPostalId() {
		return postalId;
	}
	public void setPostalId(int postalId) {
		this.postalId = postalId;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getRecipientPhone() {
		return recipientPhone;
	}
	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAwardId() {
		return awardId;
	}
	public void setAwardId(int awardId) {
		this.awardId = awardId;
	}
	public String getAwardGetStatus() {
		return awardGetStatus;
	}
	public void setAwardGetStatus(String awardGetStatus) {
		this.awardGetStatus = awardGetStatus;
	}
	public String getAwardPayStatus() {
		return awardPayStatus;
	}
	public void setAwardPayStatus(String awardPayStatus) {
		this.awardPayStatus = awardPayStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getQnName() {
		return qnName;
	}
	public void setQnName(String qnName) {
		this.qnName = qnName;
	}
	public String getAwardName() {
		return awardName;
	}
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	public Date getAwardGetTime() {
		return awardGetTime;
	}
	public void setAwardGetTime(Date awardGetTime) {
		this.awardGetTime = awardGetTime;
	}
	public Date getAwardPayTime() {
		return awardPayTime;
	}
	public void setAwardPayTime(Date awardPayTime) {
		this.awardPayTime = awardPayTime;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getQnType() {
		return qnType;
	}
	public void setQnType(String qnType) {
		this.qnType = qnType;
	}
	

	
	

}