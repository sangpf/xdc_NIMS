package com.newIns.dao.assess;

import java.util.List;
import java.util.Map;

import com.newIns.model.assess.AssessModelMBTI;
import com.newIns.model.assess.AssessModelMbtiCombination;
import com.newIns.model.assess.AssessResult;

public interface AssessModelMBTIDao {
	
	void insert(AssessModelMBTI assessModelMBTI);

	//添加 维度组合
	void insertMbtiCombination(
			AssessModelMbtiCombination assessModelMbtiCombination);
	
	List<AssessResult> selectAssessResult(Integer aqnId);

	List<AssessModelMbtiCombination> selectDetailRelationResult(
			Map<String, Object> dataMap);

	void udpateDetailRelationResult(Map<String, Object> dataMap);

	void update_AssessResult(AssessResult assessResult);
	
}
