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

import com.newIns.model.page.NiSuperListItem;
import com.newIns.model.vo.SuperListCategory;
import com.newIns.service.NiSuperListService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.StrUtils;
import com.newIns.tools.VerifyPattern;

/**
 * @Description 页面管理-列表管理-测一发
 * @author Guan
 * @time 2016年7月4日 下午3:38:00
 */
@Controller
@RequestMapping("/platform")
public class NiSuperListController {
	private static Logger log = Logger.getLogger(NiSuperListController.class);
	@Resource
	private NiSuperListService superListService;
	
	/**
	 * 保存定时任务
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/superListTimerSave.do")
	public AjaxResult superListTimerSave(HttpServletRequest request,HttpServletResponse response){
		return superListService.superListTimerSave(request, response);
	}
	
	/**
	 * 跳转到定时页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/jumpToTimerSuperPage.do")
	public String jumpToTimerSuperPage(HttpServletRequest request,Model model){
		superListService.jumpToTimerSuperPage(request, model);
		return "manager/timerPage/timerSuperListEdit";
	}

	/**
	 * @Title: loadSuperList
	 * @Author: Guan
	 * @Description: 加载超级调查问卷列表
	 * @return ModelAndView
	 * @Time 2016年7月12日 上午10:21:04
	 */
	@RequestMapping("/loadSuperList.do")
	public String loadSuperList(HttpServletRequest request,Model model) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		log.info("========================>>分页查询调查问卷信息");
		String choose = request.getParameter("choose");
		String qnName = request.getParameter("qnName");
		String pageStatus = request.getParameter("pageStatus");
		String deliveryStatus = request.getParameter("deliveryStatus");
		String reservation = request.getParameter("reservation");
		String qnType = request.getParameter("qnType");
		
//		页面列表分类 surveyQnList, 调查，awardQnList, 有奖问卷，funQnList, 吃喝玩乐，gestureQnList, 涨姿势，roseQnList, 花式开撩
		String superListCategory_Str = request.getParameter("superListCategory_Str_GO");
		
		String starTime = "";
		String endTime = "";
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

		if (choose != null && !choose.trim().equals("") && qnName != null
				&& !qnName.trim().equals("")) {
			if (choose.trim().equals("0")) {
				// 问卷名称
				hashMap.put("qnName", qnName.trim());
			}else if (choose.trim().equals("1")) {
				//投放Id
				hashMap.put("deliveryId", qnName.trim());
			}
		}

		if (pageStatus != null && !pageStatus.trim().equals("")) {
			// 页面状态
			hashMap.put("pageStatus", Integer.valueOf(pageStatus));
		}

		if (deliveryStatus != null && !deliveryStatus.trim().equals("")) {
			// 投放状态
			hashMap.put("deliveryStatus", Integer.valueOf(deliveryStatus));
		}

		if (reservation != null && !reservation.trim().equals("")) {
			hashMap.put("starTime", starTime1);
			hashMap.put("endTime", endTime1);
		}

		if (qnType != null && !qnType.trim().equals("")) {
			// 问卷类型，1：调查，3：投票
			hashMap.put("qnType", Integer.valueOf(qnType));
		}
		
		if(StrUtils.isNotEmpty(superListCategory_Str)){
			hashMap.put("superListCategory_Str", superListCategory_Str);
			
		}else{
			// 默认查询 的分类  点击左侧的导航栏,会默认查询的栏目分类
			hashMap.put("superListCategory_Str", "schoolQnList");
		}

		log.info("========================>>加载超级调查信息列表管理页面");
		
		List<SuperListCategory> superListCategoryList = getSuperListCategoryList();

		List<NiSuperListItem> superList = superListService.loadSuperList(hashMap);
		model.addAttribute("niSuper", superList);
		model.addAttribute("superListCategory_list", superListCategoryList);
		model.addAttribute("superListCategory_Str", superListCategory_Str);
		return "manager/page/super_page_list";
	}
	
	//超级调查使用的栏目名称
	public static List<SuperListCategory> getSuperListCategoryList(){
		
		List<SuperListCategory> arrayList = new ArrayList<SuperListCategory>();
		
		//Verison 1.6
//		SuperListCategory superListCategory1 = new SuperListCategory("schoolQnList", "校园生活");
//		SuperListCategory superListCategory2 = new SuperListCategory("societyQnList", "社会镜头");
//		SuperListCategory superListCategory3 = new SuperListCategory("relationQnList", "亲密关系");
//		SuperListCategory superListCategory4 = new SuperListCategory("emotionQnList", "我的情绪");
//		SuperListCategory superListCategory5 = new SuperListCategory("brainQnList", "最强大脑");
		SuperListCategory superListCategory6 = new SuperListCategory("awardQnList", "有奖问卷");
//		SuperListCategory superListCategory7 = new SuperListCategory("futureQnList", "未来规划");
//		SuperListCategory superListCategory8 = new SuperListCategory("scienceQnList", "懂点科学");
//		SuperListCategory superListCategory9 = new SuperListCategory("healthyQnList", "生理健康");
		
//		arrayList.add(superListCategory1);
//		arrayList.add(superListCategory2);
//		arrayList.add(superListCategory3);
//		arrayList.add(superListCategory4);
//		arrayList.add(superListCategory5);
		arrayList.add(superListCategory6);
//		arrayList.add(superListCategory7);
//		arrayList.add(superListCategory8);
//		arrayList.add(superListCategory9);
		
		return arrayList;
	}

	/**
	 * @Title: postSuper
	 * @Author: Guan
	 * @Description: 发布超级调查问卷（页面状态）
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 上午10:25:00
	 */
	@RequestMapping("/postSuper.do")
	@ResponseBody
	public AjaxResult postSuper(HttpServletRequest request) {
		String deliveryIds = request.getParameter("deliveryId");
		String qnTypes = request.getParameter("qnType");
		String superListCategory_arr = request.getParameter("superListCategory");
		
		String[] deliveryId = deliveryIds.split("!");
		String[] qnType = qnTypes.split("!");
		String[] superListCategory_Str = superListCategory_arr.split("!");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp pTime = new Timestamp(System.currentTimeMillis());
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		
		for (int i = 0; i < deliveryId.length; i++) {

			hashMap.put("qnTypes", qnType[i]);
			hashMap.put("deliveryIds", deliveryId[i]);
			hashMap.put("superListCategory", superListCategory_Str[i]);
			
			hashMap.put("state", 3);
			hashMap.put("pTime", pTime);
			hashMap.put("uTime", uTime);

			try {
				superListService.postSuperByids(hashMap);
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("批量发布失败");
			}
			
		}

		return AjaxResult.successResult("批量发布超级调查成功");
	}

	/**
	 * @Title: revokeSuper
	 * @Author: Guan
	 * @Description: 批量撤销轮播广告
	 * @param request
	 * @return String
	 * @Time 2016年7月5日 下午7:15:50
	 */
	@RequestMapping("/revokeSuper.do")
	@ResponseBody
	public AjaxResult revokeSuper(HttpServletRequest request) {
		String deliveryIds = request.getParameter("deliveryId");
		String qnTypes = request.getParameter("qnType");
		String superListCategory_Str_arr = request.getParameter("superListCategory");
		
		String[] deliveryId = deliveryIds.split("!");
		String[] qnType = qnTypes.split("!");
		String[] superListCategory_Str = superListCategory_Str_arr.split("!");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		
		for (int i = 0; i < deliveryId.length; i++) {
			hashMap.put("qnTypes", qnType[i]);
			hashMap.put("deliveryIds", deliveryId[i]);
			hashMap.put("superListCategory", superListCategory_Str[i]);
			
			hashMap.put("state", 1);
			hashMap.put("uTime", uTime);

			try {
				superListService.revokeSuperByids(hashMap);
				log.info("===============================>>批量撤销超级调查成功");
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("批量撤销超级调查失败");
			}
			
		}

		return AjaxResult.successResult("批量撤销超级调查成功");
	}

	/**
	 * 批量删除问卷
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteSuperFromList.do")
	@ResponseBody
	public AjaxResult deleteSuperFromList(HttpServletRequest request) {
		String parameter = request.getParameter("deliveryId");
		String qnTypes = request.getParameter("qnType");
		String superListCategory_str = request.getParameter("superListCategory");
		
		
		String[] deliveryId = parameter.split("!");
		String[] qnType = qnTypes.split("!");
		String[] superListCategory_arr = superListCategory_str.split("!");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		for (int i = 0; i < deliveryId.length; i++) {
			hashMap.put("qnTypes", qnType[i]);
			hashMap.put("deliveryIds", deliveryId[i]);
			hashMap.put("superListCategory", superListCategory_arr[i]);
		}

		try {
			superListService.deleteSuperByIds(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("删除失败");
		}
		return AjaxResult.successResult("删除调查问卷成功");
	}

	/**
	 * @Title: topSuper
	 * @Author: Guan
	 * @Description: 置顶超级调查问卷
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 下午2:51:16
	 */
	@RequestMapping("/topSuper.do")
	@ResponseBody
	public AjaxResult topSuper(HttpServletRequest request) {
		String deliveryId = request.getParameter("deliveryId");
		int delivery_Id = Integer.valueOf(deliveryId);
		String qnType = request.getParameter("qnType");
		int qn_Type = Integer.valueOf(qnType);
		
		String superListCategory = request.getParameter("superListCategory");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("uTime", uTime);
		hashMap.put("deliveryId", delivery_Id);
		hashMap.put("qnType", qn_Type);
		hashMap.put("superListCategory", superListCategory);
		
		superListService.topSuper(hashMap);
		return AjaxResult.successResult("置顶成功");
	}

	/**
	 * @Title: topCancelSuper
	 * @Author: Guan
	 * @Description: 置顶超级调查问卷
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 下午2:51:16
	 */
	@RequestMapping("/topCancelSuper.do")
	@ResponseBody
	public AjaxResult topCancelSuper(HttpServletRequest request) {
		String deliveryId = request.getParameter("deliveryId");
		int delivery_Id = Integer.valueOf(deliveryId);
		String qnType = request.getParameter("qnType");
		int qn_Type = Integer.valueOf(qnType);
		
		String superListCategory = request.getParameter("superListCategory");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("uTime", uTime);
		hashMap.put("deliveryId", delivery_Id);
		hashMap.put("qnType", qn_Type);
		hashMap.put("superListCategory", superListCategory);
		
		superListService.topCancelSuper(hashMap);
		return AjaxResult.successResult("取消成功");
	}

	/**
	 * @Title: addSuperToList
	 * @Author: Guan
	 * @Description: 跳转到添加页面
	 * @return ModelAndView
	 * @Time 2016年7月12日 下午3:23:08
	 */
	@RequestMapping("/addSuperToList.do")
	public String addSuperToList(Model model) {
		
		List<SuperListCategory> superListCategoryList = getSuperListCategoryList();
		model.addAttribute("superListCategoryList", superListCategoryList);
		return "manager/superListCategory/addSuperCategory";
	}
	
	/**
	 * 添加 超级页面列表 时 根据 投放id 问卷类型 页面类型 查询投放是否存在
	 * @return
	 */
	@RequestMapping("/searchSuperListByDevId.do")
	public String searchSuperListByDevId(HttpServletRequest request,Model model){
		
		superListService.searchSuperListByDevId(request,model);
		return "manager/superListCategory/addSuperCategory";
	}
	
	/**
	 * 保存 超级列表页面
	 * @return
	 */
	@RequestMapping("/saveSuperListCategory.do")
	@ResponseBody
	public AjaxResult saveSuperListCategory(HttpServletRequest request,Model model){
		return superListService.saveSuperListCategory(request,model);
		
	}

	/**
	 * @Title: editSuperList
	 * @Author: Guan
	 * @Description: 编辑列表（替换）
	 * @return ModelAndView
	 * @Time 2016年7月12日 下午3:23:08
	 */
	@RequestMapping("/editSuperList.do")
	public ModelAndView editSuperList(HttpServletRequest request) {
		String showOrder = request.getParameter("showOrder");
		int show_Order = Integer.valueOf(showOrder);
		ModelAndView modelAndView = new ModelAndView();
		log.info("========================>>跳转到编辑页面");
		modelAndView.setViewName("manager/EditSuperList");
		modelAndView.addObject("showOrder", show_Order);
		return modelAndView;
	}

	/**
	 * @Title: searchQnTitleById
	 * @Author: Guan
	 * @Description: 根据投放id查询问卷名称，在添加中使用
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 下午4:31:06
	 */
	@RequestMapping("/searchQnTitleById.do")
	public ModelAndView searchQnTitleById(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String deliveryId = request.getParameter("deliveryId");
		String qnType = request.getParameter("qnType");
		int qn_Type = Integer.valueOf(qnType);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("qnType", qn_Type);
		hashMap.put("deliveryId", deliveryId);
		// 先验证输入id是否为数字
		if (VerifyPattern.isNumeric(deliveryId) && deliveryId != "") {
			int delivery_Id = Integer.valueOf(deliveryId);
			NiSuperListItem superDelivery = superListService.searchQnTitleById(hashMap);
			if (superDelivery != null) {
				String qnTitle = superDelivery.getShowTitle();
				int deliveryStatus=superDelivery.getStatus();
				log.info("===============>" + qnTitle);
				modelAndView.setViewName("manager/AddSuperToList");
				modelAndView.addObject("qnTitle", qnTitle);
				modelAndView.addObject("deliveryId", delivery_Id);
				modelAndView.addObject("qnType", qnType);
				modelAndView.addObject("deliveryStatus", deliveryStatus);
			} else {
				modelAndView.setViewName("manager/AddSuperToList");
				modelAndView.addObject("qnTitle", "该投放不存在");
			}
		} else {
			modelAndView.setViewName("manager/AddSuperToList");
			modelAndView.addObject("qnTitle", "请输入数字Id");
		}
		return modelAndView;

	}

	/**
	 * @Title: searchQnTitleByIdForEdit
	 * @Author: Guan
	 * @Description: 根据投放id查询问卷名称,在编辑中使用
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 下午4:31:06
	 */
	@RequestMapping("/searchQnTitleByIdForEdit.do")
	public ModelAndView searchQnTitleByIdForEdit(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String deliveryId = request.getParameter("deliveryId");
		String showOrder = request.getParameter("showOrder");
		String qnType = request.getParameter("qnType");
		int qn_Type = Integer.valueOf(qnType);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("qnType", qn_Type);
		hashMap.put("deliveryId", deliveryId);
		// 先验证输入id是否为数字
		if (VerifyPattern.isNumeric(deliveryId) && deliveryId != "") {
			int delivery_Id = Integer.valueOf(deliveryId);
			NiSuperListItem superDelivery = superListService
					.searchQnTitleById(hashMap);
			if (superDelivery != null) {
				String qnTitle = superDelivery.getShowTitle();
				log.info("===============>" + qnTitle);
				modelAndView.setViewName("manager/EditSuperList");
				modelAndView.addObject("qnTitle", qnTitle);
				modelAndView.addObject("deliveryId", delivery_Id);
				modelAndView.addObject("showOrder", showOrder);
				modelAndView.addObject("qnType", qn_Type);
			} else {
				modelAndView.setViewName("manager/EditSuperList");
				modelAndView.addObject("qnTitle", "该投放不存在");
				modelAndView.addObject("showOrder", showOrder);
			}
		} else {
			modelAndView.setViewName("manager/EditSuperList");
			modelAndView.addObject("qnTitle", "请输入数字Id");
			modelAndView.addObject("showOrder", showOrder);
		}
		return modelAndView;

	}

	/**
	 * @Title: addSuperDelivery
	 * @Author: Guan
	 * @Description: 保存添加，将新投放加入页面列表中
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月14日 下午12:44:15
	 */
	@RequestMapping("/addSuperDelivery.do")
	@ResponseBody
	public AjaxResult addSuperDelivery(HttpServletRequest request) {
		String deliveryId = request.getParameter("deliveryId");
		int delivery_Id = Integer.valueOf(deliveryId);
		String qnType = request.getParameter("qnType");
		int qn_Type = Integer.valueOf(qnType);
		AjaxResult result = superListService.addSuperDelivery(delivery_Id,
				qn_Type);
		return result;
	}

	@RequestMapping("/replaceSuperDelivery.do")
	@ResponseBody
	public AjaxResult replaceSuperDelivery(HttpServletRequest request) {
		String deliveryId = request.getParameter("deliveryId");
		int delivery_Id = Integer.valueOf(deliveryId);
		String showOrder = request.getParameter("showOrder");
		int show_Order = Integer.valueOf(showOrder);
		String qnType = request.getParameter("qnType");
		int qn_Type = Integer.valueOf(qnType);
		AjaxResult result = superListService.replaceSuperDelivery(delivery_Id,show_Order,qn_Type);
		return result;
	}

	/**
	 * @Title: moveUpSuper  
	 * @Author: Guan
	 * @Description: 上移
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月29日 下午2:37:46
	 */
	@RequestMapping("/moveUpSuper.do")
	@ResponseBody
	public AjaxResult moveUpSuper(HttpServletRequest request) {
		String deliveryId = request.getParameter("deliveryId");
		int delivery_Id = Integer.valueOf(deliveryId);
		String qnType = request.getParameter("qnType");
		int qn_Type = Integer.valueOf(qnType);
		String showOrder = request.getParameter("showOrder");
		int show_Order = Integer.valueOf(showOrder);
		String lastDeliveryId = request.getParameter("lastDeliveryId");
		int last_DeliveryId = Integer.valueOf(lastDeliveryId);
		String lastQnType = request.getParameter("lastQnType");
		int last_QnType = Integer.valueOf(lastQnType);
		String lastShowOrder = request.getParameter("lastShowOrder");
		int last_ShowOrder = Integer.valueOf(lastShowOrder);
		

		AjaxResult result = superListService.moveUpSuper(delivery_Id,qn_Type,show_Order,last_DeliveryId,last_QnType,last_ShowOrder, request);
		return result;
	}
	/**
	 * @Title: moveDownSuper  
	 * @Author: Guan
	 * @Description: 下移
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年8月1日 下午12:54:48
	 */
	@RequestMapping("/moveDownSuper.do")
	@ResponseBody
	public AjaxResult moveDownSuper(HttpServletRequest request) {
		String deliveryId = request.getParameter("deliveryId");
		int delivery_Id = Integer.valueOf(deliveryId);
		String qnType = request.getParameter("qnType");
		int qn_Type = Integer.valueOf(qnType);
		String showOrder = request.getParameter("showOrder");
		int show_Order = Integer.valueOf(showOrder);
		String lastDeliveryId = request.getParameter("nextDeliveryId");
		int next_DeliveryId = Integer.valueOf(lastDeliveryId);
		String nextQnType = request.getParameter("nextQnType");
		int next_QnType = Integer.valueOf(nextQnType);
		String lastShowOrder = request.getParameter("nextShowOrder");
		int next_ShowOrder = Integer.valueOf(lastShowOrder);
		
		
		AjaxResult result = superListService.moveDownSuper(delivery_Id,qn_Type,show_Order,next_DeliveryId,next_QnType,next_ShowOrder,request);
		return result;
	}
}
