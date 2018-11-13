package com.newIns.model;
/**
 * @author lj
 * @Description : 定奖统计的model类
 * @time : 2016年8月11日 下午2:53:18
 */
public class NiFixedAwardStatistics {
	private int deliveryId,qnId,status,awardId;
	private String qnName,awardName;
	//类型和渠道
	private int channel,type;
	
	//完成人数和领取人数
	private int finishNum,receiveNum;

	//编码转换字段
	private String channel_str,status_str,type_str;
	
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

	public int getAwardId() {
		return awardId;
	}

	public void setAwardId(int awardId) {
		this.awardId = awardId;
	}

	public String getQnName() {
		return qnName;
	}

	public void setQnName(String qnName) {
		this.qnName = qnName;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public int getFinishNum() {
		return finishNum;
	}

	public void setFinishNum(int finishNum) {
		this.finishNum = finishNum;
	}

	public int getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(int receiveNum) {
		this.receiveNum = receiveNum;
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
	
	public String getChannel_str() {
		return channel_str;
	}

	public void setChannel_str(String channel_str) {
		this.channel_str = channel_str;
	}

	public String getStatus_str() {
		return status_str;
	}

	public void setStatus_str(String status_str) {
		this.status_str = status_str;
	}

	public String getType_str() {
		return type_str;
	}

	public void setType_str(String type_str) {
		this.type_str = type_str;
	}

	@Override
	public String toString() {
		return "NiFixedAwardStatistics [deliveryId=" + deliveryId + ", qnId="
				+ qnId + ", status=" + status + ", awardId=" + awardId
				+ ", qnName=" + qnName + ", awardName=" + awardName
				+ ", channel=" + channel + ", type=" + type + ", finishNum="
				+ finishNum + ", receiveNum=" + receiveNum + ", channel_str="
				+ channel_str + ", status_str=" + status_str + ", type_str="
				+ type_str + "]";
	}

	

	
	
}
