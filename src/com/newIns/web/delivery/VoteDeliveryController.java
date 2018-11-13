package com.newIns.web.delivery;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.model.vote.NiVoteQuestionnaire;
import com.newIns.model.vote.VoteDelivery;
import com.newIns.service.VoteDeliveryService;
import com.newIns.service.NiVoteQuestionnaireService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.FileUtil;

/**
 * 投放管理 投票问卷
 * @author Sang
 *
 */
@Controller
@RequestMapping("/platform")
public class VoteDeliveryController {
	private static Logger log = Logger.getLogger(SurveyDeliveryController.class);
	@Resource
	private VoteDeliveryService niVoteDeliveryWanxService;
	@Resource
	private NiVoteQuestionnaireService niVoteQuestionnaireService;
	
	// 保存模版
	@ResponseBody
	@RequestMapping("/saveVoteTemplatePage.do")
	public AjaxResult saveVoteTemplatePage(HttpServletRequest request,Model model){
		return niVoteDeliveryWanxService.saveTemplatePage(request,model);
	}
	
	// 跳转到选择模版页面
	@RequestMapping("/choseVoteTemplatePage.do")
	public String choseVoteTemplatePage(HttpServletRequest request,Model model){
		
		niVoteDeliveryWanxService.choseTemplatePage(request,model);
		return "manager/delivery/vote/niVoteDeliveryChoseTemplate";
	}
	
	/**
	 * 根据问卷id 问卷名称 查询问卷
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchVoteByIdOrName.do")
	public String searchVoteByIdOrName(HttpServletRequest request,Model model){
		niVoteDeliveryWanxService.searchVoteByIdOrName(request,model);
		return "manager/delivery/vote/niVoteDeliveryWanxAdd";
	}
	
	/**
	 * 定时根据开关修改显示收集份数
	 * @param request
	 * @return
	 */
	@RequestMapping("/AdjustVoteDeliveryWanxOperate.do")
	@ResponseBody
	public AjaxResult AdjustVoteDeliveryWanxOperate(HttpServletRequest request){
		AjaxResult adjustVoteDeliveryWanxOperate = niVoteDeliveryWanxService.AdjustVoteDeliveryWanxOperate(request);
		return adjustVoteDeliveryWanxOperate;
	}
	
	/**
	 * 请求显示运营调整数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/findVoteShowOrderNum.do")
	@ResponseBody
	public AjaxResult findVoteShowOrderNum(HttpServletRequest request){
		AjaxResult findVoteShowOrderNum = niVoteDeliveryWanxService.findVoteShowOrderNum(request);
		return findVoteShowOrderNum;
	}
	
	/**
	 * 弹出运营调整模态框
	 * @param request
	 * @return
	 */
	@RequestMapping("/niVoteDeliveryWanxOperateModal.do")
	@ResponseBody
	public AjaxResult niVoteDeliveryWanxOperateModal(HttpServletRequest request){
		AjaxResult niVoteDeliveryWanxOperateModal = niVoteDeliveryWanxService.niVoteDeliveryWanxOperateModal(request);
		return niVoteDeliveryWanxOperateModal;
	}
	
	/**
	 * 保存编辑
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niVoteDeliveryWanxEditorSave.do")
	@ResponseBody
	public AjaxResult niVoteDeliveryWanxEditorSave(HttpServletRequest request,Model model){
		AjaxResult ajaxResult = new AjaxResult();
		try {
			ajaxResult = niVoteDeliveryWanxService.niVoteDeliveryWanxEditorSave(request, model);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("编辑投放信息失败");
		}
		return ajaxResult;
	}
	/**
	 * 编辑跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="niVoteDeliveryWanxEditor.do",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult niVoteDeliveryWanxEditor(HttpServletRequest request,Model model){
		AjaxResult ajaxResult = new AjaxResult();
		VoteDelivery niSurveyDeliveryWanxEditor = niVoteDeliveryWanxService.niVoteDeliveryWanxEditor(request, model);
		
		Date btime = null;
		Date etime = null;
		if(niSurveyDeliveryWanxEditor!=null){
			btime = niSurveyDeliveryWanxEditor.getBtime();
			etime = niSurveyDeliveryWanxEditor.getEtime();
			//获取问卷id
			Integer sqnid = niSurveyDeliveryWanxEditor.getVqnid();
			if(sqnid!=null){
				//获取问卷名称
				NiVoteQuestionnaire selectByPrimaryKey = niVoteQuestionnaireService.selectByPrimaryKey(sqnid);
				if(selectByPrimaryKey!=null){
					ajaxResult.put("nvq", selectByPrimaryKey);
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
		ajaxResult.put("sdw", niSurveyDeliveryWanxEditor);
		return ajaxResult;
	}
	
	/**
	 * 上传图片
	 * @param request
	 * @param file
	 */
	@RequestMapping("/niVoteDeliveryWanxImgUpload.do")
	@ResponseBody
	public AjaxResult niVoteDeliveryWanxImgUpload(HttpServletRequest request,@RequestParam("uploadImgmes") MultipartFile file){
		AjaxResult ajaxResult = new AjaxResult();
		//拼接保存路径
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String formatDate = sdf.format(new Date());
		String sqnId = request.getParameter("sqnId");
		
		try {
			Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/qn/vqn/"+formatDate+"/"+sqnId);
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
	 * 保存运营调整
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/niVoteDeliveryWanxOperateSave.do")
	public AjaxResult niVoteDeliveryWanxOperateSave(HttpServletRequest request,Model model){
		
		AjaxResult ajaxResult = new AjaxResult();
		try {
			niVoteDeliveryWanxService.niVoteDeliveryWanxOperateSave(request, model);
			ajaxResult.put("success", true);
			ajaxResult.put("msg", "设置成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("设置失败");
		}
		return ajaxResult;
	}
	
	/**
	 * 跳转到数据运营页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niVoteDeliveryWanxOperateJump.do")
	public String niVoteDeliveryWanxOperateJump(HttpServletRequest request,Model model){
		niVoteDeliveryWanxService.niVoteDeliveryWanxOperateJump(request, model);
		return "manager/delivery/vote/niVoteDeliveryWanxOperate";
	}
	
	/**
	 * 保持设置
	 */
	@ResponseBody
	@RequestMapping("/niVoteDeliveryWanxSetUpSave.do")
	public AjaxResult niVoteDeliveryWanxSetUpSave(HttpServletRequest request,Model model){
		AjaxResult niVoteDeliveryWanxSetUpSave = niVoteDeliveryWanxService.niVoteDeliveryWanxSetUpSave(request, model);
		return niVoteDeliveryWanxSetUpSave;
	}
	
	/**
	 * 跳转到设置
	 */
	@RequestMapping("/niVoteDeliveryWanxSetUpJump.do")
	public String niVoteDeliveryWanxSetUpJump(HttpServletRequest request,Model model){
		niVoteDeliveryWanxService.niVoteDeliveryWanxSetUpJump(request, model);
		return "manager/delivery/vote/niVoteDeliveryWanxSetUp";
	}
	
	/**
	 * 保存信息
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/niVoteDeliveryWanxSaveMES.do")
	public AjaxResult niVoteDeliveryWanxSaveMES(HttpServletRequest request,Model model,
			@RequestParam("uploadImgmes") MultipartFile file){
		AjaxResult niVoteDeliveryWanxSaveMES = niVoteDeliveryWanxService.niVoteDeliveryWanxSaveMES(request, model,file);
		return niVoteDeliveryWanxSaveMES;
	}
	
	/**
	 * 跳转到信息添加页面
	 */
	@RequestMapping("/niVoteDeliveryWanxSaveMESJump.do")
	public String niVoteDeliveryWanxSaveMESJump(HttpServletRequest request,Model model){
		niVoteDeliveryWanxService.niVoteDeliveryWanxSaveMESJump(request, model);
		return "manager/delivery/vote/niVoteDeliveryWanxSaveMES";
	}
	
	/**
	 * 添加问卷
	 */
	@RequestMapping("/niVoteDeliveryWanxSave.do")
	@ResponseBody
	public AjaxResult niVoteDeliveryWanxSaveQue(HttpServletRequest request,Model model){
		AjaxResult niVoteDeliveryWanxSaveQue = niVoteDeliveryWanxService.niVoteDeliveryWanxSaveQue(request, model);
		return niVoteDeliveryWanxSaveQue;
	}

	/**
	 * 跳转到添加页面
	 */
	@RequestMapping("/niVoteDeliveryWanxAdd.do")
	public String niVoteDeliveryWanxAdd(HttpServletRequest request,Model model){
		niVoteDeliveryWanxService.niVoteDeliveryWanxAdd(request, model);
		
		return "manager/delivery/vote/niVoteDeliveryWanxAdd";
	}
	
	/**
	 * 人工终止
	 */
	@RequestMapping("/niVoteDeliveryWanxOver.do")
	@ResponseBody
	public AjaxResult niVoteDeliveryWanxOver(HttpServletRequest request,Model model){
		AjaxResult niVoteDeliveryWanxOver = niVoteDeliveryWanxService.niVoteDeliveryWanxOver(request, model);
		return niVoteDeliveryWanxOver;
	}
	
	/**
	 * 恢复
	 */
	@RequestMapping("/niVoteDeliveryWanxRegeneration.do")
	@ResponseBody
	public AjaxResult niVoteDeliveryWanxRegeneration(HttpServletRequest request,Model model){
		AjaxResult niVoteDeliveryWanxRegeneration = niVoteDeliveryWanxService.niVoteDeliveryWanxRegeneration(request, model);
		return niVoteDeliveryWanxRegeneration;
	}
	
	/**
	 * 暂停
	 */
	@RequestMapping("/niVoteDeliveryWanxStop.do")
	@ResponseBody
	public AjaxResult niVoteDeliveryWanxStop(HttpServletRequest request,Model model){
		AjaxResult niVoteDeliveryWanxStop = niVoteDeliveryWanxService.niVoteDeliveryWanxStop(request, model);
		return niVoteDeliveryWanxStop;
	}
	
	/**
	 * 撤回
	 */
	@RequestMapping("/niVoteDeliveryWanxWithdrawn.do")
	@ResponseBody
	public AjaxResult niVoteDeliveryWanxWithdrawn(HttpServletRequest request,Model model){
		AjaxResult niVoteDeliveryWanxWithdrawn = niVoteDeliveryWanxService.niVoteDeliveryWanxWithdrawn(request, model);
		return niVoteDeliveryWanxWithdrawn;
	}
	
	/**
	 * 投放
	 */
	@RequestMapping("/niVoteDeliveryWanxPut.do")
	@ResponseBody
	public AjaxResult niVoteDeliveryWanxPut(HttpServletRequest request,Model model){
		AjaxResult niVoteDeliveryWanxPut = niVoteDeliveryWanxService.niVoteDeliveryWanxPut(request, model);
		return niVoteDeliveryWanxPut;
	}
	/**
	 * 列表查询
	 */
	@RequestMapping("/niVoteDeliveryWanxList.do")
	public String niVoteDeliveryWanxList(HttpServletRequest request,Model model){
		niVoteDeliveryWanxService.niVoteDeliveryWanxList(request, model);
		return "manager/delivery/vote/niVoteDeliveryWanxList";
	}
	
	/**
	 * 显示页面跳转数据
	 */
	@RequestMapping("/niVoteDeliveryWanxOperateShow.do")
	@ResponseBody
	public AjaxResult niVoteDeliveryWanxOperateShow(HttpServletRequest request,Model model){
		AjaxResult niVoteDeliveryWanxOperateShow = niVoteDeliveryWanxService.niVoteDeliveryWanxOperateShow(request, model);
		return niVoteDeliveryWanxOperateShow;
	}
	
}
