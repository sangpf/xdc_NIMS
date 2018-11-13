package com.newIns.model;

/**
 * @Description
 * @author lj
 * @time 2016年7月27日 下午2:33:48
 */
public class NiAdStatistics {
	private int adId,adChannel,adViewCount,adTapCount,adStatus;
	private String adPosCode,Comment;
	private String adTitle;
	public int getAdId() {
		return adId;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	public int getAdChannel() {
		return adChannel;
	}
	public void setAdChannel(int adChannel) {
		this.adChannel = adChannel;
	}
	public int getAdViewCount() {
		return adViewCount;
	}
	public void setAdViewCount(int adViewCount) {
		this.adViewCount = adViewCount;
	}
	public int getAdTapCount() {
		return adTapCount;
	}
	public void setAdTapCount(int adTapCount) {
		this.adTapCount = adTapCount;
	}
	public String getAdPosCode() {
		return adPosCode;
	}
	public void setAdPosCode(String adPosCode) {
		this.adPosCode = adPosCode;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	public String getAdTitle() {
		return adTitle;
	}
	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public int getAdStatus() {
		return adStatus;
	}
	public void setAdStatus(int adStatus) {
		this.adStatus = adStatus;
	}
	@Override
	public String toString() {
		return "NiAdStatistics [adId=" + adId + ", adChannel=" + adChannel
				+ ", adViewCount=" + adViewCount + ", adTapCount=" + adTapCount
				+ ", adStatus=" + adStatus + ", adPosCode=" + adPosCode
				+ ", Comment=" + Comment + ", adTitle=" + adTitle + "]";
	}

	
	
}
