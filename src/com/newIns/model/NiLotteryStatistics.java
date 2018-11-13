package com.newIns.model;
/**
 * @author lj
 * @Description : 抽奖统计的model类
 * @time : 2016年8月16日 下午3:46:11
 */
public class NiLotteryStatistics {
	private int deliveryId,qnId,status,award1Id,award2Id,award3Id;
	private String qnName,award1Name,award2Name,award3Name;
	//渠道,类型
	private int channel,type;
	//有效订单数
	private int validOrderNum;
	//参与抽奖人数
	private int takePartNum;
	//各等级奖品领取人数
	private int award3ReceiveNum,award2ReceiveNum,award1ReceiveNum;
	
	//编码转换[导出表格时使用]
	private String channelStr,typeStr,statusStr;
	
	public int getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}
	public int getQnId() {
		return qnId;
	}
	public void setQnId(int qnId) {
		this.qnId = qnId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAward1Id() {
		return award1Id;
	}
	public void setAward1Id(int award1Id) {
		this.award1Id = award1Id;
	}
	public int getAward2Id() {
		return award2Id;
	}
	public void setAward2Id(int award2Id) {
		this.award2Id = award2Id;
	}
	public int getAward3Id() {
		return award3Id;
	}
	public void setAward3Id(int award3Id) {
		this.award3Id = award3Id;
	}
	public String getQnName() {
		return qnName;
	}
	public void setQnName(String qnName) {
		this.qnName = qnName;
	}
	public String getAward1Name() {
		return award1Name;
	}
	public void setAward1Name(String award1Name) {
		this.award1Name = award1Name;
	}
	public String getAward2Name() {
		return award2Name;
	}
	public void setAward2Name(String award2Name) {
		this.award2Name = award2Name;
	}
	public String getAward3Name() {
		return award3Name;
	}
	public void setAward3Name(String award3Name) {
		this.award3Name = award3Name;
	}
	public int getValidOrderNum() {
		return validOrderNum;
	}
	public void setValidOrderNum(int validOrderNum) {
		this.validOrderNum = validOrderNum;
	}
	public int getTakePartNum() {
		return takePartNum;
	}
	public void setTakePartNum(int takePartNum) {
		this.takePartNum = takePartNum;
	}
	public int getAward3ReceiveNum() {
		return award3ReceiveNum;
	}
	public void setAward3ReceiveNum(int award3ReceiveNum) {
		this.award3ReceiveNum = award3ReceiveNum;
	}
	public int getAward2ReceiveNum() {
		return award2ReceiveNum;
	}
	public void setAward2ReceiveNum(int award2ReceiveNum) {
		this.award2ReceiveNum = award2ReceiveNum;
	}
	public int getAward1ReceiveNum() {
		return award1ReceiveNum;
	}
	public void setAward1ReceiveNum(int award1ReceiveNum) {
		this.award1ReceiveNum = award1ReceiveNum;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getChannelStr() {
		return channelStr;
	}
	public void setChannelStr(String channelStr) {
		this.channelStr = channelStr;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	@Override
	public String toString() {
		return "NiLotteryStatistics [deliveryId=" + deliveryId + ", qnId="
				+ qnId + ", status=" + status + ", award1Id=" + award1Id
				+ ", award2Id=" + award2Id + ", award3Id=" + award3Id
				+ ", qnName=" + qnName + ", award1Name=" + award1Name
				+ ", award2Name=" + award2Name + ", award3Name=" + award3Name
				+ ", channel=" + channel + ", type=" + type
				+ ", validOrderNum=" + validOrderNum + ", takePartNum="
				+ takePartNum + ", award3ReceiveNum=" + award3ReceiveNum
				+ ", award2ReceiveNum=" + award2ReceiveNum
				+ ", award1ReceiveNum=" + award1ReceiveNum + ", channelStr="
				+ channelStr + ", typeStr=" + typeStr + ", statusStr="
				+ statusStr + "]";
	}
	
}
