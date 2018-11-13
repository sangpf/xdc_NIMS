package com.newIns.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.model.vote.VoteDelivery;
import com.newIns.tools.AjaxResult;

public interface VoteDeliveryService {
	
	/**
	 * 根据问卷id 问卷名称 查询问卷
	 */
	public void searchVoteByIdOrName(HttpServletRequest request,Model model);
	/**
	 * 跳转到添加页面
	 */
	public void niVoteDeliveryWanxAdd(HttpServletRequest request,Model model);
	/**
	 * 编辑 跳转问卷
	 */
	public void niVoteDeliveryWanxAdd_Edit(HttpServletRequest request,Model model);
	/**
	 * 定时根据开关修改显示收集份数
	 */
	public AjaxResult AdjustVoteDeliveryWanxOperate(HttpServletRequest request);
	/**
	 * 请求显示运营调整数据
	 */
	public AjaxResult findVoteShowOrderNum(HttpServletRequest request);
	/**
	 * 弹出运营调整模态框
	 */
	public AjaxResult niVoteDeliveryWanxOperateModal(HttpServletRequest request);
	//保存编辑
	AjaxResult niVoteDeliveryWanxEditorSave(HttpServletRequest request,Model model);
	//编辑页面跳转
	VoteDelivery niVoteDeliveryWanxEditor(HttpServletRequest request,Model model);
	
    List<VoteDelivery> selectByQnId(Integer id);
	/**
	 * 显示页面跳转数据
	 */
	public AjaxResult niVoteDeliveryWanxOperateShow(HttpServletRequest request,Model model);
	/**
	 * 保持运营调整
	 */
	public void niVoteDeliveryWanxOperateSave(HttpServletRequest request,Model model);
	/**
	 * 跳转到数据运营页面
	 */
	public void niVoteDeliveryWanxOperateJump(HttpServletRequest request,Model model);
	/**
	 * 保持设置
	 */
	public AjaxResult niVoteDeliveryWanxSetUpSave(HttpServletRequest request,Model model);
	/**
	 * 跳转到信息设置
	 */
	public void niVoteDeliveryWanxSetUpJump(HttpServletRequest request,Model model);
	/**
	 * 保存信息
	 */
	public AjaxResult niVoteDeliveryWanxSaveMES(HttpServletRequest request,Model model,MultipartFile file);
	/**
	 * 跳转到信息添加页面
	 */
	public void niVoteDeliveryWanxSaveMESJump(HttpServletRequest request,Model model);
	/**
	 * 添加问卷
	 */
	public AjaxResult niVoteDeliveryWanxSaveQue(HttpServletRequest request,Model model);
	/**
	 * 人工终止
	 */
	public AjaxResult niVoteDeliveryWanxOver(HttpServletRequest request,Model model);
	/**
	 * 恢复
	 */
	public AjaxResult niVoteDeliveryWanxRegeneration(HttpServletRequest request,Model model);
	/**
	 * 暂停
	 */
	public AjaxResult niVoteDeliveryWanxStop(HttpServletRequest request,Model model);
	/**
	 * 撤回
	 */
	public AjaxResult niVoteDeliveryWanxWithdrawn(HttpServletRequest request,Model model);
	/**
	 * 投放
	 */
	public AjaxResult niVoteDeliveryWanxPut(HttpServletRequest request,Model model);
	/**
	 * 列表查询
	 */
	public void niVoteDeliveryWanxList(HttpServletRequest request,Model model);
	
	public void choseTemplatePage(HttpServletRequest request, Model model);
	public AjaxResult saveTemplatePage(HttpServletRequest request, Model model);
}
