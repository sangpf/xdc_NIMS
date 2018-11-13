package com.newIns.dao;

import java.util.List;
import java.util.Map;

import com.newIns.model.QuestionnaireStatisticsBaseCube;

public interface ExportQtnStatisticsCubeMapper {
	//打开事件
	List<QuestionnaireStatisticsBaseCube> selectSurveyOpenStatisticsCube(int deliveryId);
	List<QuestionnaireStatisticsBaseCube> selectAssessOpenStatisticsCube(int deliveryId);
	List<QuestionnaireStatisticsBaseCube> selectVoteOpenStatisticsCube(int deliveryId);
	//点击事件
	List<QuestionnaireStatisticsBaseCube> selectSurveyTapStatisticsCube(int deliveryId);
	List<QuestionnaireStatisticsBaseCube> selectAssessTapStatisticsCube(int deliveryId);
	List<QuestionnaireStatisticsBaseCube> selectVoteTapStatisticsCube(int deliveryId);
}
