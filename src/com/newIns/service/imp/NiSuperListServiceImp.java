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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.newIns.dao.NiSuperListMapper;
import com.newIns.dao.NiSuperWanxMapper;
import com.newIns.model.page.NiSuperListItem;
import com.newIns.model.page.NiSuperWanx;
import com.newIns.model.vo.SuperListCategory;
import com.newIns.service.NiSuperListService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.StrUtils;
import com.newIns.web.page.NiSuperListController;

/**
 * @Description
 * @author Guan
 * @time 2016年7月4日 下午4:09:40
 */
@Service
public class NiSuperListServiceImp implements NiSuperListService {
	@Resource
	private NiSuperListMapper niSuperMapper;
	@Autowired
	private NiSuperWanxMapper niSuperWanxMapper;
	

	public List<NiSuperListItem> loadSuperList(HashMap<String, Object> retMap) {
		return niSuperMapper.loadSuperList(retMap);
	}

	/**
	 * @Title: postSuperByids
	 * @Author: Guan
	 * @Description: 批量发布超级调查问卷
	 * @param retMap
	 * @return int
	 * @Time 2016年7月6日 下午1:55:40
	 */
	public int postSuperByids(HashMap<String, Object> retMap) {
		int updateStateByids = niSuperMapper.postSuperByids(retMap);
		return updateStateByids;
	}

	/**
	 * @Title: revokeSuperByids
	 * @Author: Guan
	 * @Description: 批量撤销超级调查问卷
	 * @param retMap
	 * @return int
	 * @Time 2016年7月6日 下午1:55:40
	 */
	public int revokeSuperByids(HashMap<String, Object> retMap) {
		int updateStateByids = niSuperMapper.revokeSuperByids_super(retMap);
		return updateStateByids;
	}

	/**
	 * 批量删除问卷
	 */
	public int deleteSuperByIds(HashMap<String, Object> retMap) {
		int deleteSuperByIds = niSuperMapper.deleteSuperByIds(retMap);
		return deleteSuperByIds;
	}

	/**
	 * 置顶超级调查问卷
	 */
	public void topSuper(HashMap<String, Object> retMap) {
		niSuperMapper.topSuper(retMap);
	}

	/**
	 * 取消置顶
	 */
	public void topCancelSuper(HashMap<String, Object> retMap) {
		niSuperMapper.topCancelSuper(retMap);
	}

	/**
	 * 根据deliveryId从投放表中查询问卷题目
	 */
	public NiSuperListItem searchQnTitleById(HashMap<String, Object> retMap) {
		return niSuperMapper.searchQnTitleById(retMap);
	}

	/**
	 * 查询超级调查列表中是否已有该投放
	 */
	public NiSuperListItem searchSuperDeliveryFromList(int deliveryId,int qnType) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("qnType", qnType);
		hashMap.put("deliveryId", deliveryId);
		return niSuperMapper.searchSuperDeliveryFromList(hashMap);
	}

	/**
	 * 将投放添加至列表整体功能service
	 */
	public AjaxResult addSuperDelivery(int deliveryId,int qnType) {
		NiSuperListItem superDelivery = searchSuperDeliveryFromList(deliveryId,qnType);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("qnType", qnType);
		hashMap.put("deliveryId", deliveryId);
		// 根据投放Id和问卷类型判断该投放是否在超级调查列表中
		// 如果不在，则添加一条新的投放
		if (superDelivery == null) {
			// 根据投放id判断投放表中是否有这条投放
			// 如果有，则成功添加一条记录
			NiSuperListItem superArg = searchQnTitleById(hashMap);
			if (superArg != null) {
				// 获取当前系统时间
				Timestamp uTime = new Timestamp(System.currentTimeMillis());
				superArg.setuTime(uTime);
				superArg.setDeliveryId(deliveryId);
				superArg.setQnType(qnType);
				niSuperMapper.addSuperDeliveryToList(superArg);
				return AjaxResult.successResult("添加成功");
			} else {
				return AjaxResult.successResult("请输入正确投放id");
			}

		} else {
			return AjaxResult.successResult("该投放在列表中已存在");
		}
	}

	public AjaxResult replaceSuperDelivery(int deliveryId, int showOrder,int qnType) {
		NiSuperListItem superDelivery = searchSuperDeliveryFromList(deliveryId,qnType);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("qnType", qnType);
		hashMap.put("deliveryId", deliveryId);
		// 根据投放Id判断该投放是否在超级调查列表中
		// 如果不在，则替换一条新的投放
		if (superDelivery == null) {
			// 根据投放id判断投放表中是否有这条投放
			// 如果有，则成功添加一条记录
			NiSuperListItem superArg = searchQnTitleById(hashMap);
			if (superArg != null) {
				Timestamp uTime = new Timestamp(System.currentTimeMillis());// 获取当前系统时间
				superArg.setuTime(uTime);
				superArg.setDeliveryId(deliveryId);
				superArg.setShowOrder(showOrder);
				superArg.setQnType(qnType);
				niSuperMapper.replaceSuperDelivery(superArg);
				return AjaxResult.successResult("替换成功");
			} else {
				return AjaxResult.successResult("请输入正确投放id");
			}
		} else {
			return AjaxResult.successResult("该投放在列表中已存在");
		}
	}
	
	public AjaxResult moveUpSuper(int deliveryId,int qnType,int showOrder,int lastDeliveryId,int lastQnType,int lastShowOrder,HttpServletRequest request){
		String superListCategory_Str = request.getParameter("superListCategory");
		String lastSuperListCategory_Str = request.getParameter("lastSuperListCategory");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("deliveryId", deliveryId);
		hashMap.put("qnType", qnType);
		hashMap.put("showOrder", showOrder);
		hashMap.put("superListCategory_Str", superListCategory_Str);
		
		hashMap.put("lastDeliveryId", lastDeliveryId);
		hashMap.put("lastQnType", lastQnType);
		hashMap.put("lastShowOrder", lastShowOrder);
		hashMap.put("lastSuperListCategory_Str", lastSuperListCategory_Str);
		
		hashMap.put("uTime", uTime);
		niSuperMapper.moveUpSuper(hashMap);
		return AjaxResult.successResult("上移成功");
		
	}
	
	public AjaxResult moveDownSuper(int deliveryId,int qnType,int showOrder,int nextDeliveryId,
			int nextQnType,int nextShowOrder, HttpServletRequest request){
		
		String superListCategory = request.getParameter("superListCategory");
		String nextSuperListCategory = request.getParameter("nextSuperListCategory");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("deliveryId", deliveryId);
		hashMap.put("qnType", qnType);
		hashMap.put("showOrder", showOrder);
		hashMap.put("superListCategory", superListCategory);
		
		hashMap.put("nextDeliveryId", nextDeliveryId);
		hashMap.put("nextQnType", nextQnType);
		hashMap.put("nextShowOrder", nextShowOrder);
		hashMap.put("nextSuperListCategory", nextSuperListCategory);
		
		hashMap.put("uTime", uTime);
		niSuperMapper.moveDownSuper(hashMap);
		return AjaxResult.successResult("下移成功");
		
	}

	//跳转到定时任务
	public void jumpToTimerSuperPage(HttpServletRequest request, Model model) {
			String deliveryId_Str = request.getParameter("deliveryId");
			String qnType_Str = request.getParameter("qnType");
				
			String superListCategory_Str = request.getParameter("superListCategory");
			
			model.addAttribute("superListCategory_Str", superListCategory_Str);
			model.addAttribute("deliveryId_Str", deliveryId_Str);
			model.addAttribute("qnType_Str", qnType_Str);
	}

	//保存定时任务
	public AjaxResult superListTimerSave(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = new AjaxResult();
		
		String deliveryId_str = request.getParameter("deliveryId");
		String qnType_str = request.getParameter("qnType");
		String timer_dateStr = request.getParameter("timer_dateStr");
		String superListCategory_Str = request.getParameter("superListCategory");
		
		
		if(StrUtils.isEmpty(deliveryId_str) || StrUtils.isEmpty(qnType_str)){
			return AjaxResult.errorResult("保存失败");
		}
		
		Integer deliveryId = Integer.valueOf(deliveryId_str);
		Integer qnType = Integer.valueOf(qnType_str);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		
		Date parse = null;
		try {
			parse = simpleDateFormat.parse(timer_dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		NiSuperWanx niSuperWanx = new NiSuperWanx();
		niSuperWanx.setDeliveryId(deliveryId);
		niSuperWanx.setQnType(qnType);
		niSuperWanx.setSuperListCategory(superListCategory_Str);
		niSuperWanx.setTimer(parse);
		
		try {
			
			//根据 投放id 问卷类型 设置页面状态为待发布,发布时间
			niSuperMapper.updateStateById_timer(niSuperWanx);
			
			ajaxResult.put("success", true);
			ajaxResult.put("msg", "保存成功");
			ajaxResult.put("superListCategory", superListCategory_Str);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("保存失败");
		}
		
		return ajaxResult;
	}

	//添加 超级页面列表 时 根据 投放id 问卷类型 页面类型 查询投放是否存在
	public void searchSuperListByDevId(HttpServletRequest request, Model model) {
		
		String qnType_Str = request.getParameter("qnType");  //投放类型    1 调查  3投放
		String deliveryId_Str = request.getParameter("deliveryId");  //投放id
		String superListCategory_Str = request.getParameter("superListCategory");
		
		if(StrUtils.isEmpty(deliveryId_Str)){
			model.addAttribute("showTitle", "ID 为空 ");
		}else{
			//封装查询条件
			HashMap<String, Object> retMap = new HashMap<String, Object>();
			retMap.put("itemType", qnType_Str);
			retMap.put("itemId", deliveryId_Str);
			retMap.put("superListCategory", superListCategory_Str);
			
			NiSuperListItem superDelivery = null;
			try {
				superDelivery = niSuperMapper.searchQnTitleById(retMap);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("showTitle", "ID 错误,未找到该投放");
			} 
			
			if(superDelivery!=null){
				//判断 在超级列表页面是否已经存在
				NiSuperWanx niSuperWanx = niSuperWanxMapper.selectByPrimaryKey(retMap);
				
				if(niSuperWanx!=null){
					model.addAttribute("showTitle", "无法保存 ! 投放已经被添加");
				}else{
					//判断投放状态
					int status = superDelivery.getStatus();
					if(status!=2){
						model.addAttribute("showTitle", "无法保存 ! 投放状态必须为投放中");
					}else{
						
						String showTitle = superDelivery.getShowTitle();
						
						if(StrUtils.isEmpty(showTitle)){
							model.addAttribute("showTitle", "查询出结果,可以保存 !   投放标题为空");
						}else{
							model.addAttribute("showTitle", "查询出结果,可以保存 !   投放标题为:"+showTitle);
						}
						model.addAttribute("qnType_Str", qnType_Str);
						model.addAttribute("deliveryId_Str", deliveryId_Str);
						model.addAttribute("superListCategory_Str", superListCategory_Str);
						
					}
				}
				
			}else{
				model.addAttribute("showTitle", "ID 错误,未找到该投放");
			}
			
		}
		
		List<SuperListCategory> superListCategoryList = NiSuperListController.getSuperListCategoryList();
		model.addAttribute("superListCategoryList", superListCategoryList);
		
	}

	//保存 超级列表页面
	public AjaxResult saveSuperListCategory(HttpServletRequest request, Model model){
		
		String qnType_Str = request.getParameter("qnType");
		String deliveryId_Str = request.getParameter("deliveryId");
		String superListCategory_Str = request.getParameter("superListCategory");
		
		NiSuperWanx niSuperWanx = new NiSuperWanx();
		
		if(StrUtils.isNotEmpty(qnType_Str) && StrUtils.isNotEmpty(deliveryId_Str) && StrUtils.isNotEmpty(superListCategory_Str) ){
			Integer qnType = Integer.valueOf(qnType_Str);
			Integer deliveryId = Integer.valueOf(deliveryId_Str);
			
			niSuperWanx.setQnType(qnType);
			niSuperWanx.setDeliveryId(deliveryId);
			niSuperWanx.setSuperListCategory(superListCategory_Str);
			try {
				niSuperWanxMapper.insertSuperListCategory(niSuperWanx);
				return AjaxResult.successResult("保存成功");
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("保存失败");
			}
			
		}else{
			return AjaxResult.errorResult("保存失败");
		}
		
		
		
	}

}
