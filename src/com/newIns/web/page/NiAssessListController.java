/**
 * 
 */
package com.newIns.web.page;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.model.assess.NiAssessListItem;
import com.newIns.service.NiAssessListService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.VerifyPattern;

/**
 * @Description 页面管理-列表管理-测一发
 * @author Guan
 * @time 2016年7月4日 下午3:38:00
 */
@Controller
@RequestMapping("/platform")
public class NiAssessListController {
	private static Logger log = Logger.getLogger(NiAssessListController.class);
	@Resource
	private NiAssessListService assessListService;

	/**
	 * 保存定时任务
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveAssessTimer.do")
	public AjaxResult saveAssessTimer(HttpServletRequest request,HttpServletResponse response){
		return assessListService.saveAssessTimer(request, response);
	}
	
	/**
	 * 跳转到添加定时页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/jumpAssessTimerPage.do")
	public String jumpAssessTimerPage(HttpServletRequest request,Model model){
		assessListService.jumpAssessTimerPage(request, model);
		return "manager/timerPage/timerAssessEdit";
	}
	
	/**
	 *  加载测评问卷列表
	 */
	@RequestMapping("/loadAssessList.do")
	public ModelAndView loadAssessList(HttpServletRequest request) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		log.info("========================>>分页查询调查问卷信息");
		String choose = request.getParameter("choose");
		String aqnName = request.getParameter("aqnName");
		String pageStatus = request.getParameter("pageStatus");
		String deliveryStatus = request.getParameter("deliveryStatus");
		
		String reservation = request.getParameter("reservation");
		String assessListCategory = request.getParameter("assessListCategory");
		String starTime = "";
		String endTime = "";
		//用于标记是查询跳转还是通过链接跳转，如果通过链接跳转那么需要告诉jsp先将cookie中的类型置为默认类型
		//这非常重要，因为所有的类型判断都是基于cookie做的
		boolean isFirstCommit = false;	
		if (reservation != null && !reservation.trim().equals("")) {
			String[] split = reservation.split("-");
			starTime = split[0];
			endTime = split[1];
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String starTime1 = "";
		String endTime1 = "";
		if (reservation != null && !reservation.trim().equals("")) {
			Date date = new Date(starTime);
			Date date2 = new Date(endTime);
			starTime1 = format.format(date);
			endTime1 = format.format(date2);
		}

		if (choose != null && !choose.trim().equals("") && aqnName != null
				&& !aqnName.trim().equals("")) {
			if (choose.trim().equals("0")) {
				// 问卷名称
				hashMap.put("aqnName", aqnName.trim());
			}else if (choose.trim().equals("1")) {
				//投放Id
				hashMap.put("deliveryId", aqnName.trim());
			}
		}

		if (pageStatus != null && !pageStatus.trim().equals("")) {
			// 页面状态
			hashMap.put("pageStatus", Integer.valueOf(pageStatus));
		}

		if (deliveryStatus != null && !deliveryStatus.trim().equals("")) {
			// 投放状态
			hashMap.put("deliveryStaus", Integer.valueOf(deliveryStatus));
		}

		if (reservation != null && !reservation.trim().equals("")) {
			hashMap.put("starTime", starTime1);
			hashMap.put("endTime", endTime1);
		}
		if(assessListCategory==null){
			isFirstCommit = true;
			hashMap.put("assessListCategory","professialAssessQnList");	//如果前端未传递数据, 例如左侧导航栏进入 
		}
		else hashMap.put("assessListCategory", assessListCategory);

		log.info("========================>>加载测评信息列表管理页面");

		List<NiAssessListItem> assessList = assessListService.loadAssessList(hashMap);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/page/assess_page_list");
		modelAndView.addObject("niAssess", assessList);
		modelAndView.addObject("isFirstCommit",isFirstCommit);
		return modelAndView;
	}

	/**
	 * 发布测评问卷（页面状态）
	 */
	@RequestMapping("/postAssess.do")
	@ResponseBody
	public AjaxResult postAssess(HttpServletRequest request) {
		String deliveryIds = request.getParameter("deliveryId");
		String assessListCategory = request.getParameter("assessListCategory");
		String[] deliveryId = deliveryIds.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> deliveryIdList = new ArrayList<Integer>();
		for (int i = 0; i < deliveryId.length; i++) {
			deliveryIdList.add(Integer.valueOf(deliveryId[i]));
		}

		hashMap.put("state", 3);
		hashMap.put("deliveryIds", deliveryIdList);
		hashMap.put("assessListCategory", assessListCategory);
		Timestamp pTime = new Timestamp(System.currentTimeMillis());
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("pTime", pTime);
		hashMap.put("uTime", uTime);
		int postByPrimaryKeySelective = 0;
		try {
			postByPrimaryKeySelective = assessListService
					.postAssessByids(hashMap);
			log.info("===============================>>批量发布测评成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (postByPrimaryKeySelective > 0) {
			log.info("==========================>>更改状态成功!");
		}
		return AjaxResult.successResult("批量发布测评成功");
	}

	/**
	 * @Title: revokeAssess
	 * @Author: Guan
	 * @Description: 批量撤销轮播广告
	 * @param request
	 * @return String
	 * @Time 2016年7月5日 下午7:15:50
	 */
	@RequestMapping("/revokeAssess.do")
	@ResponseBody
	public AjaxResult revokeAssess(HttpServletRequest request) {
		String deliveryIds = request.getParameter("deliveryId");
		String assessListCategory = request.getParameter("assessListCategory");
		String[] deliveryId = deliveryIds.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		// ArrayList<Integer> deliveryIdList = new ArrayList<Integer>();
		for (int i = 0; i < deliveryId.length; i++) {
			// deliveryIdList.add(Integer.valueOf(deliveryId[i]));
			hashMap.put("deliveryIds", deliveryId[i]);
			hashMap.put("state", 1);
			hashMap.put("uTime", uTime);
			hashMap.put("assessListCategory", assessListCategory);
			int revokeByPrimaryKeySelective = 0;
			try {
				revokeByPrimaryKeySelective = assessListService
						.revokeAssessByids(hashMap);
				log.info("===============================>>批量撤销广告成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (revokeByPrimaryKeySelective > 0) {
				log.info("==========================>>撤销成功!");
			}
		}
		return AjaxResult.successResult("撤销广告成功");
	}

	/**
	 * 批量删除问卷
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteAssessFromList.do")
	@ResponseBody
	public AjaxResult deleteAssessFromList(HttpServletRequest request) {
		String parameter = request.getParameter("deliveryId");
		String assessListCategory = request.getParameter("assessListCategory");
		String[] deliveryId = parameter.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> deliveryIds = new ArrayList<Integer>();
		for (int i = 0; i < deliveryId.length; i++) {
			deliveryIds.add(Integer.valueOf(deliveryId[i]));
		}
		hashMap.put("deliveryIds", deliveryIds);
		hashMap.put("assessListCategory", assessListCategory);
		int updateByPrimaryKeySelective = 0;

		try {
			assessListService.deleteAssessByIds(hashMap);
			log.info("===============================>>批量删除问卷");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (updateByPrimaryKeySelective > 0) {
			log.info("===============================>>批量删除问卷成功");
		}
		return AjaxResult.successResult("删除调查问卷成功");
	}

	/**
	 * @Title: topAssess
	 * @Author: Guan
	 * @Description: 置顶测评问卷
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 下午2:51:16
	 */
	@RequestMapping("/topAssess.do")
	@ResponseBody
	public AjaxResult topAssess(HttpServletRequest request) {
		String deliveryId = request.getParameter("deliveryId");
		String assessListCategory = request.getParameter("assessListCategory");
		int delivery_Id = Integer.valueOf(deliveryId);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("uTime", uTime);
		hashMap.put("deliveryId", delivery_Id);
		hashMap.put("assessListCategory", assessListCategory);
		assessListService.topAssess(hashMap);
		return AjaxResult.successResult("置顶成功");
	}

	/**
	 * @Title: topCancelAssess
	 * @Author: Guan
	 * @Description: 置顶测评问卷
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 下午2:51:16
	 */
	@RequestMapping("/topCancelAssess.do")
	@ResponseBody
	public AjaxResult topCancelAssess(HttpServletRequest request) {
		String deliveryId = request.getParameter("deliveryId");
		String assessListCategory = request.getParameter("assessListCategory");
		int delivery_Id = Integer.valueOf(deliveryId);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("uTime", uTime);
		hashMap.put("deliveryId", delivery_Id);
		hashMap.put("assessListCategory", assessListCategory);
		assessListService.topCancelAssess(hashMap);
		return AjaxResult.successResult("取消成功");
	}

	/**
	 *  跳转到添加页面
	 */
	@RequestMapping("/add_AssessPage.do")
	public String addAssessToList() {
		log.info("========================>>跳转到添加页面");
		return "manager/page/assess_page_add";
	}

	/**
	 * 编辑测一发列表（替换）
	 */
	@RequestMapping("/editAssessList.do")
	public ModelAndView editAssessList(HttpServletRequest request) {
		String showOrder = request.getParameter("showOrder");
		String assessListCategory = request.getParameter("assessListCategory");
		int show_Order = Integer.valueOf(showOrder);
		ModelAndView modelAndView = new ModelAndView();
		log.info("========================>>跳转到编辑页面");
		modelAndView.setViewName("manager/EditAssessList");
		modelAndView.addObject("showOrder", show_Order);
		modelAndView.addObject("assessListCategory", assessListCategory);
		return modelAndView;
	}

	// 根据投放id, 名称查询投放信息
	@ResponseBody
	@RequestMapping("/search_assess_delivey.do")
	public AjaxResult search_assess_delivey(HttpServletRequest request,Model model){
		
		return assessListService.search_assess_delivey(request,model);
	}
	
	/**
	 *根据投放id查询问卷名称，在添加中使用
	 */
	@RequestMapping("/searchAqnTitleById.do")
	public ModelAndView searchQnTitleById(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String deliveryId = request.getParameter("deliveryId");
		// 先验证输入id是否为数字
		if (VerifyPattern.isNumeric(deliveryId) && deliveryId != "") {
			int delivery_Id = Integer.valueOf(deliveryId);
			NiAssessListItem assessDelivery = assessListService
					.searchQnTitleById(delivery_Id);
			if (assessDelivery != null) {
				String aqnTitle = assessDelivery.getShowTitle();
				int deliveryStatus = assessDelivery.getStatus();
				log.info("===============>" + aqnTitle);
				modelAndView.setViewName("manager/AddAssessToList");
				modelAndView.addObject("aqnTitle", aqnTitle);
				modelAndView.addObject("deliveryId", delivery_Id);
				modelAndView.addObject("deliveryStatus", deliveryStatus);
			} else {
				modelAndView.setViewName("manager/AddAssessToList");
				modelAndView.addObject("aqnTitle", "该投放不存在");
			}
		} else {
			modelAndView.setViewName("manager/AddAssessToList");
			modelAndView.addObject("aqnTitle", "请输入数字Id");
		}
		return modelAndView;

	}

	/**
	 *  根据投放id查询问卷名称,在编辑中使用
	 */
	@RequestMapping("/searchAqnTitleByIdForEdit.do")
	public ModelAndView searchAqnTitleByIdForEdit(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String deliveryId = request.getParameter("deliveryId");
		String showOrder = request.getParameter("showOrder");
		// 先验证输入id是否为数字
		if (VerifyPattern.isNumeric(deliveryId) && deliveryId != "") {
			int delivery_Id = Integer.valueOf(deliveryId);
			NiAssessListItem assessDelivery = assessListService
					.searchQnTitleById(delivery_Id);
			if (assessDelivery != null) {
				String aqnTitle = assessDelivery.getShowTitle();
				log.info("===============>" + aqnTitle);
				modelAndView.setViewName("manager/EditAssessList");
				modelAndView.addObject("aqnTitle", aqnTitle);
				modelAndView.addObject("deliveryId", delivery_Id);
				modelAndView.addObject("showOrder", showOrder);
			} else {
				modelAndView.setViewName("manager/EditAssessList");
				modelAndView.addObject("aqnTitle", "该投放不存在");
				modelAndView.addObject("showOrder", showOrder);
			}
		} else {
			modelAndView.setViewName("manager/EditAssessList");
			modelAndView.addObject("aqnTitle", "请输入数字Id");
			modelAndView.addObject("showOrder", showOrder);
		}
		return modelAndView;

	}

	// 保存测一发 
	@RequestMapping("/save_Assess_Page.do")
	@ResponseBody
	public AjaxResult save_Assess_Page(HttpServletRequest request){
		
		return assessListService.save_Assess_Page(request);
	}

	@RequestMapping("/replaceAssessDelivery.do")
	@ResponseBody
	public AjaxResult replaceAssessDelivery(HttpServletRequest request) {
		String deliveryId = request.getParameter("deliveryId");
		int delivery_Id = Integer.valueOf(deliveryId);
		String showOrder = request.getParameter("showOrder");
		String assessListCategory = request.getParameter("assessListCategory");
		int show_Order = Integer.valueOf(showOrder);
		AjaxResult result = assessListService.replaceAssessDelivery(
				delivery_Id, show_Order, assessListCategory);
		return result;
	}
	
	/**
	 * @Title: moveUpAssess  
	 * @Author: Guan
	 * @Description: 上移
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月29日 下午2:37:46
	 */
	@RequestMapping("/moveUpAssess.do")
	@ResponseBody
	public AjaxResult moveUpAssess(HttpServletRequest request) {
		String deliveryId = request.getParameter("deliveryId");
		int delivery_Id = Integer.valueOf(deliveryId);
		String showOrder = request.getParameter("showOrder");
		int show_Order = Integer.valueOf(showOrder);
		String lastDeliveryId = request.getParameter("lastDeliveryId");
		int last_DeliveryId = Integer.valueOf(lastDeliveryId);
		String lastShowOrder = request.getParameter("lastShowOrder");
		int last_ShowOrder = Integer.valueOf(lastShowOrder);
		String assessListCategory = request.getParameter("assessListCategory");
		AjaxResult result = assessListService.moveUpAssess(delivery_Id,show_Order,last_DeliveryId,last_ShowOrder,assessListCategory);
		return result;
	}
	/**
	 * @Title: moveDownAssess  
	 * @Author: Guan
	 * @Description: 下移
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年8月1日 下午12:54:48
	 */
	@RequestMapping("/moveDownAssess.do")
	@ResponseBody
	public AjaxResult moveDownAssess(HttpServletRequest request) {
		String deliveryId = request.getParameter("deliveryId");
		int delivery_Id = Integer.valueOf(deliveryId);
		String showOrder = request.getParameter("showOrder");
		int show_Order = Integer.valueOf(showOrder);
		String lastDeliveryId = request.getParameter("nextDeliveryId");
		int next_DeliveryId = Integer.valueOf(lastDeliveryId);
		String lastShowOrder = request.getParameter("nextShowOrder");
		int next_ShowOrder = Integer.valueOf(lastShowOrder);
		String assessListCategory = request.getParameter("assessListCategory");
		AjaxResult result = assessListService.moveDownAssess(delivery_Id,show_Order,next_DeliveryId,next_ShowOrder,assessListCategory);
		return result;
	}

}
