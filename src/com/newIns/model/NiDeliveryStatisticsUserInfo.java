package com.newIns.model;

import java.sql.Timestamp;

public class NiDeliveryStatisticsUserInfo {
	
	//用户所有相关信息
	private String userName,idCard,phone,email,wanxNickname,schoolName,regionName,userSn,wanxAccount;
	private int userId,gender,wanxUserId,schoolId,regionId,role,bindCard,bindStudent,wanxScore;
	private Timestamp userCTime;
	private String regionCode;
	private String province;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWanxNickname() {
		return wanxNickname;
	}
	public void setWanxNickname(String wanxNickname) {
		this.wanxNickname = wanxNickname;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getUserSn() {
		return userSn;
	}
	public void setUserSn(String userSn) {
		this.userSn = userSn;
	}
	public String getWanxAccount() {
		return wanxAccount;
	}
	public void setWanxAccount(String wanxAccount) {
		this.wanxAccount = wanxAccount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getWanxUserId() {
		return wanxUserId;
	}
	public void setWanxUserId(int wanxUserId) {
		this.wanxUserId = wanxUserId;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getBindCard() {
		return bindCard;
	}
	public void setBindCard(int bindCard) {
		this.bindCard = bindCard;
	}
	public int getBindStudent() {
		return bindStudent;
	}
	public void setBindStudent(int bindStudent) {
		this.bindStudent = bindStudent;
	}
	public int getWanxScore() {
		return wanxScore;
	}
	public void setWanxScore(int wanxScore) {
		this.wanxScore = wanxScore;
	}
	public Timestamp getUserCTime() {
		return userCTime;
	}
	public void setUserCTime(Timestamp userCTime) {
		this.userCTime = userCTime;
	}
	
	
	
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Override
	public String toString() {
		return "NiDeliveryStatisticsUserInfo [userName=" + userName
				+ ", idCard=" + idCard + ", phone=" + phone + ", email="
				+ email + ", wanxNickname=" + wanxNickname + ", schoolName="
				+ schoolName + ", regionName=" + regionName + ", userSn="
				+ userSn + ", wanxAccount=" + wanxAccount + ", userId="
				+ userId + ", gender=" + gender + ", wanxUserId=" + wanxUserId
				+ ", schoolId=" + schoolId + ", regionId=" + regionId
				+ ", role=" + role + ", bindCard=" + bindCard
				+ ", bindStudent=" + bindStudent + ", wanxScore=" + wanxScore
				+ ", userCTime=" + userCTime + "]";
	}
	
	
}
