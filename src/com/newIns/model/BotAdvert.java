package com.newIns.model;

import org.springframework.stereotype.Component;

/**@Description  
 * @author MaNia_chAng
 * @time 2016年5月23日 下午2:58:56
 */
@Component(value = "BotAdvert")
public class BotAdvert {
	String adId, adPosDes,adLink,adImg,adTitle;

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getAdPosDes() {
		return adPosDes;
	}

	public void setAdPosDes(String adPosDes) {
		this.adPosDes = adPosDes;
	}

	public String getAdLink() {
		return adLink;
	}

	public void setAdLink(String adLink) {
		this.adLink = adLink;
	}

	public String getAdImg() {
		return adImg;
	}

	public void setAdImg(String adImg) {
		this.adImg = adImg;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	@Override
	public String toString() {
		return "BotAdvert [adId=" + adId + ", adPosDes=" + adPosDes
				+ ", adLink=" + adLink + ", adImg=" + adImg + ", adTitle="
				+ adTitle + "]";
	}


}
