package com.newIns.data_processing.po;

import java.io.Serializable;

public class Download_AnswerDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer userId,wanxUserId,qnId,qId,questionNum,questionQty;
	private String wanxNickname,choice,schoolName,regionName,showTitle,qTitle,gender,questionType;
	
	public Integer getQuestionQty() {
		return questionQty;
	}
	public void setQuestionQty(Integer questionQty) {
		this.questionQty = questionQty;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getWanxUserId() {
		return wanxUserId;
	}
	public void setWanxUserId(Integer wanxUserId) {
		this.wanxUserId = wanxUserId;
	}
	public Integer getQnId() {
		return qnId;
	}
	public void setQnId(Integer qnId) {
		this.qnId = qnId;
	}
	public Integer getqId() {
		return qId;
	}
	public void setqId(Integer qId) {
		this.qId = qId;
	}
	public Integer getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}
	public String getWanxNickname() {
		return wanxNickname;
	}
	public void setWanxNickname(String wanxNickname) {
		this.wanxNickname = wanxNickname;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
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
	public String getShowTitle() {
		return showTitle;
	}
	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
}
