package com.newIns.model.niDailyUpdateWanx;

/**
 * 改为通用模板后的三更实体类  支持投放,报告,文章
 * @author sangpf
 *
 */
public class NiDailyUpdateWanx_01 {
	
	private Integer itemId;  //内容id
	private Integer deliveryId,reportId,tweetId,qnId,qnType,
					pageStatus,isTop,showOrder;
	
	private String operator,qnListCategory,comment;
	
	private java.util.Date uTime,pTime,timer;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Integer deliveryId) {
		this.deliveryId = deliveryId;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getTweetId() {
		return tweetId;
	}

	public void setTweetId(Integer tweetId) {
		this.tweetId = tweetId;
	}

	public Integer getQnId() {
		return qnId;
	}

	public void setQnId(Integer qnId) {
		this.qnId = qnId;
	}

	public Integer getQnType() {
		return qnType;
	}

	public void setQnType(Integer qnType) {
		this.qnType = qnType;
	}

	public Integer getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(Integer pageStatus) {
		this.pageStatus = pageStatus;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getQnListCategory() {
		return qnListCategory;
	}

	public void setQnListCategory(String qnListCategory) {
		this.qnListCategory = qnListCategory;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public java.util.Date getuTime() {
		return uTime;
	}

	public void setuTime(java.util.Date uTime) {
		this.uTime = uTime;
	}

	public java.util.Date getpTime() {
		return pTime;
	}

	public void setpTime(java.util.Date pTime) {
		this.pTime = pTime;
	}

	public java.util.Date getTimer() {
		return timer;
	}

	public void setTimer(java.util.Date timer) {
		this.timer = timer;
	} 
	
}
