package com.newIns.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.model.NiDeliveryStatistics;
import com.newIns.model.NiDeliveryStatisticsCodingConvert;
import com.newIns.model.NiDeliveryStatisticsCodingConvert2;
import com.newIns.model.QuestionnaireStatisticsCube;
import com.newIns.service.ExportQtnStatisticsCubeService;
import com.newIns.service.NiDeliveryStatisticsService;
import com.newIns.tools.ExcelExportUtil;
import com.newIns.tools.ExcelExportUtil_new;
import com.newIns.tools.StrUtils;

/**
 * @author lj
 * @Description : 投放统计的Controller
 * @time : 2016年8月1日 下午8:03:41
 */

@Controller
@RequestMapping("/platform")
public class NiDeliveryStatisticsController {

	// 日志
	private static Logger log = Logger.getLogger(NiDeliveryStatisticsController.class);

	@Resource
	private NiDeliveryStatisticsService niDeliveryStatisticsService;

	@Resource
	private ExportQtnStatisticsCubeService exportQtnStatisticsCubeService;

	/**
	 * @author lj
	 * @Description : 返回按条件查询的投放统计列表
	 * @time : 2016年8月2日 下午4:43:45
	 * @return ModelAndView
	 */
	@RequestMapping("niDeliveryStatisticsList.do")
	public ModelAndView DeliveryStatisticsList(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		log.info("========================>>分页查询投放统计信息----------begin -----------");
		// 接受参数
		String searchType_str = request.getParameter("searchType");
		String deliveryContent = request.getParameter("deliveryContent");
		String deliveryChannel = request.getParameter("deliveryChannel");
		String deliveryStatus = request.getParameter("deliveryStatus");
		String selectDeliveryType = request.getParameter("selectDeliveryType");
		
		log.info("------------------>> 从 request中获取参数 : searchType_str :"+searchType_str +" , deliveryContent :"+deliveryContent +", deliveryChannel :"+deliveryChannel);
		log.info("---------------->> deliveryStatus :"+deliveryStatus+", selectDeliveryType :"+selectDeliveryType);
		
		// 投放统计搜索条件[投放id&问卷名称]
		if (StrUtils.isNotEmpty(searchType_str) && StrUtils.isNotEmpty(deliveryContent)) {
			if (searchType_str.trim().equals("0")) {
				// 如果用户选择投放id为搜索条件
				hashMap.put("deliveryId", Integer.valueOf(deliveryContent.trim()));
			} else if (searchType_str.trim().equals("1")) {
				// 如果用户选择问卷名称为搜索条件
				hashMap.put("qnTitle", deliveryContent.trim());
			}
		}
		// 投放统计搜索条件[投放渠道]
		if (StrUtils.isNotEmpty(deliveryChannel)) {
			hashMap.put("deliveryChannel", deliveryChannel);
		}
		// 投放统计搜索条件[问卷类型]
		if (StrUtils.isNotEmpty(selectDeliveryType)) {
			hashMap.put("selectDeliveryType", selectDeliveryType);
		}
		// 投放统计搜索条件[投放状态]
		if (StrUtils.isNotEmpty(deliveryStatus)) {
			hashMap.put("status", Integer.valueOf(deliveryStatus));
		}
		
		log.info("-------------------->> hashMap :" + hashMap);
		List<NiDeliveryStatistics> niDeliveryStatistics = niDeliveryStatisticsService.selectDeliveryStatisticsList(hashMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/NiDeliveryStatisticsList");
		modelAndView.addObject("niDeliveryStatistics", niDeliveryStatistics);
		
		return modelAndView;
	}

	/**
	 * @author lj
	 * @Description : 导出表格
	 * @time : 2016年8月3日 下午5:30:21
	 * @param request
	 * @param response
	 */
	@RequestMapping("exportSheet.do")
	public void exportSheet(HttpServletRequest request,
			HttpServletResponse response) {

		String deliveryId = request.getParameter("deliveryId");
		String channel = request.getParameter("checkChannel");
		String checkType = request.getParameter("checkType");
		List<NiDeliveryStatistics> niDeliveryStatisticsList = niDeliveryStatisticsService
				.getDeliveryStatisticsByKey(deliveryId, channel, checkType);
		List<NiDeliveryStatisticsCodingConvert> niDeliveryStatisticsCodingConvertsList = niDeliveryStatisticsService
				.codingConvert(niDeliveryStatisticsList);
		LinkedHashMap<String, String> fieldMap = niDeliveryStatisticsService
				.getExcelColumnName();

		// 导出
		log.info("========================>>开始将投放统计列表导出到Excel");

		try {

			ExcelExportUtil.listToExcel(niDeliveryStatisticsCodingConvertsList,
					fieldMap, "投放统计", response);
			log.info("========================>>投放统计列表导出Excel成功");

		} catch (Exception e) {
			e.printStackTrace();
			log.info("========================>>投放统计列表导出Excel失败");

		}

	}

	/**
	 * @author lj
	 * @Description : 下载答题明细（纵表格式，便于统计）
	 * @time : 2016年8月4日 下午5:58:20
	 * @param request
	 * @param response
	 */
	@RequestMapping("downloadDetail1.do")
	public void ajaxResult(HttpServletRequest request,HttpServletResponse response) {
		String deliveryId = request.getParameter("deliveryId");   //投放id
		String answerChannel = request.getParameter("answerChannel");  //渠道
		String type = request.getParameter("type");   //类型

		long time0 = System.currentTimeMillis();
		
		log.info("========================>>页面请求导出 投放统计答题明细 数据, 下面开始查询数据库, 请耐心等待... ...");
		
		List<NiDeliveryStatistics> niDeliveryStatisticsList = niDeliveryStatisticsService
				.getDownloadDeliveryStatisticsList(deliveryId, answerChannel,type);
		
		log.info("=================>>投放统计答题明细 数据 已经查询完毕  下面进行数据的封装");
		
		List<NiDeliveryStatisticsCodingConvert> niReportCodingConvertsList = niDeliveryStatisticsService
				.codingConvert(niDeliveryStatisticsList);
		
		log.info("数据已经全部查出 , 共查询出数据记录数 :"+niReportCodingConvertsList.size());
		
		LinkedHashMap<String, String> fieldMap = niDeliveryStatisticsService.getDownLoadColumnName();

		long time1 = System.currentTimeMillis();
		log.info("---------------------------->>投放统计,导出答题明细, 查询数据库 一共经历时间  : "+(time1 - time0));
		
		// 导出
		log.info("========================>>准备将数据填充到Excel中...请等待");
		
		try {
//			ExcelExportUtil.listToExcel(niReportCodingConvertsList,fieldMap, "投放统计答题明细", response);
			ExcelExportUtil_new.listToExcel(niReportCodingConvertsList, fieldMap, "sheet", response);
			
			long time2 = System.currentTimeMillis();
			log.info("========================>>下载投放统计答题明细成功 , 填充数据到Excel中所需时间 : "+(time2 - time1));

		} catch (Exception e) {
			e.printStackTrace();
			log.info("========================>>下载投放统计答题明细失败");

		}
	}
	
	/**
	 * 导出 单个问卷答题信息 到excel
	 */
	@RequestMapping("/download_AnswerDetail.do")
	public void download_AnswerDetail(HttpServletRequest request,HttpServletResponse response){

		try {
			niDeliveryStatisticsService.download_AnswerDetail(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author lijz
	 * @Description : 下载答题明细（横表格式，便于查看）
	 */
	@RequestMapping("downloadDetail2.do")
	public void downloadDetail2(HttpServletRequest request,HttpServletResponse response) {
		
		String deliveryId = request.getParameter("deliveryId");
		String answerChannel = request.getParameter("answerChannel");
		String type = request.getParameter("type");
		
		// 根据问卷id得到所有答此问卷的用户答题详情
		Map<String, Object> dataMap = 
				niDeliveryStatisticsService.getDownloadDeliveryStatisticsList2(deliveryId, answerChannel,type);
		
		//问题数量
		Integer questionQty = (Integer) dataMap.get("questionQty");
		//问题内容
		List<NiDeliveryStatistics> niDeliveryStatisticsList = (List<NiDeliveryStatistics>) dataMap.get("newNiDeliveryStatisticsList");
		
		
		List<NiDeliveryStatisticsCodingConvert2> niDeliveryStatisticsCodingConvertsList = null;
		try {
			niDeliveryStatisticsCodingConvertsList = niDeliveryStatisticsService.codingConvert2(niDeliveryStatisticsList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// 封装 excel 表头信息
		LinkedHashMap<String, String> fieldMap = niDeliveryStatisticsService
				.getDownLoadColumnName2(questionQty);
		
		// 导出
		log.info("========================>>开始下载投放统计答题明细（横表格式）");
		
		try {
			log.info("-------------->>将投放统计答题明细 数据 写入到Excel中... begin...... ");
			ExcelExportUtil.listToExcel(niDeliveryStatisticsCodingConvertsList,fieldMap, "投放统计答题明细", response);
			log.info("========================>>下载投放统计答题明细成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("========================>>下载投放统计答题明细失败");
		}
	}
	
	
	

	/**
	 * 
	 * @param request
	 * @param response
	 * @Description : 下载Cube表明细
	 */
	@RequestMapping("exportStatisticsCube.do")
//	@ResponseBody
	public void exportStatisticsCube(HttpServletRequest request,
			HttpServletResponse response) {
		String deliveryIdArr = request.getParameter("deliveryIdArr");
		String typeArr = request.getParameter("typeArr");
		String channelStr = request.getParameter("channel");
		if (deliveryIdArr == null || typeArr == null
				|| deliveryIdArr.trim().equals("") || typeArr.trim().equals("")) {
			
//			return AjaxResult.errorResult("前端传递参数错误！");
			return;
		}
		//获取deliveryId，type(调查，测评，投票)列表
		String[] deliveryIdStrs = deliveryIdArr.split("-");
		String[] typeStrs = typeArr.split("-");
		if (deliveryIdStrs.length != typeStrs.length) {
//			return AjaxResult.errorResult("前端传递参数错误！");
			return;
		}
		List<QuestionnaireStatisticsCube> cubeList = new ArrayList<QuestionnaireStatisticsCube>();//cubeList包含所有cube表行数据
		for (int i = 0; i < deliveryIdStrs.length; i++) {
			int deliveryId = Integer.parseInt(deliveryIdStrs[i]);
			int type = Integer.parseInt(typeStrs[i]);
			int channel = Integer.parseInt(channelStr);

			//QuestionnaireStatisticsCubeList包含每个选中问卷的Cube表
			List<QuestionnaireStatisticsCube> QuestionnaireStatisticsCubeList = exportQtnStatisticsCubeService
					.getQuestionnaireStatisticsCubeList(deliveryId, type,
							channel);
			
			cubeList.addAll(QuestionnaireStatisticsCubeList);
		}
		//获取excel表标题行各列字段
		LinkedHashMap<String, String> fieldMap = exportQtnStatisticsCubeService
				.getDownLoadColumnName();
		log.info("========================>>开始下cube表明细");
		try {
			ExcelExportUtil.listToExcel(cubeList, fieldMap, "cube表详细", response);
			log.info("========================>>下载cube表明细成功");

		} catch (Exception e) {
			e.printStackTrace();
			log.info("========================>>下载cube表明细失败");
//			return AjaxResult.errorResult("下载Cube表成功失败！" + e.getMessage());
		}
		
		System.out.println();
		log.info("-------------------------------");
//		return AjaxResult.successResult("下载Cube表成功！");

	}
}
