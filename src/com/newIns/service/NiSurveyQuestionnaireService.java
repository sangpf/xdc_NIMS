package com.newIns.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.model.page.Page;
import com.newIns.model.survery.NiSurveyQuestionnaire;
import com.newIns.tools.AjaxResult;

public interface NiSurveyQuestionnaireService {

	void niSurveyQuestionnaireContent(HttpServletRequest request,Model model);

	Object loadSurveyQuestionnaire(int sqnId);
	
	AjaxResult updateNiSurveyQuestionnaire_edit(HttpServletRequest request,
			HttpServletResponse response,MultipartFile file,MultipartFile[] titlePic_arr) throws Exception;
	/**
	 * 查询一个问卷
	 */
	void findOneNiSurveyQuestionnaire(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception ;

	public AjaxResult editSurveyQuestionnaireSave(HttpServletRequest httpServletRequest);

	public AjaxResult lookNiSurveyQuestionnaire(HttpServletRequest request);
	//存储过程 未被添加到投放的问卷
	List<NiSurveyQuestionnaire> select_niSurveyQue_Dev();
	
	public List<NiSurveyQuestionnaire> selectByNameLevelSubject( Page page);
	
    int insert(NiSurveyQuestionnaire record);
    
    NiSurveyQuestionnaire selectByPrimaryKey(Integer sqnid);
    
    int updateByPrimaryKeySelective(NiSurveyQuestionnaire record);
    
	//读取调查问卷
	AjaxResult readinvestigationExcel(String fileName) throws Exception;
	
	//批量发布问卷
    int updateStateByids(HashMap<String, Object> retMap);
    
    //批量撤销问卷
    int revokeStateByids(HashMap<String, Object> retMap);

	AjaxResult saveNiSurveyQuestionnaire(MultipartFile file, MultipartFile pic,
			HttpServletRequest request);

	void listNiSurveyQuestionnaire(HttpServletRequest request, Model model);

	AjaxResult deleteSurveyQuestionnaire(HttpServletRequest request);

}
