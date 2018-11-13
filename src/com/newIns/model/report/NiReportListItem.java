/**
 * 
 */
package com.newIns.model.report;

import java.sql.Timestamp;

/**
 * @Description
 * @author Guan
 * @time 2016年7月11日 下午3:33:49
 */

public class NiReportListItem {
	int reportId, pageStatus, reportStatus, isTop, showOrder;
	String reportTitle;
	Timestamp uTime, pTime;

	public int getDeliveryId() {
		return reportId;
	}

	public void setDeliveryId(int reportId) {
		this.reportId = reportId;
	}

	public int getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(int pageStatus) {
		this.pageStatus = pageStatus;
	}

	public int getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(int reportStatus) {
		this.reportStatus = reportStatus;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
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

}
