package com.newIns.web;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.newIns.model.NiWanxPointStatistics;
import com.newIns.service.NiWanxPointStatisticsService;
import com.newIns.tools.ExcelExportUtil;

/**
 * @author lj
 * @Description : 玩校积分统计的Controller
 * @time : 2016年8月26日 下午3:58:08
 */
@Controller
@RequestMapping("/platform")
public class NiWanxPointStatisticsController {

	//日志
	private static Logger log=Logger.getLogger(NiWanxPointStatisticsController.class);
	
	@Resource
	private NiWanxPointStatisticsService niWanxPointStatisticsService;
	

	/**
	 * @author lj
	 * @Description : 得到玩校积分统计的列表
	 * @time : 2016年8月26日 下午4:31:49
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("niWanxPointStatisticsList.do")
	public ModelAndView getWanxPointStatisticsList(HttpServletRequest request,HttpServletResponse response){
		log.info("========================>>分页查询玩校积分统计信息");
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		
		String deliveryChannel=request.getParameter("deliveryChannel");
		String reservation=request.getParameter("reservation");
		if(deliveryChannel != null && !deliveryChannel.trim().equals("")){
			hashMap.put("qnChannel", deliveryChannel);
		}
		//处理日期[将MM/dd/yyyy转为yyyy-MM-dd]
		String startTime="";
		String endTime="";
		if(reservation != null && !reservation.trim().equals("")){
			String[] split = reservation.split("-");
			startTime=split[0];
			endTime=split[1];
		}
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String startTime1="";
		String endTime1="";
		if(startTime != null && !startTime.trim().equals("")){
			Date date1=new Date(startTime);
			Date date2=new Date(endTime);
			startTime1=dateFormat.format(date1);
			endTime1=dateFormat.format(date2);
		}
		if(startTime != null && !startTime.trim().equals("")){
			hashMap.put("starTime", startTime1);
			hashMap.put("endTime", endTime1);
		}
		List<NiWanxPointStatistics> niWanxPointStatisticsList=niWanxPointStatisticsService.getWanxPointStatisticsList(hashMap);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("manager/NiWanxPointStatisticsList");
		modelAndView.addObject("niWanxPointStatisticsList", niWanxPointStatisticsList);
		return modelAndView;
	}
	

	/**
	 * @author lj
	 * @Description : 导出玩校积分统计表格
	 * @time : 2016年8月26日 下午9:01:15
	 * @param request
	 * @param response
	 */
	@RequestMapping("exportWanxPointStatisticsSheet.do")
	public void exportPointSheet(HttpServletRequest request,HttpServletResponse response){
		String dateStr=request.getParameter("date");
		
		List<NiWanxPointStatistics> exportWanxPointStatisticsList = niWanxPointStatisticsService.getExportWanxPointStatisticsList(dateStr);
		//list编码转换
		List<NiWanxPointStatistics> resultNiWanxPointStatisticsList=niWanxPointStatisticsService.codingConvertWanxPointStatisticsList(exportWanxPointStatisticsList);
		//导出
		log.info("========================>>开始将玩校积分统计列表导出到Excel");
		LinkedHashMap<String, String> fieldMap=niWanxPointStatisticsService.getExcelColumnName();
		try {
			
			ExcelExportUtil.listToExcel(resultNiWanxPointStatisticsList, fieldMap, "玩校积分统计", response);
			log.info("========================>>玩校积分统计列表导出Excel成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("========================>>玩校积分统计列表导出Excel失败");
		}
		
	}
	
}
