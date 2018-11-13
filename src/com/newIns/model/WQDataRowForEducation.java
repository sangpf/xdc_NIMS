package com.newIns.model;
/**
 * @Description 存储插入到ni_user_education表中数据的实体
 * @author wanq
 *
 */
public class WQDataRowForEducation {
	private Integer userId;
	private String userSn;
	private Integer role;
	private Integer bindCard;
	private Integer bindStudent;
	private Integer wanxScore;
	private String wanxAccount;
	private Integer schoolId;
	private String schoolName;
	private Integer regionId;
	private String regionName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserSn() {
		return userSn;
	}

	public void setUserSn(String userSn) {
		this.userSn = userSn;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getBindCard() {
		return bindCard;
	}

	public void setBindCard(Integer bindCard) {
		this.bindCard = bindCard;
	}

	public Integer getBindStudent() {
		return bindStudent;
	}

	public void setBindStudent(Integer bindStudent) {
		this.bindStudent = bindStudent;
	}

	public Integer getWanxScore() {
		return wanxScore;
	}

	public void setWanxScore(Integer wanxScore) {
		this.wanxScore = wanxScore;
	}

	public String getWanxAccount() {
		return wanxAccount;
	}

	public void setWanxAccount(String wanxAccount) {
		this.wanxAccount = wanxAccount;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

}
