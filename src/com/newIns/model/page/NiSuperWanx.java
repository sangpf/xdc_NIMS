package com.newIns.model.page;

import java.util.Date;

/**
 * 超级调查 页面管理表
 * @author 11409
 *
 */
public class NiSuperWanx{
	
	private Integer deliveryId,qnId,qnType,pageStatus,isTop,showOrder;
	
	private Date uTime,pTime,timer;
	
	private String operator,superListCategory,comment;

	public Integer getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Integer deliveryId) {
		this.deliveryId = deliveryId;
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

	public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

	public Date getpTime() {
		return pTime;
	}

	public void setpTime(Date pTime) {
		this.pTime = pTime;
	}

	public Date getTimer() {
		return timer;
	}

	public void setTimer(Date timer) {
		this.timer = timer;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSuperListCategory() {
		return superListCategory;
	}

	public void setSuperListCategory(String superListCategory) {
		this.superListCategory = superListCategory;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}