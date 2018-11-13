package com.newIns.model.page;

import java.util.Date;

public class Page {
	private Integer itemId;  // 主键
	
	private Integer deliveryId,reportId,tweetId,qnId,
	qnType,pageStatus,isTop,showOrder,columnId,channelId;
	
	private String operator,comment;
	
	private Date lastUTime,timer;

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

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getLastUTime() {
		return lastUTime;
	}

	public void setLastUTime(Date lastUTime) {
		this.lastUTime = lastUTime;
	}

	public Date getTimer() {
		return timer;
	}

	public void setTimer(Date timer) {
		this.timer = timer;
	}
	
}
