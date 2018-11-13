package com.newIns.dao.assess;

import java.util.List;

import com.newIns.model.assess.NiAssessQuestion;

public interface NiAssessQuestionMapper {
	
	List<NiAssessQuestion> selectByAssessQuestionnaireId(Integer id);

    int deleteByPrimaryKey(Integer aqid);

    int insert(NiAssessQuestion record);

    int insertSelective(NiAssessQuestion record);

    NiAssessQuestion selectByPrimaryKey(Integer aqid);

    int updateByPrimaryKeySelective(NiAssessQuestion record);

    int updateByPrimaryKey(NiAssessQuestion record);
}