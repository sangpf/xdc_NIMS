/**
 * 
 */
package com.newIns.web.report;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.model.report.NiReportListItem;
import com.newIns.service.NiReportListService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.VerifyPattern;

/**
 * @Description 页面管理-列表管理-测一发
 * @author Guan
 * @time 2016年7月4日 下午3:38:00
 */
@Controller
@RequestMapping("/platform")
public class NiReportListController {
	private static Logger log = Logger.getLogger(NiReportListController.class);
	@Resource
	private NiReportListService reportListService;

	/**
	 * @Title: loadReportList
	 * @Author: Guan
	 * @Description: 加载报告报告列表
	 * @return ModelAndView
	 * @Time 2016年7月12日 上午10:21:04
	 */
	@RequestMapping("/loadReportList.do")
	public ModelAndView loadReportList(HttpServletRequest request) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		log.info("========================>>分页查询调查报告信息");
		String choose = request.getParameter("choose");
		String reportName = request.getParameter("reportName");
		String pageStatus = request.getParameter("pageStatus");
		String reportStatus = request.getParameter("reportStatus");
		String reservation = request.getParameter("reservation");
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

		if (choose != null && !choose.trim().equals("") && reportName != null
				&& !reportName.trim().equals("")) {
			if (choose.trim().equals("0")) {
				// 报告名称
				hashMap.put("reportName", reportName.trim());
			}else if (choose.trim().equals("1")) {
				//投放Id
				hashMap.put("reportId", reportName.trim());
			}
		}

		if (pageStatus != null && !pageStatus.trim().equals("")) {
			// 页面状态
			hashMap.put("pageStatus", Integer.valueOf(pageStatus));
		}

		if (reportStatus != null && !reportStatus.trim().equals("")) {
			// 报告状态
			hashMap.put("reportStatus", Integer.valueOf(reportStatus));
		}

		if (reservation != null && !reservation.trim().equals("")) {
			hashMap.put("starTime", starTime1);
			hashMap.put("endTime", endTime1);
		}

		log.info("========================>>加载报告信息列表管理页面");

		List<NiReportListItem> reportList = reportListService
				.loadReportList(hashMap);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/NiReport");
		modelAndView.addObject("niReport", reportList);
		return modelAndView;
	}

	/**
	 * @Title: postReport
	 * @Author: Guan
	 * @Description: 发布报告报告（页面状态）
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 上午10:25:00
	 */
	@RequestMapping("/postReportList.do")
	@ResponseBody
	public AjaxResult postReport(HttpServletRequest request) {
		String reportIds = request.getParameter("reportId");
		String[] reportId = reportIds.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> reportIdList = new ArrayList<Integer>();
		for (int i = 0; i < reportId.length; i++) {
			reportIdList.add(Integer.valueOf(reportId[i]));
		}

		hashMap.put("state", 3);
		hashMap.put("reportIds", reportIdList);
		Timestamp pTime = new Timestamp(System.currentTimeMillis());
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("pTime", pTime);
		hashMap.put("uTime", uTime);
		int postByPrimaryKeySelective = 0;
		try {
			postByPrimaryKeySelective = reportListService
					.postReportByids(hashMap);
			log.info("===============================>>批量发布报告成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (postByPrimaryKeySelective > 0) {
			log.info("==========================>>更改状态成功!");
		}
		return AjaxResult.successResult("批量发布报告成功");
	}

	/**
	 * @Title: revokeReport
	 * @Author: Guan
	 * @Description: 批量撤销轮播广告
	 * @param request
	 * @return String
	 * @Time 2016年7月5日 下午7:15:50
	 */
	@RequestMapping("/revokeReportList.do")
	@ResponseBody
	public AjaxResult revokeReport(HttpServletRequest request) {
		String reportIds = request.getParameter("reportId");
		String[] reportId = reportIds.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		// ArrayList<Integer> reportIdList = new ArrayList<Integer>();
		for (int i = 0; i < reportId.length; i++) {
			// reportIdList.add(Integer.valueOf(reportId[i]));

			hashMap.put("state", 1);
			hashMap.put("reportIds", reportId[i]);
			hashMap.put("uTime", uTime);
			int revokeByPrimaryKeySelective = 0;
			try {
				revokeByPrimaryKeySelective = reportListService
						.revokeReportByids(hashMap);
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
	 * 批量删除报告
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteReportFromList.do")
	@ResponseBody
	public AjaxResult deleteReportFromList(HttpServletRequest request) {
		String parameter = request.getParameter("reportId");
		String[] reportId = parameter.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> reportIds = new ArrayList<Integer>();
		for (int i = 0; i < reportId.length; i++) {
			reportIds.add(Integer.valueOf(reportId[i]));
		}
		hashMap.put("reportIds", reportIds);
		int updateByPrimaryKeySelective = 0;

		try {
			reportListService.deleteReportByIds(hashMap);
			log.info("===============================>>批量删除报告");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (updateByPrimaryKeySelective > 0) {
			log.info("===============================>>批量删除报告成功");
		}
		return AjaxResult.successResult("删除调查报告成功");
	}

	/**
	 * @Title: topReport
	 * @Author: Guan
	 * @Description: 置顶报告
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 下午2:51:16
	 */
	@RequestMapping("/topReport.do")
	@ResponseBody
	public AjaxResult topReport(HttpServletRequest request) {
		String reportId = request.getParameter("reportId");
		int report_Id = Integer.valueOf(reportId);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("uTime", uTime);
		hashMap.put("reportId", report_Id);
		reportListService.topReport(hashMap);
		return AjaxResult.successResult("置顶成功");
	}

	/**
	 * @Title: topCancelReport
	 * @Author: Guan
	 * @Description: 取消置顶报告
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 下午2:51:16
	 */
	@RequestMapping("/topCancelReport.do")
	@ResponseBody
	public AjaxResult topCancelReport(HttpServletRequest request) {
		String reportId = request.getParameter("reportId");
		int report_Id = Integer.valueOf(reportId);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("uTime", uTime);
		hashMap.put("reportId", report_Id);
		reportListService.topCancelReport(hashMap);
		return AjaxResult.successResult("取消成功");
	}

	/**
	 * @Title: addReportToList
	 * @Author: Guan
	 * @Description: 跳转到添加页面
	 * @return ModelAndView
	 * @Time 2016年7月12日 下午3:23:08
	 */
	@RequestMapping("/addReportToList.do")
	public ModelAndView addReportToList() {
		log.info("========================>>跳转到添加页面");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/AddReportToList");
		return modelAndView;
	}

	/**
	 * @Title: editReportList
	 * @Author: Guan
	 * @Description: 编辑测一发列表（替换）
	 * @return ModelAndView
	 * @Time 2016年7月12日 下午3:23:08
	 */
	@RequestMapping("/editReportList.do")
	public ModelAndView editReportList(HttpServletRequest request) {
		String showOrder = request.getParameter("showOrder");
		int show_Order = Integer.valueOf(showOrder);
		ModelAndView modelAndView = new ModelAndView();
		log.info("========================>>跳转到编辑页面");
		modelAndView.setViewName("manager/EditReportList");
		modelAndView.addObject("showOrder", show_Order);
		return modelAndView;
	}

	/**
	 * @Title: searchQnTitleById
	 * @Author: Guan
	 * @Description: 根据报告id查询报告名称，在添加中使用
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 下午4:31:06
	 */
	@RequestMapping("/searchReportTitleById.do")
	public ModelAndView searchQnTitleById(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String reportId = request.getParameter("reportId");
		// 先验证输入id是否为数字
		if (VerifyPattern.isNumeric(reportId) && reportId != "") {
			int report_Id = Integer.valueOf(reportId);
			NiReportListItem reportItem = reportListService
					.searchReportTitleById(report_Id);
			if (reportItem != null) {
				String reportTitle = reportItem.getReportTitle();
				int reportStatus = reportItem.getReportStatus();
				log.info("===============>" + reportTitle);
				modelAndView.setViewName("manager/AddReportToList");
				modelAndView.addObject("reportTitle", reportTitle);
				modelAndView.addObject("reportId", report_Id);
				modelAndView.addObject("reportStatus", reportStatus);
			} else {
				modelAndView.setViewName("manager/AddReportToList");
				modelAndView.addObject("reportTitle", "该报告不存在");
			}
		} else {
			modelAndView.setViewName("manager/AddReportToList");
			modelAndView.addObject("reportTitle", "请输入数字Id");
		}
		return modelAndView;

	}

	/**
	 * @Title: searchAqnTitleByIdForEdit
	 * @Author: Guan
	 * @Description: 根据报告id查询报告名称,在编辑中使用
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月12日 下午4:31:06
	 */
	@RequestMapping("/searchReportTitleByIdForEdit.do")
	public ModelAndView searchAqnTitleByIdForEdit(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String reportId = request.getParameter("reportId");
		String showOrder = request.getParameter("showOrder");
		// 先验证输入id是否为数字
		if (VerifyPattern.isNumeric(reportId) && reportId != "") {
			int report_Id = Integer.valueOf(reportId);
			NiReportListItem report = reportListService
					.searchReportTitleById(report_Id);
			if (report != null) {
				String reportTitle = report.getReportTitle();
				log.info("===============>" + reportTitle);
				modelAndView.setViewName("manager/EditReportList");
				modelAndView.addObject("reportTitle", reportTitle);
				modelAndView.addObject("reportId", report_Id);
				modelAndView.addObject("showOrder", showOrder);
			} else {
				modelAndView.setViewName("manager/EditReportList");
				modelAndView.addObject("reportTitle", "该报告不存在");
				modelAndView.addObject("showOrder", showOrder);
			}
		} else {
			modelAndView.setViewName("manager/EditReportList");
			modelAndView.addObject("reportTitle", "请输入数字Id");
			modelAndView.addObject("showOrder", showOrder);
		}
		return modelAndView;

	}

	/**
	 * @Title: addReport
	 * @Author: Guan
	 * @Description: 保存添加，将新报告加入页面列表中
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月14日 下午12:44:15
	 */
	@RequestMapping("/addReport.do")
	@ResponseBody
	public AjaxResult addReport(HttpServletRequest request) {
		String reportId = request.getParameter("reportId");
		int report_Id = Integer.valueOf(reportId);
		AjaxResult result = reportListService.addReport(report_Id);
		return result;
	}

	@RequestMapping("/replaceReport.do")
	@ResponseBody
	public AjaxResult replaceReport(HttpServletRequest request) {
		String reportId = request.getParameter("reportId");
		int report_Id = Integer.valueOf(reportId);
		String showOrder = request.getParameter("showOrder");
		int show_Order = Integer.valueOf(showOrder);
		AjaxResult result = reportListService.replaceReport(report_Id,
				show_Order);
		return result;
	}
	
	/**
	 * @Title: moveUpReport  
	 * @Author: Guan
	 * @Description: 上移
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月29日 下午2:37:46
	 */
	@RequestMapping("/moveUpReport.do")
	@ResponseBody
	public AjaxResult moveUpReport(HttpServletRequest request) {
		String reportId = request.getParameter("reportId");
		int report_Id = Integer.valueOf(reportId);
		String showOrder = request.getParameter("showOrder");
		int show_Order = Integer.valueOf(showOrder);
		String lastReportId = request.getParameter("lastReportId");
		int last_ReportId = Integer.valueOf(lastReportId);
		String lastShowOrder = request.getParameter("lastShowOrder");
		int last_ShowOrder = Integer.valueOf(lastShowOrder);
		AjaxResult result = reportListService.moveUpReport(report_Id,show_Order,last_ReportId,last_ShowOrder);
		return result;
	}
	/**
	 * @Title: moveDownReport  
	 * @Author: Guan
	 * @Description: 下移
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年8月1日 下午12:54:48
	 */
	@RequestMapping("/moveDownReport.do")
	@ResponseBody
	public AjaxResult moveDownReport(HttpServletRequest request) {
		String reportId = request.getParameter("reportId");
		int report_Id = Integer.valueOf(reportId);
		String showOrder = request.getParameter("showOrder");
		int show_Order = Integer.valueOf(showOrder);
		String lastReportId = request.getParameter("nextReportId");
		int next_ReportId = Integer.valueOf(lastReportId);
		String lastShowOrder = request.getParameter("nextShowOrder");
		int next_ShowOrder = Integer.valueOf(lastShowOrder);
		AjaxResult result = reportListService.moveDownReport(report_Id,show_Order,next_ReportId,next_ShowOrder);
		return result;
	}

}
