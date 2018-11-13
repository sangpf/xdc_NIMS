package com.newIns.dao.vote;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newIns.model.vote.NiVoteQuestionnaire;
import com.newIns.model.vote.NiVoteQuestionnaireExample;
import com.newIns.model.vote.NiVoteQuestionnaireVO;

public interface NiVoteQuestionnaireMapper {
	
	//根据问卷id 名称查询问卷
	List<NiVoteQuestionnaire> selectByVoteQueName(String voteName);
	
	//查询所有定稿投票问卷
	List<NiVoteQuestionnaire> findVoteQueByStat2();
	
	//根据投放id查询投票问卷信息
	NiVoteQuestionnaire findVoteQueByDelId(Integer id);
	
	//投放添加 问卷筛选
	List<NiVoteQuestionnaire> select_niVote_Dev();
	
    int countByExample(NiVoteQuestionnaireExample example);

    int deleteByExample(NiVoteQuestionnaireExample example);

    int deleteByPrimaryKey(Integer vqnid);
    
    int deleteStateByids(HashMap<String, Object> retMap);

    int insert(NiVoteQuestionnaire record);

    int insertSelective(NiVoteQuestionnaire record);

    List<NiVoteQuestionnaire> selectByExample(NiVoteQuestionnaireExample example);

    NiVoteQuestionnaire selectByPrimaryKey(Integer vqnid);

    int updateByExampleSelective(@Param("record") NiVoteQuestionnaire record, @Param("example") NiVoteQuestionnaireExample example);

    int updateByExample(@Param("record") NiVoteQuestionnaire record, @Param("example") NiVoteQuestionnaireExample example);

    int updateByPrimaryKeySelective(NiVoteQuestionnaire record);

    int updateByPrimaryKey(NiVoteQuestionnaire record);
    
    //列表查询
    List<NiVoteQuestionnaireVO> selectList(HashMap<String, Object> hashMap);
    
	//批量修改投票问卷
    int updateNiVoteQuestionnaire(HashMap<String, Object> retMap);
    
    
    
}