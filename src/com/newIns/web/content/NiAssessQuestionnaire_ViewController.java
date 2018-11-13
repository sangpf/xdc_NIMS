package com.newIns.web.content;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newIns.service.NiAssessQuestionnaireService;

/**
 * 预览问卷专用
 * @author 11409
 *
 */
@RequestMapping("/queView")
@Controller
public class NiAssessQuestionnaire_ViewController {

	private static Logger log = Logger.getLogger(NiAssessQuestionnaire_ViewController.class); 
	@Resource
	private NiAssessQuestionnaireService assessQuestionnaireService;
	
	/**
	 * @Title: LoadAssessQuestionnaire
	 * @Author: Guan
	 * @Description: 最新预览测评问卷接口
	 * @param aqnId
	 * @return SurveyQuestionnaire
	 * @Time 2016年6月30日 下午3:32:22
	 */
	@RequestMapping(value = "/loadAqn.do", method = RequestMethod.GET)
	@ResponseBody
	public Object LoadAssessQuestionnaire(int aqnId) {
		log.info("this is controller: LoadAssessQuestionnaire()");
		return assessQuestionnaireService.loadAssessQuestionnaire(aqnId);
	}
	
	/**
	 * 预览问卷跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niAssesQue_View_Jump.do")
	public String niAssesQue_View_Jump(HttpServletRequest request,Model model){
		
		assessQuestionnaireService.niAssesQue_View_Jump(request, model);
		
//		return "manager/content/assess_content";
		return "manager/content/Assess/assess_content";
	}
	
	
}
