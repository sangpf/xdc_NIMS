/**
 * 
 */
package com.newIns.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.newIns.model.page.NiSuperListItem;
import com.newIns.tools.AjaxResult;

/**
 * @Description 超级调查service接口
 * @author Guan
 * @time 2016年7月4日 下午4:03:11
 */

public interface NiSuperListService {
	/**
	 * 保存定时任务
	 */
	AjaxResult superListTimerSave(HttpServletRequest request,HttpServletResponse response);
	/**
	 * 跳转到定时页面
	 */
	void jumpToTimerSuperPage(HttpServletRequest request,Model model);
	
	List<NiSuperListItem> loadSuperList(HashMap<String, Object> retMap);

	int postSuperByids(HashMap<String, Object> retMap);

	int revokeSuperByids(HashMap<String, Object> retMap);
	
    int deleteSuperByIds(HashMap<String, Object> retMap);
	
	void topSuper(HashMap<String, Object> hashMap);
	
	void topCancelSuper(HashMap<String, Object> hashMap);
	
	NiSuperListItem searchQnTitleById(HashMap<String, Object> hashMap);
	
	NiSuperListItem searchSuperDeliveryFromList(int deliveryId,int qnType);
	
	AjaxResult addSuperDelivery(int deliveryId,int qnType);
	
	AjaxResult replaceSuperDelivery(int deliveryId,int showOrder,int qnType);

	AjaxResult moveUpSuper(int deliveryId, int qnType,int showOrder, int lastDeliveryId,int lastQnType,
			int lastShowOrder, HttpServletRequest request);

	AjaxResult moveDownSuper(int deliveryId, int qnType, int showOrder,
			int nextDeliveryId,int nextQnType, int nextShowOrder, HttpServletRequest request);
	
	//添加 超级页面列表 时 根据 投放id 问卷类型 页面类型 查询投放是否存在
	void searchSuperListByDevId(HttpServletRequest request, Model model);
	
	AjaxResult saveSuperListCategory(HttpServletRequest request, Model model);
}
