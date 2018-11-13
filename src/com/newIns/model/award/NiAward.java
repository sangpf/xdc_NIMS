package com.newIns.model.award;

/**
 * 奖品信息
 * @author 11409
 * 该实体类 与数据库字段 一一对应
 */
public class NiAward {
	private Integer awardId,awardPoolId,awardNum;
	private String awardPic,reason,awardName,comment;
	private Float price;
	
	public Integer getAwardId() {
		return awardId;
	}
	public void setAwardId(Integer awardId) {
		this.awardId = awardId;
	}
	public Integer getAwardPoolId() {
		return awardPoolId;
	}
	public void setAwardPoolId(Integer awardPoolId) {
		this.awardPoolId = awardPoolId;
	}
	public Integer getAwardNum() {
		return awardNum;
	}
	public void setAwardNum(Integer awardNum) {
		this.awardNum = awardNum;
	}
	public String getAwardPic() {
		return awardPic;
	}
	public void setAwardPic(String awardPic) {
		this.awardPic = awardPic;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAwardName() {
		return awardName;
	}
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	
	
	
}
