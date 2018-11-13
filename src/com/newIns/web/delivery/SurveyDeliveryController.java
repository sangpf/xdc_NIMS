package com.newIns.web.delivery;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.model.page.NiDaily3updateWanx;
import com.newIns.model.page.NiDaily3updateWanxKey;
import com.newIns.model.page.NiSuperWanx;
import com.newIns.model.survery.NiSurveyDeliveryWanxVO;
import com.newIns.model.survery.NiSurveyQuestionnaire;
import com.newIns.model.survery.SurveyDelivery;
import com.newIns.service.NiDaily3updateWanxService;
import com.newIns.service.NiSuperWanxService;
import com.newIns.service.SurveyDeliveryService;
import com.newIns.service.NiSurveyOrderService;
import com.newIns.service.NiSurveyQuestionnaireService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.FileUtil;
import com.newIns.tools.StrUtils;
import com.newIns.tools.ThreadLocalDateUtil;

/**
 * 调查问卷玩校投放管理
 * @author Sang
 *
 */
@Controller
@RequestMapping("/platform")
public class SurveyDeliveryController {
	private static Logger log = Logger.getLogger(SurveyDeliveryController.class);
	@Resource
	private SurveyDeliveryService niSurveyDeliveryWanxService;
	@Resource
	private NiSurveyQuestionnaireService niSurveyQuestionnaireService;
	@Resource
	private NiSurveyOrderService niSurveyOrderService;
	@Resource
	private NiDaily3updateWanxService niDaily3updateWanxService;
	@Resource
	private NiSuperWanxService niSuperWanxService;
	
	// 保存模版
	@ResponseBody
	@RequestMapping("/saveSurveyTemplatePage.do")
	public AjaxResult saveSurveyTemplatePage(HttpServletRequest request,Model model){
		return niSurveyDeliveryWanxService.saveTemplatePage(request,model);
	}
	
	// 跳转到选择模版页面
	@RequestMapping("/choseSurveyTemplatePage.do")
	public String choseSurveyTemplatePage(HttpServletRequest request,Model model){
		
		niSurveyDeliveryWanxService.choseTemplatePage(request,model);
		return "manager/delivery/survey/niSurveyDeliveryChoseTemplate";
	}
	
	// 筛选模版
	@RequestMapping("/screenSurveyTemplatePage.do")
	public String screenSurveyTemplatePage(HttpServletRequest request,Model model){
		niSurveyDeliveryWanxService.screenSurveyTemplatePage(request,model);
		return "manager/delivery/survey/niSurveyDeliveryChoseTemplate";
	}
	
	/**
	 * 添加投放 根据问卷id,问卷名称查询问卷,将问卷信息显示
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchQueByIdOrName.do")
	public String searchQueByIdOrName(HttpServletRequest request,Model model){
		
		niSurveyDeliveryWanxService.searchQueByIdOrName(request,model);
		
		return "manager/delivery/survey/niSurveyDeliveryWanxAddQue";
	}
	/**
	 * 保存编辑
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxEditorSave.do")
	@ResponseBody
	public AjaxResult niSurveyDeliveryWanxEditorSave(HttpServletRequest request,Model model){
		AjaxResult ajaxResult = new AjaxResult();
		try {
			ajaxResult = niSurveyDeliveryWanxService.niSurveyDeliveryWanxEditorSave(request, model);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("编辑投放信息失败");
		}
		return ajaxResult;
	}
	
	/**
	 * 编辑投放,打开新的页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxEditor_NewPage.do")
	public String niSurveyDeliveryWanxEditor_NewPage(HttpServletRequest request,Model model){
		niSurveyDeliveryWanxService.niSurveyDeliveryWanxEditorNewPage(request, model);
		return "manager/delivery/survey/niSurveyDeliveryWanxAddQue_Edit";
	}
	
	/**
	 * 编辑跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxEditor.do")
	@ResponseBody
	public AjaxResult niSurveyDeliveryWanxEditor(HttpServletRequest request,Model model){
		AjaxResult ajaxResult = new AjaxResult();
		SurveyDelivery niSurveyDeliveryWanxEditor = niSurveyDeliveryWanxService.niSurveyDeliveryWanxEditor(request, model);
		Date btime = null;
		Date etime = null;
		if(niSurveyDeliveryWanxEditor!=null){
			btime = niSurveyDeliveryWanxEditor.getBtime();
			etime = niSurveyDeliveryWanxEditor.getEtime();
			//获取问卷id
			Integer sqnid = niSurveyDeliveryWanxEditor.getSqnid();
			if(sqnid!=null){
				//获取问卷名称
				NiSurveyQuestionnaire niSurveyQuestionnaire = niSurveyQuestionnaireService.selectByPrimaryKey(sqnid);
				if(niSurveyQuestionnaire!=null){
					ajaxResult.put("sqt", niSurveyQuestionnaire);
					
				}
			}
			
		}
		
		String btimeStr = null;
		String etimeStr = null;
		try {
			btimeStr = ThreadLocalDateUtil.formatDate(btime);
			etimeStr = ThreadLocalDateUtil.formatDate(etime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ajaxResult.put("btimeStr", btimeStr);
		ajaxResult.put("etimeStr", etimeStr);
		ajaxResult.put("sdw", niSurveyDeliveryWanxEditor);
		return ajaxResult;
	}
	
	/**
	 * 发布报告页面跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxPresentationJump.do")
	public String niSurveyDeliveryWanxPresentationJump(HttpServletRequest request,Model model){
		return "manager/delivery/survey/niSurveyDeliveryWanxPresentation";
	}
	/**
	 * 保存发布报告
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxPresentationSave.do")
	public AjaxResult niSurveyDeliveryWanxPresentationSave(HttpServletRequest request,Model model){
		
		return AjaxResult.successResult("=================>>保存发布报告成功");
	}
	
	/**
	 * 上传图片
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/niSurveyUploadPicture.do")
	@ResponseBody
	public AjaxResult niSurveyUploadPicture(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("uploadImgmes") MultipartFile file,Model model) throws Exception{
		AjaxResult ajaxResult = new AjaxResult();
		//拼接保存路径
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String formatDate = sdf.format(new Date());
		String sqnId = request.getParameter("sqnId");
		if(StrUtils.isEmpty(sqnId)){
			return AjaxResult.errorResult("未选择问卷,请重新选择!");
		}
		Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/qn/sqn/"+formatDate+"/"+sqnId);
		
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
	 * 跳转到设置页面  编辑
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxSetUPJump_Edit.do")
	public String niSurveyDeliveryWanxSetUPJump_Edit(HttpServletRequest request,Model model){
		niSurveyDeliveryWanxService.niSurveyDeliveryWanxSetUPJump(request, model);
		return "manager/delivery/survey/niSurveyDeliveryWanxSetUP_Edit";
	}
	
	/**
	 * 跳转到设置页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxSetUPJump.do")
	public String niSurveyDeliveryWanxSetUPJump(HttpServletRequest request,Model model){
		niSurveyDeliveryWanxService.niSurveyDeliveryWanxSetUPJump(request, model);
		return "manager/delivery/survey/niSurveyDeliveryWanxSetUP";
	}
	/**
	 * 添加设置
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxSetUPSave.do")
	@ResponseBody
	public AjaxResult niSurveyDeliveryWanxSetUPSave(HttpServletRequest request,Model model){
		AjaxResult niSurveyDeliveryWanxSetUPSave = niSurveyDeliveryWanxService.niSurveyDeliveryWanxSetUPSave(request, model);
		return niSurveyDeliveryWanxSetUPSave;
	}
	
	/**
	 * 跳转 信息添加页面  编辑
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxAddMES_Edit.do")
	public String niSurveyDeliveryWanxAddMES_Edit(HttpServletRequest request,Model model){
		niSurveyDeliveryWanxService.niSurveyDeliveryWanxMESJUMP( request, model);
		return "manager/delivery/survey/niSurveyDeliveryWanxAddMES";
	}
	
	/**
	 * 跳转 信息添加页面
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxAddMES.do")
	public String niSurveyDeliveryWanxMESJUMP(HttpServletRequest request,Model model){
		niSurveyDeliveryWanxService.niSurveyDeliveryWanxMESJUMP( request, model);
		return "manager/delivery/survey/niSurveyDeliveryWanxAddMES";
	}
	
	/**
	 * 根据真实数据 或者运营数据事实调整显示收集份数
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/AdjustSurveyDeliveryWanxOperate.do")
	@ResponseBody
	public AjaxResult AdjustSurveyDeliveryWanxOperate(HttpServletRequest request,HttpServletResponse response,Model model){
		AjaxResult adjustSurveyDeliveryWanxOperate = niSurveyDeliveryWanxService.AdjustSurveyDeliveryWanxOperate(request, response, model);
		return adjustSurveyDeliveryWanxOperate;
	}
	
	/**
	 * 请求显示收集份数
	 * @return
	 */
	@RequestMapping("/findShowOrderNum.do")
	@ResponseBody
	public AjaxResult findShowOrderNum(HttpServletRequest request,HttpServletResponse response,Model model){
		AjaxResult findShowOrderNum = niSurveyDeliveryWanxService.findShowOrderNum(request, response, model);
		return findShowOrderNum;
	}
	
	/**
	 * 运营调整调转页面
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxOperateJump.do")
	public String niSurveyDeliveryWanxOperateJump(HttpServletRequest request,HttpServletResponse response,Model model){
		niSurveyDeliveryWanxService.niSurveyDeliveryWanxOperateJump(request, response, model);
		return "manager/delivery/survey/niSurveyDeliveryOperate";
	}
	
	/**
	 * 运营调整模态框
	 * @param request
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxOperateModal.do")
	@ResponseBody
	public AjaxResult niSurveyDeliveryWanxOperateModal(HttpServletRequest request){
		AjaxResult niSurveyDeliveryWanxOperateModal = niSurveyDeliveryWanxService.niSurveyDeliveryWanxOperateModal(request);
		return niSurveyDeliveryWanxOperateModal;
	}
	
	/**
	 * 运营管理
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("niSurveyDeliveryWanxOperateSave.do")
	@ResponseBody
	public AjaxResult niSurveyDeliveryWanxOperateSave(HttpServletRequest request,HttpServletResponse response,Model model){
		AjaxResult niSurveyDeliveryWanxOperateSave = niSurveyDeliveryWanxService.niSurveyDeliveryWanxOperateSave(request, response, model);
		return niSurveyDeliveryWanxOperateSave;
	}
	
	/**
	 * 跳转到  选择问卷   
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxAddQue.do")
	public String niSurveyDeliveryWanxAddQue(HttpServletRequest request,Model model){
		
		niSurveyDeliveryWanxService.niSurveyDeliveryWanxAddQue(request, model);
		
		return "manager/delivery/survey/niSurveyDeliveryWanxAddQue";
	}
	
	/**
	 * 新增投放 添加问卷
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxSaveQue.do")
	@ResponseBody
	public AjaxResult niSurveyDeliveryWanxSaveQue(HttpServletRequest request,Model model){
		AjaxResult niSurveyDeliveryWanxSaveQue = niSurveyDeliveryWanxService.niSurveyDeliveryWanxSaveQue(request, model);
		return niSurveyDeliveryWanxSaveQue;
	}
	
	/**
	 * 保存投放管理信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/niSurveyDeliveryWanxSaveMES.do")
	@ResponseBody
	public AjaxResult niSurveyDeliveryWanxSaveMES(HttpServletRequest request,HttpServletResponse response,Model model
			,@RequestParam("uploadImgmes") MultipartFile picFile){
		AjaxResult niSurveyDeliveryWanxSaveMES = niSurveyDeliveryWanxService.niSurveyDeliveryWanxSaveMES(request, response, model,picFile);
		return niSurveyDeliveryWanxSaveMES;
	}
	
	/**
	 * 列表查询
	 * @return
	 */
	@RequestMapping("/niSurveyDeliveryWanxList.do")
	public String niSurveyDeliveryWanxList(HttpServletRequest request,Model model,HttpServletResponse response){
		Map<String, Object> hashMap = new HashMap<String, Object>();
		//根据问卷名称，出题人获取测评问卷id
		String sqnpublish = request.getParameter("sqnpublish");  //问卷名称，发布机构标记
		String sqnNameOrEditor = request.getParameter("sqnNameOrEditor");  //问卷名称，发布机构
		String adclassid = request.getParameter("adclassid");     //广告
		String checkStats = request.getParameter("checkStats");   //被选中的投放状态    
		
		String channel_str = request.getParameter("channel");
		
		Integer channelId = null;
		if(StrUtils.isNotEmpty(channel_str)){
			channelId = Integer.valueOf(channel_str);
		}
		
		if(StrUtils.isNotEmpty(sqnpublish) && StrUtils.isNotEmpty(sqnNameOrEditor)){
			if(sqnpublish.trim().equals("1")){
				//发布机构
				hashMap.put("editor", sqnNameOrEditor);
			}else if(sqnpublish.trim().equals("0")){
				//问卷名称
				hashMap.put("sqnName", sqnNameOrEditor);
			}else if(sqnpublish.trim().equals("2")){
				//投放id
				hashMap.put("delId", sqnNameOrEditor);
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
		//列表查询， 显示投放状态，问卷状态
		List<NiSurveyDeliveryWanxVO> selectList = niSurveyDeliveryWanxService.selectList(hashMap);
		model.addAttribute("niSurveyDeliveryWanxs", selectList);
		return "manager/delivery/survey/niSurveyDeliveryWanxList";
	}
	
	/**
	 * 修改状态
	 * @param request
	 * @param model
	 */
	@RequestMapping("/updateStatSurveyDeliveryWanx.do")
	@ResponseBody
	public AjaxResult updateStatSurveyDeliveryWanx(HttpServletRequest request,Model model){
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryid = request.getParameter("deliveryid");
		String statu = request.getParameter("statu");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		
		if(deliveryid!=null && !deliveryid.trim().equals("")){
			String[] split = deliveryid.split("\\|");
			for(int i=0;i<split.length;i++){
				idList.add(Integer.valueOf(split[i]));
			}
			hashMap.put("idList", idList);
		}
		Integer statu2 = null;
		if(statu!=null && !statu.trim().equals("")){
			statu2 = Integer.valueOf(statu);
			hashMap.put("statu2", statu2);
		}
		//修改时间
		hashMap.put("uTime", new Date());
		try {
			int updateByPrimaryKeySelective = niSurveyDeliveryWanxService.updateStatuByIds(hashMap);
			if(updateByPrimaryKeySelective>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("操作失败");
		}
		return ajaxResult;
	}
	
	
	/**
	 * 撤回
	 * @param request
	 * @param model
	 */
	@RequestMapping("/withdrawnQuestionnaire.do")
	@ResponseBody
	public AjaxResult withdrawnQuestionnaire(HttpServletRequest request,Model model){
		//获取数据
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryid = request.getParameter("deliveryid");
		String statu = request.getParameter("statu");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		
		//三更
		NiDaily3updateWanxKey niDaily3updateWanxKey = new NiDaily3updateWanxKey();
		//超级问卷
//		NiSuperWanxKey niSuperWanxKey = new NiSuperWanxKey();
//		NiSuperWanx niSuperWanx = new NiSuperWanx();
		
		if(deliveryid!=null && !deliveryid.trim().equals("")){
			String[] split = deliveryid.split("\\|");
			for(int i=0;i<split.length;i++){
				Integer delivery_id = Integer.valueOf(split[i]);
				idList.add(delivery_id);
				
				//校验  三更列表
				niDaily3updateWanxKey.setDeliveryid(delivery_id);
				niDaily3updateWanxKey.setQntype(1);  // 调查
				NiDaily3updateWanx selectByPrimaryKey = niDaily3updateWanxService.selectByPrimaryKey(niDaily3updateWanxKey);
				if(selectByPrimaryKey!=null){
					Integer pagestatus = selectByPrimaryKey.getPagestatus();
					if(pagestatus!=1){
						//在三更页面中不是待发布状态
						return AjaxResult.errorResult("投放id:"+delivery_id+"已经发布到三更列表页面中,无法撤回!");
					}
				}
				//检查 超级问卷列表
				//判断该投放是否已经在超级调查页面中存在
				NiSuperWanx niSuperWanx = niSuperWanxService.selectByDeliveryId(delivery_id);
				if(niSuperWanx!=null){
					return AjaxResult.errorResult("投放id:"+delivery_id+"已经发布到超级问卷列表,无法撤回!");
				}
				
			}
			hashMap.put("idList", idList);
		}
		Integer statu2 = null;
		if(statu!=null && !statu.trim().equals("")){
			statu2 = Integer.valueOf(statu);
			hashMap.put("statu2", statu2);
		}
		//修改时间
		hashMap.put("uTime", new Date());
		try {
			int updateByPrimaryKeySelective = niSurveyDeliveryWanxService.updateStatuByIds(hashMap);
			if(updateByPrimaryKeySelective>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "撤回成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("操作失败");
		}
		return ajaxResult;
	}
	
	
	
	/**
	 * 暂停
	 * @param request
	 * @param model
	 */
	@RequestMapping("/stopStatSurveyDeliveryWanx.do")
	@ResponseBody
	public AjaxResult stopStatSurveyDeliveryWanx(HttpServletRequest request,Model model){
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryid = request.getParameter("deliveryid");
		String statu = request.getParameter("statu");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		
		//三更
		NiDaily3updateWanxKey niDaily3updateWanxKey = new NiDaily3updateWanxKey();
		
		if(deliveryid!=null && !deliveryid.trim().equals("")){
			String[] split = deliveryid.split("\\|");
			for(int i=0;i<split.length;i++){
				Integer delivery_id = Integer.valueOf(split[i]);
				idList.add(delivery_id);
				
				//校验  三更列表
				niDaily3updateWanxKey.setDeliveryid(delivery_id);
				niDaily3updateWanxKey.setQntype(1);  // 调查
				NiDaily3updateWanx selectByPrimaryKey = niDaily3updateWanxService.selectByPrimaryKey(niDaily3updateWanxKey);
				if(selectByPrimaryKey!=null){
					Integer pagestatus = selectByPrimaryKey.getPagestatus();
					if(pagestatus!=1){
						//在三更页面中不是待发布状态
						return AjaxResult.errorResult("投放id:"+delivery_id+"已经发布到三更列表页面中,无法暂停!");
					}
				}
				//检查 超级问卷列表
				NiSuperWanx niSuperWanx = niSuperWanxService.selectByDeliveryId(delivery_id);
				if(niSuperWanx!=null){
					return AjaxResult.errorResult("投放id:"+delivery_id+"已经发布到超级问卷列表,无法暂停!");
				}
				
			}
			hashMap.put("idList", idList);
		}
		Integer statu2 = null;
		if(statu!=null && !statu.trim().equals("")){
			statu2 = Integer.valueOf(statu);
			hashMap.put("statu2", statu2);
		}
		//修改时间
		hashMap.put("uTime", new Date());
		try {
			int updateByPrimaryKeySelective = niSurveyDeliveryWanxService.updateStatuByIds(hashMap);
			if(updateByPrimaryKeySelective>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("操作失败");
		}
		return ajaxResult;
	}
	
	/**
	 * 终止
	 * @param request
	 * @param model
	 */
	@RequestMapping("/overStatSurveyDeliveryWanx.do")
	@ResponseBody
	public AjaxResult overStatSurveyDeliveryWanx(HttpServletRequest request,Model model){
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryid = request.getParameter("deliveryid");
		String statu = request.getParameter("statu");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		
		//三更
		NiDaily3updateWanxKey niDaily3updateWanxKey = new NiDaily3updateWanxKey();
		
		if(deliveryid!=null && !deliveryid.trim().equals("")){
			String[] split = deliveryid.split("\\|");
			for(int i=0;i<split.length;i++){
				Integer delivery_id = Integer.valueOf(split[i]);
				
				idList.add(delivery_id);
				
				//校验  三更列表
				niDaily3updateWanxKey.setDeliveryid(delivery_id);
				niDaily3updateWanxKey.setQntype(1);  // 调查
				NiDaily3updateWanx selectByPrimaryKey = niDaily3updateWanxService.selectByPrimaryKey(niDaily3updateWanxKey);
				if(selectByPrimaryKey!=null){
					Integer pagestatus = selectByPrimaryKey.getPagestatus();
					if(pagestatus!=1){
						//在三更页面中不是待发布状态
						return AjaxResult.errorResult("投放id:"+delivery_id+"已经发布到三更列表页面中,无法终止!");
					}
				}
				//检查 超级问卷列表
				NiSuperWanx niSuperWanx = niSuperWanxService.selectByDeliveryId(delivery_id);
				if(niSuperWanx!=null){
					return AjaxResult.errorResult("投放id:"+delivery_id+"已经发布到超级问卷列表,无法终止!");
				}

			}
			hashMap.put("idList", idList);
		}
		Integer statu2 = null;
		if(statu!=null && !statu.trim().equals("")){
			statu2 = Integer.valueOf(statu);
			hashMap.put("statu2", statu2);
		}
		//修改时间
		hashMap.put("uTime", new Date());
		try {
			int updateByPrimaryKeySelective = niSurveyDeliveryWanxService.updateStatuByIds(hashMap);
			if(updateByPrimaryKeySelective>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("操作失败");
		}
		return ajaxResult;
	}
}
