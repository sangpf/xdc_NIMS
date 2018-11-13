package com.newIns.dao.assess;

import java.util.List;
import java.util.Map;

import com.newIns.model.assess.AssessResult;
import com.newIns.model.assess.NiAssessModelSimple;
import com.newIns.model.assess.NiAssessModelSimpleKey;

public interface NiAssessModelSimpleMapper {

    int insert(NiAssessModelSimple record);

    int insertSelective(NiAssessModelSimple record);
    
    //根据测评问卷id 查询简单测评模型表
    List<NiAssessModelSimple> selectByAqnId(Integer aqnId);

    NiAssessModelSimple selectByPrimaryKey(NiAssessModelSimpleKey key);

    int updateByPrimaryKeySelective(NiAssessModelSimple record);

    int updateByPrimaryKey(NiAssessModelSimple record);

	List<AssessResult> selectAssessResult(Integer aqnId);

	List<NiAssessModelSimple> selectDetailResult(Map<String, Object> dataMap);

	void updateDetailResult(Map<String, Object> dataMap);

	void update_AssessResult(AssessResult assessResult);
}