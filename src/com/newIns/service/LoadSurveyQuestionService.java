/**
 * 
 */
package com.newIns.service;

import java.util.List;

import com.newIns.model.survery.SurveyQuestionWithOption;

/**@Description  加载问题完整信息service
 * @author Guan
 * @time 2016年6月29日 下午4:54:56
 */

public interface LoadSurveyQuestionService {
	/**
	 * @Title: loadSurveyQuestionWithOption  
	 * @Author: Guan
	 * @Description: 根据问卷Id将所有问题的完整信息（包含选项）加载出来
	 * @param sqnId
	 * @return List<SurveyQuestionWithOption>
	 * @Time 2016年6月30日 下午3:30:31
	 */
	List<SurveyQuestionWithOption> loadSurveyQuestionWithOption(int sqnId);
	
}
