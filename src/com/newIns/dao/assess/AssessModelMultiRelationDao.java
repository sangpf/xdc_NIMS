package com.newIns.dao.assess;

import java.util.List;

import com.newIns.model.assess.AssessModelMultiRelation;
import com.newIns.model.assess.AssessResult;

public interface AssessModelMultiRelationDao {
	
	int insert(AssessModelMultiRelation recode);

	List<AssessResult> selectAssessResult(Integer aqnId);
	
}
