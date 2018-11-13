package com.newIns.model.award;

/** 奖品列表对应model
 */
public class Award {
	
	private int awardId,awardNum,awardPoolId;
	private String awardName,provider,awardPoolName,status,awardType,awardPic,comment;
	private float price;
	
	public int getAwardId() {
		return awardId;
	}
	public void setAwardId(int awardId) {
		this.awardId = awardId;
	}
	public int getAwardNum() {
		return awardNum;
	}
	public void setAwardNum(int awardNum) {
		this.awardNum = awardNum;
	}
	public String getAwardName() {
		return awardName;
	}
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getAwardPoolId() {
		return awardPoolId;
	}
	public void setAwardPoolId(int awardPoolId) {
		this.awardPoolId = awardPoolId;
	}
	public String getAwardPoolName() {
		return awardPoolName;
	}
	public void setAwardPoolName(String awardPoolName) {
		this.awardPoolName = awardPoolName;
	}
	public String getAwardType() {
		return awardType;
	}
	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}
	public String getAwardPic() {
		return awardPic;
	}
	public void setAwardPic(String awardPic) {
		this.awardPic = awardPic;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}


	

}
