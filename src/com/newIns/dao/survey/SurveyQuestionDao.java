/**
 * 
 */
package com.newIns.dao.survey;

import java.util.List;

import com.newIns.model.survery.SurveyQuestionWithOption;

/**@Description  加载问题完整信息Dao
 * @author Guan
 * @time 2016年6月29日 下午4:20:55
 */

public interface SurveyQuestionDao {

	List<SurveyQuestionWithOption> loadSurveyQuestionWithOption(int sqnId);
	
}
