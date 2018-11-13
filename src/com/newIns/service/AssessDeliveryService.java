package com.newIns.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.model.assess.AssessDelivery;
import com.newIns.model.assess.NiAssessDeliveryWanxVo;
import com.newIns.tools.AjaxResult;

public interface AssessDeliveryService {
	/**
	 * 根据问卷id 或者问卷名称查询测评问卷
	 */
	public void searchAssByIdOrName(HttpServletRequest request,Model model);
	/**
	 * 新增测评问卷页面
	 */
	public void NiAssessDeliveryWanxNewJump(HttpServletRequest request,Model model);
	/**
	 * 编辑测评投放  跳转到选择问卷页面
	 */
	public void niAssessDeliveryWanxQue_Edit(HttpServletRequest request,Model model);
	/**
	 * 根据真实数据 或者运营数据事实调整显示收集份数
	 */
	public AjaxResult AdjustAssesDeliveryWanxOperate(HttpServletRequest request);
	/**
	 * 请求显示收集份数
	 */
	public AjaxResult findNiAssShowOrderNum(HttpServletRequest request);
	/**
	 * 运营调整模态框
	 */
	public AjaxResult niAssessDeliveryWanxOperateModal(HttpServletRequest request);
	
	//保存编辑
	AjaxResult niAssessDeliveryWanxEditorSave(HttpServletRequest request,Model model);
	
	//编辑页面跳转
	AssessDelivery niAssessDeliveryWanxEditor(HttpServletRequest request,Model model);
	
    List<NiAssessDeliveryWanxVo> selectByQnid(Integer id);
	/**
	 * 跳转到设置
	 */
	public void niAssessDeliveryWanxSetJump(HttpServletRequest request,Model model);
	/**
	 * 自动定时任务检测完成
	 */
	public AjaxResult niAssessDeliveryWanxTimeTool(HttpServletRequest request,Model model);
	/**
	 * 终止
	 */
	public AjaxResult niAssessDeliveryWanxOver(HttpServletRequest request,Model model);
	/**
	 * 恢复
	 */
	public AjaxResult niAssessDeliveryWanxRegeneration(HttpServletRequest request,Model model);
	/**
	 * 暂停
	 */
	public AjaxResult niAssessDeliveryWanxStop(HttpServletRequest request,Model model);
	/**
	 * 撤回
	 */
	public AjaxResult niAssessDeliveryWanxWithdrawn(HttpServletRequest request,Model model);
	/**
	 * 发布
	 */
	public AjaxResult niAssessDeliveryWanxPut(HttpServletRequest request,Model model);
	/**
	 * 请求显示数据调整数值
	 */
	public AjaxResult niAssessDeliveryWanxOperateShow(HttpServletRequest request,Model model);
	/**
	 * 保存运营设置
	 */
	public void niAssessDeliveryWanxOperateSave(HttpServletRequest request,Model model);
	/**
	 * 跳转到运营设置页面
	 */
	public void niAssessDeliveryWanxOperateJump(HttpServletRequest request,Model model);
	
	/**
	 * 保存设置
	 */
	public AjaxResult niAssessDeliveryWanxSetSave(HttpServletRequest request,Model model);
	/**
	 * 保存信息
	 */
	public AjaxResult NiAssessDeliveryWanxMESSave(HttpServletRequest request,Model model,MultipartFile file);
	/**
	 * 跳转到信息添加页面
	 */
	public void NiAssessDeliveryWanxMESJump(HttpServletRequest request,Model model);
	
	/**
	 * 新建投放测评问卷管理，添加测评问卷id
	 */
	public AjaxResult NiAssessDeliveryWanxSave(HttpServletRequest request,Model model);

    
    //列表查询
    List<NiAssessDeliveryWanxVo> selectList(Map<String, Object> hashMap);

    //保存 模版
	public AjaxResult saveTemplatePage(HttpServletRequest request, Model model);
	
	//选择模版
	public void choseTemplatePage(HttpServletRequest request, Model model);
	
	public void assessDelivery_PaySetPage(HttpServletRequest request,
			Model model);
	
	public AjaxResult assessDelivery_PaySet_Save(HttpServletRequest request,
			Model model);
	
	public AjaxResult saveAssessResult_head_tail(HttpServletRequest request,
			Model model);
	
	public void assessDelivery_ResultPage(HttpServletRequest request,
			Model model);
	
    
}
