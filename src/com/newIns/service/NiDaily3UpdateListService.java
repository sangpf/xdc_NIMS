/**
 * 
 */
package com.newIns.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.newIns.model.niDailyUpdateWanx.NiDailyUpdateWanx_VO_01;
import com.newIns.model.page.NiDaily3UpdateListItem;
import com.newIns.tools.AjaxResult;

/**
 * @Description 超级调查service接口
 * @author Guan
 * @time 2016年7月4日 下午4:03:11
 */

public interface NiDaily3UpdateListService {
	
	/**
	 * 跳转到打开定时器时间页面
	 */
	void jumpDaily3UpdateTimerPage(HttpServletRequest request,Model model);
	/**
	 * 保存定时任务
	 */
	AjaxResult daily3UpdateTimerSave(HttpServletRequest request,HttpServletResponse response);
	
	List<NiDailyUpdateWanx_VO_01> loadDaily3UpdateList(HashMap<String, Object> retMap);

	int postDaily3UpdateByids(HashMap<String, Object> retMap);

	int revokeDaily3UpdateByids(HashMap<String, Object> retMap);

	int deleteDaily3UpdateByIds(HashMap<String, Object> retMap);

	//置顶
	void topDaily3Update(HashMap<String, Object> hashMap);

	void topCancelDaily3Update(HashMap<String, Object> hashMap);

	NiDaily3UpdateListItem d3searchQnTitleById(HashMap<String, Object> hashMap);

	NiDaily3UpdateListItem searchDaily3UpdateDeliveryFromList(int deliveryId,
			int qnType);

	AjaxResult addDaily3UpdateDelivery(HttpServletRequest request, Model model);

	AjaxResult replaceDaily3UpdateDelivery(int deliveryId, int showOrder,
			int qnType);

	AjaxResult moveUpDaily3Update(String itemId,String showOrder,String lastItemId,String lastShowOrder);

	//下移
	AjaxResult moveDownDaily3Update(String itemId,String showOrder,String nextitemId,String nextShowOrder);
	
	//编辑前跳转页面
	void editDaily3UpdateList(HttpServletRequest request, Model model);
	//编辑三更 保存
	AjaxResult editDaily3UpdateDelivery(HttpServletRequest request,Model model);
}
