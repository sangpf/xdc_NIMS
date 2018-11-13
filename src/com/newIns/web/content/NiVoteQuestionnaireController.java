package com.newIns.web.content;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.model.NiVqnclassDict;
import com.newIns.model.VqnItem;
import com.newIns.model.vote.NiVoteQuestionnaire;
import com.newIns.model.vote.NiVoteQuestionnaireVO;
import com.newIns.model.vote.VoteDelivery;
import com.newIns.service.LoadVqnService;
import com.newIns.service.VoteDeliveryService;
import com.newIns.service.NiVoteQuestionnaireService;
import com.newIns.service.NiVqnclassDictService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.FileUtil;
import com.newIns.tools.StrUtils;

@Controller
@RequestMapping("/platform")
public class NiVoteQuestionnaireController {
	private static Logger log = Logger.getLogger(NiVoteQuestionnaireController.class); 
	@Resource
	private NiVoteQuestionnaireService niVoteQuestionnaireService;
	@Resource
	private NiVqnclassDictService niVqnclassDictService;
	@Resource
	private VoteDeliveryService niVoteDeliveryWanxService;
	@Autowired
	private LoadVqnService loadVqnService;
	@Autowired
	private VqnItem vqnItem;
	
	/**
	 * 保存编辑后的投票问卷
	 * @return
	 */
	@RequestMapping("/updateNiVoteQuestionnaire_edit.do")
	@ResponseBody
	public AjaxResult updateNiVoteQuestionnaire_edit(@RequestParam("uploadImgmes") MultipartFile file,
			@RequestParam("optionPic") MultipartFile[] optPics,@RequestParam("titleImg") MultipartFile titleImg,
			HttpServletRequest request,HttpServletResponse response){
		
		AjaxResult updateNiVoteQuestionnaire_edit = null;
		
		try {
			updateNiVoteQuestionnaire_edit = 
					niVoteQuestionnaireService.updateNiVoteQuestionnaire_edit(file,optPics,titleImg, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateNiVoteQuestionnaire_edit;
		
	}
	
	/**
	 * 预览投票问卷   旧
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niVoteQue_View.do")
	public String niVoteQue_View(HttpServletRequest request,Model model){
		String vqnId = request.getParameter("vqnId");
		model.addAttribute("vqnId", vqnId);
		return "manager/content/vote/NiVoteQuestionnaire_View";
	}
	
	/**
	 * 保存编辑后的问卷
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping("/editNiVoteQuestionnaireSave.do")
	@ResponseBody
	public AjaxResult editNiVoteQuestionnaireSave(HttpServletRequest httpServletRequest){
		
		AjaxResult editSurveyQuestionnaireSave = niVoteQuestionnaireService.editNiVoteQuestionnaireSave(httpServletRequest);
		return  editSurveyQuestionnaireSave;
	}
	
	/**
	 * 查询问卷信息
	 * @return
	 */
	@RequestMapping("/findOneNiVoteQuestionnaire.do")
	public String findOneNiVoteQuestionnaire(HttpServletRequest request,Model model){
		
		niVoteQuestionnaireService.findOneNiVoteQuestionnaire(request, model);
		
		return "manager/content/vote/niVoteQuestionnaire_edit";
	}
	
	/**
	 * 保存问卷图片路径
	 * @param request
	 * @return
	 */
	@RequestMapping(value="niVoteQueSavePicUrl.do",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult niVoteQueSavePicUrl(HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
		String vqnId = request.getParameter("vqnId");
		String jdbcUrl = request.getParameter("jdbcUrl");
		NiVoteQuestionnaire niVoteQuestionnaire = new NiVoteQuestionnaire();
		Integer sqn_Id = null;
		if(StrUtils.isNotEmpty(vqnId)){
			sqn_Id = Integer.valueOf(vqnId);
		}
		niVoteQuestionnaire.setVqnid(sqn_Id);
		niVoteQuestionnaire.setPicpath(jdbcUrl);
		
		try {
			int updateByPrimaryKeySelective = niVoteQuestionnaireService.updateByPrimaryKeySelective(niVoteQuestionnaire);
			if(updateByPrimaryKeySelective>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "图片保存成功!");
			}else{
				return AjaxResult.errorResult("保存图片失败,不过问卷已经上传成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("保存图片失败,不过问卷已经上传成功!");
		}
		
		return ajaxResult;
	}
	/**
	 * 添加问卷后上传图片
	 * @param request
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value="/niVoteQueUploadPic.do",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult niVoteQueUploadPic(HttpServletRequest request,
			@RequestParam("uploadImgmes") MultipartFile file) throws IllegalStateException, IOException{
		AjaxResult ajaxResult = new AjaxResult();
		//拼接保存路径
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String formatDate = sdf.format(new Date());
		String vqnId = request.getParameter("vqnId");
		if(StrUtils.isEmpty(vqnId)){
			return AjaxResult.errorResult("未选择问卷,请重新选择!");
		}
		Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/qn/sqn/"+formatDate+"/"+vqnId);
		
		String realPath = (String) uploadFile.get("realPath");
		log.info("=========================>>realPath:"+realPath);
		String url = (String) uploadFile.get("url");
		String contextPath = request.getContextPath();
		ajaxResult.put("url", contextPath+url);
		log.info("===================>> url 路径为 :" + contextPath+url);
		
		//获取文件的存储路径
		String jdbcUrl = (String) uploadFile.get("jdbcUrl");
		log.info("===================>> 文件的存储路径 :" + jdbcUrl);
		ajaxResult.put("jdbcUrl", jdbcUrl);
		
		return ajaxResult;
	}
	
	/**
	 * 保存投票问卷
	 */
	@RequestMapping("/NiVoteQuestionnaireSave.do")
	@ResponseBody
	public AjaxResult NiVoteQuestionnaireSave(HttpServletRequest request,
			@RequestParam("viteQuestion_file") MultipartFile file,
			@RequestParam("viteQuestion_pic") MultipartFile pic
			){
		
		return niVoteQuestionnaireService.NiVoteQuestionnaireSave(request, file,pic);
	}
	
	/**
	 *  列表查询
	 * @return
	 */
	@RequestMapping("/NiVoteQuestionnaireList.do")
	public ModelAndView NiVoteQuestionnaireList(HttpServletRequest request,HttpServletResponse response,Model model) 
				throws UnsupportedEncodingException{
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		log.info("========================>>分页查询投票问卷信息");
		String sqnpublish = request.getParameter("sqnpublish");  //查询类别
		String sqnNameOrpublisherName = request.getParameter("sqnNameOrpublisherName");
		String qnclassid = request.getParameter("qnclassid");
		String reservation = request.getParameter("reservation");
		String surQueryStats = request.getParameter("surQueryStats");
		
		String starTime = "";
		String endTime  = "";
		if(reservation!=null && !reservation.trim().equals("")){
			String[] split = reservation.split("-");
			starTime = split[0];
			endTime  = split[1];
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String starTime1 = "";
		String endTime1 = "";
		if(starTime!=null && !starTime.trim().equals("")){
			Date date = new Date(starTime);
			Date date2 = new Date(endTime);
			starTime1 = format.format(date);
			endTime1 = format.format(date2);
		}

		if(StrUtils.isNotEmpty(sqnpublish) && StrUtils.isNotEmpty(sqnNameOrpublisherName)){
			//判断选择的是文件名称，还是调查机构名称
			if(sqnpublish.trim().equals("0")){
				//问卷名称
				hashMap.put("sqnname", sqnNameOrpublisherName.trim());
			}else if(sqnpublish.trim().equals("1")){
				//调查机构名称
				hashMap.put("publishername", sqnNameOrpublisherName.trim());
			}else if(sqnpublish.trim().equals("2")){
				hashMap.put("vqnId", sqnNameOrpublisherName.trim());
			}
		}

		if(qnclassid!=null && !qnclassid.trim().equals("")){
			//分类
			hashMap.put("qnclassid", Integer.valueOf(qnclassid));
		}
		if(surQueryStats!=null && !surQueryStats.trim().equals("")){
			//问卷状态
			hashMap.put("staus", Integer.valueOf(surQueryStats));
		}
		if(starTime!=null && !starTime.trim().equals("")){
			hashMap.put("starTime", starTime1);
			hashMap.put("endTime", endTime1);
		}
		
		//查询问卷分类
		List<NiVqnclassDict> selectList = niVqnclassDictService.selectList();
		
		List<NiVoteQuestionnaireVO> selectList2 = niVoteQuestionnaireService.selectList(hashMap);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/content/vote/NiVoteQuestionnaireList");
		modelAndView.addObject("NiVoteQuestionnaires", selectList2);
		modelAndView.addObject("niVqnclassDict_list", selectList);
		return modelAndView;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping("/NiVoteQuestionnaireAdd.do")
	public String NiVoteQuestionnaireAdd(){
		return "manager/content/vote/add_voteQuestionnaire";
	}
	
	/**
	 * 批量发布问卷
	 * @return
	 */
	@RequestMapping("/publishingVoteQuestionnaire.do")
	@ResponseBody
	public AjaxResult publishingVoteQuestionnaire(HttpServletRequest request,HttpServletResponse response){
		String sqnids = request.getParameter("sqnid");
		String[] sqnid = sqnids.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> sqnIds = new ArrayList<Integer>();
		for(int i=0;i<sqnid.length;i++){
			sqnIds.add(Integer.valueOf(sqnid[i]));
		}
		hashMap.put("state", 2);
		hashMap.put("vqnids", sqnIds);
		int releaseNiVoteQuestionnaire = 0;
		try {
			releaseNiVoteQuestionnaire = niVoteQuestionnaireService.releaseNiVoteQuestionnaire(hashMap);
			log.info("===============================>>批量发布问卷成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(releaseNiVoteQuestionnaire>0){
			log.info("==========================>>添加成功!");
		}
		return AjaxResult.successResult("发布问卷成功!");
	}
	
	/**
	 * 批量修改问卷
	 * @return
	 */
	@RequestMapping("/changeVoteQuestionnaire.do")
	@ResponseBody
	public AjaxResult changeVoteQuestionnaire(HttpServletRequest request,HttpServletResponse response){
		AjaxResult ajaxResult = new AjaxResult();
		String sqnids = request.getParameter("sqnid");
		String[] sqnid = sqnids.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> sqnIds = new ArrayList<Integer>();
		for(int i=0;i<sqnid.length;i++){
			Integer vqnid = null;
			if(StrUtils.isNotEmpty(sqnid[i])){
				vqnid = Integer.valueOf(sqnid[i]);
			}
			if(vqnid!=null){
				//根据id查询投放
				List<VoteDelivery> selectByQnId = niVoteDeliveryWanxService.selectByQnId(vqnid);
				if(selectByQnId!=null && selectByQnId.size()>0){
					for(int y=0;y<selectByQnId.size();y++){
						VoteDelivery niVoteDeliveryWanx = selectByQnId.get(y);
						Integer status = niVoteDeliveryWanx.getStatus();
						if(status!=null){
							if(status == 2 || status == 4 || status == 5 || status ==6){
								return AjaxResult.errorResult("问卷id:"+vqnid+"已经投放或者已经完成,不能修改");
							}
						}
					}
				}
				
			}
			sqnIds.add(vqnid);
		}
		hashMap.put("state", 1);
		hashMap.put("vqnids", sqnIds);
		try {
			int releaseNiVoteQuestionnaire = niVoteQuestionnaireService.releaseNiVoteQuestionnaire(hashMap);
			if(releaseNiVoteQuestionnaire>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "修改问卷成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxResult;
	}
	
	
	/**
	 * 废弃问卷
	 */
	@RequestMapping("/abandonedVoteQuestionnaire.do")
	@ResponseBody
	public AjaxResult abandonedVoteQuestionnaire(HttpServletRequest request,HttpServletResponse response){
		String parameter = request.getParameter("sqnid");
		String[] sqnid = parameter.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> sqnIds = new ArrayList<Integer>();
		for(int i=0;i<sqnid.length;i++){
			sqnIds.add(Integer.valueOf(sqnid[i]));
		}
		hashMap.put("state", 3);
		hashMap.put("vqnids", sqnIds);
		int revokeNiVoteQuestionnaire = 0;
		try {
			revokeNiVoteQuestionnaire = niVoteQuestionnaireService.revokeNiVoteQuestionnaire(hashMap);
			log.info("===============================>>废弃问卷");
			if(revokeNiVoteQuestionnaire>0){
				log.info("===============================>>废弃问卷成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return AjaxResult.successResult("废弃问卷成功");
	}
	
	/**
	 * 恢复问卷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/regenerationVoteQuestionnaire.do")
	@ResponseBody
	public AjaxResult regenerationVoteQuestionnaire(HttpServletRequest request,HttpServletResponse response){
		String parameter = request.getParameter("sqnid");
		String[] sqnid = parameter.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> sqnIds = new ArrayList<Integer>();
		for(int i=0;i<sqnid.length;i++){
			sqnIds.add(Integer.valueOf(sqnid[i]));
		}
		hashMap.put("state", 1);
		hashMap.put("vqnids", sqnIds);
		int revokeNiVoteQuestionnaire = 0;
		try {
			revokeNiVoteQuestionnaire = niVoteQuestionnaireService.revokeNiVoteQuestionnaire(hashMap);
			log.info("===============================>>批量恢复问卷");
			if(revokeNiVoteQuestionnaire>0){
				log.info("===============================>>恢复问卷成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return AjaxResult.successResult("恢复问卷成功");
	}
	
	/**
	 * 批量删除问卷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteNiVoteQuestionnaire.do")
	@ResponseBody
	public AjaxResult deleteNiVoteQuestionnaire(HttpServletRequest request,HttpServletResponse response){
		String parameter = request.getParameter("sqnid");
		String[] sqnid = parameter.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> sqnIds = new ArrayList<Integer>();
		for(int i=0;i<sqnid.length;i++){
			sqnIds.add(Integer.valueOf(sqnid[i]));
		}
		hashMap.put("vqnids", sqnIds);
		int deleteNiVoteQuestionnaire = 0;
		try {
			deleteNiVoteQuestionnaire = niVoteQuestionnaireService.deleteNiVoteQuestionnaire(hashMap);
			
			log.info("===============================>>批量删除问卷");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(deleteNiVoteQuestionnaire>0){
			log.info("===============================>>批量删除问卷成功");
		}
		return AjaxResult.successResult("批量删除投票问卷成功");
	}
	
}
