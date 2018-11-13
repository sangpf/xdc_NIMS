package com.newIns.web;

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

import com.newIns.model.NiLotteryStatistics;
import com.newIns.service.NiLotteryStatisticsService;
import com.newIns.tools.ExcelExportUtil;

/**
 * @author lj
 * @Description : 抽奖统计的Controller
 * @time : 2016年8月15日 下午3:16:49
 */
@Controller
@RequestMapping("/platform")
public class NiLotteryStatisticsController {
	//日志
	private static Logger log=Logger.getLogger(NiLotteryStatisticsController.class);
	
	@Resource
	private NiLotteryStatisticsService niLotteryStatisticsService;
	/**
	 * @author lj
	 * @Description : 根据条件查询抽奖统计列表
	 * @time : 2016年8月16日 下午5:28:53
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("niLotteryStatisticsList.do")
	public ModelAndView getNiLotteryStatisticsList(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		log.info("========================>>分页查询抽奖统计信息");
		
		//接受参数
		String lotteryDetail=request.getParameter("lotteryDetail");
		String lotteryContent=request.getParameter("lotteryContent");
		//String deliveryChannel=request.getParameter("deliveryChannel");
		String deliveryStatus=request.getParameter("deliveryStatus");
		//抽奖统计搜索条件[投放id&问卷名称]
		if(lotteryDetail!=null && !("".equals(lotteryDetail)) && lotteryContent!=null && !("".equals(lotteryContent))){
			if(lotteryDetail.trim().equals("0")){
				//如果用户选择投放id为搜索条件
				
				hashMap.put("deliveryId", Integer.valueOf(lotteryContent.trim()));
			}else if(lotteryDetail.trim().equals("1")){
				//如果用户选择问卷名称为搜索条件
				hashMap.put("qnName", lotteryContent.trim());
			}
		}
		//抽奖统计搜索条件[投放状态]
		if(deliveryStatus!=null && !("".equals(deliveryStatus))){
			hashMap.put("status", Integer.valueOf(deliveryStatus));
		}
		List<NiLotteryStatistics> niLotteryStatisticsList=niLotteryStatisticsService.getLotteryStatisticsList(hashMap);
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("niLotteryStatisticsList", niLotteryStatisticsList);
		modelAndView.setViewName("manager/NiLotteryStatisticsList");
		
		return modelAndView;
	}
	
	

	/**
	 * @author lj
	 * @Description : 导出抽奖统计的表格到Excel
	 * @time : 2016年8月16日 下午6:02:23
	 * @param request
	 * @param response
	 */
	@RequestMapping("exportLotteryStatisticsSheet.do")
	public void exportLotteryStatisticsSheet(HttpServletRequest request,HttpServletResponse response){
		//接收参数
		String deliveryId=request.getParameter("deliveryId");
		String channel=request.getParameter("channel");
		String type=request.getParameter("type");
		List<NiLotteryStatistics> niLotteryStatisticsList=niLotteryStatisticsService.exportLotteryStatisticsSheet(deliveryId, channel, type);
		//编码转换
		List<NiLotteryStatistics> newNiLotteryStatisticsList=niLotteryStatisticsService.codingConvert(niLotteryStatisticsList);
		LinkedHashMap<String, String> fieldMap=niLotteryStatisticsService.getExportLotteryStatisticsColumn();
		//导出
		log.info("========================>>开始将抽奖统计列表导出到Excel");
		
		try {
			ExcelExportUtil.listToExcel(newNiLotteryStatisticsList, fieldMap, "抽奖统计", response);
			log.info("========================>>抽奖统计列表导出Excel成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("========================>>抽奖统计列表导出Excel失败");
		}
	}
}
