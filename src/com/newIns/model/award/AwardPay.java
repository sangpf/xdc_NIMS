package com.newIns.model.award;

import java.sql.Date;

/**@Description  
 * @author MaNia_chAng
 * @time 2016年7月19日 下午3:19:05
 */
public class AwardPay {
	int userId,awardId,awardGetStatus,awardPayStatus,orderId;
	String userName,qnName,awardName,qnType;
	Date awardGetTime,awardPayTime;
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
	public int getAwardGetStatus() {
		return awardGetStatus;
	}
	public void setAwardGetStatus(int awardGetStatus) {
		this.awardGetStatus = awardGetStatus;
	}
	public int getAwardPayStatus() {
		return awardPayStatus;
	}
	public void setAwardPayStatus(int awardPayStatus) {
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
