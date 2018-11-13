package com.newIns.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newIns.model.BotAdvert;
import com.newIns.service.BotAdService;
import com.newIns.service.NiSurveyQuestionnaireService;

/**@Description  底部广告的controller,相应的Url为/wanx
 * @author MaNia_chAng
 * @time 2016年5月23日 下午2:27:34
 */

@Controller
@RequestMapping("/platform")
public class BotAdController {  
	private static Logger log = Logger.getLogger(BotAdController.class); 
	@Resource
	private BotAdService botAdService;
	@Resource
	private NiSurveyQuestionnaireService niSurveyQuestionnaireService;
	
	/**
	 * 该功能为随便创建的，仅供参考
	 * @return
	 */
	@RequestMapping(value = "/botAd.do")
	public String getBotAd(){
		BotAdvert botAd = botAdService.getBotAd(1);
		log.info("========================>>返回信息"+botAd.toString());
		return "manager/test";
	}  
	
	@RequestMapping(value = "/botAd1.do")
	public void getBotAd1(){
		
		botAdService.testTran();

	}

}
