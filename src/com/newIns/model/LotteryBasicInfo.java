package com.newIns.model;


/**@Description  
 * @author MaNia_chAng
 * @time 2016年8月5日 上午10:56:45
 */
public class LotteryBasicInfo {
	
	String awardTime,comment;
	int deliveryId,qnType,channel,sequenceNum,lotteryId,awardRank,userId,collectNum;
	
	double[] awardRate = new double[3];
	public String getAwardTime() {
		return awardTime;
	}
	public void setAwardTime(String awardTime) {
		this.awardTime = awardTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}
	public int getQnType() {
		return qnType;
	}
	public void setQnType(int qnType) {
		this.qnType = qnType;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public int getSequenceNum() {
		return sequenceNum;
	}
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public int getLotteryId() {
		return lotteryId;
	}
	public void setLotteryId(int lotteryId) {
		this.lotteryId = lotteryId;
	}
	public int getAwardRank() {
		return awardRank;
	}
	public void setAwardRank(int awardRank) {
		this.awardRank = awardRank;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}
	public double[] getAwardRate() {
		return awardRate;
	}
	public void setAwardRate(double[] awardRate) {
		this.awardRate = awardRate;
	}
	
}
