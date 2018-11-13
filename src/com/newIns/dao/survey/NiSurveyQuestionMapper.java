package com.newIns.dao.survey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newIns.model.survery.NiSurveyQuestion;
import com.newIns.model.survery.NiSurveyQuestionExample;

public interface NiSurveyQuestionMapper {
    int countByExample(NiSurveyQuestionExample example);

    int deleteByExample(NiSurveyQuestionExample example);

    int deleteByPrimaryKey(Integer sqid);

    int insert(NiSurveyQuestion record);

    int insertSelective(NiSurveyQuestion record);

    List<NiSurveyQuestion> selectByExample(NiSurveyQuestionExample example);

    NiSurveyQuestion selectByPrimaryKey(Integer sqid);

    int updateByExampleSelective(@Param("record") NiSurveyQuestion record, @Param("example") NiSurveyQuestionExample example);

    int updateByExample(@Param("record") NiSurveyQuestion record, @Param("example") NiSurveyQuestionExample example);

    int updateByPrimaryKeySelective(NiSurveyQuestion record);

    int updateByPrimaryKey(NiSurveyQuestion record);
    
    //根据问题id查询
    List<NiSurveyQuestion> selectSurveyQuestion(Integer id);
    
    List<NiSurveyQuestion> selectBySurveyQuestionnaireId(Integer id);
}