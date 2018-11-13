package com.newIns.dao.survey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.newIns.model.survery.NiSurveyQuestionnaire;
import com.newIns.model.survery.NiSurveyQuestionnaireVO;

public interface NiSurveyQuestionnaireMapper {
	
	//根据问卷名称 查询问卷信息
	List<NiSurveyQuestionnaire> selectBySurveyQuestionnaireName(String qnName);
	
	NiSurveyQuestionnaire findSurQueBy_DelId(Integer id);
	
	//存储过程 未被添加到投放的问卷
	List<NiSurveyQuestionnaire> select_niSurveyQue_Dev();
	//查询所有定稿状态的调查问卷
	List<NiSurveyQuestionnaire> findSurQueByStau2();
	
	List<NiSurveyQuestionnaire> selectByNameLevelSubject(Map<String, Object> map);
	
    int deleteByPrimaryKey(Integer sqnid);
    
    int insert(NiSurveyQuestionnaire record);

    int insertSelective(NiSurveyQuestionnaire record);

    NiSurveyQuestionnaire selectByPrimaryKey(Integer sqnid);
    
    List<NiSurveyQuestionnaire> getPage(RowBounds rowBounds);
    
    List<NiSurveyQuestionnaireVO> selectList(HashMap<String, Object> hashMap);

    int updateByPrimaryKeySelective(NiSurveyQuestionnaire record);

    int updateByPrimaryKey(NiSurveyQuestionnaire record);
    
    //批量发布
    int updateStateByids(HashMap<String, Object> retMap);
    
    //批量撤销
    int revokeStateByids(HashMap<String, Object> retMap);

	void delete_Questionnaire_ByIdS_list(HashMap<String, Object> hashMap);

	void delete_Question_ByIdS_list(HashMap<String, Object> hashMap);
    
    
    
}