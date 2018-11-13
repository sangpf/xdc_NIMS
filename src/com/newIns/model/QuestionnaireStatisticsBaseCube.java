package com.newIns.model;

import java.sql.Timestamp;
import java.util.Date;

public class QuestionnaireStatisticsBaseCube {
	private int userId;// 用户ID
	private int qnid;// 问卷ID
	private Timestamp tapTime;// 时间
	private String qnClassName;// 问卷分类
	private int gender;// 性别
	private String province;// 省份
	private String regionName;// 城市
	private String schoolName;// 学校
	private int tapSource;// 点击来源

	private int qnType;// 问卷类型，1：调查；2：测评；3：投票
	private int eventType;// 事件类型，1：打开，2：提交
	private int timeLength;// 时长
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getQnid() {
		return qnid;
	}
	public void setQnid(int qnid) {
		this.qnid = qnid;
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
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
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
	public int getTapSource() {
		return tapSource;
	}
	public void setTapSource(int tapSource) {
		this.tapSource = tapSource;
	}
	public int getQnType() {
		return qnType;
	}
	public void setQnType(int qnType) {
		this.qnType = qnType;
	}
	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public int getTimeLength() {
		return timeLength;
	}
	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
	}

	
}
