package com.newIns.web.content;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.model.survery.NiSurveyDeliveryWanxVO;
import com.newIns.model.survery.NiSurveyQuestionnaire;
import com.newIns.service.NiSqnclassDictService;
import com.newIns.service.NiSurveyQuestionnaireService;
import com.newIns.service.SurveyDeliveryService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.StrUtils;

/**
 * 调查问卷管理
 * @author Sang
 *
 */
@Controller
@RequestMapping("/platform")
public class NiSurveyQuestionnaireController {
	private static Logger log = Logger.getLogger(NiSurveyQuestionnaireController.class); 
	@Resource
	private NiSurveyQuestionnaireService niSurveyQuestionnaireService;
	@Resource
	private NiSqnclassDictService niSqnclassDictService;
	@Resource
	private SurveyDeliveryService niSurveyDeliveryWanxService;
	
	/**
	 * 预览调查问卷
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niSurveyQuestionnaireView.do")
	public String niAssesQue_View(HttpServletRequest request,Model model){
		String sqnId = request.getParameter("sqnId");
		
		model.addAttribute("sqnId", sqnId);
		
		return "manager/content/survey/NiSurveyQuestionnaire_View";
	}
	
	/**
	 * 打开问卷 添加页面
	 * @return
	 */
	@RequestMapping("/NiSurveyQuestionnaireAdd.do")
	public String addNiSurveyQuestionnaire(){
		
		return "manager/content/survey/add_SurveyQuestionnaire";
	}
	
	/**
	 * 预览问卷
	 * @param request
	 * @return
	 */
	@RequestMapping("/lookNiSurveyQuestionnaire.do")
	public String lookNiSurveyQuestionnaire(HttpServletRequest request,Model model){
		String sqnId = request.getParameter("sqnId");
		String imgurl = request.getParameter("imgurl");
		
		Integer sqn_Id = null;
		if(StrUtils.isNotEmpty(sqnId)){
			sqn_Id = Integer.valueOf(sqnId);
		}
		if(sqn_Id!=null){
			
			NiSurveyQuestionnaire niSurveyQuestionnaire = niSurveyQuestionnaireService.selectByPrimaryKey(sqn_Id);
			model.addAttribute("nsq", niSurveyQuestionnaire);
		}
		model.addAttribute("imgurl", imgurl);
		
		return "manager/content/survey/LookniSurveyQuestionnaire";
	}
	
	/**
	 *  上传 调查问卷
	 */
	@RequestMapping("/save_surveyQuestionnaire.do")
	@ResponseBody
	public AjaxResult save_surveyQuestionnaire(
			@RequestParam("surveyQuestion_file") MultipartFile file,
			@RequestParam("surveyQuestion_pic") MultipartFile pic,
			HttpServletRequest request){
		
		return niSurveyQuestionnaireService.saveNiSurveyQuestionnaire(file,pic, request);
	}

	/**
	 * 列表查询
	 */
	@RequestMapping("/NiSurveyQuestionnaireList.do")
	public String listNiSurveyQuestionnaire(HttpServletRequest request,Model model) throws UnsupportedEncodingException{
		
		niSurveyQuestionnaireService.listNiSurveyQuestionnaire(request,model);
		
		return "manager/content/survey/list_NiSurveyQuestionnaire";
	}
	
	/**
	 * 查询一个问卷
	 * @return
	 */
	@RequestMapping("/findOneNiSurveyQuestionnaire.do")
	public String findOneNiSurveyQuestionnaire(HttpServletRequest request,HttpServletResponse response,Model model){
		
		try {
			niSurveyQuestionnaireService.findOneNiSurveyQuestionnaire(request, response,model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "manager/content/survey/niSurveyQuestionnaire_edit";
	}
	
	/**
	 * 保存编辑后的问卷
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping("/editSurveyQuestionnaireSave.do")
	@ResponseBody
	public AjaxResult editSurveyQuestionnaireSave(HttpServletRequest httpServletRequest){
		
		AjaxResult editSurveyQuestionnaireSave = niSurveyQuestionnaireService.editSurveyQuestionnaireSave(httpServletRequest);
		return  editSurveyQuestionnaireSave;
	}
	
	/**
	 * 恢复问卷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/regenerationSurveyQuestionnaire.do")
	@ResponseBody
	public AjaxResult regenerationSurveyQuestionnaire(HttpServletRequest request,HttpServletResponse response){
		AjaxResult ajaxResult = new AjaxResult();
		String sqnids = request.getParameter("sqnid");
		String[] sqnid = sqnids.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> sqnIds = new ArrayList<Integer>();
		for(int i=0;i<sqnid.length;i++){
			sqnIds.add(Integer.valueOf(sqnid[i]));
		}
		hashMap.put("state", 1);
		hashMap.put("sqnids", sqnIds);
		int updateByPrimaryKeySelective = 0;
		try {
			updateByPrimaryKeySelective = niSurveyQuestionnaireService.updateStateByids(hashMap);
			if(updateByPrimaryKeySelective>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "恢复问卷成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.put("success", false);
			ajaxResult.put("msg", "恢复问卷失败");
		}
		return ajaxResult;
	}
	
	/**
	 * 修改问卷   2 - 1
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/changeSurveyQuestionnaire.do")
	@ResponseBody
	public AjaxResult changeSurveyQuestionnaire(HttpServletRequest request,HttpServletResponse response){
		AjaxResult ajaxResult = new AjaxResult();
		String sqnids = request.getParameter("sqnid");
		String[] sqnid = sqnids.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> sqnIds = new ArrayList<Integer>();
		for(int i=0;i<sqnid.length;i++){
			Integer sqn_id = null;
			if(StrUtils.isNotEmpty(sqnid[i])){
				sqn_id = Integer.valueOf(sqnid[i]);
			}
			//根据问卷id查询投放管理表信息，获取投放的状态
			List<NiSurveyDeliveryWanxVO> niSurveyDeliveryWanxVOList = niSurveyDeliveryWanxService.selectListByqnId(sqn_id);
			if(niSurveyDeliveryWanxVOList!=null && niSurveyDeliveryWanxVOList.size()>0){
				for(int y =0; y<niSurveyDeliveryWanxVOList.size();y++){
					NiSurveyDeliveryWanxVO niSurveyDeliveryWanxVO = niSurveyDeliveryWanxVOList.get(y);
					Integer status = niSurveyDeliveryWanxVO.getStatus();  //该问卷的投放状态
					if(status!=null){
						if(status == 2 || status == 4 || status == 5 || status == 6){
							return AjaxResult.errorResult("问卷id为:"+sqn_id+"已经在投放管理中发布,或已经完成,不能修改");
						}
					}
				}
			}

			sqnIds.add(sqn_id);
		}
		hashMap.put("state", 1);
		hashMap.put("sqnids", sqnIds);
		int updateByPrimaryKeySelective = 0;
		try {
			updateByPrimaryKeySelective = niSurveyQuestionnaireService.updateStateByids(hashMap);
			if(updateByPrimaryKeySelective>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("修改失败");
		}
		return ajaxResult;
	}
	
	
	/**
	 * 修改问卷内容信息
	 * @return
	 */
	@RequestMapping("/updateNiSurveyQuestionnaire_edit.do")
	@ResponseBody
	public AjaxResult updateNiSurveyQuestionnaire_edit(@RequestParam("uploadImgmes") MultipartFile file,
			@RequestParam("titleImg") MultipartFile[] titlePic_arr,
			HttpServletRequest request,HttpServletResponse response){
		AjaxResult updateNiSurveyQuestionnaire_edit = null;
		try {
			updateNiSurveyQuestionnaire_edit = 
					niSurveyQuestionnaireService.updateNiSurveyQuestionnaire_edit(request, response,file,titlePic_arr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateNiSurveyQuestionnaire_edit;
	}
	
	/**
	 * 批量发布问卷
	 * @return
	 */
	@RequestMapping("/updateNiSurveyQuestionnaire.do")
	@ResponseBody
	public AjaxResult updateNiSurveyQuestionnaire(HttpServletRequest request,HttpServletResponse response){
		AjaxResult ajaxResult = new AjaxResult();
		String sqnids = request.getParameter("sqnid");
		String[] sqnid = sqnids.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> sqnIds = new ArrayList<Integer>();
		for(int i=0;i<sqnid.length;i++){
			sqnIds.add(Integer.valueOf(sqnid[i]));
		}
		hashMap.put("state", 2);
		hashMap.put("sqnids", sqnIds);
		int updateByPrimaryKeySelective = 0;
		try {
			updateByPrimaryKeySelective = niSurveyQuestionnaireService.updateStateByids(hashMap);
			if(updateByPrimaryKeySelective>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "发布成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.put("success", false);
			ajaxResult.put("msg", "发布失败");
		}
		return ajaxResult;
	}
	
	/**
	 * 废弃问卷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/abandonedSurveyQuestionnaire.do")
	@ResponseBody
	public AjaxResult revokeSurveyQuestionnaire(HttpServletRequest request,HttpServletResponse response){
		AjaxResult ajaxResult = new AjaxResult();
		String parameter = request.getParameter("sqnid");
		String[] sqnid = parameter.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> sqnIds = new ArrayList<Integer>();
		for(int i=0;i<sqnid.length;i++){
			sqnIds.add(Integer.valueOf(sqnid[i]));
		}
		hashMap.put("state", 3);
		hashMap.put("sqnids", sqnIds);
		int updateByPrimaryKeySelective = 0;
		try {
			updateByPrimaryKeySelective = niSurveyQuestionnaireService.revokeStateByids(hashMap);
			log.info("===============================>>批量废弃问卷");
			if(updateByPrimaryKeySelective>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "废弃成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("废弃失败");
		}
		
		return ajaxResult;
	}
	
	/**
	 * 批量删除问卷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteSurveyQuestionnaire.do")
	@ResponseBody
	public AjaxResult deleteSurveyQuestionnaire(HttpServletRequest request,HttpServletResponse response){
		
		return niSurveyQuestionnaireService.deleteSurveyQuestionnaire(request);
		
	}
	
}
