package com.newIns.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component(value = "NiBanner")
public class NiBanner {
	int adId, status, adPos;
	String adTitle, adImg, adLink, adPosDes;
	Timestamp pTime, uTime;

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAdPos() {
		return adPos;
	}

	public void setAdPos(int adPos) {
		this.adPos = adPos;
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

	public String getAdPosDes() {
		return adPosDes;
	}

	public void setAdPosDes(String adPosDes) {
		this.adPosDes = adPosDes;
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

}
