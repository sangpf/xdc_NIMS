package com.newIns.model;


import java.util.Date;

/**
 * @author lj
 * @Description : 玩校积分统计的model
 * @time : 2016年8月25日 下午5:31:37
 */
public class NiWanxPointStatistics {
	//private int userAwardFlowId;
	private int qnChannel,awardNum;
	private Date awardGetTime;
	//定奖积分,抽奖积分,活动积分,总积分
	private int fixedPoint,lotteryPoint,activityPoint,sumPoint;
	private String date;
	//编码转换[渠道]
	private String qnChannelStr;
	public int getQnChannel() {
		return qnChannel;
	}
	public void setQnChannel(int qnChannel) {
		this.qnChannel = qnChannel;
	}
	public int getAwardNum() {
		return awardNum;
	}
	public void setAwardNum(int awardNum) {
		this.awardNum = awardNum;
	}
	public Date getAwardGetTime() {
		return awardGetTime;
	}
	public void setAwardGetTime(Date awardGetTime) {
		this.awardGetTime = awardGetTime;
	}
	public int getFixedPoint() {
		return fixedPoint;
	}
	public void setFixedPoint(int fixedPoint) {
		this.fixedPoint = fixedPoint;
	}
	public int getLotteryPoint() {
		return lotteryPoint;
	}
	public void setLotteryPoint(int lotteryPoint) {
		this.lotteryPoint = lotteryPoint;
	}
	public int getActivityPoint() {
		return activityPoint;
	}
	public void setActivityPoint(int activityPoint) {
		this.activityPoint = activityPoint;
	}
	public int getSumPoint() {
		return sumPoint;
	}
	public void setSumPoint(int sumPoint) {
		this.sumPoint = sumPoint;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getQnChannelStr() {
		return qnChannelStr;
	}
	public void setQnChannelStr(String qnChannelStr) {
		this.qnChannelStr = qnChannelStr;
	}
	@Override
	public String toString() {
		return "NiWanxPointStatistics [qnChannel=" + qnChannel + ", awardNum="
				+ awardNum + ", awardGetTime=" + awardGetTime + ", fixedPoint="
				+ fixedPoint + ", lotteryPoint=" + lotteryPoint
				+ ", activityPoint=" + activityPoint + ", sumPoint=" + sumPoint
				+ ", date=" + date + ", qnChannelStr=" + qnChannelStr + "]";
	}
	
	
	
}
