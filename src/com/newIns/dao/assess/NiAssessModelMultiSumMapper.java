package com.newIns.dao.assess;

import java.util.List;
import java.util.Map;

import com.newIns.model.assess.AssessModelMultiRelation;
import com.newIns.model.assess.AssessResult;
import com.newIns.model.assess.NiAssessModelMultiSum;

public interface NiAssessModelMultiSumMapper {
	
	//保存多维度测评模型
	int insert(NiAssessModelMultiSum recode);
	
	//根据测评问卷id 查询多条信息
	List<NiAssessModelMultiSum> findByAqnId(Integer id);

	//根据问卷id 纬度id 编辑
	void updateByPrimaryKeySelective(NiAssessModelMultiSum niAssessModelMultiSum);

	List<AssessResult> selectAssessResult(Integer aqnId);

	List<NiAssessModelMultiSum> selectDetailResult(Map<String, Object> dataMap);
	
	List<AssessModelMultiRelation> selectDetailRelationResult(
			Map<String, Object> dataMap);

	void updateDetailResult(Map<String, Object> dataMap);

	void updateDetailRelationResult(Map<String, Object> dataMap);

	void update_AssessResult(AssessResult assessResult);

	void update_AssessResult_Relation(AssessResult assessResult);
	
	
}
