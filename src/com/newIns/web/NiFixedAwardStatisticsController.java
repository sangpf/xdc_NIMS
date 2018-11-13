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


import com.newIns.model.NiFixedAwardStatistics;

import com.newIns.service.NiFixedAwardStatisticsService;


import com.newIns.tools.ExcelExportUtil;


/**
 * @author lj
 * @Description : 定奖统计的Controller
 * @time : 2016年8月11日 下午4:50:46
 */

@Controller
@RequestMapping("/platform")
public class NiFixedAwardStatisticsController {
	
	//日志
	private static Logger log=Logger.getLogger(NiFixedAwardStatisticsController.class);
	
	@Resource
	private NiFixedAwardStatisticsService niFixedAwardStatisticsService;


	/**
	 * @author lj
	 * @Description : 返回按条件查询的投放统计列表
	 * @time : 2016年8月11日 下午4:52:02
	 * @return ModelAndView
	 */
	@RequestMapping("niFixedAwardStatisticsList.do")
	public ModelAndView fixedAwardStatisticsList(HttpServletRequest request,HttpServletResponse response){
		
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		log.info("========================>>分页查询定奖统计信息");
		//接受参数
		String fixedAwardDetail=request.getParameter("fixedAwardDetail");
		String fixedAwardContent=request.getParameter("fixedAwardContent");
		String deliveryStatus=request.getParameter("deliveryStatus");
		//定奖统计搜索条件[投放id&问卷名称]
		if(fixedAwardDetail!=null && !("".equals(fixedAwardDetail)) && fixedAwardContent!=null && !("".equals(fixedAwardContent))){
			if(fixedAwardDetail.trim().equals("0")){
				//如果用户选择投放id为搜索条件
				
				hashMap.put("deliveryId", Integer.valueOf(fixedAwardContent.trim()));
			}else if(fixedAwardDetail.trim().equals("1")){
				//如果用户选择问卷名称为搜索条件
				hashMap.put("qnName", fixedAwardContent.trim());
			}
		}

		
		//定奖统计搜索条件[投放状态]
		if(deliveryStatus!=null && !("".equals(deliveryStatus))){
			hashMap.put("status", Integer.valueOf(deliveryStatus));
		}
		
		List<NiFixedAwardStatistics> niFixedAwardStatisticsList=niFixedAwardStatisticsService.getFixedAwardStatisticsList(hashMap);
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("manager/NiFixedAwardStatisticsList");
		modelAndView.addObject("niFixedAwardStatisticsList", niFixedAwardStatisticsList);
		
		
		

		
		return modelAndView;
	}
	
	

	/**
	 * @author lj
	 * @Description : 导出表格
	 * @time : 2016年8月11日 下午7:00:32
	 * @param request
	 * @param response
	 */
	@RequestMapping("exportFixedAwardStatisticsSheet.do")
	public void exportSheet(HttpServletRequest request,HttpServletResponse response){
		
		String deliveryId=request.getParameter("deliveryId");
		String channel=request.getParameter("channel");
		String type=request.getParameter("type");
		List<NiFixedAwardStatistics> niDeliveryStatisticsList=niFixedAwardStatisticsService.exportFixedAwardStatisticsList(deliveryId, channel, type);
		//List<NiDeliveryStatisticsCodingConvert> niDeliveryStatisticsCodingConvertsList=niDeliveryStatisticsService.codingConvert(niDeliveryStatisticsList);
		List<NiFixedAwardStatistics> newNiFixedAwardStatisticsList=niFixedAwardStatisticsService.codingConvert(niDeliveryStatisticsList);
		LinkedHashMap<String, String> fieldMap=niFixedAwardStatisticsService.getExportFixedAwardStatisticsColumn();
		
		//导出
		log.info("========================>>开始将定奖统计列表导出到Excel");

		try {
			
			ExcelExportUtil.listToExcel(newNiFixedAwardStatisticsList, fieldMap, "定奖统计", response);
			log.info("========================>>定奖统计列表导出Excel成功");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("========================>>定奖统计列表导出Excel失败");
			
		}
		
		
	}
	
	

}
