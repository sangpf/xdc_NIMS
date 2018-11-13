package com.newIns.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.newIns.model.QuestionnaireStatisticsCube;


public interface ExportQtnStatisticsCubeService {
	List<QuestionnaireStatisticsCube> getQuestionnaireStatisticsCubeList(int deliveryId,int type,int channel);
	LinkedHashMap<String, String> getDownLoadColumnName();
}
