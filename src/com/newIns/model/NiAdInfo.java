package com.newIns.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;
/**
 * @Description 广告池信息model  
 * @author Guan
 * @time 2016年7月8日 下午2:48:02
 */
@Component(value = "NiAdInfo")
public class NiAdInfo {
	
	private Integer adId,adType,showOrder,adStatus,adCustomerId,replaceAdId;
	private String adTitle, adImg, adLink;
	private Timestamp adCTime;
	
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public Integer getAdType() {
		return adType;
	}
	public void setAdType(Integer adType) {
		this.adType = adType;
	}
	public Integer getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}
	public Integer getAdStatus() {
		return adStatus;
	}
	public void setAdStatus(Integer adStatus) {
		this.adStatus = adStatus;
	}
	public Integer getAdCustomerId() {
		return adCustomerId;
	}
	public void setAdCustomerId(Integer adCustomerId) {
		this.adCustomerId = adCustomerId;
	}
	public Integer getReplaceAdId() {
		return replaceAdId;
	}
	public void setReplaceAdId(Integer replaceAdId) {
		this.replaceAdId = replaceAdId;
	}
	public String getAdTitle() {
		return adTitle;
	}
	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}
	public String getAdImg() {
		return adImg;
	}
	public void setAdImg(String adImg) {
		this.adImg = adImg;
	}
	public String getAdLink() {
		return adLink;
	}
	public void setAdLink(String adLink) {
		this.adLink = adLink;
	}
	public Timestamp getAdCTime() {
		return adCTime;
	}
	public void setAdCTime(Timestamp adCTime) {
		this.adCTime = adCTime;
	}
	
	
}
