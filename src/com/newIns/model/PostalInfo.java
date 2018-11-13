package com.newIns.model;

import java.util.Date;

/**@Description  
 * @author MaNia_chAng
 * @time 2016年7月19日 下午5:38:46
 */
public class PostalInfo {
	int postalId,userId,awardId;
	String awardName,recipientName,recipientPhone,mailAddress,postCode,province,comment,email;
	Date uTime;
	
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public Date getuTime() {
		return uTime;
	}
	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}
	public int getAwardId() {
		return awardId;
	}
	public void setAwardId(int awardId) {
		this.awardId = awardId;
	}
	public String getAwardName() {
		return awardName;
	}
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	
	

}
