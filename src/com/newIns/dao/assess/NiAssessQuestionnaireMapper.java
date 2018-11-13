package com.newIns.dao.assess;


import java.util.HashMap;
import java.util.List;

import com.newIns.model.assess.NiAssessQuestionnaire;
import com.newIns.model.assess.NiAssessQuestionnaireVO;

public interface NiAssessQuestionnaireMapper {
	
	//根据测评问卷名称查询问卷
	List<NiAssessQuestionnaire> selectByAssessName(String assName);
	
	//根据投放id 查询测评问卷信息
	NiAssessQuestionnaire findAssQue_byDelId(Integer id);
	
	//查询所有定稿的测评问卷
	List<NiAssessQuestionnaire> findNiAssQueByStat2();
	
	//投放添加 问卷筛选
	List<NiAssessQuestionnaire> select_niAssQue_Dev();
	

    int deleteByPrimaryKey(Integer aqnid);

    int insertSelective(NiAssessQuestionnaire record);

    int updateByPrimaryKeySelective(NiAssessQuestionnaire record);

    int updateByPrimaryKey(NiAssessQuestionnaire record);
    
    int insert(NiAssessQuestionnaire record);
    
    NiAssessQuestionnaire selectByPrimaryKey(Integer sqnid);
    
    //分页查询
    List<NiAssessQuestionnaireVO> selectList(HashMap<String, Object> hashMap);
    
	//读取调查问卷
	HashMap readinvestigationExcel(String fileName) throws Exception;
	
	//批量发布问卷
    int releaseNiAssessQuestionnaire(HashMap<String, Object> retMap);
    
    //批量撤销问卷
    int revokeStateByids(HashMap<String, Object> retMap);
    
	void delete_AssessQuestionnaireByids(HashMap<String, Object> hashMap);

	void delete_QuestionByIds(HashMap<String, Object> hashMap);
    
}