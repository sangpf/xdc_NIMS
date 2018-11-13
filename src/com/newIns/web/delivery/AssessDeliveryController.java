package com.newIns.web.delivery;

import java.io.IOException;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.model.assess.AssessDelivery;
import com.newIns.model.assess.NiAssessDeliveryWanxVo;
import com.newIns.model.assess.NiAssessQuestionnaire;
import com.newIns.service.AssessDeliveryService;
import com.newIns.service.NiAssessQuestionnaireService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.FileUtil;
import com.newIns.tools.StrUtils;

/**
 * 测评问卷投放管理
 * 
 */
@Controller
@RequestMapping("/platform")
public class AssessDeliveryController {
	private static Logger log = Logger.getLogger(SurveyDeliveryController.class);
	@Resource
	private AssessDeliveryService niAssessDeliveryWanxService;
	@Resource
	private NiAssessQuestionnaireService niAssessQuestionnaireService;
	
	@ResponseBody
	@RequestMapping("/saveAssessResult_head_tail.do")
	public AjaxResult saveAssessResult_head_tail(HttpServletRequest request,Model model){
		
		return niAssessDeliveryWanxService.saveAssessResult_head_tail(request,model);
	}
	
	// 付费测评保存
	@ResponseBody
	@RequestMapping("/assessDelivery_PaySet_Save.do")
	public AjaxResult assessDelivery_PaySet_Save(HttpServletRequest request,Model model){
		
		return niAssessDeliveryWanxService.assessDelivery_PaySet_Save(request,model);
	}
	
	// 跳转到测评结果页编辑
	@RequestMapping("/assessDelivery_ResultPage.do")
	public String assessDelivery_ResultPage(HttpServletRequest request,Model model){

		niAssessDeliveryWanxService.assessDelivery_ResultPage(request,model);
		
		return "manager/delivery/assess/assessDelivery_resultPage";
	}
	
	// 付费测评页面
	@RequestMapping("/assessDelivery_PaySetPage.do")
	public String assessDelivery_PaySetPage(HttpServletRequest request,Model model){
		
		niAssessDeliveryWanxService.assessDelivery_PaySetPage(request,model);
		return "manager/delivery/assess/assessDelivery_PaySet";
	}
	
	// 保存模版
	@ResponseBody
	@RequestMapping("/saveAssessTemplatePage.do")
	public AjaxResult saveAssessTemplatePage(HttpServletRequest request,Model model){
		return niAssessDeliveryWanxService.saveTemplatePage(request,model);
	}
	
	// 跳转到选择模版页面
	@RequestMapping("/choseAssessTemplatePage.do")
	public String choseAssessTemplatePage(HttpServletRequest request,Model model){
		niAssessDeliveryWanxService.choseTemplatePage(request,model);
		return "manager/delivery/assess/niAssessDeliveryChoseTemplate";
	}
	
	/**
	 * 根据问卷id 或者问卷名称查询测评问卷
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/searchAssByIdOrName.do")
	public String searchAssByIdOrName(HttpServletRequest request,Model model){
		niAssessDeliveryWanxService.searchAssByIdOrName(request, model);
		return "manager/delivery/assess/niAssessDeliveryWanxNew";
	}
	
	/**
	 * 根据真实数据 或者运营数据事实调整显示收集份数
	 * @param request
	 * @return
	 */
	@RequestMapping("/AdjustAssesDeliveryWanxOperate.do")
	@ResponseBody
	public AjaxResult AdjustAssesDeliveryWanxOperate(HttpServletRequest request){
		AjaxResult adjustAssesDeliveryWanxOperate = niAssessDeliveryWanxService.AdjustAssesDeliveryWanxOperate(request);
		return adjustAssesDeliveryWanxOperate;
	}
	
	/**
	 * 请求显示收集份数
	 * @param request
	 * @return
	 */
	@RequestMapping(value="findNiAssShowOrderNum.do",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult findNiAssShowOrderNum(HttpServletRequest request){
		AjaxResult findNiAssShowOrderNum = niAssessDeliveryWanxService.findNiAssShowOrderNum(request);
		return findNiAssShowOrderNum;
	}
	
	/**
	 * 运营调整模态框
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/niAssessDeliveryWanxOperateModal.do",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult niAssessDeliveryWanxOperateModal(HttpServletRequest request){
		AjaxResult niAssessDeliveryWanxOperateModal = niAssessDeliveryWanxService.niAssessDeliveryWanxOperateModal(request);
		return niAssessDeliveryWanxOperateModal;
	}
	
	/**
	 * 保存编辑
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="niAssessDeliveryWanxEditorSave.do",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult niAssessDeliveryWanxEditorSave(HttpServletRequest request,Model model){
		AjaxResult ajaxResult = new AjaxResult();
		try {
			ajaxResult = niAssessDeliveryWanxService.niAssessDeliveryWanxEditorSave(request, model);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("编辑投放信息失败");
		}
		return ajaxResult;
	}
	
	/**
	 * 编辑测评投放  跳转到选择问卷页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niAssessDeliveryWanxQue_Edit.do")
	public String niAssessDeliveryWanxQue_Edit(HttpServletRequest request,Model model){
		niAssessDeliveryWanxService.niAssessDeliveryWanxQue_Edit(request, model);
		return "manager/delivery/assess/niAssessDeliveryWanxNew_Edit";
	}
	
	/**
	 * 编辑页面跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="niAssessDeliveryWanxEditor.do",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult niAssessDeliveryWanxEditor(HttpServletRequest request,Model model){
		AjaxResult ajaxResult = new AjaxResult();
		AssessDelivery niAssessDeliveryWanxEditor = niAssessDeliveryWanxService.niAssessDeliveryWanxEditor(request, model);
		Date btime = null;
		Date etime = null;
		if(niAssessDeliveryWanxEditor!=null){
			btime = niAssessDeliveryWanxEditor.getbTime();
			etime = niAssessDeliveryWanxEditor.geteTime();
			//获取问卷id
			Integer aqnid = niAssessDeliveryWanxEditor.getAqnId();
			if(aqnid!=null){
				//获取问卷名称
				NiAssessQuestionnaire niAssessQuestionnaire = niAssessQuestionnaireService.selectByPrimaryKey(aqnid);
				if(niAssessQuestionnaire!=null){
					ajaxResult.put("naq", niAssessQuestionnaire);
				}
			}
			
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String btimeStr = "";
		if(btime!=null){
			btimeStr = sdf.format(btime);
		}
		String etimeStr = "";
		if(etime!=null){
			etimeStr = sdf.format(etime);
		}
		ajaxResult.put("btimeStr", btimeStr);
		ajaxResult.put("etimeStr", etimeStr);
		ajaxResult.put("sdw", niAssessDeliveryWanxEditor);
		return ajaxResult;
	}
	
	/**
	 * 自动定时任务检测完成
	 * 该接口未被使用
	 * @return
	 */
	@RequestMapping("/niAssessDeliveryWanxTimeTool.do")
	public AjaxResult niAssessDeliveryWanxTimeTool(HttpServletRequest request,Model model){
		AjaxResult niAssessDeliveryWanxTimeTool = niAssessDeliveryWanxService.niAssessDeliveryWanxTimeTool(request,model);
		return niAssessDeliveryWanxTimeTool;
	}
	
	/**
	 * 终止
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niAssessDeliveryWanxOver.do")
	@ResponseBody
	public AjaxResult niAssessDeliveryWanxOver(HttpServletRequest request,Model model){
		AjaxResult niAssessDeliveryWanxOver = niAssessDeliveryWanxService.niAssessDeliveryWanxOver(request, model);
		return niAssessDeliveryWanxOver;
	}
	
	/**
	 * 恢复
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niAssessDeliveryWanxRegeneration.do")
	@ResponseBody
	public AjaxResult niAssessDeliveryWanxRegeneration(HttpServletRequest request,Model model){
		AjaxResult niAssessDeliveryWanxRegeneration = niAssessDeliveryWanxService.niAssessDeliveryWanxRegeneration(request, model);
		return niAssessDeliveryWanxRegeneration;
	}
	
	/**
	 * 暂停
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niAssessDeliveryWanxStop.do")
	@ResponseBody
	public AjaxResult niAssessDeliveryWanxStop(HttpServletRequest request,Model model){
		AjaxResult niAssessDeliveryWanxStop = niAssessDeliveryWanxService.niAssessDeliveryWanxStop(request, model);
		return niAssessDeliveryWanxStop;
	}
	
	/**
	 * 撤回
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niAssessDeliveryWanxWithdrawn.do")
	@ResponseBody
	public AjaxResult niAssessDeliveryWanxWithdrawn(HttpServletRequest request,Model model){
		AjaxResult niAssessDeliveryWanxWithdrawn = niAssessDeliveryWanxService.niAssessDeliveryWanxWithdrawn(request, model);
		return niAssessDeliveryWanxWithdrawn;
	}
	
	/**
	 * 发布
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niAssessDeliveryWanxPut.do")
	@ResponseBody
	public AjaxResult niAssessDeliveryWanxPut(HttpServletRequest request,Model model){
		AjaxResult niAssessDeliveryWanxPut = niAssessDeliveryWanxService.niAssessDeliveryWanxPut(request, model);
		return niAssessDeliveryWanxPut;
	}
	
	/**
	 * 请求显示数据调整数值
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niAssessDeliveryWanxOperateShow.do")
	@ResponseBody
	public AjaxResult niAssessDeliveryWanxOperateShow(HttpServletRequest request,Model model){
		AjaxResult niAssessDeliveryWanxOperateShow = niAssessDeliveryWanxService.niAssessDeliveryWanxOperateShow(request, model);
		return niAssessDeliveryWanxOperateShow;
	}
	
	/**
	 * 保存运营设置
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niAssessDeliveryWanxOperateSave.do")
	@ResponseBody
	public AjaxResult niAssessDeliveryWanxOperateSave(HttpServletRequest request,Model model){
		AjaxResult ajaxResult = new AjaxResult();
		try {
			niAssessDeliveryWanxService.niAssessDeliveryWanxOperateSave(request, model);
			ajaxResult.put("success", true);
			ajaxResult.put("msg", "设置成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("设置失败");
		}
		return ajaxResult;
	}

	/**
	 * 跳转到运营设置页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niAssessDeliveryWanxOperateJump.do")
	public String niAssessDeliveryWanxOperateJump(HttpServletRequest request,Model model){
		niAssessDeliveryWanxService.niAssessDeliveryWanxOperateJump(request, model);
		return "manager/delivery/assess/niAssessDeliveryWanxOperate";
	}
	
	/**
	 * 保存设置
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niAssessDeliveryWanxSetSave.do")
	@ResponseBody
	public AjaxResult niAssessDeliveryWanxSetSave(HttpServletRequest request,Model model){
		AjaxResult niAssessDeliveryWanxSetSave = niAssessDeliveryWanxService.niAssessDeliveryWanxSetSave(request, model);
		return niAssessDeliveryWanxSetSave;
	}
	
	/**
	 * 跳转到设置页面
	 */
	@RequestMapping("/niAssessDeliveryWanxSetJump.do")
	public String niAssessDeliveryWanxSetJump(HttpServletRequest request,Model model){
		niAssessDeliveryWanxService.niAssessDeliveryWanxSetJump(request, model);
		return "manager/delivery/assess/niAssessDeliveryWanxSet";
	}
	
	/**
	 * 保存信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niAssessDeliveryWanxMESSave.do")
	@ResponseBody
	public AjaxResult NiAssessDeliveryWanxMESSave(HttpServletRequest request,Model model,
			@RequestParam("uploadImgmes") MultipartFile file ){
		AjaxResult niAssessDeliveryWanxMESSave = niAssessDeliveryWanxService.NiAssessDeliveryWanxMESSave(request, model,file);
		return niAssessDeliveryWanxMESSave;
	}
	
	/**
	 * 添加图片
	 * @return
	 */
	@RequestMapping("/niAssessDeliveryWanxImgUpload.do")
	@ResponseBody
	public AjaxResult niAssessDeliveryWanxImgUpload(HttpServletRequest request,
			@RequestParam("uploadImgmes") MultipartFile file,Model model){
		AjaxResult ajaxResult = new AjaxResult();
		
		//拼接保存路径
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String formatDate = sdf.format(new Date());
		String sqnId = request.getParameter("sqnId");
		if(StrUtils.isEmpty(sqnId)){
			return AjaxResult.errorResult("未选择问卷,请重新选择!");
		}
		try {
			Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/qn/aqn/"+formatDate+"/"+sqnId);
			if(uploadFile!=null){
				String url = (String) uploadFile.get("url");
				String realPath = (String) uploadFile.get("realPath");
				log.info("=========================>>realPath:"+realPath);
				String contextPath = request.getContextPath();
				log.info("=================>>contextPath:"+contextPath);
				ajaxResult.put("url", contextPath+url);
				log.info("====================>>图片保存路径为:"+contextPath+url);
				
				//获取文件的存储路径
				String jdbcUrl = (String) uploadFile.get("jdbcUrl");
				log.info("===================>> 文件的存储路径 :" + jdbcUrl);
				ajaxResult.put("jdbcUrl", jdbcUrl);
				
			}
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ajaxResult;
	}
	
	/**
	 * 跳转到信息添加
	 */
	@RequestMapping("/niAssessDeliveryWanxMESJump.do")
	public String NiAssessDeliveryWanxMESJump(HttpServletRequest request,Model model){
		niAssessDeliveryWanxService.NiAssessDeliveryWanxMESJump(request, model);
		return "manager/delivery/assess/niAssessDeliveryWanxMES";
	}
	
	/**
	 * 新建投放测评问卷管理，添加测评问卷id
	 */
	@RequestMapping("/niAssessDeliveryWanxSave.do")
	@ResponseBody
	public AjaxResult NiAssessDeliveryWanxSave(HttpServletRequest request,Model model){
		AjaxResult niAssessDeliveryWanxSave = niAssessDeliveryWanxService.NiAssessDeliveryWanxSave(request, model);
		return niAssessDeliveryWanxSave;
	}
	
	/**
	 * 新增测评问卷页面
	 */
	@RequestMapping("/niAssessDeliveryWanxNewJump.do")
	public String NiAssessDeliveryWanxNewJump(HttpServletRequest request,Model model){
		niAssessDeliveryWanxService.NiAssessDeliveryWanxNewJump(request, model);
		return "manager/delivery/assess/niAssessDeliveryWanxNew";
	}
	
	/**
	 * 列表查询
	 * @param request
	 * @param model
	 */
	@RequestMapping("/niAssessDeliveryWanxList.do")
	public String NiAssessDeliveryWanxList(HttpServletRequest request,Model model,HttpServletResponse response){
		Map<String, Object> hashMap = new HashMap<String, Object>();
		//根据问卷名称，出题人获取测评问卷id
		String sqnpublish = request.getParameter("sqnpublish");  //问卷名称，出题人标记
		String sqnNameOrEditor = request.getParameter("sqnNameOrEditor");  //问卷名称，出题人
		String adclassid = request.getParameter("adclassid");     //广告
		String checkStats = request.getParameter("checkStats");   //被选中的投放状态    
		
		String channel_str = request.getParameter("channel");
		Integer channelId = null;
		if(StrUtils.isNotEmpty(channel_str)){
			channelId = Integer.valueOf(channel_str);
		}
		
		if(StrUtils.isNotEmpty(sqnpublish) && StrUtils.isNotEmpty(sqnNameOrEditor)){
			if(sqnpublish!=null){
				if(sqnpublish.trim().equals("1")){
					//出题人
					hashMap.put("editor", sqnNameOrEditor);
				}else if(sqnpublish.trim().equals("0")){
					//问卷名称
					hashMap.put("sqnName", sqnNameOrEditor);
				}else if(sqnpublish.trim().equals("2")){
					//投放id
					hashMap.put("delId", sqnNameOrEditor);
				}
			}
		}
		if(adclassid!=null){
			if(adclassid.trim().equals("1")){  
				//有报告
				hashMap.put("adId", 1);
			}else if(adclassid.trim().equals("2")){
				//无报告
				hashMap.put("adId", 2);
			}else if(adclassid.trim().equals("0")){
				//不限
				hashMap.put("adId", 0);
			}
		}
		ArrayList<Integer> statList = new ArrayList<Integer>();
		if(checkStats!=null && !checkStats.trim().equals("")){
			String[] split = checkStats.split("\\|");
			for(int i=0;i<split.length;i++){
				String stau = split[i];
				statList.add(Integer.valueOf(stau));
			}
		}
		hashMap.put("statList", statList);
		hashMap.put("channelId", channelId);
		
		List<NiAssessDeliveryWanxVo> selectList = niAssessDeliveryWanxService.selectList(hashMap);
		
		model.addAttribute("selectList", selectList);
		
		return "manager/delivery/assess/niAssessDeliveryWanxList";
	}
	
}
