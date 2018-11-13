package com.newIns.dao.assess;

import java.util.List;
import java.util.Map;

import com.newIns.model.assess.AssessModelBigFive;
import com.newIns.model.assess.AssessResult;

public interface AssessModelBigFiveDao {
	
	void insert(AssessModelBigFive recode);
	
	List<AssessResult> selectAssessResult(Integer aqnId);
	
	List<AssessModelBigFive> selectDetailResult(Map<String, Object> dataMap);

	void updateDetailResult(Map<String, Object> dataMap);

	void update_AssessResult(AssessResult assessResult);
	
}
