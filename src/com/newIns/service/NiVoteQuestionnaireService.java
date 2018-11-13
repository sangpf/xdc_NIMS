package com.newIns.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.model.vote.NiVoteQuestionnaire;
import com.newIns.model.vote.NiVoteQuestionnaireVO;
import com.newIns.tools.AjaxResult;

public interface NiVoteQuestionnaireService {
	/**
	 * 新预览  跳转
	 */
	void niVoteQue_View_jump(HttpServletRequest request,Model model);
	/**
	 * 保存编辑后的投票问卷
	 * @return
	 */
	AjaxResult updateNiVoteQuestionnaire_edit(@RequestParam("uploadImgmes") MultipartFile file,
			@RequestParam() MultipartFile[] optPics,MultipartFile titleImg,
			HttpServletRequest request,HttpServletResponse response) throws Exception;
	/**
	 * 查询问卷信息
	 * @return
	 */
	void findOneNiVoteQuestionnaire(HttpServletRequest request,Model model);
	
	/**
	 * 保存编辑后的问卷
	 */
	public AjaxResult editNiVoteQuestionnaireSave(HttpServletRequest httpServletRequest);
	//投放添加 问卷筛选
	List<NiVoteQuestionnaire> select_niVote_Dev();
	
    int updateByPrimaryKeySelective(NiVoteQuestionnaire record);
	
    NiVoteQuestionnaire selectByPrimaryKey(Integer vqnid);

    //列表查询
    List<NiVoteQuestionnaireVO> selectList(HashMap<String, Object> hashMap);
    
	//批量发布问卷
    int releaseNiVoteQuestionnaire(HashMap<String, Object> retMap);
    
    //批量撤销问卷
    int revokeNiVoteQuestionnaire(HashMap<String, Object> retMap);
    
    //批量删除
    int deleteNiVoteQuestionnaire(HashMap<String, Object> retMap);
    
	AjaxResult NiVoteQuestionnaireSave(HttpServletRequest request,
			MultipartFile file, MultipartFile pic);
}
