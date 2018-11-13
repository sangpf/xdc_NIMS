package com.newIns.dao.assess;

import java.util.List;
import java.util.Map;

import com.newIns.model.assess.AssessResult;
import com.newIns.model.assess.Assess_model_multi_combination;
import com.newIns.model.assess.Assess_model_multi_combination_relation;

public interface Assess_model_multi_combination_Dao {
	
    int insert(Assess_model_multi_combination record);
	
    int insert_Assess_model_multi_combination_relation(Assess_model_multi_combination_relation record);
    
    List<AssessResult> selectAssessResult(Integer aqnId); 
    
    void udpateDetailRelationResult(Map<String, Object> dataMap);
    void updateDetailRelationResult_assessResult(AssessResult assessResult);
    
    List<Assess_model_multi_combination_relation> getAssess_model_multi_combination_relation_list(Map<String,Object> dataMap);
    
}
