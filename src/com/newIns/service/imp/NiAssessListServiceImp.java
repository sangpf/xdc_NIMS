/**
 * 
 */
package com.newIns.service.imp;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.newIns.dao.assess.NiAssessListMapper;
import com.newIns.model.assess.AssessDelivery;
import com.newIns.model.assess.NiAssessListItem;
import com.newIns.model.assess.NiAssessWanx;
import com.newIns.service.NiAssessListService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.StrUtils;
import com.newIns.tools.regex.RegexUtils;

/**
 * @Description
 * @author Guan
 * @time 2016年7月4日 下午4:09:40
 */
@Service
public class NiAssessListServiceImp implements NiAssessListService {
	@Resource
	private NiAssessListMapper niAssessMapper;

	public List<NiAssessListItem> loadAssessList(HashMap<String, Object> retMap) {
		return niAssessMapper.loadAssessList(retMap);
	}

	/**
	 * @Title: postAssessByids
	 * @Author: Guan
	 * @Description: 批量发布测评问卷
	 * @param retMap
	 * @return int
	 * @Time 2016年7月6日 下午1:55:40
	 */
	public int postAssessByids(HashMap<String, Object> retMap) {
		int updateStateByids = niAssessMapper.postAssessByids(retMap);
		return updateStateByids;
	}

	/**
	 * @Title: revokeAssessByids
	 * @Author: Guan
	 * @Description: 批量撤销测评问卷
	 * @param retMap
	 * @return int
	 * @Time 2016年7月6日 下午1:55:40
	 */
	public int revokeAssessByids(HashMap<String, Object> retMap) {
		int updateStateByids = niAssessMapper.revokeAssessByids(retMap);
		return updateStateByids;
	}

	/**
	 * 批量删除问卷
	 */
	public int deleteAssessByIds(HashMap<String, Object> retMap) {
		int deleteAssessByIds = niAssessMapper.deleteAssessByIds(retMap);
		return deleteAssessByIds;
	}

	/**
	 * 置顶测评问卷
	 */
	public void topAssess(HashMap<String, Object> retMap) {
		niAssessMapper.topAssess(retMap);
	}

	/**
	 * 取消置顶
	 */
	public void topCancelAssess(HashMap<String, Object> retMap) {
		niAssessMapper.topCancelAssess(retMap);
	}

	/**
	 * 根据deliveryId从投放表中查询问卷题目
	 */
	public NiAssessListItem searchQnTitleById(int deliveryId) {
		return niAssessMapper.searchQnTitleById(deliveryId);
	}

	/**
	 * 查询测评列表中是否已有该投放
	 */
	public NiAssessListItem searchAssessDeliveryFromList(int deliveryId, String assessListCategory) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("deliveryId", deliveryId);
		map.put("assessListCategory", assessListCategory);
		return niAssessMapper.searchAssessDeliveryFromList(map);
	}

	/**
	 * 将投放添加至列表整体功能service
	 */
	public AjaxResult addAssessDelivery(int delivery_Id, String assessListCategory) {
		NiAssessListItem assessDelivery = searchAssessDeliveryFromList(delivery_Id,assessListCategory);
		// 根据投放Id判断该投放是否在测评列表中
		// 如果不在，则添加一条新的投放
		if (assessDelivery == null) {
			// 根据投放id判断投放表中是否有这条投放
			// 如果有，则成功添加一条记录
			NiAssessListItem assessArg = searchQnTitleById(delivery_Id);
			if (assessArg != null) {
				// 获取当前系统时间
				Timestamp uTime = new Timestamp(System.currentTimeMillis());
				assessArg.setuTime(uTime);
				assessArg.setDeliveryId(delivery_Id);
				assessArg.setAssessListCategory(assessListCategory);
				niAssessMapper.addAssessDeliveryToList(assessArg);
				return AjaxResult.successResult("添加成功");
			} else {
				return AjaxResult.successResult("请输入正确投放id");
			}

		} else {
			return AjaxResult.successResult("该投放在列表中已存在");
		}
	}
	//保存到测一发
	public AjaxResult save_Assess_Page(HttpServletRequest request) {
		String assess_deliveyId_str = request.getParameter("assess_deliveyId");
		String assessListCategory = request.getParameter("assessListCategory");
		
		if(StrUtils.isEmpty(assess_deliveyId_str) || StrUtils.isEmpty(assessListCategory)){
			return AjaxResult.errorResult("投放或栏目未选择");
		}
		
		Integer assess_deliveyId = StrUtils.changeToInt(assess_deliveyId_str);
		
		NiAssessListItem assessDelivery = searchAssessDeliveryFromList(assess_deliveyId,assessListCategory);
		// 根据投放Id判断该投放是否在测评列表中
		// 如果不在，则添加一条新的投放
		if (assessDelivery == null) {
			// 根据投放id判断投放表中是否有这条投放
			// 如果有，则成功添加一条记录
			NiAssessListItem assessArg = searchQnTitleById(assess_deliveyId);
			if (assessArg != null) {
				// 获取当前系统时间
				Timestamp uTime = new Timestamp(System.currentTimeMillis());
				assessArg.setuTime(uTime);
				assessArg.setDeliveryId(assess_deliveyId);
				assessArg.setAssessListCategory(assessListCategory);
				niAssessMapper.addAssessDeliveryToList(assessArg);
				return AjaxResult.successResult("添加成功");
			} else {
				return AjaxResult.successResult("请输入正确投放id");
			}

		} else {
			return AjaxResult.successResult("该投放在列表中已存在");
		}
		
	}

	public AjaxResult replaceAssessDelivery(int deliveryId, int showOrder, String assessListCategory) {
		NiAssessListItem assessDelivery = searchAssessDeliveryFromList(deliveryId, assessListCategory);
		// 根据投放Id判断该投放是否在测评列表中
		// 如果不在，则替换一条新的投放
		if (assessDelivery == null) {
			// 根据投放id判断投放表中是否有这条投放
			// 如果有，则成功添加一条记录
			NiAssessListItem assessArg = searchQnTitleById(deliveryId);
			if (assessArg != null) {
				Timestamp uTime = new Timestamp(System.currentTimeMillis());// 获取当前系统时间
				assessArg.setuTime(uTime);
				assessArg.setDeliveryId(deliveryId);
				assessArg.setShowOrder(showOrder);
				assessArg.setAssessListCategory(assessListCategory);
				niAssessMapper.replaceAssessDelivery(assessArg);
				return AjaxResult.successResult("替换成功");
			} else {
				return AjaxResult.successResult("请输入正确投放id");
			}
		} else {
			return AjaxResult.successResult("该投放在列表中已存在");
		}
	}
	
	public AjaxResult moveUpAssess(int deliveryId,int showOrder,int lastDeliveryId,int lastShowOrder,String assessListCategory){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("deliveryId", deliveryId);
		hashMap.put("showOrder", showOrder);
		hashMap.put("lastDeliveryId", lastDeliveryId);
		hashMap.put("lastShowOrder", lastShowOrder);
		hashMap.put("uTime", uTime);
		hashMap.put("assessListCategory", assessListCategory);
		niAssessMapper.moveUpAssess(hashMap);
		return AjaxResult.successResult("上移成功");
		
	}
	
	public AjaxResult moveDownAssess(int deliveryId,int showOrder,int nextDeliveryId,int nextShowOrder,String assessListCategory){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("deliveryId", deliveryId);
		hashMap.put("showOrder", showOrder);
		hashMap.put("nextDeliveryId", nextDeliveryId);
		hashMap.put("nextShowOrder", nextShowOrder);
		hashMap.put("uTime", uTime);
		hashMap.put("assessListCategory", assessListCategory);
		niAssessMapper.moveDownAssess(hashMap);
		return AjaxResult.successResult("下移成功");
		
	}

	//跳转到添加定时页面
	public void jumpAssessTimerPage(HttpServletRequest request, Model model) {
		String deliveryId_arr = request.getParameter("deliveryId");
		String assessListCategory = request.getParameter("assessListCategory");
		model.addAttribute("deliveryId_arr", deliveryId_arr);
		model.addAttribute("assessListCategory",assessListCategory);
	}

	/**
	 * 保存定时任务
	 */
	public AjaxResult saveAssessTimer(HttpServletRequest request,
			HttpServletResponse response) {
		
		AjaxResult ajaxResult = new AjaxResult();
		String timer_dateStr = request.getParameter("timer_dateStr");
		String deliveryId_Str = request.getParameter("deliveryId");
		String assessListCategory = request.getParameter("assessListCategory");
		if(StrUtils.isEmpty(deliveryId_Str)){
			return AjaxResult.errorResult("保存失败");
		}
		
		Integer deliveryId = Integer.valueOf(deliveryId_Str);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		
		Date parse = null;
		try {
			parse = simpleDateFormat.parse(timer_dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		NiAssessWanx niAssessWanx = new NiAssessWanx();
		
		niAssessWanx.setDeliveryid(deliveryId);
		niAssessWanx.setTimer(parse);
		niAssessWanx.setAssessListCategory(assessListCategory);
		try {
			niAssessMapper.updateStateById_timer(niAssessWanx);
			ajaxResult.put("success", true);
			ajaxResult.put("msg", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("保存失败");
		}
		
		return ajaxResult;
		
		
		
	}

	// 根据投放id , 投放名称查询投放信息
	public AjaxResult search_assess_delivey(HttpServletRequest request,Model model) {
		
		AjaxResult ajaxResult = new AjaxResult();
		
		String deliveryId_Name_str = request.getParameter("deliveryId_Name");
		
		boolean numeric = RegexUtils.isNumeric(deliveryId_Name_str);
		
		List<AssessDelivery> assessDelivery_list = null;
		if(numeric){
			// 输入的是 数字id
			Integer deliveryId = StrUtils.changeToInt(deliveryId_Name_str);
			
			assessDelivery_list = niAssessMapper.select_assessDelivey_key(deliveryId);
			
		}else{
			// 输入的是投放名称
			assessDelivery_list = niAssessMapper.select_assessDelivey_likeName(deliveryId_Name_str);
			
		}
		
		if(assessDelivery_list != null && assessDelivery_list.size()>0){
			ajaxResult.put("success", true);
			ajaxResult.put("assessDelivery_list", assessDelivery_list);
			
		}else{
			return AjaxResult.errorResult("未查询到测评投放");
		}
		
		return ajaxResult;
	}

}
