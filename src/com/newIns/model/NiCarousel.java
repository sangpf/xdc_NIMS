package com.newIns.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component(value = "Carousel")
public class NiCarousel {
	
	private Integer carouselId;  // 主键
	
	private Integer channelId,adId, showOrder,status;
	
	private String adTitle, adImg, adLink;
	private Timestamp pTime,uTime;
	
	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public int getCarouselId() {
		return carouselId;
	}

	public void setCarouselId(int carouselId) {
		this.carouselId = carouselId;
	}

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public int getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
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

	public Timestamp getpTime() {
		return pTime;
	}

	public void setpTime(Timestamp pTime) {
		this.pTime = pTime;
	}

	public Timestamp getuTime() {
		return uTime;
	}

	public void setuTime(Timestamp uTime) {
		this.uTime = uTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

}
