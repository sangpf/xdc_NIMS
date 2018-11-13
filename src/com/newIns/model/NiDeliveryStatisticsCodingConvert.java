package com.newIns.model;

import java.sql.Timestamp;




/**
 * @author lj
 * @Description : 投放统计编码转换成对应汉字的model类
 * @time : 2016年8月8日 下午9:12:23
 */
public class NiDeliveryStatisticsCodingConvert {
	
	private String deliveryId;
	
	private String answerChannel;//answerChannel->玩校,微信,APP->1,2,3
	
	private String status;//status->1待投放；2投放中；3暂停中；4人工终止；5时间完成；6数量完成
	
	private String type;//type->调查,测评,投票->1,2,3	
	
	private String qnId;
	
	private String answerNum;	
	
	private String commitNum;
	
	private String finishNum;
	
	private String correctNum;
	
	private String awardNum;
	
	private String qnTitle;
	
	private String qId;
	private String choice,qTitle;
	
	//用户所有相关信息
	private String /*userName,*//*idCard,*//*phone,email,*//*wanxNickname,*/schoolName,regionName/*,userSn,*//*wanxAccount*/;
	private String userId,gender,/*wanxUserId,*//*schoolId,regionId,*/role/*,bindCard,*//*bindStudent,wanxScore*/;
//	private String userCTime;
	
	private String regionCode;
	
	private String province;
	
	private String optionDesc;
	
	private String bTime;
	private String eTime;
	
	public String getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
	public String getAnswerChannel() {
		return answerChannel;
	}
	public void setAnswerChannel(String answerChannel) {
		this.answerChannel = answerChannel;
	}
	public String getQnId() {
		return qnId;
	}
	public void setQnId(String qnId) {
		this.qnId = qnId;
	}
	public String getFinishNum() {
		return finishNum;
	}
	public void setFinishNum(String finishNum) {
		this.finishNum = finishNum;
	}
	public String getQnTitle() {
		return qnTitle;
	}
	public void setQnTitle(String qnTitle) {
		this.qnTitle = qnTitle;
	}
	public String getqId() {
		return qId;
	}
	public void setqId(String qId) {
		this.qId = qId;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public String getOptionDesc() {
		return optionDesc;
	}
	public void setOptionDesc(String optionDesc) {
		this.optionDesc = optionDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAnswerNum() {
		return answerNum;
	}
	public void setAnswerNum(String answerNum) {
		this.answerNum = answerNum;
	}
	public String getCommitNum() {
		return commitNum;
	}
	public void setCommitNum(String commitNum) {
		this.commitNum = commitNum;
	}
	public String getCorrectNum() {
		return correctNum;
	}
	public void setCorrectNum(String correctNum) {
		this.correctNum = correctNum;
	}
	public String getAwardNum() {
		return awardNum;
	}
	public void setAwardNum(String awardNum) {
		this.awardNum = awardNum;
	}
	public String getbTime() {
		return bTime;
	}
	public void setbTime(String bTime) {
		this.bTime = bTime;
	}
	public String geteTime() {
		return eTime;
	}
	public void seteTime(String eTime) {
		this.eTime = eTime;
	}
	
}
