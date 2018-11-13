/**
 * 
 */
package com.newIns.dao.survey;

import com.newIns.model.survery.SurveyQuestionnaire;

/**@Description  调查问卷Dao 问卷预览
 * @author Guan
 * @time 2016年6月28日 下午10:30:30
 */

public interface SurveyQuestionnaireDao {
	SurveyQuestionnaire loadSurveyQuestionnaire(int sqnId);
}
