package com.newIns.web.content;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newIns.service.NiSqnclassDictService;
import com.newIns.service.SurveyDeliveryService;
import com.newIns.service.NiSurveyQuestionnaireService;


@Controller
@RequestMapping("/queView")
public class NiSurveyQuestionnaire_ViewController {
	
	@Resource
	private NiSurveyQuestionnaireService niSurveyQuestionnaireService;
	@Resource
	private NiSqnclassDictService niSqnclassDictService;
	@Resource
	private SurveyDeliveryService niSurveyDeliveryWanxService;
	
	/**
	 * 最新调查问问卷预览接口
	 */
	@RequestMapping(value = "/loadSqn.do", method = RequestMethod.GET)
	@ResponseBody
	public Object LoadSurveyQuestionnaire(HttpServletRequest request) {
			String sqnId = request.getParameter("sqnId").trim();
			int sqn_Id = Integer.parseInt(sqnId);
			return niSurveyQuestionnaireService.loadSurveyQuestionnaire(sqn_Id);
	}
	
	/**
	 * 预览  新  
	 */
	@RequestMapping("/niSurveyQuestionnaireContent.do")
	public String niSurveyQuestionnaireContent(HttpServletRequest request,Model model){

		niSurveyQuestionnaireService.niSurveyQuestionnaireContent(request, model);
		
		return "manager/content/survey/survey_content";
	}
	

	
}
