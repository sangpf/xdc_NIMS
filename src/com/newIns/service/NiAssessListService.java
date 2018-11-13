/**
 * 
 */
package com.newIns.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.newIns.model.assess.NiAssessListItem;
import com.newIns.tools.AjaxResult;

/**
 * @Description
 * @author Guan
 * @time 2016年7月4日 下午4:03:11
 */

public interface NiAssessListService {
	
	/**
	 * 保存定时任务
	 */
	AjaxResult saveAssessTimer(HttpServletRequest request,HttpServletResponse response);
	/**
	 * 跳转到添加定时页面
	 */
	void jumpAssessTimerPage(HttpServletRequest request,Model model);
	
	List<NiAssessListItem> loadAssessList(HashMap<String, Object> retMap);

	int postAssessByids(HashMap<String, Object> retMap);

	int revokeAssessByids(HashMap<String, Object> retMap);
	
    int deleteAssessByIds(HashMap<String, Object> retMap);
	
	void topAssess(HashMap<String, Object> hashMap);
	
	void topCancelAssess(HashMap<String, Object> hashMap);
	
	NiAssessListItem searchQnTitleById(int deliveryId);
	
	NiAssessListItem searchAssessDeliveryFromList(int deliveryId, String assessListCategory);
	
	AjaxResult addAssessDelivery(int deliveryId, String assessListCategory);
	
	AjaxResult replaceAssessDelivery(int deliveryId,int showOrder,String assessListCategory);
	
	AjaxResult moveUpAssess(int deliveryId,int showOrder,int lastDeliveryId,int lastShowOrder,String assessListCategory);
	
	AjaxResult moveDownAssess(int deliveryId,int showOrder,int nextDeliveryId,int nextShowOrder,String assessListCategory);
	AjaxResult search_assess_delivey(HttpServletRequest request, Model model);
	AjaxResult save_Assess_Page(HttpServletRequest request);
}
