package com.newIns.service.imp;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newIns.dao.LoadVqnMapper;
import com.newIns.model.VqnItem;
import com.newIns.model.VqnOption;
import com.newIns.model.vote.VoteQuestionnaire;
import com.newIns.service.LoadVqnService;

/**@Description  
 * @author MaNia_chAng
 * @time 2016年6月28日 下午5:09:53
 */
@Service
public class LoadVqnServiceImp implements LoadVqnService{
	@Autowired
	private LoadVqnMapper loadVqnMapper;
	@Autowired
	private VqnItem vqnItem;
	
	@Autowired
	private VoteQuestionnaire niVoteQuestionnaire;
	
	public VqnItem loadVqn(int vqnId){
		//NiVoteQuestionnaire niVoteQuestionnaire=new NiVoteQuestionnaire();
		niVoteQuestionnaire = loadVqnMapper.loadVqn(vqnId);//投票问题表里所有的信息
		vqnItem.setQuestionQty(1);
		vqnItem.setComment(niVoteQuestionnaire.getComment());
		vqnItem.setCorrectAnswer(niVoteQuestionnaire.getCorrectAnswer());
		vqnItem.setEpilogue(niVoteQuestionnaire.getEpilogue());
		vqnItem.setIsSelfDefine(niVoteQuestionnaire.getIsSelfDefine());
		vqnItem.setOptionNum(niVoteQuestionnaire.getOptionNum());
		vqnItem.setPerface(niVoteQuestionnaire.getPerface());
		vqnItem.setPublisherName(niVoteQuestionnaire.getPublisherName());
		vqnItem.setQuestionType(niVoteQuestionnaire.getQuestionType());
		vqnItem.setRequired(niVoteQuestionnaire.getRequired());
		vqnItem.setVqnClassName(niVoteQuestionnaire.getVqnClassName());
		vqnItem.setVqnSummary(niVoteQuestionnaire.getVqnSummary());
		vqnItem.setVqTitle(niVoteQuestionnaire.getVqTitle());
		vqnItem.setVqnName(niVoteQuestionnaire.getVqnName());
		vqnItem.setSuccess("true");
		vqnItem.setPicPath(niVoteQuestionnaire.getPicPath());
		
		int optionNum = vqnItem.getOptionNum();

		List<VqnOption> voteOptionList = new ArrayList<VqnOption>();
		
		for(int i=0;i<optionNum;i++){
			
			VqnOption vqnOpitonItem = new VqnOption();
			vqnOpitonItem.setOptionOrder(i+1);
			vqnOpitonItem.setOptionDes(niVoteQuestionnaire.getOptionDesByIndex(i+1));
			vqnOpitonItem.setOptionFeedback(niVoteQuestionnaire.getOptionFeedbackByIndex(i+1));
			voteOptionList.add(vqnOpitonItem);
		}
		
		vqnItem.setOptions(voteOptionList);
		return vqnItem;
	}

}
