package com.newIns.model;

import java.sql.Timestamp;


public class QuestionnaireStatisticsCube {

	private int userId;// 用户ID
	private int qnId;// 问卷ID
	private Timestamp tapTime;// 时间
	private String qnClassName;// 问卷分类
	private String gender;// 性别
	private String province;// 省份
	private String regionName;// 城市
	private String schoolName;// 学校
	private String tapSource;// 点击来源

	private String qnType;// 问卷类型，1：调查；2：测评；3：投票
	private String eventType;// 事件类型，1：打开，2：提交
	private int timeLength;// 时长
	
	private int timeInterval;// 时段0-23
	private String dayOfWeek;// dayOfWeek = 1代表星期天，dayOfWeek=7代表星期六
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	public int getQnId() {
		return qnId;
	}
	public void setQnId(int qnId) {
		this.qnId = qnId;
	}
	public Timestamp getTapTime() {
		return tapTime;
	}
	public void setTapTime(Timestamp tapTime) {
		this.tapTime = tapTime;
	}
	public String getQnClassName() {
		return qnClassName;
	}
	public void setQnClassName(String qnClassName) {
		this.qnClassName = qnClassName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
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
	public String getTapSource() {
		return tapSource;
	}
	public void setTapSource(String tapSource) {
		this.tapSource = tapSource;
	}
	public String getQnType() {
		return qnType;
	}
	public void setQnType(String qnType) {
		this.qnType = qnType;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public int getTimeLength() {
		return timeLength;
	}
	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
	}
	public int getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(int timeInterval) {
		this.timeInterval = timeInterval;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	
}
