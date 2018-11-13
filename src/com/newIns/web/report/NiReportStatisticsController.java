package com.newIns.web.report;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.model.report.NiReportStatistics;
import com.newIns.model.report.NiReportStatisticsCodingConvert;
import com.newIns.service.NiReportStatisticsService;
import com.newIns.tools.ExcelExportUtil;
import com.newIns.tools.ExcelExportUtil_new;
import com.newIns.web.NiDeliveryStatisticsController;

/**
 * @author lj
 * @Description : 报告统计的Controller
 * @time : 2016年8月9日 下午4:40:59
 */
@Controller
@RequestMapping("/platform")
public class NiReportStatisticsController {
	//日志
	private static Logger log=Logger.getLogger(NiDeliveryStatisticsController.class);
	
	@Resource
	private NiReportStatisticsService niReportStatisticsService;

	/**
	 * @author lj
	 * @Description : 返回按条件查询的报告统计列表
	 * @time : 2016年8月10日 下午1:09:24
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("niReportStatisticsList.do")
	public ModelAndView NiReportStatisticsList(HttpServletRequest request,HttpServletResponse response){
		
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		log.info("========================>>分页查询报告统计信息");
		//接受参数
		String reportDetail=request.getParameter("reportDetail");
		String reportContent=request.getParameter("reportContent");
		String reportChannel=request.getParameter("reportChannel");
		String reportStatus=request.getParameter("reportStatus");
		String selectReportType=request.getParameter("selectReportType");
		//投放统计搜索条件[投放id&问卷名称]
		if(reportDetail!=null && !("".equals(reportDetail)) && reportContent!=null && !("".equals(reportContent))){
			if(reportDetail.trim().equals("0")){
				//如果用户选择报告id为搜索条件
				
				hashMap.put("reportId", Integer.valueOf(reportContent.trim()));
			}else if(reportDetail.trim().equals("1")){
				//如果用户选择问卷名称为搜索条件
				hashMap.put("qnName", reportContent.trim());
			}
		}
		
		//报告统计搜索条件[渠道]
		if(reportChannel!=null && !("".equals(reportChannel))){
			hashMap.put("channel", reportChannel);
		}
		
		//报告统计搜索条件[问卷类型]
		if(selectReportType!=null && !("".equals(selectReportType))){
			hashMap.put("qnType", selectReportType);
		}
		
		//报告统计搜索条件[状态]
		if(reportStatus!=null && !("".equals(reportStatus))){
			hashMap.put("reportStatus", Integer.valueOf(reportStatus));
		}
		
		List<NiReportStatistics> niReportStatistics=niReportStatisticsService.getReportStatisticsList(hashMap);
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("manager/NiReportStatisticsList");
		modelAndView.addObject("niReportStatisticsList", niReportStatistics);
		return modelAndView;
	}
	

	/**
	 * @author lj
	 * @Description : 导出当前报告统计表格到excel中
	 * @time : 2016年8月10日 下午7:06:49
	 * @param request
	 * @param response
	 */
	@RequestMapping("exportReportStatisticsSheet.do")
	public void exportSheet(HttpServletRequest request,HttpServletResponse response){
		//接受参数
		String reportId=request.getParameter("reportId");
		String channel=request.getParameter("channel");
		String type=request.getParameter("type");
		
		long time0 = System.currentTimeMillis();
		List<NiReportStatistics> niReportStatisticsList=niReportStatisticsService.getExportReportStatisticsList(reportId, channel, type);
		List<NiReportStatisticsCodingConvert> niReportStatisticsCodingConvertsList=niReportStatisticsService.codingConvert(niReportStatisticsList);
		LinkedHashMap<String, String> fieldMap=niReportStatisticsService.getReportStatisticsExcelColumnName();
		
		long time1 = System.currentTimeMillis();
		log.info("导出当前报告统计表格到excel中 , 其中从数据库查询数据需要时间为 : "+ (time1-time0));
		//导出
		log.info("========================>>开始将报告统计列表导出到Excel");
		
		try {
			
			ExcelExportUtil.listToExcel(niReportStatisticsCodingConvertsList, fieldMap, "报告统计列表", response);
			
			long time2 = System.currentTimeMillis();
			log.info("导出当前报告统计表格到excel中 , 封装数据到excel中时间为:  "+ (time2-time1));
			
			log.info("========================>>报告统计列表导出Excel成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("========================>>报告统计列表导出Excel失败");
		}

	}
	
	/**
	 * @author lj
	 * @Description : 导出当前报告统计表格到excel中
	 * @time : 2016年8月10日 下午7:06:49
	 * @param request
	 * @param response
	 */
	@RequestMapping("exportReportStatisticsSheet_new.do")
	public void exportSheet_new(HttpServletRequest request,HttpServletResponse response){
		//接受参数
		String reportId=request.getParameter("reportId");
		String channel=request.getParameter("channel");
		String type=request.getParameter("type");
		
		List<NiReportStatistics> niReportStatisticsList=niReportStatisticsService.getExportReportStatisticsList(reportId, channel, type);
		List<NiReportStatisticsCodingConvert> niReportCodingConvertsList=niReportStatisticsService.codingConvert(niReportStatisticsList);
		LinkedHashMap<String, String> fieldMap=niReportStatisticsService.getReportStatisticsExcelColumnName();
		
			try {
				ExcelExportUtil_new.listToExcel(niReportCodingConvertsList, fieldMap, "导出excel", response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
}
