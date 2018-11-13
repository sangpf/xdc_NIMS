/**
 * 
 */
package com.newIns.service.imp;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.NiReportListMapper;
import com.newIns.model.report.NiReportListItem;
import com.newIns.service.NiReportListService;
import com.newIns.tools.AjaxResult;

/**
 * @Description
 * @author Guan
 * @time 2016年7月4日 下午4:09:40
 */
@Service
public class NiReportListServiceImp implements NiReportListService {
	@Resource
	private NiReportListMapper niReportListMapper;

	public List<NiReportListItem> loadReportList(HashMap<String, Object> retMap) {
		return niReportListMapper.loadReportList(retMap);
	}

	/**
	 * @Title: postReportByids
	 * @Author: Guan
	 * @Description: 批量发布测评问卷
	 * @param retMap
	 * @return int
	 * @Time 2016年7月6日 下午1:55:40
	 */
	public int postReportByids(HashMap<String, Object> retMap) {
		int updateStateByids = niReportListMapper.postReportByids(retMap);
		return updateStateByids;
	}

	/**
	 * @Title: revokeReportByids
	 * @Author: Guan
	 * @Description: 批量撤销测评问卷
	 * @param retMap
	 * @return int
	 * @Time 2016年7月6日 下午1:55:40
	 */
	public int revokeReportByids(HashMap<String, Object> retMap) {
		int updateStateByids = niReportListMapper.revokeReportByids(retMap);
		return updateStateByids;
	}

	/**
	 * 批量删除问卷
	 */
	public int deleteReportByIds(HashMap<String, Object> retMap) {
		int deleteReportByIds = niReportListMapper.deleteReportByIds(retMap);
		return deleteReportByIds;
	}

	/**
	 * 置顶测评问卷
	 */
	public void topReport(HashMap<String, Object> retMap) {
		niReportListMapper.topReport(retMap);
	}

	/**
	 * 取消置顶
	 */
	public void topCancelReport(HashMap<String, Object> retMap) {
		niReportListMapper.topCancelReport(retMap);
	}

	/**
	 * 根据reportId从投放表中查询问卷题目
	 */
	public NiReportListItem searchReportTitleById(int reportId) {
		return niReportListMapper.searchReportTitleById(reportId);
	}

	/**
	 * 查询测评列表中是否已有该投放
	 */
	public NiReportListItem searchReportFromList(int reportId) {
		return niReportListMapper.searchReportFromList(reportId);
	}

	/**
	 * 将投放添加至列表整体功能service
	 */
	public AjaxResult addReport(int report_Id) {
		NiReportListItem reportItem = searchReportFromList(report_Id);
		// 根据投放Id判断该投放是否在测评列表中
		// 如果不在，则添加一条新的投放
		if (reportItem == null) {
			// 根据投放id判断投放表中是否有这条投放
			// 如果有，则成功添加一条记录
			NiReportListItem reportArg = searchReportTitleById(report_Id);
			if (reportArg != null) {
				// 获取当前系统时间
				Timestamp uTime = new Timestamp(System.currentTimeMillis());
				reportArg.setuTime(uTime);
				reportArg.setReportId(report_Id);
				niReportListMapper.addReportToList(reportArg);
				return AjaxResult.successResult("添加成功");
			} else {
				return AjaxResult.successResult("请输入正确投放id");
			}

		} else {
			return AjaxResult.successResult("该投放在列表中已存在");
		}
	}

	public AjaxResult replaceReport(int reportId, int showOrder) {
		NiReportListItem report = searchReportFromList(reportId);
		// 根据投放Id判断该投放是否在测评列表中
		// 如果不在，则替换一条新的投放
		if (report == null) {
			// 根据投放id判断投放表中是否有这条投放
			// 如果有，则成功添加一条记录
			NiReportListItem reportArg = searchReportTitleById(reportId);
			if (reportArg != null) {
				Timestamp uTime = new Timestamp(System.currentTimeMillis());// 获取当前系统时间
				reportArg.setuTime(uTime);
				reportArg.setReportId(reportId);
				reportArg.setShowOrder(showOrder);
				niReportListMapper.replaceReport(reportArg);
				return AjaxResult.successResult("替换成功");
			} else {
				return AjaxResult.successResult("请输入正确投放id");
			}
		} else {
			return AjaxResult.successResult("该投放在列表中已存在");
		}
	}
	
	public AjaxResult moveUpReport(int reportId,int showOrder,int lastReportId,int lastShowOrder){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("reportId", reportId);
		hashMap.put("showOrder", showOrder);
		hashMap.put("lastReportId", lastReportId);
		hashMap.put("lastShowOrder", lastShowOrder);
		hashMap.put("uTime", uTime);
		niReportListMapper.moveUpReport(hashMap);
		return AjaxResult.successResult("上移成功");
		
	}
	
	public AjaxResult moveDownReport(int reportId,int showOrder,int nextReportId,int nextShowOrder){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("reportId", reportId);
		hashMap.put("showOrder", showOrder);
		hashMap.put("nextReportId", nextReportId);
		hashMap.put("nextShowOrder", nextShowOrder);
		hashMap.put("uTime", uTime);
		niReportListMapper.moveDownReport(hashMap);
		return AjaxResult.successResult("下移成功");
		
	}
}
