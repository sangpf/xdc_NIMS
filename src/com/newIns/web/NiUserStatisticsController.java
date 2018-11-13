package com.newIns.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lj
 * @Description : 用户统计的Controller
 * @time : 2016年8月15日 下午5:18:29
 */
@Controller
@RequestMapping("/platform")
public class NiUserStatisticsController {

	@RequestMapping("niUserStatisticsList.do")
	public String getNiUserStatisticsList(){
		return "manager/NiUserStatisticsList";
	}
}
