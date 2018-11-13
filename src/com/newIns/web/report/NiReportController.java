package com.newIns.web.report;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.model.report.NiReport;
import com.newIns.model.report.NiReportListItem;
import com.newIns.service.NiReportListService;
import com.newIns.service.NiReportService;
import com.newIns.tools.AjaxResult;

/**
 * Created by lyr on 16/6/29.
 */
@Controller
@RequestMapping("/platform")
public class NiReportController {
	private static Logger log = Logger.getLogger(NiReportController.class);

	@Resource
	private NiReportService niReportService;
	@Resource
	private NiReportListService reportListService;

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/NiReportAdd.do")
	public String addNiReport() {

		return "manager/NiReportAdd";
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	@RequestMapping("/NiReportList.do")
	public ModelAndView listReport(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		log.info("========================>>分页查询调查问卷信息");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String searchOption = request.getParameter("searchOption");
		String searchContent = request.getParameter("searchContent");
		String qnType = request.getParameter("qnType");
		String reservation = request.getParameter("reservation");
		String reportClassId = request.getParameter("reportClassId");
		String reportStatus = request.getParameter("reportStatus");

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
		if (starTime != null && !starTime.trim().equals("")) {
			Date date = new Date(starTime);
			Date date2 = new Date(endTime);
			starTime1 = format.format(date);
			endTime1 = format.format(date2);
		}

		if (searchOption != null && !searchOption.trim().equals("")) {
			// 判断选择的是报告名称，还是发布机构名称
			if (searchOption.trim().equals("1")) {
				// 问卷名称
				hashMap.put("reportTitle", searchContent.trim());
			} else if (searchOption.trim().equals("2")) {
				// 发布机构名称
				hashMap.put("publisher", searchContent.trim());
			} else if (searchOption.trim().equals("3")) {
				// 报告Id
				hashMap.put("reportId", searchContent.trim());
			}
		}

		if (qnType != null && !qnType.trim().equals("")) {
			// 问卷类型
			hashMap.put("qnType", Integer.valueOf(qnType));
		}

		if (reportStatus != null && !reportStatus.trim().equals("")) {
			// 问卷状态
			hashMap.put("reportStatus", Integer.valueOf(reportStatus));
		}

		if (reportClassId != null && !reportClassId.trim().equals("")) {
			// 问卷类型
			hashMap.put("reportClassId", Integer.valueOf(reportClassId));
		}
		if (starTime != null && !starTime.trim().equals("")) {
			hashMap.put("starTime", starTime1);
			hashMap.put("endTime", endTime1);
		}

		List<NiReport> reportList = niReportService.selectList(hashMap);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/NiReportList");
		modelAndView.addObject("niReportList", reportList);

		return modelAndView;
	}

	/**
	 * 添加报告
	 * 
	 * @param report
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addNiReport.do", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addReport(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("uploadImgmes") MultipartFile picfile,
			@RequestParam("uploadZip") MultipartFile zipfile) throws Exception {
		// 接收新建报告相关参数
		String reportTitle = request.getParameter("reportTitle");
		String qnIdStr = request.getParameter("qnId");
		String reportUrl = request.getParameter("reportUrl");
		String qnTypeStr = request.getParameter("qnType");
		String reportClassIdStr = request.getParameter("reportClassId");
		String publisher = request.getParameter("publisher");
		String author = request.getParameter("author");
		String qnCollectedNumStr = request.getParameter("qnCollectedNum");
		String baseViewNumStr = request.getParameter("baseViewNum");
		String summary = request.getParameter("summary");
		String tag1Str = request.getParameter("tag1Str");
		String tag2Str = request.getParameter("tag2Str");
		String tag3Str = request.getParameter("tag3Str");
		String tag4Str = request.getParameter("tag4Str");
		String tag1IdStr = request.getParameter("tag1Id");
		String tag2IdStr = request.getParameter("tag2Id");
		String tag3IdStr = request.getParameter("tag3Id");
		String tag4IdStr = request.getParameter("tag4Id");
		int qnType = 0;
		if (qnTypeStr != null) {
			qnType = Integer.parseInt(qnTypeStr);
		}

		int qnId = 0;
		if (qnIdStr != null) {
			qnId = Integer.parseInt(qnIdStr);
		}
		int reportClassId = 0;
		if (reportClassIdStr != null) {
			reportClassId = Integer.parseInt(reportClassIdStr);
		}
		int qnCollectedNum = 0;
		if (qnCollectedNumStr != null) {
			qnCollectedNum = Integer.parseInt(qnCollectedNumStr);
		}
		int baseViewNum = 0;
		if (baseViewNumStr != null) {
			baseViewNum = Integer.parseInt(baseViewNumStr);
		}
		int tag1Id = 0;
		if (tag1IdStr != null) {
			tag1Id = Integer.parseInt(tag1IdStr);
		}
		int tag2Id = 0;
		if (tag2IdStr != null) {
			tag2Id = Integer.parseInt(tag2IdStr);
		}
		int tag3Id = 0;
		if (tag3IdStr != null) {
			tag3Id = Integer.parseInt(tag3IdStr);
		}
		int tag4Id = 0;
		if (tag4IdStr != null) {
			tag4Id = Integer.parseInt(tag4IdStr);
		}
		NiReport report = new NiReport();
		report.setReportTitle(reportTitle);
		report.setQnId(qnId);
		report.setReportStatus(1);
		report.setReportUrl(reportUrl);
		report.setQnType(qnType);
		report.setReportClassId(reportClassId);
		report.setPublisher(publisher);
		report.setAuthor(author);
		report.setBaseViewNum(baseViewNum);
		report.setQnCollectedNum(qnCollectedNum);
		report.setSummary(summary);
		report.setTag1Id(tag1Id);
		report.setTag2Id(tag2Id);
		report.setTag3Id(tag3Id);
		report.setTag4Id(tag4Id);
		report.setTag1Str(tag1Str);
		report.setTag2Str(tag2Str);
		report.setTag3Str(tag3Str);
		report.setTag4Str(tag4Str);
		// 报告创建时间
		Timestamp cTime = new Timestamp(System.currentTimeMillis());
		report.setcTime(cTime);
		// 在数据库中生成一条记录
		int addReport = 0;
		try {
			report.setReportStatus(1); // 设置草稿状态
			addReport = niReportService.addReport(report);
		} catch (Exception e) {
			return AjaxResult.errorResult("创建报告失败");
		}

		if (addReport > 0) {
			log.info("==========================>>添加成功!");
			// 根据添加的报告获取他的reportId
			NiReport niReport = niReportService.getReportIdByReportInfo(report);
			int reportId = niReport.getReportId();
			String reportIdStr = String.valueOf(reportId);
			// 上传列表图片
			// 获取上传图片结果
			Map<String, Object> picUploadResultMap = niReportService
					.uploadReportListPic(request, picfile, reportIdStr);

			if ((Boolean) picUploadResultMap.get("success")) {
				log.info("==========================>>上传图片成功！");
			} else
				return AjaxResult.successResult("上传报告列表图失败");
			// 将报告列表图片jdbcUrl写入表中
			niReportService.updateImgUrl(reportId,
					(String) picUploadResultMap.get("jdbcUrl"));
			log.info("==========================>>imgurl记录成功！");

			// 上传报告zip包
			Map<String, Object> picUploadZipResultMap = niReportService
					.uploadReportZip(request, zipfile, reportIdStr);
			if ((Boolean) picUploadZipResultMap.get("success")) {
				log.info("==========================>>上传zip成功！");
			} else
				return AjaxResult.successResult("上传zip失败");
			// 更新reportUrl
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String formatDate = sdf.format(new Date());
			String reportUrlPath = "/data/res/static/html/report/" + formatDate
					+ "/" + reportId + "/"
					+ picUploadZipResultMap.get("reportHtml");
			niReportService.updateReportUrl(reportId, reportUrlPath);

		}

		return AjaxResult.successResult("添加成功");
	}

	/**
	 * 批量定稿
	 * 
	 * @return
	 */
	@RequestMapping("/releaseReport.do")
	@ResponseBody
	public AjaxResult updateReportStatus(HttpServletRequest request,
			HttpServletResponse response) {
		String reportIds = request.getParameter("reportId");
		String[] reportId = reportIds.split("!");
		// 将所有待发布报告的id封装到一个list中
		ArrayList<Integer> reportIdList = new ArrayList<Integer>();
		for (int i = 0; i < reportId.length; i++) {
			reportIdList.add(Integer.valueOf(reportId[i]));
		}

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("reportIds", reportIdList);
		hashMap.put("reportStatus", 2);

		int updateByPrimarayKey = 0;

		try {
			updateByPrimarayKey = niReportService.updateReportStatus(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("失败");
		}

		if (updateByPrimarayKey > 0)
			log.info("==========================>>报告定稿成功!");

		return AjaxResult.successResult("报告定稿成功!");
	}

	/**
	 * @Title: modifyReport
	 * @Author: MaNia_chAng
	 * @Description: 根据报告ID批量修改
	 * @param request
	 * @param response
	 * @return AjaxResult
	 * @Time 2016年7月11日 下午4:48:31
	 */
	@RequestMapping("/modifyReport.do")
	@ResponseBody
	public AjaxResult modifyReport(HttpServletRequest request, Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String reportIds = request.getParameter("reportId");
		String[] reportIdArray = reportIds.split("!");
		ArrayList<Integer> reportIdList = new ArrayList<Integer>();
		for (int i = 0; i < reportIdArray.length; i++) {
			int reportId = Integer.valueOf(reportIdArray[i]);
			reportIdList.add(reportId);

			// 顾后判断
			NiReportListItem reportListItem = reportListService
					.searchReportFromList(reportId);
			if (reportListItem != null) {
				int pageStatus = reportListItem.getPageStatus();
				if (pageStatus != 1) {
					return AjaxResult.errorResult("报告已经发布到报告页面中,无法修改!");
				}
			}
		}

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("reportStatus", 1);
		hashMap.put("reportIds", reportIdList);
		int updateByPrimaryKeySelective = 0;
		try {
			updateByPrimaryKeySelective = niReportService
					.updateReportStatus(hashMap);
			log.info("===============================>>批量修改报告");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (updateByPrimaryKeySelective > 0) {
			log.info("===============================>>批量修改报告成功");
			ajaxResult.put("success", true);
			ajaxResult.put("msg", "批量修改报告成功");
		}

		return ajaxResult;
	}

	/**
	 * @Title: discardReport
	 * @Author: MaNia_chAng
	 * @Description: 根据报告ID批量废弃报告
	 * @param request
	 * @param response
	 * @return AjaxResult
	 * @Time 2016年8月11日 下午3:34:44
	 */
	@RequestMapping("/discardReport.do")
	@ResponseBody
	public AjaxResult discardReport(HttpServletRequest request,
			HttpServletResponse response) {
		String reportIds = request.getParameter("reportId");
		String[] reportIdArray = reportIds.split("!");
		ArrayList<Integer> reportIdList = new ArrayList<Integer>();
		for (int i = 0; i < reportIdArray.length; i++) {
			reportIdList.add(Integer.valueOf(reportIdArray[i]));
		}

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("reportStatus", 3);
		hashMap.put("reportIds", reportIdList);
		int updateByPrimaryKeySelective = 0;
		try {
			updateByPrimaryKeySelective = niReportService
					.updateReportStatus(hashMap);
			log.info("===============================>>批量废弃报告");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (updateByPrimaryKeySelective > 0) {
			log.info("===============================>>批量废弃报告成功");
		}

		return AjaxResult.successResult("批量废弃报告成功");
	}

	/**
	 * @Title: restoreReport
	 * @Author: MaNia_chAng
	 * @Description: 根据报告ID批量恢复报告
	 * @param request
	 * @param response
	 * @return AjaxResult
	 * @Time 2016年8月11日 下午3:45:35
	 */
	@RequestMapping("/restoreReport.do")
	@ResponseBody
	public AjaxResult restoreReport(HttpServletRequest request,
			HttpServletResponse response) {
		String reportIds = request.getParameter("reportId");
		String[] reportIdArray = reportIds.split("!");
		ArrayList<Integer> reportIdList = new ArrayList<Integer>();
		for (int i = 0; i < reportIdArray.length; i++) {
			reportIdList.add(Integer.valueOf(reportIdArray[i]));
		}

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("reportStatus", 1);
		hashMap.put("reportIds", reportIdList);
		int updateByPrimaryKeySelective = 0;
		try {
			updateByPrimaryKeySelective = niReportService
					.updateReportStatus(hashMap);
			log.info("===============================>>批量恢复报告");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (updateByPrimaryKeySelective > 0) {
			log.info("===============================>>批量恢复报告成功");
		}

		return AjaxResult.successResult("批量恢复报告成功");
	}

	/**
	 * @Title: deleteReport
	 * @Author: MaNia_chAng
	 * @Description: 批量删除
	 * @param request
	 * @param response
	 * @return AjaxResult
	 * @Time 2016年7月11日 下午5:26:40
	 */
	@RequestMapping("/deleteReport.do")
	@ResponseBody
	public AjaxResult deleteReport(HttpServletRequest request,
			HttpServletResponse response) {
		String reportIds = request.getParameter("reportId");
		String[] reportIdArray = reportIds.split("!");
		ArrayList<Integer> reportIdList = new ArrayList<Integer>();
		for (int i = 0; i < reportIdArray.length; i++) {
			reportIdList.add(Integer.valueOf(reportIdArray[i]));
		}

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("reportIds", reportIdList);
		int deleteByPrimaryKeySelective = 0;
		try {
			deleteByPrimaryKeySelective = niReportService
					.deleteReportByIds(hashMap);
			log.info("===============================>>批量删除报告");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (deleteByPrimaryKeySelective > 0) {
			log.info("===============================>>批量删除报告成功");
		}

		return AjaxResult.successResult("批量删除报告成功");
	}

	/**
	 * 编辑报告页面
	 * 
	 * @return
	 */
	@RequestMapping("/editNiReport.do")
	public ModelAndView editReport(HttpServletRequest request) {
		String reportId = request.getParameter("reportId");

		NiReport niReport = niReportService.selectByPrimaryKey(Integer
				.parseInt(reportId));
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/NiReportAdd");
		modelAndView.addObject("report", niReport);
		modelAndView.addObject("isEdit", 1);
		return modelAndView;

	}

	/**
	 * @Title: addReport
	 * @Author: Guan
	 * @Description: 编辑报告
	 * @param request
	 * @param response
	 * @param picfile
	 * @param zipfile
	 * @return
	 * @throws Exception
	 *             AjaxResult
	 * @Time 2016年8月12日 下午8:05:23
	 */
	@RequestMapping(value = "/didEditReport.do", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult editReport(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("uploadImgmes") MultipartFile picfile,
			@RequestParam("uploadZip") MultipartFile zipfile) throws Exception {
		// 接收新建报告相关参数
		String reportIdStr = request.getParameter("reportId");
		int reportId = Integer.parseInt(reportIdStr);
		String reportTitle = request.getParameter("reportTitle");
		String qnIdStr = request.getParameter("qnId");
		String reportUrl = request.getParameter("reportUrl");
		String qnTypeStr = request.getParameter("qnType");
		String reportClassIdStr = request.getParameter("reportClassId");
		String publisher = request.getParameter("publisher");
		String author = request.getParameter("author");
		String qnCollectedNumStr = request.getParameter("qnCollectedNum");
		String baseViewNumStr = request.getParameter("baseViewNum");
		String summary = request.getParameter("summary");
		String tag1Str = request.getParameter("tag1Str");
		String tag2Str = request.getParameter("tag2Str");
		String tag3Str = request.getParameter("tag3Str");
		String tag4Str = request.getParameter("tag4Str");
		String tag1IdStr = request.getParameter("tag1Id");
		String tag2IdStr = request.getParameter("tag2Id");
		String tag3IdStr = request.getParameter("tag3Id");
		String tag4IdStr = request.getParameter("tag4Id");

		int qnType = 0;
		if (qnTypeStr != null) {
			qnType = Integer.parseInt(qnTypeStr);
		}

		int qnId = 0;
		if (qnIdStr != null) {
			qnId = Integer.parseInt(qnIdStr);
		}
		int reportClassId = 0;
		if (reportClassIdStr != null) {
			reportClassId = Integer.parseInt(reportClassIdStr);
		}
		int qnCollectedNum = 0;
		if (qnCollectedNumStr != null) {
			qnCollectedNum = Integer.parseInt(qnCollectedNumStr);
		}
		int baseViewNum = 0;
		if (baseViewNumStr != null) {
			baseViewNum = Integer.parseInt(baseViewNumStr);
		}
		int tag1Id = 0;
		if (tag1IdStr != null) {
			tag1Id = Integer.parseInt(tag1IdStr);
		}
		int tag2Id = 0;
		if (tag2IdStr != null) {
			tag2Id = Integer.parseInt(tag2IdStr);
		}
		int tag3Id = 0;
		if (tag3IdStr != null) {
			tag3Id = Integer.parseInt(tag3IdStr);
		}
		int tag4Id = 0;
		if (tag4IdStr != null) {
			tag4Id = Integer.parseInt(tag4IdStr);
		}
		NiReport report = new NiReport();
		report.setReportId(reportId);
		report.setReportTitle(reportTitle);
		report.setQnId(qnId);
		report.setReportStatus(1);
		report.setReportUrl(reportUrl);
		report.setQnType(qnType);
		report.setReportClassId(reportClassId);
		report.setPublisher(publisher);
		report.setAuthor(author);
		report.setBaseViewNum(baseViewNum);
		report.setQnCollectedNum(qnCollectedNum);
		report.setSummary(summary);
		report.setTag1Id(tag1Id);
		report.setTag2Id(tag2Id);
		report.setTag3Id(tag3Id);
		report.setTag4Id(tag4Id);
		report.setTag1Str(tag1Str);
		report.setTag2Str(tag2Str);
		report.setTag3Str(tag3Str);
		report.setTag4Str(tag4Str);
		// 报告创建时间
		Timestamp cTime = new Timestamp(System.currentTimeMillis());
		report.setcTime(cTime);
		// 在数据库中生成一条记录
		int editReport = 0;
		// try {
		report.setReportStatus(1); // 设置草稿状态
		editReport = niReportService.editReport(report);
		// } catch (Exception e) {
		// return AjaxResult.errorResult("编辑报告失败");
		// }

		if (editReport > 0) {
			log.info("==========================>>编辑成功!");
			// 根据添加的报告获取他的reportId
			// NiReport niReport =
			// niReportService.getReportIdByReportInfo(report);
			// int reportId = niReport.getReportId();
			// String reportIdStr = String.valueOf(reportId);
			// 上传列表图片
			if (picfile.getSize()!=0) {
				// 获取上传图片结果
				Map<String, Object> picUploadResultMap = niReportService
						.uploadReportListPic(request, picfile, reportIdStr);

				if ((Boolean) picUploadResultMap.get("success")) {
					log.info("==========================>>上传图片成功！");
				} else
					return AjaxResult.successResult("上传报告列表图失败");
				// 将报告列表图片jdbcUrl写入表中
				niReportService.updateImgUrl(reportId,
						(String) picUploadResultMap.get("jdbcUrl"));
				log.info("==========================>>imgurl记录成功！");
			}
			// 上传报告zip包
			if (zipfile.getSize()!=0) {
				Map<String, Object> picUploadZipResultMap = niReportService
						.uploadReportZip(request, zipfile, reportIdStr);
				if ((Boolean) picUploadZipResultMap.get("success")) {
					log.info("==========================>>上传zip成功！");
				} else
					return AjaxResult.successResult("上传zip失败");
				// 更新reportUrl
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
				String formatDate = sdf.format(new Date());
				String reportUrlPath = "/data/res/static/html/report/"
						+ formatDate + "/" + reportId + "/"
						+ picUploadZipResultMap.get("reportHtml");
				niReportService.updateReportUrl(reportId, reportUrlPath);
			}

		}

		return AjaxResult.successResult("编辑成功");
	}
}