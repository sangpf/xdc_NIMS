package com.newIns.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.model.NiAdStatistics;
import com.newIns.service.NiAdStatisticsService;

/**
 * @Description 广告统计Controller
 * @time 2016年7月27日 上午11:30:20
 * @author lj
 *
 */
@Controller
@RequestMapping("/platform")
public class NiAdStatisticsController {
	
	private static Logger logger=Logger.getLogger(NiAdStatisticsController.class);
	
	@Resource
	private NiAdStatisticsService niAdStatisticsService;
	
	/**
	 * @Description 返回广告统计列表
	 * @author lj
	 * @return ModelAndView
	 * @time 2016年7月27日 下午6:00:20
	 */
	@RequestMapping("niAdStatisticsList.do")
	public ModelAndView niAdStatisticsList(HttpServletRequest request,HttpServletResponse response,Model model){
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		logger.info("========================>>分页查询广告统计信息");;
		//从后台管理的前端页面获取传入的参数
		String adDetail=request.getParameter("adDetail");
		String adContent=request.getParameter("adContent");
		String adChannel=request.getParameter("adChannel");
		String adStatus=request.getParameter("adStatus");
		
		//广告统计搜索条件[广告id&广告名称]
		if(adDetail!=null && !("".equals(adDetail)) && adContent!=null && !("".equals(adContent))){
			if(adDetail.trim().equals("0")){
				//如果用户选择为广告id位搜索条件
				hashMap.put("adId", adContent.trim());
			}else if(adDetail.trim().equals("1")){
				//如果用户选择广告名称为搜索条件
				hashMap.put("adTitle", adContent.trim());
			}
		}
		
		//广告搜索条件[广告渠道(产品)]
		if(adChannel!=null && !("".equals(adChannel))){
			hashMap.put("adChannel", Integer.valueOf(adChannel));
		}
		
		//广告搜索条件[广告状态]
		if(adStatus!=null && !("".equals(adStatus))){
			hashMap.put("adStatus", Integer.valueOf(adStatus));
		}
		
		List<NiAdStatistics> niAdStatisticsList=niAdStatisticsService.selectList(hashMap);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("manager/NiAdStatisticsList");
		modelAndView.addObject("niAdStatisticsList", niAdStatisticsList);
		
		return modelAndView;
	}
}
