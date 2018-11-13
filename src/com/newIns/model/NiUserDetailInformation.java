package com.newIns.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NiUserDetailInformation {
	private String wanxOpenId;
	private Integer bindCard;
	private Integer userId;//
	private Integer userChannel;
	private String userCTime;
	private String bindPhoneIMEI;
	private String userName;
	private String wanxNickname;
	private String phone;
	private String email;
	private Integer gender;
	private String birthday;
	private String race;
	private Integer politicsStatus;
	private Integer degree;
	private String jobPosition;
	private String address;
	private Integer marriage;
	private Integer hasChildren;
	private String nationality;
	private String regionName;
	private String schoolName;
	private Integer is985;
	private Integer is211;
	private String college;
	private String department;
	private String major;
	private String classId;
	private String userSn;
	private String jobTitle;
	private String enrolDate;
	
	public String getWanxOpenId() {
		return wanxOpenId;
	}
	public void setWanxOpenId(String wanxOpenId) {
		this.wanxOpenId = wanxOpenId;
	}
	public Integer getBindCard() {
		return bindCard;
	}
	public void setBindCard(Integer bindCard) {
		this.bindCard = bindCard;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserChannel() {
		return userChannel;
	}
	public void setUserChannel(Integer userChannel) {
		this.userChannel = userChannel;
	}
	public String getUserCTime() {
		return userCTime;
	}
	public void setUserCTime(Timestamp userCTime) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.userCTime = sdf.format(userCTime);
	}
	public String getBindPhoneIMEI() {
		return bindPhoneIMEI;
	}
	public void setBindPhoneIMEI(String bindPhoneIMEI) {
		this.bindPhoneIMEI = bindPhoneIMEI;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWanxNickname() {
		return wanxNickname;
	}
	public void setWanxNickname(String wanxNickname) {
		this.wanxNickname = wanxNickname;
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
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.birthday = sdf.format(birthday);
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public Integer getPoliticsStatus() {
		return politicsStatus;
	}
	public void setPoliticsStatus(Integer politicsStatus) {
		this.politicsStatus = politicsStatus;
	}
	public Integer getDegree() {
		return degree;
	}
	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	public String getJobPosition() {
		return jobPosition;
	}
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getMarriage() {
		return marriage;
	}
	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}
	public Integer getHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(Integer hasChildren) {
		this.hasChildren = hasChildren;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public Integer getIs985() {
		return is985;
	}
	public void setIs985(Integer is985) {
		this.is985 = is985;
	}
	public Integer getIs211() {
		return is211;
	}
	public void setIs211(Integer is211) {
		this.is211 = is211;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getUserSn() {
		return userSn;
	}
	public void setUserSn(String userSn) {
		this.userSn = userSn;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getEnrolDate() {
		return enrolDate;
	}
	public void setEnrolDate(Date enrolDate) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.enrolDate = sdf.format(enrolDate);
	}
	
}
