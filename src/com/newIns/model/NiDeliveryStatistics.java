package com.newIns.model;

import java.sql.Timestamp;

/**
 * @author lj
 * @Description : 投放统计的model类
 * @time : 2016年8月2日 上午11:36:20
 */
public class NiDeliveryStatistics {
	private int deliveryId,answerChannel,channelId,status,type,qnId,answerNum;  //type->调查,测评,投票->1,2,3		answerChannel->玩校,微信,APP->1,2,3
	private int commitNum,finishNum,correctNum,awardNum,questionQty;
	private String qnTitle,showTitle;
	//下载答题明细时用到的
	
	private int qId,questionNum;
	private String choice,qTitle,selfDefine;
	
	//用户所有相关信息
	private String userName,idCard,phone,email,wanxNickname,schoolName,regionName,userSn,wanxAccount;
	private int userId,gender,wanxUserId,schoolId,regionId,role,bindCard,bindStudent,wanxScore;
	private Timestamp userCTime;
	
	private String regionCode;
	private String province;
	
	private String optionDesc;
	
	private Timestamp bTime;
	private Timestamp eTime;
	
	public String getShowTitle() {
		return showTitle;
	}
	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getSelfDefine() {
		return selfDefine;
	}
	public void setSelfDefine(String selfDefine) {
		this.selfDefine = selfDefine;
	}
	public int getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}
	public int getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}
	public int getAnswerChannel() {
		return answerChannel;
	}
	public void setAnswerChannel(int answerChannel) {
		this.answerChannel = answerChannel;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCommitNum() {
		return commitNum;
	}
	public void setCommitNum(int commitNum) {
		this.commitNum = commitNum;
	}
	public int getFinishNum() {
		return finishNum;
	}
	public void setFinishNum(int finishNum) {
		this.finishNum = finishNum;
	}
	public int getCorrectNum() {
		return correctNum;
	}
	public void setCorrectNum(int correctNum) {
		this.correctNum = correctNum;
	}
	public int getAwardNum() {
		return awardNum;
	}
	public void setAwardNum(int awardNum) {
		this.awardNum = awardNum;
	}
	public String getQnTitle() {
		return qnTitle;
	}
	public void setQnTitle(String qnTitle) {
		this.qnTitle = qnTitle;
	}
	public int getQnId() {
		return qnId;
	}
	public void setQnId(int qnId) {
		this.qnId = qnId;
	}
	public int getAnswerNum() {
		return answerNum;
	}
	public void setAnswerNum(int answerNum) {
		this.answerNum = answerNum;
	}

	public int getqId() {
		return qId;
	}
	public void setqId(int qId) {
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
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	
	
	public Timestamp getbTime() {
		return bTime;
	}
	public void setbTime(Timestamp bTime) {
		this.bTime = bTime;
	}
	public Timestamp geteTime() {
		return eTime;
	}
	public void seteTime(Timestamp eTime) {
		this.eTime = eTime;
	}
	@Override
	public String toString() {
		return "NiDeliveryStatistics [deliveryId=" + deliveryId
				+ ", answerChannel=" + answerChannel + ", status=" + status
				+ ", type=" + type + ", qnId=" + qnId + ", answerNum="
				+ answerNum + ", commitNum=" + commitNum + ", finishNum="
				+ finishNum + ", correctNum=" + correctNum + ", awardNum="
				+ awardNum + ", qnTitle=" + qnTitle + ", qId=" + qId
				+ ", choice=" + choice + ", qTitle=" + qTitle + ", userName="
				+ userName + ", idCard=" + idCard + ", phone=" + phone
				+ ", email=" + email + ", wanxNickname=" + wanxNickname
				+ ", schoolName=" + schoolName + ", regionName=" + regionName
				+ ", userSn=" + userSn + ", wanxAccount=" + wanxAccount
				+ ", userId=" + userId + ", gender=" + gender + ", wanxUserId="
				+ wanxUserId + ", schoolId=" + schoolId + ", regionId="
				+ regionId + ", role=" + role + ", bindCard=" + bindCard
				+ ", bindStudent=" + bindStudent + ", wanxScore=" + wanxScore
				+ ", userCTime=" + userCTime + "]";
	}
	public int getQuestionQty() {
		return questionQty;
	}
	public void setQuestionQty(int questionQty) {
		this.questionQty = questionQty;
	}

}
