package com.newIns.model.award;

import java.util.Date;





/**@Description  奖池列表对应model
 * @author fanfan
 * @time 2016年7月15日 上午11:29:53
 */

public class AwardPool {
	private int awardPoolId;
	private int awardType;
	private String awardPoolName;	
	private String img;
	private float equalMoney;
	private String provider;
	private int status;
	private Date  validBTime;
	private Date validETime;
	private int ownerId;
	private int totalNum;
	private String awardDes;
	private String comment;
	private String awardLink;
	public int getAwardPoolId() {
		return awardPoolId;
	}
	public int getAwardType() {
		return awardType;
	}
	public void setAwardType(int awardType) {
		this.awardType = awardType;
	}
	public String getAwardPoolName() {
		return awardPoolName;
	}
	public void setAwardPoolName(String awardPoolName) {
		this.awardPoolName = awardPoolName;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public float getEqualMoney() {
		return equalMoney;
	}
	public void setEqualMoney(float equalMoney) {
		this.equalMoney = equalMoney;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}


	public Date getValidBTime() {
		return validBTime;
	}
	public void setValidBTime(Date validBTime) {
		this.validBTime = validBTime;
	}
	public Date getValidETime() {
		return validETime;
	}
	public void setValidETime(Date validETime) {
		this.validETime = validETime;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public String getAwardDes() {
		return awardDes;
	}
	public void setAwardDes(String awardDes) {
		this.awardDes = awardDes;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setAwardPoolId(int awardPoolId) {
		this.awardPoolId = awardPoolId;
	}
	public String getAwardLink() {
		return awardLink;
	}
	public void setAwardLink(String awardLink) {
		this.awardLink = awardLink;
	}
	
	
	
	

}
