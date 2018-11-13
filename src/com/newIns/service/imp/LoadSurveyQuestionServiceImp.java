/**
 * 
 */
package com.newIns.service.imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newIns.dao.survey.SurveyQuestionDao;
import com.newIns.model.survery.SurveyQuestionWithOption;
import com.newIns.service.LoadSurveyQuestionService;

/**
 * @Description 加载问题完整信息service
 * @author Guan
 * @time 2016年6月29日 下午4:55:46
 */
@Service(value = "LoadSurveyQuestionService")
public class LoadSurveyQuestionServiceImp implements LoadSurveyQuestionService {
	@Autowired
	private SurveyQuestionDao svyQuestionDao;
	private static Logger log = Logger
			.getLogger(LoadSurveyQuestionServiceImp.class);
	public List<SurveyQuestionWithOption> loadSurveyQuestionWithOption(int sqnId) {
		log.info("This is service:loadSurveyQuestionWithOption()");
		return svyQuestionDao.loadSurveyQuestionWithOption(sqnId);

	}
}
