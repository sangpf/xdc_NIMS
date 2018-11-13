package com.newIns.web.page;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.model.niDailyUpdateWanx.Daily3update_CategoryList;
import com.newIns.model.niDailyUpdateWanx.NiDailyUpdateWanx_VO_01;
import com.newIns.model.page.NiDaily3UpdateListItem;
import com.newIns.model.vo.SuperListCategory;
import com.newIns.service.NiDaily3UpdateListService;
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
public class NiDaily3UpdateListController {
	private static Logger log = Logger.getLogger(NiDaily3UpdateListController.class);
	@Resource
	private NiDaily3UpdateListService daily3updateListService;
	
	/**
	 * 保存编辑三更
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveEditDaily3UpdateDelivery.do")
	public AjaxResult saveEditDaily3UpdateDelivery(HttpServletRequest request,Model model){
		AjaxResult ajaxResult =  daily3updateListService.editDaily3UpdateDelivery(request,model);
		return ajaxResult;
	}
	
	/**
	 * @Description: 编辑 跳转
	 */
	@RequestMapping("/editDaily3UpdateList.do")
	public String editDaily3UpdateList(HttpServletRequest request,Model model) {
		daily3updateListService.editDaily3UpdateList(request,model);
		return "manager/dailyUpdatePage/editDailyUpdate";
	}
	
	/**
	 * 保存定时任务
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/daily3UpdateTimerSave.do")
	public AjaxResult daily3UpdateTimerSave(HttpServletRequest request,HttpServletResponse response){
		
		return daily3updateListService.daily3UpdateTimerSave(request, response);
	}
	
	/**
	 * 跳转到打开定时器时间页面
	 * @return
	 */
	@RequestMapping("/jumpDaily3UpdateTimerPage.do")
	public String jumpDaily3UpdateTimerPage(HttpServletRequest request,Model model){
		
		daily3updateListService.jumpDaily3UpdateTimerPage(request, model);
		return "manager/timerPage/timerDaily3Update";
		
	}

	/**
	 *  加载三更问卷列表
	 */
	@RequestMapping("/loadDaily3UpdateList.do")
	public String loadDaily3UpdateList(HttpServletRequest request,Model model) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		String choose = request.getParameter("choose");
		String qnName = request.getParameter("qnName");//问卷名称或者
		String pageStatus = request.getParameter("pageStatus");
		String deliveryStatus = request.getParameter("deliveryStatus");
		String reservation = request.getParameter("reservation");
		String qnType = request.getParameter("qnType");
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
			hashMap.put("superListCategory_Str", "floatLatestList");
		}

		log.info("========================>>加载三更信息列表管理页面");

		List<NiDailyUpdateWanx_VO_01> daily3updateList = daily3updateListService.loadDaily3UpdateList(hashMap);
		model.addAttribute("niDaily3UpdateList", daily3updateList);
		
		List<Daily3update_CategoryList> daily3update_CategoryList = getDaily3update_CategoryList();
		
		model.addAttribute("daily3update_CategoryList", daily3update_CategoryList);
		model.addAttribute("superListCategory_Str", superListCategory_Str);
		return "manager/dailyUpdatePage/niDailyUpdateList";
	}
	
	// 查询三更列表 所有栏目
	public static List<Daily3update_CategoryList> getDaily3update_CategoryList(){
		
		Properties props = new Properties();
		
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("Daily3UpdateList.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Daily3update_CategoryList> arrayList = new ArrayList<Daily3update_CategoryList>();
		// 三更 栏目列表实体类
		Daily3update_CategoryList daily3update_CategoryList = null;
		
		
		Set<Object> keySet = props.keySet();
		
		Iterator<Object> iterator = keySet.iterator();
		
		while(iterator.hasNext()){
			String key = (String) iterator.next();
			
			String value = (String) props.get(key);
			
			daily3update_CategoryList = new Daily3update_CategoryList();
			
			daily3update_CategoryList.setCategoryKey(key);
			daily3update_CategoryList.setCategoryValue(value);
			
			arrayList.add(daily3update_CategoryList);
		}
		
		return arrayList;
		
	}

	/**
	 *  发布三更问卷（页面状态）
	 */
	@RequestMapping("/postDaily3Update.do")
	@ResponseBody
	public AjaxResult postDaily3Update(HttpServletRequest request) {
		String itemIds = request.getParameter("itemIds");
		String[] itemId_arr = itemIds.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp pTime = new Timestamp(System.currentTimeMillis());
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		
		List<String> asList = Arrays.asList(itemId_arr);
		
		
		hashMap.put("itemIdList", asList);
		hashMap.put("state", 3);
		hashMap.put("pTime", pTime);
		hashMap.put("uTime", uTime);

		try {
			daily3updateListService.postDaily3UpdateByids(hashMap);
			log.info("===============================>>批量发布三更成功");
			return AjaxResult.successResult("批量发布三更成功");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("批量发布失败");
		}

	}

	/**
	 * @Title: revokeDaily3Update
	 * @Author: Guan
	 * @Description: 批量撤销轮播广告
	 * @param request
	 * @return String
	 * @Time 2016年7月5日 下午7:15:50
	 */
	@RequestMapping("/revokeDaily3Update.do")
	@ResponseBody
	public AjaxResult revokeDaily3Update(HttpServletRequest request) {
		String itemIds = request.getParameter("itemIds");
		String[] itemId_arr = itemIds.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		
		List<String> itemIdList = Arrays.asList(itemId_arr);
		
			hashMap.put("itemIdList", itemIdList);
			hashMap.put("state", 1);
			hashMap.put("uTime", uTime);

			try {
				daily3updateListService.revokeDaily3UpdateByids(hashMap);
				log.info("===============================>>批量撤销三更成功");
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("撤销失败!");
			}
			
		return AjaxResult.successResult("批量撤销三更成功");
	}

	/**
	 * 批量删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteDaily3UpdateFromList.do")
	@ResponseBody
	public AjaxResult deleteDaily3UpdateFromList(HttpServletRequest request) {
		String itemIds = request.getParameter("itemIds");
		String[] itemId_arr = itemIds.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		List<String> asList = Arrays.asList(itemId_arr);
		
		hashMap.put("itemId_list", asList);
		
		int updateByPrimaryKeySelective = 0;

		try {
			daily3updateListService.deleteDaily3UpdateByIds(hashMap);
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
	 * @Title: topDaily3Update
	 * @Author: Guan
	 * @Description: 置顶三更问卷
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 下午2:51:16
	 */
	@RequestMapping("/topDaily3Update.do")
	@ResponseBody
	public AjaxResult topDaily3Update(HttpServletRequest request) {
		String itemId = request.getParameter("itemId");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("itemId", itemId);
		try {
			daily3updateListService.topDaily3Update(hashMap);
			return AjaxResult.successResult("置顶成功");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("置顶失败");
		}
	}

	/**
	 * @Title: topCancelDaily3Update
	 * @Author: Guan
	 * @Description: 置顶三更问卷
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 下午2:51:16
	 */
	@RequestMapping("/topCancelDaily3Update.do")
	@ResponseBody
	public AjaxResult topCancelDaily3Update(HttpServletRequest request) {
		String itemId = request.getParameter("itemId");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("itemId", itemId);
		
		try {
			daily3updateListService.topCancelDaily3Update(hashMap);
			return AjaxResult.successResult("取消成功");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("取消失败");
		}
	}

	/**
	 * @Title: addDaily3UpdateToList
	 * @Author: Guan
	 * @Description: 跳转到添加页面
	 * @return ModelAndView
	 * @Time 2016年7月12日 下午3:23:08
	 */
	@RequestMapping("/addDaily3UpdateToList.do")
	public String addDaily3UpdateToList(Model model) {
		log.info("========================>>跳转到添加页面");

		List<Daily3update_CategoryList> daily3update_CategoryList = getDaily3update_CategoryList();
		
		model.addAttribute("daily3update_CategoryList", daily3update_CategoryList);

		return "manager/dailyUpdatePage/addDailyUpdate";
	}



	/**
	 * @Title: searchQnTitleById
	 * @Author: Guan
	 * @Description: 根据投放id查询问卷名称，在添加中使用
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 下午4:31:06
	 */
	@RequestMapping("/d3searchQnTitleById.do")
	public ModelAndView d3searchQnTitleById(HttpServletRequest request) {
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
			NiDaily3UpdateListItem daily3updateDelivery = daily3updateListService
					.d3searchQnTitleById(hashMap);
			if (daily3updateDelivery != null) {
				String qnTitle = daily3updateDelivery.getShowTitle();
				int deliveryStatus=daily3updateDelivery.getStatus();
				log.info("===============>" + qnTitle);
				modelAndView.setViewName("manager/AddDaily3UpdateToList");
				modelAndView.addObject("qnTitle", qnTitle);
				modelAndView.addObject("deliveryId", delivery_Id);
				modelAndView.addObject("qnType", qnType);
				modelAndView.addObject("deliveryStatus", deliveryStatus);
			} else {
				modelAndView.setViewName("manager/AddDaily3UpdateToList");
				modelAndView.addObject("qnTitle", "该投放不存在");
			}
		} else {
			modelAndView.setViewName("manager/AddDaily3UpdateToList");
			modelAndView.addObject("qnTitle", "请输入数字Id");
		}
		return modelAndView;

	}

	/**
	 *  根据投放id查询问卷名称,在编辑中使用
	 */
	@RequestMapping("/d3searchQnTitleByIdForEdit.do")
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
			NiDaily3UpdateListItem daily3updateDelivery = daily3updateListService
					.d3searchQnTitleById(hashMap);
			if (daily3updateDelivery != null) {
				String qnTitle = daily3updateDelivery.getShowTitle();
				log.info("===============>" + qnTitle);
				modelAndView.setViewName("manager/EditDaily3UpdateList");
				modelAndView.addObject("qnTitle", qnTitle);
				modelAndView.addObject("deliveryId", delivery_Id);
				modelAndView.addObject("showOrder", showOrder);
				modelAndView.addObject("qnType", qn_Type);
			} else {
				modelAndView.setViewName("manager/EditDaily3UpdateList");
				modelAndView.addObject("qnTitle", "该投放不存在");
				modelAndView.addObject("showOrder", showOrder);
			}
		} else {
			modelAndView.setViewName("manager/EditDaily3UpdateList");
			modelAndView.addObject("qnTitle", "请输入数字Id");
			modelAndView.addObject("showOrder", showOrder);
		}
		return modelAndView;

	}

	/**
	 *  保存添加，将新投放加入页面列表中
	 */
	@RequestMapping("/addDaily3UpdateDelivery.do")
	@ResponseBody
	public AjaxResult addDaily3UpdateDelivery(HttpServletRequest request,Model model) {
		AjaxResult result = daily3updateListService.addDaily3UpdateDelivery(request,model);
		return result;
	}

	@RequestMapping("/replaceDaily3UpdateDelivery.do")
	@ResponseBody
	public AjaxResult replaceDaily3UpdateDelivery(HttpServletRequest request) {
		String deliveryId = request.getParameter("deliveryId");
		int delivery_Id = Integer.valueOf(deliveryId);
		String showOrder = request.getParameter("showOrder");
		int show_Order = Integer.valueOf(showOrder);
		String qnType = request.getParameter("qnType");
		int qn_Type = Integer.valueOf(qnType);
		AjaxResult result = daily3updateListService.replaceDaily3UpdateDelivery(delivery_Id,
				show_Order,qn_Type);
		return result;
	}

	
	/**
	 * @Title: moveUpDaily3Update  
	 * @Author: Guan
	 * @Description: 上移
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月29日 下午2:37:46
	 */
	@RequestMapping("/moveUpDaily3Update.do")
	@ResponseBody
	public AjaxResult moveUpDaily3Update(HttpServletRequest request) {
		String itemId = request.getParameter("itemId");
		String showOrder = request.getParameter("showOrder");
		String lastItemId = request.getParameter("lastItemId");
		String lastShowOrder = request.getParameter("lastShowOrder");
		AjaxResult result = daily3updateListService.moveUpDaily3Update(itemId,showOrder,lastItemId,lastShowOrder);
		return result;
	}
	/**
	 * @Title: moveDownDaily3Update  
	 * @Author: Guan
	 * @Description: 下移
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年8月1日 下午12:54:48
	 */
	@RequestMapping("/moveDownDaily3Update.do")
	@ResponseBody
	public AjaxResult moveDownDaily3Update(HttpServletRequest request) {
		String itemId = request.getParameter("itemId");
		String showOrder = request.getParameter("showOrder");
		String nextitemId = request.getParameter("nextitemId");
		String nextShowOrder = request.getParameter("nextShowOrder");
		AjaxResult result = daily3updateListService.moveDownDaily3Update(itemId,showOrder,nextitemId,nextShowOrder);
		return result;
	}
}
