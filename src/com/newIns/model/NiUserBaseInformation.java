package com.newIns.model;

import java.util.Date;

public class NiUserBaseInformation {
	private Integer userId;
	private String userName;
	private String phone;
	private Integer gender;
	private String schoolName;
	private Integer userChannel;
	private Date userFirstCTime;
	private Date userCTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getUserChannel() {
		return userChannel;
	}

	public void setUserChannel(Integer userChannel) {
		this.userChannel = userChannel;
	}

	public Date getUserFirstCTime() {
		return userFirstCTime;
	}

	public void setUserFirstCTime(Date userFirstCTime) {
		this.userFirstCTime = userFirstCTime;
	}

	public Date getUserCTime() {
		return userCTime;
	}

	public void setUserCTime(Date userCTime) {
		this.userCTime = userCTime;
	}

}
