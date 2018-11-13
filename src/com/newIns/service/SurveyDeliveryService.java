package com.newIns.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.model.survery.NiSurveyDeliveryWanxVO;
import com.newIns.model.survery.SurveyDelivery;
import com.newIns.tools.AjaxResult;

public interface SurveyDeliveryService {
	/**
	 * 添加投放 根据问卷id,问卷名称查询问卷,将问卷信息显示
	 * @return
	 */
	void searchQueByIdOrName(HttpServletRequest request,Model model);
	/**
	 * 请求显示收集份数
	 */
	public AjaxResult findShowOrderNum(HttpServletRequest request,HttpServletResponse response,Model model);
	/**
	 * 运营管理
	 */
	public AjaxResult niSurveyDeliveryWanxOperateSave(HttpServletRequest request,HttpServletResponse response,Model model);
	/**
	 * 跳转到添加问卷
	 * @return
	 */
	public void niSurveyDeliveryWanxAddQue(HttpServletRequest request,Model model);
	/**
	 * 编辑投放,打开新的页面
	 */
	public void niSurveyDeliveryWanxEditorNewPage(HttpServletRequest request,Model model);
	
	/**
	 * 保存调查问卷
	 */
	public AjaxResult niSurveyDeliveryWanxSaveQue(HttpServletRequest request,Model model);
	
	//保存编辑
	public AjaxResult niSurveyDeliveryWanxEditorSave(HttpServletRequest request,Model model);
	//编辑页面跳转
	public SurveyDelivery niSurveyDeliveryWanxEditor(HttpServletRequest request,Model model);
	//
    List<NiSurveyDeliveryWanxVO> selectListByqnId(Integer id);
	/**
	 * 保存投放管理信息
	 */
	public AjaxResult niSurveyDeliveryWanxSaveMES(HttpServletRequest request,HttpServletResponse response,Model model,MultipartFile picFile);
	/**
	 * 跳转 信息添加页面
	 */
	public void niSurveyDeliveryWanxMESJUMP(HttpServletRequest request,Model model);
	/**
	 * 根据真实数据 或者运营数据事实调整显示收集份数
	 */
	public AjaxResult AdjustSurveyDeliveryWanxOperate(HttpServletRequest request,HttpServletResponse response,Model model);
	/**
	 * 运营调整调转页面
	 */
	public void niSurveyDeliveryWanxOperateJump(HttpServletRequest request,HttpServletResponse response,Model model);
	
	/**
	 * 运营调整模态框
	 */
	public AjaxResult niSurveyDeliveryWanxOperateModal(HttpServletRequest request);
	/**
	 * 跳转到设置页面
	 */
	public void niSurveyDeliveryWanxSetUPJump(HttpServletRequest request,Model model);
	/**
	 * 保持设置
	 */
	public AjaxResult niSurveyDeliveryWanxSetUPSave(HttpServletRequest request,Model model);
	
	//列表查询
    List<NiSurveyDeliveryWanxVO> selectList(Map<String,Object> hashMap);
    
    //保存查问卷投放表
    int insert(SurveyDelivery record);
    
    SurveyDelivery selectByPrimaryKey(Integer deliveryid);
    
    //根据主键选择性修改信息
    int updateByPrimaryKeySelective(SurveyDelivery record);
	
	/**
	 * 启动定时任务
	 * @param time
	 * @param num
	 */
	public void niSurveyDeliveryWanxOperateSave(int sqnId2,float time, long num,int deliveryid,int trueOrderNum);
	
	//批量修改问卷状态
    int updateStatuByIds(Map<String, Object> retMap);
    
    //选择 模版
	void choseTemplatePage(HttpServletRequest request, Model model);
	//保存模版
	AjaxResult saveTemplatePage(HttpServletRequest request, Model model);
	// 筛选模版
	void screenSurveyTemplatePage(HttpServletRequest request, Model model);
}
