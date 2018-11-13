package com.newIns.dao.assess;

import com.newIns.model.assess.NiAssessAnswer;
import com.newIns.model.assess.NiAssessAnswerExample;
import com.newIns.model.assess.NiAssessAnswerKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface NiAssessAnswerMapper {
    int countByExample(NiAssessAnswerExample example);

    int deleteByExample(NiAssessAnswerExample example);

    int deleteByPrimaryKey(NiAssessAnswerKey key);

    int insert(NiAssessAnswer record);

    int insertSelective(NiAssessAnswer record);

    List<NiAssessAnswer> selectByExample(NiAssessAnswerExample example);

    NiAssessAnswer selectByPrimaryKey(NiAssessAnswerKey key);

    int updateByExampleSelective(@Param("record") NiAssessAnswer record, @Param("example") NiAssessAnswerExample example);

    int updateByExample(@Param("record") NiAssessAnswer record, @Param("example") NiAssessAnswerExample example);

    int updateByPrimaryKeySelective(NiAssessAnswer record);

    int updateByPrimaryKey(NiAssessAnswer record);
}