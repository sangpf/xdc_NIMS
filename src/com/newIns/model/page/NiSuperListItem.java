/**
 * 
 */
package com.newIns.model.page;

import java.sql.Timestamp;

/**
 * @Description
 * @author Guan
 * @time 2016年7月11日 下午3:33:49
 */

public class NiSuperListItem {
	int deliveryId, pageStatus, status, collectNum,isTop,showOrder,qnType;
	String showTitle,superListCategory;  
	Timestamp uTime, pTime;

	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}

	public int getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(int pageStatus) {
		this.pageStatus = pageStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public Timestamp getuTime() {
		return uTime;
	}

	public void setuTime(Timestamp uTime) {
		this.uTime = uTime;
	}

	public Timestamp getpTime() {
		return pTime;
	}

	public void setpTime(Timestamp pTime) {
		this.pTime = pTime;
	}

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public int getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	public int getQnType() {
		return qnType;
	}

	public void setQnType(int qnType) {
		this.qnType = qnType;
	}

	public String getSuperListCategory() {
		return superListCategory;
	}

	public void setSuperListCategory(String superListCategory) {
		this.superListCategory = superListCategory;
	}

}
