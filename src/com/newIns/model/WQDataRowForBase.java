package com.newIns.model;

import java.sql.Timestamp;
/**
 * @Description 存储插入到ni_user_base表中数据的实体
 * @author wanq
 *
 */
public class WQDataRowForBase {
	private Integer userId;
	private String phone;
	private String userName;
	private Integer gender;
	private String idCard;
	private Timestamp userCTime;
	private String email;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Timestamp getUserCTime() {
		return userCTime;
	}

	public void setUserCTime(Timestamp userCTime) {
		this.userCTime = userCTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
