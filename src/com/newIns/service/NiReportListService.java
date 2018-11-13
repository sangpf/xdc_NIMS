/**
 * 
 */
package com.newIns.service;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.report.NiReportListItem;
import com.newIns.tools.AjaxResult;

/**
 * @Description
 * @author Guan
 * @time 2016年7月4日 下午4:03:11
 */

public interface NiReportListService {
	List<NiReportListItem> loadReportList(HashMap<String, Object> retMap);

	int postReportByids(HashMap<String, Object> retMap);

	int revokeReportByids(HashMap<String, Object> retMap);
	
    int deleteReportByIds(HashMap<String, Object> retMap);
	
	void topReport(HashMap<String, Object> hashMap);
	
	void topCancelReport(HashMap<String, Object> hashMap);
	
	NiReportListItem searchReportTitleById(int reportId);
	
	NiReportListItem searchReportFromList(int reportId);
	
	AjaxResult addReport(int reportId);
	
	AjaxResult replaceReport(int reportId,int showOrder);
	
	AjaxResult moveUpReport(int reportId,int showOrder,int lastReportId,int lastShowOrder);
	
	AjaxResult moveDownReport(int reportId,int showOrder,int nextReportId,int nextShowOrder);
}
