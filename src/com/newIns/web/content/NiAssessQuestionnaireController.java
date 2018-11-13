package com.newIns.web.content;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.dao.NiAqnclassDictMapper;
import com.newIns.model.assess.NiAssessDeliveryWanxVo;
import com.newIns.model.assess.NiAssessQuestionnaire;
import com.newIns.service.AssessDeliveryService;
import com.newIns.service.NiAssessQuestionnaireService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.FileUtil;
import com.newIns.tools.QueTool;
import com.newIns.tools.StrUtils;
import com.newIns.tools.fileUtils.ExportTextUtil;
import com.newIns.tools.fileUtils.Excel_FileUtil;

/**
 * 测评问卷管理
 * @author Sang
 *
 */
@Controller
@RequestMapping("/platform")
public class NiAssessQuestionnaireController {
	private static Logger log = Logger.getLogger(NiAssessQuestionnaireController.class); 
	@Resource
	private NiAssessQuestionnaireService assessQuestionnaireService;
	@Resource
	private NiAqnclassDictMapper niAqnclassDictMapper;
	@Resource
	private AssessDeliveryService niAssessDeliveryWanxService;
	
	@RequestMapping("/NiAssessQuestionnaire_download_qn.do")
	public void export_excelFile(HttpServletResponse response,HttpServletRequest request){
		
		NiAssessQuestionnaire assessQuestionnaire = assessQuestionnaireService.getOne_key(request);
		
		String filepath = assessQuestionnaire.getFilepath();
		
		if(StrUtils.isEmpty(filepath)){
						
			try {
				response.getWriter().write ("<script languge=javascript>alert('sorry,no data!')</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			
			File file = new File(filepath);
			String file_name = file.getName();
			
			String suffix = file_name.substring(file_name.lastIndexOf("."), file_name.length());
			
			FileInputStream fileInputStream = null;
			try {
				fileInputStream = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			byte[] bys = new byte[1024];  
	        int len = 0;  
			
	    	//设置响应的字符集
	        response.setCharacterEncoding("utf-8");
	        //设置响应内容的类型
	        response.setContentType("text/plain");
	        //设置文件的名称和格式
	        response.addHeader(
	                "Content-Disposition",
	                "attachment; filename="
	                        + Excel_FileUtil.genAttachmentFileName(file_name, "JSON_FOR_UCC_")
	                        + MessageFormat.format("{0,date,yyyy-MM-dd HH:mm:ss}", new Object[]{Calendar.getInstance().getTime()})
	                        + suffix);//通过后缀可以下载不同的文件格式
			
			ServletOutputStream outputStream = null;
    		BufferedOutputStream bufferedOutputStream = null;
			try {
				outputStream = response.getOutputStream();
				bufferedOutputStream = new BufferedOutputStream(outputStream);
				
	 			while((len = fileInputStream.read(bys)) != -1){
					
	 				bufferedOutputStream.write(bys, 0, len);
				}
				bufferedOutputStream.flush();
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				if(outputStream != null){
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(bufferedOutputStream != null){
					try {
						bufferedOutputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
	}
	
    /**
     * 导出文件文件
     * 用于UCC配置，将有效的数转换成JSON字符串，然后导出文本文件
     * @throws Exception
     */
//    @RequestMapping("/NiAssessQuestionnaire_download_qn.do")
    public void exportText(HttpServletResponse response){
        //获取有效的数据
        String list = (String) "i am godtrue 我最帅";//伪代码
        //将集合转换成字符串
//        String jsonString = JSON.toJSONString(list);
        ExportTextUtil.writeToTxt(response, "i am godtrue 我最帅","excel模版文件");
    }
	
	/**
	 * 页面录入保存测评问卷
	 * @param request
	 * @param picFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/savePageInputniAssessQuestionnaire.do")
	public AjaxResult savePageInputniAssessQuestionnaire(HttpServletRequest request,@RequestParam() MultipartFile picFile){
		AjaxResult result = assessQuestionnaireService.savePageInputniAssessQuestionnaire(request,picFile);
		return result;
	}
	
	/**
	 * 跳转到页面录入
	 * @return
	 */
	@RequestMapping("/pageInputniAssessQuestionnaire.do")
	public String pageInputniAssessQuestionnaire(){
		return "manager/content/Assess/pageInput";
	}
	
	/**
	 * 保存编辑后的问卷信息
	 */
	@ResponseBody
	@RequestMapping("/updateNiAssessQuestionnaire_edit.do")
	public AjaxResult updateNiAssessQuestionnaire_edit(@RequestParam("uploadImgmes") MultipartFile file,
			@RequestParam("titleImg") MultipartFile[] titlePic_arr, HttpServletRequest request){
		
		AjaxResult updateNiAssessQuestionnaire_edit = null;
		try {
			updateNiAssessQuestionnaire_edit = assessQuestionnaireService.updateNiAssessQuestionnaire_edit(file, titlePic_arr,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return updateNiAssessQuestionnaire_edit;
	}
	
	/**
	 * 预览测评问卷   旧
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niAssesQue_View.do")
	public String niAssesQue_View(HttpServletRequest request,Model model){
		String aqnId = request.getParameter("aqnId");
		
		model.addAttribute("aqnId", aqnId);
		
		return "manager/content/Assess/NiAssessQuestionnaire_View";
	}
	
	/**
	 * 保存问卷图片路径
	 * @param request
	 * @return
	 */
	@RequestMapping(value="niAssesQueSavePicUrl.do",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult niAssesQueSavePicUrl(HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
		String aqnId = request.getParameter("aqnId");
		String jdbcUrl = request.getParameter("jdbcUrl");
		NiAssessQuestionnaire niAssessQuestionnaire = new NiAssessQuestionnaire();
		Integer sqn_Id = null;
		if(StrUtils.isNotEmpty(aqnId)){
			sqn_Id = Integer.valueOf(aqnId);
		}
		niAssessQuestionnaire.setAqnid(sqn_Id);
		niAssessQuestionnaire.setPicpath(jdbcUrl);
		
		try {
			int updateByPrimaryKeySelective = assessQuestionnaireService.updateByPrimaryKeySelective(niAssessQuestionnaire);
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
	 * 上传图片
	 * @param request
	 * @param file
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value="niAssesQueUploadPic.do",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult niAssesQueUploadPic(HttpServletRequest request,@RequestParam("uploadImgmes") MultipartFile file) throws IllegalStateException, IOException{
		AjaxResult ajaxResult = new AjaxResult();
		//拼接保存路径
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String formatDate = sdf.format(new Date());
		String aqnId = request.getParameter("aqnId");
		if(StrUtils.isEmpty(aqnId)){
			return AjaxResult.errorResult("未选择问卷,请重新选择!");
		}
		Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/qn/sqn/"+formatDate+"/"+aqnId);
		
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
	 * 列表查询 测评问卷
	 */
	@RequestMapping("/niAssessQuestionnaireList.do")
	public String niAssessQuestionnaireList(HttpServletRequest request,Model model){
		
		assessQuestionnaireService.list_AssessQuestionnaire(request,model);
		
		return "manager/content/Assess/niAssessQuestionnaireList";
	}
	
	/**
	 * 添加测评问卷  跳转页面
	 */
	@RequestMapping("/NiAssessQuestionnaireAdd.do")
	public String NiAssessQuestionnaireAdd(HttpServletRequest request,
			HttpServletResponse response,Model model){
		
		return "manager/content/Assess/NiAssessQuestionnaireAdd";
	}
	
	/**
	 * 保存测评问卷  非 压缩包
	 */
	@RequestMapping("/uploadNiAssessQuestionnaire.do")
	@ResponseBody
	@Transactional
	public AjaxResult uploadNiAssessQuestionnaire(
			HttpServletRequest request,
			@RequestParam("assessQuestion_file") MultipartFile file, 
			@RequestParam("assessQuestion_pic") MultipartFile pic){
		
		return assessQuestionnaireService.NiAssessQuestionnaireSave(request, file,pic);
	}
	
	/**
	 * 保存测评问卷
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/NiAssessQuestionnaireSave_bak.do")
	@ResponseBody
	public AjaxResult NiAssessQuestionnaireSave_bak(HttpServletRequest request,
			@RequestParam("exampleInputFile") MultipartFile file,
			HttpServletResponse response,Model model) throws IllegalStateException, IOException{
		AjaxResult ajaxResult = new AjaxResult();
		String fileDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "/txt/aqn/"+fileDate+"/");
		
		String realPath = (String) uploadFile.get("realPath");
		String md5ByFile = uploadFile.get("md5ByFile").toString();
		
		//查询该md5值是否在集合中存在
		HashSet<String> quemd5Set = QueTool.getQuemd5Set();
		
		Iterator<String> iterator = quemd5Set.iterator();
		while(iterator.hasNext()){
			String next = iterator.next();
			if(next.trim().equalsIgnoreCase(md5ByFile.trim())){
				System.out.println("======================>>该问卷的md5值已经在集合中存在,所以该问卷可能已经上传!");
				return AjaxResult.errorResult("该问卷已经上传,请勿重复添加!");
			}
		}
		
		//将该问卷的md5值存入set中   
		quemd5Set.add(md5ByFile);
		
			//读取测评问卷，保存到数据库
			try {
				//拿到测评问卷的id
				ajaxResult = assessQuestionnaireService.readinvestigationExcel(realPath);
				
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("问卷录入有误,请检查");
			}
			
		return ajaxResult;
	}
	
	/**
	 * 编辑问卷内容前查询问卷,问题内容
	 * @return
	 */
	@RequestMapping("/editNiAssessQuestionnaire.do")
	public String editNiAssessQuestionnaire(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,Model model){
		
		assessQuestionnaireService.findOneNiAssessQuestionnaire(httpServletRequest, httpServletResponse,model);
		
		return "manager/content/Assess/niAssessQuestionnaire_edit";
	}
	
	// 保存 测评结果编辑 
	@ResponseBody
	@RequestMapping("/saveAssessResult.do")
	public AjaxResult saveAssessResult(HttpServletRequest request,Model model){
		return assessQuestionnaireService.saveAssessResult(request,model);
	}
	
	// 跳转到测评结果 区间编辑
	@RequestMapping("/EditAssessResult_Section.do")
	public String EditAssessResult_Section(HttpServletRequest request,Model model){
		assessQuestionnaireService.EditAssessResult_Section(request,model);
		return "manager/content/Assess/EditAssessResult_Section";
	}
	
	// 保存测评结果
	@ResponseBody
	@RequestMapping("/save_AssessResult_Section.do")
	public AjaxResult save_AssessResult_Section(HttpServletRequest request,Model model){
		return assessQuestionnaireService.save_AssessResult_Section(request,model);
	}
	
	/**
	 * 编辑测评 结果 
	 */
	@RequestMapping("/editAssessResult.do")
	public String editAssessResult(HttpServletRequest request,HttpServletResponse response,Model model){
		
		assessQuestionnaireService.editAssessResult(request,model);
		
		// 根据结果页显示样式 跳转到不同的页面
		String resutShowType_str = request.getParameter("resutShowType");
		Integer resutShowType = StrUtils.changeToInt(resutShowType_str);
		
		if(resutShowType == 2){
			return "manager/content/Assess/EditAssessResult_2";
		}

		return "manager/content/Assess/EditAssessResult";
	}
	
	/**
	 * 编辑测评 结果  列表页
	 */
	@RequestMapping("/editAssessResult_list.do")
	public String editAssessResult_list(HttpServletRequest httpServletRequest,Model model){
		assessQuestionnaireService.editAssessResult_list(httpServletRequest,model);
		return "manager/content/Assess/EditAssessResult_List";
	}
	
	/**
	 * 修改问卷 2-1
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/changeNiAssessQuestionnaire.do")
	public AjaxResult changeNiAssessQuestionnaire(HttpServletRequest request,HttpServletResponse response,Model model){
		AjaxResult ajaxResult = new AjaxResult();
		String sqnids = request.getParameter("sqnid");
		ArrayList<Integer> aqnids = new ArrayList<Integer>();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if(sqnids!=null && !sqnids.trim().equals("")){
			String[] split = sqnids.split("!");
			for(int i=0;i< split.length ;i++){
				Integer aqnId = null;
				if(StrUtils.isNotEmpty(split[i])){
					aqnId = Integer.valueOf(split[i]);
				}
				//根据测评问卷id查询投放管理
				if(aqnId!=null){
					List<NiAssessDeliveryWanxVo> selectByQnid = niAssessDeliveryWanxService.selectByQnid(aqnId);
					if(selectByQnid!=null && selectByQnid.size()>0){
						for(int y=0;y<selectByQnid.size();y++){
							NiAssessDeliveryWanxVo niAssessDeliveryWanxVo = selectByQnid.get(y);
							Integer status = niAssessDeliveryWanxVo.getStatus();
							if(status!=null){
								if(status == 2 || status == 4 || status == 5 || status == 6){
									return AjaxResult.errorResult("问卷id:"+aqnId+"已经被投放,或者完成");
								}
							}
						}
					}
				}
				
				aqnids.add(aqnId);
			}
			hashMap.put("statu", 1);
			hashMap.put("aqnids", aqnids);
			try {
				int count = assessQuestionnaireService.releaseNiAssessQuestionnaire(hashMap);
				if(count>0){
					log.info("=========================>>批量操作测评问卷状态成功");
					ajaxResult.put("success", true);
					ajaxResult.put("msg", "操作成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("=========================>>批量操作测评问卷状态失败");
			}
		}
		return ajaxResult;
	}
	
	
	
	/**
	 * 保存编辑后的问卷
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping("/editNiAssessQuestionnaireSave.do")
	@ResponseBody
	public AjaxResult editNiAssessQuestionnaireSave(HttpServletRequest httpServletRequest){
		AjaxResult editSurveyQuestionnaireSave = assessQuestionnaireService.editSurveyQuestionnaireSave(httpServletRequest);
		return  editSurveyQuestionnaireSave;
	}
	
	/**
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/releaseNiAssessQuestionnaire.do")
	public AjaxResult releaseNiAssessQuestionnaire(HttpServletRequest request,HttpServletResponse response,Model model){
		AjaxResult ajaxResult = new AjaxResult();
		String assess = request.getParameter("assess");
		log.info("========================>>该问卷现在的状态"+assess);
		String sqnids = request.getParameter("sqnid");
		ArrayList<Integer> aqnids = new ArrayList<Integer>();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if(sqnids!=null && !sqnids.trim().equals("")){
			String[] split = sqnids.split("!");
			for(int i=0;i< split.length ;i++){
				aqnids.add(Integer.valueOf(split[i]));
			}
			hashMap.put("aqnids", aqnids);
			if(assess!=null){
				if(assess.trim().equals("1")){  //发布
					hashMap.put("statu", 2);
				}else if(assess.trim().equals("2")){  //废弃
					hashMap.put("statu", 3);
				}else if(assess.trim().equals("3")){  //恢复
					hashMap.put("statu", 1);
				}
				try {
					int count = assessQuestionnaireService.releaseNiAssessQuestionnaire(hashMap);
					if(count>0){
						log.info("=========================>>批量操作测评问卷状态成功");
						ajaxResult.put("success", true);
						ajaxResult.put("msg", "操作成功");
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.info("=========================>>批量操作测评问卷状态失败");
				}
			}
		}
		return ajaxResult;
	}
	
	/**
	 * 批量删除问卷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteNiAssessQuestionnaire.do")
	@ResponseBody
	public AjaxResult deleteSurveyQuestionnaire(HttpServletRequest request,HttpServletResponse response){
		
		return assessQuestionnaireService.deleteSurveyQuestionnaire(request);
		
	}
	
}
