
package com.newIns.service.imp;

import javax.annotation.Resource;

import org.apache.ibatis.executor.Executor;
import org.apache.tools.ant.taskdefs.Execute;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newIns.dao.BotAdDao;
import com.newIns.dao.survey.NiSurveyQuestionnaireMapper;
import com.newIns.model.BotAdvert;
import com.newIns.model.survery.NiSurveyQuestionnaire;
import com.newIns.service.BotAdService;

@Service
public class BotAdServiceImp implements BotAdService{

	@Resource
	private BotAdDao botAdDao;
	@Resource
	private NiSurveyQuestionnaireMapper niSurveyQuestionnaireMapper;

	@Override
	public BotAdvert getBotAd(int id) {
		BotAdvert botAd = botAdDao.getBotAd(id);
		return botAd;
	}

	@Transactional
	public void testTran() {
		
		NiSurveyQuestionnaire niSurveyQuestionnaire = new NiSurveyQuestionnaire();
		niSurveyQuestionnaire.setSqnid(1);
		niSurveyQuestionnaire.setSqnname("勇敢的心");
		niSurveyQuestionnaireMapper.updateByPrimaryKeySelective(niSurveyQuestionnaire);
		
		int n = 1/0;
		
		niSurveyQuestionnaire.setSqnid(1621280503);
		niSurveyQuestionnaire.setSqnname("八戒的回答");
		niSurveyQuestionnaireMapper.updateByPrimaryKeySelective(niSurveyQuestionnaire);
		
	}

}
