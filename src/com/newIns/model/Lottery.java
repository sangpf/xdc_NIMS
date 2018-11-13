package com.newIns.model;

/**@Description  
 * @author MaNia_chAng
 * @time 2016年7月14日 下午3:49:35
 */
public class Lottery {
	int lotteryId,rankNum;
	String lotteryName,comment;
	int award1Id,award2Id,award3Id;
	String award1Rate,award2Rate,award3Rate;
	public int getLotteryId() {
		return lotteryId;
	}
	public void setLotteryId(int lotteryId) {
		this.lotteryId = lotteryId;
	}
	public String getLotteryName() {
		return lotteryName;
	}
	public void setLotteryName(String lotteryName) {
		this.lotteryName = lotteryName;
	}

	public String getAward1Rate() {
		return award1Rate;
	}
	public void setAward1Rate(String award1Rate) {
		this.award1Rate = award1Rate;
	}
	public String getAward2Rate() {
		return award2Rate;
	}
	public void setAward2Rate(String award2Rate) {
		this.award2Rate = award2Rate;
	}
	public String getAward3Rate() {
		return award3Rate;
	}
	public void setAward3Rate(String award3Rate) {
		this.award3Rate = award3Rate;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getRankNum() {
		return rankNum;
	}
	public void setRankNum(int rankNum) {
		this.rankNum = rankNum;
	}

	
	

}
