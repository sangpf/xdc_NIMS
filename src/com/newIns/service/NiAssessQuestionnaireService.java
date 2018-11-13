package com.newIns.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.model.assess.AssessQuestionnaire;
import com.newIns.model.assess.NiAssessQuestionnaire;
import com.newIns.tools.AjaxResult;

public interface NiAssessQuestionnaireService {
	
	NiAssessQuestionnaire getOne_key(HttpServletRequest request);
	
	/**
	 * 预览问卷跳转
	 * @return
	 */
	void niAssesQue_View_Jump(HttpServletRequest request,Model model);
	/**
	 * @Title: loadAssessQuestionnaire  
	 * @Author: Guan
	 * @Description: 预览测评问卷 
	 */
	Object loadAssessQuestionnaire(int aqnId);
	
	/**
	 * 保存编辑后的问卷信息
	 */
	AjaxResult updateNiAssessQuestionnaire_edit(@RequestParam("uploadImgmes") MultipartFile file,MultipartFile[] titlePic_arr,
			HttpServletRequest request ) throws Exception;
	/**
	 * 编辑问卷内容前查询问卷,问题内容
	 * @return
	 */
	void findOneNiAssessQuestionnaire(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,Model model);

	/**
	 * 保存编辑后的问卷
	 */
	public AjaxResult editSurveyQuestionnaireSave(HttpServletRequest httpServletRequest);
	
	//投放添加 问卷筛选
	List<NiAssessQuestionnaire> select_niAssQue_Dev();
	
    int updateByPrimaryKeySelective(NiAssessQuestionnaire record);
    
    int insert(NiAssessQuestionnaire record);
    
    NiAssessQuestionnaire selectByPrimaryKey(Integer sqnid);

	//读取测评问卷,返回测评问卷的id
	AjaxResult readinvestigationExcel(String fileName) throws Exception;
	
	//批量发布问卷
    int releaseNiAssessQuestionnaire(HashMap<String, Object> retMap);
    
    //批量撤销问卷
    int revokeStateByids(HashMap<String, Object> retMap);
    
    //页面录入保存 测评问卷信息
    AjaxResult savePageInputniAssessQuestionnaire(HttpServletRequest request,
			MultipartFile picFile);
    
	AjaxResult deleteSurveyQuestionnaire(HttpServletRequest request);
	
	void editAssessResult(HttpServletRequest httpServletRequest, Model model);
	
	void editAssessResult_list(HttpServletRequest httpServletRequest,
			Model model);
	AjaxResult saveAssessResult(HttpServletRequest request, Model model);
	void EditAssessResult_Section(HttpServletRequest request, Model model);
	AjaxResult save_AssessResult_Section(HttpServletRequest request, Model model);
	
	AjaxResult NiAssessQuestionnaireSave(HttpServletRequest request,
			MultipartFile file, MultipartFile pic);
	void list_AssessQuestionnaire(HttpServletRequest request, Model model);
    
    
}
